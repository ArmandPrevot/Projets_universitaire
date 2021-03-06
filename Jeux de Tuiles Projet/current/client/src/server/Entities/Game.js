const {TYPECASE, MSG_TYPES, MAP_TILE, MAP_SIZE, UI_REFRESH_HZ} = require('../../shared/constants');
const { Worker, MessageChannel } = require('worker_threads');
const MatriceMap = require('./MatriceMap');
const Player = require('./Player');


// TODO remonter les sockets dans le gameManager

class Game {
  constructor() {

    // Stock socket et player object avec pour ID l'ID socket
    this.sockets = [];
    this.players = [];
    this.tempInput = [];
    this.timePlayers = [];
    //Initialisation de la matrice
    this.map = new MatriceMap();

    this.lastUpdateTime = Date.now();
    //this.shouldSendUpdate = false;
    this.t = 0;
    this.dt = 0;
    this.runServiceMapPlayer({});
    
  }


  //  THREAD  ////////////////////////////////////////////////////////////////////////////////////////////////////////

  runServiceMapPlayer = (workerData) => {

    //Port communication entre threads
    const { port1, port2 } = new MessageChannel();
    //Start thread service.js pour generer cam player
    const worker = new Worker('./service.js', { workerData });
    //Update les datas du thread
    port1.on('message', (result) => { port1.postMessage({players: this.players, map: this.map.hashMap.keys, maptest: this.map.hashMap}); });
    //Update les maps des players => fin du traitement
    worker.on('message', (data) => { this.setMapPlayer(data)});
    worker.on('error', (error) => {console.log("Error from thread "+error)});
    worker.on('exit', (code) => {
      //if (code !== 0) throw new Error(`Worker stopped with exit code ${code}`);
      //worker.terminate();
    })
    //Envoi du port de communication au thread
    worker.postMessage({port: port2},[port2]);
  }

  //ACTION PLAYER////////////////////////////////////////////////////////////////////////////////////////////////////////

  addPlayer = (socket, username, idskin, t) => {
    //Save socket
    this.sockets[socket.id] = socket;
    var caseM = this.map.getRandomCaseMap(); // x & y  Generate a position to start this player at.
    while (!this.map.isCaseEmpty(caseM.x, caseM.y)){ caseM = this.map.getRandomCaseMap();}
    const xy = this.map.getXYCenterfromCase(caseM.x,caseM.y);
    //Generate new Player 
    this.players[socket.id] = new Player(socket.id, username, xy.x, xy.y,caseM.x,caseM.y, idskin);
    //Et l'ajoute a la map
    this.map.addSpawPlayer(this.players[socket.id]);
    //for sync client server
    if(this.timePlayers[socket.id] == undefined){
      this.timePlayers[socket.id] = { tt:0, rt:0, ot:0};
    }
    this.timePlayers[socket.id].ot = t;
    this.timePlayers[socket.id].rt = Date.now();
  }

  handleInput = (socket, dir) => {
    //Update la direction du joueur 
    if (this.players[socket.id]) this.players[socket.id].updateState(dir);// this.players[socket.id].updateState(dir);
  }

  //ACTION SERVER////////////////////////////////////////////////////////////////////////////////////////////////////////

  removePlayer= (id) => {
    delete this.sockets[id];
    delete this.players[id];
    delete this.timePlayers[id];
  }

  playerDie = (playerID) => {
    this.map.delCaseOf(playerID); //Remove toutes les cases du player
    const socket = this.sockets[playerID];
    // envoi un signal de game over au joueur
    if(socket != undefined){
      try{ socket.emit(MSG_TYPES.GAME_OVER); } catch(error){ console.log(error)}
    }
    this.removePlayer(playerID);     //remove le joueur de la partie
  }

  playerWin = () => {
    let tabSock = [...Object.keys(this.players)];
    //correction
    Object.keys(tabSock).forEach(playerID => { // update des joueurs
        this.playerDie(playerID);
    });
  }

  setMapPlayer = (tabmap) => {
    let { id, map, score} = tabmap;
    let nbTile = (MAP_SIZE/MAP_TILE)*(MAP_SIZE/MAP_TILE);

    if(id != undefined && this.players[id] != undefined){
      // si score = 0 cad plus d'air il meurt
      if(score == 0) this.playerDie(id);
      else if (score == nbTile) this.playerDie(id);// Revient a tuer le joueur TODO optimiser du genre reset Game avec GameManager
      else { // Update Map et score 
        this.players[id].map = map;
        this.players[id].score = score;
      }
    } 
  }

  //Joueur passe sur une case vide 
  actionEmpty = (player,x,y,value) => {
    this.map.setCaseOfMap(x,y,value);
    player.setLastArea(false);
  }

  //Joueur passe sur une case chemin
  actionPath = (elem,player,x,y,value) => {
    //Si c'est son ID loose sinon elimine le joueur associer a l'ID
    if(elem.idPlayer == player.id) this.playerDie(player.id);
    else { this.playerDie(elem.idPlayer); this.map.setCaseOfMap(x,y,value);}
    player.setLastArea(false);
  }

  // Joueur est sur une case AREA
  actionArea = (elem,player,x,y) => {
    //Si sur l'air il y as un chemin alors elimine le joueur associer a l'ID
    if(elem.path != undefined) this.playerDie(elem.path.idPlayer);
    if(elem.idPlayer == player.id) {
      //Si la derniere case du joueur n'était pas une AREA alors genere l'air du joueur
      if(!player.lastCaseArea) {
        this.map.pathToArea(player);
        player.setLastArea(true);
      }
    } // Sinon ajoute un chemin sur l'air d'un autre joueur 
    else {
      this.map.addPathOnArea(x,y,player);
      player.setLastArea(false);
    }
  }

  update = (dt,time) => { 

    //DeltaToime pour prendre en compte les deplacement en fonction du temps ecouler entre 2 frames
   /* const now = Date.now();
    const dt = (now - this.lastUpdateTime) / 1000;
    this.lastUpdateTime = now;*/
    this.t = time;
    this.dt = dt;
    // Copie du tableau des joueurs 
    let tabSock = [...Object.keys(this.players)];
    
    tabSock.forEach(playerID => { // update des joueurs

      //Get player et la socket associer a l'ID
      const player = this.players[playerID];
      const socket = this.sockets[playerID];

      if(player != undefined) {

      //update le joueur 
      player.update(this.dt);
      //Get x,y case en fonction des px
      var res = this.map.getCaseOfXY(player.x,player.y);
    
      if(player.setCurrentCase(res)) { // Joueur est dans une nouvelle case

        //Init value case
        var value = {type: TYPECASE.PATH, idPlayer: playerID, color: player.couleur};
        var elem = this.map.getElementMap(res.x,res.y);
        //Si pas referencer alors case vide
        if(elem == undefined) this.actionEmpty(player,res.x,res.y,value);
        else {
          switch (elem.type) {
            case TYPECASE.PATH: this.actionPath(elem,player,res.x,res.y,value); break;
            case TYPECASE.AREA: this.actionArea(elem,player,res.x,res.y); break;
            default: break;
          }
        }
      }
      }
    });

  }


  // UPDATE TO CLIENT 

  sendDatatoPlayer = () => {
    const leaderboard = this.getLeaderboard();
    Object.keys(this.players).forEach(playerID => { 
      const player = this.players[playerID];
      const socket = this.sockets[playerID];
      //Genere les donnees a envoyer au joueur
      var element = this.createUpdate(player, leaderboard);
      // Et envoi les 
      socket.emit(MSG_TYPES.GAME_UPDATE, element);
    });
    
  }

  getLeaderboard = () => {
    //retourne un tableau de 5 de username et score des 5 meilleurs players
    return Object.values(this.players)
      .sort((p1, p2) => p2.score - p1.score)
      .slice(0, 5)
      .map(p => ({ username: p.username, score: Math.round(p.score) }));
  }

  createUpdate = (player, leaderboard) => { 

    // TODO , retourne un tableau des autres joueur qui sont visible par le joueur 
    const nearbyPlayers = Object.values(this.players)
    .filter(p => p !== player/* && p.distanceTo(player) <= MAP_SIZE,*/)
    .map(p => p.serializeForUpdate());
    
    this.timePlayers[player.id].tt = Date.now();
   
    //Creer unse structure pour l'envoi au client
    return {
      t: Date.now(),
      me: player.serializeForUpdate(),
      map: player.map,
      minimap: this.map.getMiniMap(),
      playertime: this.timePlayers[player.id],
      others: nearbyPlayers,
      leaderboard,
    };
  }

  clone = () => {
    return { ...this };
    //return Object.assign({}, this);
  }

}

module.exports = Game;

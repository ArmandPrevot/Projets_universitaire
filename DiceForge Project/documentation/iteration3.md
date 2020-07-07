<h1>Itération n°3 :</h1>

<h3>Objectif :</h3> 
Jeu de 4 joueurs en ligne, deux dés composés de faces différentes (Or, Gloire), 9 manches de 4 tours de jeu, conditions de victoire : le joueur ayant le plus de points de gloire gagne.</br>

<i>User Story</i> : 
- En tant que Client, je souhaite pouvoir jouer au jeu à un jeu à 4 en ligne.
	- Tâche : Rendre le jeu en ligne.
	- Tâche : En tant que clients je veux pouvoir acheter une face en ligne.
	
- En tant que client je souhaite avoir un affichage plus clair.
	- Implémenter une classe qui gère l'affichage (factoriser System.out.println).

<i>Amélioration</i> : 
- JavaDoc.
- Tests Unitaires.
- Ajout d’états de jeu et d’états de tour de boucle.
- Le nom des joueurs est passé en énum java.
- La classe Game.java devient la classe Main.java afin de plus de lisibilité.
- Passage de tableau de PlayerManager à une HashMap<ID, PlayerManager>.
- L’instanciation des Faces de la Forge a été modifié.
- Ajout d’une classe GameLoop qui gère la boucle de jeu.
- Création d’un gestionnaire d’action du joueur dans une nouvelle classe AIManager.

<i>Ce que l'on a fait :</i>

- En tant que Joueur je peux :
	- jouer à une partie en ligne à 4 joueurs, avec 9 manches et 4 tours par manche
	- acheter une face et la mettre sur un dé (version en ligne)
	- voir clairement le déroulement d'une partie (affichage amélioré)

- Les améliorations techniques réalisées :
	- La boucle de jeu a changée, il y a maintenant 9 manches et 4 tours par manche. La boucle est maintenant gérée par une nouvelle classe, GameLoop.java.
	- La boucle de jeu est aussi maintenant reglée en fonction de différents états de jeu, réprésentés dans GameStateLoop.java.
	- Les noms des joueurs possibles sont maintenant stockés dans une énumeration.
	- Une nouvelle notion a été introduite, le Joueur Actif : à chaque fin de tour, le GameManager définit un Joueur Actif qui décidera si oui ou non il veut acheter, puis si oui ou non il peut acheter.
	- Les faces vendues dans la Forge sont instanciées différements, toutes les faces contenues dans la forge sont définis dans l'énumeration SimpleFace.java.
	- Les choix des joueurs sont maintenant contenus dans une nouvelle classe, AIManager, responsable de la stratégie du bot.
	- Une nouvelle classe Display.java gère l'affichage d'une partie.
	- L'évènenement du choix de la face à acheter dans la Forge est géré par ChoiceForge.java, responsable de transmettre toutes les informations nécessaires entre le Client et le Server pour l'évènement. 
	- La javaDoc a été retravaillée.
	- Les tests principaux ont été effectués.
	- La méthode checkWinner() a été améliorée en utilisant un Comparator (spécialisé pour comparer les points de gloires entre deux joueurs, SortByGlory.java).
	- L'affichage de la Forge a été retiré pour une question de lisibilité.
	- Les faces des dés d'un joueur lorsqu'il est Joueur Actif (une fois par manche).
	
 <h3>Objectif atteint :</h3> 
Jeu de 4 joueurs en ligne, deux dés composés de faces différentes (Or, Gloire), 9 manches de 4 tours de jeu, conditions de victoire : le joueur ayant le plus de points de gloire gagne.</br>

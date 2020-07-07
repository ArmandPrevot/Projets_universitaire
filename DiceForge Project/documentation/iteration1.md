<h1>Itération n°1 :</h1>

<h3>Objectif initial :</h3> 

Jeu à 1 joueur, condition de fin de partie : 3 tours de jeu, condition de victoire : le joueur a gagner plus de 10 gold

<i>Travail à faire :</i>

- Création d'un premier diagramme de classe, de séquence et de cas d'utilisation
- Création et commit du projet Maven
- Le joueur peut lancer deux dés, les résultats possibles : de 1 à 6 d'Or  
- Le joueur doit pouvoir effectuer un tour : un simple lancer de dé pour l'instant
- Le joueur rejoint la partie : un joueur rejoint la partie, la partie commence, il joue son tour
- Implémentation de la structure Client/Serveur (optionnel)
- Le jeu commence : jeu à 1 joueur qui lance son dé et obtient un montant d'or en fonction des faces (User Story)
- Écriture des tests

<i>Ce que l'on a fait :</i>

Tous les objectifs ci-dessus on été atteint, excepté pour le reseau qui lui a été reporté, les diagrammes de séquences et de cas d'utilisations.

Tous les objectifs atteints ont été amélioré, en voici le détail :
 - La structure Maven a été refaite, l'ancienne était malconfigurée
 - Il y a possibilité de jouer à 2 comme à 4 joueurs : généralisation des méthodes de Joueur
 - Refonte métier : ajout d'un GameManager, les dés ne sont plus contenus dans Joueur mais dans le GameManager, et les joueurs sont aussi contenus de cette nouvelle classe.
 - Une partie : les joueurs rejoignent, le tour commence, chacun lance ses dés et gagne de l'or en fonction des faces (1 à 6).
 - Condition de fin de partie : Au bout de quatre tours, celui qui a le plus d'or remporte la partie.
 
 <i>Tests en détails : </i>
 
DiceTest.java, la classe teste deux cas différents :
  
  - La méthode DiceRoll : Teste à l'aide d'un mock de la classe "Dice", la méthode lancerDe().  
  4 lancés truqués qui renvoie 5 (Chiffre arbitraire) | Expected : 5, Returned : 5
  
  - La méthode DiceRollWrong : Teste de la même manière la méthode lancerDe(), cette fois ci de manière négative.
  4 lancés truqués qui renvoie 2 (Chiffre arbitraire) | Expected : 2, Returned : 6
  
PlayerTest.java : La classe teste deux cas différents :
	
  - La méthode GoldInventoryTest : Teste à l'aide d'un mock de "Player" et de "Dice", la méthode returnGold() de Inventory. 
  Ajout de 6 gold dans l'inventaire et test de la méthode returnGold() | Expected : 6, Returned : 6
	
  - La méthode GoldInventoryInc : Teste à l'aide d'un mock de "Player" et de "Dice", la méthode returnGold() de Inventory.
  Ajout de 6 gold dans l'inventaire puis un lancer de dé truqué renvoie 6 qui sont ajouté en tant que Gold dans l'inventaire du joueur Expected : 12, Returned : 12
 
 <h3>Objectif atteint :</h3> 
 
 Jeu de 2 à 4 joueurs en local, 2 dés de faces unies, 4 tours de jeu, condition de victoire : le joueur qui a le plus de gold remporte la partie.

<h1>Itération n°2 :</h1>

<h3>Objectif :</h3> 

Jeu de 2 à 4 joueurs en local, deux dés composés de faces différentes (Or et Gloire), 5 tours de jeu, conditions de victoire : le joueur ayant le plus de points de gloire gagne.

<i>User Story</i>
- En tant que Joueur, je souhaite voir un récapitulatif des scores en fin de partie.
	- Tâche : afficher les scores de tous les joueurs en fin de partie
		- Sous-tâche : écrire une méthode "printScores()" dans GameManager qui affiche le résumé des scores.
		
- En tant que Joueur, je souhaite pouvoir avoir des dés composés de faces différentes (Or et Gloire) en début de partie.
	- Tâche : implémenter la nouvelle ressource sur les dés (Gloire).
		- Sous-tâche 1 : ajouter un nouveau type de face, pouvoir instancier des faces composés de points de Gloire.
		- Sous-tâche 2 : modifier la construction des dés, ils seront composés de faces d'or et de gloire.
		
- En tant que Joueur, je souhaite gagner une partie avec mes points de gloire.
	- Tâche 1 : implémenter la nouvelle ressource sur le joueur (Gloire).
		- Sous-tâche 1 : créer le type Gloire et l'ajouter à l'inventaire du joueur.
		- Sous-tâche 2 : ajouter la méthode permettant de gagner des points de Gloire.
	- Tâche 2 : implémenter la nouvelle condition de victoire.
		- Sous-tâche : modifier la méthode checkWinner() pour déterminer le gagnant en fonction de sa Gloire.
		
- En tant que Joueur, je souhaite pouvoir acheter une face à la boutique avec de l'or et la mettre sur un dé.
	- Tâche 1 : implémenter la boutique des faces.
		- Sous-tâche 1 : créer une nouvelle classe Boutique qui contiendra différentes faces (Or et Gloire).
		- Sous-tâche 2 : chaque face dans la boutique doit avoir un prix en or.
	- Tâche 2 : pouvoir acheter des faces dans la boutique.
		- Sous-tâche 1 : créer une méthode permettant à un joueur d'acheter une face de la boutique, s'il a assez d'Or pour.
		- Sous-tâche 2 : créer une méthode permettant d'affecter une nouvelle face à un dé du joueur.

<i>Amélioration technique</i>
- Le GameManager sera le nouveau responsable de la gestion des inventaires.
- L'inventaire sera le nouveau responsable des dés.
- La méthode checkWinner() devra gérer le cas d'égalité des scores (les deux joueurs gagnent dans ce cas).
- La documentation du code sera mise en place, ainsi que l'uniformisation de la langue (passage en anglais).
- Passer le switch de Dice.java en collection, et rendre privés les attributs de classes.
- Effectuer les tests.

<i>Ce que l'on a fait :</i>
- En tant que Joueur je peux :
	- voir un récapitulatif des scores en fin de partie.
	- avoir des dés composés de faces différentes (Or et Gloire) en début de partie.
	- gagner une partie avec mes points de gloire.
	- acheter une face à la boutique avec de l'or et la mettre sur un dé.
Pour cela nous avons respecter le plan construit ci-dessus pour l'Itération 2.

- Les améliorations techniques réalisées :
	- Nous avons fait en sorte que le GameManager soit responsable de la gestion des inventaires, qui lui (l'inventaire) est le nouveau responsable des dés.
	- La méthode checkWinner() est à jour et gère bien les cas d'égalité.
	- La documentation du code a été fait de même que le code a été entièrement uniformisé en anglais (variable, commentaire... tout est en anglais)
	- Le switch de Dice.java a été passé en collection.
	- Les tests ont eux aussi bien été réalisé.
	
 <h3>Objectif atteint :</h3> 
 
 Jeu de 2 à 4 joueurs en local, 2 dés de faces différentes (deux types de faces possibles GLORY et GOLD, avec des valeurs variables), 5 tours de jeu, les joueurs peuvent acheter des faces durant la partie, condition de victoire : le joueur qui a le plus de gloire remporte la partie, un tableau des scores est affiché juste avant d'annoncer le vainqueur, il y a possibilité d'égalité.

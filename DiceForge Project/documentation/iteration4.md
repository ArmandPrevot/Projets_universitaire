<h1>Itération n°4 :</h1>

<h3>Objectif :</h3> 
Jeu de 4 joueurs en ligne, deux dés composés de faces différentes (Or, Gloire, Lunaire, Solaire), 9 manches de 4 tours de jeu, conditions de victoire : le joueur ayant le plus de points de gloire gagne.</br>

<i>User Story</i> : 
- En tant que Joueur, je souhaite pouvoir acheter des faces (Or, Gloire, Lunaire, Solaire) avec mon Or.
  - Tâche : Implémenter la totalité des ressources disponibles (Lunaire, Solaire).
 
- En tant que Joueur, je souhaite pouvoir acheter des cartes (Faveurs) grâce à mes points Lunaires/Solaires.
  - Tâche : Implémenter les cartes de faveurs.
  - Tâche : Implémenter l’achat des faveurs.
  
- En tant que Joueur, je souhaite posséder un capital en or équivalent à mon tour de jeu.
  - Tâche : Ordonner les joueurs au début de la partie.

<i>Amélioration</i> : 
- JavaDoc.
- Tests Unitaires.

<i>Ce que l'on a fait :</i>

- En tant que Joueur je peux :
	- acheter des faces unis de chaque type (Or, Gloire, Lunaire, Solaire) avec mon Or et pouvoir les forger
	- acheter des cartes grâce à mes points Lunaires/Solaires
	- posséder un capital en or équivalent à mon rang en début de partie

- Les améliorations techniques réalisées :
  - Le projet est maintenant découpé en module Maven
  - 12 cartes simples (soit Lunaire, soit solaire) différentes ont été implémentées (en 4 exemplaires chacunes)
  - Les îles ont été implémentées, chaque île contient deux paires de 4 cartes similaires respectivement
  - Le nombre de faces vendus a été augmenté statiquement pour 4 joueurs (60 faces)
  - De nouvelles faces unis sont disponibles en vente (Lunaire/Solaire)
  - Un événement ChoiceExploit a été rajouté pour gérer le choix du client lors de l'achat d'une carte.
  - Un événement ChoiceExploitForge a été rajouté pour gérer le choix du client entre la forge et les iles
  - La notion de rang a été introduite, elle détermine le nombre d'Or de départ, mais l'ordre des rangs est statique
  
 <h3>Objectif atteint :</h3> 
Jeu de 4 joueurs en ligne, deux dés composés de faces différentes (Or, Gloire, Lunaire Solaire), 12 cartes simples, 9 manches de 4 tours de jeu, conditions de victoire : le joueur ayant le plus de points de gloire gagne.</br>

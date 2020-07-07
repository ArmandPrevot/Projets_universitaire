<h1>Itération n°5 :</h1>

<h3>Objectif :</h3> 
Jeu a 4 joueurs en réseau, deux dés composés de faces différentes (Or, Gloire, Lunaire, Solaire, Faces spéciales), 9 manches de 4 tours, conditions de victoire : le joueur ayant le plus de points de gloire gagne.

<i>User Story</i> : 
- En tant que Joueur, je souhaite pouvoir acheter des faces hybdrides
      - Implementer un nouveau type de face
      - Implementer l'achat des ces nouvelles faces
      
- En tant que Joueur, je souhaite pouvoir acheter des cartes avec un effet.
      - Tâche : Implémenter les effets des cartes
  
- En tant que Joueur, je souhaite avoir une forge plus spécialisée
      - Implémenter les différents bassins de la forge

<i>Amélioration</i> : 
- Déterminer le nombre de faces et de cartes en fonction du nombre de joueurs
- Tester correctement les cartes
- Améliorer l'IA : refaire l'événement ChoiceForge pour que le joueur parcours la forge pour acheter une face (au lieu d'un random)
- Améliorer l'IA : faire en sorte que si le joueur ne peut pas acheter de faces, il essaye d'acheter une carte (et inversement)
- Synchroniser le client serveur en vue du multi-partie
- JavaDoc
- Tests Unitaires.

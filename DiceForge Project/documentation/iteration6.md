<h1>Itération n°6 :</h1>

<h3>Objectif :</h3> 
Jeu a 4 joueurs en réseau, deux dés composés de faces différentes (Or, Gloire, Lunaire, Solaire, Faces spéciales), 9 manches de 4 tours, conditions de victoire : le joueur ayant le plus de points de gloire gagne.

<i>User Story</i> : 
- En tant que Joueur, je souhaite pouvoir acheter des faces hybdrides
      - Implementer un nouveau type de face
      - Implementer l'achat des ces nouvelles faces
- En tant que joueur, je souhaite pouvoir acheter des cartes avec des effets diversifiés
      - Implémenter une (ou plusieurs) cartes plus complexes

	  
<i>Amélioration</i> : 
- Nettoyer le code (enlever ce qui n'est pas indispensable, formaliser etc...)
- Synchroniser le client serveur en vue du multi-partie
- JavaDoc
- Tests Unitaires.

<i>Ce que l'on a fait : </i> 
- En tant que Joueur, je souhaite pouvoir acheter des faces hybdrides
	- Implementer un nouveau type de face
		- Implementer la possibilité de fixer une face hybdride sur un dé
		- Implementer le gain de double ressources lorsqu'on tombe sur une face hybdride
	- Implementer l'achat des ces nouvelles faces
		- Ajouter les nouvelles faces dans les bons bassins
		- Pouvoir acheter ces nouvelles faces

- En tant que Joueur, je souhaite pouvoir acheter des faces hybdrides à choix
	- Implementer un nouvel événement client serveur
		- Demandez au joueur quelle face il souhaite lorsqu'il tombe sur une face à choix
	- Implementer l'achat des ces nouvelles faces
		- Ajouter les nouvelles faces dans les bons bassins
		- Pouvoir acheter ces nouvelles faces

<i>Amélioration technique : </i> 
- L'intelligence de base (l'IA Random) a été améliorée (refonte de l'événement du choix de la face dans la forge).
- On peut desormais lancer N parties d'affilées (voir readme.md), la synchronisation client-serveur à été aussi revue en ce sens.
- L'affichage est centralisé dans un classe Singleton, qui propose deux affichages distincts selon le nombre de parties lancées.
- Une partie du retard sur les tests a été rattrapé, une grande partie de la javaDoc est faite.

<i>Ce que l'on a pas fait : </i>
- L'implémentation des faces spéciales n'as pas été fait, et a été remplacé par l'implémentation des faces hybrides à choix.

# Projet Assignements
Dans le cadre du master MBDS de la MIAGE de Sophia-Antipolis nous avons réalisé une application web permettant aux étudiants de rendre des assignements à leurs professeurs.

## Équipe
L'équipe pour ce projet est composée de Armand Prévot et Dorian Chapoulié.

## Lancer le projet localement
- Angular : ```npm install``` suivit de ```ng serve``` dans front-end/assignements-app
- Node : ```npm install``` suivit de ```node index.js``` dans /api/

## Accès au site hébergé
http://54.37.152.229/

## Comptes pour tester
mdp: azerty

Etudiants: 
hrosewall0@i2i.jp 
dsare1@twitpic.com
rissit2@unesco.org
ksacks3@last.fm

Profs:
buffa@gmail.com
galli@galli.fr
amosse@amosse.fr

Admin:
dorian.chapoulie@gmail.com

## Fonctionnalités
- Gestion de login/password selon le workflow classique (JWT Token)
- Gestion de roles (Etudiant, Professeur, Admin) selon les règles suivantes :
  - Un étudiant peut uniquement créer des assignements et les consulter,
  - Un professeur peut consulter/éditer des assignements (pour l'évaluer et le rendre),
  - Un administrateur peut consulter, créer, modifier, supprimer sans contrainte.
- Consultation des assignements (page principale) : 
  - Sous forme de liste de material cards dans deux panels séparés sur la même page
  - Chaque card possède un bouton menant vers son détails (avec les commentaires du professeur et la note)
  - Pagination
  - Drag and drop permettant de rendre un assignement, reservé aux profs et aux admins
  - Un assignement non noté ne peut pas être rendu, par conséquent celui-ci ne sera pas drag and droppable tant qu'il ne sera pas noté (liseret jaune autour de la Card)
  - Un étudiant voit ses propres assignements, un professeur voit ceux de sa matière et un administrateur voit tous les assignements confondus
- Création d'un assignement :
  - Sous forme de formulaire, on y accède depuis la navbar
  - Reservé aux étudiants et aux admins
  - Propose un choix fixe de matières (et associe automatiquement le prof et l'image illustrant la matière)
- Edition des assignements :
  - Sous forme de formulaire, on y accède depuis la vue de détails
  - Reservé aux profs et aux admins
  - Si on met une note, l'assignement pourra être rendu (soit dans le formulaire, soit par le drag n drop)
  - Propose un choix fixe de matières (et associe automatiquement le prof et l'image illustrant la matière)
- Suppression d'un assignement :
  - Reservé à l'admin
  - On y accède au travers de la vue de détails
- Gestion globale des erreurs au travers des interceptors Angular
- Gesstion des notifications avec Toastr

## Architecture
- Front Angular 13
- Back Node JS
- BDD Mongo
- Hebergement sur un VPS privé

## Vidéo de démonstration
https://www.youtube.com/watch?v=aOO3yrtRwb4

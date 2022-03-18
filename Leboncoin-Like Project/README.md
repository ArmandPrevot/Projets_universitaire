# Lecoincoin
 ### Réalisateur: Dorian Chapoulié et Armand Prévot
 ### Encadreur : Gregory Galli

## Lancer l'API
Pour lancer l'api il faudra ouvrir le projet IntelliJ (back end) et le lancer. Une fois cela fait, le back est oppérationel.

## Lancer le front
Pour lancer le front il suffit d'ouvrir le dossier "Front-end" avec Visual Studio Code et ouvrir un terminal. Ensuite, il faut rentrer ces deux commandes l'une après l'autre !
### npm i
### ng serve

## Test
Il existe une collection PostMan servant de tests. Plusieurs routes seront appelé, certaines sont erroné et retourneron un message d'erreur (mot clé ERREUR devant la requete)

## Ce que l'on a fait
### Back
Le back est entièrement dévelopé et parfaitement fonctionnel (testé via Postman), il permet les opérations CRUD sur User, Annonce et Illustration.
### Front
Le front permet :
<ul>
 <li>CRUD sur les utilisateurs</li>
 <li>Deletion sur une annonce</li>
 <li>Authentification par JWT</li>
 <li>Visualisation des utilisateurs et des annonces</li>
</ul>

### Ce que l'on doit améliorer
Il manque au front quelques fonctionnalités pour que celui-ci soit complet, notament : une gestion des images (édition comme visualisation d'annonces) ainsi que l'édition et la création d'une annonce. Aussi, une meilleure gestion de l'ajout et de l'edition d'un user (en incluant la modification de role) serait préférable.

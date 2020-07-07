<?php
/**
 * Created by PhpStorm.
 * User: doria
 * Date: 28/12/2018
 * Time: 13:21
 */
session_start([
    'cookie_lifetime' => 3600,
]);
?>



<!DOCTYPE html>
<html lang="fr">
<title>E-commerce</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat"><link rel="icon" type="image/png" href="image/logo.png" />

<style>
    body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
    .w3-bar,h1,button {font-family: "Montserrat", sans-serif}
    .fa-anchor,.fa-coffee {font-size:200px}
</style>
<body>

<div class="w3-top">
    <div class="w3-bar w3-red w3-card w3-left-align w3-large">
        <a href="index.php"     class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Accueil</a>
        <a href="Produits.php"  class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Produits</a>
        <a href="MonCompte.php" class="w3-bar-item w3-button w3-padding-large w3-white">Mon compte</a>
        <a href="Panier.php"             class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon panier</a>
    </div>

</div>


</body>
</html>


<?php

if(isset($_SESSION["isco"]) === true && $_SESSION["isco"] === true ){
    $bdd = new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");
    //$bdd = new mysqli("localhost", "root", "", "cd706595");
    $sql = "SELECT admin FROM client where id = '". $_SESSION["id"] . "';";

    if ($result = $bdd->query($sql)) {
        while ($row = $result->fetch_assoc()) {
            if ($row["admin"] != 1) {
                echo"<p  class=\"erreur\">Vous avez pas les droits ! </p>";
                echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr'\" />";
            }
        }

    } else {
        echo "<p class=\"erreur\">ERREUR</p>";
        echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr'\" />";
    }

    echo"<a class=\"formulaire\" href=\"GestionClients.php\" class=\"w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white\">Gestion clients</a>";
    echo"<a class=\"formulaire\" href=\"GestionFournisseur.php\" class=\"w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white\">Gestion fournisseurs</a>";
    echo"<a class=\"formulaire\" href=\"GestionProduit.php\" class=\"w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white\">Gestion produits</a>";


}else{

    echo"<p  class=\"erreur\">Vous devez vous connecté pour acceder à votre compte ! </p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/Connection.php'\" />";
}

?>

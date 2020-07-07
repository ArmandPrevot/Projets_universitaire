<?php

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

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">





<style>

    body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}

    .w3-bar,h1,button {font-family: "Montserrat", sans-serif}

    .fa-anchor,.fa-coffee {font-size:200px}

</style>

<body>



<!-- Navbar -->

<div class="w3-top">

    <div class="w3-bar w3-red w3-card w3-left-align w3-large">

        <a href="index.php"     class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Accueil</a>

        <a href="Produits.php"  class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Produits</a>

        <a href="MonCompte.php" class="w3-bar-item w3-button w3-padding-large w3-white">Mon compte</a>

        <a href="Panier.php"            class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon panier</a>

    </div>



</div>



<?php



if($_SESSION["isco"] == true){

    echo "<p class=\"erreur\">Vous êtes dejà connectés !</p>";

    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/MonCompte.php'\" />";

    return;

}



//$bdd = new mysqli("localhost", "root", "", "cd706595");

$bdd = new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");

$pattern = "/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/i";

$connected = false;



if( isset($_POST["email"]) === true && preg_match($pattern, $_POST["email"]) !== 1){



    echo "<p class=\"erreur\">Email invalide !</p>";



}else if(isset($_POST["mdp"]) === true && strpos($_POST["mdp"], "'") !== false){

    echo "<p class=\"erreur\">Le mot de passe ne peut pas contenir d'apostrophe </p>";



}else if(isset($_POST["connection"]) === true) {



    $sql = "SELECT * FROM client where email = '" . $_POST["email"] . "' and mdp = '" . md5($_POST["mdp"]) . "';";





    if ($result = $bdd->query($sql)) {

        while ($row = $result->fetch_assoc()) {

            if ($row["email"] === $_POST["email"] && $row["mdp"] === md5($_POST["mdp"])) {

                $connected = true;

                $_SESSION['isco'] = true;

                $_SESSION["id"] = $row["id"];

            }

        }



    } else {

        echo "<p class=\"erreur\">ERREUR</p>";

    }



    if ($connected) {

        echo "<p class=\"ok\">Vous êtes désormais connecté.</p>";

        echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/MonCompte.php'\" />";



    } else {

        echo "<p class=\"erreur\">Identifiants incorrects</p>";

        $_SESSION['isco'] = false;

    }



}



echo"<form method=\"post\"  class=\"formulaire\" >";

echo"<label>Adresse e-mail: <input type=\"text\" name=\"email\"/></label><br/>";

echo"<label>Mot de passe: <input type=\"password\" name=\"mdp\"/></label><br/>";

echo"</br>";

echo"<input type=\"submit\" name=\"connection\" value=\"Se connecter\"/>";

echo"</form>";



$bdd->close();



?>





</body>

</html>




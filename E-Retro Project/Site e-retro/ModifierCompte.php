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
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat"><link rel="icon" type="image/png" href="image/logo.png" />


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

if($_SESSION["isco"] == false){
    echo "<p class=\"erreur\">Vous devez être connecté !</p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/Connection.php'\" />";
    return;
}

$sqlSend = false;

if(isset($_POST["modifier"]) === true ){

    //$bdd = new mysqli("localhost", "root", "", "cd706595");
    $bdd = new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");
    $pattern = "/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/i";


    if( isset($_POST["email"]) === true && preg_match($pattern, $_POST["email"]) !== 1){

        echo "<p class=\"erreur\">Email invalide !</p>";

    }else if(strlen($_POST["nom"]) <= 0 || strpos($_POST["nom"], "'") !== false){
        echo "<p class=\"erreur\">Nom invalide ! </p>";

    }else if(strlen($_POST["prenom"]) <= 0 || strpos($_POST["prenom"], "'") !== false){
        echo "<p class=\"erreur\">Prenom invalide !</p>";

    }else if(strlen($_POST["mdp"]) <= 5 || strpos($_POST["mdp"], "'") !== false){
        echo "<p class=\"erreur\">Mot de passe invalide: Il doit faire 6 caractères minimum</p>";

    }else if(strlen($_POST["addr"]) <= 0 || strpos($_POST["addr"], "'") !== false){
        echo "<p class=\"erreur\">Adresse invalide !</p>";

    }else {

        $canBeRegistered = true;

        $sql = "SELECT * FROM client where email = '" . $_POST["email"] . "';";
        $result = $bdd->query($sql);

        if ($result = $bdd->query($sql)) {
            while ($row = $result->fetch_assoc()) {
                if ($row["email"] === $_POST["email"] && $row["id"] !== $_SESSION["id"]) {
                    $canBeRegistered = false;
                    break;
                }
            }

        } else {
            echo "<p class=\"erreur\">Erreur interne</p>";
        }

        $sql = "UPDATE client
SET nom = '". $_POST["nom"] ."', prenom = '". $_POST["prenom"] ."', email = '". $_POST["email"] ."', mdp = '". $_POST["mdp"]  ."', adresse ='". $_POST["addr"] ."' WHERE id = ". $_SESSION["id"] . ";";

        if($canBeRegistered === true && $result = $bdd->query($sql) ) {
            $sqlSend = true;
            echo"<p class=\"ok\"> Modifications validés ! </p>";
            echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/MonCompte.php'\" />";

            $_SESSION["isco"] = true;
        }

        if($canBeRegistered === false){
            echo "<p class=\"erreur\">Email déjà prise !</p>";
            $sqlSend = false;
        }

    }


}


if($sqlSend === false) {
    echo "<form method=\"post\"  class=\"formulaire\" >";
    echo "<label>Nom: <input type=\"text\" name=\"nom\" /></label><br/>";
    echo "<label>Prénom: <input type=\"text\" name=\"prenom\" /></label><br/>";
    echo "<label>Adresse e-mail: <input type=\"text\" name=\"email\"/></label><br/>";
    echo "<label>Mot de passe: <input type=\"password\" name=\"mdp\"  /></label><br/>";
    echo "<label>Adresse: <input type=\"text\" name=\"addr\" /></label><br/>";
    echo "</br>";
    echo "<input type=\"submit\" name=\"modifier\" value=\"Valider\"/>";
    echo "</form>";
}

?>


</body>
</html>


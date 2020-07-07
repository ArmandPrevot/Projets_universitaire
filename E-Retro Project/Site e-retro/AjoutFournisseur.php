
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
        <a href="Panier.php"           class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon panier</a>
    </div>

</div>


</body>
</html>


<?php
/**
 * Created by PhpStorm.
 * User: doria
 * Date: 28/12/2018
 * Time: 13:47
 */

session_start([
    'cookie_lifetime' => 3600,
]);


//$bdd = new mysqli("localhost", "root", "", "cd706595");
$bdd =new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");
$sql = "SELECT admin FROM client where id = '". $_SESSION["id"] . "';";

if ($result = $bdd->query($sql)) {
    while ($row = $result->fetch_assoc()) {
        if ($row["admin"] != 1) {
            echo"<p  class=\"erreur\">Vous avez pas les droits ! </p>";
            echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/MonCompte.php'\" />";
            return;
        }
    }

} else {
    echo "<p class=\"erreur\">ERREUR</p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr'\" />";
}


if(isset($_SESSION["isco"]) === true && $_SESSION["isco"] === true ){
    $sqlSend = false;

    $pattern = "/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/i";

    if(strlen($_POST["nom"]) <= 0 || strpos($_POST["nom"], "'") !== false){
        echo "<p class=\"erreur\">Nom invalide ! </p>";

    }else if(strlen($_POST["ville"]) <= 0 || strpos($_POST["ville"], "'") !== false){
        echo "<p class=\"erreur\">Ville invalide !</p>";

    }else {

        $canBeRegistered = true;

        $sql = "SELECT * FROM fournisseur where nom = '" . $_POST["nom"] . "' and ville = '". $_POST["ville"] ."';";
        $result = $bdd->query($sql);

        if ($result = $bdd->query($sql)) {
            while ($row = $result->fetch_assoc()) {
                if ($row["nom"] === $_POST["nom"] && $row["ville"] === $_POST["ville"]) {
                    $canBeRegistered = false;
                    break;
                }
            }

        } else {
            echo "<p class=\"erreur\">Erreur interne</p>";
        }

        $sql = "INSERT INTO fournisseur VALUES ('0', '". $_POST["nom"] . "', '". $_POST["ville"] . "' )";

        if($canBeRegistered === true && $bdd->query($sql) ) {
            $sqlSend = true;
            echo"<p class=\"ok\"> Nouveau fournisseur créer ! </p>";
            echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/GestionFournisseur.php'\" />";

            $_SESSION["isco"] = true;
        }

        if($canBeRegistered === false){
            echo "<p class=\"erreur\">Fournisseur existant !</p>";
            $sqlSend = false;
        }


    }



    if($sqlSend === false) {
        echo "<form method=\"post\"  class=\"formulaire\" >";
        echo "<label>Nom: <input type=\"text\" name=\"nom\"/></label><br/>";
        echo "<label>Ville: <input type=\"text\" name=\"ville\"/></label><br/>";
        echo "<input type=\"submit\" name=\"valider\" value=\"Valider\"/>";
        echo "</form>";
    }

}else{
    echo"<p  class=\"erreur\">Vous devez vous connecté pour modifier un compte ! </p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/Connection.php'\" />";

}
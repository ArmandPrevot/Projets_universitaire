
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
        <a href="Panier.php"            class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon panier</a>
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
$bdd = new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");
$sql = "SELECT admin FROM client where id = '". $_SESSION["id"] . "';";

if ($result = $bdd->query($sql)) {
    while ($row = $result->fetch_assoc()) {
        if ($row["admin"] != 1) {
            echo"<p  class=\"erreur\">Vous avez pas les droits ! </p>";
            echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr'\" />";
            return;
        }
    }

} else {
    echo "<p class=\"erreur\">ERREUR</p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr'\" />";
}


if(isset($_SESSION["isco"]) === true && $_SESSION["isco"] === true ){
    $sqlSend = false;
    $id = -1;
    $sql = "SELECT * FROM client;";

    if ($result = $bdd->query($sql)) {
        while ($row = $result->fetch_assoc()) {

            if (isset($_POST[$row["id"]])) {
                $id = $row["id"];

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
                                if ($row["email"] === $_POST["email"] && $row["id"] != $id) {
                                    $canBeRegistered = false;
                                    break;
                                }
                            }

                        } else {
                            echo "<p class=\"erreur\">Erreur interne</p>";
                        }

                        if(isset($_POST["scales"]) === true){
                            $sql = "UPDATE client
SET nom = '". $_POST["nom"] ."', prenom = '". $_POST["prenom"] ."', email = '". $_POST["email"] ."', mdp = '". md5($_POST["mdp"])  ."', adresse ='". $_POST["addr"] ."', admin='1' WHERE id = ". $id . ";";

                        }else{
                            $sql = "UPDATE client
SET nom = '". $_POST["nom"] ."', prenom = '". $_POST["prenom"] ."', email = '". $_POST["email"] ."', mdp = '". md5($_POST["mdp"])  ."', adresse ='". $_POST["addr"] ."', admin='0' WHERE id = ". $id . ";";

                        }

                        if($canBeRegistered === true && $result = $bdd->query($sql) ) {
                            $sqlSend = true;
                            echo"<p class=\"ok\"> Modifications validés ! </p>";
                            echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/GestionClients.php'\" />";

                            $_SESSION["isco"] = true;
                        }

                        if($canBeRegistered === false){
                            echo "<p class=\"erreur\">Email déjà prise !</p>";
                            $sqlSend = false;
                        }


                    }



                if($sqlSend === false) {
                    echo "<form method=\"post\"  class=\"formulaire\" >";
                    echo "<label>Nom: <input type=\"text\" name=\"nom\"/ value=\"". $row["nom"] ."\"></label><br/>";
                    echo "<label>Prénom: <input type=\"text\" name=\"prenom\"/ value=\"". $row["prenom"] ."\"></label><br/>";
                    echo "<label>Adresse e-mail: <input type=\"text\" name=\"email\"/ value=\"". $row["email"] ."\"></label><br/>";
                    echo "<label>Mot de passe: <input type=\"text\" name=\"mdp\"/value=\"". $row["mdp"] ."\"></label><br/>";
                    echo "<label>Adresse: <input type=\"text\" name=\"addr\"/value=\"". $row["adresse"] ."\"></label><br/>";
                    echo "</br>";
                    if($row["admin"] == 1){
                        echo "<input type=\"checkbox\"  name=\"scales\" checked>";
                    }else{
                        echo "<input type=\"checkbox\"  name=\"scales\">";
                    }

                    echo "<label for=\"scales\"> Admin</label>";
                    echo "<input type=\"submit\" name=\"". $row["id"] ."\" value=\"Valider\"/>";
                    echo "</form>";
                }
                Break;
            }

        }

        if($id == -1){
            echo "<p class=\"erreur\">ERREUR 1</p>";
            echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/GestionClients.php'\" />";
            return;
        }

    } else {
        echo "<p class=\"erreur\">ERREUR 2</p>";
        echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr'\" />";
    }


}else{
    echo"<p  class=\"erreur\">Vous devez vous connecté pour modifier un compte ! </p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/Connection.php'\" />";

}

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
/**
 * Created by PhpStorm.
 * User: doria
 * Date: 28/12/2018
 * Time: 13:47
 */

session_start([
    'cookie_lifetime' => 3600,
]);


$bdd =new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");
//$bdd = new mysqli("localhost", "root", "", "cd706595");
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

    if(strlen($_POST["ref"]) <= 0 || strpos($_POST["ref"], "'") !== false){
        echo "<p class=\"erreur\">Reference invalide ! </p>";

    }else if(strlen($_POST["description"]) <= 0 || strpos($_POST["description"], "'") !== false){
        echo "<p class=\"erreur\">Description invalide !</p>";

    }else if(strlen($_POST["prix_publique"]) <= 0 || strpos($_POST["prix_publique"], "'") !== false){
        echo "<p class=\"erreur\">Prix publique invalide !</p>";

    }else if(strlen($_POST["prix_achat"]) <= 0 || strpos($_POST["prix_achat"], "'") !== false) {
        echo "<p class=\"erreur\">Prix achat invalide !</p>";

    }else if(strlen($_POST["titre"]) <= 0 || strpos($_POST["titre"], "'") !== false){
        echo "<p class=\"erreur\">Titre invalide !</p>";

    }else if(strlen($_POST["descriptif"]) <= 0 || strpos($_POST["descriptif"], "'") !== false){
        echo "<p class=\"erreur\">Descriptif invalide !</p>";
    }else if($_FILES["image"]["type"] != "image/jpeg") {
        echo "<p class=\"erreur\">Image invalide !</p>";
    }else if($_FILES["icone"]["type"] != "image/jpeg") {
        echo "<p class=\"erreur\">Icone invalide !</p>";
    }else{

        $sql = "SELECT * FROM produit where reference= '" . $_POST["ref"] . "';";
        $result = $bdd->query($sql);

        $canBeRegistered = true;
        if ($result = $bdd->query($sql)) {
            while ($row = $result->fetch_assoc()) {
                if ($row["reference"] === $_POST["ref"]) {
                    $canBeRegistered = false;
                    echo "<p class=\"erreur\">Erreur: la référence existe déjà !</p>";
                    break;
                }
            }

        } else {
            echo "<p class=\"erreur\">Erreur interne</p>";
        }


        $data = addslashes(file_get_contents($_FILES["image"]["tmp_name"]));
        $data2 = addslashes(file_get_contents($_FILES["icone"]["tmp_name"]));


        $sql = "INSERT INTO produit VALUES ('0', '". $_POST["ref"] . "', '". $_POST["description"] . "', '" . $_POST["prix_publique"] . "', '". $_POST["prix_achat"] . "', '" . $data. "' , '".  $data2 ."', '". $_POST["titre"] ."', '". $_POST["descriptif"] . "')";
        if($bdd->query($sql) ) {
            $sqlSend = true;

            $sql = "SELECT id FROM produit where reference='". $_POST["ref"] ."';";
            $id = -1;
            if ($result = $bdd->query($sql)) {
                while ($row = $result->fetch_assoc()) {
                    $id = $row["id"];
                }
            }

            $ssql = "Insert INTO gestion_stock VALUES ('". $id ."', '". $_POST["quantite"] ."');";
            $bdd->query($ssql);
            echo"<p class=\"ok\"> Nouveau produit créer ! </p>";
            echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/GestionProduit.php'\" />";

            $_SESSION["isco"] = true;
        }

    }



    if($sqlSend === false) {
        echo "<form method=\"post\"  class=\"formulaire\"  enctype=\"multipart/form-data\"  >";
        echo "<label>Refrence: <input type=\"text\" name=\"ref\"/></label><br/>";
        echo "<label>Description:  <textarea name=\"description\" ></textarea><br/>";
        echo "<label>Prix publique<input type=\"number\" name=\"prix_publique\"/ ></label><br/>";
        echo "<label>Prix achat <input type=\"number\" name=\"prix_achat\"/></label><br/>";
        echo "<label>image: <input type=\"file\" name=\"image\" id=\"image\" /></label><br/>";
        echo "<label>Icone: <input type=\"file\" name=\"icone\" id=\"icone\"    /></label><br/>";
        echo "<label>Titre: <input type=\"text\" name=\"titre\"/ ></label><br/>";
        echo "<label>Descriptif:  <textarea name=\"descriptif\" ></textarea></label><br/>";
        echo "<label>Quantité <input type=\"number\" name=\"quantite\" value=\"1\"/></label><br/>";

        echo "</br>";
        echo "<input type=\"submit\" name=\"valider\" value=\"Valider\"/>";
        echo "</form>";

        echo "<br>";
        echo "<br>";
        echo "<br>";
    }

}else{
    echo"<p  class=\"erreur\">Vous devez vous connecté pour modifier un compte ! </p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/Connection.php'\" />";

}

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

//1
//1s
$bdd = new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");
//$bdd = new mysqli("localhost", "root", "", "cd706595");
$sql = "SELECT * FROM produit;";

if ($result = $bdd->query($sql)) {
    while ($row = $result->fetch_assoc()) {

        if(isset($_POST[$row["id"]. "s"])){
            $sql = "DELETE FROM produit where id = ". $row["id"] .";";
            $bdd->query($sql);
        }

    }

} else {
    echo "<p class=\"erreur\">ERREUR</p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr'\" />";
    return;
}


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

    echo "<form method=\"post\" action=\"AjouterProduit.php\"/>";
    echo"<p class=\"erreur\"><input type=\"submit\" name=\"ajouter\" value=\"Ajouter produit\"/></p>";
    echo "</form>";
    echo "<form method=\"post\" >";
    echo "<p class=\"erreur\">Reference: <input type=\"text\" name=\"ref\"/></p>";
    echo "<p class=\"erreur\"> Titre: <input type=\"text\" name=\"titre\"/></p>";
    echo"<p class=\"erreur\"><input type=\"submit\" name=\"rechercher\" value=\"Rechercher produit\"/></p>";
    echo "</form>";

    echo "<p class=\"erreur\">Liste des produit:</p>";

    $sql = "";
    if(isset($_POST["ref"]) === true && strlen($_POST["ref"]) > 0){
        if(isset($_POST["titre"]) === true && strlen($_POST["titre"]) > 0){
            $sql = "SELECT * FROM produit where reference like '%". $_POST["ref"] ."%' and titre like '%". $_POST["titre"] ."';";
        }else {
            $sql = "SELECT * FROM produit where reference like '%" . $_POST["ref"] . "%';";
        }
    }else if(isset($_POST["titre"]) === true && strlen($_POST["titre"]) > 0) {
        $sql = "SELECT * FROM produit where titre like '%" . $_POST["titre"] . "%';";
    }else{
        $sql = "SELECT * FROM produit;";
    }


    if ($result = $bdd->query($sql)) {
        while ($row = $result->fetch_assoc()) {

            echo "<table class=\"ok\">";

            echo "<tr>";
            echo"<td>Ref:</td>";
            echo "<td>". $row["reference"] ."</td>";
            echo"</tr>";

            echo "<tr>";
            echo"<td>Titre:</td>";
            echo "<td>". $row["titre"] ."</td>";
            echo"</tr>";

            echo "<tr>";
            echo"<td>Image:</td>";
            echo '<td><img src="data:image/png;base64,' . base64_encode($row["image"]) . '"/></td>';
            echo"</tr>";

            echo "<tr>";
            echo"<td>Icone:</td>";
            echo '<td><img src="data:image/png;base64,' . base64_encode($row["icone"]) . '"/></td>';
            echo"</tr>";

            echo "<tr>";
            echo"<td>Prix publique:</td>";
            echo "<td>". $row["prix_publique"] ." €</td>";
            echo"</tr>";

            echo "<tr>";
            echo"<td>Prix achat:</td>";
            echo "<td>". $row["prix_achat"] ." €</td>";
            echo"</tr>";

            echo "<tr>";
            echo"<td>Descriptif:</td>";
            echo "<td>". $row["descriptif"] ."</td>";
            echo"</tr>";

            $ssql = "SELECT * FROM gestion_stock where id_produit = ". $row["id"] .";" ;
            $rresult = $bdd->query($ssql);
            $quant = 0;

            if ($rresult->num_rows > 0) {

                while ($rrow = $rresult->fetch_assoc()) {

                    echo "<tr>";
                    echo"<td>Quantité:</td>";
                    echo "<td>". $rrow["quantite"] ."</td>";
                    echo"</tr>";

                    $quant = $rrow["quantite"];

                }
            }

            echo "<tr>";
            echo"<td>Benefice:</td>";
            echo "<td>". -($quant * $row["prix_achat"]) ."</td>";
            echo"</tr>";


            echo "<tr>";
            echo"<form method=\"post\"  class=\"formulaire\" action=\"ModifierProduit.php\">";
            echo"<td> <input type=\"submit\" name=\"". $row["id"] ."\" value=\"Modifier produit\"/> </td>";
            echo"</form>";
            echo"<form method=\"post\"  class=\"formulaire\" >";
            echo"<td> <input type=\"submit\" name=\"". $row["id"]. "s" ."\" value=\"Supprimer produit\"/> </td>";
            echo"</form>";
            echo"</tr>";
            echo"</br>";





            echo "</table>";

        }

    } else {
        echo "<p class=\"erreur\">ERREUR</p>";
    }

    echo "<br>";
    echo "<br>";
    echo "<br>";

}else{

    echo"<p  class=\"erreur\">Vous devez vous connecter pour acceder à votre compte ! </p>";
    echo "<p class=\"erreur\">ERREUR</p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/Connection.php'\" />";

}


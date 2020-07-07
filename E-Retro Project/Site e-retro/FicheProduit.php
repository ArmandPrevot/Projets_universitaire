<?php
setcookie("Panier", "", time()+3600);

$bdd = new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");

$sql = "SELECT * FROM produit;" ;
$result = $bdd->query($sql);

if ($result->num_rows > 0) {
    $selection = Array();

    while ($row = $result->fetch_assoc()) {

        if(isset($_POST[$row["id"]]) === true){

            //if(isset($_COOKIE["Panier"]) === true){
                setcookie("Panier", $row["id"] .  "-" .  $_COOKIE["Panier"] , time() + 3600);

                $_SESSION[$row["id"]]  = 0;
            //}
        }


    }
}

?>

<!DOCTYPE html>
<html lang="fr">
<title>E-commerce</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" type="text/css" href="./css/style.css" />
<script type="text/javascript" src="./js/script_fiche_produit.js"> </script>

<style>
    body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
    .w3-bar,h1,button {font-family: "Montserrat", sans-serif}
    .fa-anchor,.fa-coffee {font-size:200px}
</style>
<body onload="init()">

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-red w3-card w3-left-align w3-large">
        <a href="index.php"    class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Accueil</a>
        <a href="Produits.php" class="w3-bar-item w3-button w3-padding-large w3-white">Produits</a>
        <a href="MonCompte.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon compte</a>
        <a href="Panier.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon panier</a>
    </div>

</div>

<div class ="wrapper">

<?php

if(is_numeric($_GET["produit"])){
    echo "NOMBRE !";
}else{
    //tentative de hack
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/Produits.php'\" />";
    die;
}
    
$sql = "SELECT * FROM produit where id= ". $_GET["produit"] .";" ;
$result = $bdd->query($sql);

if ($result->num_rows > 0) {
    $selection = Array();

    while ($row = $result->fetch_assoc()) {

        echo "<div id=\"produit\">";
        echo "<table class=\"ok\" id=\"produit\">";

        echo "<tr>";
        echo "<td>". $row["titre"] ."</td>";
        echo"</tr>";

        echo "<tr>";
        echo '<td><img id="produit_img" src="data:image/png;base64,' . base64_encode($row["image"]) . '"/></td>';
        echo"</tr>";

        echo "<tr>";
        echo "<td>". $row["prix_publique"] ." €</td>";
        echo"</tr>";


        echo "<tr>";
        echo "<td>". $row["description"] ."</td>";
        echo"</tr>";

        echo "<form method=\"post\" action=\"Produits.php\">   ";

        $ssql = "SELECT * FROM gestion_stock where id_produit = ". $row["id"] .";" ;
        $rresult = $bdd->query($ssql);

        if ($rresult->num_rows > 0) {

            while ($rrow = $rresult->fetch_assoc()) {

                echo "<tr>";
                echo "<td><p> Quantitée:" . $rrow["quantite"] . "</br></p></td>";
                echo "</tr>";

                echo "<tr>";
                if($rrow["quantite"] <= 0){
                    echo "<td><input type=\"submit\" value=\"Ajouter au panier\" name=\"" . $rrow["id_produit"] . "\" disabled/></td>";
                }else {
                    echo "<td><input type=\"submit\" value=\"Ajouter au panier\" name=\"" . $rrow["id_produit"] . "\" /></td>";
                }

                echo "</tr>";
            }
        }


        echo"</tr>";
        echo "</table>";

        echo "</form>";
        echo "</div>";

    }

}


?>

</div>


</body>
</html>

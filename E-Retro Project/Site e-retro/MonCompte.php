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

<div class="w3-top">
    <div class="w3-bar w3-red w3-card w3-left-align w3-large">
        <a href="index.php"     class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Accueil</a>
        <a href="Produits.php"  class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Produits</a>
        <a href="MonCompte.php" class="w3-bar-item w3-button w3-padding-large w3-white">Mon compte</a>
        <a href="Panier.php"             class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon panier</a>
    </div>

</div>

<?php

if(isset($_SESSION["isco"]) === true && $_SESSION["isco"] === true ){

    echo "<p class=\"erreur\">Votre compte:</p>";

    //$bdd = new mysqli("localhost", "root", "", "cd706595");
    $bdd = new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");





    $sql = "SELECT * FROM client where id = '" . $_SESSION["id"] . "';";

    if ($result = $bdd->query($sql)) {
        while ($row = $result->fetch_assoc()) {

            if($row["admin"] == 1){

                $ssql = "SELECT * FROM gestion_stock ;";
                if ($rresult = $bdd->query($ssql)) {
                    while ($rrow = $rresult->fetch_assoc()) {
                        if($rrow["quantite"] <= 1){

                            $sssql = "SELECT * FROM produit where id = '" . $rrow["id_produit"] . "';";
                            if ($rrresult = $bdd->query($sssql)) {
                                while ($rrrow = $rrresult->fetch_assoc()) {

                                    $phrase =" L'article ref:" . $rrrow["reference"] . ", titre: " . $rrrow["titre"] . " est en faible quantité: " . $rrow["quantite"];
                                    echo "<script> alert(\"". $phrase ."\") </script>";
                                    echo "<p class=\"erreur\">". $phrase . "</p>";
                                }
                            }
                        }
                    }
                }

            }



            echo "<table class=\"ok\">";

                echo "<tr>";
                    echo"<td>Nom:</td>";
                    echo "<td>". $row["nom"] ."</td>";
                echo"</tr>";

            echo "<tr>";
            echo"<td>Prenom:</td>";
            echo "<td>". $row["prenom"] ."</td>";
            echo"</tr>";

            echo "<tr>";
            echo"<td>Email:</td>";
            echo "<td>". $row["email"] ."</td>";
            echo"</tr>";

            echo "<tr>";
            echo"<td>Adresse:</td>";
            echo "<td>". $row["adresse"] ."</td>";
            echo"</tr>";

            echo "<tr>";
            echo"<td>Mot de passe:</td>";
            echo "<td>". $row["mdp"] ."</td>";
            echo"</tr>";

            echo "</table>";

            echo "<a href=\"ModifierCompte.php\" class=\"modif\">Modifier mon compte</br></a>";
            echo "<a href=\"Deconection.php\" class=\"modif\">Me déconecter</br></a>";

            if($row["admin"] == 1){
                echo "<a href=\"GestionStock.php\" class=\"erreur\">Gestion</a>";
            }

        }

    } else {
        echo "<p class=\"erreur\">ERREUR</p>";
    }

}else{

    echo"<p  class=\"erreur\">Vous devez vous connecter pour acceder à votre compte ! </p>";
    echo"<a class=\"formulaire\" href=\"Connection.php\" class=\"w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white\">Me connecter</a>";
    echo"<a class=\"formulaire\" href=\"Inscription.php\" class=\"w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white\">M'enregistrer</a>";

}


?>


</body>
</html>


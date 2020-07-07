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

<div class="w3-top">
    <div class="w3-bar w3-red w3-card w3-left-align w3-large">
        <a href="index.php"     class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Accueil</a>
        <a href="Produits.php"  class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Produits</a>
        <a href="MonCompte.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon compte</a>
        <a href="Panier.php"    class="w3-bar-item w3-button w3-padding-large w3-white">Mon panier</a>
    </div>

</div>

<?php

if($_SESSION["isco"] == false){
    echo "<p class=\"erreur\">Vous devez être connecté !</p>";
    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/Connection.php'\" />";
    return;
}

//1s
$bdd = new mysqli("db344725-eretro.sql-pro.online.net", "db108290", "eretromdpbdd", "db344725_eretro");
$ids = explode("-", $_COOKIE["Panier"]);
$skipId = -1;

if(count($ids) <= 0){
    echo "<script> alert(\"Vous n'avez pas d'articles dans votre panier !\") </script>";
    echo" <meta http-equiv=\"refresh\" content=\"1;URL='http://www.leoquentin06ecommerce.fr/Produits.php'\" />";
}

if(isset($_POST["valider"]) === true){

    $phrase = "Merci d'avoir commander nos articles ! Voici la liste des articles commandés: ";

    for($ii = 0; $ii < count($ids) - 1; $ii++) {

        $sql = "SELECT * FROM produit where id = '" . $ids[$ii] . "';";

        if ($rresult = $bdd->query($sql)) {
            while ($row = $rresult->fetch_assoc()) {
                $phrase .= $row["titre"]. " " . $row["prix_publique"] ."€ </br>";
            }
        }

    }



    echo"<p class=\"ok\"> $phrase Un mail vous à été envoyé. </p>";
    $phrase .= (new DateTime())->format('Y-m-d H:i:s');

    $sql = "SELECT email FROM client where id = '" . $_SESSION["id"] . "';";
    $email = "";
    if ($rresult = $bdd->query($sql)) {
        while ($row = $rresult->fetch_assoc()) {
           $email = $row["email"];
        }
    }


    mail ( $email , "Merci pour votre commande !", $phrase );


    for($i = 0; $i < count($ids) - 1; $i++) {

        $ssql = "Select * From gestion_stock where id_produit = ". $ids[$i] .";" ;
        $quantite = 0;
        if ($rresult = $bdd->query($ssql)) {
            while ($rrow = $rresult->fetch_assoc()) {
                $quantite = $rrow["quantite"];
            }
        }


        $quantite--;

        $ssql = "UPDATE gestion_stock set quantite = '". $quantite ."' where id_produit = ". $ids[$i] .";" ;
        if ($rresult = $bdd->query($ssql)) {
            while ($rrow = $rresult->fetch_assoc()) {

            }
        }
    }

    $ids = array();
    echo" <meta http-equiv=\"refresh\" content=\"2;URL='http://www.leoquentin06ecommerce.fr/Produits.php'\" />";


}


for($i = 0; $i < count($ids) - 1; $i++){

    $sql = "SELECT * FROM produit where id = " . $ids[$i] . ";";
    $count = 0;


    if(isset($_POST[$ids[$i]]) ){
       // $count ++;

       $_SESSION[$ids[$i]] += 1;
        $count +=  $_SESSION[$ids[$i]];
    }

    if(isset($_POST[ $ids[$i] . "s"])){
        //$count--;
        $_SESSION[$ids[$i]] -= 1;
        $count +=  $_SESSION[$ids[$i]];
    }

    if($ids[$i] === $skipId){

        continue;

    }

    for($j = 0; $j < count($ids) - 1; $j++){

        if($ids[$j] === $ids[$i]){
            $count++;

            if($count > 1){
                $skipId = $ids[$j];
            }

        }

    }

    $count += $_SESSION[ids[$i]];

            if ($result = $bdd->query($sql)) {
                while ($row = $result->fetch_assoc()) {

            echo "<table class=\"ok\">";


            echo "<tr>";
            echo "<td>Produit: </td>";
            echo "<td>" . $row["titre"] . "</td>";
            echo "</tr>";

            echo "<tr>";
            echo "<td>Icone:</td>";
            echo '<td><img src="data:image/png;base64,' . base64_encode($row["icone"]) . '"/></td>';
            echo "</tr>";

            echo "<tr>";
            echo "<td>Prix:</td>";
            echo "<td>" . $row["prix_publique"] . " €</td>";
            echo "</tr>";

            echo "<tr>";
            echo "<td>Descriptif:</td>";
            echo "<td>" . $row["descriptif"] . "</td>";
            echo "</tr>";

            echo "<tr>";
            echo "<td>Quantité:</td>";
            echo "<td>" . $count . "</td>";
            echo "</tr>";

            echo "<tr>";
            echo"<form method=\"post\"  class=\"formulaire\" action=\"Panier.php\">";
            echo"<td> <input type=\"submit\" name=\"". $row["id"] ."\" value=\"+\"/> </td>";
            echo"<td> <input type=\"submit\" name=\"". $row["id"]. "s" ."\" value=\"-\"/> </td>";
            echo"</form>";
            echo"</tr>";
            echo"</br>";


            echo "</table>";


        }



            }


}

echo"<form method=\"post\"  class=\"formulaire\" action=\"Panier.php\">";
echo"<td> <input type=\"submit\" name=\"valider\" value=\"valider\"/> </td>";
echo"</form>";

echo"</br>";
echo"</br>";
echo"</br>";



?>
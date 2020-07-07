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

<link rel="stylesheet" href="css/e_retro.css">

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

        <a href="Panier.php"             class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon panier</a>

    </div>



</div>



<?php



if($_SESSION["isco"] == true){

    echo "<p class=\"erreur\">Vous êtes dejà connectés !</p>";

    echo" <meta http-equiv=\"refresh\" content=\"0;URL='http://www.leoquentin06ecommerce.fr/MonCompte.php'\" />";

    return;

}



$sqlSend = false;



    if(isset($_POST["inscription"]) === true ){



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

                    if ($row["email"] === $_POST["email"]) {

                        $canBeRegistered = false;

                        break;

                    }

                }



            } else {

                echo "<p class=\"erreur\">Erreur interne</p>";

            }





            $sql = "INSERT INTO client VALUES ('0', '". $_POST["nom"] . "', '". $_POST["prenom"] . "', '" . $_POST["email"] . "', '". md5($_POST["mdp"]) . "', '" . $_POST["addr"] . "' , '0' )";



           if($canBeRegistered === true ){



              $r = $bdd->query($sql);



               $sqlSend = true;

               echo "<p class=\"ok\"> Inscription validée ! </p>";



               $_SESSION["isco"] = true;



               $sql = "SELECT id FROM client where email = '" . $_POST["email"] . "';";

               $result = $bdd->query($sql);



               if ($result = $bdd->query($sql)) {

                   while ($row = $result->fetch_assoc()) {

                       $_SESSION["id"] = $row["id"];

                   }



               } else {

                   echo "<p class=\"erreur\">Erreur interne</p>";

               }



           }



           if($canBeRegistered === false){

               echo "<p class=\"erreur\">Email déjà prise !</p>";

               $sqlSend = false;

           }



        }





    }



    if($sqlSend === false) {

	?>
<div id="form">
	<?php
 ?>
<div id="form">

    <form method="post">
<p></p>
<br>
        <H1>Inscription</H1>
        <label for="nom">Nom :</label>
        <input type="text" name="nom">
        <label for="prenom">Prenom :</label>
        <input type="text" name="prenom">
        <label for="mail">Mail :</label>
        <input type="email" name="email">
	<label for="adresse">Adresse :</label>
        <input type="text" name="addr">
        <label for="mdp">Mot de passe :</label>
        <input type="password" name="mdp">
        <label for="mdp2">Confirmer mot de passe :</label>
        <input type="password" name="mdp2">
	<br>
	<p></p>
	<br>
	<input type="submit" name="inscription" value="Valider"/>
    </form>
	<?php

    }



?>





</body>

</html>




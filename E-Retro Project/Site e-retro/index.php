<!DOCTYPE html>
<html lang="fr">
<title>E-commerce</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"><link rel="icon" type="image/png" href="image/logo.png" />

<style>
    body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
    .w3-bar,h1,button {font-family: "Montserrat", sans-serif}
    .fa-anchor,.fa-coffee {font-size:200px}
</style>
<body>

<!-- Navbar -->
<div class="w3-top">
    <div class="w3-bar w3-red w3-card w3-left-align w3-large">
        <a href="index.php" class="w3-bar-item w3-button w3-padding-large w3-white">Accueil</a>
        <a href="Produits.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Produits</a>
        <a href="MonCompte.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon compte</a>
        <a href="Panier.php" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Mon panier</a>
    </div>

</div>

<!-- Header -->
<header class="w3-container w3-red w3-center" style="padding:128px 16px">
    <img src="image/Tel_gif.gif" alt="Logo">
<br>
    <h1 class="w3-margin w3-jumbo">E-Retro</h1>
    <p class="w3-xlarge">le magasin en ligne n°1</p>


    <a href="Produits.php" ><button class="w3-button w3-black w3-padding-large w3-large w3-margin-top">Produits</button></a>
</header>

<!-- First Grid -->
<div class="w3-row-padding w3-padding-64 w3-container">
    <div class="w3-content">
        <div class="w3-twothird">
            <h1>Garantie satisfait ou remboursé</h1>
            <h5 class="w3-padding-32">Vous avez 1 semaine pour être satisfait, ou nous vous rembourseront intégralement.</h5>

            <p class="w3-text-grey">Chez E-stock, nous sommes très proche de nos clients, c'est pour cela que nous sommes à votre écoute. Notre SAV est ouvert 24h/24,7j/7. De plus, notre numéro de SAV est non surtaxé</p>
        </div>

        <div class="w3-third w3-center">
            <i class="fa fa-anchor w3-padding-64 w3-text-red"></i>
        </div>
    </div>
</div>

<!-- Second Grid -->
<div class="w3-row-padding w3-light-grey w3-padding-64 w3-container">
    <div class="w3-content">
        <div class="w3-third w3-center">
            <i class="fa fa-coffee w3-padding-64 w3-text-red w3-margin-right"></i>
        </div>

        <div class="w3-twothird">
            <h1>Livraison à domicile</h1>
            <h5 class="w3-padding-32"></h5>

            <p class="w3-text-grey">Grâce à nos nombreux partenariats, nous vous garantissons une livraison en 2 jours ouvrés maximum. Ci ce n'est pas le cas, vos frais de ports vous seront intégralement remboursés.</p>
        </div>
    </div>
</div>

<div class="w3-container w3-black w3-center w3-opacity w3-padding-64">
    <h1 class="w3-margin w3-xlarge">Notre objectif: vous satisfaire</h1>
</div>

<!-- Footer -->
<footer class="w3-container w3-padding-64 w3-center w3-opacity">
    <div class="w3-xlarge w3-padding-32">
        <p>IUT Nice côte d'Azur - Fabron - 2018 - 2019</p>
       
    </div>
    
</footer>


</body>
</html>



<?php
?>

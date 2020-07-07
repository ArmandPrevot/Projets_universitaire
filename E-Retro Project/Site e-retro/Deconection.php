<?php
/**
 * Created by PhpStorm.
 * User: doria
 * Date: 27/12/2018
 * Time: 18:06*/

session_start([
    'cookie_lifetime' => 3600,
]);
session_destroy();
setcookie("Panier", "", 0);
header( "refresh:1; url=index.php" );
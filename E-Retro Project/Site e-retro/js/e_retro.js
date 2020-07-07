window.onload=function() {
    var mdp = document.getElementById("mdp");
    var min = document.getElementById("min");
    var maj = document.getElementById("maj");
    var taille = document.getElementById("taille");
    var chi = document.getElementById("chi");
    var spe = document.getElementById("spe");

    mdp.onfocus = function () {
        document.getElementById("merreur").style.display = "block";
    }
    mdp.onblur = function () {
        document.getElementById("merreur").style.display = "none";
    }

    mdp.onkeyup=function () {
        var lowerCase = /[a-z]/g;
        var upperCase = /[A-Z]/g;
        var numbers = /[0-9]/g;
        var special = /[\W]/g;

        if (mdp.value.match(lowerCase)) {
            min.style.display = "none";
            console.log("slrbjg");
        } else {
            min.style.display = "block";
            console.log("d");
        }

        if (mdp.value.match(upperCase)) {
            maj.style.display = "none";
        } else {
            maj.style.display = "block";
        }

        if (mdp.value.match(numbers)) {
            chi.style.display = "none";
        } else {
            chi.style.display = "block";
        }
        if (mdp.value.length>=8) {
            taille.style.display = "none";
        } else {
            taille.style.display = "block";
        }
        if (mdp.value.match(special)) {
            spe.style.display = "none";
        } else {
            spe.style.display = "block";
        }
    }
}
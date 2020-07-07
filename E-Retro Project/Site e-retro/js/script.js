



function testMdp() {



    var mdp1 = document.getElementById("mdp").value;

    var mdp2 = document.getElementById("mdp2").value;

    var message = document.getElementById("retour-connexion");

    var test1 = 0;





    if(mdp1 !== mdp2){



        message.innerHTML = "Les mots de passe ne sont pas identiques";

    }else {





        for(var i = 0; i<mdp1.length ;i++){

           


            if(mdp1[i].match(" ")) {

                test1 = 1;

            }

        }



        if(test1 === 1){



            message.innerHTML = "Caractères utilisés interdit ' ' ";

        }else {


	    
            message.innerHTML = "Ok";
	    window.location.href = "index.php";


        }





    }



    if(mdp1 === "" || mdp2 === ""){



        message.innerHTML = "Champs vides";

    }



}
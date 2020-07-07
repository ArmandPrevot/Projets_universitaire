

function init(){

    const produits = document.querySelectorAll("#produit_img");

    for(let i = 0; i < produits.length; i++){
        produits[i].style = "grid-column-start: "+ i + ";";

        produits[i].addEventListener("mouseover", () => {

            produits[i].style = "transform: scale(1.1);";
        })

        produits[i].addEventListener("mouseout", () => {
            produits[i].style = "-webkit-filter: zoom(0px);";
        })
    }

}
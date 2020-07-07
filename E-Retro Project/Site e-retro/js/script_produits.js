

function init(){

    const produits = document.querySelectorAll("#produit_icn");

    for(let i = 0; i < produits.length; i++){
        produits[i].style = "grid-column-start: "+ i + ";";

        produits[i].addEventListener("mouseover", () => {
            produits[i].style = "transform: scale(1.1);";
        })

        produits[i].addEventListener("mouseout", () => {
            produits[i].style = "-webkit-filter: zoom(0px);";
        })
    }



    const bouttonProduits = document.querySelectorAll("#boutton_produit");
    for(let i = 0; i < bouttonProduits.length; i++){

       bouttonProduits[i].addEventListener('click', function () {

            var cart = document.querySelector('#cart');
            var imgtodrag =  document.querySelector("#produit_icn");
            if (imgtodrag) {
                var imgclone = imgtodrag.clone()
                    .offset({
                    top: imgtodrag.offset().top,
                    left: imgtodrag.offset().left
                })
                    .css({
                    'opacity': '0.5',
                        'position': 'absolute',
                        'height': '150px',
                        'width': '150px',
                        'z-index': '100'
                })
                    .appendTo(document.querySelector('body'))
                    .animate({
                    'top': cart.offset().top + 10,
                        'left': cart.offset().left + 10,
                        'width': 75,
                        'height': 75
                }, 1000, 'easeInOutExpo');
                
                setTimeout(function () {
                    cart.effect("shake", {
                        times: 2
                    }, 200);
                }, 1500);

                imgclone.animate({
                    'width': 0,
                        'height': 0
                }, function () {
                    bouttonProduits[i].detach()
                });
            }
        });

    }

}
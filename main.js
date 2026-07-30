/* ==========================================
   Airport Lost & Found
   main.js
========================================== */

// ==========================
// Barre de navigation
// ==========================

const header = document.querySelector("header");

window.addEventListener("scroll", () => {

    if(window.scrollY > 60){

        header.style.background = "#ffffff";
        header.style.boxShadow = "0 5px 20px rgba(0,0,0,0.15)";
        header.style.transition = "0.3s";

    }else{

        header.style.background = "rgba(255,255,255,.95)";
        header.style.boxShadow = "0 3px 15px rgba(0,0,0,.08)";

    }

});


// ==========================
// Apparition des sections
// ==========================

const observer = new IntersectionObserver((entries)=>{

    entries.forEach(entry=>{

        if(entry.isIntersecting){

            entry.target.classList.add("show");

        }

    });

},{
    threshold:0.2
});

document.querySelectorAll(".card,.step,.about").forEach(element=>{

    observer.observe(element);

});


// ==========================
// Bouton Retour en haut
// ==========================

const topButton = document.createElement("button");

topButton.innerHTML = '<i class="fa-solid fa-arrow-up"></i>';

topButton.id = "topButton";

document.body.appendChild(topButton);

topButton.style.position="fixed";
topButton.style.bottom="30px";
topButton.style.right="30px";
topButton.style.width="55px";
topButton.style.height="55px";
topButton.style.borderRadius="50%";
topButton.style.border="none";
topButton.style.cursor="pointer";
topButton.style.background="#1E88E5";
topButton.style.color="white";
topButton.style.fontSize="20px";
topButton.style.display="none";
topButton.style.boxShadow="0 10px 25px rgba(0,0,0,.2)";
topButton.style.zIndex="1000";

window.addEventListener("scroll",()=>{

    if(window.scrollY>500){

        topButton.style.display="block";

    }else{

        topButton.style.display="none";

    }

});

topButton.onclick=()=>{

    window.scrollTo({

        top:0,
        behavior:"smooth"

    });

};


// ==========================
// Animation des cartes
// ==========================

const cards=document.querySelectorAll(".card");

cards.forEach(card=>{

    card.addEventListener("mouseenter",()=>{

        card.style.transform="translateY(-12px) scale(1.02)";

    });

    card.addEventListener("mouseleave",()=>{

        card.style.transform="translateY(0px) scale(1)";

    });

});


// ==========================
// Message de bienvenue
// ==========================

window.addEventListener("load",()=>{

    console.log("Airport Lost & Found chargé avec succès.");

});
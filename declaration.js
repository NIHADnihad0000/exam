
const form = document.getElementById("declarationForm");

const message = document.getElementById("message");

const resultat = document.getElementById("resultat");

const referenceElement = document.getElementById("reference");

const submitButton = document.getElementById("submitButton");


// URL du backend Spring Boot
const API_URL = "http://localhost:8081";


form.addEventListener("submit", async function(event) {

    event.preventDefault();


    // Désactiver le bouton pendant l'envoi
    submitButton.disabled = true;
    submitButton.textContent = "Envoi en cours...";


    // =========================
    // 1. RÉCUPÉRER LES DONNÉES
    // =========================

    const nom =
        document.getElementById("nom").value.trim();

    const prenom =
        document.getElementById("prenom").value.trim();

    const numeroPasseport =
        document.getElementById("numeroPasseport").value.trim();

    const telephone =
        document.getElementById("telephone").value.trim();

    const email =
        document.getElementById("email").value.trim();


    const categorie =
        document.getElementById("categorie").value;

    const lieuPerte =
        document.getElementById("lieuPerte").value.trim();

    const datePerte =
        document.getElementById("datePerte").value;

    const description =
        document.getElementById("description").value.trim();


    try {

        // =========================
        // 2. CRÉER LE PASSAGER
        // =========================

        const passager = {

            nom: nom,

            prenom: prenom,

            numeroPasseport: numeroPasseport,

            telephone: telephone,

            email: email

        };


        console.log(
            "Passager envoyé :",
            passager
        );


        const passagerResponse = await fetch(
            `${API_URL}/api/passagers`,
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(passager)
            }
        );


        if (!passagerResponse.ok) {

            const erreur =
                await passagerResponse.text();

            throw new Error(
                "Erreur passager : " + erreur
            );
        }


        const passagerEnregistre =
            await passagerResponse.json();


        console.log(
            "Passager enregistré :",
            passagerEnregistre
        );


        // =========================
        // 3. CRÉER LA DÉCLARATION
        // =========================

        const declaration = {

            description: description,

            categorie: categorie,

            datePerte: datePerte,

            lieuPerte: lieuPerte,

            passager: {

                id: passagerEnregistre.id

            }

        };


        console.log(
            "Déclaration envoyée :",
            declaration
        );


        const declarationResponse = await fetch(
            `${API_URL}/api/declarations`,
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(declaration)
            }
        );


        if (!declarationResponse.ok) {

            const erreur =
                await declarationResponse.text();

            throw new Error(
                "Erreur déclaration : " + erreur
            );
        }


        // =========================
        // 4. RÉCUPÉRER LA RÉPONSE
        // =========================

        const declarationEnregistree =
            await declarationResponse.json();


        console.log(
            "Déclaration enregistrée :",
            declarationEnregistree
        );


        // =========================
        // 5. AFFICHER LA RÉFÉRENCE
        // =========================

        referenceElement.textContent =
            declarationEnregistree.reference;


        resultat.classList.add("active");


        message.className =
            "message success";

        message.textContent =
            "Votre déclaration a été enregistrée avec succès.";


        // Réinitialiser le formulaire
        form.reset();


    } catch (error) {

        console.error(
            "Erreur complète :",
            error
        );


        message.className =
            "message error";

        message.textContent =
            "Une erreur est survenue : " +
            error.message;


    } finally {

        submitButton.disabled = false;

        submitButton.textContent =
            "Envoyer la déclaration";

    }

});
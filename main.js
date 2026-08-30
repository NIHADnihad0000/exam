document.addEventListener("DOMContentLoaded", function () {

    /* =========================
       MENU MOBILE
    ========================= */

    const menuToggle =
        document.getElementById("menuToggle");

    const nav =
        document.getElementById("nav");


    if (menuToggle && nav) {

        menuToggle.addEventListener("click", function () {

            nav.classList.toggle("open");

            const icon =
                menuToggle.querySelector("i");

            if (nav.classList.contains("open")) {

                icon.classList.remove("fa-bars");

                icon.classList.add("fa-xmark");

            } else {

                icon.classList.remove("fa-xmark");

                icon.classList.add("fa-bars");
            }

        });


        /* Fermer le menu après un clic */

        const navLinks =
            nav.querySelectorAll("a");

        navLinks.forEach(function (link) {

            link.addEventListener("click", function () {

                nav.classList.remove("open");

                const icon =
                    menuToggle.querySelector("i");

                icon.classList.remove("fa-xmark");

                icon.classList.add("fa-bars");

            });

        });

    }


    /* =========================
       HEADER AU SCROLL
    ========================= */

    const header =
        document.getElementById("header");


    window.addEventListener("scroll", function () {

        if (window.scrollY > 50) {

            header.classList.add("scrolled");

        } else {

            header.classList.remove("scrolled");

        }

    });


    /* =========================
       COMPTEURS
    ========================= */

    const counters =
        document.querySelectorAll(".counter");


    let countersStarted = false;


    function startCounters() {

        if (countersStarted) {
            return;
        }

        countersStarted = true;


        counters.forEach(function (counter) {

            const target =
                Number(counter.dataset.target);

            let current = 0;

            const increment =
                Math.max(1, Math.ceil(target / 80));


            const timer =
                setInterval(function () {

                    current += increment;


                    if (current >= target) {

                        current = target;

                        clearInterval(timer);
                    }


                    counter.textContent =
                        current.toLocaleString("fr-FR");

                }, 20);

        });

    }


    /* =========================
       DÉTECTION DE LA SECTION
    ========================= */

    const statistics =
        document.querySelector(".statistics");


    if (statistics) {

        const observer =
            new IntersectionObserver(
                function (entries) {

                    entries.forEach(function (entry) {

                        if (entry.isIntersecting) {

                            startCounters();

                            observer.disconnect();
                        }

                    });

                },
                {
                    threshold: 0.3
                }
            );

        observer.observe(statistics);
    }


    /* =========================
       ANNÉE FOOTER
    ========================= */

    const currentYear =
        document.getElementById("currentYear");


    if (currentYear) {

        currentYear.textContent =
            new Date().getFullYear();

    }


    /* =========================
       LIENS DE NAVIGATION ACTIVE
    ========================= */

    const sections =
        document.querySelectorAll("section[id]");

    const links =
        document.querySelectorAll(".nav-link");


    window.addEventListener("scroll", function () {

        let currentSection = "";


        sections.forEach(function (section) {

            const sectionTop =
                section.offsetTop - 120;

            const sectionHeight =
                section.offsetHeight;


            if (
                window.scrollY >= sectionTop &&
                window.scrollY < sectionTop + sectionHeight
            ) {

                currentSection =
                    section.getAttribute("id");

            }

        });


        links.forEach(function (link) {

            link.classList.remove("active");


            const href =
                link.getAttribute("href");


            if (
                href === "#" + currentSection
            ) {

                link.classList.add("active");

            }

        });

    });

});
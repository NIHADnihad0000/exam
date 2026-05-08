from tkinter import *

# ---------------- Fenêtre principale ----------------
fenetre = Tk()
fenetre.title("Optimisation Graphique")
fenetre.geometry("700x550")
fenetre.config(bg="#1f1f2e")
fenetre.resizable(False, False)

# ---------------- Fonction Objectif ----------------
cx = 30
cy = 50

# ---------------- Points proposés ----------------
points = {
    "A": (0, 40),
    "B": (20, 30),
    "C": (40, 0),

}

# ---------------- Fonction ----------------
def trouver_optimal():

    meilleur_point = ""
    meilleur_z = -1

    texte = ""

    for nom, coord in points.items():

        x = coord[0]
        y = coord[1]

        # Calcul de Z
        z = cx * x + cy * y

        texte += f"{nom}({x},{y})   ➜   Z = {z}\n"

        # Recherche du maximum
        if z > meilleur_z:

            meilleur_z = z
            meilleur_point = nom
            meilleur_x = x
            meilleur_y = y

    # Affichage résultat
    resultat.config(
        text=texte +
             f"\n━━━━━━━━━━━━━━━━━━\n"
             f"POINT OPTIMAL : {meilleur_point}\n"
             f"x = {meilleur_x}   |   y = {meilleur_y}\n"
             f"PROFIT MAXIMAL = {meilleur_z}",
        fg="#00ffcc"
    )

# ---------------- Titre ----------------
titre = Label(
    fenetre,
    text="APPLICATION D’OPTIMISATION",
    font=("Helvetica", 22, "bold"),
    bg="#1f1f2e",
    fg="white"
)
titre.pack(pady=20)

# ---------------- Sous-titre ----------------
sous_titre = Label(
    fenetre,
    text="Détection Automatique du Point Optimal",
    font=("Arial", 13),
    bg="#1f1f2e",
    fg="#cfcfcf"
)
sous_titre.pack()

# ---------------- Cadre principal ----------------
frame = Frame(
    fenetre,
    bg="#2b2b40",
    bd=3,
    relief=RIDGE
)
frame.pack(padx=30, pady=30, fill="both", expand=True)

# ---------------- Fonction objectif ----------------
fonction = Label(
    frame,
    text="Fonction Objectif :  Z = 30x + 50y",
    font=("Arial", 15, "bold"),
    bg="#2b2b40",
    fg="#ffcc00"
)
fonction.pack(pady=20)

# ---------------- Bouton ----------------
btn = Button(
    frame,
    text="Trouver le Point Optimal",
    command=trouver_optimal,
    font=("Arial", 13, "bold"),
    bg="#00b894",
    fg="white",
    padx=20,
    pady=10,
    cursor="hand2",
    activebackground="#019875"
)
btn.pack(pady=20)

# ---------------- Résultat ----------------
resultat = Label(
    frame,
    text="",
    font=("Consolas", 13, "bold"),
    bg="#2b2b40",
    fg="white",
    justify=LEFT
)
resultat.pack(pady=20)

# ---------------- Footer ----------------
footer = Label(
    fenetre,
    text="Projet Python - Programmation Linéaire",
    font=("Arial", 10),
    bg="#1f1f2e",
    fg="#888888"
)
footer.pack(side=BOTTOM, pady=10)

# ---------------- Lancement ----------------
fenetre.mainloop()
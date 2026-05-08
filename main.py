from pulp import *

# Création du problème (maximisation)
model = LpProblem("Optimisation_Production", LpMaximize)

# Variables de décision
x = LpVariable("Produit_A", lowBound=0)
y = LpVariable("Produit_B", lowBound=0)

# Fonction objectif
model += 30 * x + 50 * y, "Profit"

# Contraintes
model += 3 * x + 2 * y <= 120
model += 2 * x + 4 * y <= 160
model += x + y <= 70
model += x <= 40
model += y <= 50

# Résolution
model.solve()

# Résultats
print("Statut :", LpStatus[model.status])
print("x =", value(x))
print("y =", value(y))
print("Profit maximum =", value(model.objective))
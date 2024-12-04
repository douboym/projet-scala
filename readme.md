
# Mower Project

Ce projet implémente une simulation de tondeuses autonomes sur une pelouse rectangulaire.
Les tondeuses reçoivent des instructions via un fichier d'entrée,
et le programme retourne leurs positions finales après exécution.

## Comment exécuter le projet

1. Importez le projet dans IntelliJ IDEA.
2.  Remplacez le chemin du fichier d'entrée
3. Cliquez sur **Run** ou utilisez le raccourci `Shift + F10` pour exécuter l'objet `MowerProject`.

## Fichier d'entrée

Le fichier d'entrée doit suivre ce format :
- La première ligne : Dimensions de la pelouse (largeur et hauteur).
- Les groupes suivants de deux lignes :
- **Ligne 1** : Position initiale et orientation (`N`, `E`, `S`, `W`).
- **Ligne 2** : Série d'instructions (`D`, `G`, `A`).

### Exemple
5 5 1 2 N GAGAGAGAA 3 3 E AADAADADDA

## Résultat attendu

Le programme affiche les positions finales des tondeuses après exécution des instructions.

### Exemple de sortie
Tondeuse 1 : 1 3 N Tondeuse 2 : 5 1 E


## Auteurs

- Étudiant 1 : Mamadou DIEDHIOU
- Étudiant 2 : Moustapha MOUNIR


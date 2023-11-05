# Pochemon

Pochemon est un jeu 2D codé en Java et inspiré de 
pokémon. L'utilisateur peut contrôler un personnage 
dans une map. Dans cette map, il y a plusieurs 
dresseurs que l'utilisateur peut affronter avec son ami 
Pochechu. Pochechu est un pochemon de type chaleur très 
puissant, il est au niveau 5 au début, l'utilisateur 
peut l'améliorer en remportant des combats. L'objected 
est d'entraîner son pochemon pour pouvoir battre le 
meilleur dresseur, **Giovanni**.

## Installation

Quelque soit le système d'exploitation, il est
préférable de lancer et de compiler grâce aux deux
fichiers prévus pour. Si le programme est lancé à la
main, il y a un risque qu'il ne trouve pas les fichiers 
nécessaires. Pour que ces scripts marchent, il faut que
les commandes **java** et **javac** soient dans la 
variable d'environnement **PATH**.

### Linux

Première utilisation :

```bash
chmod u+x compile.sh run.sh
./compile && ./run
```

Ensuite, il est possible de le lancer en utilisant seulement la commande :

```bash
./run
```

### Windows

Pour compiler et ensuite lancer le programme :

```bat
.\compile
.\run
```

### MacOS

Je ne peux pas aider :/

## Organisation

A la racine du projet se trouvent les scripts de
compilation et d'exécution du jeu, et 3 dossiers.

Le dossier **res** contient toutes les ressources dont
le jeu a besoins, images, musiques et maps et les
crédits.

Le dossier **src** contient le code source Java du
projet.

Le dossier **bin** sera créé une fois que
l'utilisateur aura compilé le code. Celui contient
les fichiers ***.class*** pour la JVM.

## Fonctionnement

En mode libre, l'utilisateur peut utiliser les touches
***Z, Q, S, D*** pour se déplacer dans le monde libre.
Une fois qu'il voit un dresseur, il peut intéragir avec
lui, et lancer un combat en se mettant à côté de lui et
en appuyant sur espace. Le combat se déroule, sur une
nouvelle fenêtre qui reste au-dessus de toutes les
et qu'on ne peut pas fermer. Une fois le combat fini,
une image affichera le vainqueur et l'utilisateur sera
en mesure de fermer la fenêtre et continuer dans le
monde libre.

## Dresseurs et pochemons

# Rapport

## Les erreurs corrigées

### Board

#### Constructeur

Parfois (mais pas tout le temps), une erreur était relevé sur la méthode testNumberOfPawns. La raison est que le constructeur faisait une boucle for aléatoirement sur le nombre de pions à initialiser.

Mais parfois il pouvais écraser un pion, ce qui fait qu'il y avait un pion de moins que souhaité sur le plateau.

Nous avons donc changé la boucle for en boucle while

#### testGetStatusOfSquare

Dans la condition suivante, les cases testées à n+1 sont toujours considérées comme étant dans le tableau.
"""if (y <= this.getYSize() && x <= this.getXSize() && y >= 0 && x >= 0)"""

On change la condition pour que la taille soit strictement inférieure à la taille


### Position

#### testIsNextTo()

Dans la condition initale, on testais si une position était adjascent en x et adjascent en y. La zone pour laquelle la méthode renvoie true est donc assimilable à une croix qui parcours toute la board.

Pour que la condition se rapproche de ce qui est demandé dans la spec, on a utilisé un XOR plutôt qu'un AND


### Pawn

#### testPawnConstructor

Les hitpoints initiaux étaient configurés à 2 au lieu de 6 comme marqué dans la spec
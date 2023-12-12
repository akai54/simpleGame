# Rapport

## Les erreurs corrigées

### Board

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
# Rapport

## Les erreurs corrigées

### Board

#### testGetStatusOfSquare

Dans la condition suivante, les cases testées à n+1 sont toujours considérées comme étant dans le tableau.
"""if (y <= this.getYSize() && x <= this.getXSize() && y >= 0 && x >= 0)"""

On change la condition pour que la taille soit strictement inférieure à la taille

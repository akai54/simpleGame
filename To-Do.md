# Simple Game

### Board
- [x] **Test du constructeur** : Vérifiez l'initialisation correcte du plateau, y compris les dimensions, la case bonus.
- [x] **Test GetSquareContent** : Testez les carrés occupés et vides.
- [x] **Test RemovePawn** : Vérifiez qu’un pion est correctement retiré du plateau.
- [x] **Test IsBonusSquare** : Vérifiez si la méthode identifie avec précision le carré bonus.
- [x] **Test NumberOfPawns** : Vérifiez le nombre de pions sur le plateau après diverses opérations comme l'ajout ou la suppression de pions.
- [x] **Test NewTurn** : Assurez-vous que le jeu met correctement à jour le pion actuel après chaque tour.
- [x] **Test SquareContentSprite** : Testez la représentation visuelle des différentes cases du plateau (y compris les cases bonus et les cases avec pions).
- [ ] **Test ToString** : Vérifiez la représentation correcte des chaînes de l'ensemble de la carte.
- [x] **Test GetStatusOfSquare** : Testez différents statuts de carrés (occupés, vides, hors du tableau).
- [x] **Test GetCurrentPawn** : Vérifiez que la méthode renvoie correctement le pion actuel.
- [x] **Test addPawn(Pawn pawn)** : Assurez-vous que les pions sont correctement ajoutés au plateau.
- [x] **Test removeAllPawns()** : Vérifiez que tous les pions sont correctement retirés du plateau.

### Direction
- [x] **Test des valeurs de l'énumération** : Vérifiez que l'énumération Direction contient les valeurs correctes (Up, Down, Left, Right).

### Game
- [x] **Test du constructeur** : Vérifiez que le constructeur du jeu initialise un plateau avec les dimensions et le nombre de pions corrects.
- [x] **Test GameOver** : Testez la méthode isGameOver avec des scénarios comme un pion restant, un pion avec 3 pièces d'or ou plus et des conditions de jeu normales.
- [ ] **Tests PlayRound** :
    - [x] Déplacement valide : testez playRound avec une direction valide où le pion se déplace vers une case vide.
    - [x] Attaque valide : testez le playRound où le pion attaque un autre pion.
    - [x] Déplacement invalide (hors tableau) : testez playRound avec une direction sortant du tableau, en attendant une ImpossibleActionException.

### Pawn
- [X] **Test du constructeur** : Vérifiez qu'un nouveau pion est correctement initialisé avec la lettre, la position, les points de vie et l'or spécifiés.
- [X] **Test se Déplacer** :
    - [X] Déplacement valide : testez le déplacement du pion vers une case vide adjacente.
    - [X] Déplacement invalide (hors plateau/occupé/trop loin) : testez le déplacement vers une case qui est hors du plateau, déjà occupée ou non adjacente, en attendant une ImpossibleActionException.
- [X] **Test de suffer** : Testez la méthode suffer pour vous assurer qu'elle réduit correctement les points de vie et gère la mort des pions.
    - [X] Remove from Board : Vérifier qu'un pion mort est enlevé du plateau
- [X] **Test IsDead** : Vérifiez qu'isDead identifie correctement le moment où les points de vie d'un pion atteignent zéro.
- [X] **Tests d'attaque** :
    - [X] Attaque valide (case normale et case bonus) : Testez l'attaque d'un autre pion dans des conditions normales et depuis une case bonus.
    - [X] Attaque invalide (pas d'ennemi/trop loin) : testez l'attaque d'une case vide ou d'une case trop éloignée, en attendant une ImpossibleActionException.
    - [X] Incrément d'or en cas de mise à mort : Vérifiez que l'or du pion augmente lorsqu'il tue un autre pion.
- [ ] **Tests pour la méthode getLetter() et getGold()** : Assurez-vous que ces méthodes retournent les valeurs correctes.

### Position
- [x] **Test du constructeur** : Vérifiez que l'objet Position est correctement initialisé avec les coordonnées x et y données.
- [x] **Test de copy** : Vérifiez que la méthode copy() crée une copie exacte de l'objet Position.
- [x] **Tests GetPositionNextTo** :
    - Testez chaque direction (haut, bas, gauche, droite) pour vous assurer que la méthode renvoie la position adjacente correcte.
- [x] **Test IsNextTo** :
    - Vérifiez que isNextTo identifie correctement les positions adjacentes (y compris les diagonales).
    - Testez les positions non adjacentes pour vous assurer qu’elles renvoient faux.
- [x] **Test égal** :
    - Testez avec un type d'objet différent et des positions différentes pour vous assurer qu'il renvoie false de manière appropriée.
    - **Tests pour la méthode equals(Object other)** : Assurez-vous que la méthode fonctionne correctement en comparant des positions identiques et différentes.

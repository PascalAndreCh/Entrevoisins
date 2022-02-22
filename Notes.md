# OpenClassrooms

  
![]()
## Application **ENTREVOISINS**
![]()
### Notes

deux seuls écrans sont indiqués dans la note de cadrage.  
![](ImagesEntrevoisins/Note01.jpg)
![](ImagesEntrevoisins/Note02.jpg)

- l'écran de la liste des favoris n'est pas décrit.
  - Faut il conserver les corbeilles et rendre les voisins supprimable depuis la liste des favoris ou non ?
  - Faut il remplacer les corbeilles par des étoiles, cliquables pour ôter un voisin de la liste des favoris ou ne rien mettre ?
  - Le bouton de création d'un voisin doit il aussi être accessible depuis la liste des favoris, si oui, faut il mettre un voisin créé à cet endroit automatiquement dans la liste des favoris ou pas ?

N'ayant pu joindre Claire, j'ai opté pour les étoiles cliquables dans la liste des favoris. Si une création a lieu à partir de la liste des favoris, le nouveau voisin ne sera pas mis d'office dans cette liste.
*(Un nouveau "voisin" est créé par la propre personne elle même, où elle renseigne ces propres données, mais ne s'ajoute pas dans sa liste de favoris, ceux sont les autres qui la mette ou non dans leur liste de favoris)*.
*En situation réelle, j'aurais insisté pour avoir une réponse précise, laquelle aurait peut être été : "fait comme tu veux" (ma préférée)*.

- Ajout de 2 fonctionnalités. (mise dans une branche à part : BrancheTestModif).
  - lors d'un clic sur la corbeille, le voisin est immédiatement supprimé et ces données perdues. Si le clic a été fait par erreur, il n'y a aucun moyen de retour en arrière. J'ai donc inséré une boite de dialogue lors d'un clic sur la corbeille pour valider ou abandonner la suppression.*(ça m'embêterai de perdre à cause d'une mauvaise manipulation le numéro de téléphone de Caroline)*.
  - Si une erreur est commise lors d'une création, ou un voisin qui changerait de numéro de téléphone par exemple, il n'y a aucune possibilité de modification. J'ai donc rendu les toutes les zones accessibles et modifiables dans l'écran détail du voisin. *(D'un autre côté, rien n'indique dans la note de cadrage que ces zones ne doivent pas être modifiables)*.





# RAPPORT DE DOC
## MANUEL D'UTILISATION

| **Ecran**       | **Description**                                                                                                                                                                                                                        | **Photo**                                                                                                                                                          |
|-----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Ecran d'accueil | Lors de l'ouverture de l'application, on se retrouve sur l'écran d'acceuil où nous avons la possibilité de visiter les différents enclos (!!! METTRE FONCTIONNALITE DES AUTRES BOUTONS !!!)                                            | ![Ecran d'accueil](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/screen%20interface/Ecran%20principal.png?raw=true) |
| Ecran types enclos | Après sélection du bouton des enclos on se retrouve sur cette page qui nous donne la possibilité de choisir le type d'enclos que nous voulons visiter (Un enclos, un aquarium ou un voliere)                                           | ![Ecran types enclos](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/screen%20interface/Ecran%20enclos.png?raw=true) |
| Ecran enclos    | Si nous voulons visiter l'enclos terrestre on clique sur le bouton adéquat qui nous ramène sur cette page qui nous donne plusieurs informations sur l'enclos, l'état de l'enclos et la possibilité de visualiser les créatures.        | ![Ecran enclos](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/screen%20interface/Ecran%20enclos%201.png?raw=true)   |
| Ecran licorne   | Les écrans des créatures se présentent comme ceci, on voit les informations sur la créature qu'on visite, une image de la créature ainsi que ses informations de santé.                                                                | ![Ecran licorne](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/screen%20interface/Ecran%20licorne.png?raw=true)     |
| Ecran Aquarium  | Si nous voulons visiter l'aquarium on clique sur le bouton adéquat qui nous ramène sur cette page qui nous donne plusieurs informations sur l'aquarium, l'état de l'aquarium et la possibilité de visualiser les créatures marines.    | ![Ecran Aquarium](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/screen%20interface/Ecran%20aquarium.png?raw=true)   |
| Ecran megalodon | Les écrans des créatures se présentent toutes de la même manière, on voit les informations sur la créature qu'on visite, une image de la créature ainsi que ses informations de santé, mais une photo différente pour chaque créature. | ![Ecran megalodon](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/screen%20interface/Ecran%20megalodon.png?raw=true) |
| Ecran voliere   | Tous les enclos sont présentés pareils. On peut voir l'état de propreté des enclos avec la possibilité de le nettoyer.                                                                                                                 | ![Ecran voliere](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/screen%20interface/Ecran%20voliere.png?raw=true)     |
| Ecran voliere propre | Après avoir nettoyer l'enclos son état de propreté se modifie, il va ensuite se remodifier au fil du temps aléatoirement.                                                                                                              | ![Ecran voliere propre](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/screen%20interface/Ecran%20voliere%20propre.png?raw=true)         |
| Ecran phenix   | Les écrans des créatures se présentent toutes de la même manière, on voit les informations sur la créature qu'on visite, une image de la créature ainsi que ses informations de santé, mais une photo différente pour chaque créature. | ![Ecran phenix](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/screen%20interface/Ecran%20phenix.png?raw=true)|


***

## ETUDE DE CONCEPTION

### ANALYSE DES BESOINS

#### PERSONNAGE
| Nom             | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | Photo                                                                                                                                       |
|-----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| **Dragon**      | Les dragons doivent posséder les caractéristiques suivantes : un nom (d'espèce), un sexe, un poids, une taille, un âge, un indicateur de faim, un indicateur de sommeil, un indicateur de santé. Les dragons doivent pouvoir manger, émettre un son, être soigné, s'endormir ou se réveiller, viellir (et mourir s'il a atteint la dernière catégorie d'âge et qu'il doit encore viellir, ou s'il devient trop malade). De plus, les dragons doivent pouvoir courir, nager et voler. Ils doivent pouvoir pondre des oeufs. La naissance d'un nouveau dragon dépend de sa durée de gestation et d'incubation. Les mâles ne doivent bien sûr pas pouvoir mettre bas. Enfin, ils peuvent renaître directement une fois mort. | ![dragon](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/master/src/main/resources/assets/creatures/dragon.png?raw=true)          |
| **Human**       | ...                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | ...                                                                                                                                         |
| **Kraken**      | Les krakens doivent posséder les caractéristiques suivantes : un nom (d'espèce), un sexe, un poids, une taille, un âge, un indicateur de faim, un indicateur de sommeil, un indicateur de santé. Les krakens doivent pouvoir manger, émettre un son, être soigné, s'endormir ou se réveiller, viellir (et mourir s'il a atteint la dernière catégorie d'âge et qu'il doit encore viellir, ou s'il devient trop malade). De plus, les krakens doivent pouvoir nager. Ils doivent pondre des oeufs. La naissance d'un nouveau kraken dépend de sa durée de gestation et d'incubation. Les mâles ne doivent bien sûr pas pouvoir mettre bas.                                                                                | ![kraken](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/master/src/main/resources/assets/creatures/kraken.png?raw=true)          |
| **Lycanthrope** | Les lycanthropes doivent posséder les caractéristiques suivantes : un nom (d'espèce), un sexe, un poids, une taille, un âge, un indicateur de faim, un indicateur de sommeil, un indicateur de santé. Les lycanthropes doivent pouvoir manger, émettre un son, être soigné, s'endormir ou se réveiller, viellir (et mourir s'il a atteint la dernière catégorie d'âge et qu'il doit encore viellir, ou s'il devient trop malade). De plus, les lycanthropes doivent pouvoir courir. Ils doivent pouvoir mettre bas. La naissance d'un nouveau lycanthrope dépend de sa durée de gestation. Les mâles ne doivent bien sûr pas pouvoir mettre bas.                                                                         | ![lycanthrope](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/master/src/main/resources/assets/creatures/lycanthrope.png?raw=true) |
| **Megalodon**   | Les mégalodons doivent posséder les caractéristiques suivantes : un nom (d'espèce), un sexe, un poids, une taille, un âge, un indicateur de faim, un indicateur de sommeil, un indicateur de santé. Les mégalodons doivent pouvoir manger, émettre un son, être soigné, s'endormir ou se réveiller, viellir (et mourir s'il a atteint la dernière catégorie d'âge et qu'il doit encore viellir, ou s'il devient trop malade). De plus, les mégalodons doivent pouvoir nager. Ils doivent pondre des oeufs. La naissance d'un nouveau mégalodon dépend de sa durée de gestation et d'incubation. Les mâles ne doivent bien sûr pas pouvoir mettre bas.                                                                    | ![megalodon](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/master/src/main/resources/assets/creatures/megalodon.png?raw=true)    |
| **Mermaid**     | Les sirènes doivent posséder les caractéristiques suivantes : un nom (d'espèce), un sexe, un poids, une taille, un âge, un indicateur de faim, un indicateur de sommeil, un indicateur de santé. Les sirènes doivent pouvoir manger, émettre un son, être soigné, s'endormir ou se réveiller, viellir (et mourir s'il a atteint la dernière catégorie d'âge et qu'il doit encore viellir, ou s'il devient trop malade). De plus, les sirènes doivent pouvoir nager. Ils doivent pouvoir mettre bas. La naissance d'une nouvelle sirère dépend de sa durée de gestation. Les mâles ne doivent bien sûr pas pouvoir mettre bas.                                                                                            | ![mermaid](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/master/src/main/resources/assets/creatures/mermaid.png?raw=true)        |
| **Nymph**       | Les nymphes doivent posséder les caractéristiques suivantes : un nom (d'espèce), un sexe, un poids, une taille, un âge, un indicateur de faim, un indicateur de sommeil, un indicateur de santé. Les nymphes doivent pouvoir manger, émettre un son, être soigné, s'endormir ou se réveiller, viellir (et mourir s'il a atteint la dernière catégorie d'âge et qu'il doit encore viellir, ou s'il devient trop malade). De plus, les nymphes doivent pouvoir courir. Ils doivent pouvoir mettre bas. La naissance d'une nouvelle nymphe dépend de sa durée de gestation. Les mâles ne doivent bien sûr pas pouvoir mettre bas. Enfin, les nymphes doivent peuvent renaître directement une fois mort.                    | ![nymph](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/master/src/main/resources/assets/creatures/nymph.png?raw=true)            |
| **Phoenix**     | Les phénix doivent posséder les caractéristiques suivantes : un nom (d'espèce), un sexe, un poids, une taille, un âge, un indicateur de faim, un indicateur de sommeil, un indicateur de santé. Les phénix doivent pouvoir manger, émettre un son, être soigné, s'endormir ou se réveiller, viellir (et mourir s'il a atteint la dernière catégorie d'âge et qu'il doit encore viellir, ou s'il devient trop malade). De plus, les phénix doivent pouvoir voler. Ils doivent pondre des oeufs. La naissance d'un nouveau phénix dépend de sa durée de gestation et d'incubation. Les mâles ne doivent bien sûr pas pouvoir mettre bas. Enfin, les phénix doivent pouvoir renaître directement une fois mort.             | ![phoenix](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/master/src/main/resources/assets/creatures/phoenix.png?raw=true)         |
| **Unicorn**     | Les licornes doivent posséder les caractéristiques suivantes : un nom (d'espèce), un sexe, un poids, une taille, un âge, un indicateur de faim, un indicateur de sommeil, un indicateur de santé. Les licornes doivent pouvoir manger, émettre un son, être soigné, s'endormir ou se réveiller, viellir (et mourir s'il a atteint la dernière catégorie d'âge et qu'il doit encore viellir, ou s'il devient trop malade). De plus, les licornes doivent pouvoir courir. Elles doivent pouvoir mettre bas. La naissance d'une nouvelle licorne dépend de sa durée de gestation. Les mâles ne doivent bien sûr pas pouvoir mettre bas.                                                                      | ![unicorn](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/master/src/main/resources/assets/creatures/unicorn.png?raw=true)        |

***

#### DEFINITION DES FONCTIONNALITES

| **Caractéristiques**  | **Description**                                                                                                       |
|-----------------------|-----------------------------------------------------------------------------------------------------------------------|
| **Créatures :**       | **Description créatures :**                                                                                           |
| Nom                   | Nommer toutes les créatures nécessaires                                                                               |
| Sexe                  | Définir le sexe des créatures                                                                                         |
| Poids                 | Définir le poids des créatures                                                                                        |
| Taille                | Définir la taille des créatures                                                                                       |
| Age                   | Définir l'âge des créatures                                                                                           |
| Faim                  | Définir un indicateur de faim des créatures                                                                           |
| Sommeil               | Définir un indicateur de sommeil des créatures                                                                        |
| Santé                 | Définir un indicateur de santé des créatures                                                                          |
| **Lycanthrope :**     | **Description lycanthrope :**                                                                                         |
| Sexe                  | Définir le sexe des lycanthropes                                                                                      |
| Categorie d'age       | Définir la categorie d'âge des lycanthropes                                                                           |
| Force                 | Définir la force des lycanthropes                                                                                     |
| Facteur de domination | Définir un facteur de domination des lycanthropes                                                                     |
| Rang de domination    | Définir un rang de domination des lycanthropes                                                                        |
| Niveau                | Définir un niveau des lycanthropes en fonction de sa categorie d'age, sa force, son facteur et son rang de domination |
| Facteur d'impétuosité | Définir un facteur d'impétuosité des lycanthropes                                                                     |
| Son entourage         | Définir la meute des lycanthropes ou s'il est solitaire                                                               |
| Séparer de la meute   | Séparer un lycanthrope de sa meute                                                                                    |
| Le transformer       | Transformer un lycanthrope en humain                                                                                  |
| **Meute :**           | **Description meute :**                                                                                               |
| Nombre de lycanthrope | Définir le nombre de lycanthropes dans une meute                                                                      |
| Couple alpha          | Définir un couple alpha dans une meute qui dirige la meute                                                            |
| Hurlement            | Communiquer avec les autres lycanthropes                                                                              |
| Ecouter             | Ecouter les hurlements des autres lycanthropes s'il ne dort pas ou malade                                             |
| **Enclos :**         | **Description enclos :**                                                                                              |
| Nom                  | Nommer tous les enclos nécessaires selon le type de l'enclos                                                          |
| Superficie           | Définir la superficie des enclos                                                                                      |
| Nombre maximum de créatures | Définir le nombre maximum de créatures dans un enclos                                                                 |
| Nombre de créatures présentes | Définir le nombre de créatures présentes dans un enclos                                                               |
|Les créatures présentes | Définir les créatures présentes dans un enclos                                                                        |
| Propreté             | Définir un indicateur de propreté des enclos                                                                          |
| **Voliere :**        | **Description voliere :**                                                                                             |
| Hauteur              | Définir la hauteur des volieres                                                                                       |
| Créatures présentes | Une voliere ne peut contenir que des créatures volantes                                                               |
|Entretien des volieres | Vérification du toit des volieres en plus de l'entretien classique des enclos                                         |
| **Aquarium :**       | **Description aquarium :**                                                                                            |
| Profondeur           | Définir la profondeur et la salinité des aquariums                                                                    |
| Créatures présentes | Un aquarium ne peut contenir que des créatures marines                                                                |
|Entretien des aquariums | Vérification de la profondeur et la salinité des aquariums à la place de l'entretien classique des enclos             |
|**Maitre du Zoo :**   | **Description maitre du zoo :**                                                                                       |
| Nom                  | Nommer le maitre du zoo                                                                                               |
| Sexe                 | Définir le sexe du maitre du zoo                                                                                      |
| Age                  | Définir l'âge du maitre du zoo                                                                                        |
| **Zoo :**           | **Description zoo :**                                                                                                 |
| Nom                  | Nommer le zoo                                                                                                         |
| Un maitre de Zoo     | Le Zoo doit avoir son maitre de Zoo                                                                                   |
| Des enclos           | Le Zoo doit avoir un nombre maximal d'enclos                                                                          |
 
***
| **Obligations** | **Description**                                                                                                              |
|-----------------|------------------------------------------------------------------------------------------------------------------------------|
| **Créatures :** | **Description créatures :**                                                                                                  |
| Manger | Les créatures doivent manger pour survivre                                                                                   |
| Emettre un son | Les créatures doivent émettre un son pour communiquer                                                                        |
| Etre soigné | Les créatures doivent être soigné pour survivre                                                                              |
| Dormir | Les créatures doivent dormir pour survivre                                                                                   |
| Se réveiller | Les créatures doivent se réveiller pour survivre                                                                             |
| Viellir | Les créatures doivent viellir pour survivre                                                                                  |
| Mourir | Les créatures doivent mourir si elles sont trop malades ou trop viellies                                                     |
| **Lycanthrope :** | **Description lycanthrope :**                                                                                                |
| Hurler | Les lycanthropes doivent hurler pour communiquer                                                                             |
| Ecouter | Les lycanthropes doivent écouter les hurlements des autres lycanthropes si ils ne sont pas malades ni endormis               |
| Se transformer | Les lycanthropes doivent se transformer en humain                                                                            |
| Transformation | Lors de la transformation, les lycanthropes ont une chance de quitter la meute en fonction du niveau ce qui provoque sa mort |
| **Meute :** | **Description meute :**                                                                                                      |
| Hierarchie | Les lycanthropes doivent avoir une hierarchie dans la meute                                                                  |
| Couple alpha | Les lycanthropes doivent avoir un couple alpha dans la meute                                                                 |
| Reproduction | Les lycanthropes doivent pouvoir se reproduire                                                                               |
| Rang de domniation | Il faut pouvoir changer les rang de domination des lycanthropes dans la meute                                                |
| Ajouter un lycanthrope | Il faut pouvoir ajouter ou enlever un lycanthrope dans la meute                                                              |
| **Enclos :** | **Description enclos :**                                                                                                     |
| Caractéristiques | Il faut pouvoir afficher les caractéristiques d'un enclos ainsi que celles des créatures qu'il contient                      |
| Ajouter des créatures | Il faut pouvoir ajouter ou enlever des créatures dans un enclos                                                              |
| Nourrir les créatures | Il faut pouvoir nourrir les créatures dans un enclos                                                                         |
| Nettoyer les enclos | Il faut pouvoir nettoyer les enclos                                                                                          |
| **Maitre du Zoo :** | **Description maitre du zoo :**                                                                                              |
| Examiner les enclos | Le maitre du zoo doit pouvoir examiner les enclos                                                                            |
| Nettoyer les enclos | Le maitre du zoo doit pouvoir nettoyer les enclos                                                                            |
| Nourrir les créatures | Le maitre du zoo doit pouvoir nourrir les créatures                                                                          |
| Transfers des créatures | Le maitre du zoo doit pouvoir transferer des créatures d'un enclos à un autre                                                |
| **Zoo :** | **Description zoo :**                                                                                                        |
| Afficher nombre de créatures | Le zoo doit pouvoir afficher le nombre de créatures présentes dans le zoo                                                    |
| Afficher les créatures | Le zoo doit pouvoir afficher les créatures présentes dans le zoo                                                             |
| Modifier état des créatures | Rendre malade, endormir les créatures aléatoirement                                                                          |
| Modifier état des enclos | Modifier les états de propreté, salinité... des enclos aléatoirement                                                         |

***

### CONCEPTION DE L'INTERFACE UTILISATEUR (UI)

Toutes les images de notre application ont été réalisées par Dall-e

Afin de réaliser notre application nous avons pris comme base cette maquette :
![maquette](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/maquette.png?raw=true)

Nous avons opté pour cette image en arrière plan car nous avons trouvé qu'elle peut correspondre à l'univers de notre application :
![Background](https://github.com/GeniusTom-Dev/Oasis-des-Legendes/blob/interface/src/main/resources/assets/background.png?raw=true)

***

### ARCHITECTURE DE L'INFORMATION
***

### CHOIX DES TECHNOLOGIES

Pour la réalisation des images de notre application, nous avons utilisé l'IA Dall-e.
Pour la réalisation de l'interface graphique, nous avons utilisé JavaFX.
Le reste du projet est réalisé en Java avec l'IDE IntelliJ. 
Nous avons utilisé GitHub pour la mise en commun du code de l'application.

***

### SECURITE ET PERFORMANCES
***

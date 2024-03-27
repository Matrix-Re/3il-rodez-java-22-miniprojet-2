<img src="LOGO.png" alt="Description de l'image" width="700" height="200">

Comme on parle de trouver le chemin le plus court entre deux points, j'ai renommé ce projet **Waze ou presque**, je trouve que c'est plus parlant 😂.

## Réponses aux questions

**Question :** Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes ?<br>
**Réponse :** une maps pour la relation entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes.

**Question :** Pourquoi pensez-vous que les classes `Noeud` et `Graphe` ont été définies avec des paramètres génériques ?<br>
**Réponse :** pour pouvoir faire des noeuds et des graphes de différents types, comme des strings, int, case, etc.


**Question :** Pourquoi pensez-vous que la création d'une interface est une bonne pratique dans ce contexte ?<br><br>
**Réponse:** L'utilisation de l'interface `AlgorithmeChemin` est une pratique recommandée, car elle permet de répondre efficacement à la demande du client concernant un algorithme de chemin spécifique, même lorsque le fournisseur initial ne dispose pas directement de cette fonctionnalité. En intégrant une implémentation accompagnée d'un adaptateur, cette interface facilite la communication entre le client et le fournisseur, assurant ainsi la récupération et le transfert approprié des données dans le format requis.

## Feedback

J'ai grandement apprécié le projet et je pense qu'il est fort probable que j'utilise l'un ou les deux algorithmes dans la conception d'un jeu vidéo que je prévois de publier sur mon GitHub 😉. Mis à part cela, je n'ai pas rencontré de difficultés majeures pour comprendre le code et le modifier afin de répondre aux questions posées. Le seul obstacle que j'ai rencontré a été lié à l'adaptateur. J'ai eu du mal à saisir son utilité et son fonctionnement au début, mais après avoir examiné attentivement le code et les commentaires, j'ai finalement saisi son rôle et son fonctionnement.

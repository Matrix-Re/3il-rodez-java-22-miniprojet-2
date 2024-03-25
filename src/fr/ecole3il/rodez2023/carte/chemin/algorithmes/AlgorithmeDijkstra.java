package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.elements.*;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    @Override
    public Chemin trouverChemin(Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee){
        return AdaptateurAlgorithme.trouverChemin((AlgorithmeChemin<Case>) this, carte, xDepart, yDepart, xArrivee, yArrivee);
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        Set<Noeud<E>> noeudsVisites = new HashSet<>();

        // On initialise les coûts à l'infini
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.MAX_VALUE);
        }
        // Sauf pour le départ
        couts.put(depart, 0.0);

        // On fait une boucle tant que le noeud d'arrivée n'a pas été visité
        while (!noeudsVisites.contains(arrivee)) {
            // Trouver le noeud non visité avec le coût le plus bas
            Noeud<E> noeudActuel = getNoeudMinimum(couts, noeudsVisites);
            // On ajoute le noeud actuel à la liste des noeuds visités
            noeudsVisites.add(noeudActuel);

            /* Pour chaque voisin de noeudActuel, si le coût total pour atteindre le voisin via noeudActuel est inférieur
            * au coût actuel pour atteindre le voisin, mettre à jour le coût dans la carte des coûts et mettre à jour le
            * prédécesseur du voisin à noeudActuel. */
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double coutTotal = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);

                double coutVoisin = couts.get(voisin);

                if (coutTotal < coutVoisin) {
                    couts.put(voisin, coutTotal);
                    predecesseurs.put(voisin, noeudActuel);
                }
            }
        }

        /* Une fois que le nœud d'arrivée a été visité, construire le chemin le plus court en remontant à partir du nœud
        * d'arrivée jusqu'au nœud de départ en utilisant la carte des prédécesseurs. */
        List<Noeud<E>> chemin = new ArrayList<>();
        for (Noeud<E> noeud = arrivee; noeud != null; noeud = predecesseurs.get(noeud)) {
            chemin.add(0, noeud);
        }

        if (chemin.get(0).equals(depart)) {
            return chemin;
        } else {
            return new ArrayList<>();
        }
    }

    private Noeud<E> getNoeudMinimum(Map<Noeud<E>, Double> couts, Set<Noeud<E>> noeudsVisites) {
        Noeud<E> noeudMinimum = null;
        double minimum = Double.MAX_VALUE;

        for (Map.Entry<Noeud<E>, Double> entry : couts.entrySet()) {
            if (!noeudsVisites.contains(entry.getKey()) && entry.getValue() < minimum) {
                noeudMinimum = entry.getKey();
                minimum = entry.getValue();
            }
        }

        if (noeudMinimum == null) {
            throw new RuntimeException("Aucun chemin trouvé entre le départ et l'arrivée");
        }

        return noeudMinimum;
    }

}

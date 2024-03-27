package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.*;

import java.util.*;

public class AlgorithmeAEtoile <E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {

        if (!graphe.getNoeuds().contains(depart) || !graphe.getNoeuds().contains(arrivee)) {
            return new ArrayList<>();
        }

        Map<Noeud<E>, Double> coutTotalEstime = new HashMap<>();
        Map<Noeud<E>, Double> coutReel = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(coutTotalEstime::get));

        // Initialisation des structures de données
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutTotalEstime.put(noeud, Double.MAX_VALUE);
            coutReel.put(noeud, Double.MAX_VALUE);
            predecesseurs.put(noeud, null);
        }
        coutReel.put(depart, 0.0);
        coutTotalEstime.put(depart, 0.0); // Coût initial estimé du départ est 0

        filePriorite.add(depart);

        // Boucle principale
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(arrivee)) {
                break;
            }
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouveauCoutReel = coutReel.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauCoutReel < coutReel.get(voisin)) {
                    coutReel.put(voisin, nouveauCoutReel);
                    coutTotalEstime.put(voisin, nouveauCoutReel + estimerCout(voisin, arrivee)); // Mise à jour de l'estimation heuristique
                    predecesseurs.put(voisin, noeudActuel);
                    filePriorite.add(voisin);
                }
            }
        }

        // Reconstruction du chemin
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            chemin.add(noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    private double estimerCout(Noeud<E> noeud, Noeud<E> arrivee) {
        // Exemple d'estimation heuristique : distance entre les nœuds en utilisant le nombre de pas nécessaires
        return 1.0; // Coût constant pour chaque nœud
    }

    @Override
    public Chemin trouverChemin(Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        return AdaptateurAlgorithme.trouverChemin((AlgorithmeChemin<Case>) this, carte, xDepart, yDepart, xArrivee, yArrivee);
    }

}
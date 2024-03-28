package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.List;

public interface AlgorithmeChemin<E> {

    /**
     * This method finds the shortest path from a starting node to an end node in a graph.
     *
     * @param graphe The graph in which to find the path.
     * @param depart The starting node.
     * @param arrivee The end node.
     * @return The shortest path from the starting node to the end node.
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);

    /**
     * This method finds the shortest path from a starting point to an end point on a map.
     *
     * @param carte The map on which to find the path.
     * @param xDepart The x-coordinate of the starting point.
     * @param yDepart The y-coordinate of the starting point.
     * @param xArrivee The x-coordinate of the end point.
     * @param yArrivee The y-coordinate of the end point.
     * @return The shortest path from the starting point to the end point.
     */
    Chemin trouverChemin(Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee);
}

package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.*;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme{

    /**
     * This method finds the shortest path from a starting point to an end point on a map.
     *
     * @param algorithme The algorithm to use for finding the path.
     * @param carte The map on which to find the path.
     * @param xDepart The x-coordinate of the starting point.
     * @param yDepart The y-coordinate of the starting point.
     * @param xArrivee The x-coordinate of the end point.
     * @param yArrivee The y-coordinate of the end point.
     * @return The shortest path from the starting point to the end point.
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee){
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> noeudDepart = graphe.getNoeud(xDepart, yDepart);
        Noeud<Case> noeudArrivee = graphe.getNoeud(xArrivee, yArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, noeudDepart, noeudArrivee);
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : cheminNoeuds) {
            cheminCases.add(noeud.getValeur());
        }

        return new Chemin(cheminCases);
    }

    /**
     * This method creates a graph from a map.
     *
     * @param carte The map from which to create the graph.
     * @return The graph representing the map.
     */
    static Graphe<Case> creerGraphe(Carte carte){
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        // Création des nœuds
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                graphe.ajouterNoeud(new Noeud<>(caseActuelle));
            }
        }

        // Ajout des arêtes
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                ajouterAretesVoisines(graphe, caseActuelle, x, y, largeur, hauteur);
            }
        }

        return graphe;
    }

    /**
     * This method adds edges to the nodes in the graph that represent neighboring tiles in the map.
     *
     * @param graphe The graph to which to add the edges.
     * @param currentCase The current case in the map.
     * @param x The x-coordinate of the current case.
     * @param y The y-coordinate of the current case.
     * @param largeur The width of the map.
     * @param hauteur The height of the map.
     */
    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur){
        // Get the current node from the graph
        Noeud<Case> currentNode = null;
        for (Noeud<Case> noeud : graphe.getNoeuds()) {
            Case c = noeud.getValeur();
            if (c.equals(currentCase)) {
                currentNode = noeud;
                break;
            }
        }

        // Check all four directions around the current case
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            // Check if the new coordinates are within the grid
            if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                // Get the neighboring case and node
                /**
                * problème de référence de variable
                * */
                Noeud<Case> neighborNode = graphe.getNoeud(newX, newY);
                Case neighborCase = neighborNode.getValeur();

                // Calculate the cost to move from the current case to the neighboring case
                double cost = calculerCout(currentCase, neighborCase);

                // Add an edge between the current node and the neighboring node
                graphe.ajouterArete(currentNode, neighborNode, cost);

                // Add the neighboring node as a neighbor of the current node
                currentNode.ajouterVoisin(neighborNode);
            }
        }
    }

    /**
     * This method calculates the cost to move from one tile to another.
     *
     * @param from The tile from which to move.
     * @param to The tile to which to move.
     * @return The cost to move from the first tile to the second tile.
     */
    static double calculerCout(Case from, Case to){
        // distance de Manhattan car on ne peut se déplacer que dans 4 directions horizontales et verticales
        // sinon on aurait pu utiliser la distance euclidienne pour un déplacement en diagonale
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    /**
     * This method displays the path from a starting point to an end point on a map.
     *
     * @param chemin The path to display.
     */
    static void afficherChemin(List<Noeud<Case>> chemin){
        for (Noeud<Case> noeud : chemin) {
            Case caseActuelle = noeud.getValeur();
            System.out.println("Case: x = " + caseActuelle.getX() + ", y = " + caseActuelle.getY());
        }
    }
}

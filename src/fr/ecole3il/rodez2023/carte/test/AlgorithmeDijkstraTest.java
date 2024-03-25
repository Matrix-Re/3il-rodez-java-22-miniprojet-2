package fr.ecole3il.rodez2023.carte.test;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmeDijkstraTest {
    private AlgorithmeDijkstra algorithmeDijkstra;
    private Graphe<String> graphe;
    private Noeud<String> noeudA;
    private Noeud<String> noeudB;
    private Noeud<String> noeudC;
    private Noeud<String> noeudD;
    private Noeud<String> noeudE;

    @BeforeEach
    public void setUp() {
        algorithmeDijkstra = new AlgorithmeDijkstra();
        graphe = new Graphe<>();
        noeudA = new Noeud<>("NoeudA");
        noeudB = new Noeud<>("NoeudB");
        noeudC = new Noeud<>("NoeudC");
        noeudD = new Noeud<>("NoeudD");

        noeudE = new Noeud<>("NoeudE");

        noeudA.ajouterVoisin(noeudB);
        noeudA.ajouterVoisin(noeudC);

        noeudB.ajouterVoisin(noeudD);

        noeudC.ajouterVoisin(noeudD);

        graphe.ajouterNoeud(noeudA);
        graphe.ajouterNoeud(noeudB);
        graphe.ajouterNoeud(noeudC);
        graphe.ajouterNoeud(noeudD);

        graphe.ajouterArete(noeudA, noeudB, 1.0);
        graphe.ajouterArete(noeudA, noeudC, 2.0);
        graphe.ajouterArete(noeudB, noeudD, 3.0);
        graphe.ajouterArete(noeudC, noeudD, 4.0);
    }

    @Test
    public void trouverCheminReturnsNullWhenGraphIsEmpty() {
        graphe.getNoeuds().clear();
        List<Noeud> chemin = algorithmeDijkstra.trouverChemin(graphe, noeudA, noeudD);
        assertTrue(chemin.isEmpty());
    }

    @Test
    public void trouverCheminReturnsCorrectPathWhenGraphHasOneNode() {
        // Add only one node to the graph
        List<Noeud> chemin = algorithmeDijkstra.trouverChemin(graphe, noeudA, noeudA);
        assertEquals(Collections.singletonList(noeudA), chemin);
    }

    @Test
    public void trouverCheminReturnsCorrectPathWhenGraphHasTwoNodes() {
        // Add two nodes to the graph
        List<Noeud> chemin = algorithmeDijkstra.trouverChemin(graphe, noeudA, noeudB);
        assertEquals(Arrays.asList(noeudA, noeudB), chemin);
    }

    @Test
    public void trouverCheminThrowsExceptionWhenStartNodeIsNotInGraph() {
        List<Noeud> chemin = algorithmeDijkstra.trouverChemin(graphe, noeudA, noeudE);
        assertTrue(chemin.isEmpty());
    }

    @Test
    public void trouverCheminThrowsExceptionWhenEndNodeIsNotInGraph() {
        List<Noeud> chemin = algorithmeDijkstra.trouverChemin(graphe, noeudE, noeudA);
        assertTrue(chemin.isEmpty());
    }
}
package fr.ecole3il.rodez2023.carte.test;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrapheTest {

    private Graphe<String> graphe;
    private Noeud<String> noeud1;
    private Noeud<String> noeud2;

    @BeforeEach
    void setUp() {
        graphe = new Graphe<>();
        noeud1 = new Noeud<>("Noeud1");
        noeud2 = new Noeud<>("Noeud2");
    }

    @Test
    void ajouterNoeudShouldAddNoeud() {
        graphe.ajouterNoeud(noeud1);
        assertTrue(graphe.getNoeuds().contains(noeud1));
    }

    @Test
    void ajouterNoeudShouldNotAddDuplicateNoeud() {
        graphe.ajouterNoeud(noeud1);
        graphe.ajouterNoeud(noeud1);
        assertEquals(1, graphe.getNoeuds().size());
    }

    @Test
    void ajouterAreteShouldAddArete() {
        graphe.ajouterArete(noeud1, noeud2, 1.0);
        assertEquals(1.0, graphe.getCoutArete(noeud1, noeud2));
    }

    @Test
    void ajouterAreteShouldNotAddDuplicateArete() {
        graphe.ajouterArete(noeud1, noeud2, 1.0);
        graphe.ajouterArete(noeud1, noeud2, 2.0);
        assertEquals(1.0, graphe.getCoutArete(noeud1, noeud2));
    }

    @Test
    void getCoutAreteShouldReturnZeroIfAreteDoesNotExist() {
        assertEquals(0, graphe.getCoutArete(noeud1, noeud2));
    }

    @Test
    void getVoisinsShouldReturnEmptyListIfNoeudHasNoVoisins() {
        assertTrue(graphe.getVoisins(noeud1).isEmpty());
    }
}
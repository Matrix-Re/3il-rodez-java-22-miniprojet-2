package fr.ecole3il.rodez2023.carte.test;

import fr.ecole3il.rodez2023.carte.elements.Noeud;
import fr.ecole3il.rodez2023.carte.test.NoeudTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NoeudTest {

    private Noeud<Integer> noeud1;
    private Noeud<Integer> noeud2;

    @BeforeEach
    void setUp() {
        noeud1 = new Noeud<>(1);
        noeud2 = new Noeud<>(2);
    }

    @Test
    void ajouterVoisinAddsNeighborToNode() {
        noeud1.ajouterVoisin(noeud2);

        assertTrue(noeud1.getVoisins().contains(noeud2));
    }

    @Test
    void ajouterVoisinAddsNodeAsNeighborToNeighbor() {
        noeud1.ajouterVoisin(noeud2);

        assertTrue(noeud2.getVoisins().contains(noeud1));
    }

    @Test
    void ajouterVoisinDoesNotAddDuplicateNeighbors() {
        noeud1.ajouterVoisin(noeud2);
        noeud1.ajouterVoisin(noeud2);

        assertEquals(1, noeud1.getVoisins().size());
    }

    @Test
    void ajouterVoisinDoesNotAddNodeAsNeighborToItself() {
        noeud1.ajouterVoisin(noeud1);

        assertFalse(noeud1.getVoisins().contains(noeud1));
    }
}
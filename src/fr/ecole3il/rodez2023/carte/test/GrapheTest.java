package fr.ecole3il.rodez2023.carte.test;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GrapheTest {

    private Graphe<String> graphe;
    private Noeud<String> noeud1;
    private Noeud<String> noeud2;
    private Noeud<String> noeud3;
    private Noeud<String> noeud4;

    @BeforeEach
    void setUp() {
        graphe = new Graphe<>();
        noeud1 = new Noeud<>("Noeud1");
        noeud2 = new Noeud<>("Noeud2");
        noeud3 = new Noeud<>("Noeud3");
        noeud4 = new Noeud<>("Noeud4");

        noeud1.ajouterVoisin(noeud2);
        noeud1.ajouterVoisin(noeud3);

        noeud2.ajouterVoisin(noeud4);

        noeud3.ajouterVoisin(noeud4);

        graphe.ajouterNoeud(noeud1);
        graphe.ajouterNoeud(noeud2);
        graphe.ajouterNoeud(noeud3);
        graphe.ajouterNoeud(noeud4);

        graphe.ajouterArete(noeud1, noeud2, 1.0);
        graphe.ajouterArete(noeud1, noeud3, 2.0);
        graphe.ajouterArete(noeud2, noeud4, 3.0);
        graphe.ajouterArete(noeud3, noeud4, 4.0);
    }

    @Test
    void ajouterNoeudShouldAddNoeud() {
        Noeud<String> noeud5 = new Noeud<>("Noeud5");
        graphe.ajouterNoeud(noeud5);
        assertTrue(graphe.getNoeuds().contains(noeud5));
    }

    @Test
    void ajouterNoeudShouldNotAddDuplicateNoeud() {
        int sizeBeforeAdd = graphe.getNoeuds().size();
        graphe.ajouterNoeud(noeud1);
        assertEquals(sizeBeforeAdd, graphe.getNoeuds().size());
    }

    @Test
    void ajouterAreteShouldAddArete() {
        graphe.ajouterArete(noeud1, noeud4, 5.0);
        assertEquals(5.0, graphe.getCoutArete(noeud1, noeud4));
    }

    @Test
    void ajouterAreteShouldNotAddDuplicateArete() {
        graphe.ajouterArete(noeud1, noeud2, 6.0);
        assertEquals(1.0, graphe.getCoutArete(noeud1, noeud2));
    }

    @Test
    void getCoutAreteShouldReturnZeroIfAreteDoesNotExist() {
        assertEquals(0, graphe.getCoutArete(noeud2, noeud3));
    }

    @Test
    void getVoisinsShouldReturnEmptyListIfNoeudHasNoVoisins() {
        Noeud<String> noeud5 = new Noeud<>("Noeud5");
        graphe.ajouterNoeud(noeud5);
        assertTrue(graphe.getVoisins(noeud5).isEmpty());
    }
}
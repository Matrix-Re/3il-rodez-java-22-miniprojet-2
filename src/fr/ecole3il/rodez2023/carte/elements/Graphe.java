package fr.ecole3il.rodez2023.carte.elements;

import java.util.*;

public class Graphe<E> {

    List <Noeud<E>> listNoeuds = new ArrayList<>();
    Map<AbstractMap.SimpleEntry<Noeud<E>, Noeud<E>>, Double> aretes = new HashMap<>();

    public void ajouterNoeud(Noeud<E> noeud){
        if (!listNoeuds.contains(noeud))
            listNoeuds.add(noeud);
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout){
        if (getCoutArete(depart, arrivee) == 0)
            aretes.put(new AbstractMap.SimpleEntry<Noeud<E>, Noeud<E>>(depart, arrivee), cout);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee){
        AbstractMap.SimpleEntry<Noeud<E>, Noeud<E>> key = new AbstractMap.SimpleEntry<>(depart, arrivee);
        return (aretes.containsKey(key)) ? aretes.get(key) : 0;
    }

    public List<Noeud<E>> getNoeuds(){
        return listNoeuds;
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud){
        return noeud.getVoisins();
    }
}

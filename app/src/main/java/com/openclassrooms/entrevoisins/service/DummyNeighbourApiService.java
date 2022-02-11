package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    @Override
    public List<Neighbour> getFavoriteNeighbour() {
        ArrayList<Neighbour> favoriNeighbour = new ArrayList<>();
        for (Neighbour  i : neighbours) {
            if (i.getFavor()) {
                favoriNeighbour.add(i);
            }
        }
        return favoriNeighbour;
    }

    @Override
    public void createFavoriteNeighbour(Neighbour neighbour) {
        neighbour.setFavor(true);
//        la liste des favoris est recréée à chaque fois, donc, inutile de faire un add
//        la modification du booléen dans la liste des voisins est suffisante
//        favoriNeighbour.add(neighbour);
    }

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        neighbour.setFavor(false);
//        favoriNeighbour.remove(neighbour);
    }

    @Override
    public void deleteAllFavoriteNeighbour() {
        for (Neighbour neighbour: neighbours){
            neighbour.setFavor(false);
        }
    }



}


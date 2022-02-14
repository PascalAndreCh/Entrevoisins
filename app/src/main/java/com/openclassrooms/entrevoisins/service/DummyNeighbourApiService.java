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



    //TODO code rajouté en dessous
    // création liste des favoris

    @Override
    public List<Neighbour> getFavoriteNeighbour () {
        ArrayList<Neighbour> favoriNeighbour = new ArrayList<>();
        for (Neighbour  i : neighbours) {
            if (i.getFavor()) {
                favoriNeighbour.add(i);
            }
        }
             return favoriNeighbour ;
    }

    @Override
    public void createFavoriteNeighbour (Neighbour neighbour) {
        neighbour.setFavor(true);
    }

    @Override
    public void deleteFavoriteNeighbour (Neighbour neighbour) {
        neighbour.setFavor(false);
    }

    @Override
    public void deleteAllFavoriteNeighbour() {
        for (Neighbour neighbour: neighbours){
            neighbour.setFavor(false);
        }
    }


}


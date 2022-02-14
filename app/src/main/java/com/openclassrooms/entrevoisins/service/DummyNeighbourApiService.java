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
    public List<Neighbour> getFavoriNeighbour () {
        ArrayList<Neighbour> favoriNeighbour = new ArrayList<>();
        int j = 0;
        for (Neighbour  i : neighbours) {
            if (i.getFavor()) {
                favoriNeighbour.add(i);
                j++;
            }
        }
        if (j != 0) {
            return favoriNeighbour;
        } else {
            favoriNeighbour.clear();
            return favoriNeighbour ;
        }
    }

    @Override
    public void createFavoriNeighbour (Neighbour neighbour) {
        neighbour.setFavor(true);
    }

    @Override
    public void deleteFavoriNeighbour (Neighbour neighbour) {
        neighbour.setFavor(false);
    }

}


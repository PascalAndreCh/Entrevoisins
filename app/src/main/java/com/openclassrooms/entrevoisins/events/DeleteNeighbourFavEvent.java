package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.NeighbourFav;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteNeighbourFavEvent {

    /**
     * Neighbour to delete
     */
    public NeighbourFav neighbourFav;

    /**
     * Constructor.
     * @param neighbourFav
     */
    public DeleteNeighbourFavEvent(NeighbourFav neighbourFav) {
        this.neighbourFav = neighbourFav;
    }
}

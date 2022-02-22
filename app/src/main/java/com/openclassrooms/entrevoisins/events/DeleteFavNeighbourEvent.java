package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user delete a Favor Neighbour
 */
public class DeleteFavNeighbourEvent {

    /**
     * Neighbour to delete favor
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     *
     * @param neighbour
     */
    public DeleteFavNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}

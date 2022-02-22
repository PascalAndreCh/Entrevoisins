package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user delete a Neighbour to favor list
 */
public class DeleteFavNeighbourEvent {

    /**
     * Neighbour to delete
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

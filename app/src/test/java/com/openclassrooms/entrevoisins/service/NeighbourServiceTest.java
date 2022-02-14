package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
        service.deleteAllFavoriteNeighbour();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getNeighbourFavoriteWithSuccess() {
        Neighbour neighbour = service.getNeighbours().get(0); // On recupère le 1er voisin de la list des neighbours
        assertTrue(service.getFavoriteNeighbour().size() == 0); // on verifie que la list de favori est vide
        service.createFavoriteNeighbour(neighbour); // On ajoute ce voisin en favori
        assertTrue(service.getFavoriteNeighbour().size() > 0); // on verifie que la list de favori n'est pas vide
    }

    @Test
    public void addNeighbourFavoriteWithSuccess() {
        assertEquals(0, service.getFavoriteNeighbour().size());
        List<Neighbour> neighbours = service.getNeighbours();
        service.createFavoriteNeighbour(neighbours.get(0));
        assertEquals(1, service.getFavoriteNeighbour().size());
        assertEquals(neighbours.get(0), service.getFavoriteNeighbour().get(0)); // On verifie que le voisin ajouté est bien le bon
    }

    @Test
    public void deleteNeighbourFavoriteWithSuccess() {
        //TODO
    }
}

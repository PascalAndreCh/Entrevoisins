package com.openclassrooms.entrevoisins.service;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

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
    public void createNeighbourWithSuccess() {
        Neighbour newNeighbour = new Neighbour(13, "Pascal", "https://i.pravatar.cc/150?u=a042581f4e29026702d", "rue d'en face", "0721222324", "bonjour, je n'est rien a dire pour l'instant", false);
        service.createNeighbour(newNeighbour);
        assertTrue(service.getNeighbours().contains(newNeighbour));
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
        assertEquals(0, service.getFavoriteNeighbour().size()); // on vérifie que la liste de favori est vide
        List<Neighbour> neighbours = service.getNeighbours(); // on récupère la liste des voisins
        service.createFavoriteNeighbour(neighbours.get(0)); // on crée un favori en prenant le premier voisin
        assertEquals(1, service.getFavoriteNeighbour().size()); // on vérifie qu'un favori a bien été créé
        assertEquals(neighbours.get(0), service.getFavoriteNeighbour().get(0)); // On verifie que le voisin ajouté est bien le bon
    }

    @Test
    public void deleteNeighbourFavoriteWithSuccess() {
        service.createFavoriteNeighbour(service.getNeighbours().get(0)); // on crée 3 favoris
        service.createFavoriteNeighbour(service.getNeighbours().get(3));
        service.createFavoriteNeighbour(service.getNeighbours().get(7));
        Neighbour favoriteNeighbourToDelete = service.getFavoriteNeighbour().get(1);
        service.deleteNeighbour(favoriteNeighbourToDelete); // on supprime le 2eme;
        assertFalse(service.getFavoriteNeighbour().contains(favoriteNeighbourToDelete)); // on vérifie qu'il n'est plus dans la liste des favoris
    }
}

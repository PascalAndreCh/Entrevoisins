package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {
    int indice = -1;
    int numberFav = 0;

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
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

        for ( int i = 0; i < service.getNeighbours().size(); i++) {
                if (service.getNeighbours().get(i).getFavor() == false) {
                    if (indice == -1) {
                        indice = i; // on récupère l'indice du premier voisin qui n'est pas favori
                    }
                } else {
                        numberFav++; // on récupère le nombre de favori déjà existants s'il y en a
                    }
        }

        if (indice == -1) {
            assertTrue(service.getFavoriNeighbour().size() == service.getNeighbours().size()); // si tous les voisins sont
            // des favoris, on vérifie que la taille des 2 listes est identique
        } else if (numberFav == 0) {
            assertTrue(service.getFavoriNeighbour().size() == 0); // si le nombre de favoris est nul,
            // on vérifie que la liste des favoris est vide
        } else {
            assertFalse(service.getFavoriNeighbour().size() == 0); // sinon on vérifie que la liste
            // des favoris est non vide car il y a au moins un favori

            assertTrue(service.getFavoriNeighbour().size() == numberFav); // on vérifie que tous les voisins pointés
            // comme favori sont bien comptabilisés dans la liste des favoris
        }

        if (indice != -1) {
            // si la liste de favoris ne contient pas la totalité des voisins, on y ajoute celui retenu
            Neighbour neighbour = service.getNeighbours().get(indice);
            service.createFavoriNeighbour(neighbour);
            assertTrue(service.getFavoriNeighbour().size() == numberFav+1); // on vérifie que le nouveau favori
            // a bien été crée, donc que la liste des favoris a bien un éléments de plus
        }
     }

}

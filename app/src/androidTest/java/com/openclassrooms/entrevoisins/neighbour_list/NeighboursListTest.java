
package com.openclassrooms.entrevoisins.neighbour_list;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.DeleteViewActionFav;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int ITEMS_FAV_COUNT = 3;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myFavoriteNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_favori_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myFavoriteNeighboursList_deleteAction_shouldRemoveItem() {
        // suppression d'un favori et r??affichage liste si clic sur etoile dans liste des favoris
        // Given : We remove the element at position 2

        onView(ViewMatchers.withText(R.string.tab_favorites_title)).perform(click());

        onView(ViewMatchers.withId(R.id.list_favori_neighbours)).check(withItemCount(ITEMS_FAV_COUNT));
        // When perform a click on a star icon
        onView(ViewMatchers.withId(R.id.list_favori_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewActionFav()));
        // Then : the number of element is 3
        onView(ViewMatchers.withId(R.id.list_favori_neighbours)).check(withItemCount(ITEMS_FAV_COUNT - 1));
        ITEMS_FAV_COUNT--;
    }

    @Test
    public void myNeighboursList_click_shouldOpenNeighbourActivity() {
        Neighbour neighbour = DI.getNeighbourApiService().getNeighbours().get(3);

        //On performe un clic ?? la position 3 sur la vue list_neighbours
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        //Check that the name is the right one
        onView(ViewMatchers.withId(R.id.name)).check(matches(withText(neighbour.getName())));
    }

    @Test
    public void when_click_onfavoritebutton_then_favoritelist_should_be_not_empty() {
        onView(ViewMatchers.withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.starSelect)) // dans l'??cran d??tail, on clic sur l'??toile
                .perform(click());
        onView(ViewMatchers.withId(R.id.button_back)) // dans l'??cran d??tail, on clic sur le bouton retour pour revenir dans l'activit?? pr??c??dente
                .perform(click());
        onView(ViewMatchers.withId(R.id.list_favori_neighbours)).check(withItemCount(ITEMS_FAV_COUNT + 1)); // on v??rifie qu'un favori a ??t?? cr????
        ITEMS_FAV_COUNT++;
    }

}
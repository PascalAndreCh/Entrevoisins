package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.button.MaterialButton;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailNeighbourActivity extends AppCompatActivity {
    // TODO

    @BindView(R.id.avatarFav)
    ImageView avatarFav;
    @BindView(R.id.nameLytFav)
    TextView nameViewFav;
    @BindView(R.id.nameLytFavBis)
    TextView nameBisViewFav;
    @BindView(R.id.phoneNumberLytFav)
    TextView phoneViewFav;
    @BindView(R.id.addressLytFav)
    TextView addressViewFav;
    @BindView(R.id.aboutMeLytFav)
    TextView aboutMeViewFav;
    @BindView(R.id.button_back)
    MaterialButton backButton;
    @BindView(R.id.starSelect)
    FloatingActionButton selectFav;
    @BindView(R.id.nameLytFavFacebook)
    TextView facebookViewFav;

    private NeighbourApiService mApiService;
    private String mAdresseInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mApiService = DI.getNeighbourApiService();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
// clic sur le bouton retour arriere
                finish();
                return true;
            }
        }
// pas trouvé quand on y passe ?
        return super.onOptionsItemSelected(item);
    }




    /**
     * Generate a random image. Useful to mock image picker
     * @return String
     */
    String randomImage() {
        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent(activity, DetailNeighbourActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}

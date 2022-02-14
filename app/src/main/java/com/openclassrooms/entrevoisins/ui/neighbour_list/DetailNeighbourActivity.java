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
import android.widget.ImageButton;
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

    public static final String NEIGHBOUR_POSITION_KEY = "NEIGHBOUR_POSITION_KEY";
    public static final String PROVENANCE = "PROVENANCE";
    public static final String ID_VOISIN = "ID_VOISIN";

    Neighbour neighbour;

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phoneNumber)
    TextView numberPhone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.aboutMe)
    TextView aboutMe;
    @BindView(R.id.button_back)
    ImageButton backButton;
    @BindView(R.id.nameFacebook)
    TextView facebookName;
    @BindView(R.id.nameBis)
    TextView bisName;
    @BindView(R.id.starSelect)
    FloatingActionButton selectStar;


    private NeighbourApiService mApiService;
    private String mAdresseInternet;
    int provenanc = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighboursave);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        int position = getIntent().getIntExtra(NEIGHBOUR_POSITION_KEY, -1);
        if (position == -1) {
            finish();
            return;
        }

        // si clic sur liste favori, alors récupérer utilisateur dans liste des favoris
        // récupérer id du voisin
        long id_voisin = getIntent().getLongExtra(ID_VOISIN, 1);
        for (Neighbour i : mApiService.getNeighbours()) {
            if (id_voisin == i.getId()) {
                neighbour = i;
                break;
            }
        }

        provenanc = getIntent().getIntExtra(PROVENANCE, 1);

        if (provenanc == 1) {
//            neighbour = mApiService.getNeighbours().get(position);

        } else if (provenanc == 2) {
//            neighbour = mApiService.getFavoriNeighbour().get(position);

        } else {
//            neighbour = mApiService.getNeighbours().get(position);
        }
        name.setText(neighbour.getName());
        bisName.setText(neighbour.getName());
        address.setText(neighbour.getAddress());
        numberPhone.setText(neighbour.getPhoneNumber());
        aboutMe.setText(neighbour.getAboutMe());
        mAdresseInternet = "www.facebook.fr/" + neighbour.getName();
        facebookName.setText(mAdresseInternet);
        if (neighbour.getFavor()) {
            selectStar.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            selectStar.setImageResource(R.drawable.ic_star_white_24dp);
        }


        backButton.setOnClickListener(v -> finish());

        selectStar.setOnClickListener(v -> {
            if (neighbour.getFavor()) {
                mApiService.deleteFavoriNeighbour(neighbour);
//                neighbour.setFavor(false);
                selectStar.setImageResource(R.drawable.ic_star_white_24dp);
            } else {
                mApiService.createFavoriNeighbour(neighbour);
//                neighbour.setFavor(true);
                selectStar.setImageResource(R.drawable.ic_star_yellow_24dp);
            }
                });

        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(avatar);
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
//    public static void navigate(FragmentActivity activity) {
//        Intent intent = new Intent(activity, DetailNeighbourActivity.class);
//        ActivityCompat.startActivity(activity, intent, null);
//    }

}

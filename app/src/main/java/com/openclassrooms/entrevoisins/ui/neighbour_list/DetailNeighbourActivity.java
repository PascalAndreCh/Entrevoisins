package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailNeighbourActivity extends AppCompatActivity {

    public static final String NEIGHBOUR_POSITION_KEY = "NEIGHBOUR_POSITION_KEY";
    public static final String ID_VOISIN = "ID_VOISIN";

    Neighbour neighbour;

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.phoneNumber)
    TextInputLayout phoneNumber;
    @BindView(R.id.address)
    TextInputLayout textAddress;
    @BindView(R.id.aboutMe)
    TextInputLayout meAbout;
    @BindView(R.id.button_back)
    ImageButton backButton;
    @BindView(R.id.nameFacebook)
    TextView facebookName;
    @BindView(R.id.nameBis)
    TextInputLayout bisName;
    @BindView(R.id.starSelect)
    FloatingActionButton selectStar;

    private NeighbourApiService mApiService;
    private String mAdresseInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour_edit);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();
        int position = getIntent().getIntExtra(NEIGHBOUR_POSITION_KEY, -1);
        if (position == -1) {
            finish();
            return;
        }

        long id_voisin = getIntent().getLongExtra(ID_VOISIN, 1);
        for (Neighbour i : mApiService.getNeighbours()) {
            if (id_voisin == i.getId()) {
                neighbour = i;
                break;
            }
        }

        EditText nam = (EditText) findViewById(R.id.nameBisEdit);
        nam.setText(neighbour.getName());

        name.setText(neighbour.getName());

        EditText adr = (EditText) findViewById(R.id.addressEdit);
        adr.setText(neighbour.getAddress());

        EditText tel = (EditText) findViewById(R.id.phoneNumberEdit);
        tel.setText(neighbour.getPhoneNumber());

        EditText me = (EditText) findViewById(R.id.aboutMeEdit);
        me.setText(neighbour.getAboutMe());

        mAdresseInternet = "www.facebook.fr/" + neighbour.getName();
        facebookName.setText(mAdresseInternet);
        if (neighbour.getFavor()) {
            selectStar.setImageResource(R.drawable.ic_star_yellow_24dp);
        } else {
            selectStar.setImageResource(R.drawable.ic_star_white_24dp);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                neighbour.setName(nam.getText().toString());
                neighbour.setAddress(adr.getText().toString());
                neighbour.setPhoneNumber(tel.getText().toString());
                neighbour.setAboutMe(me.getText().toString());
                finish();
            }
        });

        selectStar.setOnClickListener(v -> {
            if (neighbour.getFavor()) {
                mApiService.deleteFavoriteNeighbour(neighbour);
                selectStar.setImageResource(R.drawable.ic_star_white_24dp);
            } else {
                mApiService.createFavoriteNeighbour(neighbour);
                selectStar.setImageResource(R.drawable.ic_star_yellow_24dp);
            }
                });

        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(avatar);


    }

}

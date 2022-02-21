package com.openclassrooms.entrevoisins.ui.neighbour_list;


import static com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity.ID_VOISIN;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity.NEIGHBOUR_POSITION_KEY;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavNeighbourEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// import javax.swing.*;

public class MyNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;

    public MyNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myPopup = new AlertDialog.Builder(v.getContext());
                myPopup.setMessage("Voulez-vous vraiment supprimer "+neighbour.getName()+" ?");
                myPopup.setTitle("****** ATTENTION ******");
                myPopup.setPositiveButton(" OUI ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (neighbour.getFavor()) {
                            EventBus.getDefault().post(new DeleteFavNeighbourEvent(neighbour));
                        }
                        EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour));
                    }
                });
                myPopup.setNegativeButton(" NON ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(v.getContext(), "ABANDON", Toast.LENGTH_SHORT).show();
                    }
                });
                myPopup.show();
            }
            });

          holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailNeighbourIntent = new Intent (v.getContext(), DetailNeighbourActivity.class);
                detailNeighbourIntent.putExtra(NEIGHBOUR_POSITION_KEY, holder.getAdapterPosition());
                long idVoiLg = neighbour.getId();
                detailNeighbourIntent.putExtra(ID_VOISIN, idVoiLg);
                v.getContext().startActivity(detailNeighbourIntent);
              }
        });

    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}


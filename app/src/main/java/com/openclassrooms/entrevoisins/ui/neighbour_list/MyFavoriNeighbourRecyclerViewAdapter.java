package com.openclassrooms.entrevoisins.ui.neighbour_list;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity.ID_VOISIN;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.DetailNeighbourActivity.NEIGHBOUR_POSITION_KEY;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mNeighbours;


    public MyFavoriNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mNeighbours = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favori_neighbour, parent, false);
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

        holder.mStarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (neighbour.getFavor()) {
                    neighbour.setFavor(false);
                    holder.mStarButton.setImageResource(R.drawable.ic_star_white_24dp);
                    EventBus.getDefault().post(new DeleteFavNeighbourEvent(neighbour));
                } else {
                    neighbour.setFavor(true);
                    holder.mStarButton.setImageResource(R.drawable.ic_star_yellow_24dp);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent detailNeighbourIntent = new Intent(v.getContext(), DetailNeighbourActivity.class);
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
        @BindView(R.id.item_list_avatarfav)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_namefav)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_star_button)
        public ImageButton mStarButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

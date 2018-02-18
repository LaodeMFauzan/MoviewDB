package com.learn.fauzan.moviewdb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by fauzan on 13/02/18.
 */

public class TopRatedAdapter extends RecyclerView.Adapter<TopRatedAdapter.ViewHolder> {
    ArrayList<ModelTopRated.ResultsBean> listTopRated;
    Context context;

    public TopRatedAdapter(ArrayList<ModelTopRated.ResultsBean> listTopRated) {
        this.listTopRated = listTopRated;
    }

    @Override
    public void onBindViewHolder(TopRatedAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(listTopRated.get(position).getTitle());
        holder.txtVote.setText(listTopRated.get(position).getOverview());
        holder.txtDate.setText(listTopRated.get(position).getRelease_date());

        String urlGambar="https://image.tmdb.org/t/p/w500"+listTopRated.get(position).getPoster_path();
        Picasso.with(context).load(urlGambar).into(holder.imgMovie);

    }

    @Override
    public int getItemCount() {
        return listTopRated.size();
    }

    @Override
    public TopRatedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);

        return new TopRatedAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        TextView txtTitle,txtVote,txtDate;

        public ViewHolder(View itemView) {
            super(itemView);

            context=itemView.getContext();
            imgMovie=(ImageView)itemView.findViewById(R.id.img_movie);
            txtDate=(TextView)itemView.findViewById(R.id.date_movie);
            txtTitle=(TextView)itemView.findViewById(R.id.title_movie);
            txtVote=(TextView)itemView.findViewById(R.id.vote_movie);

        }
    }
}

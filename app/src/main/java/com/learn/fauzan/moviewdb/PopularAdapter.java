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
 * Created by fauzan on 18/02/18.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
        ArrayList<ModelPopular.ResultsBean> listPopular;
        Context context;

    public PopularAdapter(ArrayList<ModelPopular.ResultsBean> listTopRated) {
            this.listPopular = listTopRated;
            }

    @Override
    public void onBindViewHolder(PopularAdapter.ViewHolder holder, int position) {
            holder.txtTitle.setText(listPopular.get(position).getTitle());
            holder.txtVote.setText(listPopular.get(position).getOverview());
            holder.txtDate.setText(listPopular.get(position).getRelease_date());

            String urlGambar="https://image.tmdb.org/t/p/w500"+listPopular.get(position).getPoster_path();
            Picasso.with(context).load(urlGambar).into(holder.imgMovie);

            }

    @Override
    public int getItemCount() {
            return listPopular.size();
    }

    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);

            return new PopularAdapter.ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMovie;
        TextView txtTitle,txtVote,txtDate;

        public ViewHolder(View itemView) {
            super(itemView);

            context=itemView.getContext();
            imgMovie=itemView.findViewById(R.id.img_movie);
            txtDate=itemView.findViewById(R.id.date_movie);
            txtTitle=itemView.findViewById(R.id.title_movie);
            txtVote=itemView.findViewById(R.id.vote_movie);

        }
    }
}

package com.learn.fauzan.moviewdb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fauzan on 11/02/18.
 */

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {

    ArrayList<Model.ResultsBean> listNowPlaying;
    Context context;

    public NowPlayingAdapter(ArrayList<Model.ResultsBean> listNowPlaying) {
        this.listNowPlaying = listNowPlaying;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtTitle.setText(listNowPlaying.get(position).getTitle());
        holder.txtVote.setText(listNowPlaying.get(position).getOverview());
        holder.txtDate.setText(listNowPlaying.get(position).getRelease_date());

        String urlGambar="https://image.tmdb.org/t/p/w500"+listNowPlaying.get(position).getPoster_path();
        Picasso.with(context).load(urlGambar).into(holder.imgMovie);

    }

    @Override
    public int getItemCount() {
        return listNowPlaying.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);

        return new ViewHolder(view);
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

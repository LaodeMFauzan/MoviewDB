package com.learn.fauzan.moviewdb;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMovie;
    Spinner spinner;
    MenuItem item;
    ProgressBar progressBar;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.pb_load);
        rvMovie=findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,2);
        rvMovie.setLayoutManager(layoutManager);

        progressBar.setVisibility(View.VISIBLE);

    }

    private void getDataNowPlaying(){
        rvMovie.setVisibility(View.INVISIBLE);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory
                (GsonConverterFactory.create()).build();

        AppService.ApiService apiService=retrofit.create(AppService.ApiService.class);

        Call<Model> call=apiService.getNowPlaying("movie/now_playing?api_key=9f6d0c66ac094416f3599028b9bf40e1&language=en-US&page=1");
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Model model=response.body();
                ArrayList<Model.ResultsBean> movieBean=new ArrayList<Model.ResultsBean>();

                for (int i=0; i<model.getResults().size(); i++){
                    //Log.d("Judul :"+i,model.getResults().get(i).getTitle());
                    movieBean.add(new Model.ResultsBean(
                            model.getResults().get(i).getId(),
                            model.getResults().get(i).getTitle(),
                            model.getResults().get(i).getPoster_path(),
                            Double.toString(model.getResults().get(i).getVote_average()),
                            getDisplayReleaseDate(model.getResults().get(i).getRelease_date()))
                    );
                }
                progressBar.setVisibility(View.GONE);
                rvMovie.setVisibility(View.VISIBLE);

                NowPlayingAdapter adapter=new NowPlayingAdapter(movieBean);
                rvMovie.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataTopRated(){
        rvMovie.setVisibility(View.INVISIBLE);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory
                (GsonConverterFactory.create()).build();

        AppService.ApiService apiService=retrofit.create(AppService.ApiService.class);

        Call<ModelTopRated> call=apiService.getTopRated("movie/top_rated?api_key=9f6d0c66ac094416f3599028b9bf40e1&language=en-US&page=1");
        call.enqueue(new Callback<ModelTopRated>() {
            @Override
            public void onResponse(Call<ModelTopRated> call, Response<ModelTopRated> response) {
                ModelTopRated model=response.body();
                ArrayList<ModelTopRated.ResultsBean> movieBean=new ArrayList<ModelTopRated.ResultsBean>();

                for (int i=0; i<model.getResults().size(); i++){
                    //Log.d("Judul :"+i,model.getResults().get(i).getTitle());
                    movieBean.add(new ModelTopRated.ResultsBean(
                            model.getResults().get(i).getId(),
                            model.getResults().get(i).getTitle(),
                            model.getResults().get(i).getPoster_path(),
                            Double.toString(model.getResults().get(i).getVote_average()),
                            getDisplayReleaseDate(model.getResults().get(i).getRelease_date()))
                    );
                }
                progressBar.setVisibility(View.GONE);
                rvMovie.setVisibility(View.VISIBLE);

                TopRatedAdapter adapter=new TopRatedAdapter(movieBean);
                rvMovie.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ModelTopRated> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataPopular(){
        rvMovie.setVisibility(View.INVISIBLE);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory
                (GsonConverterFactory.create()).build();
        AppService.ApiService apiService=retrofit.create(AppService.ApiService.class);

        Call<ModelPopular> call=apiService.getPopular("movie/popular?api_key=9f6d0c66ac094416f3599028b9bf40e1&language=en-US&page=1");
        call.enqueue(new Callback<ModelPopular>() {
            @Override
            public void onResponse(Call<ModelPopular> call, Response<ModelPopular> response) {
                ModelPopular model=response.body();
                ArrayList<ModelPopular.ResultsBean> movieBean=new ArrayList<ModelPopular.ResultsBean>();

                for (int i=0; i<model.getResults().size(); i++){
                    //Log.d("Judul :"+i,model.getResults().get(i).getTitle());
                    movieBean.add(new ModelPopular.ResultsBean(
                            model.getResults().get(i).getId(),
                            model.getResults().get(i).getTitle(),
                            model.getResults().get(i).getPoster_path(),
                            Double.toString(model.getResults().get(i).getPopularity()),
                            getDisplayReleaseDate(model.getResults().get(i).getRelease_date()))
                    );
                }
                progressBar.setVisibility(View.GONE);
                rvMovie.setVisibility(View.VISIBLE);

                PopularAdapter adapter=new PopularAdapter(movieBean);
                rvMovie.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ModelPopular> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view, menu);

        item = menu.findItem(R.id.spinner);
        spinner = (Spinner) MenuItemCompat.getActionView(item);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_item,R.layout.spinner_item);

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int item=spinner.getSelectedItemPosition();
                progressBar.setVisibility(View.VISIBLE);
                if(item==0){

                    getDataNowPlaying();
                }
                else if(item==1){

                    getDataTopRated();
                }
                else{
                    getDataPopular();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return true;
    }

    private String getDisplayReleaseDate(String releaseDate) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(DATE_FORMAT.parse(releaseDate));
            return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH) + " " + calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            return "";
        }
    }
}

package com.example.recyclerview_initial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    SwipeRefreshLayout swipeRefreshLayout;

    List<String> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        moviesList = new ArrayList<>();

        //RecyclerView set BEGIN
        recyclerView = findViewById(R.id.recyclerView);
        recyclerAdapter = new RecyclerAdapter(moviesList,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
        //RecyclerView set END

        //Divider er jonno
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        //Divider END


        moviesList.add("Iron Man");
        moviesList.add("Incredible Hulk");
        moviesList.add("Iron Man 2");
        moviesList.add("Thor");
        moviesList.add("Captain America");
        moviesList.add("Avenger");
        moviesList.add("Batman");
        moviesList.add("Joker");
        moviesList.add("Spider Man");
        moviesList.add("Iron Man 3");
        moviesList.add("Avengers : Infinity War");
        moviesList.add("Dr. Strange");
        moviesList.add("Avengers : End Game");
        moviesList.add("Black Panther");
        moviesList.add("Vision");
        moviesList.add("What If...");

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moviesList.add("Black Widow");
                moviesList.add("HawkEye");
                moviesList.add("Thor: Dark World");

                recyclerAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false); // This End our Refreshing
            }
        });


    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this,moviesList.get(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongItemClick(int position) {
        //Toast.makeText(this,moviesList.get(position),Toast.LENGTH_SHORT).show();
        moviesList.remove(position);
        recyclerAdapter.notifyItemRemoved(position);
    }
}
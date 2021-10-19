package com.example.recyclerview_initial;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    int count = 0;

    //Class er instanceVariables BEGIN
    List<String> moviesList;
    private RecyclerViewClickInterface recyclerViewClickInterface;
    //Class er instanceVariables END


    public RecyclerAdapter(List<String> moviesList, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.moviesList = moviesList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i(TAG, "onCreateViewHolder: " + count++);

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rowCountTextView.setText(String.valueOf(position));
        holder.textView.setText(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    //MYVIEWHOLDER CLASS BEGIN
    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, rowCountTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            rowCountTextView = itemView.findViewById(R.id.rowCountTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

//                    moviesList.remove(getAdapterPosition()); // Amader created LIST theke Movie ta delete korlam
//                    notifyItemRemoved(getAdapterPosition()); //Eita mane holo Recycler View er theke ROW ta delete kore dilam
                    recyclerViewClickInterface.onLongItemClick(getAdapterPosition());

                    return true; //Item ta nidristio POSITION ee tai "True" return kortase
                }
            });

        }


    }
    //MYVIEWHOLDER CLASS END


}

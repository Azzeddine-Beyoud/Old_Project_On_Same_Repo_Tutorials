package com.example.my_imdb.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_imdb.Model.Movie;
import com.example.my_imdb.R;
import com.example.my_imdb.View.DetailsActivity;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private Context context;
    private List<Movie> MOVIES_List;

    public MoviesAdapter(Context context,List<Movie> movieS_List) {

        this.context=context;
        this.MOVIES_List = movieS_List;
    }

    @NonNull
    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_rows,parent,false);
        //with this instruction we linked between adapter and movie_list_rows

        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MyViewHolder holder, int position) {

        Movie movieOBj=MOVIES_List.get(position);

        holder.title.setText(movieOBj.getTitle());
        holder.genre.setText(movieOBj.getGenre());
        holder.year.setText(movieOBj.getYear());
        holder.info.setText(movieOBj.getInfo());

        holder.image.setImageResource(movieOBj.getImage());


    }

    @Override
    public int getItemCount() {
        return MOVIES_List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title,genre,year,info;
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            info=itemView.findViewById(R.id.info);
            title=itemView.findViewById(R.id.title);
            genre=itemView.findViewById(R.id.genre);
            year=itemView.findViewById(R.id.year);
            image=itemView.findViewById(R.id.imagemovie);

            //we have to write itemView before findviewById because this is a class not activity
            //we mean this class isn't know activity XML alone we have to helps to find attributes
            //with (itemView)

            //here we defined all item
        }

        @Override
        public void onClick(View v) {
            int positoin = getAdapterPosition();
            Movie item=MOVIES_List.get(positoin);

            Intent intent=new Intent(context, DetailsActivity.class);
            intent.putExtra("info",item.getInfo());
            intent.putExtra("title",item.getTitle());
            intent.putExtra("genre",item.getGenre());
            intent.putExtra("year",item.getYear());
            intent.putExtra("image",item.getImage());
            context.startActivity(intent);

        }
    }
}

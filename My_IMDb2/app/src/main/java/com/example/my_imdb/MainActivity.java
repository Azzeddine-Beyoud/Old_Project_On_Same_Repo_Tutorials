package com.example.my_imdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.my_imdb.Controller.MoviesAdapter;
import com.example.my_imdb.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList;

    private RecyclerView recyclerView;

    private MoviesAdapter moviesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        moviesAdapter=new MoviesAdapter(this,movieList);
        recyclerView.setAdapter(moviesAdapter);

        MoviesData();

    }

    private void MoviesData() {

        Movie movie0=new Movie( "1. Denise Ho: Becoming the Song "," 83 min | Documentary "," 2020 ",(R.drawable.image1),"This timely documentary explores the singer's remarkable journey from Cantopop superstar to outspoken political activist, putting her life and career on the line in support of HongKonger's struggle to maintain their political freedom.");

        Movie movie1=new Movie(" 2. The Truth","PG | 106 min | Drama, Family "," 2019 ",  (R.drawable.image2),"A stormy reunion between scriptwriter Lumir with her famous mother and actress, Fabienne, against the backdrop of Fabienne's autobiographic book and her latest role in a Sci-Fi picture as a mother who never grows old.");
        Movie movie2=new Movie(" 3. The Outpost "," R | 123 min | Drama, History, War "," 2020",(R.drawable.image3),"A small team of U.S. soldiers battle against hundreds of Taliban fighters in Afghanistan.");
        Movie movie3=new Movie(" 4. John Lewis: Good Trouble "," PG | 96 min | Documentary "," 2020 ",(R.drawable.image4),"The film explores Georgia representative's, 60-plus years of social activism and legislative action on civil rights, voting rights, gun control, health care reform, and immigration.");
        Movie movie4=new Movie(" 5. Trolls World Tour "," PG | 90 min | Animation, Adventure, Comedy "," 2020 ",(R.drawable.image5),"When the Queen of the Hard Rock Trolls tries to take over all the Troll kingdoms, Queen Poppy and her friends try different ways to save all the Trolls.");
        Movie movie5=new Movie(" 6. Blood and Money "," 89 min | Thriller ","",(R.drawable.image6),"A retired veteran hunting in Northern Maine stumbles across a dead woman and a large sum of money.");
        Movie movie6=new Movie(" Proximity (II) "," 119 min | Drama, Sci-Fi "," 2020 ",(R.drawable.image7),"A young NASA JPL scientist is abducted by extraterrestrials but when no one believes his story he becomes obsessed with finding proof which leads him on a journey of discovery.");
        Movie movie7=new Movie(" 8. Hope Gap  "," PG-13 | 100 min | Drama, Romance "," 2020 ",(R.drawable.image8),"A couple's visit with their son takes a dramatic turn when the father tells him he plans on leaving his mother.");
        Movie movie8=new Movie(" 9. Sorry We Missed You  "," 101 min | Drama "," 2019 ",(R.drawable.image9),"Hoping that self-employment through gig economy can solve their financial woes, a hard-up UK delivery driver and his wife struggling to raise a family end up trapped in the vicious circle of this modern-day form of labour exploitation.");
        Movie movie9=new Movie(" 10. Belzebuth "," Not Rated | 114 min | Horror "," 2020 ",(R.drawable.image10),"After losing his family in an extremely tragic way, Detective Ritter must investigate a massacre at a school perpetrated by a student. What seemed like a pretty clear case becomes much");

        movieList.add(movie0);
        movieList.add(movie1);
        movieList.add(movie2);
        movieList.add(movie3);
        movieList.add(movie4);
        movieList.add(movie5);
        movieList.add(movie6);
        movieList.add(movie7);
        movieList.add(movie8);
        movieList.add(movie9);

        moviesAdapter.notifyDataSetChanged();
        // for alarm if we changed or modifier on data

    }
}
package Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.InfoActivity;
import com.example.recyclerview.R;

import java.util.List;

import Model.Listitem;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Listitem> listitems;

    public MyAdapter(Context context, List listitems) {
          this.context=context;
          this.listitems=listitems;
    }

    @NonNull

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Listitem item = listitems.get(position);

        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.age.setText(item.getAge());
    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }

    //Innerclass//////////
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private TextView description;
        private TextView age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            name=itemView.findViewById(R.id.titel);
            description=itemView.findViewById(R.id.myinfo);
            age=itemView.findViewById(R.id.age);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Listitem item = listitems.get(position);

            Intent intent = new Intent(context, InfoActivity.class);
            intent.putExtra("name",item.getName());
            intent.putExtra("description",item.getDescription());
            intent.putExtra("age",item.getAge());
            context.startActivity(intent);

            // we are write  context.startActivity(intent); because this is page or class is
            //not activity this is our class or this is controler so in controler we send
            // information with intent but we have to write (context) after that .

            //Toast.makeText(context,item.getName(),Toast.LENGTH_SHORT).show();

        }
    }
}

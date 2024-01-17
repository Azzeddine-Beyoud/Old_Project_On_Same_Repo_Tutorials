package com.example.project_final_ali.Controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_final_ali.HomeFragment;
import com.example.project_final_ali.MainActivity;
import com.example.project_final_ali.Module.Client;
import com.example.project_final_ali.OffLineFragment;
import com.example.project_final_ali.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterLocal extends RecyclerView.Adapter<AdapterLocal.MyViewHolder> {

    private Context context ;
    private List<Client> listOfClient;
    private DataBaseHandler dataBaseHandler;

    public AdapterLocal(Context context, List<Client> listOfClient) {
        this.context = context;
        this.listOfClient = listOfClient;
    }

    public AdapterLocal(Context context, List<Client> listOfClient, DataBaseHandler dataBaseHandler) {
        this.context = context;
        this.listOfClient = listOfClient;
        this.dataBaseHandler = dataBaseHandler;
    }

    @NonNull
    @Override
    public AdapterLocal.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLocal.MyViewHolder holder, int position) {
        Client client = listOfClient.get(position);

        holder.textViewName.setText(client.getNameClient());
        holder.textViewRefrence.setText(client.getReference());
        holder.textViewTimeStamp.setText(formatDate(client.getTimeStamp()));
        Picasso.get().load(client.getPictureUri())
                .fit()
                .centerCrop()
                .into(holder.commandImage);

        //holder.timestemp.setText(formatDate(client.getTimestemp));
    }

    @Override
    public int getItemCount() {
        return listOfClient.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewName , textViewRefrence, textViewTimeStamp;
        ImageView commandImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
              //itemView.setOnClickListener(this);
            textViewName = itemView.findViewById(R.id.nameView);
            textViewRefrence = itemView.findViewById(R.id.ReferenceView);
            textViewTimeStamp = itemView.findViewById(R.id.timestampId);
            commandImage = itemView.findViewById(R.id.imageView);

        }

        @Override
        public void onClick(View view) {
            int position =getAdapterPosition();
            Client client = listOfClient.get(position);

//            Intent intent = new Intent(context, HomeFragment.class);
//            intent.putExtra("name", client.getNameClient());
//            intent.putExtra("reference",client.getReference());
//            context.startActivity(intent);

        }
    }


    private String formatDate(String dateStr){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(dateStr);
            SimpleDateFormat sdfOut = new SimpleDateFormat("MMM d");
            return sdfOut.format(date);
        }
        catch (ParseException e){
            Log.d("error",e.getMessage());
        }
        return "";
    }

//    private void deleteData(int position ){
//        dataBaseHandler.deleteClient(listOfClient.get((position)));
//        listOfClient.remove(position);
//        OffLineFragment.notifyAdapter;
//    }
}

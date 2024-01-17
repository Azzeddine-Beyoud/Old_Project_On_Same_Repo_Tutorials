package com.example.fragmenttofragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.Nameholder> {
    ArrayList<Name> names;
    onItemClickListener listener;

    public NameAdapter(ArrayList<Name> names, onItemClickListener listener) {
        this.names = names;
        this.listener=listener;
    }

    @NonNull
    @Override
    public Nameholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_name,parent,false);

        return new Nameholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Nameholder holder, int position) {

        Name name = names.get(position);
        holder.bind(name);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class Nameholder extends RecyclerView.ViewHolder{

        TextView tv_name;
        Name name;

        public Nameholder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.custom_tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onnItemClick(name);
                }
            });
        }

        void bind(Name name){
            this.name = name;
            tv_name.setText(name.getName());
        }
    }
}



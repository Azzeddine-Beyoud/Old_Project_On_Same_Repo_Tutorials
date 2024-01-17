package com.example.fragtoact_create;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPerson extends RecyclerView.Adapter<AdapterPerson.MyViewholder> {
    ArrayList<Person> personS;
    onItemClickListener listener;

    public AdapterPerson(ArrayList<Person> personS, onItemClickListener listener) {
        this.personS = personS;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AdapterPerson.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_person,parent,false);

        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPerson.MyViewholder holder, int position) {
        Person person = personS.get(position);
        //holder.tv_namePerson.setText(person.getName());
        holder.bind(person);


    }

    @Override
    public int getItemCount() {
        return personS.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        TextView tv_namePerson;
        Person person;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tv_namePerson=itemView.findViewById(R.id.Tv_custom);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){

                    listener.onnItemClick(person);
                }
            });
        }
        void bind(Person person){
            this.person= person;
            tv_namePerson.setText(person.getName());
        }
    }
}

package com.example.project_final_ali.Controller;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.project_final_ali.Module.Client;
import com.example.project_final_ali.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;



public class AdapterClients extends FirestoreRecyclerAdapter<Client,AdapterClients.ClientHolder> {

    private OnItemClickListener listener;

    private ViewBinderHelper viewBinderHelper;

    String phone;

    public AdapterClients(@NonNull FirestoreRecyclerOptions<Client> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ClientHolder holder, int position, @NonNull Client model) {


        holder.textViewName.setText(model.getNameClient());
        holder.textViewRefrence.setText(String.valueOf(model.getReference()));
       // holder.textViewphone.setText(model.getPhone());
        Picasso.get().load(model.getPictureUri())
                .fit()
                .centerCrop()
                .into(holder.commandImage);
        //fit() for reasiase the image disgin imageView so we don't have
        // full size of the image phone memoire
    }

    @NonNull
    @Override
    public ClientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.client_item,parent,false);

        return new ClientHolder(view);
    }

    public  void deleteClient(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public void makeCall(View v ,int position){
        ClientHolder clientHolder=new ClientHolder(v);
        clientHolder.textViewphone.getText();
    }

    public void upDate(int position){
       // getSnapshots().getSnapshot(position).getReference().update(new Client());
    }

    public interface OnItemClickListener{

        void onItemClick(DocumentSnapshot documentSnapshot,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }



    public class ClientHolder extends RecyclerView.ViewHolder {

        TextView textViewName , textViewRefrence, textViewphone;
        ImageView commandImage;

        public ClientHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.nameView);
            textViewRefrence = itemView.findViewById(R.id.ReferenceView);
            commandImage = itemView.findViewById(R.id.imageView);
//            textViewPriority = itemView.findViewById(R.id.text_view_priority);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);

                    }
                }
            });
        }

    }






}

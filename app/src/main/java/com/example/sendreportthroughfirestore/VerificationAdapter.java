package com.example.sendreportthroughfirestore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VerificationAdapter extends RecyclerView.Adapter<VerificationAdapter.ViewHolder> {
    Context context;
    List<VerificationModel>verificationModelList;
    VerificationAdapter verificationAdapter;

    public VerificationAdapter(Context context, List<VerificationModel> verificationModelList) {
        this.context = context;
        this.verificationModelList = verificationModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View  view= LayoutInflater.from(parent.getContext()).inflate(R.layout.verification_row_desgin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VerificationModel verificationModel=verificationModelList.get(position);

        holder.t1.setText(verificationModel.getName());
        holder.t2.setText(verificationModel.getEmail());
        holder.t3.setText(verificationModel.getLicence());
        String imageUrl=null;
        imageUrl=verificationModel.getImage();
        Picasso.get().load(imageUrl).into(holder.imageView);


      holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {


              return true;
          }
      });

    }

    @Override
    public int getItemCount() {
        return verificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView t1,t2,t3;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.img);
            t1=itemView.findViewById(R.id.name);
            t2=itemView.findViewById(R.id.email);
            t3=itemView.findViewById(R.id.lice);
            cardView=itemView.findViewById(R.id.a1);
        }
    }
}

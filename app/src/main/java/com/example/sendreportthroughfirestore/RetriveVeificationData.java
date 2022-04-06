package com.example.sendreportthroughfirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class RetriveVeificationData extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    FirebaseStorage mStorage;
    DatabaseReference mRef;
    RecyclerView recyclerView;
    VerificationAdapter verificationAdapter;
    VerificationModel verificationModel;
    List<VerificationModel>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_veification_data);

        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference().child("Verfication");
        mStorage= FirebaseStorage.getInstance();

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<VerificationModel>();
        verificationAdapter=new VerificationAdapter(RetriveVeificationData.this,list);

        recyclerView.setAdapter(verificationAdapter);
//       mRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                list.clear();
//                if (snapshot.exists()) {
//                    for (DataSnapshot docSnap : snapshot.getChildren()) {
//                        verificationModel = docSnap.getValue(VerificationModel.class);
//                       list.add(verificationModel);
//                        admin_lawyersListAdapter = new Admin_LawyersListAdapter(AdminActivity.this, LawyersList);
//                        re.setAdapter(admin_lawyersListAdapter);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                VerificationModel verificationModel=snapshot.getValue(VerificationModel.class);
                list.add(verificationModel);
                verificationAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
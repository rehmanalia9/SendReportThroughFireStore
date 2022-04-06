package com.example.sendreportthroughfirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class LawyerVerificationForm extends AppCompatActivity {

    ImageButton imgbtn;
    EditText ed1,ed2,ed3;
    Button btn;
    FirebaseDatabase mDatabase;
   FirebaseStorage mStorage;
    DatabaseReference mRef;
    private  static  final int Gallery_Code=1;
    Uri imguri=null;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_verification_form);

        imgbtn=findViewById(R.id.imageButton);
        ed1=findViewById(R.id.name);
        ed2=findViewById(R.id.lawyer_licenceno);
        ed3=findViewById(R.id.lawyeremail);
        btn=findViewById(R.id.lawyer_submit);

        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference().child("Verfication");
        mStorage= FirebaseStorage.getInstance();
        progressDialog=new ProgressDialog(this);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
              intent.setType("image/*");
              startActivityForResult(intent,Gallery_Code);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==Gallery_Code && resultCode==RESULT_OK){
        imguri=data.getData();
        imgbtn.setImageURI(imguri);
        }
        btn.setOnClickListener(new View.OnClickListener() {
//            String name=ed1.getText().toString();
//            String licence=ed2.getText().toString();
//            String email=ed3.getText().toString();
            @Override
            public void onClick(View v) {
                if (imguri == null) {
                    Toast.makeText(LawyerVerificationForm.this, "Please Upload Bar Council ID Card", Toast.LENGTH_SHORT).show();
                }
                if (ed1.getText().toString().isEmpty()) {
                    Toast.makeText(LawyerVerificationForm.this, "Please put name", Toast.LENGTH_SHORT).show();

                }
                if (ed2.getText().toString().isEmpty()) {
                    Toast.makeText(LawyerVerificationForm.this, "Please put Licence", Toast.LENGTH_SHORT).show();

                }
                if (ed3.getText().toString().isEmpty()) {
                    Toast.makeText(LawyerVerificationForm.this, "Please put email", Toast.LENGTH_SHORT).show();
                }

                else {
                    String name = ed1.getText().toString();
                    String licence = ed2.getText().toString();
                    String email = ed3.getText().toString();

//          if (!(name.isEmpty() && licence.isEmpty()&& email.isEmpty()&&imguri!=null))
//
//                {


                    progressDialog.setTitle("Uploading");
                    progressDialog.show();

                    StorageReference filepath = mStorage.getReference().child("imagePost").child(imguri.getLastPathSegment());
                    filepath.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();
                                    DatabaseReference newPost = mRef.push();
                                    newPost.child("Name").setValue(name);
                                    newPost.child("Licence").setValue(licence);
                                    newPost.child("Email").setValue(email);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();

                                    Intent intent=new Intent(LawyerVerificationForm.this,RetriveVeificationData.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    });

                }

                // }
            }
        });

    }
}
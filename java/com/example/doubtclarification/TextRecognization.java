package com.example.doubtclarification;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.example.doubtclarification.HomePage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.io.IOException;
import java.util.List;

import static com.google.firebase.ml.vision.common.FirebaseVisionImage.fromFilePath;


public class TextRecognization extends AppCompatActivity {

    private Button b1,b2;
    private ImageView imageView1;
    private TextView textView;
    private String imageUrl;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_recognization);

        b1=findViewById(R.id.textre);
        b2=findViewById(R.id.search);

        textView=findViewById(R.id.text);
        imageUrl=getIntent().getStringExtra("image");
        setUrl(imageUrl);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detect();
                textView.setText("");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/kmpmpRcF94Q"));
                startActivity(b);
            }
        });



    }



    private void detect() {
        BitmapDrawable drawable = (BitmapDrawable) imageView1.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        FirebaseVisionImage firebaseVisionImage = FirebaseVisionImage.fromBitmap(bitmap);
        FirebaseVision firebaseVision = FirebaseVision.getInstance();
        FirebaseVisionTextRecognizer firebaseVisionTextRecognizer = firebaseVision.getOnDeviceTextRecognizer();
        Task<FirebaseVisionText> task = firebaseVisionTextRecognizer.processImage(firebaseVisionImage);
        task.addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
            @Override
            public void onSuccess(FirebaseVisionText firebaseVisionText) {
                String s = firebaseVisionText.getText();
                textView.setText(s);
            }

        });
        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setUrl (String imageUrl){
        imageView1 = findViewById(R.id.image);
        Glide.with(this).asBitmap().load(imageUrl).into(imageView1);
    }

}
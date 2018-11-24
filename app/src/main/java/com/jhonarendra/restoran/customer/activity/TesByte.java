package com.jhonarendra.restoran.customer.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jhonarendra.restoran.customer.R;
import com.jhonarendra.restoran.customer.model.Hidangan;
import com.jhonarendra.restoran.customer.storage.DatabaseHelper;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Jhonarendra on 11/22/2018.
 */

public class TesByte extends AppCompatActivity {
    Bitmap bitmap;
    ImageView ivTes, ivCopy;
    TextView tvTes;
    DatabaseHelper db;
    InputStream imgStream;
    private List<Hidangan> allHidanganList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes);

        ivTes = findViewById(R.id.iv_tes);
        ivCopy = findViewById(R.id.iv_tes_copy);
        tvTes = findViewById(R.id.tv_tes);

        db = new DatabaseHelper(this);

        allHidanganList = db.getAllHidangan();
        tvTes.setText("jml: "+allHidanganList.size()+"\n");


        for (int i=0;i<9;i++){
            String imgUrl = MainActivity.URL+"upload/"+allHidanganList.get(i).getFoto_hidangan();
            Glide.with(this).load(imgUrl).into(ivTes);

            ContextWrapper cw = new ContextWrapper(this);
            File directory = cw.getDir("Hidangan", Context.MODE_PRIVATE);
            File mypath=new File(directory, allHidanganList.get(i).getFoto_hidangan());

            bitmap = ((BitmapDrawable)ivTes.getDrawable()).getBitmap();

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(mypath);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            tvTes.append(allHidanganList.get(i).getFoto_hidangan().toString());
            String path = "/data/data/com.jhonarendra.restoran.customer/app_Hidangan";

            loadImageFromStorage(path, allHidanganList.get(i).getFoto_hidangan());
        }
    }
    private void loadImageFromStorage(String path, String nama)
    {

        try {
            File f=new File(path, nama);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ivCopy.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/com.jhonarendra.restoran.customer/app_imageDir
        File directory = cw.getDir("Hidangan", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"es_krim.png");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}

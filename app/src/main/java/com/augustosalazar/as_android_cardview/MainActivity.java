package com.augustosalazar.as_android_cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ViewAdapter.RecyclerClickListner {

    private ViewAdapter viewAdapter;
    private RecyclerView mRecyclerView;
    private Firebase myFirebaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        myFirebaseRef = new Firebase("https://uninortefirebasetest.firebaseio.com/");



        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);

        List<Information> data = new ArrayList<>();
        String[] titulo = getResources().getStringArray(R.array.data);


        for (int i = 0; i < titulo.length;i++){
            Information info = new Information(titulo[i]);
            data.add(info);
        }
        viewAdapter= new ViewAdapter(this,data);
        viewAdapter.setRecyclerClickListner(this);
        mRecyclerView.setAdapter(viewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void itemClick(View view, int position) {
        Log.d("Recyclerview", "Click position "+position);
    }

    public void onClickBotonBorrar(View view) {
        Log.d("Recyclerview", "Borrar Click position "+view.getTag());
    }

    public void onClickAgregarEntrada(View view) {
        Random rn = new Random();
        myFirebaseRef.child("message "+rn.nextInt(10 - 2 + 1) + 2).setValue(""+ rn.nextInt(10 - 2 + 1) + 2);
    }
}

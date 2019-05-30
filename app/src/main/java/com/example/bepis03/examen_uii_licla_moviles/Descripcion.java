package com.example.bepis03.examen_uii_licla_moviles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class Descripcion extends AppCompatActivity {

    TextView titulo , descripcion;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        titulo= (TextView) findViewById(R.id.titulo1);
        descripcion = (TextView) findViewById(R.id.descripcion1);
        imageView = (ImageView) findViewById(R.id.imagen1);

        String rtitulo = getIntent().getStringExtra("titulo");
        String rdescripcion = getIntent().getStringExtra("descripcion");
        String rimagen =  getIntent().getStringExtra("imagen");

        titulo.setText(rtitulo);
        descripcion.setText(rdescripcion);
        Glide.with(Descripcion.this).load(rimagen).into(imageView);
    }
}

package com.example.bepis03.examen_uii_licla_moviles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DataAdapter  extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    private ArrayList<String> mTitulo = new ArrayList<>();
    private ArrayList<String> mDescripcion = new ArrayList<>();
    private ArrayList<String> mImagen = new ArrayList<>();
    private Activity mActivity;
    private int lastPosition = -1;
    private Context context;



    public DataAdapter(MainActivity activity, ArrayList<String> mBlogTitleList, ArrayList<String> mAuthorNameList, ArrayList<String> mBlogUploadDateList) {
        this.mActivity = activity;
        this.mTitulo = mBlogTitleList;
        this.mDescripcion = mAuthorNameList;
        this.mImagen = mBlogUploadDateList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titulonoticia, descripcion;
        private ImageView imageView;
       private LinearLayout linearLayout;

        public MyViewHolder(View view) {
            super(view);
            titulonoticia= (TextView) view.findViewById(R.id.titulo);
            descripcion = (TextView) view.findViewById(R.id.descripcion);
            imageView = (ImageView) view.findViewById(R.id.imagen);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_data, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        holder.titulonoticia.setText(mTitulo.get(position));
       // holder.descripcion.setText(mDescripcion.get(position));
        Glide.with(mActivity)
                .load(mImagen.get(position))
                .into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = mTitulo.get(position);
                String descripcion = mDescripcion.get(position);
                String imagen = mImagen.get(position);

                Intent intent = new Intent(mActivity, Descripcion.class);
                intent.putExtra("titulo", titulo);
                intent.putExtra("descripcion", descripcion);
                intent.putExtra("imagen", imagen);
                mActivity.startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return mTitulo.size();
    }


    }

package com.example.bepis03.examen_uii_licla_moviles;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private String url = "http://www.upt.edu.pe/";
    private ArrayList<String> mTitulo = new ArrayList<>();
    private ArrayList<String> mDescripcion = new ArrayList<>();
    private ArrayList<String> mImagenL = new ArrayList<>();
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Description().execute();

    }

    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Web Scraping");
            mProgressDialog.setMessage("cargando");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document mBlogDocument = Jsoup.connect(url).get();
                // Using Elements to get the Meta data
                Elements mElementDataSize = mBlogDocument.select("div[class=col-md-12]");
                // Locate the content attributes
                int mElementSize = mElementDataSize.size();

                for (int i = 0; i < mElementSize; i++) {

                    Elements mElementAuthorName = mBlogDocument.select("li").select("center").select
                            ("strong").eq
                            (i);
                    String mAuthorName = mElementAuthorName.text();

                    Elements mElementBlogUploadDate = mBlogDocument.select("li").select("span").select
                            ("i[style=text-align:justify;]").eq
                            (i);
                    String mBlogUploadDate = mElementBlogUploadDate.text();

                    Elements mElementBlogTitle = mBlogDocument.select("li").select("em").select("img").eq(i);
                    String mImagen = "http://www.upt.edu.pe" + mElementBlogTitle.attr("src");

                    Log.d("img", "log");

                    mTitulo.add(mAuthorName);
                    mDescripcion.add(mBlogUploadDate);
                    mImagenL.add(mImagen);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView

            RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.act_recyclerview);

            DataAdapter mDataAdapter = new DataAdapter(MainActivity.this, mTitulo, mDescripcion, mImagenL);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mDataAdapter);

            mProgressDialog.dismiss();



        }
    }
}
package com.MessMantech.mahmood.algo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    long time;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_main );
        MobileAds.initialize( this, "ca-app-pub-4797728656934365~7099600281" );
        time = System.currentTimeMillis();
        mContext = this;
        new TimeTask().execute();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        System.exit( 0 );
    }

    class TimeTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            while (true) {
                if (System.currentTimeMillis() - time > 5000) {
                    return null;
                }
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute( aVoid );
            Intent i = new Intent( mContext, Main2Activity.class );
            startActivity( i );
        }
    }

}


package com.MessMantech.mahmood.algo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static java.lang.Thread.sleep;


public class Simulation_toh extends AppCompatActivity implements View.OnClickListener {
    AdView mAdView;
    TextView[] tvBox = new TextView[12];
    TextView[] tvLine = new TextView[5];
    Button btPause;
    Button btReset;
    int[] oldArray = {7, 4, 1, 9, 5, 2};
    boolean pause = false;
    boolean finished = false;
    boolean reset = false;
    Object lock;
    int size = 6;
    BubbleSortThread t;

    // TextView tv = new TextView(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_simulation_toh );
        mAdView = (AdView) findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd( adRequest );
        tvBox[0] = (TextView) findViewById( R.id.index1 );
        tvBox[1] = (TextView) findViewById( R.id.index2 );
        tvBox[2] = (TextView) findViewById( R.id.index3 );
        tvBox[3] = (TextView) findViewById( R.id.index4 );
        tvBox[4] = (TextView) findViewById( R.id.index5 );
        tvBox[5] = (TextView) findViewById( R.id.index6 );
        tvBox[6] = (TextView) findViewById( R.id.index7 );
        tvBox[7] = (TextView) findViewById( R.id.index8 );
        tvBox[8] = (TextView) findViewById( R.id.index9 );
        tvBox[9] = (TextView) findViewById( R.id.index10 );
        tvBox[10] = (TextView) findViewById( R.id.index11 );
        tvBox[11] = (TextView) findViewById( R.id.index12 );
        tvLine[0] = (TextView) findViewById( R.id.line1 );
        tvLine[1] = (TextView) findViewById( R.id.line2 );
        tvLine[2] = (TextView) findViewById( R.id.line3 );
        tvLine[3] = (TextView) findViewById( R.id.line4 );
        tvLine[4] = (TextView) findViewById( R.id.line5 );
        btPause = (Button) findViewById( R.id.btPause );
        btReset = (Button) findViewById( R.id.btReset );
        btPause.setText( "PAUSE" );
        btPause.setOnClickListener( this );
        btReset.setOnClickListener( this );
//        for (int i = 0; i < 6; i++){
//            tvBox[i].setText(""+array[i]);
//        }
        t = new BubbleSortThread();
        t.start();

    }

    int[] swap(int[] array, int i1, int i2) {
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
        return array;
    }

    void textboxTextUpdate(final int index, final int num) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvBox[index].setText( "" + num );
            }
        } );
    }

    void textboxColorUpdate(final int index, final int color) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvBox[index].setBackgroundColor( getResources().getColor( color ) );
            }
        } );
    }

    void textlineColorUpdate(final int index, final int color) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvLine[index].setBackgroundColor( getResources().getColor( color ) );
            }
        } );
    }

    void textViuSizeUpdate(final int index, final int wi, final int hi) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvBox[index].requestLayout();
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams( wi * 20, hi * 30 );
                p.setMargins( 10, 2, 2, 2 );
                tvBox[index].setLayoutParams( p );
            }
        } );
    }

    void waitsec(int t) {
        try {
            sleep( t * 1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void buttonTextUpdate(final int i) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                if (i == 0)
                    btPause.setText( "RESUME" );
                else
                    btPause.setText( "PAUSE" );
            }
        } );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btPause:
                pause = true;
                String str = btPause.getText().toString();
                if (str == "PAUSE") {
                    buttonTextUpdate( 0 );
                    t.pauseThread();
                    pause = true;

                }
                if (str == "RESUME") {
                    buttonTextUpdate( 1 );
                    t.resumeThread();
                }
                break;
            case R.id.btReset:
                reset = true;
            default:
                break;
        }
    }

    class BubbleSortThread extends Thread {

        BubbleSortThread() {
            lock = new Object();
            pause = false;
            finished = false;
        }

        @Override
        public void run() {
            while (!finished) {
                int[] array = new int[size];
                reset = false;
                finalPause();
                if (reset == true) continue;
                finalPause();
                int lastIndex = size - 1;
                textViuSizeUpdate( 3, 5, 1 );
                textViuSizeUpdate( 2, 4, 1 );
                textViuSizeUpdate( 1, 3, 1 );
                textViuSizeUpdate( 0, 1, 2 );
                textViuSizeUpdate( 4, 0, 0 );
                textViuSizeUpdate( 5, 0, 0 );
                textViuSizeUpdate( 6, 0, 0 );
                textViuSizeUpdate( 7, 1, 5 );
                textViuSizeUpdate( 8, 0, 0 );
                textViuSizeUpdate( 9, 0, 0 );
                textViuSizeUpdate( 10, 0, 0 );
                textViuSizeUpdate( 11, 1, 5 );
                for (int i = 0; i < 5; i++) {
                    textlineColorUpdate( i, R.color.colorW );
                }
                textlineColorUpdate( 0, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 0, R.color.colorW );
                textlineColorUpdate( 1, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 1, R.color.colorW );
                textlineColorUpdate( 3, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 3, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 3, R.color.colorW );
                textlineColorUpdate( 3, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 3, R.color.colorW );
                textlineColorUpdate( 3, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textViuSizeUpdate( 3, 5, 1 );
                textViuSizeUpdate( 2, 4, 1 );
                textViuSizeUpdate( 1, 3, 1 );
                textViuSizeUpdate( 0, 1, 2 );
                textViuSizeUpdate( 4, 0, 0 );
                textViuSizeUpdate( 5, 0, 0 );
                textViuSizeUpdate( 6, 0, 0 );
                textViuSizeUpdate( 7, 1, 5 );
                textViuSizeUpdate( 8, 0, 0 );
                textViuSizeUpdate( 9, 0, 0 );
                textViuSizeUpdate( 10, 0, 0 );
                textViuSizeUpdate( 11, 1, 5 );
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 3, R.color.colorW );
                textlineColorUpdate( 4, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                textViuSizeUpdate( 3, 5, 1 );
                textViuSizeUpdate( 2, 4, 1 );
                textViuSizeUpdate( 1, 1, 3 );
                textViuSizeUpdate( 0, 0, 0 );
                textViuSizeUpdate( 4, 0, 0 );
                textViuSizeUpdate( 5, 0, 0 );
                textViuSizeUpdate( 6, 0, 0 );
                textViuSizeUpdate( 7, 1, 5 );
                textViuSizeUpdate( 8, 0, 0 );
                textViuSizeUpdate( 9, 0, 0 );
                textViuSizeUpdate( 10, 1, 4 );
                textViuSizeUpdate( 11, 3, 1 );
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 4, R.color.colorW );
                textlineColorUpdate( 3, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textViuSizeUpdate( 3, 5, 1 );
                textViuSizeUpdate( 2, 1, 4 );
                textViuSizeUpdate( 1, 0, 0 );
                textViuSizeUpdate( 0, 0, 0 );
                textViuSizeUpdate( 4, 0, 0 );
                textViuSizeUpdate( 5, 0, 0 );
                textViuSizeUpdate( 6, 1, 4 );
                textViuSizeUpdate( 7, 4, 1 );
                textViuSizeUpdate( 8, 0, 0 );
                textViuSizeUpdate( 9, 0, 0 );
                textViuSizeUpdate( 10, 1, 4 );
                textViuSizeUpdate( 11, 3, 1 );
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 3, R.color.colorW );
                textlineColorUpdate( 4, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textViuSizeUpdate( 3, 5, 1 );
                textViuSizeUpdate( 2, 1, 4 );
                textViuSizeUpdate( 1, 0, 0 );
                textViuSizeUpdate( 0, 0, 0 );
                textViuSizeUpdate( 4, 0, 0 );
                textViuSizeUpdate( 5, 1, 3 );
                textViuSizeUpdate( 6, 3, 1 );
                textViuSizeUpdate( 7, 4, 1 );
                textViuSizeUpdate( 8, 0, 0 );
                textViuSizeUpdate( 9, 0, 0 );
                textViuSizeUpdate( 10, 0, 0 );
                textViuSizeUpdate( 11, 1, 5 );
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 4, R.color.colorW );
                textlineColorUpdate( 3, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                textViuSizeUpdate( 3, 1, 5 );
                textViuSizeUpdate( 2, 0, 0 );
                textViuSizeUpdate( 1, 0, 0 );
                textViuSizeUpdate( 0, 0, 0 );
                textViuSizeUpdate( 4, 0, 0 );
                textViuSizeUpdate( 5, 1, 3 );
                textViuSizeUpdate( 6, 3, 1 );
                textViuSizeUpdate( 7, 4, 1 );
                textViuSizeUpdate( 8, 0, 0 );
                textViuSizeUpdate( 9, 0, 0 );
                textViuSizeUpdate( 10, 1, 4 );
                textViuSizeUpdate( 11, 5, 1 );
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 3, R.color.colorW );
                textlineColorUpdate( 4, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                textViuSizeUpdate( 3, 3, 1 );
                textViuSizeUpdate( 2, 1, 4 );
                textViuSizeUpdate( 1, 0, 0 );
                textViuSizeUpdate( 0, 0, 0 );
                textViuSizeUpdate( 4, 0, 0 );
                textViuSizeUpdate( 5, 0, 0 );
                textViuSizeUpdate( 6, 1, 4 );
                textViuSizeUpdate( 7, 4, 1 );
                textViuSizeUpdate( 8, 0, 0 );
                textViuSizeUpdate( 9, 0, 0 );
                textViuSizeUpdate( 10, 1, 4 );
                textViuSizeUpdate( 11, 5, 1 );
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 4, R.color.colorW );
                textlineColorUpdate( 3, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textViuSizeUpdate( 3, 3, 1 );
                textViuSizeUpdate( 2, 1, 4 );
                textViuSizeUpdate( 1, 0, 0 );
                textViuSizeUpdate( 0, 0, 0 );
                textViuSizeUpdate( 4, 0, 0 );
                textViuSizeUpdate( 5, 0, 0 );
                textViuSizeUpdate( 6, 0, 0 );
                textViuSizeUpdate( 7, 1, 5 );
                textViuSizeUpdate( 8, 0, 0 );
                textViuSizeUpdate( 9, 1, 3 );
                textViuSizeUpdate( 10, 4, 1 );
                textViuSizeUpdate( 11, 5, 1 );
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 3, R.color.colorW );
                textlineColorUpdate( 4, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textViuSizeUpdate( 3, 1, 5 );
                textViuSizeUpdate( 2, 0, 0 );
                textViuSizeUpdate( 1, 0, 0 );
                textViuSizeUpdate( 0, 0, 0 );
                textViuSizeUpdate( 4, 0, 0 );
                textViuSizeUpdate( 5, 0, 0 );
                textViuSizeUpdate( 6, 0, 0 );
                textViuSizeUpdate( 7, 1, 5 );
                textViuSizeUpdate( 8, 1, 2 );
                textViuSizeUpdate( 9, 3, 1 );
                textViuSizeUpdate( 10, 4, 1 );
                textViuSizeUpdate( 11, 5, 1 );
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 4, R.color.colorW );
                if (reset == true) continue;
                finalPause();
                finished = true;
            }
        }

        void finalPause() {
            synchronized (lock) {
                while (pause) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        void pauseThread() {
            synchronized (lock) {
                pause = true;
            }
        }

        void resumeThread() {
            synchronized (lock) {
                pause = false;
                lock.notifyAll();
            }
        }

    }

}

package com.MessMantech.mahmood.algo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static java.lang.Thread.sleep;


public class Simulation_Dijkstra extends AppCompatActivity implements View.OnClickListener {
    AdView mAdView;
    TextView[] tvBox = new TextView[9];
    TextView[] tvViu = new TextView[6];
    TextView[] tvLine = new TextView[8];
    ImageView[] imgv = new ImageView[15];
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
        setContentView( R.layout.activity_simulation__dijkstra );
        mAdView = (AdView) findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd( adRequest );
        imgv[0] = (ImageView) findViewById( R.id.img1 );
        imgv[1] = (ImageView) findViewById( R.id.img2 );
        imgv[2] = (ImageView) findViewById( R.id.img3 );
        imgv[3] = (ImageView) findViewById( R.id.img4 );
        imgv[4] = (ImageView) findViewById( R.id.img5 );
        imgv[5] = (ImageView) findViewById( R.id.img6 );
        imgv[6] = (ImageView) findViewById( R.id.img7 );
        imgv[7] = (ImageView) findViewById( R.id.img8 );
        imgv[8] = (ImageView) findViewById( R.id.img9 );
        imgv[9] = (ImageView) findViewById( R.id.img10 );
        imgv[10] = (ImageView) findViewById( R.id.img11 );
        imgv[11] = (ImageView) findViewById( R.id.img12 );
        imgv[12] = (ImageView) findViewById( R.id.img13 );
        imgv[13] = (ImageView) findViewById( R.id.img14 );
        imgv[14] = (ImageView) findViewById( R.id.img15 );
        tvBox[0] = (TextView) findViewById( R.id.tv1 );
        tvBox[1] = (TextView) findViewById( R.id.tv2 );
        tvBox[2] = (TextView) findViewById( R.id.tv3 );
        tvBox[3] = (TextView) findViewById( R.id.tv4 );
        tvBox[4] = (TextView) findViewById( R.id.tv5 );
        tvBox[5] = (TextView) findViewById( R.id.tv6 );
        tvBox[6] = (TextView) findViewById( R.id.tv7 );
        tvBox[7] = (TextView) findViewById( R.id.tv8 );
        tvBox[8] = (TextView) findViewById( R.id.tv9 );
        tvLine[0] = (TextView) findViewById( R.id.line1 );
        tvLine[1] = (TextView) findViewById( R.id.line2 );
        tvLine[2] = (TextView) findViewById( R.id.line3 );
        tvLine[3] = (TextView) findViewById( R.id.line4 );
        tvLine[4] = (TextView) findViewById( R.id.line5 );
        tvLine[5] = (TextView) findViewById( R.id.line6 );
        tvLine[6] = (TextView) findViewById( R.id.line7 );
        tvLine[7] = (TextView) findViewById( R.id.line8 );
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

    void imgUpdate(final int index, final int img) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                imgv[index].setImageDrawable( getResources().getDrawable( img ) );

            }
        } );
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

    void textViuSizeUpdate(final int index, final int size) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvViu[index].requestLayout();
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams( 42, size * 20 );
                p.setMargins( 10, 2, 2, 2 );
                tvViu[index].setLayoutParams( p );
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
                int lastIndex = size - 1;

                imgUpdate( 0, R.drawable.a );
                imgUpdate( 1, R.drawable.leftblack );
                imgUpdate( 2, R.drawable.rightblack );
                imgUpdate( 3, R.drawable.b );
                imgUpdate( 4, R.drawable.c );
                imgUpdate( 5, R.drawable.leftblack );
                imgUpdate( 6, R.drawable.rightblack );
                imgUpdate( 7, R.drawable.leftblack );
                imgUpdate( 8, R.drawable.rightblack );
                imgUpdate( 9, R.drawable.e );
                imgUpdate( 10, R.drawable.f );
                imgUpdate( 11, R.drawable.p );
                imgUpdate( 12, R.drawable.n );
                textboxTextUpdate( 0, 2 );
                textboxTextUpdate( 1, 4 );
                textboxTextUpdate( 2, 6 );
                textboxTextUpdate( 3, 3 );
                textboxTextUpdate( 4, 2 );
                textboxTextUpdate( 2, 3 );
                textboxTextUpdate( 6, 9 );
                textboxTextUpdate( 7, 1 );
                textboxTextUpdate( 8, 7 );
                waitsec( 1 );
                imgUpdate( 0, R.drawable.af );
                waitsec( 2 );
                imgUpdate( 3, R.drawable.bf );
                waitsec( 1 );
                imgUpdate( 9, R.drawable.ef );
                waitsec( 2 );
                imgUpdate( 10, R.drawable.ff );
                waitsec( 1 );
                imgUpdate( 4, R.drawable.cf );
                waitsec( 1 );
                imgUpdate( 11, R.drawable.pf );
                waitsec( 1 );
                imgUpdate( 12, R.drawable.nf );
                waitsec( 1 );


                if (reset == true) continue;
                textlineColorUpdate( 7, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                waitsec( 1 );
                textlineColorUpdate( 7, R.color.colorW );//finalPause();if (reset == true) continue;
//                finished = true;
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

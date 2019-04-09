package com.MessMantech.mahmood.algo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static com.MessMantech.mahmood.algo.R.color.colorPrimary;
import static java.lang.Thread.sleep;


public class Simulation_linear extends AppCompatActivity implements View.OnClickListener {
    AdView mAdView;
    TextView[] tvBox = new TextView[6];
    TextView[] tvEq = new TextView[6];
    TextView[] tvViu = new TextView[6];
    TextView[] tvLine = new TextView[6];
    Button btPause;
    Button btReset;
    int[] oldarray = {7, 4, 1, 9, 5, 2};
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
        setContentView( R.layout.activity_simulation_linear );
        mAdView = (AdView) findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd( adRequest );
        tvBox[0] = (TextView) findViewById( R.id.index1 );
        tvBox[1] = (TextView) findViewById( R.id.index2 );
        tvBox[2] = (TextView) findViewById( R.id.index3 );
        tvBox[3] = (TextView) findViewById( R.id.index4 );
        tvBox[4] = (TextView) findViewById( R.id.index5 );
        tvBox[5] = (TextView) findViewById( R.id.index6 );
        tvViu[0] = (TextView) findViewById( R.id.viu1 );
        tvViu[1] = (TextView) findViewById( R.id.viu2 );
        tvViu[2] = (TextView) findViewById( R.id.viu3 );
        tvViu[3] = (TextView) findViewById( R.id.viu4 );
        tvViu[4] = (TextView) findViewById( R.id.viu5 );
        tvViu[5] = (TextView) findViewById( R.id.viu6 );
        tvEq[0] = (TextView) findViewById( R.id.eq1 );
        tvEq[1] = (TextView) findViewById( R.id.eq2 );
        tvEq[2] = (TextView) findViewById( R.id.eq3 );
        tvEq[3] = (TextView) findViewById( R.id.eq4 );
        tvEq[4] = (TextView) findViewById( R.id.eq5 );
        tvEq[5] = (TextView) findViewById( R.id.eq6 );
        tvLine[0] = (TextView) findViewById( R.id.line1 );
        tvLine[1] = (TextView) findViewById( R.id.line2 );
        tvLine[2] = (TextView) findViewById( R.id.line3 );
        tvLine[3] = (TextView) findViewById( R.id.line4 );
        tvLine[4] = (TextView) findViewById( R.id.line5 );
        tvLine[5] = (TextView) findViewById( R.id.line6 );
        btPause = (Button) findViewById( R.id.btPause );
        btReset = (Button) findViewById( R.id.btReset );
        btPause.setText( "PAUSE" );
        btPause.setOnClickListener( this );
        btReset.setOnClickListener( this );
        t = new BubbleSortThread();
        t.start();

    }

    void swap(int[] array, int i1, int i2) {
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    void textboxTextUpdate(final int index, final int num) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvBox[index].setText( "" + num );
            }
        } );
    }

    void keyboxColortUpdate(final int index, final int num) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvViu[index].setTextColor( getResources().getColor( num ) );
            }
        } );
    }

    void textboxEqualUpdate(final int index, final int num) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvEq[index].setTextColor( getResources().getColor( num ) );
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

    //    void textViuSizeUpdate(final int index, final int size){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                tvViu[index].requestLayout();
//                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(42, size * 20);
//                p.setMargins(10, 2, 2, 2);
//                tvViu[index].setLayoutParams(p);
//            }
//        });
//    }
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
                finalPause();
                reset = false;
                int lastIndex = size - 1;
                int[] array = new int[size];
                for (int i = 0; i < size; i++) {
                    array[i] = oldarray[i];
                    textboxEqualUpdate( i, R.color.colorW );
                    keyboxColortUpdate( i, R.color.colorW );
                    textboxTextUpdate( i, array[i] );
                    textboxColorUpdate( i, colorPrimary );
//                    textViuSizeUpdate(i, array[i]);
                }
                if (reset == true) continue;
                for (int i = 0; i < 6; i++) {
                    textlineColorUpdate( i, R.color.colorW );
                }
                textlineColorUpdate( 0, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 0, R.color.colorW ); //finalPause();

                int key = 5;
                if (reset == true) continue;
                finalPause();

                for (int i = 0; i < size; i++) {
                    textlineColorUpdate( 1, R.color.colorAccent );
                    if (reset == true) break;
                    finalPause();
                    waitsec( 1 );
                    if (reset == true) break;
                    finalPause();
                    textlineColorUpdate( 1, R.color.colorW );
                    if (reset == true) break;
                    finalPause();

                    textlineColorUpdate( 2, R.color.colorAccent );
                    if (reset == true) break;
                    finalPause();
                    textboxColorUpdate( i, R.color.colorAccent );
                    if (reset == true) break;
                    finalPause();
                    keyboxColortUpdate( i, R.color.colorPrimary );
                    if (reset == true) break;
                    finalPause();
                    waitsec( 1 );
                    if (reset == true) break;
                    finalPause();
                    textboxEqualUpdate( i, R.color.colorPrimary );
                    if (reset == true) break;
                    finalPause();
                    waitsec( 1 );
                    if (reset == true) break;
                    finalPause();
                    textlineColorUpdate( 2, R.color.colorW );
                    if (reset == true) break;
                    finalPause();
                    if (array[i] == key) {
                        textlineColorUpdate( 3, R.color.colorAccent );
                        if (reset == true) break;
                        finalPause();
                        waitsec( 1 );
                        if (reset == true) break;
                        finalPause();
                        textlineColorUpdate( 3, R.color.colorW );
                        if (reset == true) break;
                        finalPause();
                        break;
                    }
                    textboxEqualUpdate( i, R.color.colorW );
                    if (reset == true) break;
                    finalPause();
                    textlineColorUpdate( 4, R.color.colorAccent );
                    if (reset == true) break;
                    finalPause();
                    keyboxColortUpdate( i, R.color.colorW );
                    if (reset == true) break;
                    finalPause();
                    waitsec( 1 );
                    if (reset == true) break;
                    finalPause();
                    textlineColorUpdate( 4, R.color.colorW );
                    if (reset == true) break;
                    finalPause();
                    textboxColorUpdate( i, colorPrimary );
                    if (reset == true) break;
                    finalPause();
                    waitsec( 1 );
                    if (reset == true) break;
                    finalPause();
                }

                textlineColorUpdate( 5, R.color.colorAccent );
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 5, R.color.colorW );
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

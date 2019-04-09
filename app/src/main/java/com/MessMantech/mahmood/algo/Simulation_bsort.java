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


public class Simulation_bsort extends AppCompatActivity implements View.OnClickListener {
    AdView mAdView;
    TextView[] tvBox = new TextView[6];
    TextView[] tvViu = new TextView[6];
    TextView[] tvLine = new TextView[8];
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
        setContentView( R.layout.activity_simulation_bsort );
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
                for (int i = 0; i < size; i++) {
                    array[i] = oldArray[i];
                    textboxTextUpdate( i, array[i] );
                    textboxColorUpdate( i, R.color.colorB );
                    textViuSizeUpdate( i, array[i] );
                }
                for (int i = 0; i < 8; i++) {
                    textlineColorUpdate( 0, R.color.colorW );
                }
                waitsec( 1 );
                textlineColorUpdate( 0, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                waitsec( 1 );
                textlineColorUpdate( 0, R.color.colorW ); //finalPause();
                if (reset == true) continue;
                for (int i = 0; i < size && reset == false; i++) {
                    textlineColorUpdate( 1, R.color.colorAccent );
                    finalPause();
                    if (reset == true) continue;
                    waitsec( 1 );
                    textlineColorUpdate( 1, R.color.colorW ); //finalPause();
                    if (reset == true) continue;
                    for (int j = 0; j < size - i - 1 && reset == false; j++) {
                        textlineColorUpdate( 2, R.color.colorAccent );
                        finalPause();
                        if (reset == true) continue;
                        waitsec( 1 );
                        textlineColorUpdate( 2, R.color.colorW ); //finalPause();
                        if (reset == true) continue;
                        textlineColorUpdate( 3, R.color.colorAccent );
                        finalPause();
                        if (reset == true) continue;
                        textboxColorUpdate( j, R.color.colorAccent );
                        finalPause();
                        if (reset == true) continue;
                        textboxColorUpdate( j + 1, R.color.colorAccent );
                        finalPause();
                        if (reset == true) continue;
                        waitsec( 1 );
                        textlineColorUpdate( 3, R.color.colorW );//finalPause();if (reset == true) continue;
                        if (array[j] > array[j + 1]) {
                            textlineColorUpdate( 4, R.color.colorAccent );
                            finalPause();
                            if (reset == true) continue;
                            array = swap( array, j, j + 1 );
                            textboxTextUpdate( j, array[j] );
                            finalPause();
                            if (reset == true) continue;
                            textboxTextUpdate( j + 1, array[j + 1] );
                            finalPause();
                            if (reset == true) continue;
                            textViuSizeUpdate( j, array[j] );
                            textViuSizeUpdate( j + 1, array[j + 1] );
                            finalPause();
                            if (reset == true) continue;
                            waitsec( 1 );
                        }
                        textlineColorUpdate( 4, R.color.colorW );//finalPause();if (reset == true) continue;
                        textlineColorUpdate( 5, R.color.colorAccent );
                        finalPause();
                        if (reset == true) continue;
                        textboxColorUpdate( j, R.color.colorB );
                        finalPause();
                        if (reset == true) continue;
                        textboxColorUpdate( j + 1, R.color.colorB );
                        finalPause();
                        if (reset == true) continue;
                        waitsec( 1 );
                        textlineColorUpdate( 5, R.color.colorW );//finalPause();if (reset == true) continue;

                    }
                    if (reset == true) continue;
                    textboxColorUpdate( lastIndex, R.color.colorPrimary );
                    finalPause();
                    if (reset == true) continue;
                    lastIndex--;
                    textlineColorUpdate( 6, R.color.colorAccent );
                    finalPause();
                    if (reset == true) continue;
                    waitsec( 1 );
                    textlineColorUpdate( 6, R.color.colorW );//finalPause();if (reset == true) continue;
                }
                if (reset == true) continue;
                textlineColorUpdate( 7, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                waitsec( 1 );
                textlineColorUpdate( 7, R.color.colorW );//finalPause();if (reset == true) continue;
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

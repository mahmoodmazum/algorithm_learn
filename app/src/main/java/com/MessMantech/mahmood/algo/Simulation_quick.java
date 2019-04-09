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


public class Simulation_quick extends AppCompatActivity implements View.OnClickListener {
    AdView mAdView;
    TextView[] tvBox = new TextView[6];
    TextView[] tvViu = new TextView[6];
    TextView[] tvLine = new TextView[17];
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
        setContentView( R.layout.activity_simulation_quick );
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
        tvLine[8] = (TextView) findViewById( R.id.line9 );
        tvLine[9] = (TextView) findViewById( R.id.line10 );
        tvLine[10] = (TextView) findViewById( R.id.line11 );
        tvLine[11] = (TextView) findViewById( R.id.line12 );
        tvLine[12] = (TextView) findViewById( R.id.line13 );
        tvLine[13] = (TextView) findViewById( R.id.line14 );
        tvLine[14] = (TextView) findViewById( R.id.line15 );
        tvLine[15] = (TextView) findViewById( R.id.line16 );
        tvLine[16] = (TextView) findViewById( R.id.line17 );
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
                finalPause();
                reset = false;
                int lastIndex = size - 1;
                int[] array = new int[size];
                for (int i = 0; i < size; i++) {
                    array[i] = oldarray[i];
                    textlineColorUpdate( i, R.color.colorW );
                    textboxTextUpdate( i, array[i] );
                    textboxColorUpdate( i, R.color.colorPrimary );
                    textViuSizeUpdate( i, array[i] );
                }
                if (reset == true) continue;
                for (int i = 0; i < 17; i++) {
                    textlineColorUpdate( i, R.color.colorW );
                }
                textlineColorUpdate( 0, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                waitsec( 1 );
                if (reset == true) continue;
                textlineColorUpdate( 0, R.color.colorW ); //finalPause();
                if (reset == true) continue;
                quickSort( array, 0, size - 1 );
                if (reset == true) continue;
                finished = true;
                if (reset == true) continue;
            }
        }

        void quickSort(int[] array, int p, int r) {

            textlineColorUpdate( 1, R.color.colorAccent );
            finalPause();
            if (reset == true) return;
            waitsec( 1 );
            if (reset == true) return;
            textlineColorUpdate( 1, R.color.colorW );
            if (reset == true) return;
            if (p < r) {
                if (reset == true) return;
                int q = partition( array, p, r );
                if (reset == true) return;
                textboxColorUpdate( q, R.color.colorPrimary );
                finalPause();
                if (reset == true) return;
                textlineColorUpdate( 2, R.color.colorAccent );
                finalPause();
                if (reset == true) return;
                waitsec( 1 );
                if (reset == true) return;
                textlineColorUpdate( 2, R.color.colorW );
                if (reset == true) return;

                quickSort( array, p, q - 1 );
                if (reset == true) return;
                textlineColorUpdate( 3, R.color.colorAccent );
                finalPause();
                if (reset == true) return;
                waitsec( 1 );
                if (reset == true) return;
                textlineColorUpdate( 3, R.color.colorW );
                if (reset == true) return;

                quickSort( array, q + 1, r );
                if (reset == true) return;
                textlineColorUpdate( 4, R.color.colorAccent );
                finalPause();
                if (reset == true) return;
                waitsec( 1 );
                if (reset == true) return;
                textlineColorUpdate( 4, R.color.colorW );
                if (reset == true) return;

            }
            textlineColorUpdate( 5, R.color.colorAccent );
            finalPause();
            if (reset == true) return;
            waitsec( 1 );
            if (reset == true) return;
            textlineColorUpdate( 5, R.color.colorW );
            if (reset == true) return;
            if (reset == true) return;
        }

        int partition(int[] array, int p, int r) {
            textlineColorUpdate( 6, R.color.colorAccent );
            finalPause();
            waitsec( 1 );
            if (reset == true) return 0;
            textlineColorUpdate( 6, R.color.colorW );
            if (reset == true) return 0;

            int x = array[r];
            if (reset == true) return 0;
            textlineColorUpdate( 7, R.color.colorAccent );
            finalPause();
            if (reset == true) return 0;
            textboxColorUpdate( r, R.color.colorAccent );
            finalPause();
            if (reset == true) return 0;
            waitsec( 1 );
            if (reset == true) return 0;
            textlineColorUpdate( 7, R.color.colorW );
            if (reset == true) return 0;

            int i = p - 1;
            if (reset == true) return 0;
            textlineColorUpdate( 8, R.color.colorAccent );
            finalPause();
            if (reset == true) return 0;
            if (i >= 0)
                textboxColorUpdate( i, R.color.colorAccent );
            finalPause();
            if (reset == true) return 0;
            waitsec( 1 );
            if (reset == true) return 0;
            textlineColorUpdate( 8, R.color.colorW );
            if (reset == true) return 0;

            for (int j = p; j < r; j++) {
                if (reset == true) return 0;
                textlineColorUpdate( 9, R.color.colorAccent );
                finalPause();
                if (reset == true) return 0;
                textboxColorUpdate( j, R.color.colorAccent );
                finalPause();
                if (reset == true) return 0;
                waitsec( 1 );
                if (reset == true) return 0;
                textlineColorUpdate( 9, R.color.colorW );
                if (reset == true) return 0;

                textlineColorUpdate( 10, R.color.colorAccent );
                finalPause();
                if (reset == true) return 0;
                waitsec( 1 );
                if (reset == true) return 0;
                textlineColorUpdate( 10, R.color.colorW );
                if (reset == true) return 0;
                if (array[j] < x) {
                    if (reset == true) return 0;

                    if (i >= 0 && i != p - 1)
                        textboxColorUpdate( i, R.color.colorPrimary );
                    finalPause();
                    waitsec( 1 );
                    if (reset == true) return 0;

                    i++;
                    if (reset == true) return 0;

                    textboxColorUpdate( i, R.color.colorAccent );
                    finalPause();
                    if (reset == true) return 0;
                    textlineColorUpdate( 11, R.color.colorAccent );
                    finalPause();
                    waitsec( 1 );
                    if (reset == true) return 0;
                    textlineColorUpdate( 11, R.color.colorW );
                    if (reset == true) return 0;

                    swap( array, i, j );
                    if (reset == true) return 0;
                    textlineColorUpdate( 12, R.color.colorAccent );
                    finalPause();
                    if (reset == true) return 0;
                    textboxTextUpdate( i, array[i] );
                    finalPause();
                    if (reset == true) return 0;
                    textViuSizeUpdate( i, array[i] );
                    finalPause();
                    if (reset == true) return 0;
                    textboxTextUpdate( j, array[j] );
                    finalPause();
                    if (reset == true) return 0;
                    textViuSizeUpdate( j, array[j] );
                    finalPause();
                    if (reset == true) return 0;
                    waitsec( 1 );
                    if (reset == true) return 0;
                    textlineColorUpdate( 12, R.color.colorW );
                    if (reset == true) return 0;
                }
                textlineColorUpdate( 13, R.color.colorAccent );
                finalPause();
                if (reset == true) return 0;
                waitsec( 1 );
                if (reset == true) return 0;
                textlineColorUpdate( 13, R.color.colorW );
                if (reset == true) return 0;

                if (i >= 0)
                    textboxColorUpdate( i, R.color.colorPrimary );
                finalPause();
                if (reset == true) return 0;
                textboxColorUpdate( j, R.color.colorPrimary );
                finalPause();
                if (reset == true) return 0;
            }
            textlineColorUpdate( 15, R.color.colorAccent );
            finalPause();
            if (reset == true) return 0;
            waitsec( 1 );
            if (reset == true) return 0;
            textlineColorUpdate( 15, R.color.colorW );
            if (reset == true) return 0;

            textboxColorUpdate( i + 1, R.color.colorAccent );
            finalPause();
            if (reset == true) return 0;
            waitsec( 1 );
            if (reset == true) return 0;
            swap( array, i + 1, r );
            if (reset == true) return 0;
            textlineColorUpdate( 14, R.color.colorAccent );
            finalPause();
            if (reset == true) return 0;
            textboxTextUpdate( i + 1, array[i + 1] );
            finalPause();
            if (reset == true) return 0;
            textViuSizeUpdate( i + 1, array[i + 1] );
            finalPause();
            if (reset == true) return 0;
            textboxTextUpdate( r, array[r] );
            finalPause();
            if (reset == true) return 0;
            textViuSizeUpdate( r, array[r] );
            finalPause();
            if (reset == true) return 0;
            waitsec( 1 );
            if (reset == true) return 0;
            textlineColorUpdate( 14, R.color.colorW );
            if (reset == true) return 0;


            textboxColorUpdate( i + 1, R.color.colorPrimary );
            finalPause();
            if (reset == true) return 0;
            textlineColorUpdate( 16, R.color.colorAccent );
            finalPause();
            if (reset == true) return 0;
            waitsec( 1 );
            if (reset == true) return 0;
            textlineColorUpdate( 16, R.color.colorW );
            if (reset == true) return 0;

            textboxColorUpdate( r, R.color.colorPrimary );
            finalPause();
            if (reset == true) return 0;
            return i + 1;

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

package com.MessMantech.mahmood.algo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import static java.lang.Thread.sleep;


public class Simulation_merge extends AppCompatActivity implements View.OnClickListener {
    AdView mAdView;
    TextView[][] tvBox = new TextView[4][7];
    TextView[] tvLine = new TextView[25];
    Button btPause;
    Button btReset;
    int[] oldarray = {3, 5, 2, 1};
    boolean pause = false;
    boolean finished = false;
    boolean reset = false;
    Object lock;
    int size = 4;
    BubbleSortThread t;

    // TextView tv = new TextView(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_simulation_merge );
        mAdView = (AdView) findViewById( R.id.adView );
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd( adRequest );
        tvBox[0][0] = (TextView) findViewById( R.id.index1 );
        tvBox[0][1] = (TextView) findViewById( R.id.index2 );
        tvBox[0][2] = (TextView) findViewById( R.id.index3 );
        tvBox[0][3] = (TextView) findViewById( R.id.index4 );
        tvBox[0][4] = (TextView) findViewById( R.id.index5 );
        tvBox[0][5] = (TextView) findViewById( R.id.index6 );
        tvBox[0][6] = (TextView) findViewById( R.id.index7 );
        tvBox[1][0] = (TextView) findViewById( R.id.index8 );
        tvBox[1][1] = (TextView) findViewById( R.id.index9 );
        tvBox[1][2] = (TextView) findViewById( R.id.index10 );
        tvBox[1][3] = (TextView) findViewById( R.id.index11 );
        tvBox[1][4] = (TextView) findViewById( R.id.index12 );
        tvBox[1][5] = (TextView) findViewById( R.id.index13 );
        tvBox[1][6] = (TextView) findViewById( R.id.index14 );
        tvBox[2][0] = (TextView) findViewById( R.id.index15 );
        tvBox[2][1] = (TextView) findViewById( R.id.index16 );
        tvBox[2][2] = (TextView) findViewById( R.id.index17 );
        tvBox[2][3] = (TextView) findViewById( R.id.index18 );
        tvBox[2][4] = (TextView) findViewById( R.id.index19 );
        tvBox[2][5] = (TextView) findViewById( R.id.index20 );
        tvBox[2][6] = (TextView) findViewById( R.id.index21 );
        tvBox[3][0] = (TextView) findViewById( R.id.index22 );
        tvBox[3][1] = (TextView) findViewById( R.id.index23 );
        tvBox[3][2] = (TextView) findViewById( R.id.index24 );
        tvBox[3][3] = (TextView) findViewById( R.id.index25 );
        tvBox[3][4] = (TextView) findViewById( R.id.index26 );
        tvBox[3][5] = (TextView) findViewById( R.id.index27 );
        tvBox[3][6] = (TextView) findViewById( R.id.index28 );
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
        tvLine[17] = (TextView) findViewById( R.id.line18 );
        tvLine[18] = (TextView) findViewById( R.id.line19 );
        tvLine[19] = (TextView) findViewById( R.id.line20 );
        tvLine[20] = (TextView) findViewById( R.id.line21 );
        tvLine[21] = (TextView) findViewById( R.id.line22 );
        tvLine[22] = (TextView) findViewById( R.id.line23 );
        tvLine[23] = (TextView) findViewById( R.id.line24 );
        tvLine[24] = (TextView) findViewById( R.id.line25 );
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

    void textboxTextUpdate(final int row, final int col, final int num) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvBox[row][col].setText( "" + num );
            }
        } );
    }

    void textboxColorUpdate(final int row, final int col, final int color) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                tvBox[row][col].setBackgroundColor( getResources().getColor( color ) );
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
                textlineColorUpdate( 0, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 0, R.color.colorW ); //finalPause();
                if (reset == true) continue;
                mergeSort( array, 0, size - 1 );
                if (reset == true) continue;
                finalPause();
                if (reset == true) continue;
                textlineColorUpdate( 5, R.color.colorAccent );
                if (reset == true) continue;
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 5, R.color.colorW );
                if (reset == true) continue;
                finalPause();
                finished = true;
                if (reset == true) continue;
                finalPause();
            }
        }

        void mergeSort(int[] array, int p, int r) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    textboxColorUpdate( i, j, R.color.colorW );
                }
            }
            for (int i = 0; i < array.length; i++) {
                array[i] = oldarray[i];
                textboxColorUpdate( 3, i + 2, R.color.colorPrimary );
                if (reset == true) return;
                textboxTextUpdate( 3, i + 2, array[i] );
                if (reset == true) return;
            }
            for (int i = 0; i < 25; i++) {
                textlineColorUpdate( i, R.color.colorW );
            }
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 1, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 1, R.color.colorW );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 2, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 2, R.color.colorW );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 3, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();

            textboxColorUpdate( 3, 2, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 3, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 2, R.color.colorW );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 3, R.color.colorW );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 1, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 2, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            textboxTextUpdate( 2, 1, array[0] );
            if (reset == true) return;
            finalPause();
            textboxTextUpdate( 2, 2, array[1] );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 1, R.color.colorPrimary );
            if (reset == true) return;
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 2, R.color.colorPrimary );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 3, R.color.colorW );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 4, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 4, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 5, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 4, R.color.colorW );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 5, R.color.colorW );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 4, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 5, R.color.colorAccent );
            if (reset == true) return;
            finalPause();
            textboxTextUpdate( 2, 4, array[2] );
            if (reset == true) return;
            finalPause();
            textboxTextUpdate( 2, 5, array[3] );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 4, R.color.colorPrimary );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 5, R.color.colorPrimary );
            if (reset == true) return;
            finalPause();
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 4, R.color.colorW );


            textlineColorUpdate( 1, R.color.colorAccent );
            waitsec( 1 );
            textlineColorUpdate( 1, R.color.colorW );
            textlineColorUpdate( 2, R.color.colorAccent );
            waitsec( 1 );
            textlineColorUpdate( 2, R.color.colorW );
            textlineColorUpdate( 3, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();

            textboxColorUpdate( 2, 1, R.color.colorAccent );
            textboxColorUpdate( 2, 2, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 1, R.color.colorW );
            textboxColorUpdate( 2, 2, R.color.colorW );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 1, 0, R.color.colorAccent );
            textboxTextUpdate( 1, 0, array[0] );

            textlineColorUpdate( 3, R.color.colorW );
            textlineColorUpdate( 4, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();

            textboxColorUpdate( 1, 2, R.color.colorAccent );
            textboxTextUpdate( 1, 2, array[1] );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 1, 0, R.color.colorPrimary );
            textboxColorUpdate( 1, 2, R.color.colorPrimary );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 4, R.color.colorW );


            textlineColorUpdate( 1, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 1, R.color.colorW );
            textlineColorUpdate( 2, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 2, R.color.colorW );
            textlineColorUpdate( 4, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();

            textboxColorUpdate( 2, 4, R.color.colorAccent );
            textboxColorUpdate( 2, 5, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 4, R.color.colorW );
            textboxColorUpdate( 2, 5, R.color.colorW );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 1, 4, R.color.colorAccent );
            textboxTextUpdate( 1, 4, array[2] );

            textlineColorUpdate( 3, R.color.colorW );
            textlineColorUpdate( 4, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();

            textboxColorUpdate( 1, 6, R.color.colorAccent );
            textboxTextUpdate( 1, 6, array[3] );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 1, 4, R.color.colorPrimary );
            textboxColorUpdate( 1, 6, R.color.colorPrimary );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();

            textlineColorUpdate( 4, R.color.colorW );
            ////////////////////////////////
            textlineColorUpdate( 5, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 5, R.color.colorW );
            textlineColorUpdate( 7, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 7, R.color.colorW );
            textlineColorUpdate( 8, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 8, R.color.colorW );
            textlineColorUpdate( 9, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 9, R.color.colorW );
            textlineColorUpdate( 10, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 10, R.color.colorW );
            textlineColorUpdate( 11, R.color.colorW );
            textlineColorUpdate( 12, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 12, R.color.colorW );
            textlineColorUpdate( 13, R.color.colorAccent );
            textlineColorUpdate( 13, R.color.colorW );
            textlineColorUpdate( 14, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 14, R.color.colorW );
            textlineColorUpdate( 15, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 15, R.color.colorW );
            textlineColorUpdate( 16, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 16, R.color.colorW );
            textlineColorUpdate( 17, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 17, R.color.colorW );


            textlineColorUpdate( 18, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 18, R.color.colorW );
            textlineColorUpdate( 19, R.color.colorAccent );
            textboxColorUpdate( 1, 0, R.color.colorAccent );
            textboxColorUpdate( 1, 2, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 1, 0, R.color.colorB );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 1, 0, R.color.colorW );
            textboxColorUpdate( 2, 1, R.color.colorB );
            textboxTextUpdate( 2, 1, array[0] );
            textlineColorUpdate( 19, R.color.colorW );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 20, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 20, R.color.colorW );

            textlineColorUpdate( 21, R.color.colorAccent );
            textboxColorUpdate( 1, 2, R.color.colorB );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 1, 2, R.color.colorW );
            textboxColorUpdate( 2, 2, R.color.colorB );
            textboxTextUpdate( 2, 2, array[1] );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 21, R.color.colorW );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 22, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 22, R.color.colorW );
            textboxColorUpdate( 2, 1, R.color.colorPrimary );
            textboxColorUpdate( 2, 2, R.color.colorPrimary );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();

            textlineColorUpdate( 6, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 6, R.color.colorW );
            textlineColorUpdate( 5, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 5, R.color.colorW );
            textlineColorUpdate( 7, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 7, R.color.colorW );
            textlineColorUpdate( 8, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 8, R.color.colorW );
            textlineColorUpdate( 9, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 9, R.color.colorW );
            textlineColorUpdate( 10, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 10, R.color.colorW );
            textlineColorUpdate( 11, R.color.colorW );
            textlineColorUpdate( 12, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 12, R.color.colorW );
            textlineColorUpdate( 13, R.color.colorAccent );
            textlineColorUpdate( 13, R.color.colorW );
            textlineColorUpdate( 14, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 14, R.color.colorW );
            textlineColorUpdate( 15, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 15, R.color.colorW );
            textlineColorUpdate( 16, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 16, R.color.colorW );
            textlineColorUpdate( 17, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 17, R.color.colorW );

            textlineColorUpdate( 18, R.color.colorAccent );
            textboxColorUpdate( 1, 4, R.color.colorAccent );
            textboxColorUpdate( 1, 6, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 1, 6, R.color.colorB );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 1, 6, R.color.colorW );
            textboxColorUpdate( 2, 4, R.color.colorB );
            textboxTextUpdate( 2, 4, array[3] );
            textlineColorUpdate( 18, R.color.colorW );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 19, R.color.colorAccent );
            textboxColorUpdate( 1, 4, R.color.colorB );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 19, R.color.colorW );
            textlineColorUpdate( 20, R.color.colorAccent );
            textboxColorUpdate( 1, 4, R.color.colorW );
            textboxColorUpdate( 2, 5, R.color.colorB );
            textboxTextUpdate( 2, 5, array[2] );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 20, R.color.colorW );
            textboxColorUpdate( 2, 4, R.color.colorPrimary );
            textboxColorUpdate( 2, 5, R.color.colorPrimary );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();


            textlineColorUpdate( 6, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 6, R.color.colorW );
            textlineColorUpdate( 5, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 5, R.color.colorW );
            textlineColorUpdate( 7, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 7, R.color.colorW );
            textlineColorUpdate( 8, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 8, R.color.colorW );
            textlineColorUpdate( 9, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 9, R.color.colorW );
            textlineColorUpdate( 10, R.color.colorAccent );
            waitsec( 1 );
            textlineColorUpdate( 10, R.color.colorW );
            textlineColorUpdate( 11, R.color.colorW );
            textlineColorUpdate( 12, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 12, R.color.colorW );
            textlineColorUpdate( 13, R.color.colorAccent );
            textlineColorUpdate( 13, R.color.colorW );
            textlineColorUpdate( 14, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 14, R.color.colorW );
            textlineColorUpdate( 15, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 15, R.color.colorW );
            textlineColorUpdate( 16, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 16, R.color.colorW );
            textlineColorUpdate( 17, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 17, R.color.colorW );

            textlineColorUpdate( 18, R.color.colorAccent );
            textboxColorUpdate( 2, 1, R.color.colorAccent );
            textboxColorUpdate( 2, 4, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 4, R.color.colorB );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 4, R.color.colorW );
            textboxColorUpdate( 3, 2, R.color.colorB );
            textboxTextUpdate( 3, 2, array[3] );
            textlineColorUpdate( 18, R.color.colorW );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 19, R.color.colorAccent );
            textboxColorUpdate( 3, 2, R.color.colorPrimary );
            textboxColorUpdate( 2, 1, R.color.colorPrimary );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 19, R.color.colorW );
            textlineColorUpdate( 20, R.color.colorAccent );
            textboxColorUpdate( 2, 1, R.color.colorAccent );
            textboxColorUpdate( 2, 5, R.color.colorAccent );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 5, R.color.colorB );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 5, R.color.colorW );
            textboxColorUpdate( 3, 3, R.color.colorB );
            textboxTextUpdate( 3, 3, array[2] );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 3, R.color.colorPrimary );
            textboxColorUpdate( 2, 1, R.color.colorPrimary );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textlineColorUpdate( 20, R.color.colorW );
            textboxColorUpdate( 2, 1, R.color.colorB );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 1, R.color.colorW );
            textboxColorUpdate( 3, 4, R.color.colorB );
            textboxTextUpdate( 3, 4, array[0] );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 4, R.color.colorPrimary );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 2, R.color.colorB );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 2, 2, R.color.colorW );
            textboxColorUpdate( 3, 5, R.color.colorB );
            textboxTextUpdate( 3, 5, array[1] );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
            textboxColorUpdate( 3, 5, R.color.colorPrimary );
            waitsec( 1 );
            if (reset == true) return;
            finalPause();
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

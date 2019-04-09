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


public class Simulation_Heap_sort extends AppCompatActivity implements View.OnClickListener {
    AdView mAdView;
    TextView[] tvBox = new TextView[6];
    TextView[] tvViu = new TextView[6];
    TextView[] tvLine = new TextView[20];
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
        setContentView( R.layout.activity_simulation__heap_sort );
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
        tvLine[17] = (TextView) findViewById( R.id.line18 );
        tvLine[18] = (TextView) findViewById( R.id.line19 );
        tvLine[19] = (TextView) findViewById( R.id.line20 );
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
                finalPause();
                int lastIndex = size - 1;
                finalPause();
                for (int i = 0; i < size; i++) {
                    array[i] = oldArray[i];
                    finalPause();
                    textboxTextUpdate( i, array[i] );
                    finalPause();
                    textboxColorUpdate( i, R.color.colorB );
                    finalPause();
                    textViuSizeUpdate( i, array[i] );
                    finalPause();
                }
                if (reset == true) continue;
                finalPause();
                for (int i = 0; i < 8; i++) {
                    textlineColorUpdate( i, R.color.colorW );
                    if (reset == true) continue;
                    finalPause();
                }
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 0, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                finalPause();
                textlineColorUpdate( 0, R.color.colorW );
                finalPause();
                if (reset == true) continue;
                finalPause();
                array = heapSort( array, array.length );
                if (reset == true) continue;
                finalPause();

                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 7, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 7, R.color.colorW );//finalPause();if (reset == true) continue;
                finished = true;
                finalPause();
            }
        }

        int[] heapSort(int[] array, int len) {
            int l = array.length;
            if (reset == true) return null;
            finalPause();
            int heapSize = l;
            if (reset == true) return null;
            finalPause();
            array = buildMaxHeap( array );
            if (reset == true) return null;
            finalPause();
            for (int i = l - 1; i > 0; i--) {

                textlineColorUpdate( 3, R.color.colorW );
                finalPause();
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                textlineColorUpdate( 3, R.color.colorW );
                finalPause();
                if (reset == true) continue;
                finalPause();

                if (reset == true) return null;
                finalPause();
                swap( array, 0, i );
                if (reset == true) return null;
                finalPause();
                textlineColorUpdate( 4, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                finalPause();
                textboxColorUpdate( 0, R.color.colorAccent );
                if (reset == true) return null;
                finalPause();
                textboxColorUpdate( i, R.color.colorAccent );
                if (reset == true) return null;
                finalPause();
                waitsec( 1 );
                if (reset == true) return null;
                finalPause();
                textboxTextUpdate( 0, array[0] );
                if (reset == true) return null;
                finalPause();
                textboxTextUpdate( i, array[i] );
                if (reset == true) return null;
                finalPause();
                textViuSizeUpdate( 0, array[0] );
                if (reset == true) return null;
                finalPause();
                textViuSizeUpdate( i, array[i] );
                if (reset == true) return null;
                finalPause();
                textlineColorUpdate( 4, R.color.colorW );
                finalPause();
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                if (reset == true) return null;
                finalPause();
                textboxColorUpdate( 0, R.color.colorPrimary );
                if (reset == true) return null;
                finalPause();
                textboxColorUpdate( i, R.color.colorPrimary );
                if (reset == true) return null;
                finalPause();
                waitsec( 1 );
                if (reset == true) return null;
                finalPause();
                textlineColorUpdate( 5, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                finalPause();
                heapSize--;
                if (reset == true) return null;
                finalPause();
                waitsec( 1 );
                textlineColorUpdate( 5, R.color.colorW );
                finalPause();
                if (reset == true) continue;
                finalPause();
                textlineColorUpdate( 6, R.color.colorW );
                finalPause();
                if (reset == true) continue;
                finalPause();
                array = maxHeapify( array, 0, heapSize );
                if (reset == true) return null;
                waitsec( 1 );
                textlineColorUpdate( 6, R.color.colorW );
                finalPause();
                if (reset == true) continue;
                finalPause();
            }
            if (reset == true) return null;
            finalPause();
            return array;
        }

        int[] buildMaxHeap(int[] array) {
            textlineColorUpdate( 7, R.color.colorW );
            finalPause();
            if (reset == true) return null;
            finalPause();
            waitsec( 1 );
            textlineColorUpdate( 7, R.color.colorW );
            finalPause();
            if (reset == true) return null;
            finalPause();
            if (reset == true) return null;
            finalPause();
            int l = array.length;
            if (reset == true) return null;
            finalPause();
            for (int i = l / 2; i >= 0; i--) {
                textlineColorUpdate( 8, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                finalPause();
                waitsec( 1 );
                textlineColorUpdate( 8, R.color.colorW );
                finalPause();
                if (reset == true) continue;
                finalPause();
                if (reset == true) return null;
                finalPause();
                textlineColorUpdate( 9, R.color.colorAccent );
                finalPause();
                if (reset == true) continue;
                finalPause();
                array = maxHeapify( array, i, l );
                if (reset == true) return null;
                finalPause();
                waitsec( 1 );
                textlineColorUpdate( 9, R.color.colorW );
                finalPause();
                if (reset == true) continue;
                finalPause();
            }
            if (reset == true) return null;
            finalPause();
            return array;
        }

        int[] maxHeapify(int[] array, int p, int heapSize) {

            textlineColorUpdate( 9, R.color.colorAccent );
            finalPause();
            if (reset == true) return null;
            finalPause();
            waitsec( 1 );
            textlineColorUpdate( 9, R.color.colorW );
            finalPause();
            if (reset == true) return null;
            finalPause();
            int largest;
            if (reset == true) return null;
            finalPause();
            textlineColorUpdate( 11, R.color.colorAccent );
            finalPause();
            if (reset == true) return null;
            finalPause();
            int l = p << 1;
            if (reset == true) return null;
            finalPause();
            waitsec( 1 );
            textlineColorUpdate( 11, R.color.colorW );
            finalPause();
            if (reset == true) return null;
            finalPause();
            textlineColorUpdate( 12, R.color.colorAccent );
            finalPause();
            if (reset == true) return null;
            finalPause();
            int r = (p << 1) + 1;
            if (reset == true) return null;
            finalPause();
            waitsec( 1 );
            textlineColorUpdate( 12, R.color.colorW );
            finalPause();
            if (reset == true) return null;
            finalPause();
            if (l < heapSize && array[l] > array[p])
                largest = l;
            else
                largest = p;
            if (reset == true) return null;
            if (r < heapSize && array[r] > array[p])
                largest = r;
            if (largest != p) {
                textlineColorUpdate( 17, R.color.colorAccent );
                finalPause();
                if (reset == true) return null;
                finalPause();
                waitsec( 1 );
                textlineColorUpdate( 17, R.color.colorW );
                finalPause();
                if (reset == true) return null;
                finalPause();
                textlineColorUpdate( 19, R.color.colorAccent );
                finalPause();
                if (reset == true) return null;
                finalPause();
                swap( array, largest, p );
                finalPause();
                textboxColorUpdate( largest, R.color.colorAccent );
                if (reset == true) return null;
                finalPause();
                textboxColorUpdate( p, R.color.colorAccent );
                if (reset == true) return null;
                finalPause();
                waitsec( 1 );
                if (reset == true) return null;
                finalPause();
                textboxTextUpdate( largest, array[largest] );
                if (reset == true) return null;
                finalPause();
                textboxTextUpdate( p, array[p] );
                if (reset == true) return null;
                finalPause();
                textViuSizeUpdate( largest, array[largest] );
                if (reset == true) return null;
                finalPause();
                textViuSizeUpdate( p, array[p] );
                if (reset == true) return null;
                finalPause();
                textlineColorUpdate( 19, R.color.colorW );
                finalPause();
                if (reset == true) return null;
                finalPause();
                waitsec( 1 );
                if (reset == true) return null;
                finalPause();
                textboxColorUpdate( largest, R.color.colorPrimary );
                if (reset == true) return null;
                finalPause();
                textboxColorUpdate( p, R.color.colorPrimary );
                if (reset == true) return null;
                finalPause();
                waitsec( 1 );
                if (reset == true) return null;
                finalPause();
                array = maxHeapify( array, largest, heapSize );
                if (reset == true) return null;
                finalPause();
            }
            if (reset == true) return null;
            finalPause();
            return array;
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

package com.MessMantech.mahmood.algo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void Bubble(View v) {
        Intent intentBubble = new Intent( this, Bubble_sort.class );
        startActivity( intentBubble );
    }

    public void Select(View v) {
        Intent intentSelect = new Intent( this, Selection_Sort.class );
        startActivity( intentSelect );
    }

    public void Insertion(View v) {
        Intent intent = new Intent( this, Insertion_Sort.class );
        startActivity( intent );

    }

    public void Heap(View v) {
        Intent intent = new Intent( this, Heap_sort.class );
        startActivity( intent );
    }

    public void Merge(View v) {
        Intent intent = new Intent( this, Merge_sort.class );
        startActivity( intent );
    }

    public void Quick(View v) {
        Intent intent = new Intent( this, Quick_sort.class );
        startActivity( intent );
    }

    public void Linear_Search(View v) {
        Intent intent = new Intent( this, Linear_search.class );
        startActivity( intent );

    }

    public void Binary_Search(View v) {
        Intent intent = new Intent( this, Binary_search.class );
        startActivity( intent );

    }

    public void BFS(View v) {
        Intent intent = new Intent( this, BFS.class );
        startActivity( intent );

    }

    public void DFS(View v) {
        Intent intent = new Intent( this, DFS.class );
        startActivity( intent );

    }

    public void Dikstra(View v) {
        Intent intent = new Intent( this, Dijkstra.class );
        startActivity( intent );
    }


    public void ToH(View v) {
        Intent intent = new Intent( this, TowerOfHanoy.class );
        startActivity( intent );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.optionmenu, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.blue:
                // Set the text color to blue
                Intent intent = new Intent( this, About.class );
                startActivity( intent );
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }


}

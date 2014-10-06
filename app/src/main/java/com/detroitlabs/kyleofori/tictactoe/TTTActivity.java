package com.detroitlabs.kyleofori.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;


public class TTTActivity extends Activity {
    private boolean xTurn = false;
    private char[][] gameBoard = new char[3][3];

    private void setUpOnClickListeners() {
        LinearLayout L = (LinearLayout) findViewById(R.id.linearLayout);
        for(int y = 0; y < L.getChildCount(); y++) {
            if(L.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) L.getChildAt(y);
                for(int x = 0; x < R.getChildCount(); x ++) {
                    View V = R.getChildAt(x); // In our case this will be each button on the grid
                    V.setOnClickListener(new PlayOnClick(x, y));
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ttt, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

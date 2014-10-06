package com.detroitlabs.kyleofori.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;


public class TTTActivity extends Activity {
    private boolean xTurn = false;
    private char[][] gameBoard = new char[3][3];


    private void setupOnClickListeners() {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        for(int y = 0; y < T.getChildCount(); y ++) {
            if(T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for(int x = 0; x < R.getChildCount(); x ++) {
                    View V = R.getChildAt(x); // In our case this will be each button on the grid
                    V.setOnClickListener(new PlayOnClick(x, y));
                }
            }
        }
    }


    private class PlayOnClick implements View.OnClickListener {

        private int x = 0;
        private int y = 0;

        public PlayOnClick(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View view) {
            if(view instanceof Button) {
                Button B = (Button) view;
                gameBoard[x][y] =  xTurn ? 'O' : 'X';
                B.setText(xTurn ? "O" : "X");
                B.setEnabled(false);
                xTurn = !xTurn;
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttt);
        setupOnClickListeners(); //Just put this in here, see if it works
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

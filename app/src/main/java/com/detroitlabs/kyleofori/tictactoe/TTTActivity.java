package com.detroitlabs.kyleofori.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

//heavily based on http://lyndonarmitage.com/making-tic-tac-toe-in-android/

public class TTTActivity extends Activity {
    private boolean xTurn = false;
    private char[][] gameBoard = new char[3][3];
    private Button mNewGameButton;
    private TextView mDisplayText;


    private void setupOnClickListeners() {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        for(int y = 0; y < T.getChildCount(); y++) {
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
            if (view instanceof Button) {
                Button B = (Button) view;
                gameBoard[x][y] = xTurn ? 'X' : 'O';
                B.setText(xTurn ? "X" : "O");
                B.setEnabled(false);
                xTurn = !xTurn;

                // check if anyone has won
                if (checkWin()) {
                    disableButtons(); //this used to say B.setEnabled(false);
                }
            }
        }
    }




    /**
     * Reset each button in the grid to be blank and enabled.
     */
    private void resetButtons() {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    if(R.getChildAt(x) instanceof Button) {
                        Button B = (Button) R.getChildAt(x);
                        B.setText("");
                        B.setEnabled(true);
                    }
                }
            }
        }
    }

    /**
     * Disable all buttons in the grid.
     */
    private void disableButtons() {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout);
        for (int y = 0; y < T.getChildCount(); y++) {
            if (T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++) {
                    if(R.getChildAt(x) instanceof Button) {
                        Button B = (Button) R.getChildAt(x);
                        B.setEnabled(false);
                    }
                }
            }
        }
    }

    /**
     * This is a generic algorithm for checking if a specific player has won on a tic tac toe board of any size.
     *
     * @param board  the board itself
     * @param size   the width and height of the board
     * @param player the player, 'X' or 'O'
     * @return true if the specified player has won
     */
    private boolean checkWinner(char[][] board, int size, char player) {
        // check each column
        for (int x = 0; x < size; x++) {
            int total = 0;
            for (int y = 0; y < size; y++) {
                if (board[x][y] == player) {
                    total++;
                }
            }
            if (total >= size) {
                return true; // they win
            }
        }

        // check each row
        for (int y = 0; y < size; y++) {
            int total = 0;
            for (int x = 0; x < size; x++) {
                if (board[x][y] == player) {
                    total++;
                }
            }
            if (total >= size) {
                return true; // they win
            }
        }

        // forward diag
        int total = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (x == y && board[x][y] == player) {
                    total++;
                }
            }
        }
        if (total >= size) {
            return true; // they win
        }

        // backward diag
        total = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (x + y == size - 1 && board[x][y] == player) {
                    total++;
                }
            }
        }
        if (total >= size) {
            return true; // they win
        }

        return false; // nobody won
    }


    /**
     * Method that returns true when someone has won and false when nobody has.
     * It also display the winner on screen.
     *
     * @return
     */
    private boolean checkWin() {

        char winner = '\0';
        if (checkWinner(gameBoard, 3, 'X')) {
            winner = 'X';
        } else if (checkWinner(gameBoard, 3, 'O')) {
            winner = 'O';
        }

        if (winner == '\0') {
            return false; // nobody won
        } else {
            // display winner
            mDisplayText.setText(winner + " wins");
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttt);
        setupOnClickListeners(); //Just put this in here, see if it works

        resetButtons();

        mDisplayText = (TextView) findViewById(R.id.titleText);

        mNewGameButton = (Button)findViewById(R.id.new_game);
        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) {
                newGame(view);
            }
        });
    }

    /**
     * Called when you press new game.
     * @param view the New Game Button
     */
    public void newGame(View view) {
        xTurn = false;
        gameBoard = new char[3][3];
        resetButtons();
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

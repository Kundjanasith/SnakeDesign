package com.ske.snakebaddesign.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ske.snakebaddesign.R;
import com.ske.snakebaddesign.guis.BoardView;
import com.ske.snakebaddesign.models.Game;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

//    private int boardSize;
//    private int p1Position;
//    private int p2Position;
    private int turn;
    private Game game;
    private BoardView boardView;
    private Button buttonTakeTurn;
    private Button buttonRestart;
    private TextView textPlayerTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        resetGame();
    }

    private void initComponents() {
        boardView = (BoardView) findViewById(R.id.board_view);
        buttonTakeTurn = (Button) findViewById(R.id.button_take_turn);
        buttonTakeTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeTurn();
            }
        });
        buttonRestart = (Button) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
        textPlayerTurn = (TextView) findViewById(R.id.text_player_turn);
    }

    private void resetGame() {
        turn = 0;
//        p1Position = 0;
//        p2Position = 0;
//        boardSize = 6;
        game = new Game();
        game.getBoard().setSize(3);
        boardView.setBoardSize(game.getBoard().getSize());
        boardView.setP1Position(game.getPlayer1().getPosition());
        boardView.setP2Position(game.getPlayer2().getPosition());
    }

    private void takeTurn() {
        final int value = 1 + new Random().nextInt(6);
        String title = "You rolled a die";
        String msg = "You got " + value;
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveCurrentPiece(value);
                dialog.dismiss();
            }
        };
        displayDialog(title, msg, listener);
    }

    private void moveCurrentPiece(int value) {
        if (turn % 2 == 0) {
//            p1Position = adjustPosition(p1Position, value);
//            boardView.setP1Position(p1Position);
            game.getPlayer1().setPosition(adjustPosition(game.getPlayer1().getPosition(),value));
            boardView.setP1Position(game.getPlayer1().getPosition());
            textPlayerTurn.setText("Player 2's Turn");
        } else {
//            p2Position = adjustPosition(p2Position, value);
//            boardView.setP2Position(p2Position);
            game.getPlayer2().setPosition(adjustPosition(game.getPlayer2().getPosition(),value));
            boardView.setP2Position(game.getPlayer2().getPosition());
            textPlayerTurn.setText("Player 1's Turn");
        }
        checkWin();
        turn++;
    }

    private int adjustPosition(int current, int distance) {
        current = current + distance;
        int maxSquare = game.getBoard().getSize() * game.getBoard().getSize() - 1;
        if(current > maxSquare) {
            current = maxSquare - (current - maxSquare);
        }
        return current;
    }

    private void checkWin() {
        String title = "Game Over";
        String msg = "";
        OnClickListener listener = new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
                dialog.dismiss();
            }
        };
        if (game.getPlayer1().getPosition() == game.getBoard().getSize() * game.getBoard().getSize() - 1) {
            msg = "Player 1 won!";
        } else if (game.getPlayer2().getPosition() == game.getBoard().getSize() * game.getBoard().getSize() - 1) {
            msg = "Player 2 won!";
        } else {
            return;
        }
        displayDialog(title, msg, listener);
    }

    private void displayDialog(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", listener);
        alertDialog.show();
    }

}

package com.suyog.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  // 0= blue,1=red
    int activeplayer= 0;
    int gameIsActive = 1;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
     int[][] wins={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        //System.out.println(counter.getTag().toString());


        int tappedcounter = Integer.parseInt(counter.getTag().toString());

        if (gamestate[tappedcounter] == 2 && gameIsActive==1) {

            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1000f);


            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;

            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winp :wins){
                if(gamestate[winp[0]]==gamestate[winp[1]] &&
                        gamestate[winp[1]]==gamestate[winp[2]] &&
                        gamestate[winp[0]] != 2){

                    String winner="Red";
                    if((gamestate[winp[0]])==0){

                        winner="Blue";
                    }
                    TextView winnerMessage =(TextView) findViewById(R.id.winnermessage);
                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout=(LinearLayout) findViewById(R.id.playagainlayout);

                    layout.setVisibility(View.VISIBLE);

                    gameIsActive=0;
                }else{
                    boolean gameIsover =true;

                    for(int cstate : gamestate){
                        if(cstate==2) gameIsover=false;
                    }

                    if(gameIsover){
                        TextView winnerMessage =(TextView) findViewById(R.id.winnermessage);
                        winnerMessage.setText("It's a Draw!");

                        LinearLayout layout=(LinearLayout) findViewById(R.id.playagainlayout);

                        layout.setVisibility(View.VISIBLE);


                    }
                }
            }
        }
    }

    public void playagain(View view){
        gameIsActive=1;
        LinearLayout layout=(LinearLayout) findViewById(R.id.playagainlayout);

        layout.setVisibility(View.INVISIBLE);

        activeplayer=0;

        for(int i = 0; i < gamestate.length;i++){

            gamestate[i] = 2;
        }

        GridLayout gridlayout =(GridLayout) findViewById(R.id.grid);

        for(int j=0; j < gridlayout.getChildCount(); j++){

            ((ImageView) gridlayout.getChildAt(j)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

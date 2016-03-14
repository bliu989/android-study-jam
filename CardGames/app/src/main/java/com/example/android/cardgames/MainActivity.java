package com.example.android.cardgames;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateSummaries();
    }

    int[][] gameInfo = {
            {R.drawable.capitalism, R.string.capitalism, R.string.capitalism_description},
            {R.drawable.mao, R.string.mao, R.string.mao_description},
            {R.drawable.hearts, R.string.hearts, R.string.hearts_description},
            {R.drawable.scotch_bridge, R.string.scotch_bridge, R.string.scotch_bridge_description},
            {R.drawable.rummy, R.string.rummy, R.string.rummy_description}};
    int numberOfGames = gameInfo.length;
    public void populateSummaries() {
        LayoutInflater inflater =
                (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);

        for (int i = 0; i < numberOfGames; i++) {
            // set up the layout for the summary
            View newSummary = inflater.inflate(R.layout.summary_layout, mainLayout, false);
            // put the appropriate image in the new view
            ImageView gameImage = (ImageView) newSummary.findViewById(R.id.game_image);
            gameImage.setImageResource(gameInfo[i][0]);
            gameImage.setId(i);
            // put the appropriate label on the button and give the button an id
            Button gameButton = (Button) newSummary.findViewById(R.id.game_button);
            gameButton.setText(gameInfo[i][1]);
            gameButton.setId(i + numberOfGames);
            // add the description of the game
            TextView gameDescription = (TextView) newSummary.findViewById(R.id.game_description);
            gameDescription.setText(getText(gameInfo[i][2]));
            // add the whole new view to the screen
            mainLayout.addView(newSummary);
        }
    }

    public void enterRules(View v) {
        int viewId = v.getId() % numberOfGames;
        Intent intent = new Intent(this, RulesActivity.class);
        intent.putExtra("viewId", viewId);
        intent.putExtra("game", gameInfo[viewId]);
        startActivity(intent);
    }
}

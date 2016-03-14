package com.example.android.cardgames;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_rules);

        // get variables passed through my MainActivity
        Bundle extras = getIntent().getExtras();
        int viewId = extras.getInt("viewId");
        int[] game = extras.getIntArray("game");

        setHeader(game);

        // adds a reference to the page to which the views will be added
        LinearLayout rulesLayout = (LinearLayout) findViewById(R.id.rules_layout);

        int[][] gameStrings = {
                {R.string.capitalism_short_summary, R.string.capitalism_setup,
                        R.string.capitalism_rules, R.string.capitalism_endgame,
                        R.string.capitalism_variations},
                {R.string.mao_short_summary, R.string.mao_setup,
                        R.string.mao_rules, R.string.mao_endgame,
                        R.string.mao_variations},
                {R.string.hearts_short_summary, R.string.hearts_setup,
                        R.string.hearts_rules, R.string.hearts_endgame,
                        R.string.hearts_variations},
                {R.string.scotch_bridge_short_summary, R.string.scotch_bridge_setup,
                        R.string.scotch_bridge_rules, R.string.scotch_bridge_endgame,
                        R.string.scotch_bridge_variations},
                {R.string.rummy_short_summary, R.string.rummy_setup,
                        R.string.rummy_rules, R.string.rummy_endgame,
                        R.string.rummy_variations}
        };
        int[] currentGameString = gameStrings[viewId];

        // writes a short summary
        TextView shortSummary = new TextView(this);
        shortSummary.setText(getText(currentGameString[0]));
        shortSummary.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        int padding = dpToPixels(12);
        shortSummary.setPadding(padding,padding,padding,padding);
        rulesLayout.addView(shortSummary);


        LayoutInflater inflater =
                (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // writes about the setup of the game
        View setupBox = inflater.inflate(R.layout.description_box, rulesLayout, false);

        TextView setup_header = (TextView) setupBox.findViewById(R.id.header_text);
        setup_header.setText(R.string.setup);

        TextView setup_content = (TextView) setupBox.findViewById(R.id.content);
        setup_content.setText(currentGameString[1]);

        rulesLayout.addView(setupBox);

        // writes about how the game is played
        View rulesBox = inflater.inflate(R.layout.description_box, rulesLayout, false);

        TextView rulesHeader = (TextView) rulesBox.findViewById(R.id.header_text);
        rulesHeader.setText(R.string.rules);

        TextView rulesContent = (TextView) rulesBox.findViewById(R.id.content);
        rulesContent.setText(currentGameString[2]);

        rulesLayout.addView(rulesBox);


        // writes about how the game ends
        View endgameBox = inflater.inflate(R.layout.description_box, rulesLayout, false);

        TextView endgameHeader = (TextView) endgameBox.findViewById(R.id.header_text);
        endgameHeader.setText(R.string.endgame);

        TextView endgameContent = (TextView) endgameBox.findViewById(R.id.content);
        endgameContent.setText(currentGameString[3]);

        rulesLayout.addView(endgameBox);

        // writes about variations of the game
        View variationsBox = inflater.inflate(R.layout.description_box, rulesLayout, false);

        TextView variations_header = (TextView) variationsBox.findViewById(R.id.header_text);
        variations_header.setText(R.string.variations);

        TextView variations_content = (TextView) variationsBox.findViewById(R.id.content);
        variations_content.setText(currentGameString[4]);

        rulesLayout.addView(variationsBox);
    }

    public void setHeader(int[] game) {
        LayoutInflater inflater =
                (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout rulesLayout = (LinearLayout) findViewById(R.id.rules_layout);

        View newSummary = inflater.inflate(R.layout.header_layout, rulesLayout, false);
        // put the appropriate image in the new view
        ImageView gameImage = (ImageView) newSummary.findViewById(R.id.game_image);
        gameImage.setImageResource(game[0]);
        // put the appropriate label on the button and give the button an id
        Button gameButton = (Button) newSummary.findViewById(R.id.game_button);
        gameButton.setText(game[1]);
        // add the whole new view to the screen
        rulesLayout.addView(newSummary);
    }

    public int dpToPixels(int dp){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);
    }
}

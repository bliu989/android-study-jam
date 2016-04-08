package com.example.android.cardgames;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    int[] currentGameString;
    int numberOfSections;

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
        currentGameString = gameStrings[viewId];

        // writes a short summary
        TextView shortSummary = (TextView) findViewById(R.id.short_summary);
        shortSummary.setText(getText(currentGameString[0]));

        LayoutInflater inflater =
                (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int[] sections = {
                R.string.setup,
                R.string.rules,
                R.string.endgame,
                R.string.variations,
        };

        numberOfSections = sections.length;
        for (int section = 0 ; section < numberOfSections ; section++) {
            View box = inflater.inflate(R.layout.description_box, rulesLayout, false);

            Button boxHeader = (Button) box.findViewById(R.id.header_text);
            boxHeader.setId(section);
            boxHeader.setText(sections[section]);

            TextView boxContent = (TextView) box.findViewById(R.id.content);
            boxContent.setId(section + numberOfSections);
           // boxContent.setText(currentGameString[section+1]);

            rulesLayout.addView(box);
        }
    }

    public void setHeader(int[] game) {
        LayoutInflater inflater =
                (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout rulesLayout = (LinearLayout) findViewById(R.id.game_graphic);

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

    public void expandCollapse(View v){
        TextView boxContent = (TextView) findViewById(v.getId()+numberOfSections);
        if (boxContent.getText() == "") {
            boxContent.setText(currentGameString[v.getId() + 1]);
        }
        else {
            boxContent.setText("");
        }
    }
}

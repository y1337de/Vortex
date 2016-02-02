package de.y1337.vortex;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import de.y1337.vortex.helpers.Coin;
import de.y1337.vortex.logic.CoinChangeAlgo;
import de.y1337.vortex.logic.CoinChangeAnswer;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectView(R.id.coinInputField)
    private EditText coinInputField;
    @InjectView(R.id.coinOutput)
    private TextView coinOutput;
    @InjectView(R.id.doCalculations)
    private Button calculate;
    @InjectView(R.id.bestSolution)
    private TextView bestSolution;
    @InjectView(R.id.coinsView)
    private DrawCoins drawCoins;
    @InjectView(R.id.toolbar)
    private Toolbar toolbar;
    private Context context;
    private CoinChangeAlgo coinChangeAlgo;
    private static final int[] COINS = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coinChangeAlgo = new CoinChangeAlgo(this);
        context = this;
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = coinInputField.getText().toString();
                if (value != null || !value.isEmpty()) {
                    int cents = (int) (Double.parseDouble(coinInputField.getText().toString()) * 100);
                    Log.e("TAG", "CENTS: " + cents);
                    //CoinChangeAnswer answers = coinChangeAlgo.findAllPossibleCombinations(cents, COINS);
                    //coinOutput.setText("The value: " + coinInputField.getText().toString() + " can be changed in " + answers.allPossibleChanges.size() + " ways.");
                    //Coinchange.start(cents);
                    CoinChangeAnswer answers = coinChangeAlgo.findOptimalChange(cents, COINS);
                    bestSolution.setText(answers.getOptimalChange());
                    for (Coin coin : answers.fetchCoins()) {
                        Log.e("TAG", "COIN: " + coin.getValue());
                    }
                    drawCoins.setCoinList(answers.fetchCoins());
                } else {
                    Toast.makeText(context, "Falsche Eingabe!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

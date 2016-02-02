package de.y1337.vortex.helpers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by julian on 28.01.16.
 */
public class CoinHelper {
    private String[][] coins;
    private List<Coin> coinList;
    int i;
    int j;

    public CoinHelper(String[][] coins) {
        this.coins = coins;
        i = coins.length;
        j = coins[0].length;
        this.coinList = new ArrayList<>();
    }

    public void generateListOfCoins() {
        String[] result = coins[i-1][j-1].split(" ");
        for (String resultFromBest : result) {
            Coin coin = Coin.getCoinByString(resultFromBest);
            coinList.add(coin);
        }
    }

    public List<Coin> getCoinResult() {
        return this.coinList;
    }

    public String generateAnswer() {
        StringBuilder builder = new StringBuilder();
        for (Coin coin : coinList) {
            builder.append(coin.getValue());
            builder.append(" ");
        }
        Log.e("TAG", builder.toString());
        return builder.toString();
    }
}

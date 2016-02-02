package de.y1337.vortex.helpers;

import android.graphics.drawable.Drawable;

import de.y1337.vortex.R;

/**
 * Created by julian on 28.01.16.
 */
public enum Coin {

    ONE_CENT("1 cent", "1", R.drawable.onecent, 200, 200),
    TWO_CENT("2 cent", "2", R.drawable.twocent, 200, 200),
    FIVE_CENT("5 cent", "5", R.drawable.fivecent, 200, 200),
    TEN_CENT("10 cent", "10", R.drawable.tencent, 200, 200),
    TWENTY_CENT("20 cent", "20", R.drawable.twentycent, 200, 200),
    FIFTY_CENT("50 cent", "50", R.drawable.fiftycent, 200, 200),
    ONE_EURO("1 €", "100", R.drawable.oneeuro, 200, 200),
    TWO_EURO("2 €", "200", R.drawable.twoeuro, 200, 200),
    FIVE_EURO("5 €", "500", R.drawable.fiveeuro, 200, 200),
    TEN_EURO("10 €", "1000", R.drawable.teneuro, 200, 200),
    TWENTY_EURO("20 €", "2000", R.drawable.twentyeuro, 200, 200),
    FIFTY_EURO("50 €", "5000", R.drawable.fiftyeuro, 200, 200),
    ONE_HUNDRED_EURO("100 €", "10000", R.drawable.onehundredeuro, 200, 200),
    TWO_HUNDRED_EURO("200 €", "20000", R.drawable.twohundredeuro, 200, 200),
    FIVE_HUNDRED_EURO("500 €", "50000", R.drawable.fivehundredeuro, 200, 200),
    ;
    private String value;
    private String valueInString;
    private int drawable;
    private int width;
    private int height;

    Coin(String value, String valueInString, int drawable, int width, int height) {
        this.value = value;
        this.valueInString = valueInString;
        this.drawable = drawable;
        this.width = width;
        this.height = height;
    }

    public String getValue() {
        return this.value;
    }

    private String getValueInString() {
        return this.valueInString;
    }

    public int getDrawable() {
        return this.drawable;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public static Coin getCoinByString(final String valueInString) {
        for (Coin coin : Coin.values()) {
            if (coin.getValueInString().equals(valueInString))
                return coin;
        }
        return null;
    }
}

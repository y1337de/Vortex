package de.y1337.vortex;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;
import java.util.Random;

import de.y1337.vortex.helpers.Coin;
import de.y1337.vortex.helpers.RandomUtils;

/**
 * Created by julian on 28.01.16.
 */
public class DrawCoins extends View {

    Paint paint = new Paint();
    List<Coin> coinList;

    public DrawCoins(Context context) {
        super(context);
        init(context);
    }

    public DrawCoins(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public DrawCoins(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Resources res = getResources();
        float percent = (float) 10 / 100;
        if (coinList != null) {
            for (Coin coin : coinList) {
                Random random = new Random();
                float randomX = RandomUtils.randomFloat(getWidth() / 2);        //
                float randomY = RandomUtils.randomFloat(getHeight() / 2);

                Bitmap bitmap = BitmapFactory.decodeResource(res, coin.getDrawable());
                int scaledWidth = (int) (bitmap.getWidth() * percent);
                int scaledHeight = (int) (bitmap.getHeight() * percent);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false);
                canvas.drawBitmap(scaledBitmap, randomX, randomY, paint);
            }
        }
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
        invalidate();
    }
}

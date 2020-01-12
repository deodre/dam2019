package eu.ase.ro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.Random;

public class PieChart extends View {

    private List<Integer> values;
    public PieChart(Context context, List<Integer> valori) {
        super(context);
        this.values = valori;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        Paint pieSlice = new Paint();
        Paint text = new Paint();

        int S = 0;
        for(int i = 0; i < values.size(); i++) {
            S+=values.get(i);
        }
        float A = 270;
        pieSlice.setColor(Color.rgb(255, 255, 0));
        canvas.drawArc(200, 200, 800, 800, A, (values.get(0)*360)/(float)S, true, pieSlice);
        canvas.drawRect(50, 850, 100, 900,pieSlice);
        text.setTextSize(40);
        text.setColor(Color.WHITE);
        canvas.drawText("1 STAR " + "(" + values.get(0) + ")", 150, 885, text);

        for(int i = 1; i < values.size(); i++) {
            pieSlice.setColor(Color.rgb(i*10*255%256, i*13*165%256, 0));
            A += (values.get(i-1)*360)/(float)S;
            canvas.drawArc(200, 200, 800, 800, A, (values.get(i)*360)/(float)S, true, pieSlice);
            canvas.drawRect(50, i*70+850, 100, i*70+900,pieSlice);
            canvas.drawText((i+1) + " STARS " + "(" + values.get(i) + ")", 150, i*70+885, text);
        }
    }
}
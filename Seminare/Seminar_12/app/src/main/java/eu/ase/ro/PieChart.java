package eu.ase.ro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.List;

public class PieChart extends View {

    private List<Integer> values;
    public PieChart(Context context, List<Integer> valori) {
        super(context);
        this.values = valori;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        Paint pensula = new Paint();
        pensula.setColor(Color.RED);
        pensula.setStrokeWidth(10);
        int S = 0;
        for(int i = 0; i < values.size(); i++) {
            S+=values.get(i);
            Log.e("Sum", "= "+S);
        }
        float A = 270;
        canvas.drawArc(200, 200, 800, 800, A, (values.get(0)*360)/(float)S, true, pensula);
        for(int i = 1; i < values.size(); i++) {
            pensula.setColor(Color.rgb(i*3*i+20, 2*i+56, 43*i*4));
            A += (values.get(i-1)*360)/(float)S;
            canvas.drawArc(200, 200, 800, 800, A, (values.get(i)*360)/(float)S, true, pensula);
        }
    }
}

package eu.ase.ro;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

public class Desenare extends View {

    public Desenare(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        Paint pensula = new Paint();
        pensula.setColor(Color.RED);
        pensula.setStrokeWidth(20);
        canvas.drawLine(10, 100, 500, 100, pensula);

        Paint instrument = new Paint();
        instrument.setColor(Color.BLUE);
        instrument.setStrokeWidth(15);
        instrument.setStyle(Paint.Style.STROKE);
        canvas.drawRect(10, 150, 500, 350, instrument);
        canvas.drawRect(50, 200, 150, 300, pensula);

        LinearGradient gradient = new LinearGradient(400, 400, 600, 600, Color.GREEN, Color.RED, Shader.TileMode.MIRROR);
        instrument.setShader(gradient);
        instrument.setStyle(Paint.Style.FILL);
        canvas.drawCircle(500, 500, 100, instrument);

        canvas.drawArc(100, 700, 800,1400, 270, 50,true, pensula);
        pensula.setStyle(Paint.Style.FILL);
        pensula.setColor(Color.rgb(400%256, 100%256, 600%256));
        canvas.drawArc(100, 700, 800,1400, 150, 120,false, pensula);
        pensula.setStyle(Paint.Style.STROKE);
        canvas.drawArc(100, 700, 800,1400, 30, 50,false, pensula);

    }
}

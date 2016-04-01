package jr.torc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.MotionEvent;

public class Panel extends View {
    public Panel(Context context) { super(context); }
    public Panel(Context context, AttributeSet attrs) { super(context, attrs); }
    public Panel(Context context, AttributeSet attrs, int defStyle) { super(context, attrs, defStyle); }

    public Panel(Context context, float f) {        //constructor for the panel taking in context and a number
        super(context);             //sets up the context
        this.f = f;                 //takes in the f value sent through by the main activity
    }

    float f = 300;      //setting a standard f level

    @Override
    protected void onDraw(Canvas canvas)
    {
        Paint paint = new Paint();      //setting up a new paint


        paint.setColor(Color.BLACK);        //setting the default colour to black
        canvas.drawRect(125, 125, 750, 800, paint);     //drawing rectangle for main body of mug

        paint.setColor(Color.BLACK);
        canvas.drawRect(750, 175, 875, 600, paint);     //drawing mug handle

        paint.setColor(Color.rgb(66,33,00));            //setting the colour to the background colour (brown)
        canvas.drawRect(750, 250, 825, 525, paint);     //drawing the inset to the mug handle

        paint.setColor(Color.WHITE);                    //sets the colour to be white to show the milk
        canvas.drawRect(150, 150, 725, f, paint);       //variable height adjust based on float f
    }

}

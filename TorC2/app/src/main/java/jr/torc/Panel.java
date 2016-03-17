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

    @Override
    protected void onDraw(Canvas canvas)
    {
        float f = 300;      //velue used to store amount of milk, dynamically changed by user

        /*@Override
        public boolean onTouchEvent(MotionEvent e){
            float y = e.getY();
            f = y;
        }
*/
        Paint paint = new Paint();      //setting up a new paint

        paint.setColor(Color.BLACK);        //setting the default colour to black
        canvas.drawRect(125, 125, 750, 800, paint);     //drawing rectangle for main body of mug

        paint.setColor(Color.BLACK);
        canvas.drawRect(750, 175, 875, 700, paint);     //drawing mug handle

        paint.setColor(Color.rgb(66,33,00));
        canvas.drawRect(750, 250, 825, 625, paint);     //drawing the inset to the mug handle

        paint.setColor(Color.WHITE);
        canvas.drawRect(150, 150, 725, f, paint);       //variable height adjust based on float f
    }
}

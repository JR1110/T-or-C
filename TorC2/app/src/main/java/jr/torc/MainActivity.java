package jr.torc;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout mug = (LinearLayout) findViewById(R.id.flPanel);
        mug.addView(new Panel(this), 0);      //adding the panel view to the linear layout flPanel

        final Button btnTea = (Button) findViewById(R.id.btnTea);             //variable for the tea button
        final Button btnCoffee = (Button) findViewById(R.id.btnCoffee);       //variable used for the coffee button
        Button btnNext = (Button) findViewById(R.id.btnNextOrder);      //variable for the next person button

        btnTea.setOnClickListener(new View.OnClickListener() {      //opens up new on click listener tea button
            @Override
            public void onClick(View v) {
                btnTea.setTextColor(Color.RED);      //sets the font colour of the button to red
                btnCoffee.setTextColor(Color.BLACK); //Sets the coffee button's colour back to normal
            }
        });

        btnCoffee.setOnClickListener(new View.OnClickListener(){        //opens up a new on click listener for the coffee button
            @Override
            public void onClick(View v){
                btnCoffee.setTextColor(Color.RED);       //changes the buttons colour
                btnTea.setTextColor(Color.BLACK);       //changes the tea buttons colour back to normal
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

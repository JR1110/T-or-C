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
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.concurrent.atomic.AtomicReference;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout mug = (LinearLayout) findViewById(R.id.flPanel);
        mug.addView(new Panel(this), 0);      //adding the panel view to the linear layout flPanel

        final order o = new order();      //setting up a new order

        final AtomicReference<Button> btnTea = new AtomicReference<>((Button) findViewById(R.id.btnTea));             //variable for the tea button
        final Button btnCoffee = (Button) findViewById(R.id.btnCoffee);       //variable used for the coffee button
        final Button btnNext = (Button) findViewById(R.id.btnNextOrder);      //variable for the next person button

        btnTea.get().setOnClickListener(new View.OnClickListener() {      //opens up new on click listener tea button
            @Override
            public void onClick(View v) {
                btnTea.get().setTextColor(Color.RED);      //sets the font colour of the button to red
                btnCoffee.setTextColor(Color.BLACK); //Sets the coffee button's colour back to normal
                btnCoffee.setSelected(false);           //sets the coffee selected to false
            }
        });

        btnCoffee.setOnClickListener(new View.OnClickListener(){        //opens up a new on click listener for the coffee button
            @Override
            public void onClick(View v){
                btnCoffee.setTextColor(Color.RED);       //changes the buttons colour
                btnCoffee.setSelected(true);          //makes the button selected
                btnTea.get().setTextColor(Color.BLACK);       //changes the tea buttons colour back to normal
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener(){      //opens up a new on click listener for the next person button

            EditText name = (EditText) findViewById(R.id.nameInput);     //finds the user's textbox

            @Override
            public void onClick(View v){
                try {
                    o.setName(name.toString());
                    if (btnCoffee.isSelected()) {
                        o.setDrink("Coffee");
                    } else {
                        o.setDrink("Tea");
                    }
                }
                catch (Exception ex){

                }

                reset();        //runs the reset subroutine
            }

            public void reset(){
                btnTea.get().setTextColor(Color.BLACK);       //resets tea to black
                btnCoffee.setTextColor(Color.BLACK);        //resets coffee button to black
                name.setText("");       //resets the textbox to blank
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

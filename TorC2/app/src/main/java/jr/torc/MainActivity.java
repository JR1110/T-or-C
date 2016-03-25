package jr.torc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout mug = (LinearLayout) findViewById(R.id.flPanel);
        final TextView txtSpecify = (TextView) findViewById(R.id.txtSpecify);
        final EditText txtSpecifyInput = (EditText) findViewById(R.id.txtOtherInput);

        mug.addView(new Panel(this), 0);      //adding the panel view to the linear layout flPanel

        final order o = new order();      //setting up a new order

        final Button btnTea = (Button) findViewById(R.id.btnTea);             //variable for the tea button
        final Button btnCoffee = (Button) findViewById(R.id.btnCoffee);       //variable used for the coffee button
        final Button btnNext = (Button) findViewById(R.id.btnNextOrder);      //variable for the next person button
        final Button btnOther = (Button) findViewById(R.id.btnOther);         //variable used for the other button
        final Button btnFinish = (Button) findViewById(R.id.btnFinish);       //variable used for the finish button

        final Button btnMinus = (Button) findViewById(R.id.btnMinus);       //variable used for the minus button
        final Button btnPlus = (Button) findViewById(R.id.btnPlus);         //variable used for the plus button

        final TextView numSugar = (TextView) findViewById(R.id.numSugar);       //variable used for the sugar number
        numSugar.setText("0");          //sets the level to be 0 from the start

        final RadioGroup rG = (RadioGroup) findViewById(R.id.milkGroup);          //varibale used for the radio buttons

        rG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRB = (RadioButton)findViewById(checkedId);       //finding what radio button is checked
                float f;
                if(checkedRB.getText().equals("Milk")) {
                    f = 300;
                    mug.addView(new Panel(MainActivity.this, f), 0);      //adding the panel view to the linear layout flPanel
                    o.setMilkLevel("Milk");                                 //adding milk level to the order
                } else if (checkedRB.getText().equals("More")) {
                    f = 400;
                    mug.addView(new Panel(MainActivity.this, f), 0);      //adding the panel view to the linear layout flPanel
                    o.setMilkLevel("Lots of milk!");                        //adding milk level to th order
                } else if (checkedRB.getText().equals("No Milk")) {
                    f = 0;
                    mug.addView(new Panel(MainActivity.this, f), 0);      //adding the panel view to the linear layout flPanel
                    o.setMilkLevel("No Milk");                              //adding the milk level to the order
                } else if(checkedRB.getText().equals("Less")) {
                    f = 200;
                    mug.addView(new Panel(MainActivity.this, f), 0);      //adding the panel view to the linear layout flPanel
                    o.setMilkLevel("A little milk");                        //adding the milk level to the order
                }
            }
        });


        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sugar = Integer.parseInt(numSugar.getText().toString());      //converts the sugar textox to a number
                if (sugar > 0) {        //if there is some sugar to be taken away
                    sugar = sugar - 1;      //takes away one level of sugar
                    numSugar.setText(Integer.toString(sugar));        //shows that to the user in the textbox
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sugar = Integer.parseInt(numSugar.getText().toString());      //converts the sugar textbox into a number
                if (sugar >= 0 && sugar < 9) {        //if there is 0 or more sugar (can't be a negative number)
                    sugar++;      //adds one to the sugar level
                    numSugar.setText(Integer.toString(sugar));        //shows the user that in the textbox
                }
            }

        });

        btnTea.setOnClickListener(new View.OnClickListener() {      //opens up new on click listener tea button
            @Override
            public void onClick(View v) {
                btnTea.setTextColor(Color.RED);      //sets the font colour of the button to red
                btnCoffee.setTextColor(Color.BLACK); //Sets the coffee button's colour back to normal
                btnOther.setTextColor(Color.BLACK);     //sets the text colour of other button to black
                btnTea.setSelected(true);           //sets the tea selected to true
                btnCoffee.setSelected(false);           //sets the coffee selected to false
                btnOther.setSelected(false);           //sets the other selected to false

                mug.setVisibility(View.VISIBLE);        //makes the mug visible

                txtSpecify.setVisibility(View.GONE);        //hides the text specify
                txtSpecifyInput.setVisibility(View.GONE);        //hides the input for specify
            }
        });

        btnCoffee.setOnClickListener(new View.OnClickListener(){        //opens up a new on click listener for the coffee button
            @Override
            public void onClick(View v){
                btnCoffee.setTextColor(Color.RED);       //changes the buttons colour
                btnCoffee.setSelected(true);          //makes the button selected
                btnTea.setSelected(false);           //sets the tea selected to false
                btnOther.setSelected(false);           //sets the other selected to false
                btnTea.setTextColor(Color.BLACK);       //changes the tea buttons colour back to normal
                btnOther.setTextColor(Color.BLACK);     //sets the text colour of other button to black

                mug.setVisibility(View.VISIBLE);      //adding the panel view to the linear layout flPanel

                txtSpecify.setVisibility(View.GONE);        //hides the text specify
                txtSpecifyInput.setVisibility(View.GONE);        //hides the input for specify
            }
        });

        btnOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOther.setTextColor(Color.RED);      //sets the font colour of the button to red
                btnCoffee.setTextColor(Color.BLACK); //Sets the coffee button's colour back to normal
                btnTea.setTextColor(Color.BLACK);     //sets the text colour of Tea button to black
                btnOther.setSelected(true);           //sets the other selected to true
                btnCoffee.setSelected(false);           //sets the coffee selected to false
                btnTea.setSelected(false);           //sets the Tea selected to false

                mug.setVisibility(View.GONE);       //stops showing the mug

                txtSpecify.setVisibility(View.VISIBLE);        //shows the text specify
                txtSpecifyInput.setVisibility(View.VISIBLE);        //shows the input for specify

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener(){      //opens up a new on click listener for the next person button

            EditText name = (EditText) findViewById(R.id.nameInput);     //finds the user's textbox

            @Override
            public void onClick(View v){
                try {
                    if (name.getText() == null) {
                        Toast needName = Toast.makeText(MainActivity.this, "Name needed", Toast.LENGTH_SHORT);       //creating toast to prompt user they need to add their name
                        needName.show();
                        throw new IllegalArgumentException("no name");
                    } else {
                        o.setName(name.getText().toString());
                    }
                    if (btnCoffee.isSelected()) {       //setting the drink
                        o.setDrink("Coffee");
                    } else if (btnTea.isSelected()){
                        o.setDrink("Tea");
                    } else if (btnOther.isSelected()){
                        o.setDrink(txtSpecifyInput.getText().toString());
                    }
                    int sugar = Integer.parseInt(numSugar.getText().toString());      //converts the sugar textbox into a number
                    o.setSugar(sugar);      //sets the sugar level as such

                    jsonWriter();       //runs the JSON writer

                    Toast success = Toast.makeText(MainActivity.this, "Drink added", Toast.LENGTH_SHORT);
                    success.show();

                    reset();        //runs the reset subroutine
                }
                catch (Exception ex){
                    Log.d("Creating class error :", ex.getMessage());
                }
            }

            public void jsonWriter(){
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    try {
                        File file = new File(getApplicationContext().getExternalFilesDir(null), "orders.txt");     //opening up a new file
                        PrintWriter printOut = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));         //creates a new printwriter

                        printOut.println(o.getDrink().toString() + " " + o.getName().toString() + " " + o.getMilkLevel() + " " + o.getSugar() + ";");         //prints the info the the printer

                        printOut.close();       //closes the output printer
                    } catch (Exception ex) {
                        Log.d("Writing error :", ex.getMessage());
                    }
                }
            }

            public void reset(){
                btnTea.setTextColor(Color.BLACK);       //resets tea to black
                btnCoffee.setTextColor(Color.BLACK);        //resets coffee button to black
                btnOther.setTextColor(Color.BLACK);         //resets the other colour to black
                txtSpecify.setVisibility(View.GONE);        //hides the text specify
                txtSpecifyInput.setVisibility(View.GONE);        //hides the input for specify
                txtSpecifyInput.setText("");        //resets the text of other
                mug.setVisibility(View.VISIBLE);        //shows the mug
                name.setText("");       //resets the textbox to blank
                numSugar.setText("0");      //resets the sugar level to 0
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, viewOrders.class);        //setting up the intent to change the activities
                startActivity(intent);      //starts the intent activity
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

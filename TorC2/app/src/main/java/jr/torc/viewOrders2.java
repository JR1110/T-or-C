package jr.torc;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class viewOrders2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders2);

        Intent previousIntent = getIntent();                               //getting the previous intent
        String pN = previousIntent.getExtras().getString("pN");     //getting the file string from said intent

        final TableLayout tbl = (TableLayout) findViewById(R.id.tblOrder);        //getting the table to display to

        List<String> Teas = new ArrayList<>();              //making a new list for teas
        List<String> Coffees = new ArrayList<>();           //making a new list for coffees
        List<String> Others = new ArrayList<>();            //making a new list for 'others'

        readIn(Teas, Coffees, Others, pN);           //runs the read in sub-routine
        fillTable(Teas, Coffees, Others, tbl);        //runs the fill table sub routine

    }

    private void readIn(List<String> Teas, List<String> Coffees, List<String> Others, String pN)
    {
        String state = Environment.getExternalStorageState();       //getting the storage state
        if (Environment.MEDIA_MOUNTED.equals(state)) {              //if there is external storage
            File file = new File(getApplicationContext().getExternalFilesDir(null), pN + ".txt");         //oppening up the JSON file
            StringBuffer sB = new StringBuffer("");         //setting up a string buffer
            int ch;

            try {

                FileInputStream iS = new FileInputStream(file);         //setting up the new input stream
                while ((ch = iS.read()) != -1)      //while there is more to read
                {
                    sB.append((char) ch);       //adds the next character to the string buffer
                }
                iS.close();         //closes the reader

            } catch (Exception ex) {
                Log.d("Reading error :", ex.getMessage());      ///shows them as reading errors
            }

            String[] split = sB.toString().split(";");          //splitting up the read in on the ; character

            for (int i = 0; i < split.length; i++)       //until the whole array is read
            {
                String order = split[i].toString();             //flatten out the individual order

                String[] orderDetails = order.split(" ");       //splitting the order into components

                if (orderDetails.length != 4)                    //if there is no array based on spaces
                {
                    continue;                                   //skip and go onto next iteration
                } else {
                    String fullOrder = orderDetails[1] + " - " + " " + orderDetails[2] + " with " + orderDetails[3] + " sugars";
                    if (orderDetails[0].contains("Tea")) {                   //if it is a tea order
                        Teas.add(0, fullOrder);                        //adds the formatted string order to the list
                    } else if (orderDetails[0].contains("Coffee")) {        //if it is a coffee order
                        Coffees.add(0, fullOrder);                          //adds the formatted string order to the list
                    } else {        //if it is a 'other' order
                        Others.add(0, orderDetails[1] + " - " + orderDetails[0]);                      //adds the formatted string order to the list
                    }
                }
            }
        }
    }

    private void fillTable(List<String> Teas, List<String> Coffees, List<String> Others, TableLayout tbl)
    {
        TableRow r = new TableRow(this);        //adding a new table row
        TextView t = new TextView(this);        //adding a new textview

        t.setText("Teas :");                    //setting the title teas

        r.setBackgroundColor(Color.BLACK);      //setting the background to black
        t.setTextColor(Color.argb(00, 255, 69, 0));        //setting the text colour to orange

        tbl.addView(r);

        for (String s : Teas)
        {
            TableRow tR = new TableRow(this);        //adding a new table row
            TextView tV = new TextView(this);        //adding a new textview

            tV.setText(s);                    //setting the order as the text

            tR.setBackgroundColor(Color.WHITE);      //setting the background to white
            tV.setTextColor(Color.argb(00,00,00,00));        //setting the text colour to orange

            tbl.addView(tR);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_orders2, menu);
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

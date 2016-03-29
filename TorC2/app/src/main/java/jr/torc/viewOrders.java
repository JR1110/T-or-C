package jr.torc;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class viewOrders extends ActionBarActivity {

    Intent previousIntent;       //getting the intent from the previous screen
    String partyName;       //setting up the party name

    ExpandableListAdapter eLa;           //setting it up for the list adaptor
    ExpandableListView eLV;                      //setting up an extendable list view

    private String[] drinks;            //string array for the group headers
    private String [][] orders = new String [3][100];        //string 2D array for the orders


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        previousIntent = getIntent();                               //getting the previous intent
        partyName = previousIntent.getExtras().getString("pN");     //getting the file string from said intent

        eLV = (ExpandableListView) findViewById(R.id.viewOrders);

        try {
            prepareHeaderData();        //runs the sub-routine fo rht headers of the drinks
            readIn();       //runs in the read in sub-routine

            eLa = new expandedListView(drinks, orders, this);
            eLV.setAdapter(eLa);
        } catch (Exception ex) {
            Log.d("dropdown error", ex.getMessage());
        }


    }

    private void prepareHeaderData() {
        drinks = new String [] {"Tea", "Coffee", "Other"};            //adding the drink totals to the order
    }


    private void readIn() {
        String state = Environment.getExternalStorageState();       //getting the storage state
        if (Environment.MEDIA_MOUNTED.equals(state)) {              //if there is external storage
            File file = new File(getApplicationContext().getExternalFilesDir(null), partyName+".txt");         //oppening up the JSON file
            StringBuffer sB = new StringBuffer("");         //setting up a string buffer
            int ch;

            try {

                FileInputStream iS = new FileInputStream(file);         //setting up the new input stream
                while ((ch = iS.read()) != -1)      //while there is more to read
                {
                    sB.append((char) ch);       //adds the next character to the string buffer
                }
                iS.close();         //closes the reader

            } catch (Exception ex) {        //catches any exceptions
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
                        orders[0][0] = fullOrder;                        //adds the formatted string order to the list
                    } else if (orderDetails[0].contains("Coffee")) {        //if it is a coffee order
                        orders[1][0] = fullOrder;                          //adds the formatted string order to the list
                    } else {        //if it is a 'other' order
                        orders[2][0] = (orderDetails[1] + " - " + orderDetails[0]);                      //adds the formatted string order to the list
                    }
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_orders, menu);
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
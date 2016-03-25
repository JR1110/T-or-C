package jr.torc;

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

    ExpandableListAdapter expandableListAdapter;           //setting it up for the list adaptor
    expandedListView expandedListView;                      //setting up an extendable list view
    List<String> listHeaders = new ArrayList<String>();                               //list of strings for the headers
    HashMap<String, List<String>> listItems = new HashMap<>();               //hashmap used for the items in the headers


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        ExpandableListView eLV = (ExpandableListView) findViewById(R.id.viewOrders);

        try {
            prepareHeaderData();        //runs the sub-routine fo rht headers of the drinks
            readIn();       //runs in the read in sub-routine

            expandableListAdapter = new expandedListView(listHeaders, listItems, this);
            eLV.setAdapter(expandableListAdapter);
        } catch (Exception ex) {
            Log.d("dropdown error", ex.getMessage());
        }


    }

    private void prepareHeaderData() {
        listHeaders.add("Tea");         //making tea header
        listHeaders.add("Coffee");      //making coffee header
        listHeaders.add("Other");       //making other header


    }


    private void readIn() {
        List<String> Teas = new ArrayList<String>();            //list used for order strings for teas
        List<String> Coffees = new ArrayList<String>();         //list used for order strings for coffees
        List<String> Others = new ArrayList<String>();          //list used for order string for 'others

        String state = Environment.getExternalStorageState();       //getting the storage state
        if (Environment.MEDIA_MOUNTED.equals(state)) {              //if there is external storage
            File file = new File(getApplicationContext().getExternalFilesDir(null), "orders.txt");         //oppening up the JSON file
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

            for(int i = 0; i < split.length; i++)       //until the whole array is read
            {
                String order = split[i].toString();             //flatten out the individual order

                String[] orderDetails = order.split(" ");       //splitting the order into components
                orderDetails[0].replace("\n","");               //removes the backspaces

                if (orderDetails.length != 4)                    //if there is no array based on spaces
                {
                    continue;                                   //skip and go onto next iteration
                } else {
                    String fullOrder = orderDetails[1] + " - " + " " + orderDetails[2] + " with " + orderDetails[3] + " sugars";
                    if (orderDetails[0].equals("Tea")) {                   //if it is a tea order
                        Teas.add(fullOrder.toString());                        //adds the formatted string order to the list
                    } else if (orderDetails[0].equals("Coffee")) {       //if it is a coffee order
                        Coffees.add(fullOrder.toString());                     //adds the formatted string order to the list
                    } else {        //if it is a 'other' order
                        Others.add(orderDetails[1] + " - " + orderDetails[0]);                      //adds the formatted string order to the list
                    }
                }
            }
        }

        if (Teas.size() == 0)
        {
            Teas.add("No Teas...");         //shows the user that no teas have been added
        }

        if (Coffees.size() == 0)
        {
            Coffees.add("No Coffees...");      //shows the user that no coffees have been added
        }

        if (Others.size() == 0)
        {
            Others.add("No Other drinks...");       //shows the user no 'others' have been added
        }

        listItems.put(listHeaders.get(0), Teas);            //adds the tea orders to the expand4ed list view
        listItems.put(listHeaders.get(1), Coffees);         //adds the coffee beverages to the expanded list view dropdown
        listItems.put(listHeaders.get(2), Others);          //adds the other (annoying peoples) orders to the expanded list view
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

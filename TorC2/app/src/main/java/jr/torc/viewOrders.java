package jr.torc;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileInputStream;


public class viewOrders extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        String state = Environment.getExternalStorageState();       //getting the storage state
        if (Environment.MEDIA_MOUNTED.equals(state)) {              //if there is external storage
            File file = new File(getApplicationContext().getExternalFilesDir(null), "orders.json");         //oppening up the JSON file
            StringBuffer sB = new StringBuffer("");         //setting up a string buffer
            int ch;

            try {

                FileInputStream iS = new FileInputStream(file);         //setting up the new input stream
                while ((ch = iS.read()) != -1)      //while there is more to read
                {
                    sB.append((char) ch);       //adds the next character to the string buffer
                }
                iS.close();         //closes the reader

                Log.d("Hello! :", sB.toString());       //for me to see!

            } catch (Exception ex) {        //catches any exceptions
                Log.d("Reading error :", ex.getMessage());      ///shows them as reading errors
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

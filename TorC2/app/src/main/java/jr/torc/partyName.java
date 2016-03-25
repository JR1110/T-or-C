package jr.torc;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class partyName extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_name);

        final EditText txtPartyName = (EditText) findViewById(R.id.txtPartyName);         //finding the input from the user
        final Button btnstart = (Button) findViewById(R.id.btnStart);                       //finding the start button

        btnstart.setOnClickListener(new View.OnClickListener(){        //opens up a new on click listener for the start button
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(partyName.this, MainActivity.class);         //sets the intent to go to the main activity

                if (txtPartyName.getText().length() > 0) {
                    intent.putExtra("pN", txtPartyName.getText().toString());           //sending the party name through intent
                    startActivity(intent);                                                  //starts the main activity
                } else {
                    Toast needName = Toast.makeText(partyName.this, "Event Name Needed", Toast.LENGTH_SHORT);
                    needName.show();        //shows the toast
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_party_name, menu);
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

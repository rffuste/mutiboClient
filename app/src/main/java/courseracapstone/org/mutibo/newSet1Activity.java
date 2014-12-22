package courseracapstone.org.mutibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class newSet1Activity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_set1);
        setTitle("Create new set");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_set1, menu);
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
        else
        {
            if (id==R.id.stop_music)
            {
                stopService(new Intent(newSet1Activity.this, MusicSvc.class));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewSet2(View view)
    {
        Intent intent = new Intent(this, newSet2Activity.class);

        // Get titles of movies
        EditText movie1 = (EditText) findViewById(R.id.editTextMovie1);
        String messageMovie1 = movie1.getText().toString();

        EditText movie2 = (EditText) findViewById(R.id.editTextMovie2);
        String messageMovie2 = movie2.getText().toString();

        EditText movie3 = (EditText) findViewById(R.id.editTextMovie3);
        String messageMovie3 = movie3.getText().toString();

        EditText movie4 = (EditText) findViewById(R.id.editTextMovie4);
        String messageMovie4 = movie4.getText().toString();

        // add them to the intent as an EXTRA_MESSAGE
        intent.putExtra(Messages.MOVIE1_MESSAGE, messageMovie1);
        intent.putExtra(Messages.MOVIE2_MESSAGE, messageMovie2);
        intent.putExtra(Messages.MOVIE3_MESSAGE, messageMovie3);
        intent.putExtra(Messages.MOVIE4_MESSAGE, messageMovie4);

        startActivity(intent);

    }
}

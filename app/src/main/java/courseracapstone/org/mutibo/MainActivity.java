package courseracapstone.org.mutibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    public boolean logedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //start service to play Mutibo music theme
        startService(new Intent(MainActivity.this, MusicSvc.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
                stopService(new Intent(MainActivity.this, MusicSvc.class));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewSet1(View v){

        // call create new Set activity
        Intent intent = new Intent(this, newSet1Activity.class);
        startActivity(intent);
    }

    public void play(View view)
    {
        //logedIn = true;

        if (!logedIn){
            //login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        else {
            //play
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
        }

    }
}

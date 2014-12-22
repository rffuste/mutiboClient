package courseracapstone.org.mutibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class GameOverActivity extends Activity {

    private String total_points;
    private String strFinalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Game over");
        setContentView(R.layout.activity_game_over);

        // Get the message from the intent
        Intent intent = getIntent();
        total_points = intent.getStringExtra(Messages.TOTAL_POINTS);

        Bundle extra = intent.getExtras();
        total_points = String.valueOf(extra.get(Messages.TOTAL_POINTS));

        TextView finalPoints = (TextView) findViewById(R.id.textViewGameOverPoints);
        strFinalPoints =  "You have won " + total_points + " points.";
        finalPoints.setText(strFinalPoints);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
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

    public void endGame(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

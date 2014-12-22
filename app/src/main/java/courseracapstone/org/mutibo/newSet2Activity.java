package courseracapstone.org.mutibo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import butterknife.OnClick;


public class newSet2Activity extends Activity {

    public String movie1_message;
    public String movie2_message;
    public String movie3_message;
    public String movie4_message;
    public int odd;
    public String answer;

    public String user = "admin";
    public String pass = "pass";

    public String server = "https://192.168.1.108:8443";
    //public String server = "https://192.168.0.203:8443";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Create new set");
        // show activity
        setContentView(R.layout.activity_new_set2);

        // Get the message from the intent
        Intent intent = getIntent();
        movie1_message = intent.getStringExtra(Messages.MOVIE1_MESSAGE);
        movie2_message = intent.getStringExtra(Messages.MOVIE2_MESSAGE);
        movie3_message = intent.getStringExtra(Messages.MOVIE3_MESSAGE);
        movie4_message = intent.getStringExtra(Messages.MOVIE4_MESSAGE);


        // set movie titles
        RadioButton radioButtonMovie1 = (RadioButton) findViewById(R.id.radioButtonMovie1);
        radioButtonMovie1.setText(movie1_message);

        RadioButton radioButtonMovie2 = (RadioButton) findViewById(R.id.radioButtonMovie2);
        radioButtonMovie2.setText(movie2_message);

        RadioButton radioButtonMovie3 = (RadioButton) findViewById(R.id.radioButtonMovie3);
        radioButtonMovie3.setText(movie3_message);

        RadioButton radioButtonMovie4 = (RadioButton) findViewById(R.id.radioButtonMovie4);
        radioButtonMovie4.setText(movie4_message);


        final EditText answerText = (EditText) findViewById(R.id.editTextAnswer);
        answerText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // closes keyboard when pressed "enter key"
                    answerText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    return true;
                }
                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_set2, menu);
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
                stopService(new Intent(newSet2Activity.this, MusicSvc.class));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.email_sign_in_button)
    public void createSet(View view) {


        // TODO need to obtain user credentials here!!!

//        String user = mEmailView.getText().toString();
//        String pass = password_.getText().toString();
//        String server = server_.getText().toString();



        // ---------- GET ACCESS SET VALUES: 4 titles, odd, answer ----------------------
        EditText answerCtrl = (EditText) findViewById(R.id.editTextAnswer);
        answer = answerCtrl.getText().toString();

        RadioGroup rg = (RadioGroup) findViewById(R.id.moviesRadioGroup);

        // get selected radio button from radioGroup
        int selectedId = rg.getCheckedRadioButtonId();

                switch(selectedId){
                    case R.id.radioButtonMovie1:
                        odd = 1;
                        break;

                    case R.id.radioButtonMovie2:
                        odd = 2;
                        break;

                    case R.id.radioButtonMovie3:
                        odd = 3;
                        break;

                    case R.id.radioButtonMovie4:
                        odd = 4;
                        break;
                }
        //------------------------------------------------------------------------------
        final SetQuestion set = new SetQuestion(
                movie1_message,
                movie2_message,
                movie3_message,
                movie4_message,
                odd,
                answer
        );

        final MutiboSvcApi svc = MutiboScv.init(server, user, pass);


        CallableTask.invoke(
                new Callable<List<SetQuestion>>() {
                    @Override
                    public List<SetQuestion> call() throws Exception {
                        // Code to execute in the background

                        SetQuestion setResult = svc.addSet(set);

                        List<SetQuestion> result = new ArrayList<SetQuestion>();
                        result.add(setResult);

                        return  result;

                    }
                },
                new TaskCallback<List<SetQuestion>>() {

                    @Override
                    public void success(List<SetQuestion> result) {
                        // Code to execute in the UI thread if the
                        // background operation succeeds
                        //questions = result;
                        Toast.makeText(
                                newSet2Activity.this,
                                "Set added ok",
                                Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(newSet2Activity.this, MainActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void error(Exception e) {
                        // Code to execute in the UI thread if the
                        // background operation fails
                        Toast.makeText(
                                newSet2Activity.this,
                                "Error adding set",
                                Toast.LENGTH_LONG).show();
                    }

                }
        );
        //----------------- READ QUESTIONS END -----------------------------------



    } // end createSet()
}

package courseracapstone.org.mutibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class PlayActivity extends Activity {

    public List<SetQuestion> questions;
    public List<SetQuestion> possibleQuestions;
    private static final String TAG = "PlayActivity";
    private int points;
    private int errors;
    SetQuestion qu;
    TextView textPoints;
    TextView textErrors;
    public Boolean gameOver=false;
    Button next;
    Button submit;
    TextView textAnswer;
    RadioButton movie1;
    RadioButton movie2;
    RadioButton movie3;
    RadioButton movie4;
    Switch rateSet;
    int choosenSet;


    public String user = "admin";
    public String pass = "pass";

    public String server = "https://192.168.1.108:8443";
    //public String server = "https://192.168.0.203:8443";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        setTitle("Play Mutibo");
        questions = new ArrayList<SetQuestion>();

        rateSet = (Switch) findViewById(R.id.switchRating);
        rateSet.setEnabled(true);
        rateSet.setChecked(true);
        rateSet.setTextOff("Bad");
        rateSet.setTextOn("Good");
        setupGame();



  }

    private void setupGame() {
        points = 0;
        errors = 3;

        textPoints = (TextView) findViewById(R.id.textViewPoints);
        textPoints.setText(String.valueOf(points));

        textErrors = (TextView) findViewById(R.id.textViewErrors);
        textErrors.setText(String.valueOf(errors));

        next = (Button)  findViewById(R.id.buttonNext);
        submit = (Button) findViewById(R.id.buttonSubmit);
        next.setVisibility(View.INVISIBLE);

        textAnswer = (TextView) findViewById(R.id.textViewAnswer);
        textAnswer.setVisibility(View.INVISIBLE);

        movie1 = (RadioButton) findViewById(R.id.radioButtonMovie1);
        movie2 = (RadioButton) findViewById(R.id.radioButtonMovie2);
        movie3 = (RadioButton) findViewById(R.id.radioButtonMovie3);
        movie4 = (RadioButton) findViewById(R.id.radioButtonMovie4);



        readQuestions();

    }

    private void readQuestions() {
        // ------------ READ QUESTIONS --------------------------------------------
        final MutiboSvcApi svc = MutiboScv.init(server, user, pass);

        CallableTask.invoke(
                new Callable<List<SetQuestion>>() {
                    @Override
                    public List<SetQuestion> call() throws Exception {
                        // Code to execute in the background

                        List<SetQuestion> response = svc.getSetList();


                        for (SetQuestion s: response)
                        {
                            questions.add(s);
                        }

                        return  questions;

                    }
                },
                new TaskCallback<List<SetQuestion>>() {

                    @Override
                    public void success(List<SetQuestion> result) {
                        // Code to execute in the UI thread if the
                        // background operation succeeds
                        questions = result;

                        possibleQuestions = new ArrayList<SetQuestion>();
                        possibleQuestions.addAll(questions);
                        play();
                    }

                    @Override
                    public void error(Exception e) {
                        // Code to execute in the UI thread if the
                        // background operation fails
                        Toast.makeText(
                                PlayActivity.this,
                                "Error reading sets.",
                                Toast.LENGTH_LONG).show();
                    }

                }
        );
        //----------------- READ QUESTIONS END -----------------------------------

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.play, menu);
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
                stopService(new Intent(PlayActivity.this, MusicSvc.class));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleQuestions(List<SetQuestion> questions) {
        this.questions = questions;

        for (SetQuestion s: PlayActivity.this.questions) {
            Toast.makeText(PlayActivity.this, s.getTitle1(), Toast.LENGTH_SHORT).show();
        }
    }


    private void play()
    {
        //choosenSet = 1 + (int)(Math.random()*possibleQuestions.size());


        choosenSet = setSelection(possibleQuestions);
        qu = possibleQuestions.get(choosenSet);

        //delete choosen Set from possible question set for next sets.
        possibleQuestions.remove(choosenSet);

        // set movie titles
        RadioButton radioButtonMovie1 = (RadioButton) findViewById(R.id.radioButtonMovie1);
        radioButtonMovie1.setText(qu.getTitle1());

        RadioButton radioButtonMovie2 = (RadioButton) findViewById(R.id.radioButtonMovie2);
        radioButtonMovie2.setText(qu.getTitle2());

        RadioButton radioButtonMovie3 = (RadioButton) findViewById(R.id.radioButtonMovie3);
        radioButtonMovie3.setText(qu.getTitle3());

        RadioButton radioButtonMovie4 = (RadioButton) findViewById(R.id.radioButtonMovie4);
        radioButtonMovie4.setText(qu.getTitle4());

    }

    public void submitAnswer(View view) {
        RadioGroup rg = (RadioGroup) findViewById(R.id.moviesRadioGroup);
        int userAnswer=0;

        next.setVisibility(View.VISIBLE);
        textAnswer.setVisibility(View.VISIBLE);
        submit.setEnabled(false);


        // get selected radio button from radioGroup
        int selectedId = rg.getCheckedRadioButtonId();

        switch(selectedId){
            case R.id.radioButtonMovie1:
                userAnswer = 1;
                break;

            case R.id.radioButtonMovie2:
                userAnswer = 2;
                break;

            case R.id.radioButtonMovie3:
                userAnswer = 3;
                break;

            case R.id.radioButtonMovie4:
                userAnswer = 4;
                break;
        }
        //------------------------------------------------------------------------------

        textAnswer = (TextView) findViewById(R.id.textViewAnswer);
        textAnswer.setText(qu.getAnswer());

        if (qu.getOdd() == userAnswer)
        {
            //user guess is right
            points++;
            textPoints.setText(String.valueOf(points));

            Toast.makeText(
                    PlayActivity.this,
                    "Correct!",
                    Toast.LENGTH_LONG).show();

        }
        else
        {
            //user guess is wrong
            errors--;
            if ((errors==0) || (possibleQuestions.size()==0))
            {
                // Game is over, so start finishing the game
                gameOver=true;
                next.setText("Game over");




//                Toast.makeText(
//                        PlayActivity.this,
//                        "You won " + String.valueOf(points) + " points.",
//                        Toast.LENGTH_LONG).show();


            }
            textErrors.setText(String.valueOf(errors));

            Toast.makeText(
                    PlayActivity.this,
                    "Error!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void onNext(View view) {



        if (!gameOver){
            // clean last user answer
            textAnswer.setText("");

            movie1.setChecked(false);
            movie2.setChecked(false);
            movie3.setChecked(false);
            movie4.setChecked(false);

            next.setVisibility(View.INVISIBLE);
            submit.setEnabled(true);

            refreshRating();
            play();
        }
        else
        {
            refreshRating();

            Intent intent = new Intent(this, GameOverActivity.class);
            intent.putExtra(Messages.TOTAL_POINTS, points);
            startActivity(intent);

            //Intent intent = new Intent(this, MainActivity.class);
            //startActivity(intent);

        }


    }

    private void refreshRating() {
        int finalRate=qu.getRating();

        // user have rated as good the set
        if(rateSet.isChecked())
        {
            if (qu.getRating()<10)
            {
                Long indexInt = qu.getId();
                int indexLong = indexInt.intValue();

                finalRate++;
                //qu.setRating(finalRate);
                questions.get(indexLong).setRating(finalRate);
            }
        }
        else
        {
            // user have rated as bad the set
            if (qu.getRating()>0)
            {
                Long indexInt = qu.getId();
                int indexLong = indexInt.intValue();

                finalRate--;
                //qu.setRating(finalRate);
                questions.get(indexLong).setRating(finalRate);

            }
        }

        final MutiboSvcApi svc = MutiboScv.init(server, user, pass);

        CallableTask.invoke(
            new Callable<List<SetQuestion>>() {
                @Override
                public List<SetQuestion> call() throws Exception {
                // Code to execute in the background
                    return svc.refreshSetRatings(questions);

                    //List<SetQuestion> response = questions;
                    //return response;

                }
            },
            new TaskCallback<List<SetQuestion>>() {
                @Override
                public void success(List<SetQuestion> result) {
                    // Code to execute in the UI thread if the
                    // background operation succeeds
                }

                @Override
                public void error(Exception e) {
                    // Code to execute in the UI thread if the
                    // background operation fails

                    Toast.makeText(
                            PlayActivity.this,
                            "Error refreshing set ratings.",
                            Toast.LENGTH_SHORT).show();

                }
            }
        );
    }


    public int setSelection(List<SetQuestion> questions){
        int response=0;

        response = 1 + (int)(Math.random()*possibleQuestions.size());

        return  response;
    }
}

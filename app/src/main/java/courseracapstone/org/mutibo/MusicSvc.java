package courseracapstone.org.mutibo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ruben on 08/11/2014.
 */
public class MusicSvc  extends Service{
    private static final String TAG = "MusicSvc";
    MediaPlayer player;

    @Override
    public void onCreate() {
        //Toast.makeText(this, "My Music service Created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate");
        Log.i(TAG, "onCreate");

        player = MediaPlayer.create(this, R.raw.mutibo);
        player.setLooping(true); // Set looping
    }

    @Override
    public void onStart(Intent intent, int startid) {
        //Toast.makeText(this, "Music service Started", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart");
        player.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        //Toast.makeText(this, "Music service Stopped", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy");
        player.stop();
    }
}

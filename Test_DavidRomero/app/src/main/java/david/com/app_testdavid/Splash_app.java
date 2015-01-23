package david.com.app_testdavid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.Timer;
import java.util.TimerTask;


public class Splash_app extends Activity {

    int splashDelay =2500;
    ShimmerTextView tx_title_app,my_name;
    Shimmer shimmer = new Shimmer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_app);
        tx_title_app=(ShimmerTextView)findViewById(R.id.tx_title_app);

        my_name=(ShimmerTextView)findViewById(R.id.my_name);

        tx_title_app.setReflectionColor(getResources().getColor(android.R.color.white));
        my_name.setReflectionColor(getResources().getColor(android.R.color.white));

        shimmer.start(tx_title_app);
        shimmer.start(my_name);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(Splash_app.this, HomeActivity.class);
                startActivity(mainIntent);
                finish();//Activity Destroy.
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, splashDelay);//time to wait
    }
}


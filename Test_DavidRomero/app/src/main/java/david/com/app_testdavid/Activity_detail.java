package david.com.app_testdavid;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import david.com.app_testdavid.utils.Utils;

/**
 * Created by David Romero on 22/01/15.
 */
public class Activity_detail extends ActionBarActivity{

    String id, mag, place, alert, status, tsunami, code, magType, type, title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){

            id=bundle.getString("id");
            mag=bundle.getString("mag");
            place=bundle.getString("place");
            alert=bundle.getString("alert");
            status=bundle.getString("status");
            tsunami=bundle.getString("tsunami");
            code=bundle.getString("code");
            magType=bundle.getString("magType");
            type=bundle.getString("type");
            title=bundle.getString("title");

            Utils.createAlert(Activity_detail.this, id+"\n"+ mag+ "\n"+place+ "\n"+alert+"\n"+ status+ "\n"+ tsunami+ "\n"+ code+ "\n"+ magType+"\n"+  type+"\n"+  title);
        }


    }
}

package david.com.app_testdavid;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

import david.com.app_testdavid.utils.Utils;

/**
 * Created by David Romero on 22/01/15.
 */
public class Activity_detail extends ActionBarActivity{

    String id, mag, place, alert, status, tsunami, code, magType, type, title;

    TextView tex_title,tex_id,tex_place,tex_mag,tex_alert,tex_tsu;

    TextView tex_code, tex_magType, tex_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tex_title=(TextView)findViewById(R.id.tex_title);
        tex_id=(TextView)findViewById(R.id.tex_id);
        tex_place=(TextView)findViewById(R.id.tex_place);
        tex_mag=(TextView)findViewById(R.id.tex_mag);
        tex_alert=(TextView)findViewById(R.id.tex_alert);
        tex_tsu=(TextView)findViewById(R.id.tex_tsu);

        tex_code=(TextView)findViewById(R.id.tex_code);
        tex_magType=(TextView)findViewById(R.id.tex_magType);
        tex_type=(TextView)findViewById(R.id.tex_type);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Detail Earthquake");







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


            tex_title.setText(title);
            tex_id.setText("ID:  "+id);
            tex_place.setText("Place :\n"+place);
            tex_mag.setText("Magnitude :  "+mag);
            tex_alert.setText("Alert :"+alert);
            tex_tsu.setText("Tsunami :  "+tsunami);

            tex_code.setText("Code :\n"+code);
            tex_magType.setText("Magnitude Type :\n"+magType);
            tex_type.setText("Type :\n"+type);

        }


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

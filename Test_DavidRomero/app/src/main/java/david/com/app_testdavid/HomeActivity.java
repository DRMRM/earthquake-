package david.com.app_testdavid;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;

import david.com.app_testdavid.adapters.MyExpandableListItemAdapter;
import david.com.app_testdavid.beans.Features;
import david.com.app_testdavid.beans.Metadata;
import david.com.app_testdavid.beans.Propierties;
import david.com.app_testdavid.parser.ParserJson;
import david.com.app_testdavid.utils.Utils;



/**
 * Created by David Romero on 21/01/15.
 */
public class HomeActivity extends ActionBarActivity {

    final String title="Aplication Earthquake ";
    String JsonString="";

    Metadata metadata=null;
    TextView tx_title_home, tx_home_earthquake;

    ArrayList <Features>feature=null;

    ListView list_home;
    int INITIAL_DELAY_MILLIS=500;
    SwipeRefreshLayout swipeRefreshLayout;

    private AnimationAdapter mAnimAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle(title);

        list_home=(ListView)findViewById(R.id.list_home);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.BLUE, Color.GREEN, Color.CYAN);

        tx_title_home=(TextView)findViewById(R.id.tx_title_home);
        tx_home_earthquake=(TextView)findViewById(R.id.tx_home_earthquake);

        downloadData();
 }
    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener(){
        @Override
        public void onRefresh() {
            //simulate doing something
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                    downloadData();
                }
            }, 3000);
        }};

    public void downloadData(){
if(Utils.isConected(HomeActivity.this)){
    new GetAllEarthquake().execute();
}else {
    Toast.makeText(HomeActivity.this,"Are Not Conected",Toast.LENGTH_LONG).show();
}


    }
    public void getDatasMain(ArrayList<Features>datas){
        MyExpandableListItemAdapter  mExpandableListItemAdapter = new MyExpandableListItemAdapter(this,datas);
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(mExpandableListItemAdapter);
        alphaInAnimationAdapter.setAbsListView(list_home);
        assert alphaInAnimationAdapter.getViewAnimator() != null;
        alphaInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);
        list_home.setAdapter(alphaInAnimationAdapter);
        mExpandableListItemAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_app, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {


            case R.id.reload:
                downloadData();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class GetAllEarthquake extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            JsonString= Utils.GetJsonData(getResources().getString(R.string.url_earthquake));
            ParserJson parser=new ParserJson();

            metadata=parser.getMetadata(JsonString);
            feature=parser.getFeature(JsonString);


            return null;


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String text="";
           ArrayList<Propierties>prs=new ArrayList<Propierties>();
           Propierties p=null;

            if(feature.size()>0){

            try { tx_title_home.setText(metadata.getTitle()); }catch (Exception e){}
            try { tx_home_earthquake.setText("Count earthquake : "+metadata.getCount()); }catch (Exception e){}
            getDatasMain(feature);

                for (int i=0; i<feature.size();i++){
                    p=feature.get(i).getPropierties();
                    prs.add(p);
                }
                Features.getInstance().setFets(feature);


            }else {
                Utils.createAlert(HomeActivity.this,"There are not data");
            }


        }

    }
}

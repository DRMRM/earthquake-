package david.com.app_testdavid.adapters;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import david.com.app_testdavid.R;
import david.com.app_testdavid.beans.Features;
import david.com.app_testdavid.beans.Propierties;


public class Adapter_locations_list extends BaseAdapter{

	ArrayList<Features>feats;
	private Context context;



	public void setData(ArrayList<Features> feats, Context context){
		this.feats = feats;
		this.context = context;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return feats.size();
	}


	@Override
	public Features getItem(int position) {
		// TODO Auto-generated method stub
		return feats.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.row_list_locations, null);
		}
        Features ftes=feats.get(position);
		TextView tx_list_magnitude=(TextView)convertView.findViewById(R.id.tx_list_magnitude);

		TextView tx_list_title=(TextView)convertView.findViewById(R.id.tx_list_title);


		try {tx_list_magnitude.setText("Mag: "+ftes.getPropierties().getMag()); } catch (Exception e) { }
		try {tx_list_title.setText(ftes.getPropierties().getTitle());} catch (Exception e) { }
		
		return convertView;
	}

}

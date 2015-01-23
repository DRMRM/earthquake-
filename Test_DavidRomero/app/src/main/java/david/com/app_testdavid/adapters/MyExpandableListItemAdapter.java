/*
 * Copyright 2014 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package david.com.app_testdavid.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.nhaarman.listviewanimations.itemmanipulation.expandablelistitem.ExpandableListItemAdapter;

import org.w3c.dom.Text;

import david.com.app_testdavid.Activity_detail;
import david.com.app_testdavid.Localizacion;
import david.com.app_testdavid.R;
import david.com.app_testdavid.beans.Features;
import david.com.app_testdavid.beans.Propierties;

public class MyExpandableListItemAdapter extends ExpandableListItemAdapter<Features> {

     Context mContext;
     ArrayList<Features>datos;
  
    public MyExpandableListItemAdapter(Context context, ArrayList<Features> datos) {
        super(context, R.layout.activity_expandablelistitem_card, R.id.activity_expandablelistitem_card_title, R.id.activity_expandablelistitem_card_content);
        mContext = context;
        this.datos=datos;
        
        for (int i = 0; i < datos.size(); i++) {
            add(this.datos.get(i));
        }
    }

    @Override
    public View getTitleView(final int position,  View convertView, final ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.header_listview, null);
            TextView tx_mag, tx_place;


            double mag=Double.parseDouble(datos.get(position).getPropierties().getMag());
            tx_mag=(TextView)convertView.findViewById(R.id.tx_mag);

            if(mag>=0 && mag <=0.9){
                tx_mag.setTextColor(mContext.getResources().getColor(R.color.android_green));
            }else if(mag>=1 && mag <=4.0){
                tx_mag.setTextColor(mContext.getResources().getColor(R.color.android_darkgreen));
            }else if(mag>4 && mag <=7.0){
                tx_mag.setTextColor(mContext.getResources().getColor(R.color.android_darkorange));
            }else if(mag>7 ){
                tx_mag.setTextColor(mContext.getResources().getColor(R.color.android_darkred));
            }

            tx_place=(TextView)convertView.findViewById(R.id.tx_place);
            Features feature=datos.get(position);






            try {tx_place.setText(feature.getPropierties().getTitle()); }catch (Exception e){ }
            try { tx_mag.setText("Magnitude :\n"+feature.getPropierties().getMag());}catch (Exception e){ }


        }


        return convertView;


    }

    @Override
    public View getContentView(final int position,  View convertView,  final ViewGroup parent) {
        
        
        if (convertView == null) {
        	convertView = LayoutInflater.from(mContext).inflate(R.layout.row_image_expandable, null);
		}
        final Features dato=datos.get(position);
    


        TextView tx_title=(TextView)convertView.findViewById(R.id.tx_title);
        TextView tx_status=(TextView)convertView.findViewById(R.id.tx_status);
        TextView tx_alert=(TextView)convertView.findViewById(R.id.tx_alert);
        TextView tx_type=(TextView)convertView.findViewById(R.id.tx_type);
        
        tx_title.setText("Place : "+dato.getPropierties().getPlace());
        tx_status.setText("Status : "+dato.getPropierties().getStatus());
        tx_type.setText("Type : "+dato.getPropierties().getType());
        tx_alert.setText("Alert : "+dato.getPropierties().getAlert());


        ImageView img_ping,img_see;

        img_ping=(ImageView)convertView.findViewById(R.id.img_ping);
        img_see=(ImageView)convertView.findViewById(R.id.img_see);


        img_ping.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_map=new Intent(mContext, Localizacion.class);

                intent_map.putExtra("lat",dato.getGeometry().getCoordinates().getLatitude());
                intent_map.putExtra("longi",dato.getGeometry().getCoordinates().getLongitude());
                intent_map.putExtra("title",dato.getPropierties().getTitle());
                intent_map.putExtra("desc","Mag :" +dato.getPropierties().getMag());

                mContext.startActivity(intent_map);

            }
        });

        img_see.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_det=new Intent(mContext, Activity_detail.class);

                intent_det.putExtra("id",dato.getId());
                intent_det.putExtra("mag",dato.getPropierties().getMag());
                intent_det.putExtra("place",dato.getPropierties().getPlace());
                intent_det.putExtra("alert",dato.getPropierties().getAlert());
                intent_det.putExtra("status",dato.getPropierties().getStatus());
                intent_det.putExtra("tsunami",dato.getPropierties().getTsunami());
                intent_det.putExtra("code",dato.getPropierties().getCode());
                intent_det.putExtra("magType",dato.getPropierties().getMagType());
                intent_det.putExtra("type",dato.getPropierties().getType());
                intent_det.putExtra("title",dato.getPropierties().getTitle());

                mContext.startActivity(intent_det);

            }
        });

        
        
        try {

        	  //ImageLoader.getInstance().displayImage(dato.get, row_img_expandable);

		} catch (Exception e) {
			// TODO: handle exception
		}
        


        return convertView;
    }
}
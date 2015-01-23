package david.com.app_testdavid.parser;

import android.content.Context;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import david.com.app_testdavid.beans.Coordinates;
import david.com.app_testdavid.beans.Features;
import david.com.app_testdavid.beans.Geometry;
import david.com.app_testdavid.beans.Metadata;
import david.com.app_testdavid.beans.Propierties;

/**
 * Created by David Romero on 21/01/15.
 */
public class ParserJson {


    public Metadata getMetadata(String Json){

        Metadata metadata=null;

        JSONObject jsonObject;
        JSONObject jsonObjectMetada;




        try {
            metadata =new Metadata();
            jsonObject=new JSONObject(Json);

            jsonObjectMetada=new JSONObject(jsonObject.getString("metadata"));

            metadata.setGenerated(jsonObjectMetada.getString("generated"));
            metadata.setUrl(jsonObjectMetada.getString("url"));
            metadata.setTitle(jsonObjectMetada.getString("title"));
            metadata.setStatus(jsonObjectMetada.getString("status"));
            metadata.setApi(jsonObjectMetada.getString("api"));
            metadata.setCount(jsonObjectMetada.getString("count"));


        }catch (Exception e){
            metadata.setTitle(""+e.toString());
        }
       return  metadata;
    }

    public ArrayList<Features> getFeature(String json){
        ArrayList<Features> features=new ArrayList<Features>();

        Features feature;
        Geometry geometry;
        Coordinates coordinates;

        JSONObject jsonObject;
        JSONArray jsonArray;
        JSONObject jsonArrayPropi;


        JSONObject jsonArrayGeo;

        JSONArray jsonArraygeometry;





        Propierties propierties;

        try {
            jsonObject=new JSONObject(json);
            jsonArray=new JSONArray(jsonObject.getString("features"));

            for(int i=0; i<jsonArray.length();i++){
                feature=new Features();

                feature.setType(jsonArray.getJSONObject(i).getString("type"));

                feature.setId(jsonArray.getJSONObject(i).getString("id"));

                jsonArrayPropi=new JSONObject(jsonArray.getJSONObject(i).getString("properties"));
                jsonArrayGeo=new JSONObject(jsonArray.getJSONObject(i).getString("geometry"));

                jsonArraygeometry=new JSONArray(jsonArrayGeo.getString("coordinates"));





                propierties=new Propierties();



                propierties.setMag(jsonArrayPropi.getString("mag"));
                propierties.setPlace(jsonArrayPropi.getString("place"));
                propierties.setTime(jsonArrayPropi.getString("time"));
                propierties.setUpdated(jsonArrayPropi.getString("updated"));
                propierties.setTz(jsonArrayPropi.getString("tz"));
                propierties.setUrl(jsonArrayPropi.getString("url"));
                propierties.setDetail(jsonArrayPropi.getString("detail"));
                propierties.setFelt(jsonArrayPropi.getString("felt"));
                propierties.setCdi(jsonArrayPropi.getString("cdi"));
                propierties.setMmi(jsonArrayPropi.getString("mmi"));
                propierties.setAlert(jsonArrayPropi.getString("alert"));
                propierties.setStatus(jsonArrayPropi.getString("status"));
                propierties.setTsunami(jsonArrayPropi.getString("tsunami"));
                propierties.setSig(jsonArrayPropi.getString("sig"));
                propierties.setNet(jsonArrayPropi.getString("net"));



                propierties.setCode(jsonArrayPropi.getString("code"));
                propierties.setIds(jsonArrayPropi.getString("ids"));
                propierties.setSources(jsonArrayPropi.getString("sources"));
                propierties.setTypes(jsonArrayPropi.getString("types"));
                propierties.setNst(jsonArrayPropi.getString("nst"));
                propierties.setDmin(jsonArrayPropi.getString("dmin"));
                propierties.setRms(jsonArrayPropi.getString("rms"));
                propierties.setGap(jsonArrayPropi.getString("gap"));
                propierties.setMagType(jsonArrayPropi.getString("magType"));
                propierties.setType(jsonArrayPropi.getString("type"));
                propierties.setTitle(jsonArrayPropi.getString("title"));

                feature.setPropierties(propierties);



                geometry=new Geometry();
                geometry.setType(jsonArrayGeo.getString("type"));


                coordinates=new Coordinates();
                try { coordinates.setLongitude(jsonArraygeometry.getString(0)); }catch (Exception e){}
                try { coordinates.setLatitude(jsonArraygeometry.getString(1)); }catch (Exception e){}
                try { coordinates.setAltitude(jsonArraygeometry.getString(2)); }catch (Exception e){}

                geometry.setCoordinates(coordinates);


                feature.setGeometry(geometry);




















                features.add(feature);



            }

        } catch (JSONException e) {
            Log.e("Error  JSONException ", e.toString());

        }


        return  features;
    }


}

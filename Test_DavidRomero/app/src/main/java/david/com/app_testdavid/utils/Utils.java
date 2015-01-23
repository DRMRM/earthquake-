package david.com.app_testdavid.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.ContextThemeWrapper;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import david.com.app_testdavid.R;

/**
 * Created by David Romero on 21/01/15.
 */
public class Utils {



    public static void createAlert(final Context activity, String mensaje) {
        Utils.createAlert(activity, null, mensaje);
    }
    public static void createAlert(final Context activity, String mensaje, DialogInterface.OnClickListener ocl) {
        Utils.createAlert(activity, null, mensaje, ocl);
    }
    public static void createAlert(final Context activity, String titulo, String mensaje) {
        Utils.createAlert(activity, titulo, mensaje, null);
    }
    public static void createAlert(final Context activity, String titulo, String mensaje, DialogInterface.OnClickListener ocl) {
        Utils.createAlert(activity, titulo, mensaje, false, ocl);
    }
    public static void createAlert(final Context activity, String titulo, String mensaje, boolean cancelButton, DialogInterface.OnClickListener ocl) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(activity, R.style.AppTheme));
        builder.setTitle(titulo)
                .setMessage(mensaje)
                .setCancelable(false)
                .setPositiveButton("OK", ocl);
        if(cancelButton) {
            builder.setNegativeButton("Cancelar", null);
        }
        AlertDialog alert = builder.create();
        alert.show();
    }

    public static boolean isConected(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx .getSystemService(Context.CONNECTIVITY_SERVICE);
        //
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        // este bucle debera no ser tan Ðapa


        for (int i = 0; i < redes.length; i++) {

            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }

        }
        return bConectado;
    }

    public static String GetJsonData(String url){
        String jsoncode="";

        HttpResponse response;
        HttpClient myClient = new DefaultHttpClient();
        HttpPost myConnection = new HttpPost(url);

        try {
            response = myClient.execute(myConnection);
            jsoncode = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsoncode;

    }


}

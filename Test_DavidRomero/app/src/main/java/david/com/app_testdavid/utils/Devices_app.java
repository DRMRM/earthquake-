package david.com.app_testdavid.utils;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by David Romero on 21/01/15.
 */
public class Devices_app
{
    public static Point getSizeScreen(Context context) {
        Point point = new Point();
        try {DisplayMetrics display = context.getResources().getDisplayMetrics();
            point.x = display.widthPixels;;
            point.y = display.heightPixels;
        } catch (Exception e) {}
        return point;
    }
}

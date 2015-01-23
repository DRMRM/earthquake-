package david.com.app_testdavid.beans;

/**
 * Created by David Romero on 21/01/15.
 */
public class Coordinates {

    private String latitude, longitude, altitude;

    private static Coordinates _instance;

    public static synchronized Coordinates getInstance() {
        if (_instance == null) {
            _instance = new Coordinates();
        }
        return _instance;
    }



    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }
}

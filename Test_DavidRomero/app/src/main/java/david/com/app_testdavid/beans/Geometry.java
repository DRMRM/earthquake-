package david.com.app_testdavid.beans;

/**
 * Created by David Romero on 21/01/15.
 */
public class Geometry {

    private String type;
    private Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    private static Geometry _instance;

    public static synchronized Geometry getInstance() {
        if (_instance == null) {
            _instance = new Geometry();
        }
        return _instance;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

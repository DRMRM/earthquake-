package david.com.app_testdavid.beans;

import java.util.ArrayList;

/**
 * Created by David Romero on 21/01/15.
 */
public class Features {
    private static Features _instance;

    public static synchronized Features getInstance() {
        if (_instance == null) {
            _instance = new Features();
        }
        return _instance;
    }

    private String type,id;
   private Propierties propierties;
    private Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public Propierties getPropierties() {
        return propierties;
    }

    public void setPropierties(Propierties propierties) {
        this.propierties = propierties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

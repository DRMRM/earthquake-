package david.com.app_testdavid.beans;

/**
 * Created by David Romero on 21/01/15.
 */
public class Metadata {

    private String generated, url, title, api ,status,count;
    private static Metadata _instance;

    public static synchronized Metadata getInstance() {
        if (_instance == null) {
            _instance = new Metadata();
        }
        return _instance;
    }

    public String getGenerated() {
        return generated;
    }

    public void setGenerated(String generated) {
        this.generated = generated;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}

package pl.koneckimarcin.triathlontrainingmanagement.strava;


import java.util.Properties;

public class StravaPropertyReader {

    private static Properties stravaProperties = new Properties();

    static {
        try {
            stravaProperties.load(StravaPropertyReader.class.getResourceAsStream("/application-strava.properties"));
        } catch (Exception e) {
            System.out.println(e.getMessage()); // todo: exception
        }
    }
    public static String getValue(String key){
        return stravaProperties.getProperty(key);
    }
}

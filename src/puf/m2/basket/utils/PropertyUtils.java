package puf.m2.basket.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtils {
    public static Properties props = new Properties();
    
    static {
        try {
            props.load(new FileInputStream("basket.properties"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

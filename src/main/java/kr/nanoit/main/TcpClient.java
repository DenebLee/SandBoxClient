package kr.nanoit.main;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Main {
    public static final SimpleDateFormat SIMPLE_DATA_FORMAT = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
    public static Configuration configuration;

    public static void main(String[] args) throws ConfigurationException, IOException {
        final Configurations configurations = new Configurations();
        configuration = configurations.properties(new File("src/main/java/resources/ClientValue.properties"));

        try {





            HttpConnect httpConnect = new HttpConnect();
            httpConnect.Connection();













        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

package assignment.server.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private String filePath = "D:\\My\\Program\\Company\\Synnex FPT\\Project\\Summary\\src\\assignment\\server\\dao\\config.txt";
    private Map<String, String> config = readFile(filePath);
    public String DB_CONNECTION;
    public String SERVER_NAME;
    public String PORT_NUMBER;
    public String DB_NAME;
    public String DB_USER;
    public String DB_PASSWORD;
    public String DB_MAX_CONNECTIONS;

    public Configuration() throws Exception {
        this.init();
    }

    public void init(){
        this.DB_CONNECTION = config.get("DB_CONNECTION");
        this.SERVER_NAME = config.get("SERVER_NAME");
        this.PORT_NUMBER = config.get("PORT_NUMBER");
        this.DB_NAME = config.get("DB_NAME");
        this.DB_USER = config.get("DB_USER");
        this.DB_PASSWORD = config.get("DB_PASSWORD");
        this.DB_MAX_CONNECTIONS = config.get("DB_MAX_CONNECTIONS");
    }

    private Map<String, String> readFile(String filePath) throws Exception {
        Map<String, String> config = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("=");

            if (parts.length == 2) {
                config.put(parts[0].trim(), parts[1].trim());
            }
        }

        reader.close();
        return config;
    }
}
package PersistanceLayer;

import BusinessLayer.Entities.Config;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ConfigJsonDao {
    private static final String route = "files/config.json";
    private static final Path path = Path.of(route);

    public ConfigJsonDao() {
    }

    public Config readConfig(){
        Config config = new Config();
        try{
            Gson gson=new Gson();

            String text= Files.readString(path);
            config=gson.fromJson(text, Config.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

}
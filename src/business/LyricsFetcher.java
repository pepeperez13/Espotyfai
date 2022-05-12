package business;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LyricsFetcher {
    private static final String genericURL = "https://api.lyrics.ovh/v1/";


    public String getSongLyrics (String artist, String song) {
        try {

            // Ponemos las frases en formato correcto
            artist = artist.replaceAll(" ", "%20");
            song = song.replaceAll(" ", "%20");
            String urlString = genericURL + artist + "/" + song;

            URL url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                return "Lyrics for the song could not be found";
            }else{
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                JsonObject jsonObject = (JsonObject) JsonParser.parseString(info.toString());
                String lyrics = String.valueOf(jsonObject.get("lyrics"));

                // Hacemos los cambios para rreglar el formato del texto
                lyrics = lyrics.replace("\\n\\n", System.lineSeparator());
                lyrics = lyrics.replace("\\n", System.lineSeparator());
                lyrics = lyrics.replace("\\r", "");
                System.out.println(lyrics);
                return lyrics;
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return  "Lyrics for the song could not be found.";
    }

}

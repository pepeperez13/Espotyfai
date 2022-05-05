package business;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LyricsFetcher {
    private static final String genericURL = "https://api.lyrics.ovh/v1/";

    public static void main (String[] args) {
        String lyrics = getSongLyrics("Coldplay", "Adventure of a Lifetime");
        System.out.println(lyrics);
        lyrics = getSongLyrics("Black Eyed Peas", "The Time");
        System.out.println(lyrics);

    }

    public static String getSongLyrics (String artist, String song) {
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

                String stringInfo = info.toString();
                String[] frases = stringInfo.split(":");

                return frases[1].replace("\\n",  System.lineSeparator());
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return  "Lyrics for the song could not be found.";
    }




}

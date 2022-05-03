package business;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LyricsFetcher {

    public void /*String*/ getSongLyrics (String artist, String name) {


    }

    public static void main (String[] args) {

        try {

            URL url = new URL("https://api.lyrics.ovh/v1/Coldplay/Adventure%20of%20a%20Lifetime");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException();
            }else{
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                //System.out.println(info);

                String stringInfo = info.toString();
                String[] frases = stringInfo.split(":");

                System.out.println(frases[1]);
                String definitiva = frases[1].replaceAll("\n\n", "");

                //for (String frase: frases) {
                  //  System.out.println(frase);
                //}
                System.out.println("hola\na todos como estais");
                System.out.println(definitiva);
            }



        } catch (Exception e){
            e.printStackTrace();
        }

    }


}

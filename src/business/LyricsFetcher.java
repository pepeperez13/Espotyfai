package business;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * Clase que se encarga de establecer conexión con la API proporcionada
 */
public class LyricsFetcher {
    private static final String genericURL = "https://api.lyrics.ovh/v1/";

    /**
     * Metodo unico que establece conexion con la api, recibe los lyrics y los estructura de forma correcta. Recibe los
     * parametros necesarios para poder hacer la llamada necesaria a la api
     * @param artist Artista de la cancion cuyos lyrics quieren obtenerse
     * @param song Nombre de la cancion cuyos lyrics quieren obtenerse
     * @return String con el formato correcto de los lyrics obtenidos
     */
    public String getSongLyrics (String artist, String song) {
        // Ponemos las frases en formato correcto
        artist = artist.replaceAll(" ", "%20");
        song = song.replaceAll(" ", "%20");

        // Añadimos a la url de la API los datos restante para que pueda encontra los lyrics
        String urlString = genericURL + artist + "/" + song;


        try {
            URL url;
            url = new URL(urlString);

            // Intentamos establecer conexion
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            try {
                connection.connect();
            } catch (ConnectException e) {
                return "API had an error trying to fetch song lyrics";
            }

            int responseCode = connection.getResponseCode();

            // Si la API ha encontrado los lyrics de la cancion, los leemos
            if (responseCode != 200) {
                return "Lyrics for the song could not be found";
            } else {
                StringBuilder info = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                // Leemos toda la info de la api y montamos un string
                while (scanner.hasNext()) {
                    info.append(scanner.nextLine());
                }

                // Colocamos los lyrics de la cancion en formato correcto, para que luego se puedan
                // leer con facilidad
                JsonObject jsonObject = (JsonObject) JsonParser.parseString(info.toString());
                String lyrics = String.valueOf(jsonObject.get("lyrics"));

                // Hacemos los cambios para rreglar el formato del texto
                lyrics = lyrics.replace("\\n\\n", System.lineSeparator());
                lyrics = lyrics.replace("\\n", System.lineSeparator());
                lyrics = lyrics.replace("\\r", "");
                System.out.println(lyrics);
                return lyrics;
            }
        } catch (IOException e) {
            // Todas las posibles excepciones ProtocolException y MalformedUrlExcepcion son subclases de IOException,
            // por lo que podemos hacer un catch de solo IOExcepcion
            return "API had an error trying to fetch song lyrics";
        }
    }

}

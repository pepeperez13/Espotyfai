package business.entities;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Clase que representa una cancion
 */
public class Song {
    private String title;
    private String genre;
    private String album;
    private String artist;
    private String owner;
    private String path;
    private int position;

    /**
     * Constructor de la clase
     * @param title titulo de la cancion
     * @param genre genero de la cancion
     * @param album album de la cancion
     * @param artist artista de la cancion
     * @param path ruta de la cancion
     * @param owner dueño de la cancion
     */
    public Song(String title, String genre, String album, String artist, String path, String owner) {
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.owner = owner;
        this.path = path;
    }

    /**
     * Obtiene el titulo de la cancion
     * @return titulo de la cancion
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtiene el genero de la cancion
     * @return genero de la cancion
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Obtiene el album al que pertenece la cancion
     * @return album al que pertenece la cancion
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Obtiene el artista de la cancion
     * @return artista de la cancion
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Obtiene quien es el dueno de la cancion
     * @return dueno de la cancion
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Obtiene el path donde se encuentra guardado la cancion
     * @return path donde se encuentra guardado la cancion
     */
    public String getPath() {
        return path;
    }

    /**
     * Obtiene la posicion de la cancion en la base de datos
     * @return posicion de la cancion en la base de datos
     */
    public int getPosition() {
        return position;
    }

    /**
     * Establece la posicion de la cancion
     * @param position posicion en la que se va a encontrar la cancion
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Metodo que, a traves del path de una cancion, calcula su duracion en segundos
     * @return duracion de la cancion en segundos
     */
    public double getSongDurationSeconds () {
        String error = "";
        AudioFormat format = null;
        long frames = 0;
        try {
            File file = new File(this.getPath());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            format = audioStream.getFormat();
            frames = audioStream.getFrameLength();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            error = "Audio file is not supported by the system";
            //JOptionPane.showMessageDialog(this, "Audio file is not supported by the system", "Following errors were found", JOptionPane.WARNING_MESSAGE);
        } catch (IOException ioException) {
            error = "Song provided could not be found";
            //JOptionPane.showMessageDialog(this, "Audio file is not supported by the system", "Following errors were found", JOptionPane.WARNING_MESSAGE);
        }

        return (frames + 0.0) / format.getFrameRate();
    }

    /**
     * Metodo que, a traves del path de una cancion, calcula su duracion en formato minutos:segundos
     * @return duracion de la cancion en minutos:segundos
     */
    public String getSongDurationMinutes () {
        double time  = getSongDurationSeconds();
        int minutes = (int) (time / (60));
        int seconds = (int) ((time) % 60);
        return String.format("%d:%02d", minutes, seconds);
    }

    /**
     * Obtiene en forma de string algunos datos de la cancion
     * @return String con info
     */
    @Override
    public String toString() {
        return title+ " - "+ artist;
    }
}

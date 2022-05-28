package business.entities;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Song {
    private String title;
    private String genre;
    private String album;
    private String artist;
    private String owner;
    private String path;
    private int position;

    public Song(String title, String genre, String album, String artist, String path, String owner) {
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.owner = owner;
        this.path = path;
    }

    public Song() {

    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getOwner() {
        return owner;
    }

    public String getPath() {
        return path;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

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

    public String getSongDurationMinutes () {
        double time  = getSongDurationSeconds();
        int minutes = (int) (time / (60));
        int seconds = (int) ((time) % 60);
        return String.format("%d:%02d", minutes, seconds);
    }

}

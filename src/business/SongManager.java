package business;
import business.entities.Song;
import business.entities.SongPlaylist;
import persistance.SongDAO;
import persistance.SongPlaylistDAO;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class SongManager<Public> {

    private static SongDAO songDAO = new SQLConnectorSong();
    private static SongPlaylistDAO songPDAO = new SQLConnectorSongPlaylist();


    public static void addSong (String title, String Genre, String album, String artist, String path,String owner) {
        songDAO.InsertDataSong(title, Genre, album, artist, path,owner);
    }
    public static LinkedList<Song> ListSongs() {
        songDAO = new SQLConnectorSong();
        LinkedList<Song> songs;
        songs = songDAO.SelectDataSong();
        System.out.println(songs);
        return songs;
    }
    public static void InsertNewSong(String title, String genre, String album, String artist, String path, String owner){
        songDAO = new SQLConnectorSong();

        songDAO.InsertDataSong(title, genre, album, artist, path, owner);
    }

    public static Song SelectSong(String Song) {
        songDAO = new SQLConnectorSong();
        Song songs;
        songs = songDAO.SelectSong(Song);

        System.out.println(songs.getGenre());

        return songs;
    }
    /*public static LinkedList<Song> SelectSong(String Song) {
        songDAO = new SQLConnectorSong();
        LinkedList<Song> song;
        song = songDAO.SelectDataSong();
        System.out.println(song);
        return song;
    }*/
    //Metodo que permite dado un path reproducir archivos de music a .wav
    public void PlayMusic(String path, int index) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        String error = "";

        Clip clip;
        try {
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            //Switch que controla si se le da a play, pause, se rebobina al principio o se para termina el proceso de reproduccion
            while(index !=4) {
                //Sistema solo de prueba para hacer funcionar la reproduccion, en un fururo se controlara mediante la interfaz grafica del sistema.

                switch (index) {
                    case (1):
                        clip.start();
                        break;
                    case (2):
                        clip.stop();
                        break;
                    case (3):
                        clip.setMicrosecondPosition(0);
                        break;
                    case (4):
                        clip.close();
                        break;

                }
            }
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            error = "Audio file is not supported by the system";
            //JOptionPane.showMessageDialog(this, "Audio file is not supported by the system", "Following errors were found", JOptionPane.WARNING_MESSAGE);
        } catch (IOException ioException) {
            error = "Song provided could not be found";
            //JOptionPane.showMessageDialog(this, "Audio file is not supported by the system", "Following errors were found", JOptionPane.WARNING_MESSAGE);
        } catch (LineUnavailableException lineUnavailableException) {
            error = "Unknown error occured when trying to reproduce the song";
            //JOptionPane.showMessageDialog(this, "Audio file is not supported by the system", "Following errors were found", JOptionPane.WARNING_MESSAGE);
        }


    }



}




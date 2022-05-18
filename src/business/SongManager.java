package business;
import business.entities.Song;
import persistance.SongDAO;
import persistance.dao.sql.SQLConnectorSong;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class SongManager<Public> {

    private static SongDAO songDAO = new SQLConnectorSong();



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

    public static LinkedList<Song> SelectSong(String Song) {
        songDAO = new SQLConnectorSong();
        LinkedList<Song> song;
        song = songDAO.SelectDataSong();
        System.out.println(song);
        return song;
    }
    //Metodo que permite dado un path reproducir archivos de music a .wav
    public static void PlayMusic(String path,int index) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        //sqlsong = new SQLConnectorSong();
        //LinkedList<Song> song1 = null;
        //sqlsong.SelectSong(name) = song1;



            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            int response = 0;

        //Switch que controla si se le da a play, pause, se rebobina al principio o se para termina el proceso de reproduccion
        while(response !=4) {
            System.out.println("Play (1) Button --- Stop (2) Button --- Reset (3) Button --- Exit");
        //Sistema solo de prueba para hacer funcionar la reproduccion, en un fururo se controlara mediante la interfaz grafica del sistema.



            switch(response) {
                case (1): clip.start();
                    break;
                case (2): clip.stop();
                    break;
                case (3): clip.setMicrosecondPosition(0);
                    break;
                case (4): clip.close();
                    break;

                default: System.out.println("----");
            }


        }

        }




}




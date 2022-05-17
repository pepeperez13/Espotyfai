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




    public void addSong (String title, String Genre, String album, String artist, String path,String owner) {
        songDAO.InsertDataSong(title, Genre, album, artist, path,owner);
    }
    public LinkedList<Song> ListSongs() {
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
    public static void PlayMusic(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        //sqlsong = new SQLConnectorSong();
        //LinkedList<Song> song1 = null;
        //sqlsong.SelectSong(name) = song1;


            Scanner scanner = new Scanner(System.in);
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            String response = "";

        //Switch que controla si se le da a play, pause, se rebobina al principio o se para termina el proceso de reproduccion
        while(!response.equals("E")) {
            System.out.println("Play (P) Button --- Stop (S) Button --- Reset (R) Button --- Exit");
        //Sistema solo de prueba para hacer funcionar la reproduccion, en un fururo se controlara mediante la interfaz grafica del sistema.
            response = scanner.next();
            response = response.toUpperCase();

            switch(response) {
                case ("P"): clip.start();
                    break;
                case ("S"): clip.stop();
                    break;
                case ("R"): clip.setMicrosecondPosition(0);
                    break;
                case ("Ee"): clip.close();
                    break;

                default: System.out.println("----");
            }


        }

        }




}




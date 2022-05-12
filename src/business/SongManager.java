package business;
import business.entities.Song;
import persistance.dao.sql.SQLConnectorSong;

import java.util.LinkedList;

public class SongManager {

    private SQLConnectorSong sqlsong;
    public SongManager (SQLConnectorSong sql) {
        this.sqlsong = sql;
    }
    public void addSong (String title, String Genre, String album, String artist, String path,String owner) {
        sqlsong.InsertDataSong(title, Genre, album, artist, path,owner);
    }
    public LinkedList<Song> ListSongs() {
        sqlsong = new SQLConnectorSong();
        LinkedList<Song> songs;
        songs = sqlsong.SelectDataSong();
        System.out.println(songs);
        return songs;
    }
}




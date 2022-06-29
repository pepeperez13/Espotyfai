package persistance;



import business.entities.Config;
import business.entities.Song;

import java.util.LinkedList;

public interface SongDAO {
    void InsertDataSong(String title,String genre,String album, String artist,String path,String owner);

    void DeleteDataSong(String title);

    LinkedList<Song> SelectDataSong();

    Song SelectSong(String song);

    Config GetDataBaseData();
}
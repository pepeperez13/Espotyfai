package persistance;



import business.entities.Song;

import java.util.LinkedList;

public interface SongDAO {
    public void InsertDataSong(String title,String genre,String album, String artist,String path,String owner);

    public void DeleteDataSong(String title);

    public void UpdateDataSong(String title1,String genre,String album, String artist,String path,String owner,String title2);

    public LinkedList<Song> SelectDataSong();

    public Song SelectSong(String song);
}
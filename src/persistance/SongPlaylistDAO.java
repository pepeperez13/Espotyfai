package persistance;

import business.entities.SongPlaylist;

import java.util.LinkedList;

public interface SongPlaylistDAO {
    public void InsertDataSongP(String title, String name);

    public void DeleteDataSongP(String title);

    //public void UpdateDataSongP(String title1,String name,String title2);

    public LinkedList<SongPlaylist> SelectDataSongP();

    public LinkedList<SongPlaylist> SelectSongsP(String name);

}

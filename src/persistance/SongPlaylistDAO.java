package persistance;

import business.entities.SongPlaylist;

import java.util.LinkedList;

public interface SongPlaylistDAO {
     void InsertDataSongP(String title, String name);

     void DeleteDataSongP(String title);

    //public void UpdateDataSongP(String title1,String name,String title2);

     LinkedList<SongPlaylist> SelectDataSongP();

     LinkedList<SongPlaylist> SelectSongsP(String name);

}

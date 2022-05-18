package business;

import business.entities.Song;
import business.entities.SongPlaylist;
import persistance.SongDAO;
import persistance.SongPlaylistDAO;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import java.util.LinkedList;


public class SongPlaylistManager<Public> {
    private static SongPlaylistDAO songPDAO;

    public static void InsertNewSongPlaylist(String title, String name) {
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.InsertDataSongP(title, name);
    }

    public void updateSongPlaylist(String title1,String name, String title2){
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.UpdateDataSongP(title1,name,title2);
    }

    public static LinkedList<SongPlaylist> SelectSongPlaylist(String SongPlaylist) {
        songPDAO = new SQLConnectorSongPlaylist();
        LinkedList<SongPlaylist> songPlaylist;
        songPlaylist = songPDAO.SelectDataSongP();
        System.out.println(songPlaylist);
        return songPlaylist;
    }



}

package business;


import business.entities.Song;
import business.entities.SongPlaylist;
import persistance.PlaylistDAO;
import persistance.SongDAO;
import persistance.SongPlaylistDAO;
import persistance.dao.sql.SQLConnectorPlaylist;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import java.util.LinkedList;
import java.io.*;




public class SongPlaylistManager<Public> {
    private static SongPlaylistDAO songPDAO;
    private static PlaylistDAO playlistDAO;
    private static SongDAO songDAO;

    public static void InsertNewSongPlaylist(String title, String name) {
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.InsertDataSongP(title, name);
    }
    public static void DeleteNewSongPlaylist(String title) {
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.DeleteDataSongP(title);
    }

    public void updateSongPlaylist(String title1,String name, String title2){
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.UpdateDataSongP(title1,name,title2);
    }

    public static LinkedList<Song> ListPlaylistSongs(String name){
        songPDAO = new SQLConnectorSongPlaylist();
        songDAO = new SQLConnectorSong();
        playlistDAO = new SQLConnectorPlaylist();
        LinkedList<SongPlaylist> PSongs;

        LinkedList<Song> songs = new LinkedList<Song>();

        PSongs = songPDAO.SelectSongsP(name);
        for(int i = 0; i < PSongs.size(); i++) {
            System.out.println(PSongs.get(i).getTitle());


            songs.add(songDAO.SelectSong(PSongs.get(i).getTitle())) ;


        }

        return songs;
    }





}

package business;


import business.entities.Playlist;
import business.entities.Song;
import business.entities.SongPlaylist;
import persistance.PlaylistDAO;
import persistance.SongDAO;
import persistance.SongPlaylistDAO;
import persistance.dao.sql.SQLConnectorPlaylist;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import java.util.LinkedList;


public class SongPlaylistManager<Public> {
    private static SongPlaylistDAO songPDAO;
    private static PlaylistDAO playlistDAO;
    private static SongDAO songDAO;
    private static PlaylistManager playlistManager;


    public static boolean InsertNewSongPlaylist(String songtitle, String playlistname) {
        songPDAO = new SQLConnectorSongPlaylist();
        playlistManager= new PlaylistManager();
        LinkedList<Playlist> playlists=playlistManager.getDataPlaylists();
        for(Playlist p: playlists){
            if(p.getName().equals(playlistname)){
                if(p.getOwner().equals(Store.getUser().getName())){
                    songPDAO.InsertDataSongP(songtitle, playlistname);
                    return true;
                }
            }
        }
        return false;
    }

    public  void deleteNewSongPlaylist(String title) {
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.DeleteDataSongP(title);
    }

   /* public void updateSongPlaylist(String title1,String name, String title2){
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.UpdateDataSongP(title1,name,title2);
    }*/

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

    public boolean songExistsInPlaylist (String songTitle, String playlistName) {
        LinkedList<Song> songs = ListPlaylistSongs(playlistName);
        boolean exists = false;

        try {
            for (Song song : songs) {
                if (song.getTitle().equals(songTitle)) {
                    exists = true;
                }
            }
        } catch (NullPointerException e) {
            exists = false;
        }
        return exists;
    }





}

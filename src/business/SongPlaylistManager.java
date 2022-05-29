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


    public boolean InsertNewSongPlaylist(String title, String name) throws  Exception{
        boolean exists = false;
        int lastPos = 0;
        playlistManager = new PlaylistManager();
        songPDAO = new SQLConnectorSongPlaylist();
        LinkedList<Playlist> playlists = playlistManager.getDataPlaylists();

        lastPos = songPDAO.getLastPos(name);

        for(Playlist p: playlists){
            if(p.getName().equals(name)){
                if(p.getOwner().equals(Owner.getUser().getName())){
                    songPDAO.InsertDataSongP(title, name, lastPos +1);
                    return true;
                }
            }
        }
        return false;


    }
    public LinkedList<Song> insertAllSongsinPlaylist(String playlistName) throws  Exception{
        LinkedList<Song> songs = new LinkedList<Song>();
        songs=ListPlaylistSongs(playlistName);
        for (Song song : songs) {
            if(!songExistsInPlaylist(song.getTitle(),playlistName)){
                InsertNewSongPlaylist(song.getTitle(), playlistName);
            }

        }
        return songs;
    }
    //metodo para quitar o eliminar una cancion de una playlist
    public void deleteSongPlaylistSong(String name,String title){
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.DeleteDataSongP(name,title);
    }
    public static void deleteSongP(String name, String title){
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.DeleteDataSongPpt3(title,name);
    }
    public static void test(int pos){
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.DeleteDataSongPpt2(pos);

    }


    public static int getLastPosition(String name){
        int pos = 0;
        songPDAO = new SQLConnectorSongPlaylist();
        pos  = songPDAO.getLastPos(name);
        return pos;
    }




   /* public void updateSongPlaylist(String title1,String name, String title2){
        songPDAO = new SQLConnectorSongPlaylist();
        songPDAO.UpdateDataSongP(title1,name,title2);
    }*/

    public  LinkedList<Song> ListPlaylistSongs(String name){
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
    public void updatePosP(String title, String name, int index){
        LinkedList<SongPlaylist> PSongs2 = null;
        songPDAO = new SQLConnectorSongPlaylist();
        int pos = 0;

        switch (index){
            case (1):

                pos = songPDAO.getPosP(title,name);
                System.out.println(pos);


                songPDAO.UpdatePosPExtraU(name,pos);
                songPDAO.updatePosP(title,name,pos - 1);
                break;
            case (2):
                pos = songPDAO.getPosP(title,name);
                System.out.println(pos);
                System.out.println("HOLASSAA");

                songPDAO.UpdatePosPExtraD(name,pos);
                songPDAO.updatePosP(title,name,pos + 1);
                break;
        }

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


package persistance;

import business.entities.SongPlaylist;

import java.util.LinkedList;

public interface SongPlaylistDAO {

     void InsertDataSongP(String title, String name, int pos);

     LinkedList<SongPlaylist> SelectSongsP(String name);

     int getLastPos(String name);

     int UpdatePosPExtraD (String name, int pos);

     int UpdatePosPExtraU (String name, int pos);

     int getPosP (String title, String name);

     LinkedList<SongPlaylist> updatePosP(String title, String name,int pos);

     void DeleteSongPFull(String title);

     void DeleteDataSongP(String name,String title);

     void DeleteDataSongPpt3(String name,String title);



}

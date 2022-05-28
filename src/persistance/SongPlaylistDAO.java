package persistance;

import business.entities.SongPlaylist;

import java.util.LinkedList;

public interface SongPlaylistDAO {
     void InsertDataSongP(String title, String name, int pos) throws Exception;

     //public void UpdateDataSongP(String title1,String name,String title2);

     LinkedList<SongPlaylist> SelectDataSongP();

     LinkedList<SongPlaylist> SelectSongsP(String name);
     public int getLastPos(String name);
     public int UpdatePosPExtraD (String name, int pos);
     public int UpdatePosPExtraU (String name, int pos);
     public int getPosP (String title, String name);
     public LinkedList<SongPlaylist> updatePosP(String title, String name,int pos);
     public void DeleteSongPFull(String title);
     public void DeleteDataSongP(String name,String title);
     public void DeleteDataSongPpt2(int pos);
     public void DeleteDataSongPpt3(String name,String title);



}

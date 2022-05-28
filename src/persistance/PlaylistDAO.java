package persistance;


import business.entities.Playlist;
import business.entities.User;

import java.util.LinkedList;

public interface PlaylistDAO {

     void InsertDataPlaylist(String name,String owner) throws Exception;

     void DeleteDataPlaylist(String name);

     LinkedList<Playlist> SelectPlaylistsOfUser(User user);

     void UpdateDataPlaylist(String name1,String owner, String name2);

     LinkedList<Playlist> SelectDataPlaylist();

}
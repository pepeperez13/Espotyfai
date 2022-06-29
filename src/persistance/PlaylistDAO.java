package persistance;


import business.entities.Playlist;
import business.entities.User;

import java.util.LinkedList;

public interface PlaylistDAO {

     void InsertDataPlaylist(String name,String owner);

     void DeleteDataPlaylist(String name);

     LinkedList<Playlist> SelectPlaylistsOfUser(User user);

     LinkedList<Playlist> SelectDataPlaylist();

}
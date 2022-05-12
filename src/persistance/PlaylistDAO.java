package persistance;


import business.entities.Playlist;

import java.util.LinkedList;

public interface PlaylistDAO {

    public void InsertDataPlaylist(String name,String owner);

    public void DeleteDataPlaylist(String name);

    public void UpdateDataPlaylist(String name1,String owner, String name2);

    public LinkedList<Playlist> SelectDataPlaylist();

}
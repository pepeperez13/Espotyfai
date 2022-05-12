package persistance;

import business.entities.Artist;

import java.util.LinkedList;

public interface ArtistDAO {
    public void InsertDataArtist(String name);

    public void DeleteDataArtist();

    public void UpdateDataArtist(String name1, String name2);

    public LinkedList<Artist> SelectDataArtist();
}

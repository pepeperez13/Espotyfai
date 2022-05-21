package business.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {
    private String name;
    private String owner;
    private List<Song> songs;

    public Playlist(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                '}';
    }
    
}

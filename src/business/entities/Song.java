package business.entities;

public class Song {
    private String title;
    private String genre;
    private String album;
    private String artist;
    private String owner;
    private String path;

    public Song(String title1, String s, String album, String artist, String title, String genre) {
        this.title = title;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.owner = owner;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getOwner() {
        return owner;
    }

    public String getPath() {
        return path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

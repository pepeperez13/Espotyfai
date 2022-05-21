package business.entities;

public class SongPlaylist {

    private String songTitle;
    private String playlistName;


    public SongPlaylist(String songTitle, String playlistName) {
        this.songTitle = songTitle;
        this.playlistName = playlistName;
    }
    public String getTitle() {
        return songTitle;
    }

}

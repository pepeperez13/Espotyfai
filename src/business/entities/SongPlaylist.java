package business.entities;

public class SongPlaylist {

    private String songTitle;
    private String playlistName;
    private int pos;

    public SongPlaylist(String songTitle, String playlistName,int pos) {
        this.songTitle = songTitle;
        this.playlistName = playlistName;
        this.pos = pos;
    }
    public String getTitle() {
        return songTitle;
    }

    public String getName() {
        return playlistName;
    }

}

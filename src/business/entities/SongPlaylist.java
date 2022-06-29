package business.entities;

/**
 * Clase que representa la relacion de una cancion con una playlist
 */
public class SongPlaylist {

    private String songTitle;
    private String playlistName;
    private int pos;

    /**
     * Constructor de la clase
     * @param songTitle titulo de la cancion
     * @param playlistName nombre de la playlist
     * @param pos posicion que tiene esa cancion en esa playlist
     */
    public SongPlaylist(String songTitle, String playlistName,int pos) {
        this.songTitle = songTitle;
        this.playlistName = playlistName;
        this.pos = pos;
    }

    /**
     * Obtiene el titula de la cancion
     * @return titulo de la cancion
     */
    public String getTitle() {
        return songTitle;
    }

    /**
     * Obtiene el nombre de una playlist
     * @return nombre de la playlist
     */
    public String getName() {
        return playlistName;
    }

}

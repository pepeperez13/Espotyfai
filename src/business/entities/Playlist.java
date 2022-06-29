package business.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que representa las Playlist de la aplicacion
 */
public class Playlist {
    private String name;
    private String owner;
    private List<Song> songs;

    /**
     * Constuctor de la clase
     * @param name nombre de la Playlist
     * @param owner dueño de la Playlist
     */
    public Playlist(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    /**
     * Constructor que permite incializar una playlist antes de tener sus datos
     */
    public Playlist() {
    }

    /**
     * Obtiene una lista con las canciones de una playlist
     * @return Lista de canciones de la playlist
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Establece cuales son las canciones de una playlist
     * @param songs lista de canciones a guardar
     */
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    /**
     * Obtiene el nombre de la playlist
     * @return nombre de la playlist
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la playlist
     * @param name nombre de la playlist
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene el dueno de la playlist
     * @return Nombre del dueno de la playlist
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Establece quien es el dueno de la playlist
     * @param owner Nombre del dueno de la playlist
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Obtiene la info de una playlist en formato string
     * @return info de la playlist
     */
    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                '}';
    }
    
}

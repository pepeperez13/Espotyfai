package business;

import business.entities.Song;

import java.util.LinkedList;
import java.util.Scanner;

public class BuscadorManager {
    private Song song;
    //private Conexion conexion;

    public LinkedList<Song> listaCancionesPrueba (LinkedList<Song> list_songs) {
        Song song1 = new Song("As it was", "Pop", "AsItWas", "Harry Styles", "path", "Aleserra");
        Song song2 = new Song("Bam Bam", "Pop-Rock", "BAMBAM", "Camila Cabello", "path", "Abraham");
        Song song3 = new Song("Heat Waves", "Electro-Pop", "HeatWaves", "Glass Animal", "path",  "Borja");
        Song song4 = new Song("Pantisyto", "Reggeaton", "ReggeaAlbum", "Feid", "path", "Pepe");
        Song song5 = new Song("Cayo la noche", "Reggeaton - Trap", "Cayo la night", "Quevedo, Bad Bunny", "path", "Lachner");

        list_songs.add(song1);
        list_songs.add(song2);
        list_songs.add(song3);
        list_songs.add(song4);
        list_songs.add(song5);

        for (int i = 0; i < list_songs.size(); i++) {
            System.out.println(list_songs.get(i).getTitle() + "\n");
            System.out.println(list_songs.get(i).getArtist() + "\n");
        }
        return list_songs;
    }

    public Song buscarCancion (String song_name) {
        LinkedList<Song> list_songs = new LinkedList<>();

        Song cancion_encontrada = null;
        Scanner scanner = new Scanner(System.in);
        list_songs = listaCancionesPrueba(list_songs);

        System.out.println("Que cancion quieres buscar? ");
        String buscador = song_name;
        System.out.println(song_name);

        int posicion_song = 0;
        boolean found = false;
        for (int i = 0; i < list_songs.size(); i++) {
            if (buscador.equals(list_songs.get(i).getTitle())) {
                posicion_song = i;
                found = true;
            }
        }
        if (!found) {
            System.out.println("La cancion que buscas no se encuentra actualmente en el sistema. \n");
            cancion_encontrada = null;
        } else {
            if (found) {
                System.out.println("Cancion encontrada! :) \n");
                System.out.println(list_songs.get(posicion_song).getTitle());
                System.out.println(list_songs.get(posicion_song).getArtist());
                System.out.println(list_songs.get(posicion_song).getGenre());
                System.out.println(list_songs.get(posicion_song).getOwner());
                System.out.println(list_songs.get(posicion_song).getAlbum());

                cancion_encontrada = new Song(list_songs.get(posicion_song).getTitle(), list_songs.get(posicion_song).getGenre(), list_songs.get(posicion_song).getAlbum(), list_songs.get(posicion_song).getArtist(), list_songs.get(posicion_song).getPath(), list_songs.get(posicion_song).getOwner());

            }
        }
        return cancion_encontrada;
    }
}

package presentation.view;

import business.entities.Song;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;

public class MainView extends JPanel {

    public MainView(){
        JTable songs_table;
        Song song;
        //MODEL
        File carpeta = new File("songs");
        File[] lista = carpeta.listFiles();
        //System.out.println("\n Hay " +  lista.length + " elementos");

        File canciones = carpeta.getAbsoluteFile();

        // Data to be displayed in the JTable
        String[][] data = {
                { "1", "As it was", "Harry Styles", "pop-rock", "3,35" },
                { "2", "As it was", "Harry Styles", "pop-rock", "3,35" },
                { "3", "As it was", "Harry Styles", "pop-rock", "3,35" },
                { "4", "As it was", "Harry Styles", "pop-rock", "3,35" },

        };

        //Prueba ArrayList Songs
        LinkedList<Song> list_songs = new LinkedList<Song>();
        Song song1 = new Song("As it was", "Pop", "AsItWas", "Harry Styles", "path", "Aleserra");
        Song song2 = new Song("Bam Bam", "Pop-Rock", "BAMBAM", "Camila Cabello", "path", "Abraham");
        Song song3 = new Song("Heat Waves", "Electro-Pop", "HeatWaves", "Glass Animal", "path","Borja");
        Song song4 = new Song("Pantisyto", "Reggeaton", "ReggeaAlbum", "Feid", "path","Pepe");
        Song song5 = new Song("Cayo la noche", "Reggeaton - Trap", "Cayo la night", "path","Quevedo, Bad Bunny", "Lachner");

        list_songs.add(song1);
        list_songs.add(song2);
        list_songs.add(song3);
        list_songs.add(song4);
        list_songs.add(song5);

        //String[][] table_list_of_songs = list_songs.toArray();

        //Pasamos a String[]
        String[][] lista_string = new String[0][];

        for (int i = 0; i < list_songs.size(); i++) {
            lista_string[i][0] = (list_songs.get(i)).getTitle();
            lista_string[i][1] = (list_songs.get(i)).getGenre();
            lista_string[i][2] = (list_songs.get(i)).getAlbum();
            lista_string[i][3] = (list_songs.get(i)).getArtist();
            lista_string[i][4] = (list_songs.get(i)).getOwner();
        }


        //Columnas
        String[] columnNames = { "Title", "Genre", "Album", "Artist", "Owner" };

        // Initializing the JTable
        songs_table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(songs_table);
        //songs_table.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane sp = new JScrollPane(songs_table);

        //PANELES
        JPanel home_panel = new JPanel();
        JPanel songs_list = new JPanel(new BorderLayout());

        //BORDES HOME
        Border borde_home_panel = new TitledBorder(new EtchedBorder(), "HOME ");
        home_panel.setBorder(borde_home_panel);

        Border borde_lista_canciones_home = new TitledBorder(new EtchedBorder(), "CANCIONES: ");
        songs_list.setBorder(borde_lista_canciones_home);

        songs_list.add(sp, BorderLayout.CENTER);
        songs_list.add(songs_table.getTableHeader(), BorderLayout.NORTH);
        songs_list.add(songs_table, BorderLayout.CENTER);

        home_panel.add(songs_list, BorderLayout.CENTER);

        this.add(home_panel, BorderLayout.CENTER);
    }

}

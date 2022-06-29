package presentation.view;

import business.SongManager;
import business.entities.Song;
import presentation.controller.SongsTableController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.LinkedList;

/**
 * Clase que muestra las canciones disponibles en una jtable
 */
public class SongsTableView extends JPanel {
    private LinkedList<Song> lista_canciones_programa;
    private SongsTableController songsTableController;

    /**
     * Constructor que configura un JTable con las canciones
     */
    public SongsTableView (MainManagerView mainManagerView, DetailedSongView detailedSongView){
        JTable songs_table;

        this.setBackground(new Color(255, 255, 255));

        //Cogemos la lista de las canciones dentro del programa
        lista_canciones_programa = cargarLista();

        //Pasamos a String[]
        String[][] lista_string = new String[lista_canciones_programa.size()][5];

        for (int i = 0; i < lista_canciones_programa.size(); i++) {
            if (lista_canciones_programa.get(i) == null) {

            }else {
                lista_string[i][0] = (lista_canciones_programa.get(i)).getTitle();
                lista_string[i][1] = (lista_canciones_programa.get(i)).getGenre();
                lista_string[i][2] = (lista_canciones_programa.get(i)).getAlbum();
                lista_string[i][3] = (lista_canciones_programa.get(i)).getArtist();
                lista_string[i][4] = (lista_canciones_programa.get(i)).getOwner();
            }
        }

        //Columnas
        String[] columnNames = { "Title", "Genre", "Album", "Artist", "Owner" };

        // Initializing the JTable
        songs_table = new JTable(lista_string, columnNames);
        songs_table.setBackground(new Color(255, 255,255));
        //songs_table.setPreferredSize(new Dimension(1200, 600));
        songsTableController = new SongsTableController(songs_table, mainManagerView, detailedSongView);
        songs_table.getSelectionModel().addListSelectionListener(songsTableController);

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

    /**
     * Método que retorna las canciones del sistema
     * @return LinkedList
     */
    public LinkedList<Song> cargarLista() {
        LinkedList<Song> lista_canciones = new LinkedList<>();

        lista_canciones = SongManager.ListSongs();

        return lista_canciones;
    }

}

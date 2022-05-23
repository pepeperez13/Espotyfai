package presentation.view;

import business.entities.Song;
import presentation.controller.DetailedSongController;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuscadorView extends JPanel {
    public static final String SEARCH_SONG = "SEARCH_SONG";
    public static final String DETAIL_SONG = "DETAIL_SONG";
    private JButton boton_buscar, details_song_button;
    private JLabel title_text, artist_text, album_text, genre_text, owner_text;
    private JLabel info_title_song, info_artist_song, info_genre_song, info_album_song, info_owner_song;
    private JLabel mensaje_resultados;
    private JTextField buscador_text_field;
    private JPanel cardPanel = new JPanel();
    private DetailedSongView detailedSongView;
    private DetailedSongController detailedSongController;
    private CardLayout c = new CardLayout();

    public BuscadorView () {
        //AJUSTES PRINCIPALES DEL FRAME
        /*this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMaximumSize(new Dimension(1500, 900));
        this.setMinimumSize(new Dimension(900, 500));
        this.setTitle("Espotifai - Buscar");
        this.setLocationRelativeTo(null);*/
        this.setLayout(new BorderLayout());
        this.setBackground(Color.PINK.brighter());
        //this.setOpaque(true);

        JPanel panel_buscador_general = new JPanel(new BorderLayout(2, 1));
        JPanel barra_buscador_panel = new JPanel(new GridBagLayout());
        JPanel panel_lista_canciones_buscador = new JPanel();
        GridBagConstraints bp = new GridBagConstraints();

        panel_buscador_general.setBackground(Color.PINK.brighter());
        barra_buscador_panel.setBackground(Color.PINK.brighter());
        panel_buscador_general.setBackground(Color.PINK.brighter());

        //Borde panel buscador
        Border borde_panel_buscador = new TitledBorder(new EtchedBorder(), "BUSCAR CANCIONES ");
        panel_buscador_general.setBorder(borde_panel_buscador);

        Border borde_lista_canciones_buscador = new TitledBorder(new EtchedBorder(), "RESULTADOS: ");
        panel_lista_canciones_buscador.setBorder(borde_lista_canciones_buscador);

        Border borde_barra_buscador = new TitledBorder(new EtchedBorder(), "BUSCADOR ");
        barra_buscador_panel.setBorder(borde_barra_buscador);

        //COMPONENTES BARRA BUSCADOR
        JLabel barra_buscador_label = new JLabel("Buscar: ");
        bp.fill = GridBagConstraints.HORIZONTAL;
        bp.anchor = GridBagConstraints.PAGE_START; //bottom of space
        bp.weightx = 0.0;
        bp.gridx = 0;
        bp.gridy = 0;
        barra_buscador_panel.add(barra_buscador_label, bp);

        buscador_text_field = new JTextField();
        bp.fill = GridBagConstraints.HORIZONTAL;
        bp.ipady = 0;       //reset to default
        bp.gridwidth = 2;
        bp.weightx = 2;
        bp.gridx = 1;
        bp.gridy = 0;
        barra_buscador_panel.add(buscador_text_field, bp);

        boton_buscar = new JButton("Buscar");
        bp.fill = GridBagConstraints.HORIZONTAL;
        bp.weightx = 0.5;
        bp.ipady = 0;       //reset to default
        bp.gridx = 3;
        bp.gridy = 0;
        barra_buscador_panel.add(boton_buscar, bp);
        boton_buscar.setActionCommand(SEARCH_SONG);

        // PANEL RESULTADOS
        JPanel info_caciones = new JPanel(new GridBagLayout());
        GridBagConstraints sd = new GridBagConstraints();

        //COMPONENTES BARRA RESULTADOS BUSQUEDA
        info_title_song = new JLabel("Title: ");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 0;
        sd.gridy = 0;
        info_caciones.add(info_title_song, sd);

        title_text = new JLabel("--------");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 1;
        sd.gridy = 0;
        info_caciones.add(title_text, sd);

        info_artist_song = new JLabel("Artist: ");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 0;
        sd.gridy = 1;
        info_caciones.add(info_artist_song, sd);

        artist_text = new JLabel("--------");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 1;
        sd.gridy = 1;
        info_caciones.add(artist_text, sd);

        info_album_song = new JLabel("Album: ");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 0;
        sd.gridy = 2;
        info_caciones.add(info_album_song, sd);

        album_text = new JLabel("--------");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 1;
        sd.gridy = 2;
        info_caciones.add(album_text, sd);

        info_genre_song = new JLabel("Genre: ");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 0;
        sd.gridy = 3;
        info_caciones.add(info_genre_song, sd);

        genre_text = new JLabel("--------");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 1;
        sd.gridy = 3;
        info_caciones.add(genre_text, sd);

        info_owner_song = new JLabel("Owner: ");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 0;
        sd.gridy = 4;
        info_caciones.add(info_owner_song, sd);

        owner_text = new JLabel("--------");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 1;
        sd.gridy = 4;
        info_caciones.add(owner_text, sd);

        details_song_button = new JButton("Detalles");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.anchor = GridBagConstraints.PAGE_END; //bottom of space
        sd.weightx = 0.0;
        sd.gridx = 2;
        sd.gridy = 5;
        info_caciones.add(details_song_button, sd);
        details_song_button.setActionCommand(DETAIL_SONG);


        mensaje_resultados = new JLabel("Busque una cancion");
        sd.fill = GridBagConstraints.HORIZONTAL;
        sd.weightx = 0.0;
        sd.gridx = 1;
        sd.gridy = 6;
        info_caciones.add(mensaje_resultados, sd);

        panel_lista_canciones_buscador.add(info_caciones, BorderLayout.CENTER);

        //ADICIONES

        panel_buscador_general.add(barra_buscador_panel, BorderLayout.NORTH);
        panel_buscador_general.add(panel_lista_canciones_buscador);

        add(panel_buscador_general, BorderLayout.CENTER);
        //setVisible(true);
    }

    public void registerController(ActionListener listener) {
        boton_buscar.addActionListener(listener);
        details_song_button.addActionListener(listener);
    }

    public String getSearchSong () {
        return buscador_text_field.getText();
    }

    private void mostrarInformacionCancion (Song cancion_encontrada) {
        info_title_song.setText(cancion_encontrada.getTitle());
        info_artist_song.setText(cancion_encontrada.getArtist());
        info_album_song.setText(cancion_encontrada.getAlbum());
        info_genre_song.setText(cancion_encontrada.getGenre());
        info_owner_song.setText(cancion_encontrada.getOwner());
    }

    public void setSearch(Song cancion_encontrada) {
        if (cancion_encontrada == null) {
            mensaje_resultados.setText("No se ha encontrado la cancion buscada :(");
            title_text.setText("-----");
            artist_text.setText("-----");
            album_text.setText("-----");
            genre_text.setText("-----");
            owner_text.setText("-----");
        } else {
            mensaje_resultados.setText("Cancion encontrada en el sistema :)");
            title_text.setText(cancion_encontrada.getTitle());
            artist_text.setText(cancion_encontrada.getArtist());
            album_text.setText(cancion_encontrada.getAlbum());
            genre_text.setText(cancion_encontrada.getGenre());
            owner_text.setText(cancion_encontrada.getOwner());
        }
    }


}

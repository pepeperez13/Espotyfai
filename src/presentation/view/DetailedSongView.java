package presentation.view;

import business.LyricsFetcher;
import business.entities.Playlist;
import business.entities.Song;
import presentation.controller.DetailedSongController;
import presentation.controller.ShowPlaylistsController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Clase que se encarga de disenar y mostrar los distintos componentes para ver la información detallada de una canción
 * @author Jose Perez
 */
public class DetailedSongView extends JPanel {

    // Somos conscientes que no deberiamos tener la entidad en la vista que la muestra, pero por el diseño del
    // programa (mejorable) no hemos podido eliminarla
    private Song song;

    private JLabel titleAndAuthor;
    private JPanel generalData;
    private JButton closeButton;

    private JLabel genreSpecificTitle;
    private JLabel albumSpecificTitle;
    private JLabel ownerSpecificTitle;
    private JLabel durationSpecificTitle;
    private JTextArea lyricsTextArea;

    private static final String ADD_TO_PLAYLIST_COMMAND = "ADD_TO_PLAYLIST_COMMAND";
    private static final String PLAY_SONG_COMMAND = "PLAY_SONG_COMMAND";
    private static final String PAUSE_SONG_COMMAND = "PAUSE_SONG_COMMAND";
    private static final String CLOSE_PANEL_COMMAND = "CLOSE_PANEL_COMMAND";
    private final DetailedSongController controller;
    private ShowPlaylistsController showPlaylistsController;
    private LyricsFetcher lyricsFetcher;

    /**
     * Método constructor que crea los componentes de la clase e incializa otras clases que puedan ser necesarias
     */
    public DetailedSongView () {
        controller = new DetailedSongController(this);
        lyricsFetcher = new LyricsFetcher();

        setLayout(new BorderLayout(0, 50));

        configureTitleAndAuthor();
        configureGeneralData();
        add(configureCloseButton(), BorderLayout.SOUTH);

    }

    /**
     * Configura la parte superior de la vista, mostrando titulo y nombre de la cancion
     */
    private void configureTitleAndAuthor () {
        titleAndAuthor = new JLabel();
        titleAndAuthor.setPreferredSize(new Dimension(100, 60));
        titleAndAuthor.setFont(new Font("Tahoma", Font.BOLD, 36));
        titleAndAuthor.setHorizontalAlignment(JLabel.CENTER);
        titleAndAuthor.setBackground(new Color(52, 166, 244));
        titleAndAuthor.setForeground(Color.BLUE);
        titleAndAuthor.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        titleAndAuthor.setFocusable(false);
    }

    /**
     * Configura la parte central de la vista, mostrando toda la información restante y los controles de play/pause y
     * anadir a playlist
     */
    private void configureGeneralData () {
        generalData = new JPanel();
        generalData.setLayout(new GridLayout(3, 2));

        generalData.add(configureGenrePanel());
        generalData.add(configureAlbumPanel());
        generalData.add(configureOwnerPanel());
        generalData.add(configureDurationPanel());
        generalData.add(configureLyricsPanel());
        generalData.add(configureInteractionsPanel());

    }

    /*
     * Pese a que los siguientes 4 metodos 4 paneles con la misma estructura, lo único que cambia es el contenido, he
     * tenido que hacerlo en 4 metodos diferentes para no tener que crear los componentes de nuevo cada vez que se actualizan
     * y tenerlos como atributos de la clase. Antes había un método que construía un panel con información que recibía
     * por parámetros, pero debido a unas recomendaciones del profesor, tuve que cambiarlo, repitiendo así más código
     */

    /**
     * Configura el panel del genero de la cancion
     * @return panel configurado
     */
    private JPanel configureGenrePanel() {
        // Componentes que deben ser creados, y posteriormente actualizados (nos ahorramos crear nuevos componentes cada
        // vez que se actualizan
        JPanel genreInfoPanel = new JPanel(new GridLayout(1, 2));
        JLabel genreGeneralTitle = new JLabel("Genre: ");
        genreGeneralTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        genreGeneralTitle.setHorizontalAlignment(JLabel.CENTER);
        genreSpecificTitle = new JLabel();
        genreSpecificTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        genreSpecificTitle.setHorizontalAlignment(JLabel.LEFT);
        genreInfoPanel.add(genreGeneralTitle);
        genreInfoPanel.add(genreSpecificTitle);
        return genreInfoPanel;
    }

    /**
     * Configura el panel del album de la cancion
     * @return panel configurado
     */
    private JPanel configureAlbumPanel() {
        JPanel albumInfoPanel = new JPanel(new GridLayout(1, 2));
        JLabel albumGeneralTitle = new JLabel("Album: ");
        albumGeneralTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        albumGeneralTitle.setHorizontalAlignment(JLabel.CENTER);
        albumSpecificTitle = new JLabel();
        albumSpecificTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        albumSpecificTitle.setHorizontalAlignment(JLabel.LEFT);
        albumInfoPanel.add(albumGeneralTitle);
        albumInfoPanel.add(albumSpecificTitle);
        return albumInfoPanel;
    }

    /**
     * Configura el panel del onwer de la cancion
     * @return panel configurado
     */
    private JPanel configureOwnerPanel () {
        JPanel ownerInfoPanel = new JPanel(new GridLayout(1, 2));
        JLabel ownerGeneralTitle = new JLabel("Owner: ");
        ownerGeneralTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        ownerGeneralTitle.setHorizontalAlignment(JLabel.CENTER);
        ownerSpecificTitle = new JLabel();
        ownerSpecificTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ownerSpecificTitle.setHorizontalAlignment(JLabel.LEFT);
        ownerInfoPanel.add(ownerGeneralTitle);
        ownerInfoPanel.add(ownerSpecificTitle);

        return ownerInfoPanel;
    }

    /**
     * Configura el panel de la duracion de la cancion
     * @return panel configurado
     */
    private JPanel configureDurationPanel () {
        JPanel durationInfoPanel = new JPanel(new GridLayout(1, 2));
        JLabel durationGeneralTitle = new JLabel("Duration: ");
        durationGeneralTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        durationGeneralTitle.setHorizontalAlignment(JLabel.CENTER);
        durationSpecificTitle = new JLabel();
        durationSpecificTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
        durationSpecificTitle.setHorizontalAlignment(JLabel.LEFT);
        durationInfoPanel.add(durationGeneralTitle);
        durationInfoPanel.add(durationSpecificTitle);

        return durationInfoPanel;
    }

    /**
     * Configura el panel de los lyrics de la cancion
     * @return panel configurado
     */
    private JPanel configureLyricsPanel () {
        JPanel lyricsPanel = new JPanel(new GridLayout(1, 2));

        JLabel lyricsGeneralTitle = new JLabel("Lyrics: ");
        lyricsGeneralTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lyricsGeneralTitle.setHorizontalAlignment(JLabel.CENTER);

        lyricsTextArea = new JTextArea();
        JScrollPane lyricsScrollPane = new JScrollPane(lyricsTextArea);
        lyricsTextArea.setEditable(false);
        lyricsTextArea.setFont(new Font("Tahoma", Font.PLAIN, 16));

        lyricsFetcher = new LyricsFetcher();

        lyricsPanel.add(lyricsGeneralTitle);
        lyricsPanel.add(lyricsScrollPane);

        return lyricsPanel;
    }

    /**
     * Configura el panel que contiene los botones para anadir cancion a playlist, play y pause
     * @return panel configurado
     */
    private JPanel configureInteractionsPanel () {
        JPanel interactionsGeneralPanel = new JPanel();
        interactionsGeneralPanel.setLayout(new BoxLayout(interactionsGeneralPanel, BoxLayout.Y_AXIS));

        JButton addToPlaylistButton = new JButton("         Add to playlist         ");
        addToPlaylistButton.setPreferredSize(new Dimension(100, 60));
        addToPlaylistButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        addToPlaylistButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToPlaylistButton.setBackground(new Color(52, 166, 244));
        addToPlaylistButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        addToPlaylistButton.setFocusable(false);
        addToPlaylistButton.setActionCommand(ADD_TO_PLAYLIST_COMMAND);
        addToPlaylistButton.addActionListener(controller);

        interactionsGeneralPanel.add(addToPlaylistButton);

        JPanel playPausePanel = new JPanel();
        playPausePanel.setLayout(new GridLayout(1, 2));

        JButton playButton = new JButton("         Play         ");
        playButton.setPreferredSize(new Dimension(100, 60));
        playButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setBackground(Color.GREEN);
        playButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        playButton.setFocusable(false);
        playButton.setActionCommand(PLAY_SONG_COMMAND);
        playButton.addActionListener(controller);

        JButton pauseButton = new JButton("         Pause         ");
        pauseButton.setPreferredSize(new Dimension(100, 60));
        pauseButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseButton.setBackground(Color.RED);
        pauseButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        pauseButton.setFocusable(false);
        pauseButton.setActionCommand(PAUSE_SONG_COMMAND);
        pauseButton.addActionListener(controller);

        playPausePanel.add(playButton);
        playPausePanel.add(pauseButton);

        interactionsGeneralPanel.add(playPausePanel);

        return interactionsGeneralPanel;
    }

    /**
     * Configura el boton de la parte inferior de la pantalla, que permite cerrar la vista detallada
     * @return boton configurado
     */
    private JButton configureCloseButton() {
        closeButton = new JButton("Close song info");
        closeButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        closeButton.setBackground(Color.GRAY);
        closeButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        closeButton.setFocusable(false);
        closeButton.setActionCommand(CLOSE_PANEL_COMMAND);
        closeButton.addActionListener(controller);

        return closeButton;
    }

    /**
     * Metodo que es llamado cada vez que se necesita actualizar la informacion que se muestra en la vista detallada.
     * Elimina lo previamente anadido, cambia el texto de los componentes y vuelve a anadir los componentes
     * @param song nueva cancion que se debe mostrar
     */
    public void updateSong (Song song)  {
        this.song = song;

        if (titleAndAuthor != null) {
            remove(titleAndAuthor);
            remove(generalData);
        }

        titleAndAuthor = updateTitleAndAuthor(song);
        generalData = updateGeneralData(song);

        add(titleAndAuthor, BorderLayout.NORTH);
        add(generalData, BorderLayout.CENTER);

    }

    /**
     * Actualiza el titulo y autor de la cancion a mostrar
     * @param song cancion a mostrar
     * @return label actualizado
     */
    private JLabel updateTitleAndAuthor(Song song) {
        titleAndAuthor.setText(song.getTitle() + " - " + song.getArtist());
        return titleAndAuthor;
    }

    /**
     * Actualiza la informacion general de la cancion a mostrar
     * @param song cancion a mostrar
     * @return panel configurado
     */
    private JPanel updateGeneralData(Song song) {
        genreSpecificTitle.setText(song.getGenre());
        albumSpecificTitle.setText(song.getAlbum());
        ownerSpecificTitle.setText(song.getOwner());
        durationSpecificTitle.setText(song.getSongDurationMinutes());
        lyricsTextArea.setText(lyricsFetcher.getSongLyrics(song.getArtist(), song.getTitle()));

        return generalData;
    }

    /**
     * Muestra optionPane si la cancion que se quiere anadir a una playlist ya esta incluida
     */
    public void showErrorMessage () {
        String message = "Song is already contained in selected playlist and can not be added again.";
        JOptionPane.showMessageDialog(this, message, "Following errors were found", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Muestra optionPane si la cancion se ha anadido correctamente a la playlist
     */
    public void showOKMessage() {
        String message = "Song was added to the playlist successfully.";
        JOptionPane.showMessageDialog(this, message, "", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Muestra optionPane si se intenta anadir cancion a una playlist de la que no somos duenos
     */
    public void showErrorUserMessage() {
        String message = "Cannot add song to a playlist of which you are not the owner.";
        JOptionPane.showMessageDialog(this, message, "Following errors were found", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Permite obtener el titulo de la cancion que se muestra
     * @return
     */
    public String getSongTitle() {
        return song.getTitle();
    }

    /**
     * Configura un optionPane que muestra las playlist existentes, que se abre en cuando el usuario le da al boton
     * de "add to playlist"
     */
    public void showPlaylists () {
        JPanel playlistsPanel = new JPanel();
        showPlaylistsController = new ShowPlaylistsController(this);

        Box box= Box.createVerticalBox();
        LinkedList<Playlist> playlists = controller.getDataPlaylists();

        for (Playlist playlist : playlists) {
            JButton button = new JButton(playlist.getName());
            button.setFont(new Font("Tahoma", Font.PLAIN, 14));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFocusable(false);
            button.setActionCommand(playlist.getName());
            button.addActionListener(showPlaylistsController);
            box.add(button);
        }

        JScrollPane scroll= new JScrollPane(box);
        playlistsPanel.add(scroll);
        JOptionPane.showMessageDialog(this, playlistsPanel, "Select a playlist to add the song", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Permite obtener la cancion que se esta mostrando
     * @return cancion que se muestra
     */
    public Song getSong () {
        return song;
    }

}


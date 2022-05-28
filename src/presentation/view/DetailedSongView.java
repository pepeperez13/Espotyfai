package presentation.view;

import business.LyricsFetcher;
import business.entities.Playlist;
import business.entities.Song;
import presentation.controller.DetailedSongController;
import presentation.controller.ShowPlaylistsController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class DetailedSongView extends JPanel {

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
    private DetailedSongController controller;
    private ShowPlaylistsController showPlaylistsController;
    private LyricsFetcher lyricsFetcher;


    public DetailedSongView () {
        controller = new DetailedSongController(this);
        lyricsFetcher = new LyricsFetcher();

        setLayout(new BorderLayout(0, 50));

        // Creamos y configuramos los componentes de la vista
        configureTitleAndAuthor();
        configureGeneralData();
        add(configureCloseButton(), BorderLayout.SOUTH);

    }

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


    public void updateSong (Song song)  {
        this.song = song;

        if (titleAndAuthor != null) {
            remove(titleAndAuthor);
            remove(generalData);
        }

        titleAndAuthor = updateTitleAndAuthor();
        generalData = updateGeneralData();

        add(titleAndAuthor, BorderLayout.NORTH);
        add(generalData, BorderLayout.CENTER);

    }

    private JLabel updateTitleAndAuthor() {
        titleAndAuthor.setText(song.getTitle() + " - " + song.getArtist());
        return titleAndAuthor;
    }

    private JPanel updateGeneralData() {
        genreSpecificTitle.setText(song.getGenre());
        albumSpecificTitle.setText(song.getAlbum());
        ownerSpecificTitle.setText(song.getOwner());
        durationSpecificTitle.setText(song.getSongDurationMinutes());
        lyricsTextArea.setText(lyricsFetcher.getSongLyrics(song.getArtist(), song.getTitle()));

        return generalData;
    }

    public void showErrorMessage () {
        String message = "Song is already contained in selected playlist and can not be added again.";

        JOptionPane.showMessageDialog(this, message, "Following errors were found", JOptionPane.WARNING_MESSAGE);
    }

    public void showOKMessage() {
        String message = "Song was added to the playlist successfully.";

        JOptionPane.showMessageDialog(this, message, "", JOptionPane.PLAIN_MESSAGE);
    }
    public void showErrorUserMessage() {
        String message = "Cannot add song to a playlist of which you are not the owner.";

        JOptionPane.showMessageDialog(this, message, "Following errors were found", JOptionPane.WARNING_MESSAGE);
    }

    public String getSongTitle() {
        return song.getTitle();
    }

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

    public Song getSong () {
        return song;
    }

}


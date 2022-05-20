package presentation.view.detailedSong;

import business.LyricsFetcher;
import business.entities.Playlist;
import business.entities.Song;
import presentation.controller.DetailedSongController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class DetailedSongView extends JFrame {

    private Song song;
    private Playlist chosenPlaylist;

    private String title;
    private String genre;
    private String album;
    private String artist;
    private String owner;
    private String path;

    private static final String ADD_TO_PLAYLIST_COMMAND = "ADD_TO_PLAYLIST_COMMAND";
    private static final String PLAY_SONG_COMMAND = "PLAY_SONG_COMMAND";
    private static final String PAUSE_SONG_COMMAND = "PAUSE_SONG_COMMAND";
    private static final String CLOSE_PANEL_COMMAND = "CLOSE_PANEL_COMMAND";
    private DetailedSongController controller;
    private ShowPlaylistsController showPlaylistsController;
    private LyricsFetcher lyricsFetcher;


    public DetailedSongView () {
        controller = new DetailedSongController(this);
        setLocation(0,10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1500, 900);
        setResizable(true);

        setLayout(new BorderLayout(0, 50));

        add(setTitleAndAuthor("The Time", "The Black Eyed Peas"), BorderLayout.NORTH);
        add(setGeneralData("Rock", "Albumsito", "Yoyo", "3:24"), BorderLayout.CENTER);
        add(setCloseLabel(), BorderLayout.SOUTH);

    }

    private JLabel setTitleAndAuthor (String title, String author) {
        JLabel text = new JLabel(title + " - " + author);
        text.setPreferredSize(new Dimension(100, 60));
        text.setFont(new Font("Tahoma", Font.BOLD, 36));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setBackground(new Color(52, 166, 244));
        text.setForeground(Color.BLUE);
        text.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        text.setFocusable(false);
        return text;
    }

    private JPanel setGeneralData (String genre, String album, String owner, String duration) {
        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new GridLayout(3, 2));

        generalPanel.add(setInfoPanel("Genre", genre));
        generalPanel.add(setInfoPanel("Album", album));
        generalPanel.add(setInfoPanel("Owner", owner));
        generalPanel.add(setInfoPanel("Duration", duration));
        generalPanel.add(setLyricsPanel());
        generalPanel.add(setInteractionsPanel());


        return generalPanel;
    }

    private JPanel setInfoPanel (String title, String content) {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JLabel generalTitle = new JLabel(title);
        generalTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        generalTitle.setHorizontalAlignment(JLabel.CENTER);
        JLabel specificLabel = new JLabel(content);
        specificLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        specificLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(generalTitle);
        panel.add(specificLabel);

        return panel;
    }

    private JPanel setLyricsPanel () {
        JPanel lyricsPanel = new JPanel(new GridLayout(1, 2));

        JLabel generalTitle = new JLabel("Lyrics");
        generalTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        generalTitle.setHorizontalAlignment(JLabel.CENTER);

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));

        lyricsFetcher = new LyricsFetcher();
        textArea.setText(lyricsFetcher.getSongLyrics("Black Eyed Peas", "The Time"));

        lyricsPanel.add(generalTitle);
        lyricsPanel.add(scrollPane);
        return lyricsPanel;
    }

    private JPanel setInteractionsPanel () {

        JPanel generalPanel = new JPanel();
        generalPanel.setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));

        JButton playlistButton = new JButton("         Add to playlist         ");
        playlistButton.setPreferredSize(new Dimension(100, 60));
        playlistButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        playlistButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playlistButton.setBackground(new Color(52, 166, 244));
        playlistButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        playlistButton.setFocusable(false);
        playlistButton.setActionCommand(ADD_TO_PLAYLIST_COMMAND);
        playlistButton.addActionListener(controller);

        generalPanel.add(playlistButton);

        JPanel playPanel = new JPanel();
        playPanel.setLayout(new GridLayout(1, 2));

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


        playPanel.add(playButton);
        playPanel.add(pauseButton);

        generalPanel.add(playPanel);

        return generalPanel;
    }

    private JButton setCloseLabel () {

        JButton exitButton= new JButton("Close song info");
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        exitButton.setBackground(Color.GRAY);
        exitButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        exitButton.setFocusable(false);
        exitButton.setActionCommand(CLOSE_PANEL_COMMAND);
        exitButton.addActionListener(controller);

        return exitButton;
    }

    public void showErrorMessage () {
        String message = "Song is already contained in selected playlist and can not be added again.";

        JOptionPane.showMessageDialog(this, message, "Following errors were found", JOptionPane.WARNING_MESSAGE);
    }

    public String getSongTitle() {
        title = "The Time";
        return title;
    }

    public void showPlaylists () {
        JPanel playlistsPanel = new JPanel();
        showPlaylistsController = new ShowPlaylistsController(this);

        Box box= Box.createVerticalBox();
        //LinkedList<Playlist> playlists = controller.getDataPlaylists();
        Playlist p1 = new Playlist("Playlist 1", "popo");
        Playlist p2 = new Playlist("Playlist 2", "popo");
        Playlist p3 = new Playlist("Perreo duro durisimo hermano", "carlos");
        Playlist p4 = new Playlist("Cosas tranquilitas", "pepe");
        Playlist p5 = new Playlist("Gym", "mario");
        Playlist p6 = new Playlist("Locurote", "popo");

        LinkedList<Playlist> playlists = new LinkedList<>();
        playlists.add(p1); playlists.add(p2); playlists.add(p3); playlists.add(p4); playlists.add(p5); playlists.add(p6);;


        for (int i= 0; i < playlists.size(); i++) {
            JButton button = new JButton(playlists.get(i).getName());
            button.setFont(new Font("Tahoma", Font.PLAIN, 14));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFocusable(false);
            button.setActionCommand(playlists.get(i).getName());
            button.addActionListener(showPlaylistsController);
            box.add(button);
        }

        JScrollPane scroll= new JScrollPane(box);
        //scroll.setPreferredSize(new Dimension(150, 100));
        playlistsPanel.add(scroll);
        JOptionPane.showMessageDialog(this, playlistsPanel, "Select a playlist to add the song", JOptionPane.PLAIN_MESSAGE);
    }

    public String getGenre() {
        return genre;
    }

    public String getAlbum() {
        return album;
    }

    public String getSongArtist() {
        return artist;
    }

    public String getSongOwner() {
        return owner;
    }

    public String getPath() {
        return path;
    }

    public Song getSong () {
        return song;
    }
}


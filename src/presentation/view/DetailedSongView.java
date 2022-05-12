package presentation.view;

import business.LyricsFetcher;
import presentation.controller.DetailedSongController;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;

public class DetailedSongView extends JFrame {

    private static final String ADD_TO_PLAYLIST_COMMAND = "ADD_TO_PLAYLIST_COMMAND";
    private static final String PLAY_SONG_COMMAND = "PLAY_SONG_COMMAND";
    private static final String PAUSE_SONG_COMMAND = "PAUSE_SONG_COMMAND";
    private DetailedSongController controller;
    private LyricsFetcher lyricsFetcher;


    public DetailedSongView () {
        controller = new DetailedSongController();
        setLocation(0,10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1500, 900);
        setResizable(true);

        setLayout(new BorderLayout(0, 50));

        add(setTitleAndAuthor("The Time", "The Black Eyed Peas"), BorderLayout.NORTH);
        add(setGeneralData("Rock", "Albumsito", "Yoyo", "3:24"), BorderLayout.CENTER);
        add(setCloseLabel(), BorderLayout.SOUTH);

        setVisible(true);
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
        /*JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        exitPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JLabel closeWindow = new JLabel("Close Song Info");
        closeWindow.setFont(new Font("Tahoma", Font.PLAIN, 36));

        ImageIcon crossImage = new ImageIcon("images/cruz.png");
        Image image = crossImage.getImage();
        image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        crossImage = new ImageIcon(image);
        JLabel crossImageLabel = new JLabel(crossImage);

        exitPanel.add(closeWindow, gbc);
        exitPanel.add(crossImageLabel, gbc);*/

        JButton exitButton= new JButton("Close song info");
        exitButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        exitButton.setBackground(Color.GRAY);
        exitButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        exitButton.setFocusable(false);
        exitButton.setActionCommand(PLAY_SONG_COMMAND);
        exitButton.addActionListener(controller);

        return exitButton;
    }

}


package presentation.render;

import business.entities.Playlist;
import business.entities.Song;
import presentation.controller.ConfigPlaylistController;

import javax.swing.*;
import java.awt.*;

public class SongListRender extends JPanel {
    private JLabel lbIcon = new JLabel();
    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();
    private JButton btSubir = new JButton();
    private JButton btBajar = new JButton();
    private JButton btEliminar = new JButton();
    private ConfigPlaylistController controller;
    public static final String UP_BUTTON = "SL_UP_BUTTON";
    public static final String DOWN_BUTTON = "SL_DOWN_BUTTON";
    public static final String DELETE_BUTTON = "SL_DELETE_BUTTON";
    private Song song;

    public SongListRender(Song song,ConfigPlaylistController controller) {
        this.song = song;
        this.controller = controller;
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

        btSubir.setText("Subir");
        btBajar.setText("Bajar");
        btEliminar.setText("Eliminar");
        btSubir.setSize(100,50);
        btBajar.setSize(100,50);
        btEliminar.setSize(100,50);
        JPanel panelText = new JPanel(new GridLayout(2, 2));
        panelText.add(lbName);
        panelText.add(btSubir);
        panelText.add(btBajar);
        panelText.add(lbAuthor);
        panelText.add(btEliminar);
        add(lbIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);

        btSubir.setActionCommand(UP_BUTTON);
        btBajar.setActionCommand(DOWN_BUTTON);
        btEliminar.setActionCommand(DELETE_BUTTON);

        btEliminar.addActionListener(controller);
        btSubir.addActionListener(controller);
        btBajar.addActionListener(controller);

        ImageIcon img = new ImageIcon("Images/logo.png");
        Image image1 = img.getImage();
        image1 = image1.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon logoSimbol = new ImageIcon(image1);
        lbIcon.setIcon(logoSimbol);
        lbName.setText(song.getTitle());
        lbAuthor.setText(song.getArtist());
        lbAuthor.setForeground(Color.blue);


        lbName.setOpaque(true);
        lbAuthor.setOpaque(true);
        lbIcon.setOpaque(true);


    }

}

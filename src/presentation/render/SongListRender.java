package presentation.render;

import business.entities.Playlist;
import business.entities.Song;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SongListRender extends JPanel {
    private JLabel lbIcon = new JLabel();
    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();
    private JButton btSubir = new JButton();
    private JButton btBajar = new JButton();
    private JButton btEliminar = new JButton();
    private ActionListener controller;
    public static final String UP_BUTTON = "SL_UP_BUTTON";
    public static final String DOWN_BUTTON = "SL_DOWN_BUTTON";
    public static final String DELETE_BUTTON = "SL_DELETE_BUTTON";
    private Song song;
    private JSeparator separator= new JSeparator();

    public SongListRender(Song song, ActionListener controller) {
        this.song = song;
        this.controller = controller;
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

        btSubir.setText("Subir");
        btBajar.setText("Bajar");
        btSubir.addActionListener(controller);
        btSubir.putClientProperty( "song_subir", this.song );
        btBajar.addActionListener(controller);
        btBajar.putClientProperty( "song_bajar", this.song );

        btEliminar.setText("Eliminar");
        btSubir.setSize(100,50);
        btBajar.setSize(100,50);
        btEliminar.setSize(100,100);
        GridLayout gridLayout= new GridLayout(3, 2);
        GridLayout gridLayout1= new GridLayout(1,2);
        JPanel panel= new JPanel();
        panel.setLayout(gridLayout1);
        JPanel panelText = new JPanel();
        panelText.setLayout(gridLayout);
        panelText.setBackground(Color.white);
        panel.setBackground(Color.white);
        gridLayout.setVgap(20);
        gridLayout.setHgap(20);
        gridLayout1.setVgap(20);
        gridLayout1.setHgap(20);
        panel.add(btSubir);
        panel.add(btBajar);
        panelText.add(lbName);
        panelText.add(panel);
        panelText.add(lbAuthor);
        panelText.add(btEliminar);
        panelText.add(separator);
        panelText.add(separator);

        add(lbIcon, BorderLayout.WEST);
        separator.setBackground(Color.black);
        separator.setSize(new Dimension(300,300));
        add(panelText, BorderLayout.CENTER);

        btSubir.setActionCommand(UP_BUTTON);
        btBajar.setActionCommand(DOWN_BUTTON);
        btEliminar.setActionCommand(DELETE_BUTTON);

        btSubir.setForeground(Color.black);
        btSubir.setBackground(Color.CYAN);

        btBajar.setForeground(Color.black);
        btBajar.setBackground(Color.CYAN);

        btEliminar.setForeground(Color.black);
        btEliminar.setBackground(Color.CYAN);

        btSubir.setBorderPainted(false);
        btEliminar.setBorderPainted(false);
        btBajar.setBorderPainted(false);

        btEliminar.addActionListener(controller);
        btEliminar.putClientProperty("SONG_ELIMINAR",this.song);

        ImageIcon img = new ImageIcon("Images/logo.png");
        Image image1 = img.getImage();
        image1 = image1.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon logoSimbol = new ImageIcon(image1);
        lbIcon.setIcon(logoSimbol);
        lbName.setText(song.getTitle());
        lbName.setFont(new Font("Arial", Font.BOLD,18));
        lbAuthor.setText(song.getOwner());
        lbAuthor.setForeground(Color.blue);
        lbIcon.setBackground(Color.white);
        lbAuthor.setBackground(Color.white);
        lbName.setBackground(Color.white);


        lbName.setOpaque(true);
        lbAuthor.setOpaque(true);
        lbIcon.setOpaque(true);
        this.setBackground(Color.white);


    }

}

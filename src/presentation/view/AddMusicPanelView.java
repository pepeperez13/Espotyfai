package presentation.view;
import business.SongManager;
import persistance.dao.sql.SQLConnector;
import presentation.controller.AddMusicController;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;

public class AddMusicPanelView extends JPanel {
    public static final String SAVE_TITULO = "SAVE_TITULO";
    public static final String SAVE_ALBUM = "SAVE_ALBUM";
    public static final String SAVE_GENERO = "SAVE_GENERO";
    public static final String SAVE_AUTOR = "SAVE_AUTOR";
    public static final String UPLOAD_MUSIC = "UPLOAD_MUSIC";
    private JTextField textTitulo;
    private JTextField textAlbum;
    private JTextField textGenero;
    private JTextField textAutor;
    private JButton uploadIcon;
    private final GridBagConstraints gc = new GridBagConstraints();

    public AddMusicPanelView () {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("wav", "mp3");

        setLayout(new GridBagLayout());
        gc.fill = GridBagConstraints.NONE;

        setBackground(new Color(255, 255, 255));

        configurePanel();
    }

    private void configurePanel () {
        JLabel labelAdd = new JLabel();
        labelAdd.setText("Añadir canción");
        labelAdd.setFont(new Font("Arial Black", Font.BOLD, 25));

        JSeparator s1 = new JSeparator();
        s1.setOrientation(SwingConstants.HORIZONTAL);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(255, 255, 255));
        p.add(labelAdd);
        p.add(s1);

        JLabel labelTitulo = new JLabel();
        labelTitulo.setText("Título");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        textTitulo = new JTextField();
        textTitulo.setName("Título");
        textTitulo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textTitulo.setActionCommand(SAVE_TITULO);

        JLabel labelAlbum = new JLabel();
        labelAlbum.setText("Album");
        labelAlbum.setFont(new Font("Arial", Font.BOLD, 18));

        textAlbum = new JTextField();
        textAlbum.setName("Album");
        textAlbum.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textAlbum.setActionCommand(SAVE_ALBUM);

        JLabel labelFichero = new JLabel();
        labelFichero.setText("Fichero MP3/WAV");
        labelFichero.setFont(new Font("Arial", Font.BOLD, 18));

        ImageIcon logoSimbol = new ImageIcon("Images/upload.png");
        Image img = logoSimbol.getImage();
        img = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        logoSimbol = new ImageIcon(img);
        uploadIcon = new JButton(logoSimbol);
        uploadIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadIcon.setActionCommand(UPLOAD_MUSIC);

        JLabel labelGenero = new JLabel();
        labelGenero.setText("Género musical");
        labelGenero.setFont(new Font("Arial", Font.BOLD, 18));

        textGenero = new JTextField();
        textGenero.setName("Género musical");
        textGenero.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textGenero.setActionCommand(SAVE_GENERO);

        JLabel labelAutor = new JLabel();
        labelAutor.setText("Autor");
        labelAutor.setFont(new Font("Arial", Font.BOLD, 18));

        textAutor = new JTextField();
        textAutor.setName("Autor");
        textAutor.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textAutor.setActionCommand(SAVE_AUTOR);

        gc.gridx = 0;
        gc.gridy = 0;
        add(p, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        add(labelTitulo, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        add(textTitulo, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        add(labelAlbum, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        add(textAlbum, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        add(labelFichero, gc);
        gc.gridx = 0;
        gc.gridy = 6;
        add(uploadIcon, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        add(labelGenero, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        add(textGenero, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        add(labelAutor, gc);
        gc.gridx = 1;
        gc.gridy = 5;
        add(textAutor, gc);
    }

    public void registerController(ActionListener listener) {
        textTitulo.addActionListener(listener);
        textAlbum.addActionListener(listener);
        uploadIcon.addActionListener(listener);
        textGenero.addActionListener(listener);
        textAutor.addActionListener(listener);
    }

    public static void main (String[] args) {
        AddMusicPanelView menuPrincipal = new AddMusicPanelView();
        JFrame f = new JFrame();
        f.add(menuPrincipal);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible (true);
    }
}
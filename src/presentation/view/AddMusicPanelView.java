package presentation.view;
import business.SongManager;
import presentation.controller.AddMusicController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class AddMusicPanelView extends JPanel {
    public static final String ADD_SONG = "ADD_SONG";
    private final JTextField textTitulo = new JTextField();
    private final JTextField textAlbum = new JTextField();
    private final JComboBox optionGenero = new JComboBox();
    private final JTextField textAutor = new JTextField();
    private final JFileChooser fileChooser = new JFileChooser();
    private final GridBagConstraints gc = new GridBagConstraints();

    public AddMusicPanelView () {
        setLayout(new GridBagLayout());
        gc.fill = GridBagConstraints.NONE;

        setBackground(new Color(255, 255, 255));

        AddMusicController addMusicController = new AddMusicController(this);
        configurePanel(addMusicController);
    }

    private void configurePanel (AddMusicController addMusicController) {
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

        textTitulo.setName("Título");
        textTitulo.setPreferredSize(new Dimension(200, 20));
        textTitulo.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel labelAlbum = new JLabel();
        labelAlbum.setText("Album");
        labelAlbum.setFont(new Font("Arial", Font.BOLD, 18));

        textAlbum.setName("Album");
        textAlbum.setPreferredSize(new Dimension(200, 20));
        textAlbum.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel labelFichero = new JLabel();
        labelFichero.setText("Fichero MP3/WAV");
        labelFichero.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel labelGenero = new JLabel();
        labelGenero.setText("Género musical");
        labelGenero.setFont(new Font("Arial", Font.BOLD, 18));

        optionGenero.addItem(StaticsPanelView.KPOP);
        optionGenero.addItem(StaticsPanelView.JAZZ);
        optionGenero.addItem(StaticsPanelView.ROCK);
        optionGenero.addItem(StaticsPanelView.ELECTRONIC);
        optionGenero.addItem(StaticsPanelView.METAL);
        optionGenero.addItem(StaticsPanelView.HIPHOP);
        optionGenero.addItem(StaticsPanelView.POP);
        optionGenero.addItem(StaticsPanelView.REGGAETON);
        optionGenero.addItem(StaticsPanelView.REGGAE);
        optionGenero.addItem(StaticsPanelView.LATIN);
        /*textGenero.setName("Género musical");
        textGenero.setPreferredSize(new Dimension(200 , 20));
        textGenero.setBorder(BorderFactory.createLineBorder(Color.GRAY));*/

        JLabel labelAutor = new JLabel();
        labelAutor.setText("Autor");
        labelAutor.setFont(new Font("Arial", Font.BOLD, 18));

        textAutor.setName("Autor");
        textAutor.setPreferredSize(new Dimension(200, 20));
        textAutor.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JButton okay = new JButton("Añadir canción");
        okay.setFont(new Font("Arial", Font.BOLD, 18));
        okay.setBackground(new Color(152, 245, 214));
        okay.setBorderPainted(false);
        okay.setActionCommand(ADD_SONG);
        okay.addActionListener(addMusicController);

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
        //gc.gridx = 0;
        //gc.gridy = 6;
        //add(uploadIcon, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        add(labelGenero, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        add(optionGenero, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        add(labelAutor, gc);
        gc.gridx = 1;
        gc.gridy = 5;
        add(textAutor, gc);
        gc.gridx = 1;
        gc.gridy = 6;
        add(okay, gc);

    }

    public String getAlbum () {
        return textAlbum.getText();
    }
    public String getAutor () {
        return textAutor.getText();
    }
    public String getGenero () {
        String info = null;
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.KPOP)){
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.JAZZ)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.ROCK)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.ELECTRONIC)){
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.METAL)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.HIPHOP)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.POP)){
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.REGGAETON)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.REGGAE)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StaticsPanelView.LATIN)) {
            info = (String) optionGenero.getSelectedItem();
        }
        return info;
    }
    public String getTitulo () {
        return textTitulo.getText();
    }
    public String getPath () {
        return openFile();
    }
    private String openFile () {
        String path = null;
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV", ".wav");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getPath();
        }
        return path;
    }
}

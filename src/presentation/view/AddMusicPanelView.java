package presentation.view;

import presentation.controller.AddMusicController;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que contiene la vista de AÑADIR CANCIÓN
 */
public class AddMusicPanelView extends JPanel {
    public static final String ADD_SONG = "ADD_SONG";
    private final JTextField textTitulo = new JTextField();
    private final JTextField textAlbum = new JTextField();
    private final JComboBox optionGenero = new JComboBox();
    private final JTextField textAutor = new JTextField();
    private final JFileChooser fileChooser = new JFileChooser();
    private final GridBagConstraints gc = new GridBagConstraints();

    /**
     * Constructor de AddMusicPanelView
     */
    public AddMusicPanelView () {
        setLayout(new GridBagLayout());
        gc.fill = GridBagConstraints.NONE;

        setBackground(new Color(255, 255, 255));

        AddMusicController addMusicController = new AddMusicController(this);
        configurePanel(addMusicController);
    }

    /**
     * Método que configura la vista (JLabels, JButtons, JTextFields, ...)
     * @param addMusicController Recibe el controlador que gestionará la información introducida
     *                           por el usuario, asi como as acciones sobre los botones
     */
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

        optionGenero.addItem(StatisticsPanelView.KPOP);
        optionGenero.addItem(StatisticsPanelView.JAZZ);
        optionGenero.addItem(StatisticsPanelView.ROCK);
        optionGenero.addItem(StatisticsPanelView.ELECTRONIC);
        optionGenero.addItem(StatisticsPanelView.METAL);
        optionGenero.addItem(StatisticsPanelView.HIPHOP);
        optionGenero.addItem(StatisticsPanelView.POP);
        optionGenero.addItem(StatisticsPanelView.REGGAETON);
        optionGenero.addItem(StatisticsPanelView.REGGAE);
        optionGenero.addItem(StatisticsPanelView.LATIN);

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

    /**
     * Devuelve el string que hay en el TextField
     * @return String
     */
    public String getAlbum () {
        return textAlbum.getText();
    }

    /**
     * Devuelve el string que hay en el TextField
     * @return String
     */
    public String getAutor () {
        return textAutor.getText();
    }

    /**
     * Devuelve el string que ha seleccionado el usuario
     * @return String
     */
    public String getGenero () {
        String info = null;
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.KPOP)){
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.JAZZ)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.ROCK)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.ELECTRONIC)){
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.METAL)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.HIPHOP)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.POP)){
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.REGGAETON)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.REGGAE)) {
            info = (String) optionGenero.getSelectedItem();
        }
        if (optionGenero.getSelectedItem().equals(StatisticsPanelView.LATIN)) {
            info = (String) optionGenero.getSelectedItem();
        }
        return info;
    }

    /**
     * Devuelve el string que hay en el TextField
     * @return String
     */
    public String getTitulo () {
        return textTitulo.getText();
    }

    /**
     * Devuelve el string que hay en el TextField
     * @return String
     */
    public String getPath () {
        return openFile();
    }

    /**
     * Método que retorna un String con el archivo seleccionado del dispositivo del usuario actual
     * @return String Path
     */
    private String openFile () {
        String path = null;
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            path = fileChooser.getSelectedFile().getPath();
        }
        return path;
    }
}

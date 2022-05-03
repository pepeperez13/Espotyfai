package presentation.view;
import javax.swing.*;
import java.awt.*;

public class ShowMusicPanelView extends JPanel {
    private final GridBagConstraints gc = new GridBagConstraints();


    public ShowMusicPanelView() {
        setBackground(new Color(255, 255, 255));
        setLayout(new GridBagLayout());

        gc.fill = GridBagConstraints.NONE;

        configurePanel();
    }

    private void configurePanel () {

        JLabel labelCancion = new JLabel();
        labelCancion.setText("Ver canción");
        labelCancion.setFont(new Font("Arial", Font.BOLD, 18));

        JSeparator s1 = new JSeparator();
        s1.setOrientation(SwingConstants.HORIZONTAL);

        JLabel labelTitulo = new JLabel();
        labelTitulo.setText("Título");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        //Setear el texto desde la base de datos
        JTextArea textTitulo = new JTextArea();
        textTitulo.setBorder(BorderFactory.createLineBorder(new Color(0, 0,0)));

        JLabel labelAlbum = new JLabel();
        labelAlbum.setText("Album");
        labelAlbum.setFont(new Font("Arial", Font.BOLD, 18));

        //Setear el texto desde la base de datos
        JTextArea textAlbum = new JTextArea();
        textAlbum.setBorder(BorderFactory.createLineBorder(new Color(0, 0,0)));


        JLabel labelAutor = new JLabel();
        labelAutor.setText("Título");
        labelAutor.setFont(new Font("Arial", Font.BOLD, 18));

        //Setear el texto desde la base de datos
        JTextArea textAutor = new JTextArea();
        textAutor.setBorder(BorderFactory.createLineBorder(new Color(0, 0,0)));

        JLabel labelGenero = new JLabel();
        labelGenero.setText("Título");
        labelGenero.setFont(new Font("Arial", Font.BOLD, 18));

        //Setear el texto desde la base de datos
        JTextArea textGenero = new JTextArea();
        textGenero.setBorder(BorderFactory.createLineBorder(new Color(0, 0,0)));

        JButton jbSearch = new JButton("Search");
        jbSearch.setFont(new Font("Arial", Font.BOLD, 18));
        jbSearch.setBackground(new Color(152, 245, 214));
        jbSearch.setBorder(BorderFactory.createEmptyBorder());

        gc.gridx = 0;
        gc.gridy = 0;
        add(labelCancion, gc);
        add(s1, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        add(labelTitulo, gc);
        gc.gridx = 1;
        gc.gridy = 1;
        add(textTitulo, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        add(labelAlbum, gc);
        gc.gridx = 1;
        gc.gridy = 2;
        add(textAlbum, gc);
        gc.gridx = 0;
        gc.gridy = 3;
        add(labelAutor, gc);
        gc.gridx = 1;
        gc.gridy = 3;
        add(textAutor, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        add(labelGenero, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        add(textGenero, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        add(jbSearch, gc);
    }

    public static void main (String[] args) {
        ShowMusicPanelView menuPrincipal = new ShowMusicPanelView();
        JFrame f = new JFrame();
        f.add(menuPrincipal);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible (true);
    }
}
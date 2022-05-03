package presentation.view;
import javax.swing.*;
import java.awt.*;

public class DeleteMusicPanelView extends JPanel {
    private GridBagConstraints gc = new GridBagConstraints();

    public DeleteMusicPanelView () {
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 255, 255));

        JLabel labelEliminarCancion = new JLabel("Eliminar canción");
        labelEliminarCancion.setFont(new Font("Arial", Font.BOLD, 18));
        labelEliminarCancion.setBackground(new Color(255, 255, 255));

        JSeparator s1 = new JSeparator();
        s1.setOrientation(SwingConstants.HORIZONTAL);

        JPanel p = new JPanel();
        p.setBackground(new Color(255, 255, 255));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(labelEliminarCancion);
        p.add(s1);

        JLabel labelTitulo = new JLabel("Título");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        labelTitulo.setBackground(new Color(255, 255, 255));

        JTextArea textTitulo = new JTextArea();

        JLabel labelAlbum = new JLabel("Album");
        labelAlbum.setFont(new Font("Arial", Font.BOLD, 18));
        labelAlbum.setBackground(new Color(255, 255, 255));

        JTextArea textAlbum = new JTextArea();

        JButton jbdelete = new JButton();
        jbdelete.setText("Eliminar");
        jbdelete.setBackground(new Color(230, 101, 101));
        jbdelete.setBorder(BorderFactory.createEmptyBorder());

        JLabel mensaje = new JLabel("Solo se podrá eliminar la canción si ha sido añadida por usted!");
        mensaje.setFont(new Font("Arial", Font.BOLD, 11));
        mensaje.setBackground(new Color(255, 255, 255));

        gc.gridx = 0;
        gc.gridy = 0;
        add(p);
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
        add(jbdelete, gc);
        gc.gridx = 0;
        gc.gridy = 6;
        add(mensaje, gc);
    }

    public static void main (String[] args) {
        DeleteMusicPanelView menuPrincipal = new DeleteMusicPanelView();
        JFrame f = new JFrame();
        f.add(menuPrincipal);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible (true);
    }
}

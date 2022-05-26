package presentation.view;
import presentation.controller.ConfMusicController;
import presentation.controller.DeleteMusicController;

import javax.swing.*;
import java.awt.*;

public class DeleteMusicPanelView extends JPanel {
    private GridBagConstraints gc = new GridBagConstraints();
    private JTextField nameSong = new JTextField();
    public static final String DELETE = "DELETE";
    private DeleteMusicController deleteMusicController;

    public DeleteMusicPanelView () {
        deleteMusicController = new DeleteMusicController(this);

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

        nameSong.setPreferredSize(new Dimension(200, 20));

        JButton jbdelete = new JButton();
        jbdelete.setText("Eliminar");
        jbdelete.setBackground(new Color(230, 101, 101));
        jbdelete.setBorder(BorderFactory.createEmptyBorder());
        jbdelete.setActionCommand(DELETE);
        jbdelete.addActionListener(deleteMusicController);

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
        add(nameSong, gc);
        gc.gridx = 0;
        gc.gridy = 5;
        add(jbdelete, gc);
        gc.gridx = 0;
        gc.gridy = 6;
        add(mensaje, gc);
    }

    public String getNameSong() {
        return nameSong.getText();
    }
}

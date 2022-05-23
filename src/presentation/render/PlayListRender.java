package presentation.render;

import business.entities.Playlist;
;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayListRender extends JPanel  {

    private JLabel lbIcon = new JLabel();
    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();
    private JButton btEditar = new JButton();
    private JButton btEliminar = new JButton();
    public static final String EDIT_BUTTON = "PL_EDIT_BUTTON";
    public static final String DELETE_BUTTON = "PL_DELETE_BUTTON";
    private Playlist playlist = null;

    public PlayListRender(Playlist playlist, ActionListener controller) {
        this.playlist = playlist;
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

        btEditar.setText("Editar");
        btEliminar.setText("Eliminar");
        btEditar.setSize(100,50);
        btEliminar.setSize(100,50);
        JPanel panelText = new JPanel(new GridLayout(2, 2));
        panelText.add(lbName);
        panelText.add(btEditar);
        panelText.add(lbAuthor);
        panelText.add(btEliminar);
        add(lbIcon, BorderLayout.WEST);
        add(panelText, BorderLayout.CENTER);

        btEditar.setActionCommand(EDIT_BUTTON);
        btEliminar.setActionCommand(DELETE_BUTTON);

        btEditar.addActionListener(controller);
        btEditar.putClientProperty( "PLAYLIST", this.playlist );

        btEliminar.addActionListener(controller);

        ImageIcon img = new ImageIcon("Images/logo.png");
        Image image1 = img.getImage();
        image1 = image1.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon logoSimbol = new ImageIcon(image1);
        lbIcon.setIcon(logoSimbol);
        lbName.setText(playlist.getName());
        lbAuthor.setText(playlist.getOwner());
        lbAuthor.setForeground(Color.blue);


        lbName.setOpaque(true);
        lbAuthor.setOpaque(true);
        lbIcon.setOpaque(true);

    }



}
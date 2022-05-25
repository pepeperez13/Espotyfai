package presentation.render;

import business.Store;
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
    private JSeparator separator= new JSeparator();



    public PlayListRender(Playlist playlist, ActionListener controller) {
        this.playlist = playlist;
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(1,1,1,1));


        btEliminar.setText("Eliminar");
        btEditar.setSize(100,50);
        btEliminar.setSize(100,50);
        GridLayout gridLayout= new GridLayout(3, 2);
        JPanel panelText = new JPanel();
        panelText.setLayout(gridLayout);
        if(isMyPlaylist()){
            btEditar.setText("Editar");
        }else{
            btEditar.setText("Ver");
        }
        panelText.setBackground(Color.white);
        gridLayout.setVgap(30);
        gridLayout.setHgap(30);
        panelText.add(lbName);
        panelText.add(btEditar);
        panelText.add(lbAuthor);
        panelText.add(separator);
        if(isMyPlaylist()){
            panelText.add(btEliminar);

        }
        panelText.add(separator);
        panelText.add(separator);
        add(lbIcon, BorderLayout.WEST);
        separator.setBackground(Color.black);
        separator.setSize(new Dimension(300,300));
        add(panelText, BorderLayout.CENTER);


        btEditar.setActionCommand(EDIT_BUTTON);
        btEliminar.setActionCommand(DELETE_BUTTON);

        btEditar.setForeground(Color.black);
        btEditar.setBackground(Color.CYAN);

        btEliminar.setForeground(Color.black);
        btEliminar.setBackground(Color.CYAN);

        btEditar.setBorderPainted(false);
        btEliminar.setBorderPainted(false);


        btEditar.addActionListener(controller);
        btEditar.putClientProperty( "PLAYLIST", this.playlist );

        btEliminar.addActionListener(controller);
        btEliminar.putClientProperty("PLAYLIST_ELIMINAR",this.playlist);

        ImageIcon img = new ImageIcon("Images/logo.png");
        Image image1 = img.getImage();
        image1 = image1.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon logoSimbol = new ImageIcon(image1);
        lbIcon.setIcon(logoSimbol);
        lbName.setText(playlist.getName());
        lbName.setFont(new Font("Arial", Font.BOLD,18));
        lbAuthor.setText(playlist.getOwner());
        lbAuthor.setForeground(Color.blue);
        lbIcon.setBackground(Color.white);
        lbName.setBackground(Color.white);
        lbAuthor.setBackground(Color.white);


        lbName.setOpaque(true);
        lbAuthor.setOpaque(true);
        lbIcon.setOpaque(true);
        this.setBackground(Color.white);



    }

    private boolean isMyPlaylist(){
        return playlist.getOwner().equals(Store.getUser().getName());
    }


}
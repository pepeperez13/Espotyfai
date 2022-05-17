package presentation.render;

import business.entities.Playlist;

import javax.swing.*;
import java.awt.*;

public class PlayListRender extends JPanel implements ListCellRenderer<Playlist> {

    private JLabel lbIcon = new JLabel();
    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();
    private JButton btEditar = new JButton();
    private JButton btEliminar = new JButton();

    public PlayListRender() {
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
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Playlist> list, Playlist playlist, int index, boolean isSelected, boolean cellHasFocus) {

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

        if (isSelected) {
            lbName.setBackground(list.getSelectionBackground());
            lbAuthor.setBackground(list.getSelectionBackground());
            lbIcon.setBackground(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        } else { // when don't select
            lbName.setBackground(list.getBackground());
            lbAuthor.setBackground(list.getBackground());
            lbIcon.setBackground(list.getBackground());
            setBackground(list.getBackground());
        }


        return this;
    }


}
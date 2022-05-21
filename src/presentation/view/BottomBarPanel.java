package presentation.view;

import javax.swing.*;
import java.awt.*;

public class BottomBarPanel extends JPanel {

    public JPanel bottomBarPanel () {
        /*this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMaximumSize(new Dimension(1500, 900));
        this.setMinimumSize(new Dimension(900, 500));
        this.setTitle("Espotifai - Buscar");
        this.setLocationRelativeTo(null)*/

        JPanel song_player = new JPanel(new BorderLayout());

        song_player.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel song_info_label = new JLabel("nombre de la cancion - artista");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START; //bottom of space
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 0;
        song_player.add(song_info_label, c);

        JButton back_song = new JButton("<<");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        song_player.add(back_song, c);

        JButton stop = new JButton("||");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        song_player.add(stop, c);

        JButton play = new JButton(">");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        song_player.add(play, c);

        JButton next_song = new JButton(">>");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        song_player.add(next_song, c);

        JButton full_screen = new JButton("Details");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;       //aligned with button 2
        c.gridy = 2;       //third row
        song_player.add(full_screen, c);

        this.add(song_player, BorderLayout.SOUTH);

        //this.setVisible(true);
        return this;
    }
}

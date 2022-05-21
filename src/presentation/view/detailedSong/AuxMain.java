package presentation.view.detailedSong;

import business.entities.Song;
import persistance.PlaylistDAO;

import javax.swing.*;

public class AuxMain {

    public static void main (String[] args) {
        JFrame frame = new JFrame();
        DetailedSongView detailedSongView = new DetailedSongView();
        frame.add(detailedSongView);

        detailedSongView.setVisible(true);
        frame.setVisible(true);
        while (1 < 2){
        detailedSongView.recibirCancion(new Song("aux", "aux", "aux", "aux", "aux", "aux"));
        detailedSongView.recibirCancion(new Song("aux", "aux", "aufcerfrfrx", "aux", "aux", "aux"));
            System.out.println("e");
        }

    }
}

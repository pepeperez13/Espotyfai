package presentation.controller;

import business.Owner;
import business.SongManager;
import business.entities.Song;
import presentation.view.BottomBarPanel;
import presentation.view.DeleteMusicPanelView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase, controlador que se encarga de eliminar una canción del usuario actual
 */
public class DeleteMusicController implements ActionListener {
    private DeleteMusicPanelView deleteMusicPanelView;

    /**
     * Constructor
     * @param deleteMusicPanelView Vista desde donde el usuario
     *                             introduce el nombre de la canción a eliminar
     */
    public DeleteMusicController(DeleteMusicPanelView deleteMusicPanelView) {
        this.deleteMusicPanelView = deleteMusicPanelView;
    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(DeleteMusicPanelView.DELETE)) {
            Song song = getSongByName(deleteMusicPanelView.getNameSong());
            if (BottomBarPanel.getSong() != null) {
                if (!BottomBarPanel.getSong().getTitle().equals(deleteMusicPanelView.getNameSong())) {
                    if (deleteMusicPanelView.getNameSong() != null) {
                        song = getSongByName(deleteMusicPanelView.getNameSong());
                        if (song.getOwner().equals(Owner.getUser().getName())) {
                            if (SongManager.DeleteSong(deleteMusicPanelView.getNameSong())) {
                                JOptionPane.showMessageDialog(deleteMusicPanelView, "DELETE SUCCESSFULLY", "", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(deleteMusicPanelView, "DELETE UNSUCCESSFULLY", "", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(deleteMusicPanelView, "CANNOT DELETE A SONG OF WHICH YOU ARE NOT OWNER", "Following errors were found", JOptionPane.WARNING_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(deleteMusicPanelView, "COMPLETE THE FIELD", "Following errors were found", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(deleteMusicPanelView, "SONG IS PLAYING", "Following errors were found", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                if (song.getOwner().equals(Owner.getUser().getName())) {
                    if (SongManager.DeleteSong(deleteMusicPanelView.getNameSong())) {
                        JOptionPane.showMessageDialog(deleteMusicPanelView, "DELETE SUCCESSFULLY", "", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(deleteMusicPanelView, "DELETE UNSUCCESSFULLY", "", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(deleteMusicPanelView, "CANNOT DELETE A SONG OF WHICH YOU ARE NOT OWNER", "Following errors were found", JOptionPane.WARNING_MESSAGE);
                }

            }
        }
    }

    private Song getSongByName (String name) {
        return SongManager.SelectSong(name);
    }
}

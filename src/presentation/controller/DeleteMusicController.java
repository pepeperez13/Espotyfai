package presentation.controller;

import business.SongManager;
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
            if (!BottomBarPanel.getSong().getTitle().equals(deleteMusicPanelView.getNameSong())) {
                if (deleteMusicPanelView.getNameSong() != null) {
                    SongManager.DeleteSong(deleteMusicPanelView.getNameSong());
                    JOptionPane.showMessageDialog(deleteMusicPanelView, "DELETE SUCCESSFULLY", "", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(deleteMusicPanelView, "COMPLETE THE FIELD", "Following errors were found", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(deleteMusicPanelView, "SONG IS PLAYING", "Following errors were found", JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}

package presentation.controller;

import business.SongManager;
import presentation.view.BottomBarPanel;
import presentation.view.DeleteMusicPanelView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteMusicController implements ActionListener {
    private DeleteMusicPanelView deleteMusicPanelView;

    public DeleteMusicController(DeleteMusicPanelView deleteMusicPanelView) {
        this.deleteMusicPanelView = deleteMusicPanelView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(DeleteMusicPanelView.DELETE)) {
            if (deleteMusicPanelView.getNameSong() != null && !BottomBarPanel.getSong().getTitle().equals(deleteMusicPanelView.getNameSong())) {
                SongManager.DeleteSong(deleteMusicPanelView.getNameSong());
                JOptionPane.showMessageDialog(deleteMusicPanelView, "DELETE SUCCESSFULLY", "OKAY", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(deleteMusicPanelView, "COMPLETE THE FIELD", "Following errors were found", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}

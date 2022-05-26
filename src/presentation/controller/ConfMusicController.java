package presentation.controller;

import business.SongManager;
import com.sun.tools.javac.Main;
import presentation.view.ConfMusicPanelView;
import presentation.view.DeleteMusicPanelView;
import presentation.view.MainManagerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfMusicController implements ActionListener {
    private ConfMusicPanelView menuFrontal;
    private MainManagerView mainManagerView;
    private SongManager songManager;
    private DeleteMusicPanelView deleteMusicPanelView;
    public ConfMusicController (ConfMusicPanelView confMusicPanelView, MainManagerView mainManagerView) {
        this.menuFrontal = confMusicPanelView;
        this.mainManagerView = mainManagerView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ConfMusicPanelView.GO_ADD_MUSIC)) {
            mainManagerView.changeView(9, 2);
        } else if (e.getActionCommand().equals(ConfMusicPanelView.GO_DELETE_MUSIC)) {
            mainManagerView.changeView(11, 2);
            if (deleteMusicPanelView.getNameSong() == null) {
                JOptionPane.showMessageDialog(deleteMusicPanelView, "COMPLETE THE FIELD", "Following errors were found", JOptionPane.WARNING_MESSAGE);

            } else {
                SongManager.DeleteSong(deleteMusicPanelView.getNameSong());
                JOptionPane.showMessageDialog(deleteMusicPanelView, "DELETE SUCCESSFULLY", "OKAY", JOptionPane.INFORMATION_MESSAGE);

            }

        }
    }
}

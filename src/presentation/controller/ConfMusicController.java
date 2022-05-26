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
    private MainManagerView mainManagerView;
    public ConfMusicController (MainManagerView mainManagerView) {
        this.mainManagerView = mainManagerView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ConfMusicPanelView.GO_ADD_MUSIC)) {
            mainManagerView.changeView(9, 2);
        } else if (e.getActionCommand().equals(ConfMusicPanelView.GO_DELETE_MUSIC)) {
            mainManagerView.changeView(11, 2);
        }
    }
}

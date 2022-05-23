package presentation.controller;

import com.sun.tools.javac.Main;
import presentation.view.ConfMusicPanelView;
import presentation.view.MainManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfMusicController implements ActionListener {
    private ConfMusicPanelView menuFrontal;
    private MainManagerView mainManagerView;
    public ConfMusicController (ConfMusicPanelView confMusicPanelView, MainManagerView mainManagerView) {
        this.menuFrontal = confMusicPanelView;
        this.mainManagerView = mainManagerView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ConfMusicPanelView.GO_ADD_MUSIC)) {
            mainManagerView.changeView(9, 2);
        } else if (e.getActionCommand().equals(ConfMusicPanelView.GO_SHOW_MUSIC)) {
            mainManagerView.changeView(10, 2);
        } else if (e.getActionCommand().equals(ConfMusicPanelView.GO_DELETE_MUSIC)) {
            mainManagerView.changeView(11, 2);
        }
    }
}

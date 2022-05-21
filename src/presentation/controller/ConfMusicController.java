package presentation.controller;

import presentation.view.ConfMusicPanelView;
import presentation.view.ConfigAccountView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfMusicController implements ActionListener {
    private ConfigAccountView menuFrontal;
    public ConfMusicController (ConfigAccountView menuFrontal) {
        this.menuFrontal = menuFrontal;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ConfMusicPanelView.GO_ADD_MUSIC)) {
            menuFrontal.changueView(4);
        } else if (e.getActionCommand().equals(ConfMusicPanelView.GO_SHOW_MUSIC)) {
            menuFrontal.changueView(5);
        } else if (e.getActionCommand().equals(ConfMusicPanelView.GO_DELETE_MUSIC)) {
            menuFrontal.changueView(6);
        }
    }
}

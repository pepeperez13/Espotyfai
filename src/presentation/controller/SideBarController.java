package presentation.controller;

import presentation.view.ConfigMenu;
import presentation.view.MainManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideBarController implements ActionListener {
    private ConfigMenu configMenu;
    private MainManagerView mainManagerView;
    public SideBarController (ConfigMenu configMenu, MainManagerView mainManagerView) {
        this.configMenu = configMenu;
        this.mainManagerView = mainManagerView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ConfigMenu.GO_CONFIG_MUSIC)) {
            mainManagerView.changeView(6, 2);
        } else if (e.getActionCommand().equals(ConfigMenu.GO_CONFIG_USER)) {
            mainManagerView.changeView(7, 2);
        } else if (e.getActionCommand().equals(ConfigMenu.GO_STATICS)) {
            mainManagerView.changeView(8, 2);
        } else if (e.getActionCommand().equals(ConfigMenu.GO_INICIO)) {
            mainManagerView.changeView(1, 1);
        }
    }
}

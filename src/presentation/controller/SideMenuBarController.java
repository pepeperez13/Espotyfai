package presentation.controller;//package presentation.controller;

//import presentation.view.SideBarView;

import presentation.view.SideMenuBarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuBarController implements ActionListener {
    private final SideMenuBarView menuBarView;

    public SideMenuBarController (SideMenuBarView menuBarView) {
        this.menuBarView = menuBarView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SideMenuBarView.GO_INICIO)) {
            menuBarView.changeView("cero");
        } else if (e.getActionCommand().equals(SideMenuBarView.GO_BUSCADOR)) {
            menuBarView.changeView("uno");
        } else if (e.getActionCommand().equals(SideMenuBarView.GO_MISLISTAS)) {
            menuBarView.changeView("dos");
        } else if (e.getActionCommand().equals(SideMenuBarView.GO_SETTINGS)) {
            menuBarView.changeView("tres");
        }
    }
}
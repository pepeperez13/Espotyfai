package presentation.controller;

import presentation.view.SideMenuPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuPanelController implements ActionListener {
    private SideMenuPanel view;

    public SideMenuPanelController (SideMenuPanel view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case view.INICIO:
                view.goHome();
                break;
            case view.GESTION_MUSICA:
                view.goConfMusic();
                break;
            case view.GESTION_CUENTA:
                view.goConfCuenta();
                break;
            case view.ESTADISTICAS:
                view.goEstadisticas();
                break;
        }
    }
}

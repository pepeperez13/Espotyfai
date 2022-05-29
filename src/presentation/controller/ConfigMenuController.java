package presentation.controller;

import presentation.view.ConfigMenuView;
import presentation.view.MainManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que contiene el controlador de la vista del MENU DE CONFIGURACIÓN
 */
public class ConfigMenuController implements ActionListener {
    private MainManagerView mainManagerView;

    /**
     * Constructor
     * @param mainManagerView Vista general del programa y desde donde
     *                        se crea la vista del menu de configuración
     */
    public ConfigMenuController(MainManagerView mainManagerView) {
        this.mainManagerView = mainManagerView;
    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ConfigMenuView.GO_CONFIG_MUSIC)) {
            mainManagerView.changeView(6, 2);
        } else if (e.getActionCommand().equals(ConfigMenuView.GO_CONFIG_USER)) {
            mainManagerView.changeView(7, 2);
        } else if (e.getActionCommand().equals(ConfigMenuView.GO_STATICS)) {
            mainManagerView.changeView(8, 2);
        } else if (e.getActionCommand().equals(ConfigMenuView.GO_INICIO)) {
            mainManagerView.changeView(1, 1);
        }
    }
}

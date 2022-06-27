package presentation.controller;

import presentation.view.MainManagerView;
import presentation.view.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que controla que vistas mostrar según la opción del menu general clickada
 */
public class MainViewController implements ActionListener {

    private MainManagerView mainManagerView;

    /**
     * Constructor
     * @param mainManagerView vista principal
     */
    public MainViewController(MainManagerView mainManagerView) {
        this.mainManagerView = mainManagerView;

    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(MainMenu.GO_INICIO)) {
            mainManagerView.changeView(1, 1);
        } else if (e.getActionCommand().equals(MainMenu.GO_BUSCADOR)) {
            mainManagerView.changeView(2, 1);
        } else if (e.getActionCommand().equals(MainMenu.GO_MISLISTAS)) {
            mainManagerView.changeView(3, 1);
        } else if (e.getActionCommand().equals(MainMenu.GO_SETTINGS)) {
            mainManagerView.changeView(6, 2);
        }

    }

}



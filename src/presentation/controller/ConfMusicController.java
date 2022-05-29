package presentation.controller;

import presentation.view.ConfMusicPanelView;
import presentation.view.MainManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que informa al CardLayout, de la vista general, algunos de los paneles para mostrar.
 */
public class ConfMusicController implements ActionListener {
    private MainManagerView mainManagerView;

    /**
     * Constructor
     * @param mainManagerView Vista general del programa
     */
    public ConfMusicController (MainManagerView mainManagerView) {
        this.mainManagerView = mainManagerView;
    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ConfMusicPanelView.GO_ADD_MUSIC)) {
            mainManagerView.changeView(9, 2);
        } else if (e.getActionCommand().equals(ConfMusicPanelView.GO_DELETE_MUSIC)) {
            mainManagerView.changeView(11, 2);
        }
    }
}

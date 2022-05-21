package presentation.controller;

import presentation.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener {
    private final MainView menuBarView;

    public MainViewController (MainView menuBarView) {
        this.menuBarView = menuBarView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(MainView.GO_INICIO)) {
            menuBarView.changeView(1);
            System.out.println(1);
        } else if (e.getActionCommand().equals(MainView.GO_BUSCADOR)) {
            menuBarView.changeView(2);
            System.out.println(2);
        } else if (e.getActionCommand().equals(MainView.GO_MISLISTAS)) {
            menuBarView.changeView(3);
            System.out.println(3);
        } else if (e.getActionCommand().equals(MainView.GO_SETTINGS)) {
            menuBarView.changeView(4);
            System.out.println(4);
        }
    }

}

package presentation.controller;

import presentation.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewController implements ActionListener  {

    private MainView view;

    public MainViewController(MainView view) {
        this.view = view;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case MainView.MANAGE_ACCOUNT:
                    view.showManageAccountContent();
                break;




        }
    }
}

package presentation.controller;

import presentation.view.MainConfigurationView;
import presentation.view.ManageAccountView;
import presentation.view.SideMenuPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainConfigurationViewController implements ActionListener  {

    private MainConfigurationView view;

    public MainConfigurationViewController(MainConfigurationView view) {
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
            case MainConfigurationView.MANAGE_ACCOUNT:
                    view.showManageAccountContent();
                break;




        }
    }
}

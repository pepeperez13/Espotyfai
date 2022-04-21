package presentation.controller;

import presentation.view.MainConfigurationView;
import presentation.view.SideMenuPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenuPanelController implements ActionListener{

    private SideMenuPanel sideMenuPanel;
    private MainConfigurationView view;

    public SideMenuPanelController(SideMenuPanel sideMenuPanel) {
        this.sideMenuPanel = sideMenuPanel;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case SideMenuPanel.MANAGE_ACCOUNT:
                    view.showManageAccountContent();
                    break;



            }
        }
    }


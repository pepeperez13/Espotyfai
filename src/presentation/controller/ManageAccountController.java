package presentation.controller;

import presentation.view.MainConfigurationView;
import presentation.view.ManageAccountView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAccountController implements ActionListener {


    private final ManageAccountView logOutView;



    public ManageAccountController(ManageAccountView logOutView) {
        this.logOutView = logOutView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ManageAccountView.LOGOUT_BUTTON:

                break;

            case ManageAccountView.DELETE_ACCOUNT_BUTTON:

                break;


        }
    }

}

package presentation.controller;

import business.UserManager;
import presentation.view.ManageAccountView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAccountController implements ActionListener {

    private final UserManager userManager;
    private final ManageAccountView logOutView;



    public ManageAccountController(UserManager userManager, ManageAccountView logOutView) {
        this.userManager = userManager;
        this.logOutView = logOutView;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ManageAccountView.LOGOUT_BUTTON:
                userManager.logout();
                break;

            case ManageAccountView.DELETE_ACCOUNT_BUTTON:
                userManager.deleteUser();
                break;


        }
    }

}

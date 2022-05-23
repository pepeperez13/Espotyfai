package presentation.controller;

import business.UserManager;
import persistance.UserDAO;
import persistance.dao.sql.SQLConnectorUser;
import presentation.view.ManageAccountView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAccountController implements ActionListener {

    private final UserManager userManager;
    private final ManageAccountView manageAccountView;



    public ManageAccountController( ManageAccountView manageAccountView) {
        UserDAO userDAO = new SQLConnectorUser();
        this.userManager = new UserManager(userDAO);
        this.manageAccountView = manageAccountView;
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ManageAccountView.LOGOUT_BUTTON:
                manageAccountView.showLogoutMessage();
                break;

            case ManageAccountView.DELETE_ACCOUNT_BUTTON:
                manageAccountView.showDeleteMessage();
                break;
            case ManageAccountView.NO_BUTTON_DELETE:

                break;
            case ManageAccountView.NO_BUTTON_LOGOUT:

                break;
            case ManageAccountView.YES_BUTTON_DELETE:
                userManager.deleteUser();
                break;
            case ManageAccountView.YES_BUTTON_LOGOUT:
                userManager.logout();
                break;

        }
    }

}

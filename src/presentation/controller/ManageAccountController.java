package presentation.controller;

import business.UserManager;
import persistance.UserDAO;
import persistance.dao.sql.SQLConnectorUser;
import presentation.view.InitView;
import presentation.view.ManageAccountView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAccountController implements ActionListener {

    private final UserManager userManager;
    private final ManageAccountView manageAccountView;
    private final InitController initController;


    public ManageAccountController(ManageAccountView manageAccountView, InitView initView) {
        UserDAO userDAO = new SQLConnectorUser();
        this.userManager = new UserManager(userDAO);
        this.manageAccountView = manageAccountView;
        initController= new InitController(initView);
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
                initController.refreshView(1);
                break;
            case ManageAccountView.YES_BUTTON_LOGOUT:
                userManager.logout();
                initController.refreshView(1);
                break;

        }
    }

}

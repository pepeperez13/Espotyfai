package presentation.view;

import business.UserManager;
import persistance.dao.sql.SQLConnector;
import persistance.dao.sql.SQLConnectorUser;
import presentation.controller.LoginController;

import javax.swing.*;

public class LoginView extends JPanel {
    private UserManager userManager;
    private LoginController registerViewController;
    public LoginView(SQLConnectorUser sqlConnector) {
        userManager = new UserManager(sqlConnector);
        registerViewController = new LoginController(this, userManager);
    }
}

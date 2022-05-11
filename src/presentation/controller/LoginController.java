package presentation.controller;

import business.UserManager;
import presentation.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginView registerView;
    private UserManager userManager;
    public LoginController(LoginView registerView, UserManager userManager) {
        this.registerView = registerView;
        this.userManager = userManager;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

package presentation.controller;

import presentation.view.LoginView;
import presentation.view.SignUpView;

public class InitController {
    private LoginView loginView;
    private SignUpView signUpView;

    public InitController(LoginView loginView, SignUpView signUpView) {
        this.loginView = loginView;
        this.signUpView = signUpView;
    }

    public int getOperation () {
        return loginView.getNumView() + signUpView.getNumView();
    }

}

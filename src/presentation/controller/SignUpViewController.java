package presentation.controller;

import business.UserManager;
import persistance.dao.sql.SQLConnector;

import presentation.view.SignUpView;

import javax.management.modelmbean.ModelMBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SignUpViewController implements ActionListener{
    private static SignUpView view;
    private static UserManager manager;

    private boolean userExistsError;
    private boolean emailExistsError;
    private boolean emailFormatError;
    private boolean passwordFormatError;
    private boolean passwordConfirmationError;

    public SignUpViewController (SignUpView signUpView, UserManager userManager) {
        this.view = signUpView;
        this.manager = userManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SignUpView.REGISTER_BUTTON)) {
            System.out.println("HOLAAAAAAA");

            if (checkDataCorrect()) {
                manager.insertNewUser(view.getUserName(), view.getEmail(), view.getPassword());
                view.goLogin(2);
            } else {
                view.showErrorMessage(userExistsError, emailExistsError, emailFormatError, passwordFormatError, passwordConfirmationError);
                System.out.println("Hay error");
            }
        }

    }

    // Clase que comprobará que todos los parámetros de login sean correctos
    public boolean checkDataCorrect() {

        userExistsError = manager.checkUsernameExistance(view.getUserName());

        emailExistsError = manager.checkEmailExistance(view.getEmail());

        emailFormatError = manager.checkEmailFormat(view.getEmail());

        passwordFormatError = manager.checkPasswordFormat(view.getPassword());

        passwordConfirmationError = !view.getPassword().equals(view.getConfirmation());

        return !userExistsError && !emailExistsError && !emailFormatError && !passwordFormatError && !passwordConfirmationError;
    }

}

package presentation.controller;

import business.UserManager;
import persistance.UserDAO;
import persistance.dao.sql.SQLConnector;

import persistance.dao.sql.SQLConnectorUser;
import presentation.view.SignUpView;

import javax.management.modelmbean.ModelMBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SignUpViewController implements ActionListener{
    private static SignUpView view;
    private UserDAO userDAO = new SQLConnectorUser();
    private UserManager manager;

    private boolean userExistsError;
    private boolean emailExistsError;
    private boolean emailFormatError;
    private boolean passwordFormatError;
    private boolean passwordConfirmationError;
    private InitController initController;

    public SignUpViewController (SignUpView signUpView) {
        this.view = signUpView;
        manager = new UserManager(userDAO);
        initController = new InitController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SignUpView.REGISTER_BUTTON)) {

            if (checkDataCorrect()) {
                manager.insertNewUser(view.getUserName(), view.getEmail(), view.getPassword());
                initController.refreshView(2);
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

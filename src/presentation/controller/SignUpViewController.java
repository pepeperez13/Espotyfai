package presentation.controller;

import business.Store;
import business.UserManager;
import persistance.UserDAO;


import persistance.dao.sql.SQLConnectorUser;
import presentation.view.InitView;
import presentation.view.SignUpView;

import javax.management.modelmbean.ModelMBean;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SignUpViewController implements ActionListener{
    private static SignUpView view;
    private final UserManager manager;
    private Store store;
    private boolean userExistsError;
    private boolean emailExistsError;
    private boolean emailFormatError;
    private boolean passwordFormatError;
    private boolean passwordConfirmationError;
    private final InitController initController;

    public SignUpViewController (SignUpView signUpView, InitView initView) {
        view = signUpView;
        UserDAO userDAO = new SQLConnectorUser();
        manager = new UserManager(userDAO);
        initController = new InitController(initView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SignUpView.REGISTER_BUTTON)) {

            if (checkDataCorrect()) {
                manager.insertNewUser(view.getUserName(), view.getEmail(), view.getPassword());
                //store = new Store(manager.getCurrentUser(view.getUserName()));
                store.setUser(manager.getCurrentUser(view.getUserName()));
                initController.refreshView(3);
            } else {
                view.showErrorMessage(userExistsError, emailExistsError, emailFormatError, passwordFormatError, passwordConfirmationError);
            }
        } else if (e.getActionCommand().equals(SignUpView.LOGIN_BUTTON)) {
            initController.refreshView(2);
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

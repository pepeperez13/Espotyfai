package presentation.controller;

import business.Store;
import business.UserManager;
import persistance.UserDAO;

import persistance.dao.sql.SQLConnectorUser;
import presentation.view.InitView;
import presentation.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class   LoginController implements ActionListener {
    private final LoginView loginView;
    private final UserManager manager;

    private boolean userExistsError;
    private boolean passwordConfirmationError;
    private final InitController initController;
    //private Store store;
    public LoginController(LoginView loginView, InitView initView) {
        this.loginView = loginView;
        UserDAO userDAO = new SQLConnectorUser();
        manager = new UserManager(userDAO);
        initController = new InitController(initView);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(LoginView.INICIO)) {

            if (checkDataCorrect()) {
                //Retornar el user current. Buscando con el login introducido y retornando de la base de datos
                //store = new Store();
                Store.setUser(manager.getCurrentUser(loginView.getUserName()));
                initController.refreshView(3);
            } else {
                loginView.showErrorMessage(userExistsError, passwordConfirmationError);
            }

        } else if (e.getActionCommand().equals(LoginView.REGISTRO)) {
            initController.refreshView(1);
        }
    }

    // Clase que comprobará que todos los parámetros de login sean correctos
    public boolean checkDataCorrect() {
        userExistsError = !manager.checkUsernameExistance(loginView.getUserName());
        if (!userExistsError) {
            passwordConfirmationError = !manager.checkCorrectPassword(loginView.getPassword(), loginView.getUserName());
        }
        return !userExistsError && !passwordConfirmationError;
    }
}

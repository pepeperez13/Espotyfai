package presentation.controller;

import business.Store;
import business.UserManager;
import persistance.UserDAO;
import persistance.dao.sql.SQLConnector;
import persistance.dao.sql.SQLConnectorUser;
import presentation.view.InitView;
import presentation.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class   LoginController implements ActionListener {
    private LoginView loginView;
    private UserManager manager;

    private boolean userExistsError;
    private boolean passwordConfirmationError;
    private UserDAO userDAO = new SQLConnectorUser();
    private InitController initController;
    private Store store;
    public LoginController(LoginView loginView, InitView initView) {
        this.loginView = loginView;
        manager = new UserManager(userDAO);
        initController = new InitController(initView);
        store = new Store();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(LoginView.INICIO)) {

            if (checkDataCorrect()) {
                initController.refreshView(3);
                //Retornar el user current. Buscando con el login introducido y retornando de la base de datos
                store.setUser(manager.getCurrentUser(loginView.getUserName()));
            } else {
                //Mostrar error
            }

        } else if (e.getActionCommand().equals(LoginView.REGISTRO)) {
            initController.refreshView(1);
        }
    }

    // Clase que comprobará que todos los parámetros de login sean correctos
    public boolean checkDataCorrect() {
        userExistsError = manager.checkUsernameExistance(loginView.getUserName());
        if (!userExistsError) {
            passwordConfirmationError = manager.checkCorrectPassword(loginView.getPassword(), loginView.getUserName());
        }
        return !userExistsError && !passwordConfirmationError;
    }
}

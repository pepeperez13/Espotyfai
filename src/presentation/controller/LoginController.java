package presentation.controller;

import business.Owner;
import business.UserManager;
import persistance.UserDAO;

import persistance.dao.sql.SQLConnectorUser;
import presentation.view.InitView;
import presentation.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que controla todos los posibles eventos generados desde la vista del login
 * @author Jose Perez
 */
public class LoginController implements ActionListener {

    private final LoginView loginView;
    private final UserManager manager;
    private boolean userExistsError;
    private boolean passwordConfirmationError;
    private final InitController initController;

    /**
     * Metodo constructor que recibe e incializa clases necesarias para el controlador
     * @param loginView vista del login
     * @param initView vista inicial, necesaria para el controlador
     */
    public LoginController(LoginView loginView, InitView initView) {
        this.loginView = loginView;
        UserDAO userDAO = new SQLConnectorUser();
        manager = new UserManager(userDAO);
        initController = new InitController(initView);
    }

    /**
     * Metodo que gestiona los diferentes eventos generados por la vista del login
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(LoginView.INICIO)) {
            // SI los datos son correctos, se actualiza el store y se pasa a la vista principal. En caso contrario, mensaje de error
            if (checkDataCorrect()) {
                Owner.setUser(manager.getCurrentUser(loginView.getUserName()));
                initController.refreshView(3);
            } else {
                loginView.showErrorMessage(userExistsError, passwordConfirmationError);
            }
        } else if (e.getActionCommand().equals(LoginView.REGISTRO)) {
            initController.refreshView(1);
        }
    }

    /**
     * Metodo que, a traves del manager, comprobara que los datos introducidos en el login son corrector
     * @return booleano que permite saber si todos los datos eran correctos o no
     */
    public boolean checkDataCorrect() {
        // Comprobamos si existe el usuario y por tanto no hay error
        userExistsError = !manager.checkUsernameExistance(loginView.getUserName());
        // Si existe el usuario, comprobamos la contraseña
        if (!userExistsError) {
            passwordConfirmationError = !manager.checkCorrectPassword(loginView.getPassword(), loginView.getUserName());
        }
        return !userExistsError && !passwordConfirmationError;
    }
}

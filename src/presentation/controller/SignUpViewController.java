package presentation.controller;

import business.Owner;
import business.UserManager;
import persistance.UserDAO;

import persistance.dao.sql.SQLConnectorUser;
import presentation.view.InitView;
import presentation.view.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que controla los diferentes eventos de la vista de Sign Up
 * @author Jose perez
 */
public class SignUpViewController implements ActionListener{
    private static SignUpView view;
    private final UserManager manager;
    private boolean userExistsError;
    private boolean emailExistsError;
    private boolean emailFormatError;
    private boolean passwordFormatError;
    private boolean passwordConfirmationError;
    private final InitController initController;

    /**
     * Constructor de la clase que inicializa otras clases necesarias
     * @param signUpView vista detallada que estará controlando
     * @param initView vista incial que controla el resto de vistas
     */
    public SignUpViewController (SignUpView signUpView, InitView initView) {
        view = signUpView;
        UserDAO userDAO = new SQLConnectorUser();
        manager = new UserManager(userDAO);
        initController = new InitController(initView);
    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SignUpView.REGISTER_BUTTON)) {
            if (checkDataCorrect()) {
                manager.insertNewUser(view.getUserName(), view.getEmail(), view.getPassword());
                Owner.setUser(manager.getCurrentUser(view.getUserName()));
                initController.refreshView(3);
            } else {
                view.showErrorMessage(userExistsError, emailExistsError, emailFormatError, passwordFormatError, passwordConfirmationError);
            }
        } else if (e.getActionCommand().equals(SignUpView.LOGIN_BUTTON)) {
            initController.refreshView(2);
        }

    }

    /**
     * Llama a distintos metodos del manager que comprueban si ha habido algún error en los datos introducidos
     * @return booleano que indica si todos los datos son correctos (true) o no (falso)
     */
    private boolean checkDataCorrect() {

        userExistsError = manager.checkUsernameExistance(view.getUserName());

        emailExistsError = manager.checkEmailExistance(view.getEmail());

        emailFormatError = manager.checkEmailFormat(view.getEmail());

        passwordFormatError = manager.checkPasswordFormat(view.getPassword());

        passwordConfirmationError = !view.getPassword().equals(view.getConfirmation());

        return !userExistsError && !emailExistsError && !emailFormatError && !passwordFormatError && !passwordConfirmationError;
    }

}

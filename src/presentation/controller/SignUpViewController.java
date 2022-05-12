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

    // Luego esto irá en el main (es de prueba). La cosa es vincular el controller con la vista para que funcionen
    // los listeners. Mirar solución AC6
    /*public static void main (String[] args) {
        SQLConnector sql = new SQLConnector();
        SignUpViewController controller = new SignUpViewController();
        SignUpView signUpView = new SignUpView(controller);
        manager = new UserManager(sql);
        view = signUpView;
        signUpView.setVisible(true);
    }*/

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
                System.out.println("Todo ok");
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

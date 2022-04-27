package presentation.controller;

import business.UserManager;
import presentation.view.signUp.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpViewController implements ActionListener{
    private static SignUpView view;
    private static UserManager manager;

    private static boolean USER_EXISTS_ERROR;
    private static boolean EMAIL_EXISTS_ERROR;
    private static boolean EMAIL_FORMAT_ERROR;
    private static boolean PASSWORD_FORMAT_ERROR;
    private static boolean PASSWORD_CONFIRMATION_ERROR;

    // Luego esto irá en el main (es de prueba). La cosa es vincular el controller con la vista para que funcionen
    // los listeners. Mirar solución AC6
    public static void main (String[] args) {
        SignUpViewController controller = new SignUpViewController();
        SignUpView signUpView = new SignUpView(controller);
        manager = new UserManager();
        view = signUpView;
        signUpView.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SignUpView.REGISTER_BUTTON)) {
            System.out.println("HOLAAAAAAA");

            if (checkDataCorrect()) {
                // navegar a la página principal
                System.out.println("Todo ok");
            } else {
                view.showErrorMessage(USER_EXISTS_ERROR, EMAIL_EXISTS_ERROR, EMAIL_FORMAT_ERROR, PASSWORD_FORMAT_ERROR, PASSWORD_CONFIRMATION_ERROR);
                System.out.println("Hay error");
            }
        }

    }

    // Clase que comprobará que todos los parámetros de login sean correctos
    public boolean checkDataCorrect() {

        if (manager.checkUsernameExistance(view.getUserName())) {
            USER_EXISTS_ERROR = true;
        }

        if (manager.checkEmailExistance(view.getEmail())) {
            EMAIL_EXISTS_ERROR = true;
        }

        if (manager.checkEmailFormat(view.getEmail())) {
            EMAIL_FORMAT_ERROR = true;
        }

        if (manager.checkPasswordFormat(view.getPassword())) {
            PASSWORD_FORMAT_ERROR = true;
        }

        if (!view.getPassword().equals(view.getConfirmation())) {
            PASSWORD_CONFIRMATION_ERROR = true;
        }

        return false;
    }

}

package presentation.controller;

import business.UserManager;
import persistance.dao.sql.SQLConnector;

import presentation.view.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpViewController implements ActionListener{
    private SignUpView view;
    private UserManager manager;

    private static boolean USER_EXISTS_ERROR;
    private static boolean EMAIL_EXISTS_ERROR;
    private static boolean EMAIL_FORMAT_ERROR;
    private static boolean PASSWORD_FORMAT_ERROR;
    private static boolean PASSWORD_CONFIRMATION_ERROR;

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
                view.showErrorMessage(USER_EXISTS_ERROR, EMAIL_EXISTS_ERROR, EMAIL_FORMAT_ERROR, PASSWORD_FORMAT_ERROR, PASSWORD_CONFIRMATION_ERROR);
                System.out.println("Hay error");
            }
        }

    }

    // Clase que comprobará que todos los parámetros de login sean correctos
    public boolean checkDataCorrect() {

        USER_EXISTS_ERROR = manager.checkUsernameExistance(view.getUserName());

        EMAIL_EXISTS_ERROR = manager.checkEmailExistance(view.getEmail());

        EMAIL_FORMAT_ERROR = manager.checkEmailFormat(view.getEmail());

        PASSWORD_FORMAT_ERROR = manager.checkPasswordFormat(view.getPassword());

        PASSWORD_CONFIRMATION_ERROR = !view.getPassword().equals(view.getConfirmation());

        return !USER_EXISTS_ERROR && !EMAIL_EXISTS_ERROR && !EMAIL_FORMAT_ERROR && !PASSWORD_FORMAT_ERROR && !PASSWORD_CONFIRMATION_ERROR;
    }

}

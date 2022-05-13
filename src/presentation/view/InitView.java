package presentation.view;

import presentation.controller.InicioController;
import presentation.controller.InitController;
import presentation.controller.LoginController;
import presentation.controller.SignUpViewController;

import javax.swing.*;
import java.awt.*;

public class InitView extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final InicioController inicioController = new InicioController();
    //private final SignUpViewController signUpViewController = new SignUpViewController();
    //private final LoginController loginController = new LoginController();

    private InitController initController;

    public InitView () {
        setTitle("SPOTIFAI");
        setLayout(new BorderLayout());

        //initController = new InitController();

        JPanel content = setView();
        add(content);

        setSize(1500, 900);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
    }

    private JPanel setView () {
        JPanel content = new JPanel();

        InicioView inicioView = new InicioView();
        SignUpView signUpView = new SignUpView();
        LoginView loginView = new LoginView();

        content.setLayout(cardLayout);

        content.add(signUpView, "1");
        content.add(loginView, "2");
        //content.add(inicioView, "3");

        //Falta comunicacion entre los controllers para que informen a la vista Main lo que tiene que mostrar
        cardLayout.show(content, "1");

        return content;
    }
}

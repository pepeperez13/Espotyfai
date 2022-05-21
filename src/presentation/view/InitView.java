package presentation.view;

import presentation.controller.InitController;

import javax.swing.*;
import java.awt.*;

public class InitView extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    JPanel content = new JPanel();
    //private final SignUpViewController signUpViewController = new SignUpViewController();
    //private final LoginController loginController = new LoginController();

    private InitController initController;

    public InitView () {
        setTitle("SPOTIFAI");
        setLayout(new BorderLayout());

        initController = new InitController(this);

        JPanel content = setView();
        add(content);

        setSize(1500, 900);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel setView () {
        MainView mainView = new MainView();
        SignUpView signUpView = new SignUpView(this);
        LoginView loginView = new LoginView(this);

        content.setLayout(cardLayout);

        content.add(signUpView, "1");
        content.add(loginView, "2");
        content.add(mainView, "3");

        return content;
    }

    public void changeView (int num) {
        cardLayout.show(content, String.valueOf(num));
    }
}
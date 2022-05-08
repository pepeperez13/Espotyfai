package presentation.view;

import persistance.dao.sql.SQLConnector;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public static final String SIGN_UP = "SIGN_UP";
    public static final String REGISTER = "REGISTER";
    public static final String CONF = "CONF";

    private final SQLConnector sqlConnector = new SQLConnector();
    //private final CardLayout cardLayout = new CardLayout();

    public MainView() {
        setTitle("SPOTIFAI");
        setLayout(new BorderLayout());

        JPanel content = setView();
        add(content);

        setSize(1500, 900);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel setView () {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());

        //SideBarView sideBarView = new SideBarView(sqlConnector);
        SignUpView signUpView = new SignUpView(sqlConnector);
        //LoginView loginView = new LoginView(sqlConnector);

        //content.setLayout(cardLayout);

        content.add(signUpView, BorderLayout.CENTER);
        //content.add(loginView, 2);
        //content.add(sideBarView, 3);

        //Falta comunicacion entre los controllers para que informen a la vista Main lo que tiene que mostrar

        return content;
    }
}
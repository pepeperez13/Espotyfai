package presentation.view;

import persistance.dao.sql.SQLConnector;
import persistance.dao.sql.SQLConnectorUser;

import javax.swing.*;
import java.awt.*;

public class InitView extends JPanel {

    public static final String SIGN_UP = "SIGN_UP";
    public static final String REGISTER = "REGISTER";
    public static final String CONF = "CONF";

    private final SQLConnectorUser sqlConnector = new SQLConnectorUser();
    //private final CardLayout cardLayout = new CardLayout();

    public InitView() {
        setLayout(new BorderLayout());

        JPanel content = setView();
        add(content);

        setSize(1500, 900);

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
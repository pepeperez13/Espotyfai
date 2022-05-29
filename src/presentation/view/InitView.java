package presentation.view;

import presentation.controller.InitController;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que contiene el único JFrame del programa e inicializa las siguientes vistas:
 * -REGISTRO
 * -INICIO DE SESIÓN
 * -VISTA PRINCIPAL
 */
public class InitView extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    JPanel content = new JPanel();
    private InitController initController;

    /**
     * Constructor que configura el JFrame
     */
    public InitView () {
        setTitle("ESPOTIFY");
        setLayout(new BorderLayout());

        initController = new InitController(this);

        JPanel content = setView();
        add(content);

        setSize(1500, 900);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Método que configura el CardLayout
     * @return JPanel
     */
    private JPanel setView () {
        MainManagerView mainManagerView = new MainManagerView(this);
        SignUpView signUpView = new SignUpView(this);
        LoginView loginView = new LoginView(this);

        content.setLayout(cardLayout);

        content.add(signUpView, "1");
        content.add(loginView, "2");
        content.add(mainManagerView, "3");

        return content;
    }

    /**
     * Método que muestra el panel que indica su controlador
     * @param num Identificador de la vista
     */
    public void changeView (int num) {
        cardLayout.show(content, String.valueOf(num));
    }
}
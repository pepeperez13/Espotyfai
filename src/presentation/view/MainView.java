package presentation.view;

import presentation.controller.MainViewController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainView extends JFrame {

    public static final String MANAGE_ACCOUNT = "MANAGE_ACCOUNT";

    private JPanel content;
    private SideMenuPanel sideBar;
    private BottomBarPanel botBar;

    private MainViewController controller;

    public MainView() throws IOException {


        final JPanel pane = new JPanel(new BorderLayout());

        this.content = new JPanel(new CardLayout());
        this.content.setBackground(new Color(0, 0, 0));

        this.sideBar = new SideMenuPanel();
        this.botBar = new BottomBarPanel();

        pane.add(this.content, BorderLayout.CENTER);
        pane.add(this.sideBar, BorderLayout.WEST);
        pane.add(this.botBar, BorderLayout.SOUTH);

        getContentPane().add(pane);

        setPreferredSize(new Dimension(970, 760));
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void showManageAccountContent() {
        ManageAccountView panel = new ManageAccountView();
        this.content.add(panel);
    }


    public static void main(String[] args) throws IOException {
        MainView conf = new MainView();
        conf.setVisible(true);
    }

}

        //showManageAccountContent();;


//        SideMenuPanel sp = new SideMenuPanel(this);
//
//
//        setTitle("SPOTIFAI");
//        setLocationRelativeTo(null);
//        setSize(1500, 900);
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        controller = new ConfigurationMenuController(this);
//
//        //Creamos el Jpanel de la izquierda y derecha con layouts
//        //f.add(createMenuPanelFront(), BorderLayout.WEST);
//        //f.add(createContentPanel(), BorderLayout.EAST);
//        createMenuPanelFront();
//        createContentPanel();


//    public void createMenuPanelFront() {
       // menuPanel = new JPanel();
//        //JPanel subPanel = new JPanel();
//        JLabel nuestroLogo = new JLabel();
//        JLabel botonInicio = new JLabel();
//        JButton gestionarCuenta = new JButton();
//        JLabel gestionarMusica = new JLabel();
//        JLabel misEstadisticas = new JLabel();
//        JSeparator lineSeparador1 = new JSeparator();
//        JSeparator lineSeparador2 = new JSeparator();
//        JSeparator lineSeparador3 = new JSeparator();
//
//        menuPanel.setBackground(new Color(191, 105, 240));
//        menuPanel.setBounds(0, 0, 200, 900);
//        //menuPanel.setLayout(new GridLayout(3, 1, 0, 3));
//        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
//
//        //subPanel.setBackground(new Color(191, 105, 240));
//        //subPanel.setBounds(0, 0, 200, 200);
//        //subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
//
//        // add commands
//        gestionarCuenta.setActionCommand(MANAGE_ACCOUNT);
//        gestionarCuenta.addActionListener(controller);
//
//        nuestroLogo.setText("NuestroLogo");
//        botonInicio.setText("Boton");
//        gestionarMusica.setText("Gestionar música");
//        gestionarCuenta.setText("Gestionar cuenta");
//        misEstadisticas.setText("Mis estadísticas");
//        lineSeparador1.setOrientation(SwingConstants.HORIZONTAL);
//        //lineSeparador1.setSize(200, 1);
//        lineSeparador2.setOrientation(SwingConstants.HORIZONTAL);
//        //lineSeparador2.setSize(200, 1);
//        lineSeparador3.setOrientation(SwingConstants.HORIZONTAL);
//        //lineSeparador3.setSize(200, 1);
//
//        menuPanel.add(nuestroLogo);
//        menuPanel.add(botonInicio);
//        //menuPanel.add(subPanel);
//        menuPanel.add(gestionarCuenta);
//        menuPanel.add(lineSeparador1);
//        menuPanel.add(gestionarMusica);
//        menuPanel.add(lineSeparador2);
//        menuPanel.add(misEstadisticas);
//        menuPanel.add(lineSeparador3);

        //getContentPane().add(menuPanel);
  //  }
/*
    public void createContentPanel () {
        this.content = new JPanel();
        this.content.setBackground(new Color(255, 255, 255));
        this.content.setBounds(200, 0, 1300, 900);

        getContentPane().add(content);
    }
    */





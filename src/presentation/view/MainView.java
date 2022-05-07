package presentation.view;

import persistance.dao.sql.SQLConnector;
import presentation.controller.AddMusicController;
import presentation.controller.SideBarController;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private SideBarView menuFrontal;
    private SideBarController menuFrontalControler;
    private SQLConnector sqlConnector = new SQLConnector();
    public MainView() {
        setTitle("SPOTIFAI");
        //Seteamos el Manager Layout de la pagina del menu principal
        setLayout(new BorderLayout());

        menuFrontal = new SideBarView(sqlConnector);
        menuFrontalControler = new SideBarController(menuFrontal);
        menuFrontal.registerController(menuFrontalControler);
        setView();

        setSize(1500, 900);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void setView () {
        JPanel content = new JPanel();
        content = menuFrontal.createPanel();

        getContentPane().add(menuFrontal, BorderLayout.WEST);
        getContentPane().add(content, BorderLayout.CENTER);
    }
}
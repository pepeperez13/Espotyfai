package presentation.view;

import presentation.controller.MainConfigurationViewController;

import javax.swing.*;
import java.awt.*;

public class MainConfigurationView extends JFrame {

    public static final String MANAGE_ACCOUNT = "MANAGE_ACCOUNT";

    private JPanel content;
    private SideMenuPanel sideBar;
    private BottomBarPanel botBar;



    public MainConfigurationView() {


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
        repaint();
    }


    public static void main(String[] args) {
        MainConfigurationView conf = new MainConfigurationView();
        conf.setVisible(true);
    }

}






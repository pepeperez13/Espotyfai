package presentation.view;

import presentation.JImagePanel;
import presentation.controller.ManageAccountController;

import javax.swing.*;
import java.awt.*;

public class ManageAccountView extends JPanel {

    public static final String BACK_BUTTON = "BACK_BUTTON";
    public static final String LOGOUT_BUTTON = "LOGOUT_BUTTON";
    public static final String DELETE_ACCOUNT_BUTTON = "DELETE_ACCOUNT_BUTTON";
    private MainView configurationMenu;
    private ManageAccountController controller;

    public ManageAccountView() {
        controller = new ManageAccountController(this);
        createView(controller);
    }

    public void createView(ManageAccountController logOutController){
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        JButton logoutButton=new JButton();
        JButton deleteButton=new JButton();
        JButton backButton=new JButton();
        JImagePanel jImagePanel=new JImagePanel("images/background.jpeg");

        logoutButton.setText("LogOut");
        deleteButton.setText("Delete account");
        backButton.setText("Back");


        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.setActionCommand(BACK_BUTTON);
        logoutButton.setActionCommand(LOGOUT_BUTTON);
        deleteButton.setActionCommand(DELETE_ACCOUNT_BUTTON);

        backButton.addActionListener(logOutController);
        logoutButton.addActionListener(logOutController);
        deleteButton.addActionListener(logOutController);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));

        p.add(Box.createVerticalGlue());
        p.add(setLogo());
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(logoutButton);
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(deleteButton);
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(backButton);
        p.add(Box.createVerticalGlue());
        add(jImagePanel);
        add(p);



        setVisible(true);
    }
    private JLabel setLogo () {
        ImageIcon logoImage = new ImageIcon("images/spotiLogo.png");
        Image image = logoImage.getImage();
        image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        logoImage = new ImageIcon(image);
        JLabel logoImageLabel = new JLabel(logoImage);
        logoImageLabel.setBounds(500, 10, 500, 120);

        logoImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return logoImageLabel;
    }

}

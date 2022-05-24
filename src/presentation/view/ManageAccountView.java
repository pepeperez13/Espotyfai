package presentation.view;

import presentation.controller.ManageAccountController;


import javax.swing.*;
import java.awt.*;

public class ManageAccountView extends JPanel {


    public static final String LOGOUT_BUTTON = "LOGOUT_BUTTON";
    public static final String DELETE_ACCOUNT_BUTTON = "DELETE_ACCOUNT_BUTTON";
    public static final String YES_BUTTON_LOGOUT="YES_BUTTON_LOGOUT";
    public static final String NO_BUTTON_LOGOUT="NO_BUTTON_LOGOUT";
    public static final String NO_BUTTON_DELETE="NO_BUTTON_DELETE";
    public static final String YES_BUTTON_DELETE="YES_BUTTON_DELETE";

    private ManageAccountController manageAccountController;

    public ManageAccountView(InitView initView) {
         manageAccountController= new ManageAccountController(this,initView);
        createView(manageAccountController);
    }

    public void createView(ManageAccountController manageAccountController){
        ImageIcon logoImage = new ImageIcon("images/fondoBlanco.jpg");
        Image image = logoImage.getImage();
        image = image.getScaledInstance(800, 300, Image.SCALE_DEFAULT);
        logoImage = new ImageIcon(image);
        JLabel logoImageLabel = new JLabel(logoImage);
        logoImageLabel.setBackground(Color.white);
        logoImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton logoutButton=new JButton();
        JButton deleteButton=new JButton();

        logoutButton.setText("Logout");
        deleteButton.setText("Delete account");
        logoutButton.setFont(new Font("Dialog", Font.BOLD,20));
        deleteButton.setFont(new Font("Dialog", Font.BOLD,20));

        logoutButton.setForeground(Color.black);
        logoutButton.setBackground(Color.CYAN);

        deleteButton.setForeground(Color.black);
        deleteButton.setBackground(Color.CYAN);

        logoutButton.setBorderPainted(false);
        deleteButton.setBorderPainted(false);


        logoutButton.setActionCommand(LOGOUT_BUTTON);
        deleteButton.setActionCommand(DELETE_ACCOUNT_BUTTON);


        logoutButton.addActionListener(manageAccountController);
        deleteButton.addActionListener(manageAccountController);



        JPanel panelbox= new JPanel();
        JPanel p = new JPanel();
        GridLayout gridLayout= new GridLayout(2,2);
        p.setLayout(gridLayout);
        panelbox.setLayout(new BoxLayout(panelbox,BoxLayout.Y_AXIS));
//        JPanel blanco= new JPanel();
//        blanco.setBackground(Color.black);
//        blanco.setMinimumSize(new Dimension(500,500));
//        blanco.setSize(5000,5000);

        gridLayout.setVgap(30);
        gridLayout.setHgap(30);
        p.setBackground(new Color(255,255,255));

        JLabel askLogout = new JLabel();
        JLabel askDeleteAccount = new JLabel();

        askLogout.setFont(new Font("Dialog",Font.BOLD,20));
        askDeleteAccount.setFont(new Font("Dialog", Font.BOLD,20));

        askLogout.setText("Do you want to logout?");
        askDeleteAccount.setText("Do you want to delete your account?");
        p.add(askLogout);
        p.add(logoutButton);
        p.add(askDeleteAccount);
        p.add(deleteButton);
        this.setBackground(new Color(255,255,255));
        p.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        panelbox.add(logoImageLabel);
        panelbox.add(p);
        add(panelbox);

        setVisible(true);


    }
    public void showLogoutMessage(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));

        JButton jYes= new JButton("Yes");
        JButton jNo= new JButton("No");

        jNo.setActionCommand(NO_BUTTON_LOGOUT);
        jNo.addActionListener(manageAccountController);

        jYes.setActionCommand(YES_BUTTON_LOGOUT);
        jYes.addActionListener(manageAccountController);


        panel.add(jYes);
        panel.add(jNo);



        //showPlaylistsController = new ShowPlaylistsController(this);


        JOptionPane.showConfirmDialog(this, panel, "Are you sure ?", JOptionPane.DEFAULT_OPTION);


    }
    public void showDeleteMessage(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));

        JButton jYes= new JButton("Yes");
        JButton jNo= new JButton("No");

        jNo.setActionCommand(NO_BUTTON_DELETE);
        jNo.addActionListener(manageAccountController);

        jYes.setActionCommand(YES_BUTTON_DELETE);
        jYes.addActionListener(manageAccountController);

        panel.add(jYes);
        panel.add(jNo);

        //showPlaylistsController = new ShowPlaylistsController(this);




        JOptionPane.showMessageDialog(this, panel, "Are you sure ?", JOptionPane.PLAIN_MESSAGE);


    }
}

package presentation.view;

import persistance.dao.sql.SQLConnector;
import persistance.dao.sql.SQLConnectorSong;
import presentation.controller.ConfMusicController;
import presentation.controller.SideBarController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SideBarView extends JPanel{ /**Cambiar a JPanel*/
    public static final String INICIO_COMMAND = "INICIO_COMMAND";
    public static final String GO_CONFIG_MUSIC = "GO_CONFIG_MUSIC";
    public static final String GO_CONFIG_USER = "GO_CONFIG_USER";
    public static final String GO_STATICS = "GO_STATICS";
    public int numView;
    private SideBarController sideBarController;
    private JButton inicio;
    private JButton jbconfMusic;
    private JButton jbconfUsuario;
    private JButton jbconfEstadisticas;
    private ManageAccountView manageAccountView = new ManageAccountView();
    private ConfMusicPanelView confMusicPanelView = new ConfMusicPanelView();
    private ConfMusicController confMusicController;
    private StaticsPanelView staticsPanel = new StaticsPanelView();
    private AddMusicPanelView addMusicPanel;
    private ShowMusicPanelView showMusicPanel = new ShowMusicPanelView();
    private DeleteMusicPanelView deleteMusicPanel = new DeleteMusicPanelView();
    private JPanel cardPanel = new JPanel();
    private JPanel menuFrontal = new JPanel();
    private CardLayout c = new CardLayout();
    private final GridBagConstraints constraint = new GridBagConstraints();

    public SideBarView () {
        setLayout(new BorderLayout());

        Dimension dimension = getPreferredSize();
        dimension.width = 200;
        setPreferredSize(dimension);

        setLayout(new BorderLayout());
        //setLayout(new GridBagLayout());
        setBackground(new Color(191, 105, 240));

        sideBarController = new SideBarController(this);
        SQLConnectorSong sqlConnectorSong = new SQLConnectorSong();
        addMusicPanel = new AddMusicPanelView(sqlConnectorSong);

        menuFrontal = configureMenuFrontal();
        cardPanel = configureCardPanel();
        add(menuFrontal, BorderLayout.WEST);
        add(cardPanel, BorderLayout.CENTER);

        setSize(1500, 900);
        /*setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
    }

    private JPanel configureMenuFrontal () {
        menuFrontal.setBackground(new Color(191, 105, 240));
        menuFrontal.setLayout(new GridBagLayout());
        constraint.fill = GridBagConstraints.NONE;

        ImageIcon logoSimbol = new ImageIcon("Images/logo.png");
        Image image1 = logoSimbol.getImage();
        image1 = image1.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        logoSimbol = new ImageIcon(image1);
        JLabel logoApp = new JLabel(logoSimbol);
        logoApp.setBounds(0, 0, 100, 100);
        logoApp.setAlignmentX(Component.CENTER_ALIGNMENT);


        Icon inicioSimbol = new ImageIcon("Images/icons8-casa-24.png");
        inicio = new JButton(inicioSimbol);
        inicio.setBorder(BorderFactory.createEmptyBorder());
        inicio.setOpaque(false);
        inicio.setContentAreaFilled(false);
        inicio.setBorderPainted(false);
        inicio.setActionCommand(INICIO_COMMAND);
        //inicio.addActionListener(this);

        jbconfMusic = new JButton();
        jbconfMusic.setText("Gestionar música");
        jbconfMusic.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfMusic.setForeground(new Color(255, 255, 255));
        jbconfMusic.setBackground(new Color(191, 105, 240));
        jbconfMusic.setBorderPainted(false);
        jbconfMusic.setActionCommand(GO_CONFIG_MUSIC);
        //jbconfMusic.addActionListener(this);

        jbconfUsuario = new JButton();
        jbconfUsuario.setText("Gestionar cuenta");
        jbconfUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfUsuario.setForeground(new Color(255, 255, 255));
        jbconfUsuario.setBackground(new Color(191, 105, 240));
        jbconfUsuario.setBorderPainted(false);
        jbconfUsuario.setActionCommand(GO_CONFIG_USER);
        //jbconfUsuario.addActionListener(this);

        jbconfEstadisticas = new JButton();
        jbconfEstadisticas.setText("Mis estadísticas");
        jbconfEstadisticas.setFont(new Font("Arial", Font.BOLD, 18));
        jbconfEstadisticas.setForeground(new Color(255, 255, 255));
        jbconfEstadisticas.setBackground(new Color(191, 105, 240));
        jbconfEstadisticas.setBorderPainted(false);
        jbconfEstadisticas.setActionCommand(GO_STATICS);
        //jbconfEstadisticas.addActionListener(this);

        JSeparator separator1 = new JSeparator();
        separator1.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator separator2 = new JSeparator();
        separator2.setOrientation(SwingConstants.HORIZONTAL);
        JSeparator separator3 = new JSeparator();
        separator3.setOrientation(SwingConstants.HORIZONTAL);

        JPanel groupBotones = new JPanel();
        groupBotones.setBackground(new Color(191, 105,240));
        groupBotones.setLayout(new BoxLayout(groupBotones, BoxLayout.Y_AXIS));

        registerController(sideBarController);

        groupBotones.add(jbconfMusic);
        groupBotones.add(separator1);
        groupBotones.add(jbconfUsuario);
        groupBotones.add(separator2);
        groupBotones.add(jbconfEstadisticas);
        groupBotones.add(separator3);

        //Colocamos el Icono de la app
        constraint.gridx = 0;
        constraint.gridy = 0;
        menuFrontal.add(logoApp, constraint);
        constraint.gridx = 0;
        constraint.gridy = 1;
        menuFrontal.add(inicio, constraint);
        //Colocamos los botones
        constraint.gridx = 0;
        constraint.gridy = 2;
        menuFrontal.add(groupBotones, constraint);

        return menuFrontal;
    }

    private void registerController(ActionListener listener) {
        jbconfUsuario.addActionListener(listener);
        jbconfMusic.addActionListener(listener);
        jbconfEstadisticas.addActionListener(listener);
    }
    public void changueView (int num) {
        numView = num;
        configureCardPanel();
        c.show(cardPanel, String.valueOf(numView));
    }

    private JPanel configureCardPanel () {
        cardPanel.setLayout(c);

        confMusicController = new ConfMusicController(confMusicPanelView, this);
        confMusicPanelView.registerController(confMusicController);

        cardPanel.add(confMusicPanelView, "1");
        cardPanel.add(manageAccountView, "2");
        cardPanel.add(staticsPanel, "3");
        cardPanel.add(addMusicPanel, "4");
        cardPanel.add(showMusicPanel, "5");
        cardPanel.add(deleteMusicPanel, "6");

        return cardPanel;
    }

    /*public static void main (String[] strings) {
        SideBarView sideBarView = new SideBarView();
        sideBarView.setVisible(true);
    }*/
}

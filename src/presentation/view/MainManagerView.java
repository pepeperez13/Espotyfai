package presentation.view;

import business.BuscadorManager;
import business.SongManager;
import presentation.controller.BuscadorViewController;
import presentation.controller.DetailedSongController;
import presentation.controller.MainViewController;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que contiene los CardLayout que manejan los paneles de configuración u opciones y sus respectivos menús
 */
public class MainManagerView extends JPanel {
    private int numViewCardPanel;
    private int numViewMenuCardPanel;
    private BuscadorView buscadorView;
    private BuscadorViewController buscadorViewController;
    private PlaylistView playlistView;
    private SongListlView songListlView;
    private DetailedSongView detailedSongView;
    private DetailedSongController detailedSongController;
    private MainMenu mainMenu;
    private ConfigMenuView configMenuView;
    private ManageAccountView manageAccountView;
    private ConfMusicPanelView confMusicPanelView;
    private StatisticsPanelView staticsPanel;
    private AddMusicPanelView addMusicPanel;
    private DeleteMusicPanelView deleteMusicPanel;
    private JPanel cardPanel = new JPanel();
    private JPanel menuCardPanel = new JPanel();
    private final CardLayout c = new CardLayout();
    private final CardLayout c2 = new CardLayout();
    private MainView mainView;
    private BottomBarPanel bottomBarPanel;
    private MainViewController mainViewController;

    /**
     * Constructor de la vista general del programa
     * @param initView es la Vista principal
     */
    public MainManagerView(InitView initView) {
        this.setLayout(new BorderLayout());

        mainMenu = new MainMenu(this);
        mainViewController= new MainViewController(mainMenu,this);
        configMenuView = new ConfigMenuView(this);
        detailedSongView = new DetailedSongView();
        bottomBarPanel = new BottomBarPanel(detailedSongView, this);
        manageAccountView = new ManageAccountView(initView);
        cardPanel = configureCardPanel();
        menuCardPanel = configureMenuCardPanel();

        add(menuCardPanel, BorderLayout.WEST);
        add(cardPanel,  BorderLayout.CENTER);
        add(bottomBarPanel, BorderLayout.SOUTH);

    }

    /**
     * Método que se encarga de decirle a los CardLayout la vista que tiene que mostrar
     * @param numCardPanel Indica una de las vistas de las opciones
     * @param numMenuPanel Indica uno de los dos menus
     */
    public void changeView(int numCardPanel, int numMenuPanel) {
        numViewCardPanel = numCardPanel;
        numViewMenuCardPanel = numMenuPanel;
        configureCardPanel();
        configureMenuCardPanel();
        switch(numCardPanel){
            case 3:
                this.playlistView.bringPlaylists(mainViewController.getAllPlaylists(),mainViewController.getPlaylistsOfUser());
                break;
            case 12:
                this.songListlView.loadSongs();
                break;
            default:
                break;
        }
        c.show(cardPanel, String.valueOf(numCardPanel));
        c2.show(menuCardPanel, String.valueOf(numViewMenuCardPanel));
    }

    /**
     * Método que inicializa las vistas de las distintas opciones del programa
     * @return
     */
    private JPanel configureCardPanel () {
        cardPanel.setLayout(c);

        buscadorView = new BuscadorView();
        playlistView = new PlaylistView();
        songListlView= new SongListlView();


        detailedSongController = new DetailedSongController(detailedSongView);
        buscadorViewController = new BuscadorViewController(buscadorView, new BuscadorManager(), detailedSongView, this);
        confMusicPanelView = new ConfMusicPanelView(this);
        buscadorView.registerController(buscadorViewController);
        playlistView.registerController(mainViewController);
        songListlView.registerController(mainViewController);
        deleteMusicPanel = new DeleteMusicPanelView();
        mainView= new MainView();

        staticsPanel= new StatisticsPanelView(SongManager.ListSongs());
        addMusicPanel = new AddMusicPanelView();

        cardPanel.add(mainView, "1");
        cardPanel.add(buscadorView, "2");
        cardPanel.add(playlistView, "3");
        cardPanel.add(detailedSongView, "5");
        cardPanel.add(confMusicPanelView, "6");
        cardPanel.add(manageAccountView, "7");
        cardPanel.add(staticsPanel, "8");
        cardPanel.add(addMusicPanel, "9");
        cardPanel.add(deleteMusicPanel, "11");
        cardPanel.add(songListlView,"12");

        c.first(cardPanel);

        return cardPanel;
    }

    /**
     * Método que configura el CardLayout de los menus
     * @return
     */
    private JPanel configureMenuCardPanel () {
        menuCardPanel.setLayout(c2);

        menuCardPanel.add(mainMenu, "1");
        menuCardPanel.add(configMenuView, "2");

        return menuCardPanel;
    }

}
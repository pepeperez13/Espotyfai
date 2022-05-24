package presentation.view;

import business.BuscadorManager;
import business.SongManager;
import business.entities.Song;
import presentation.controller.BuscadorViewController;
import presentation.controller.ConfMusicController;
import presentation.controller.DetailedSongController;
import presentation.controller.MainViewController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MainManagerView extends JPanel {
    public int numViewCardPanel;
    public int numViewMenuCardPanel;
    private BuscadorView buscadorView;
    private BuscadorViewController buscadorViewController;
    private PlaylistView playlistView;
    private SongListlView songListlView;
    private DetailedSongView detailedSongView;
    private DetailedSongController detailedSongController;
    private MainMenu mainMenu;
    private ConfigMenu configMenu;
    private ManageAccountView manageAccountView = new ManageAccountView();
    private ConfMusicPanelView confMusicPanelView;
    private ConfMusicController confMusicController;
    private StaticsPanelView staticsPanel;
    private AddMusicPanelView addMusicPanel;
    private ShowMusicPanelView showMusicPanel = new ShowMusicPanelView();
    private DeleteMusicPanelView deleteMusicPanel = new DeleteMusicPanelView();
    private JPanel cardPanel = new JPanel();
    private JPanel menuCardPanel = new JPanel();
    private final CardLayout c = new CardLayout();
    private final CardLayout c2 = new CardLayout();
    private MainView mainView;
    private BottomBarPanel bottomBarPanel;
    private MainViewController mainViewController;

    public MainManagerView() {
        this.setLayout(new BorderLayout());
        //this.setBackground(Color.PINK);
        //this.setOpaque(true);

        mainMenu = new MainMenu(this);
        mainViewController= new MainViewController(mainMenu,this);
        configMenu = new ConfigMenu(this);
        detailedSongView = new DetailedSongView();
        bottomBarPanel = new BottomBarPanel(detailedSongView, this);

        cardPanel = configureCardPanel();
        menuCardPanel = configureMenuCardPanel();

        add(menuCardPanel, BorderLayout.WEST);
        add(cardPanel,  BorderLayout.CENTER);
        add(bottomBarPanel, BorderLayout.SOUTH);

    }
    public void changeView(int numCardPanel, int numMenuPanel) {
        numViewCardPanel = numCardPanel;
        numViewMenuCardPanel = numMenuPanel;
        configureCardPanel();
        configureMenuCardPanel();
        switch(numCardPanel){
            case 3:
                this.playlistView.bringPlaylists();
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

    private JPanel configureCardPanel () {
        cardPanel.setLayout(c);

        buscadorView = new BuscadorView();
        playlistView = new PlaylistView();
        songListlView= new SongListlView();

        detailedSongController = new DetailedSongController(detailedSongView);
        buscadorViewController = new BuscadorViewController(buscadorView, new BuscadorManager(), detailedSongView, this);
        buscadorView.registerController(buscadorViewController);
        confMusicPanelView = new ConfMusicPanelView(this);
        playlistView.registerController(mainViewController);
        songListlView.registerController(mainViewController);
        mainView= new MainView();

        /*LinkedList<Song> l = new LinkedList<>();
        l.add(new Song("sasas", "KPOP", "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", "REGGAE", "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", "REGGAE", "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", "REGGAE", "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", "KPOP", "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", "KPOP", "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", "LATIN", "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", "LATIN", "ASA", "S", "PSA", "ASAS"));*/

        staticsPanel= new StaticsPanelView(SongManager.ListSongs());
        addMusicPanel = new AddMusicPanelView();

        cardPanel.add(mainView, "1");
        cardPanel.add(buscadorView, "2");
        cardPanel.add(playlistView, "3");
        cardPanel.add(detailedSongView, "5");
        cardPanel.add(confMusicPanelView, "6");
        cardPanel.add(manageAccountView, "7");
        cardPanel.add(staticsPanel, "8");
        cardPanel.add(addMusicPanel, "9");
        cardPanel.add(showMusicPanel, "10");
        cardPanel.add(deleteMusicPanel, "11");
        cardPanel.add(songListlView,"12");

        c.first(cardPanel);

        return cardPanel;
    }

    private JPanel configureMenuCardPanel () {
        menuCardPanel.setLayout(c2);

        menuCardPanel.add(mainMenu, "1");
        menuCardPanel.add(configMenu, "2");

        return menuCardPanel;
    }

}

import persistance.ConfigJsonDao;
import presentation.view.MainView;


public class Main {
    public static void main(String[] args) {
        ConfigJsonDao configJsonDao= new ConfigJsonDao();
        configJsonDao.readConfig();

        MainView menuPrincipal = new MainView();
        menuPrincipal.setVisible (true);
    }
}


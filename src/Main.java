import persistance.ConfigJsonDao;
import presentation.view.InitView;
import presentation.view.MainView;


public class Main {
    public static void main(String[] args) {
        ConfigJsonDao configJsonDao= new ConfigJsonDao();
        configJsonDao.readConfig();

        MainView pagPrincipal = new MainView();
        pagPrincipal.setVisible (true);
    }
}


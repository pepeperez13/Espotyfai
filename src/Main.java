import business.entities.Config;
import persistance.ConfigJsonDao;
import presentation.view.InitView;

public class Main {
    public static void main(String[] args) {
        ConfigJsonDao configJsonDao= new ConfigJsonDao();
        Config config=configJsonDao.readConfig();

        InitView initView = new InitView();
        initView.setVisible (true);
    }
}


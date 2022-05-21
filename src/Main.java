import persistance.ConfigJsonDao;
import presentation.view.InitView;


public class Main {
    public static void main(String[] args) {
        ConfigJsonDao configJsonDao= new ConfigJsonDao();
        configJsonDao.readConfig();

        InitView initView = new InitView();
        initView.setVisible (true);

    }
}


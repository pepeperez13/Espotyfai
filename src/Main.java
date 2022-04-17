import persistance.ConfigJsonDao;

public class Main {
    public static void main(String[] args) {
        ConfigJsonDao configJsonDao= new ConfigJsonDao();
        configJsonDao.readConfig();
    }



}


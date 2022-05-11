package business;

import persistance.dao.sql.SQLConnector;

public class SongManager {
    private SQLConnector sql;
    public SongManager (SQLConnector sql) {
        this.sql = sql;
    }
    public void addSong (String title, String Genre, String album, String artist, String path) {
        sql.InsertDataSong(title, Genre, album, artist, path);
    }
}

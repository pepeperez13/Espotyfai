package presentation.controller;

import business.SongManager;
import business.SongPlaylistManager;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorUser;
import persistance.dao.sql.SQLConnectorSongPlaylist;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class TestController {


    public static void main (String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SongManager songManager = new SongManager();
        SongPlaylistManager songPlaylistManager = new SongPlaylistManager();

        //SQLConnectorSong sqlConnectorSong = sql.InsertDataSong("Lambskin","ReggTech","boyd","BOYD","C:\\Users\\borja\\V3\\Espotyfai\\files\\SongFiles\\Lambskin.wav","Borja");
        //String path = "C:\\Users\\borja\\V3\\Espotyfai\\files\\SongFiles\\Lambskin.wav";
        //SongManager.PlayMusic(path,1);
        //SongManager.InsertNewSong("ME REHUSO","REGG","AGUA","DANNY MARES","C:\\Users\\borja\\V3\\Espotyfai\\files\\SongFiles\\MEREHUSO.wav","Borja");
        //SongManager.SelectSong("Lambskin");
        //SongManager.addSong("SUPERNUEVO","SOD","SA","DANNY HOLA","C:\\Users\\borja\\V3\\Espotyfai\\files\\SongFiles\\MEREHUSO.wav","Borja");

        //SongPlaylistManager.InsertNewSongPlaylist(" ire","top50");
        SongPlaylistManager.DeleteNewSongPlaylist("ultrasolo");
        //SongPlaylistManager.ListPlaylistSongs("top50");
    }
}

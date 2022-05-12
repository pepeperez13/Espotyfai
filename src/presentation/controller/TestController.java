package presentation.controller;

import business.SongManager;
import persistance.dao.sql.SQLConnectorSong;
import persistance.dao.sql.SQLConnectorUser;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class TestController {


    public static void main (String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        SongManager songManager = new SongManager();

        //SQLConnectorSong sqlConnectorSong = sql.InsertDataSong("Lambskin","ReggTech","boyd","BOYD","C:\\Users\\borja\\V3\\Espotyfai\\files\\SongFiles\\Lambskin.wav","Borja");
        //String path = "C:\\Users\\borja\\V3\\Espotyfai\\files\\SongFiles\\Lambskin.wav";
        //SongManager.PlayMusic(path);
        //SongManager.InsertNewSong("Lambskin","ReggTech","boyd","BOYD","C:\\Users\\borja\\V3\\Espotyfai\\files\\SongFiles\\Lambskin.wav","Borja");
        SongManager.SelectSong("Lambskin");


    }
}

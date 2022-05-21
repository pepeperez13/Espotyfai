package presentation.controller;

import business.SongManager;
import business.Store;
import business.entities.User;
import presentation.view.AddMusicPanelView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMusicController implements ActionListener {
    private final Store store =  new Store();
    private final AddMusicPanelView addMusicPanelView;
    public AddMusicController (AddMusicPanelView addMusicPanelView) {
        this.addMusicPanelView = addMusicPanelView;
        //Creado para probar
        //User user = new User("NAME_1", "NAME_1@gmail.com", "8f85dd834aca7263a42950664b579cc9");
        //store.setUser(user);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!checkDataCorrect()) {
            if (e.getActionCommand().equals(AddMusicPanelView.ADD_SONG)) {
                SongManager.addSong(addMusicPanelView.getTitulo(), addMusicPanelView.getGenero(), addMusicPanelView.getAlbum(), addMusicPanelView.getAutor(), addMusicPanelView.getPath(), store.getUser().getName());
            }
        } else {
            //mensaje de error;
            JOptionPane.showMessageDialog(addMusicPanelView, "COMPLETE ALL FIELDS", "Following errors were found", JOptionPane.WARNING_MESSAGE);
        }


    }

    private boolean checkDataCorrect() {
        boolean infoIncorrect = false;
        if (addMusicPanelView.getTitulo() == null || addMusicPanelView.getGenero() == null || addMusicPanelView.getAlbum() == null || addMusicPanelView.getAutor() == null || addMusicPanelView.getPath() == null) {
            infoIncorrect = true;
        }
        return infoIncorrect;
    }
}

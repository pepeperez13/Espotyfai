package presentation.controller;

import business.SongManager;
import presentation.view.AddMusicPanelView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMusicController implements ActionListener {
    private final SongManager songManager;
    private final AddMusicPanelView addMusicPanelView;
    public AddMusicController (SongManager songManager, AddMusicPanelView addMusicPanelView) {
        this.songManager = songManager;
        this.addMusicPanelView = addMusicPanelView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        /*if (e.getActionCommand().equals(AddMusicPanelView.ADD_SONG)) {
            songManager.addSong(addMusicPanelView.getTitulo(), addMusicPanelView.getGenero(), addMusicPanelView.getAlbum(), addMusicPanelView.getAutor(), addMusicPanelView.getPath());
            System.out.println(addMusicPanelView.getTitulo() + addMusicPanelView.getGenero() + addMusicPanelView.getAlbum() + addMusicPanelView.getAutor() + addMusicPanelView.getPath());
        } else if (e.getActionCommand().equals(AddMusicPanelView.ADD_PATH)) {
            addMusicPanelView.getPath();
        }*/
    }
}

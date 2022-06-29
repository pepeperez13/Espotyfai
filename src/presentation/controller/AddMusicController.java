package presentation.controller;

import business.SongManager;
import business.Owner;
import presentation.view.AddMusicPanelView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que contiene el controlador de la vista de AÑADIR CANCIONES
 */
public class AddMusicController implements ActionListener {
    private final Owner owner =  new Owner();
    private final AddMusicPanelView addMusicPanelView;

    /**
     * Constructor
     * @param addMusicPanelView Vista desde donde el usuario introduce
     *                          la información de la canción.
     */
    public AddMusicController (AddMusicPanelView addMusicPanelView) {
        this.addMusicPanelView = addMusicPanelView;
    }

    /**
     * Gestiona, mediante "if" a traves de los action command, las diferentes acciones que deben llevarse a cabo
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!checkDataCorrect()) {
            if (e.getActionCommand().equals(AddMusicPanelView.ADD_SONG)) {
                if (!SongManager.checkSongExistance(addMusicPanelView.getTitulo(), addMusicPanelView.getPath())) {
                    SongManager.addSong(addMusicPanelView.getTitulo(), addMusicPanelView.getGenero(), addMusicPanelView.getAlbum(), addMusicPanelView.getAutor(), addMusicPanelView.getPath(), owner.getUser().getName());
                }else{
                    JOptionPane.showMessageDialog(addMusicPanelView, "Song has already been added", "Following errors were found", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            //mensaje de error;
            JOptionPane.showMessageDialog(addMusicPanelView, "COMPLETE ALL FIELDS", "Following errors were found", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Método que checkea que los campos de los TextFields no esten vacios.
     * @return False si los TextFields no están vacios
     */
    private boolean checkDataCorrect() {
        boolean infoIncorrect = false;
        if (addMusicPanelView.getTitulo() == null || addMusicPanelView.getGenero() == null || addMusicPanelView.getAlbum() == null || addMusicPanelView.getAutor() == null || addMusicPanelView.getPath() == null) {
            infoIncorrect = true;
        }
        return infoIncorrect;
    }
}

package presentation.view;

import business.entities.Song;
import presentation.controller.SongPlayerController;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que disena y configura el panel que contiene toda la informacion y controles de la barra inferior de reproduccion
 */
public class BottomBarPanel extends JPanel {
    private SongPlayerController controller;
    private static Song song;
    private static JPanel song_player;
    private static GridBagConstraints c;
    private static JLabel songInfo;
    private static JSlider jSlider;
    private static JLabel currentTime;
    private static JLabel endTime;

    /**
     * Metodo constructor que crea y configura todos los componentes que tiene la barra de reporoduccion
     * @param detailedSongView vista detallada que requiere el controlador del reproductor de musica
     * @param mainManagerView vista principal que requiere el controlador del reporodcutor de music
     */
    public BottomBarPanel (DetailedSongView detailedSongView, MainManagerView mainManagerView) {
        controller = new SongPlayerController(this, detailedSongView, mainManagerView);

        song_player = new JPanel(new GridBagLayout());
        song_player.setBackground(new Color(191, 105, 240));
        c = new GridBagConstraints();

        /*
        A continuación, se crean y configuran todos los botones, label y slider que contiene la barra inferior
         */

        JButton back_song = new JButton("<<");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 1;
        song_player.add(back_song, c);
        back_song.setActionCommand("PREVIOUS_SONG");
        back_song.addActionListener(controller);

        JButton pause = new JButton("||");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        song_player.add(pause, c);
        pause.setActionCommand("PAUSE_SONG");
        pause.addActionListener(controller);

        JButton play = new JButton(">");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 1;
        song_player.add(play, c);
        play.setActionCommand("PLAY_SONG");
        play.addActionListener(controller);

        JButton next_song = new JButton(">>");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;
        c.gridy = 1;
        song_player.add(next_song, c);
        next_song.setActionCommand("NEXT_SONG");
        next_song.addActionListener(controller);

        JButton full_screen = new JButton("Details");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 3;       //aligned with button 2
        c.gridy = 2;       //third row
        song_player.add(full_screen, c);
        full_screen.setActionCommand("DETAILED_VIEW");
        full_screen.addActionListener(controller);

        JButton repeatSong = new JButton("Repeat Song");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        song_player.add(repeatSong, c);
        repeatSong.setActionCommand("REPEAT_SONG");
        repeatSong.addActionListener(controller);

        JButton repeatPlaylist = new JButton("Repeat Playlist");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        song_player.add(repeatPlaylist, c);
        repeatPlaylist.setActionCommand("REPEAT_PLAYLIST");
        repeatPlaylist.addActionListener(controller);


        songInfo = new JLabel("No song" + " - " + "No artist");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.PAGE_START; //bottom of space
        c.weightx = 0.5;
        c.gridwidth = 5;
        c.gridx = 1;
        c.gridy = 0;
        song_player.add(songInfo);


        this.add(song_player);
        this.add(configureSlider());
        this.setBackground(new Color(191, 105, 240));

    }

    /**
     * Metodo que configura el panel que contiene slider de reproduccion y los tiempos total/transcurrido de la cancion
     * @return panel configurado
     */
    private JPanel configureSlider () {
        jSlider =  new JSlider();
        currentTime =  new JLabel();
        endTime = new JLabel();
        JPanel slider = new JPanel();

        slider.setLayout(new GridLayout(1, 3));
        slider.setBackground(new Color(191, 105, 240));

        // Se configura el slider y los tiempos segun la cancion que se tenga que reproducir
        currentTime.setText(String.valueOf(SongPlayerController.getCurrentTime()));
        endTime.setText(String.valueOf(SongPlayerController.getEndTime()/1000000));
        jSlider.setMaximum((int) (SongPlayerController.getEndTime()/1000000));
        jSlider.setValue((int) SongPlayerController.getCurrentTime());
        jSlider.setMajorTickSpacing(10);
        jSlider.setMinorTickSpacing(5);
        jSlider.setBackground(new Color(191, 105, 240));

        add(currentTime);add(jSlider);add(endTime);

        return slider;
    }

    /**
     * Metodo que se va a llamar cada vez que se efectue un cambio de canción, con tal de que el panel de reproduccion
     * actualice toda la informacion que muestra
     * @param song
     */
    public static void updateSong (Song song) {
        try {
            BottomBarPanel.song = song;

            if (songInfo != null) {
                song_player.remove(songInfo);
            }

            song_player.add(songInfo);
            setSongInfo(song.getTitle(), song.getArtist());
            songInfo.repaint();
        } catch (NullPointerException e) {
            System.out.println("No hay canción para reproducir. No hace falta mostrar nada");
        }
    }

    /**
     * Actualiza el nombre y artista de la cancion
     * @param title nombre a mostrar
     * @param artist artista a mostrar
     */
    public static void setSongInfo (String title, String artist) {
        songInfo.setText(title + " - " + artist);
    }

    /**
     * Permite obtener la cancin que se esta mostrando
     * @return cancion que se esta mostrando
     */
    public static Song getSong () {
        return song;
    }

    /**
     * Metodo que llama el reproductor cada x segundos que actualiza el progresos del slider y de los minutos transucrridos
     * @param time tiempo de la cancion transcurrido
     */
    public static void setValueSlider (double time) {
        double currentTimeSeconds = time/1000000;
        double endTimeSeconds = SongPlayerController.getEndTime()/1000000;
        String currentTimeString = String.format("%d:%02d", (int) (currentTimeSeconds % 3600) / 60, (int) currentTimeSeconds%60);
        String endTimeString = String.format("%d:%02d", (int) (endTimeSeconds % 3600) / 60, (int) endTimeSeconds%60);
        currentTime.setText(currentTimeString);
        endTime.setText(endTimeString);
        jSlider.setMaximum((int) endTimeSeconds);
        jSlider.setValue((int) currentTimeSeconds);
    }
}
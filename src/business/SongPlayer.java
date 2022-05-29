package business;

import presentation.controller.MainViewController;
import presentation.controller.SongPlayerController;
import presentation.view.BottomBarPanel;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SongPlayer implements Runnable{
    private String path;
    private int index;
    private Clip clip;
    private Thread t;
    private double songDuration;
    private double currentTime;
    private double endTime;

    public void managePlayer (String path, int index, double songDuration) {
        // Comprobamos si hay algún cambio en la acción recibida
        if (!Objects.equals(this.path, path) || this.index != index) {
            // Si sólo se ha cambiado el index (diferente acción sobre la misma canción)
            if (index != this.index && Objects.equals(this.path, path)) {
                this.index = index;
            }
            if (!Objects.equals(this.path, path) /*|| MainViewController.isReproducingPlaylist()*/) {
                // Si cambia el path, significa que la cancion que se reproducia anteriormente debe pararse
                if (clip != null) {
                    clip.close();
                    System.out.println("paradoooo");
                }
                this.songDuration = songDuration;
                this.index = index;
                this.path = path;

                openFile();
                t = new Thread(this);
                t.start();

            }
        }
    }

    // Abre el fichero de audio que se está reproduciendo y gestiona las excepciones
    private void openFile () {
        String error;
        try {
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            error = "Audio file is not supported by the system";
            //JOptionPane.showMessageDialog(parentPanel, error, "Following errors were found", JOptionPane.WARNING_MESSAGE);
        } catch (IOException ioException) {
            error = "Song provided could not be found";
            //JOptionPane.showMessageDialog(parentPanel, error, "Following errors were found", JOptionPane.WARNING_MESSAGE);
        } catch (LineUnavailableException lineUnavailableException) {
            error = "Unknown error occured when trying to reproduce the song";
            //JOptionPane.showMessageDialog(parentPanel, error, "Following errors were found", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void run() {
        System.out.println(songDuration);
        // El thread sigue hasta que se acaba la canción (volverá a empezar otro cuando pasemos a la siguiente canción)
        while(clip.getMicrosecondPosition() < (songDuration*1000000 - 1000000) || index ==5) {
            this.currentTime = clip.getMicrosecondPosition();
            this.endTime = songDuration*1000000;
            switch (index) {
                case (1):
                    clip.start();
                    break;
                case (2):
                    clip.stop();
                    break;
                case (3):
                    clip.setMicrosecondPosition(0);
                    break;
                case (5):
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    break;
                case (4):
                    clip.close();
                    break;
            }
            if (currentTime%2000000 == 0) {
                BottomBarPanel.setValueSlider(this.currentTime);
            }
        }
        clip.close();
        // Si se ha acabado la canción, cambiamos el path, para que si se quiere volver a dar al play, se reproduzca
        path = "none";
        SongPlayerController.autoNextSong();

    }

    public double getCurrentTime () {
        return this.currentTime;
    }

    public double getEndTime () {
        return this.endTime;
    }

}
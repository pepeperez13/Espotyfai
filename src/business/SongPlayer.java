package business;

import presentation.controller.MainViewController;
import presentation.controller.PlayListController;
import presentation.controller.SongPlayerController;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Clase que se encarga de la gestión de los archvos de audio (reproducir, parar, etc.)
 * @author Jose Perez
 */
public class SongPlayer implements Runnable{
    private String path;
    private int index;
    private Clip clip;
    private Thread t;
    private double songDuration;
    private double currentTime;
    private double endTime;

    /**
     * Método principal y fundamental de esta clase. Es el que se encarga de realizar todas las acciones sobre una canción, iniciando
     * un thread para poder reproducir las canciones
     * @param path path de la canción sobre la que se quiere ejecutar una acción
     * @param index indica que tipo de acción se quiere realizar sobre la canción
     * @param songDuration duración de la canción, necesaria para pasar a la siguiente canción, barra de progreso...
     */
    public void managePlayer (String path, int index, double songDuration) {
        // Comprobamos si hay algún cambio en la acción recibida
        if (!Objects.equals(this.path, path) || this.index != index || PlayListController.isReproducingPlaylist()) {
            // Si sólo se ha cambiado el index (diferente acción sobre la misma canción)
            if (index != this.index && Objects.equals(this.path, path)) {
                this.index = index;
            }
            // Solo si se reproduce desde playlist, se volverá a empezar una canción, aunque sea la misma que ya se está reproduciendo
            if (!Objects.equals(this.path, path) || PlayListController.isReproducingPlaylist()) {
                // Si cambia el path, significa que la cancion que se reproducia anteriormente debe pararse
                if (clip != null) {
                    clip.close();
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

    /**
     * Abre el fichero de audio que se quiere reproducir y gestiona las excepciones
     */
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

    /**
     * Método que funciona en un thread separado, encargado de ir leyendo info de la canción mientras se ejecuta para poder mostrar
     * tiempo transcurrido, y pendiente de cambios en el "index" para realizar la acción requerida
     */
    @Override
    public void run() {

        // El thread sigue gracias al bucle hasta que se acaba la canción (volverá a empezar otro cuando pasemos a la siguiente canción)
        while(clip.getMicrosecondPosition() < (songDuration*1000000 - 1000000) || index ==3) {
            this.currentTime = clip.getMicrosecondPosition();
            this.endTime = songDuration*1000000;
            switch (index) {
                case (1) -> clip.start();
                case (2) -> clip.stop();
                case (3) -> clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            // Actualizamos el valor del slider y progreso cada dos segundos
            if (currentTime%2000000 == 0) {
                SongPlayerController.setValueSlider(this.currentTime);
            }
        }
        clip.close();
        // Si se ha acabado la canción, cambiamos el path, para que si se quiere volver a dar al play, se reproduzca
        path = "none";
        // Cuando se acabe la canción, pasamos automáticamente a la siguiente
        SongPlayerController.autoNextSong();

    }

    /**
     * Permite saber a clases externas el tiempo transcurrido de la canción
     * @return tiempo transcurrido
     */
    public double getCurrentTime () {
        return this.currentTime;
    }

    /**
     * Permite saber a clases externas la duración de la canción
     * @return duración de la canción
     */
    public double getEndTime () {
        return this.endTime;
    }

}
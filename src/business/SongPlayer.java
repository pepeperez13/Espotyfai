package business;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SongPlayer implements Runnable{
    private String path;
    private int index;
    private Clip clip;
    private Thread t;

    /*
    public SongPlayer () {
        t = new Thread(this);
        t.start();
    }
     */
    public void managePlayer (String path, int index) {
        String error = "";
        // Comprobamos si hay algún cambio en la acción recibida
        if (!Objects.equals(this.path, path) || this.index != index) {
            // Si sólo se ha cambiado el index (diferente acción sobre la misma canción)
            if (index != this.index && Objects.equals(this.path, path)) {
                this.index = index;
            }
            if (!Objects.equals(this.path, path)) {
                // Si cambia el path, significa que la cancion que se reproducia anteriormente debe pararse
                if (clip != null) {
                    clip.close();
                }
                this.index = index;
                this.path = path;
                try {
                    File file = new File(path);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    Thread t1 = new Thread(this);
                    t1.start();

                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    error = "Audio file is not supported by the system";
                    //JOptionPane.showMessageDialog(this, "Audio file is not supported by the system", "Following errors were found", JOptionPane.WARNING_MESSAGE);
                } catch (IOException ioException) {
                    error = "Song provided could not be found";
                    //JOptionPane.showMessageDialog(this, "Audio file is not supported by the system", "Following errors were found", JOptionPane.WARNING_MESSAGE);
                } catch (LineUnavailableException lineUnavailableException) {
                    error = "Unknown error occured when trying to reproduce the song";
                    //JOptionPane.showMessageDialog(this, "Audio file is not supported by the system", "Following errors were found", JOptionPane.WARNING_MESSAGE);
                }
            }



        }


    }


    @Override
    public void run() {
        //clip.start();

        while(index !=4) {
            //Sistema solo de prueba para hacer funcionar la reproduccion, en un fururo se controlara mediante la interfaz grafica del sistema.

            switch (index) {
                case (1):
                    System.out.println("Sonando cancion: " + path);
                    clip.start();
                    break;
                case (2):
                    clip.stop();
                    break;
                case (3):
                    clip.setMicrosecondPosition(0);
                    break;
                case (4):
                    clip.close();
                    break;

            }
        }
    }


}

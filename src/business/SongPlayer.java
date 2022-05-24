package business;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SongPlayer implements Runnable{
    private String path;
    private int index;
    private Clip clip;

    public void managePlayer (String path, int index) {
        this.path = path;
        this.index = index;
        String error = "";

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


    @Override
    public void run() {
        clip.start();


        while(index !=4) {
            //Sistema solo de prueba para hacer funcionar la reproduccion, en un fururo se controlara mediante la interfaz grafica del sistema.

            switch (index) {
                case (1):
                    System.out.println("sonando");
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

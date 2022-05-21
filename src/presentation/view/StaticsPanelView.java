package presentation.view;

import business.entities.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.LinkedList;

public class StaticsPanelView extends JPanel {

    public static final String KPOP = "KPOP";
    public static final String JAZZ = "JAZZ";
    public static final String ROCK = "ROCK";
    public static final String ELECTRONIC = "ELECTRONIC";
    public static final String METAL = "METAL";
    public static final String HIPHOP = "HIPHOP";
    public static final String POP = "POP";
    public static final String REGGAETON = "REGGAETON";
    public static final String REGGAE = "REGGAE";
    public static final String LATIN = "LATIN";
    private final int[] contadoresGenero = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final String[] generos;
    private LinkedList<Song> listaCanciones = new LinkedList<>();
    public StaticsPanelView (String[] generos, LinkedList<Song> listaCanciones /** De donde vamos a sacar los valores*/) {
        this.generos = generos;
        
        //Valor * numeroPixeles/MayorValor
        this.listaCanciones = listaCanciones;
        for (int i = 0; i < listaCanciones.size(); i++) {
            if (listaCanciones.get(i).getGenre().equals(KPOP)) {
                contadoresGenero[0]++;
            } else if (listaCanciones.get(i).getGenre().equals(JAZZ)) {
                contadoresGenero[1]++;
            } else if (listaCanciones.get(i).getGenre().equals(ROCK)) {
                contadoresGenero[2]++;
            } else if (listaCanciones.get(i).getGenre().equals(ELECTRONIC)) {
                contadoresGenero[3]++;
            } else if (listaCanciones.get(i).getGenre().equals(METAL)) {
                contadoresGenero[4]++;
            } else if (listaCanciones.get(i).getGenre().equals(HIPHOP)) {
                contadoresGenero[5]++;
            } else if (listaCanciones.get(i).getGenre().equals(POP)) {
                contadoresGenero[6]++;
            } else if (listaCanciones.get(i).getGenre().equals(REGGAETON)) {
                contadoresGenero[7]++;
            } else if (listaCanciones.get(i).getGenre().equals(REGGAE)) {
                contadoresGenero[8]++;
            } else if (listaCanciones.get(i).getGenre().equals(LATIN)) {
                contadoresGenero[9]++;
            }
        }


    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (listaCanciones == null) {
            return;
        } else {
            double minValue = 0;
            double maxValue = 0;
            for (int i = 0; i < 10; i++) {
                if (minValue > contadoresGenero[i]) {
                    minValue = contadoresGenero[i];
                }
                if (maxValue < contadoresGenero[i]) {
                    maxValue = contadoresGenero[i];
                }
            }
            Dimension d = getSize();
            int clientWidth = d.width;
            int clientHeight = d.height;
            //int barWidth = clientHeight / 10;
            int barWidth = 10;

            Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
            FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

            int x = labelFontMetrics.getAscent();
            int y = clientHeight / 10;
            g.setFont(labelFont);
            g.drawString("K-Pop", x, y);
            g.drawString("Jazz", x, y);
            g.drawString("Rock", x, y);
            g.drawString("Electronic Dance Music", x, y);
            g.drawString("Death Metal", x, y);
            g.drawString("Hip-Hop", x, y);
            g.drawString("Europop", x, y);
            g.drawString("Reggaeton", x, y);
            g.drawString("Reggae", x, y);
            g.drawString("Latin Music", x, y);

            int west = labelFontMetrics.getHeight();

            if (maxValue == minValue) {
                return;
            } else {
                double scale = (clientWidth - west) / (maxValue - minValue);
                y = clientHeight - labelFontMetrics.getAscent();
                g.setFont(labelFont);

                for (int i = 0; i < 10; i++) {
                    int valueX = i * barWidth + 1;
                    int valueY = 10; //Falta por ver
                    int height = (int) (contadoresGenero[i]*scale);
                    if (contadoresGenero[i] >= 0) {
                        valueX += (int) ((maxValue - contadoresGenero[i]) * scale);
                    } else {
                        valueX = (int) (maxValue * scale);
                        height = -height;
                    }
                    g.setColor(Color.GREEN);
                    g.fillRect(valueX, valueY, barWidth - 2,height);
                    g.setColor(Color.cyan);
                    g.drawRect(valueX, valueY, barWidth - 2, height);
                    int labelWidth = labelFontMetrics.stringWidth(generos[i]);
                    x  = i * barWidth + (barWidth - labelWidth) / 2;
                    g.drawString(generos[i], x, y);
                }
            }
        }
    }

    public static void main (String[] argv) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(400, 400);
    
        String[] generos = {"KPOP", "JAZZ", "ROCK", "ELECTRONIC", "METAL", "HIPHOP", "POP", "REGGAETON", "REGGAE", "LATIN"};
        Song s1 = new Song("titulo1", "KPOP", "kl", "s", "dasdsadasdasd.dsadas", "yo");
        Song s2 = new Song("titulo1", "KPOP", "kl", "s", "dasdsadasdasd.dsadas", "yo");
        Song s3 = new Song("titulo1", "KPOP", "kl", "s", "dasdsadasdasd.dsadas", "yo");
        Song s4 = new Song("titulo1", "KPOP", "kl", "s", "dasdsadasdasd.dsadas", "yo");
        Song s5 = new Song("titulo1", "ROCK", "kl", "s", "dasdsadasdasd.dsadas", "yo");
        Song s6 = new Song("titulo1", "ROCK", "kl", "s", "dasdsadasdasd.dsadas", "yo");
        Song s7 = new Song("titulo1", "LATIN", "kl", "s", "dasdsadasdasd.dsadas", "yo");
        Song s8 = new Song("titulo1", "LATIN", "kl", "s", "dasdsadasdasd.dsadas", "yo");

        LinkedList<Song> songs = new LinkedList<>();
        songs.add(s1);
        songs.add(s2);
        songs.add(s3);
        songs.add(s4);
        songs.add(s5);
        songs.add(s6);
        songs.add(s7);
        songs.add(s8);

        jFrame.getContentPane().add(new StaticsPanelView(generos, songs));

        WindowListener wndCloser = new WindowAdapter() {
            public void  windowClosing (WindowEvent e) {
                System.exit(0);
            }
        };

        jFrame.addWindowListener(wndCloser);
        jFrame.setVisible(true);
    }
}

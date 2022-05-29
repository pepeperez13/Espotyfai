package presentation.view;

import business.entities.Song;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 *Clase que se encarga de dibujar el gráfico con las estadísticas de los géneros musicales que tiene el usuario
 */
public class StatisticsPanelView extends JPanel {
    public static final String KPOP = "K pop";
    public static final String JAZZ = "Jazz";
    public static final String ROCK = "Rock";
    public static final String ELECTRONIC = "Electronic";
    public static final String METAL = "Metal";
    public static final String HIPHOP = "Hip Hop";
    public static final String POP = "Pop";
    public static final String REGGAETON = "Reggaeton";
    public static final String REGGAE = "Reggae";
    public static final String LATIN = "Latin";
    private final int ESPACIO_VERTTICAL = 60;
    private final int ESPACIO_HORIZONTAL = 320;
    private final int[] contadoresGenero = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final LinkedList<Song> listaCanciones;

    /**
     * Constructor.
     * Calcula el número de canciones de cada género musical que tiene el usuario actual.
     * @param listaCanciones    LinkedList con todas las canciones del usuario actual.
     */
    public StatisticsPanelView(LinkedList<Song> listaCanciones) {
        this.listaCanciones = listaCanciones;
        //Valor * numeroPixeles/MayorValor
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

    /**
     * Dibuja las barras y sus respectivas etiquetas.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        if (listaCanciones == null) {
            JOptionPane.showMessageDialog(this, "NO SONGS", "Following errors were found", JOptionPane.WARNING_MESSAGE);
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
            int clientWidth = d.width - 400;
            int clientHeight = d.height;
            int barWidth = 12;

            Font labelFont = new Font("Arial", Font.BOLD, 25);
            FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

            int x = labelFontMetrics.getAscent();
            int y = clientHeight / 10;

            g.setFont(labelFont);
            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(76, 131, 187));
            g.drawString("K-Pop", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[0]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[0]*clientWidth/maxValue), barWidth );
            g.drawString(String.valueOf(contadoresGenero[0]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[0]*clientWidth/maxValue) + 10, y);

            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(194, 85, 76));
            g.drawString("Jazz", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[1]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[1]*clientWidth/maxValue), barWidth);
            g.drawString(String.valueOf(contadoresGenero[1]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[1]*clientWidth/maxValue) + 10, y);

            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(156, 187, 92));
            g.drawString("Rock", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[2]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[2]*clientWidth/maxValue), barWidth);
            g.drawString(String.valueOf(contadoresGenero[2]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[2]*clientWidth/maxValue) + 10, y);

            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(76, 172, 196));
            g.drawString("Electronic Dance Music", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[3]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[3]*clientWidth/maxValue), barWidth);
            g.drawString(String.valueOf(contadoresGenero[3]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[3]*clientWidth/maxValue) + 10, y);

            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(242, 151, 75));
            g.drawString("Death Metal", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[4]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[4]*clientWidth/maxValue), barWidth);
            g.drawString(String.valueOf(contadoresGenero[4]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[4]*clientWidth/maxValue) + 10, y);

            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(129, 100, 157));
            g.drawString("Hip-Hop", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[5]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[5]*clientWidth/maxValue), barWidth);
            g.drawString(String.valueOf(contadoresGenero[5]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[5]*clientWidth/maxValue) + 10, y);

            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(148, 177, 211));
            g.drawString("Europop", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[6]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[6]*clientWidth/maxValue), barWidth);
            g.drawString(String.valueOf(contadoresGenero[6]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[6]*clientWidth/maxValue) + 10, y);

            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(148, 255, 211));
            g.drawString("Reggaeton", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[7]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[7]*clientWidth/maxValue), barWidth);
            g.drawString(String.valueOf(contadoresGenero[7]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[7]*clientWidth/maxValue) + 10, y);

            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(148, 177, 255));
            g.drawString("Reggae", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[8]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[8]*clientWidth/maxValue), barWidth);
            g.drawString(String.valueOf(contadoresGenero[8]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[8]*clientWidth/maxValue) + 10, y);

            y += ESPACIO_VERTTICAL;
            g.setColor(new Color(255, 255, 100));
            g.drawString("Latin Music", x, y);
            g.fillRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[9]*clientWidth/maxValue), barWidth);
            g.drawRect(ESPACIO_HORIZONTAL, y - 13, (int) (contadoresGenero[9]*clientWidth/maxValue), barWidth);
            g.drawString(String.valueOf(contadoresGenero[9]*100/listaCanciones.size()) + '%', ESPACIO_HORIZONTAL + (int) (contadoresGenero[9]*clientWidth/maxValue) + 10, y);
        }
    }
}

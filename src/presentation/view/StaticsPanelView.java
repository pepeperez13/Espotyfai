package presentation.view;

import business.entities.Song;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class StaticsPanelView extends JPanel {

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
    private final int ESPACIO = 60;
    private final int[] contadoresGenero = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //private static final int Regg = 1;
    //private String[] generos;
    private LinkedList<Song> listaCanciones = new LinkedList<>();
    public StaticsPanelView (LinkedList<Song> listaCanciones /** De donde vamos a sacar los valores*/) {
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

            //System.out.println(minValue+"minValue"+"\n"+maxValue+"maxValue");
            //int west = labelFontMetrics.getHeight();
            Dimension d = getSize();
            int clientWidth = d.width;
            int clientHeight = d.height;
            int barWidth = 12;

            Font labelFont = new Font("SansSerif", Font.PLAIN, 10);
            FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

            int x = labelFontMetrics.getAscent();
            int y = clientHeight / 100;
            g.setFont(labelFont);
            y += ESPACIO;
            x += ESPACIO;
            g.drawString("K-Pop", x, y);
            g.setColor(new Color(76, 131, 187));
            //System.out.println(((contadoresGenero[0]*clientWidth)/maxValue));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[0]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[0]*clientWidth)/maxValue)-100), barWidth - 2);
            y += ESPACIO;
            g.drawString("Jazz", x, y);
            g.setColor(new Color(194, 85, 76));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[1]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[1]*clientWidth)/maxValue)-100), barWidth - 2);
            y += ESPACIO;
            g.drawString("Rock", x, y);
            g.setColor(new Color(156, 187, 92));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[2]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[2]*clientWidth)/maxValue)-100), barWidth - 2);
            y += ESPACIO;
            g.drawString("Electronic Dance Music", x, y);
            g.setColor(new Color(76, 172, 196));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[3]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[3]*clientWidth)/maxValue)-100), barWidth - 2);
            y += ESPACIO;
            g.drawString("Death Metal", x, y);
            g.setColor(new Color(242, 151, 75));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[4]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[4]*clientWidth)/maxValue)-100), barWidth - 2);
            y += ESPACIO;
            g.drawString("Hip-Hop", x, y);
            g.setColor(new Color(129, 100, 157));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[5]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[5]*clientWidth)/maxValue)-100), barWidth - 2);
            y += ESPACIO;
            g.drawString("Europop", x, y);
            g.setColor(new Color(148, 177, 211));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[6]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[6]*clientWidth)/maxValue)-100), barWidth - 2);
            y += ESPACIO;
            g.drawString("Reggaeton", x, y);
            g.setColor(new Color(148, 255, 211));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[7]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[7]*clientWidth)/maxValue)-100), barWidth - 2);
            y += ESPACIO;
            g.drawString("Reggae", x, y);
            g.setColor(new Color(148, 177, 255));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[8]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[8]*clientWidth)/maxValue)-100), barWidth - 2);
            y += ESPACIO;
            g.drawString("Latin Music", x, y);
            g.setColor(new Color(255, 255, 100));
            g.fillRect(x+ESPACIO, y, ((int) ((contadoresGenero[9]*clientWidth)/maxValue)-100), barWidth - 2);
            g.drawRect(x+ESPACIO, y, ((int) ((contadoresGenero[9]*clientWidth)/maxValue)-100), barWidth - 2);
                /*double scale = (clientWidth - west) / (maxValue - minValue);
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
                    //Sacar el tamañobien
                    //g.setColor(Color.GREEN);
                    //g.fillRect(valueX, valueY, height, barWidth - 2);
                    //g.drawRect(valueX, valueY, height, barWidth - 2);
                    //int labelWidth = labelFontMetrics.stringWidth(generos[i]);
                    //x  = i * barWidth + (barWidth - labelWidth) / 2;
                    //g.drawString(generos[i], x, y);
                }*/

        }
    }

    /*public static void main(String[] args) {
        LinkedList<Song> l = new LinkedList<>();
        l.add(new Song("sasas", KPOP, "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", REGGAE, "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", REGGAE, "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", REGGAE, "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", KPOP, "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", KPOP, "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", LATIN, "ASA", "S", "PSA", "ASAS"));
        l.add(new Song("sasas", LATIN, "ASA", "S", "PSA", "ASAS"));

        StaticsPanelView s = new StaticsPanelView(l);

        s.setVisible(true);
    }*/
}

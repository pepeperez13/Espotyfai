package presentation.view;

import presentation.controller.ConfMusicController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase que contiene la vista de la CONFIGURACIÓN DE LA MUSICA que cotniene las opciones de:
 * -AÑADIR MÚSICA
 * -ELIMINAR MÚSICA
 */
public class ConfMusicPanelView extends JPanel {
    public static final String GO_ADD_MUSIC = "GO_ADD_MUSIC";
    public static final String GO_DELETE_MUSIC = "GO_DELETE_MUSIC";
    private JButton jbAdd;
    private JButton jbDel;
    private ConfMusicController confMusicController;
    private final GridBagConstraints gc = new GridBagConstraints();

    /**
     *Constructor en el que se describe la estructura de la vista
     * Se inicializa el controller de la vista para la interacción con los botones
     * @param mainManagerView
     */
    public ConfMusicPanelView(MainManagerView mainManagerView) {
        confMusicController = new ConfMusicController(mainManagerView);

        setBackground(new Color(255, 255, 255));
        setLayout(new GridBagLayout());

        gc.fill = GridBagConstraints.NONE;

        JSeparator s1 = new JSeparator();
        s1.setOrientation(SwingConstants.HORIZONTAL);

        jbAdd = new JButton();
        jbAdd.setText("Añadir canción");
        jbAdd.setFont(new Font("Arial", Font.BOLD, 18));
        jbAdd.setBackground(new Color(152, 245, 214));
        jbAdd.setBorderPainted(false);
        jbAdd.setActionCommand(GO_ADD_MUSIC);

        jbDel = new JButton();
        jbDel.setText("Eliminar canción");
        jbDel.setFont(new Font("Arial", Font.BOLD, 18));
        jbDel.setBackground(new Color(230,101, 101));
        jbDel.setBorderPainted(false);
        jbDel.setActionCommand(GO_DELETE_MUSIC);

        registerController(confMusicController);

        gc.gridx = 0;
        gc.gridy = 0;
        add(jbAdd, gc);
        add(s1);
        gc.gridx = 0;
        gc.gridy = 3;
        add(jbDel, gc);
    }

    /**
     * Método que conecta la vista con el controlador [View->Controller]
     */
    public void registerController (ActionListener listener) {
        jbAdd.addActionListener(listener);
        jbDel.addActionListener(listener);
    }


}

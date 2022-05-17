package presentation.controller;

import presentation.view.ConfigAccountView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideBarController implements ActionListener {
    private final ConfigAccountView menuFrontal;
    public SideBarController (ConfigAccountView menuFrontal) {
        this.menuFrontal = menuFrontal;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(ConfigAccountView.GO_CONFIG_MUSIC)) {
            menuFrontal.changueView(1);
        } else if (e.getActionCommand().equals(ConfigAccountView.GO_CONFIG_USER)) {
            menuFrontal.changueView(2);
        } else if (e.getActionCommand().equals(ConfigAccountView.GO_STATICS)) {
            menuFrontal.changueView(3);
        }
    }
}

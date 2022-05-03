package presentation.controller;

import presentation.view.SideBarView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideBarController implements ActionListener {
    private final SideBarView menuFrontal;
    public SideBarController (SideBarView menuFrontal) {
        this.menuFrontal = menuFrontal;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SideBarView.GO_CONFIG_MUSIC)) {
            menuFrontal.changueView(1);
        } else if (e.getActionCommand().equals(SideBarView.GO_CONFIG_USER)) {
            menuFrontal.changueView(2);
        } else if (e.getActionCommand().equals(SideBarView.GO_STATICS)) {
            menuFrontal.changueView(3);
        }
    }
}

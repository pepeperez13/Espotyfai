package presentation.controller;

import presentation.view.InicioView;
import presentation.view.InitView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioController implements ActionListener {
    private InicioView inicioView;
    private InitController initController;
    public InicioController(InicioView inicioView, InitView initView) {
        this.inicioView = inicioView;
        initController = new InitController(initView);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(InicioView.GO_CONFIG)) {
            initController.refreshView(4);
        }
    }
}

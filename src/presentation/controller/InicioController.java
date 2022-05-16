package presentation.controller;

import presentation.view.InicioView;
import presentation.view.InitView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioController implements ActionListener {
    private InicioView inicioView;
    private InitView initView;
    public InicioController(InicioView inicioView, InitView initView) {
        this.inicioView = inicioView;
        this.initView = initView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

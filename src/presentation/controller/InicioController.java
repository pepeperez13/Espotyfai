package presentation.controller;

import business.Store;
import presentation.view.InicioView;
import presentation.view.InitView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioController implements ActionListener {
    private InicioView inicioView;
    public InicioController(InicioView inicioView) {
        this.inicioView = inicioView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(InicioView.GO_CONFIG)) {
            inicioView.changueView(4);
        } else if (e.getActionCommand().equals(InicioView.GO_LIST)) {
            inicioView.changueView(3);
        }else if(e.getActionCommand().equals(InicioView.GO_INICIO)){
            inicioView.changueView(1);
        }else if(e.getActionCommand().equals(InicioView.GO_SEARCH)){
            //inicioView.changueView(2);
        }
    }
}

package presentation.controller;

import presentation.view.InitView;

public class InitController {
    private int numView;
    private InitView initView;

    /**
     * Constructor
     * @param initView Vista principal del programa (JFrame)
     */
    public InitController(InitView initView) {
        this.initView = initView;
    }

    /**
     * Método que le indica a la vista principal, que panel tiene que mostrar
     * @param a Entero que indica el panel
     */
    public void refreshView (int a) {
        this.numView = a;
        initView.changeView(a);
    }
}

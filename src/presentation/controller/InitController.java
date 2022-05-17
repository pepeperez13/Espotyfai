package presentation.controller;

import presentation.view.InitView;

public class InitController {
    private int numView;
    private InitView initView;

    public InitController(InitView initView) {
        this.initView = initView;
    }

    public void refreshView (int a) {
        this.numView = a;
        initView.changeView(a);
    }
}
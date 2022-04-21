package presentation.controller;

import presentation.view.SignUpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpViewController implements ActionListener {
    private SignUpView view;

    public static void main (String[] args) {
        SignUpView signUpView = new SignUpView();
        signUpView.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(SignUpView.MALE_COMMAND)) {
            // Select male
        } else if (e.getActionCommand().equals(SignUpView.FEMALE_COMMAND)) {
            // Select female
        } else {
            // Select other
        }
    }

}

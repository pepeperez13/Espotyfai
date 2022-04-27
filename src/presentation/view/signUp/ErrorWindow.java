package presentation.view.signUp;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {

    public ErrorWindow (boolean userExists, boolean emailExists, boolean emailFormatError, boolean passwordFormatError, boolean confirmationError) {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel errorMessage = new JLabel("Following errors were found:");
        errorMessage.setFont(new Font("Tahoma", Font.BOLD, 11));
        if (userExists) {
            add(new JLabel("User already exists"));
        }
        if (emailExists) {
            add(new JLabel("Email is already used"));
        }
        if (emailFormatError) {
            add(new JLabel("Email format is incorrect"));
        }
        if (passwordFormatError) {
            add(new JLabel("Password format is incorrect"));
        }
        if (confirmationError) {
            add(new JLabel("Passwords don't match"));
        }

        setVisible(true);


    }
}

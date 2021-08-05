import MainManu.MainMenuWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(100/10);
        SwingUtilities.invokeLater(
                () ->    new MainMenuWindow()
        );
    }
}

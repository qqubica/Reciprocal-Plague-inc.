package MainManu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButton extends JButton{
    public ExitButton(){
        setText("EXIT");
        setAlignmentX(Component.CENTER_ALIGNMENT);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exiting from main manu close button");
                System.exit(0);
            }
        });
    }
}

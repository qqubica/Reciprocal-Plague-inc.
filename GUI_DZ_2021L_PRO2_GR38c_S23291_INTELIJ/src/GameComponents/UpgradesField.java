package GameComponents;

import javax.swing.*;
import java.awt.*;

public class UpgradesField extends JTextField{

    public UpgradesField(){
        setText("Wybiewrz ulepszenia");
        setEnabled(false);
        setBorder(null);
        setDisabledTextColor(Color.BLUE);
        setHorizontalAlignment(JTextField.CENTER);
        setToolTipText("Jak będzie cię stać na ulepszenia to podświetlą się na zielono");
    }
}

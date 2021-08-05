package Game;

import javax.swing.*;
import java.awt.*;

public class UpgradesFrame extends JFrame {
    public UpgradesFrame() {

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JButton upgrade1 = new JButton("lekaż");
        JButton upgrade2 = new JButton("maseczki");
        JButton upgrade3 = new JButton("strzykawki");
        JButton upgrade4 = new JButton("leki");
        JButton upgrade5 = new JButton("nowy szpital");
        JButton upgrade6 = new JButton("wiecej łóżek szpitalnych");
        JButton upgrade7 = new JButton("kwarantanna");
        JButton upgrade8 = new JButton("tmp");
        JButton upgrade9 = new JButton("tmp1");

        JButton[] upgrades = {upgrade1, upgrade2, upgrade3, upgrade4, upgrade5, upgrade6, upgrade7, upgrade8, upgrade9};

        for (int i = 0; i < upgrades.length; i++) {

            upgrades[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            getContentPane().add(upgrades[i]);

        }
    }
}

package GameComponents;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentListener;

import static com.sun.java.accessibility.util.SwingEventMonitor.addListSelectionListener;

public class UpgradeList extends JList{

    public UpgradeList() {
        String[] upgrades = {"lekaż", "maseczki", "strzykawki", "leki",
                "nowy szpital", "wiecej łóżek szpitalnych",
                "szkolenie lekarzy", "cud", "MORE PEOPLE"};

        setListData(upgrades);
        setToolTipText("Jak będzie cię stać na ulepszenia to podświetlą się na zielono");
        setLayoutOrientation(JList.VERTICAL);

    }

    public int upgradesPrices(int index){
        return switch (index) {
            case 0 -> 15_000;
            case 1 -> 1000;
            case 2 -> 2000;
            case 3 -> 3000;
            case 4 -> 50_000;
            case 5 -> 10_000;
            case 6 -> 12_000;
            case 7, 8-> 1_000_000;
            default -> 0;
        };
    }

    public boolean canAford(int index, int money){
        return money >= upgradesPrices(index);
    }
}

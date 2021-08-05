package GameComponents;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class UpgradePanel extends JPanel {
    public UpgradeList upgradesList;
    JTextField upgradesField;

    public UpgradePanel() {
        BoxLayout bL= new BoxLayout(this,BoxLayout.Y_AXIS);

        setLayout(bL);

        upgradesField = new UpgradesField();
        upgradesList = new UpgradeList();


        upgradesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting()){
                    System.out.println(upgradesList.getSelectedIndex());
                }

            }
        });


        add(upgradesField);
        add(upgradesList);

        Dimension upgradeListSize = new Dimension(upgradesList.getPreferredSize().width, preferredSize().height-upgradesField.getHeight());

        upgradesList.setPreferredSize(upgradeListSize);
    }
}

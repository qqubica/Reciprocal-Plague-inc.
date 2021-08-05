package GameComponents;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class InfoPanel extends JPanel {
    public JTextField clook;
    public JTextField nrOfInfected;
    public MyJpb jpb;

    public InfoPanel() {

        setLayout(new FlowLayout());

        clook = new JTextField();
        clook.setEnabled(false);
        clook.setDisabledTextColor(Color.black);
        clook.setBackground(getBackground());
        clook.setBorder(null);
        add(clook);

        jpb = new MyJpb();
        add(jpb);
        nrOfInfected = new JTextField();
        nrOfInfected.setText("a " + Long.MAX_VALUE);
        nrOfInfected.setEnabled(false);
        nrOfInfected.setDisabledTextColor(Color.black);
        nrOfInfected.setBackground(getBackground());
        nrOfInfected.setBorder(null);
        add(nrOfInfected);
    }

    public void setClook(LocalDate date) {
        clook.setText(date.toString());
    }

    public void setNrOfInfected(long infected, long helthy, int money) {
        if (infected != Long.parseLong(nrOfInfected.getText().split("[ /]")[1])) {
            String toSet = "Zarażoncych " + infected + "/" + helthy + "\tPieniądze:" + money;
            nrOfInfected.setText(toSet);
        }
    }
}

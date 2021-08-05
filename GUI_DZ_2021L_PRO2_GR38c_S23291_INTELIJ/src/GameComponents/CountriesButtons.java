package GameComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountriesButtons extends JButton {
    public int population;
    public int sickPopulation;

    public CountriesButtons(String country) {

        population = (int) (Math.random() * 80_000_000 + 20_000_000);
        sickPopulation=0;

        setText(country);
        setAlignmentX(CENTER_ALIGNMENT);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("W "+getText()+" jest "+sickPopulation+" zarazonych");
            }
        });


    }
}

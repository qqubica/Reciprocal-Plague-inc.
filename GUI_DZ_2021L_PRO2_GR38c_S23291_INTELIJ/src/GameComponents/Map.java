package GameComponents;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel {
    GridBagLayout layout;
    GridBagConstraints constraints;
    public CountriesButtons[] countriesButton;

    public Map() {

        layout = new GridBagLayout();

        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.insets = new Insets(0, 0, 0, 0);

        setBackground(Color.blue);
        setLayout(layout);
        setVisible(true);

        createMap();

    }

    void createMap(){

        String[] countries = {"Polska", "Niemcy", "Francja", "USA", "Australia", "Chiny", "Egipt", "Argentyna", "Japonia", "Laponia"};

        addCountrys(countries);

        addButton(countriesButton[0], 3, 2, 2, 1);
        addButton(countriesButton[1], 1, 1, 2, 2);
        addButton(countriesButton[2], 1, 3, 1, 2);
        addButton(countriesButton[3], 4, 8, 1, 3);
        addButton(countriesButton[4], 5, 1, 1, 2);
        addButton(countriesButton[5], 1, 7, 1, 2);
        addButton(countriesButton[6], 5, 3, 1, 1);
        addButton(countriesButton[7], 6, 6, 2, 1);
        addButton(countriesButton[8], 4, 5, 1, 1);
        addButton(countriesButton[9], 2, 4, 1, 1);

        addBoxes();

    }

    public int getNrOfCountries() {
        return countriesButton.length;
    }

    void addBoxes() {
        addButton(Box.createRigidArea(new Dimension(1, 1)), 0, 0, 1, 1);
        addButton(Box.createRigidArea(new Dimension(1, 1)), 0, 9, 1, 1);
        addButton(Box.createRigidArea(new Dimension(1, 1)), 7, 7, 1, 1);
        addButton(Box.createRigidArea(new Dimension(1, 1)), 2, 9, 1, 1);
        addButton(Box.createRigidArea(new Dimension(1, 1)), 5, 2, 1, 1);
        addButton(Box.createRigidArea(new Dimension(1, 1)), 5, 2, 1, 1);
        addButton(Box.createRigidArea(new Dimension(1, 1)), 1, 4, 1, 1);
    }

    void addCountrys(String[] countrysNames) {
        countriesButton = new CountriesButtons[countrysNames.length];
        for (int i = 0; i < countrysNames.length; i++) {
            countriesButton[i] = new CountriesButtons(countrysNames[i]);
        }
    }

    void addButton(Component b, int gridx, int gridy, int gridheight, int gridwidth) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;

        constraints.gridheight = gridheight;
        constraints.gridwidth = gridwidth;

        constraints.weightx = 0.5;
        constraints.weighty = 0.5;

        constraints.fill = GridBagConstraints.BOTH;

        layout.setConstraints(b, constraints);

        add(b, constraints);
    }
}

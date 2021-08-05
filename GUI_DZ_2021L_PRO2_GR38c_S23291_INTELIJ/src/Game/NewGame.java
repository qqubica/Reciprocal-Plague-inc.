package Game;

import GameComponents.*;
import Threads.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class NewGame extends JFrame {
    public int startingCountry;
    public int gameDifficulty;

    public int money = 1000;
    public long score;

    public double infectionRate;
    public int infectionTime;
    public double deathProbability;

    public long worldPopulation;
    public long infectedPopulation;
    public long deadPopulation;

    public Map map;
    public UpgradePanel upgradesPanel;
    public InfoPanel infoPanel;

    public Clook clookThread;
    public Virus virusProgress;

    public LocalDate gameDate = LocalDate.now();
    public LocalDate gameStartDate = gameDate;

    public NewGame() {

        selectDiff();

        setTitle("AntiPlague");
        addListners();
        getContentPane().setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1280,720));
        setSize(1280,720);
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);

//        setLogo(sciezka do logo);

        upgradesPanel = new UpgradePanel();

        addUpgradeListListner();

        getContentPane().add(upgradesPanel, BorderLayout.LINE_START);

        upgradesPanel.getComponent(1).setPreferredSize(new Dimension(upgradeDim(upgradesPanel)));

        infoPanel = new InfoPanel();
        infoPanel.setClook(gameDate);
        infoPanel.setNrOfInfected(infectedPopulation,
                worldPopulation - infectedPopulation <= 0 ? 0 : worldPopulation - infectedPopulation
                , money);
        add(infoPanel, BorderLayout.PAGE_START);

        map = new Map();
        getContentPane().add(map, BorderLayout.CENTER);

        startGame();

    }

    void addListners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(new JFrame(), "Gra zakończona");
                gameEnd();
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isAltDown() && e.isShiftDown() && e.getKeyChar() == 81) {
                    gameEnd();
                    System.out.println("Skrót klawiszowy");
                }
            }
        });
    }

    void finalScore() {
        score = worldPopulation * gameDifficulty + money * 10 + 10_000_000 / (gameStartDate.until(gameDate, ChronoUnit.DAYS));
    }

    public void gameEnd() {
        System.out.println("GAME END");
        finalScore();
        setVisible(false);
    }

    void startGame() {

        getWorldPopulation();
        startDisise();

        clookThread = new Clook();
        clookThread.giveGame(this);
        clookThread.start();

        virusProgress = new Virus();
        virusProgress.giveGame(this);
        virusProgress.start();
        virusProgress.checkProgress();

//        pack();
    }

    public void startJbp() {
        ((MyJpb) (infoPanel.getComponent(1))).setHowFast((int) (clookThread.getJpbSpeed() * 0.88));
    }

    void getWorldPopulation() {
        for (int i = 0; i < map.countriesButton.length; i++) {
            worldPopulation += (int) (Math.random() * 80_000_000 + 20_000_000);
        }
        System.out.println("World population is " + worldPopulation);
    }

    void addUpgradeListListner() {
        upgradesPanel.upgradesList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting()) {
                    if (upgradesPanel.upgradesList.canAford(upgradesPanel.upgradesList.getSelectedIndex(), money)) {
                        money -= upgradesPanel.upgradesList.upgradesPrices(upgradesPanel.upgradesList.getSelectedIndex());

                        JOptionPane.showMessageDialog(new JFrame(), "zakupiłeś " + upgradesPanel.upgradesList.getSelectedValue());
                        System.out.println("zostalo ci " + money + " pieniedzy");

                        switch (upgradesPanel.upgradesList.getSelectedIndex()) {
                            case 0 -> infectionTime -= 1;
                            case 1 -> infectionRate -= 0.1;
                            case 2, 6 -> infectionTime -= 1;
                            case 3 -> infectionRate -= 0.15;
                            case 4 -> deathProbability -= 0.8;
                            case 5 -> deathProbability -= 0.1;
                            case 7 -> infectedPopulation = 1;
                            case 8 -> worldPopulation += 1_000_000;
                        }
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "nie stać cię na zakup " + upgradesPanel.upgradesList.getSelectedValue());
                    }
                }
            }
        });
    }

    public void selectDiff() {
        System.out.println("will start after you will select difficulty lvl");

        gameDifficulty = new DifficultySelector().selectDifficulty();

        System.out.println(
                gameDifficulty == 1 ?
                        "Wybrales nomalny poziom gry" : gameDifficulty == 2 ?
                        "Wybralesc trudny poziom gry" : "Wybrales latwy poziom gry"
        );

        switch (gameDifficulty) {
            case 0 -> {
                infectionRate = 1.5;
                infectionTime = 7;
                deathProbability = 0.22;
            }
            case 1 -> {
                infectionRate = 1.9;
                infectionTime = 10;
                deathProbability = 0.35;
            }
            case 2 -> {
                infectionRate = 2.4;
                infectionTime = 15;
                deathProbability = 0.55;
            }
        }
        System.out.println("infectionRate = " + infectionRate + "\ninfectionTime = " + infectionTime + "\ndeathProbability = " + deathProbability);
    }

    public void setLogo(String path) {
        ImageIcon logo = new ImageIcon(path);
        setIconImage(logo.getImage());
        //tu miało być logo ale nie zdążyłem
    }

    Dimension upgradeDim(JPanel upgradesPanel) {
        return new Dimension(upgradesPanel.getComponent(0).getPreferredSize().width > upgradesPanel.getComponent(1).getPreferredSize().width ?
                upgradesPanel.getComponent(0).getPreferredSize().width : upgradesPanel.getComponent(1).getPreferredSize().width, getHeight() - upgradesPanel.getComponent(0).getPreferredSize().height);
    }

    public void startDisise() {
        int[] startingCountriesId = new int[gameDifficulty + 1];
        String startingCountriesMessage = "";
        for (int i = 0; i < gameDifficulty + 1; i++) {
            startingCountry = (int) (Math.random() * map.getNrOfCountries());
            infectedPopulation++;

            startingCountriesId[i] = startingCountry;

            System.out.println("Zaraza zaczela sie w " + map.countriesButton[startingCountry].getText());
        }
        for (int i = 0; i < startingCountriesId.length; i++) {
            if (i == 0) {
                startingCountriesMessage += map.countriesButton[startingCountriesId[i]].getText();
            } else {
                if (i + 1 < startingCountriesId.length) {
                    startingCountriesMessage += ", " + map.countriesButton[startingCountriesId[i]].getText();
                } else {
                    startingCountriesMessage += " i " + map.countriesButton[startingCountriesId[i]].getText();
                }
            }
        }
        JOptionPane.showMessageDialog(new Frame(), "Jako że " + (gameDifficulty == 1 ?
                "wybrales nomalny poziom gry" : gameDifficulty == 2 ?
                "wybralesc trudny poziom gry" : "wybrales latwy poziom gry") + " pierwsi zarazeni pojawią się w " + startingCountriesMessage);
        for (CountriesButtons b : map.countriesButton) {
            infectedPopulation += b.sickPopulation;
        }
        System.out.println("aktualnie jest " + infectedPopulation + " zarazonych");
    }

}

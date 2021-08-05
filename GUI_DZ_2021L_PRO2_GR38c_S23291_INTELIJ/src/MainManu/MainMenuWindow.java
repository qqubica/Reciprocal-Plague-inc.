package MainManu;

import Game.NewGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuWindow extends JFrame {
    NewGame game;
    HiScoreButton hiScoreButton = new HiScoreButton();

    public MainMenuWindow() {

        GridLayout layout = new GridLayout(3, 3, 0, 30);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setName("GUI projekt 3");
        setVisible(true);
        setLayout(layout);
        setLocationRelativeTo(null);

        add(new InvisibleButton());

        JButton newGameButton = new JButton("Nowa gra");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.out.println("Starting new game");
                game = new NewGame();
                game.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentHidden(ComponentEvent e) {
                        hiScoreButton.addNewScore(game.score);
                        setVisible(true);
                    }
                });

            }
        });
        getContentPane().add(newGameButton, BorderLayout.PAGE_START);

        add(new InvisibleButton());
        add(new InvisibleButton());

        hiScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!hiScoreButton.hiScores.isEmpty())
                    if (e.getModifiers() == 16)
                        setVisible(false);

            }
        });

        add(hiScoreButton, BorderLayout.CENTER);

        add(new InvisibleButton());
        add(new InvisibleButton());

        getContentPane().add(new ExitButton(), BorderLayout.PAGE_END);

        pack();

    }
}

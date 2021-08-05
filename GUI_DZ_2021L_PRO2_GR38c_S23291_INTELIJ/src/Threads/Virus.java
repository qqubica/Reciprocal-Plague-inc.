package Threads;

import Game.NewGame;

import javax.swing.*;

public class Virus extends BasicThread {

    @Override
    public void run() {

    }

    public boolean checkProgress() {
        synchronized (monitor) {
            double infectionTime = thisGame.infectionTime;
            if (thisGame.infectedPopulation < 100) {

                if (Math.random() <= thisGame.infectionRate / infectionTime)
                    thisGame.infectedPopulation++;

            } else {

                if (thisGame.infectedPopulation < thisGame.worldPopulation && Math.random() <= thisGame.infectedPopulation * thisGame.infectionRate / infectionTime)
                    thisGame.infectedPopulation += (int) (thisGame.infectedPopulation * thisGame.infectionRate / infectionTime);

                if (Math.random() <= 1 / infectionTime) {
                    thisGame.money+=(int) (thisGame.infectedPopulation * (1 / infectionTime));
                    thisGame.infectedPopulation -= (int) (thisGame.infectedPopulation * (1 / infectionTime));
                }

            }
            if (Math.random() <= thisGame.deathProbability / infectionTime) {
                if (thisGame.worldPopulation < thisGame.deadPopulation) {

                    thisGame.worldPopulation -= (int) (thisGame.gameDifficulty * 5 * thisGame.infectedPopulation * thisGame.deathProbability / infectionTime);
                    thisGame.infectedPopulation -= (int) (thisGame.gameDifficulty * 5 * thisGame.infectedPopulation * thisGame.deathProbability / infectionTime);
                    thisGame.deadPopulation += (int) (thisGame.gameDifficulty * 5 * thisGame.infectedPopulation * thisGame.deathProbability) / infectionTime;

                } else {

                    thisGame.worldPopulation -= (int) (thisGame.infectedPopulation * thisGame.deathProbability / infectionTime);
                    thisGame.infectedPopulation -= (int) (thisGame.infectedPopulation * thisGame.deathProbability / infectionTime);
                    thisGame.deadPopulation += (int) (thisGame.infectedPopulation * thisGame.deathProbability / infectionTime);

                }
            }
            if (thisGame.worldPopulation <= 0|| thisGame.infectedPopulation==0) {
                JOptionPane.showMessageDialog(new JFrame(), "świat skończył się " + thisGame.gameDate);
                stopProgress();
                System.out.println("AFTER PROGRESS STOP");
                return true;
            }
            return false;
        }
    }

    void stopProgress() {
        try {
            wait(100);

        } catch (Exception e) {
            e.getMessage();
        }
        System.out.println("STOPING PROGRESS");
        stop();
    }

    public void giveGame(NewGame game){
        super.thisGame = game;
    }

}

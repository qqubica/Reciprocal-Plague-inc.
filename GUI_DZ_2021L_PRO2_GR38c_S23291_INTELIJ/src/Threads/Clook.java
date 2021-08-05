package Threads;

import Game.NewGame;
import GameComponents.MyJpb;

public class Clook extends BasicThread {
    int simSpeed = 300;
    boolean gameEnded = false;
    int szescZer;

    @Override
    public void run() {
        thisGame.startJbp();
        while (!gameEnded) {
            try {
                Thread.sleep(simSpeed);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            nextDay();
        }

        ((MyJpb) (thisGame.infoPanel.getComponent(1))).stopMyJpb();

        System.out.println("STOP");
        thisGame.gameEnd();

        stop();
    }


    void nextDay() {
        synchronized (monitor) {
            thisGame.gameDate = thisGame.gameDate.plusDays(1);
            thisGame.infoPanel.setClook(thisGame.gameDate);
            thisGame.infoPanel.setNrOfInfected(thisGame.infectedPopulation,
                    thisGame.worldPopulation - thisGame.infectedPopulation <= 0 ? 0 : thisGame.worldPopulation - thisGame.infectedPopulation,
                    thisGame.money);
            thisGame.infoPanel.jpb.resetJpb();
            if (thisGame.virusProgress.checkProgress()) {
                System.out.println("PROGRES STOP SUCCESFULL");
                gameEnded = true;
            }
            thisGame.money += 50;
            if (calcualte6zer() != szescZer) {
                szescZer = calcualte6zer();
                thisGame.pack();
            }
        }
    }

    int calcualte6zer() {
        int tmpM = thisGame.money;
        int siedemZer = 0;
        while (tmpM / 10 > 0) {
            tmpM /= 10;
            siedemZer++;
        }
        return siedemZer++;
    }

    public void giveGame(NewGame game) {
        thisGame = game;
    }

    public int getJpbSpeed() {
        return simSpeed / 100;
    }

}

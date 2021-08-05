package Game;

import javax.swing.*;

public class DifficultySelector extends JOptionPane{
    DifficultySelector() {
        System.out.println("Select Difficulty");
    }
    public int selectDifficulty(){
        JFrame difficultySelect = new JFrame();
        String[] DifficultyLvl = {"latwy", "normalny", "trudny"};

        return showOptionDialog(
                difficultySelect, "Wybierz poziom trudnosci",
                "Wybierz poziom trudnosci", JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, DifficultyLvl, DifficultyLvl[1]
        );
    }
}

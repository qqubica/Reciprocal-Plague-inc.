package MainManu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;


public class HiScoreButton extends JButton {
    int tmp;
    String score;
    Vector<String> hiScores = new Vector<>();
    public JList<String> hiScoresList;
    JFrame scoreFrame= new JFrame();

    public HiScoreButton() {
        setText("Najlepsze wyniki");
        setAlignmentX(Component.CENTER_ALIGNMENT);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSavedScores();

                if (!hiScores.isEmpty()) {

                    System.out.println("showing best scores");
                    System.out.println("hiScores Vector\t"+hiScores);

                    int [] forSortArr =new int[hiScores.size()];

                    for (int i = 0; i < hiScores.size(); i++)
                        forSortArr[i]=(Integer.parseInt(hiScores.get(i).split(" ")[1]));

                    int tmp;
                    String tmpString;

                    String [] strArr = new String[hiScores.size()];
                    Vector<String> backup = hiScores;

                    hiScores.copyInto(strArr);

                    for (int i = 0; i < forSortArr.length; i++) {
                        for (int j = 0; j < forSortArr.length; j++) {
                            if (forSortArr[i] > forSortArr[j]) {
                                tmp = forSortArr[i];
                                forSortArr[i] = forSortArr[j];
                                forSortArr[j] = tmp;

                                tmpString = strArr[i];
                                strArr[i] = strArr[j];
                                strArr[j] = tmpString;
                            }
                        }
                    }

                    hiScores.removeAllElements();

                    for (int i = 0; i < strArr.length; i++) {
                        strArr[i]=i+1+" "+strArr[i];
                        hiScores.add(strArr[i]);
                    }

                    JFrame scoreFrame= new JFrame();

                    scoreFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            if (isVisible()) {
                                setVisible(true);
                            }
                        }
                    });

                    hiScoresList = new JList<>(hiScores);
                    hiScoresList.setLayoutOrientation(JList.VERTICAL);
                    hiScoresList.setFont(new Font("biger",1,18));

                    scoreFrame.setLocationRelativeTo(null);
                    scoreFrame.setVisible(true);
                    scoreFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    scoreFrame.addWindowListener(new WindowAdapter() {
                       @Override
                        public void windowClosing(WindowEvent e) {
                           hiScores.removeAllElements();
                           hiScores = backup;
                        }
                    });


                    JScrollPane jsp = new JScrollPane(hiScoresList);

                    scoreFrame.getContentPane().add(jsp);

                    scoreFrame.pack();

                } else {
                    System.out.println("No scores to show yet");
                    JOptionPane.showMessageDialog(scoreFrame, "Na razie nie ma zapisanych rekordów");
                }
            }
        });
    }

    void saveScores(String score){
        try {
            FileWriter fw = new FileWriter("Results.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            if (!(new FileReader("Results.txt").read()==-1)){
                bw.newLine();
            }
            bw.write(score);
            bw.close();
            fw.close();
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void getSavedScores(){
        score = "";

        try {
            FileReader fr = new FileReader("Results.txt");

            tmp = fr.read();

            while (tmp != -1) {
                while (tmp != 10 && tmp != -1 && tmp != 13) {
                    score += (char) tmp;
                    tmp = fr.read();
                }
                while (tmp==10 || tmp == 13) {
                    tmp = fr.read();
                    if (tmp == 13){
                        tmp=fr.read();
                    }
                }

                hiScores.add(score);
                score = "";
            }
        } catch (Exception exception) {
            exception.getMessage();
        }
    }

    public void addNewScore(long score){
        String nick = null;
        while (nick==null) {
            nick = (String) (JOptionPane.showInputDialog(
                    new JFrame(),
                    "Wpisz swój nick aby zapisać wynik",
                    "Wprowadź swój nick",
                    JOptionPane.OK_OPTION,
                    null, null, null));
        }
        hiScores.add(nick+" "+score);
        saveScores(hiScores.lastElement());
    }
}

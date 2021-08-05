package GameComponents;

import javax.swing.*;

public class MyJpb extends JProgressBar {
    Thread jpbThread;
    int howFast;

    public MyJpb() {
        setStringPainted(true);
        setToolTipText("Postęp dnia");
        setValue(0);
        setMaximum(100);
        setStringPainted(true);
    }

    public void stopMyJpb(){
        jpbThread.stop();
    }

    public void setHowFast(int howFast) {
        this.howFast = howFast;
        startJpb();
    }

    public void resetJpb() {
        setValue(0);
        startJpb();
    }

    public void startJpb() {
        jpbThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (getValue() < 100) {
                    try {
                        Thread.sleep(howFast);
                    } catch (Exception e) {
                        e.getMessage();
                    }
                    setValue(getValue() + 1);
                }
            }
        });
        jpbThread.start();
    }
}

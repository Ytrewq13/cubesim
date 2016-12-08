package mycubetimer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EventHandler extends KeyAdapter {

    public boolean firstPress = true;
    public boolean stopping = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            if (this.firstPress) {
                this.firstPress = false;
                if (MyCubeTimer.cubeTimer.started() && !MyCubeTimer.cubeTimer.running()) {
                    MyCubeTimer.cubeTimer.stop();
                    MyCubeTimer.cubeTimer.reset();
                } else if (MyCubeTimer.cubeTimer.running()) {
                    MyCubeTimer.cubeTimer.stop();
                    Float timeSeconds = MyCubeTimer.cubeTimer.getElapsedTime();
                    timeSeconds = timeSeconds / 1000;
                    MyCubeTimer.addSolveTime(timeSeconds);
                    this.stopping = true;
                }
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Test.");
        if (e.getKeyCode() == 32 && !this.stopping) {
            if (!MyCubeTimer.cubeTimer.running()) {
                MyCubeTimer.cubeTimer.start();
            }
        }
        this.firstPress = true;
        this.stopping = false;
    }

}

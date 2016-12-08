package mycubetimer;

import java.util.ArrayList;
import java.util.List;

public class MyCubeTimer {

    public static Timer cubeTimer;
    public static TimerWindow window;
    private static List<Float> times = new ArrayList<Float>();

    public static void main(String[] args) {
        Cube myCube = new Cube();
        int[] windowDims = {400, 300};
        cubeTimer = new Timer();
        window = new TimerWindow(windowDims);
        EventHandler eh = new EventHandler();
        while (true) {
            putTime();
        }
    }

    public static void putTime() {
        String text = "";
        text += String.format("%.2f", (cubeTimer.getElapsedTime() / 1000));
        if (text.charAt(2) != '.' && (text.charAt(1) == '.')) {
            text = "0" + text;
        }
        while (text.length() < 5) {
            text += "0";
        }
        window.setLabel(text);
    }
    
    public static void addSolveTime(Float latestTime) {
        if (times.size() == 12) {
            times = times.subList(1, 12);
        }
        times.add(latestTime);
        Float[] timesArr = new Float[times.size()];
        for (int i = 0; i < times.size(); i++) {
            timesArr[i] = times.get(i);
        }
        String text = "";
        for (int i = 0; i < timesArr.length; i++) {
            text += String.format("%.2f", timesArr[i]) + "<br />";
        }
        window.setTimes(text);
        calcAvg();
    }
    
    private static void calcAvg() {
        float average;
        float sum = 0;
        for (int i = 0; i < times.size(); i++) {
            sum += times.get(i);
        }
        average = sum / times.size();
        window.setAvg(String.format("%.2f", average));
    }

}

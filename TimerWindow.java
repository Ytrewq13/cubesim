package mycubetimer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TimerWindow extends JFrame {
    private JFrame window;
    private JPanel panel;
    private JLabel label;
    private JLabel timeListLabel;
    private JLabel avgLbl;
    private int xdim;
    private int ydim;
    public TimerWindow(int[] dims) {
        this.xdim = dims[0];
        this.ydim = dims[1];
        this.window = new JFrame("My cube timer.");
        this.panel = new JPanel(new BorderLayout());
        EventHandler eh = new EventHandler();
        this.panel.addKeyListener(eh);
        this.label = new JLabel("", SwingConstants.CENTER);
        this.timeListLabel = new JLabel("", SwingConstants.RIGHT);
        this.avgLbl = new JLabel("", SwingConstants.LEFT);
        this.window.add(this.panel);
        this.panel.add(this.label, BorderLayout.CENTER);
        this.panel.add(this.timeListLabel, BorderLayout.EAST);
        this.panel.add(this.avgLbl, BorderLayout.WEST);
        window.setMinimumSize(new Dimension(xdim,ydim));
        this.window.setSize(xdim, ydim);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.pack();
        this.window.setVisible(true);
        this.panel.requestFocus();
    }
    public void setLabel(String text) {
        this.label.setText(text);
    }
    public void setTimes(String text) {
        this.timeListLabel.setText("<html>" + text + "</html>");
    }
    public void setAvg(String text) {
        this.avgLbl.setText(text);
    }
}

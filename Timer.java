package mycubetimer;

public class Timer {

    private long startTime = 0;
    private long stopTime = 0;
    private boolean running = false;
    private boolean started = false;
    
    public int timesCount = 0;

    // Method to start the stopwatch.
    public void start() {
        this.running = true;
        this.started = true;
        this.startTime = System.currentTimeMillis();
    }

    // Method to stop the stopwatch.
    public void stop() {
        this.running = false;
        this.stopTime = System.currentTimeMillis();
    }
    
    // Method to reset the stopwatch.
    public void reset() {
        this.running = false;
        this.started = false;
        this.timesCount++;
    }

    // Method to return the current elapsed time of the stopwatch.
    public float getElapsedTime() {
        float elapsed;
        if (this.running) {
            elapsed = System.currentTimeMillis() - this.startTime;
        } else if (!this.started) {
            elapsed = 0;
        } else {
            elapsed = this.stopTime - this.startTime;
        }
        return elapsed;
    }

    public float getElapsedSeconds() {
        float elapsed;
        if (this.running) {
            elapsed = ((System.currentTimeMillis() - this.startTime) / 1000);
        } else if (!this.started) {
            elapsed = 0;
        } else {
            elapsed = ((this.stopTime - this.startTime) / 1000);
        }
        return elapsed;
    }

    public boolean running() {
        return this.running;
    }
    public boolean started() {
        return this.started;
    }
    public void unStart() {
        this.started = false;
    }
}

import javax.swing.*;

public class Philosopher extends Thread {
    private int leftStick, rightStick, philosopherIndex;
    private FoodSticks chopStciks;
    private boolean isRun = true;
    private String state;
    private JTextArea window;

    public Philosopher (int leftStick, int rightStick, int index, FoodSticks chopStciks, JTextArea w){
        this.leftStick = leftStick;
        this.rightStick = rightStick;
        this.philosopherIndex = index;
        this.chopStciks = chopStciks;
        this.state = "thinking...";
        this.window = w;
    }

    public int getLeftStickId(){
        return this.leftStick;
    }

    public int getRightStickId(){
        return this.rightStick;
    }

    public int getPhilosopherIndex(){
        return this.philosopherIndex;
    }

    public void setState(String s){
        this.state = s;
    }

    public String getPhiloState(){
        return this.state;
    }

    public void run() {
        while (isRun){
            try {
                //System.out.println("Philosopher " + philosopherIndex + " is eating.");
                window.setText(window.getText() + "Philosopher " + philosopherIndex + " is eating.\n");
                this.setState("eating...");
                chopStciks.increaseCurrentlyEating();
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                chopStciks.releaseLeftStick( this);
                Thread.sleep((int) (Math.random() * 1000));
                //this.setState("releasing left stick.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                chopStciks.releaseRightStick( this);
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                this.setState("thinking...");
                //System.out.println("Philosopher " + philosopherIndex + " is thinking.");
                window.setText(window.getText() + "Philosopher " + philosopherIndex + " is thinking.\n");
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chopStciks.takeFoodSticks(this);
        }
        //this.interrupt();
    }

    public void stopRunning(){
        this.isRun = false;
    }
}

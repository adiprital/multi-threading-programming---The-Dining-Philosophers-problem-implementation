import javax.swing.*;

public class FoodSticks {   // shared objects
    public boolean[] sticks = new boolean[5];
    private int currentlyEating = 0;
    private JTextArea window;

    public FoodSticks(JTextArea w){
        for (int i = 0; i< sticks.length; i++){
            this.sticks[i] = false;
            this.window = w;
        }
    }

    public void increaseCurrentlyEating(){
        this.currentlyEating++;
        //System.out.println("currently eating right now: " + this.currentlyEating + " philosophers");
    }

    public void decreaseCurrentlyEating(){
        this.currentlyEating--;
        //System.out.println("currently eating right now: " + this.currentlyEating + " philosophers");
    }

    public synchronized void takeFoodSticks(Philosopher p)  {
        while(this.sticks[p.getLeftStickId()]){
            try{
                //System.out.println("Philosopher " + p.getPhilosopherIndex() + " is waiting for his left food stick");
                window.setText(window.getText() + "Philosopher " + p.getPhilosopherIndex() + " is waiting for his left food stick.\n");
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        sticks[p.getLeftStickId()] = true;
        //System.out.println("Philosopher " + p.getPhilosopherIndex() + " caught his left food stick number " + p.getLeftStickId());
        window.setText(window.getText() + "Philosopher " + p.getPhilosopherIndex() + " caught his left food stick number " + p.getLeftStickId()+".\n");
        while(this.sticks[p.getRightStickId()]){
            try{
                window.setText(window.getText() + "Philosopher " + p.getPhilosopherIndex() + " is waiting for his right food stick"+".\n");
                //System.out.println("Philosopher " + p.getPhilosopherIndex() + " is waiting for his right food stick");
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        sticks[p.getRightStickId()] = true;
        window.setText(window.getText() + "Philosopher " + p.getPhilosopherIndex() + " caught his right food stick number " + p.getRightStickId() + ".\n" );
        //System.out.println("Philosopher " + p.getPhilosopherIndex() + " caught his right food stick number " + p.getRightStickId());
    }

    public synchronized void releaseLeftStick(Philosopher p){
        this.sticks[p.getLeftStickId()] = false;
        p.setState("releasing left stick.");
        window.setText(window.getText() + "Philosopher " + p.getPhilosopherIndex() + " has released his left stick whose number is " + p.getLeftStickId() + ".\n" );
        //System.out.println("Philosopher " + p.getPhilosopherIndex() + " has released his left stick whose number is " + p.getLeftStickId());
    }

    public synchronized void releaseRightStick(Philosopher p){
        this.sticks[p.getRightStickId()] = false;
        p.setState("releasing right stick.");
        this.decreaseCurrentlyEating();
        window.setText(window.getText() + "Philosopher " + p.getPhilosopherIndex() + " has released his right stick whose number is " + p.getRightStickId() + ".\n");
        //System.out.println("Philosopher " + p.getPhilosopherIndex() + " has released his right stick whose number is " + p.getRightStickId());
        notifyAll();
    }
}

import javax.swing.*;

public class Printing extends Thread {
    private JTextArea window;
    private Philosopher[] philosophers;
    private boolean toPrint;

    public Printing(JTextArea j, Philosopher[] p){
        this.window = j;
        window.setText("printing...");
        this.philosophers = p;
        toPrint = true;
    }

    public void setToPrintFalse(){
        this.toPrint = false;
    }

    public void run() {
        String[] states = new String[5];
        while (toPrint) {
            for (int i = 0; i < philosophers.length; i++) {
                states[i] = philosophers[i].getPhiloState();
            }
            String res = "Philosopher 0: " + philosophers[0].getPhiloState() + "\nPhilosopher 1: " + philosophers[1].getPhiloState()+ "\nPhilosopher 2: " + philosophers[2].getPhiloState()+ "\nPhilosopher 3: " + philosophers[3].getPhiloState()+ "\nPhilosopher 4: " + philosophers[4].getPhiloState();
            window.setText(res);
        }
        //this.interrupt();
    }
}

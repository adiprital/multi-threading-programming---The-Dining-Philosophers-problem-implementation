import javax.swing.*;
import java.awt.event.*;

public class DiningPhilosophers extends JFrame {  // MainThread

    private JButton cmdStart, cmdStop;
    private JLabel st, dining;
    private JTextArea eating, all;
    private JScrollPane jsp;
    FoodSticks chopStciks;
    Printing print;
    Philosopher[] philosophers = new Philosopher[5];

    public DiningPhilosophers(){
        setLayout(null);
        handleTextArea();
        chopStciks =  new FoodSticks(all);
        handleLabel();
        handleButton();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }

    private void handleTextArea(){
        eating = new JTextArea();
        eating.setBounds(20,50,445,85);
        add(eating);

        all = new JTextArea();
        jsp = new JScrollPane(all);
        jsp.setBounds(20, 170, 445, 230);
        add(jsp);
    }

    private void handleLabel(){
        st = new JLabel("State:");
        st.setBounds(20,20, 300,30);
        add(st);

        dining = new JLabel("Dining philosophers problem:");
        dining.setBounds(20,140, 300,30);
        add(dining);
    }

    private void handleButton() {
        cmdStart = new JButton("Start");
        cmdStop = new JButton("Stop");

        cmdStart.setBounds(90, 410, 100, 30);
        cmdStop.setBounds(290, 410, 100, 30);

        add(cmdStart);
        add(cmdStop);

        cmdStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < philosophers.length; i++){
                    philosophers[i] = new Philosopher(i,(i + 1) % chopStciks.sticks.length, i, chopStciks, all);
                    if (i == philosophers.length - 1) {
                        philosophers[i] = new Philosopher((i + 1) % chopStciks.sticks.length,i, i, chopStciks, all);
                    }
                    chopStciks.takeFoodSticks(philosophers[i]);
                    philosophers[i].start();
                }
                print = new Printing(eating,philosophers);
                print.start();
            }
        });

        cmdStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < philosophers.length; i++){
                    philosophers[i].stopRunning();
                }
                print.setToPrintFalse();
            }
        });
    }

    public static void main(String args[])  {
        new DiningPhilosophers();
    }
}
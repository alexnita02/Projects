package GUI;

import BusinessLogic.*;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private JFrame frame;
    private JTextField timeLimit;
    private JLabel timeLimitLabel;
    private JTextField maxProcessingTime;
    private JLabel maxProcessingTimeLabel;
    private JTextField minProcessingTime;
    private JLabel minProcessingTimeLabel;
    private JTextField maxArrivalTime;
    private JLabel maxArrivalTimeLabel;
    private JTextField minArrivalTime;
    private JLabel minArrivalTimeLabel;
    private JTextField numberOfClients;
    private JLabel numberOfClientsLabel;
    private JTextField numberOfServers;
    private JLabel numberOfServersLabel;
    private JButton start;


    public SimulationFrame() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1280, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        timeLimit = new JTextField();
        timeLimit.setBounds(250, 18, 146, 36);
        frame.getContentPane().add(timeLimit);
        timeLimit.setColumns(10);

        timeLimitLabel = new JLabel("Simulation time =");
        timeLimitLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        timeLimitLabel.setBounds(100, 15, 185, 50);
        frame.getContentPane().add(timeLimitLabel);

        minArrivalTime = new JTextField();
        minArrivalTime.setBounds(250, 64, 146, 36);
        frame.getContentPane().add(minArrivalTime);
        minArrivalTime.setColumns(10);

        minArrivalTimeLabel = new JLabel("Minimal arrival time =");
        minArrivalTimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        minArrivalTimeLabel.setBounds(70, 70, 282, 29);
        frame.getContentPane().add(minArrivalTimeLabel);

        maxArrivalTime = new JTextField();
        maxArrivalTime.setBounds(727, 64, 146, 36);
        frame.getContentPane().add(maxArrivalTime);
        maxArrivalTime.setColumns(10);

        maxArrivalTimeLabel = new JLabel("Maximal arrival time =");
        maxArrivalTimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        maxArrivalTimeLabel.setBounds(550, 70, 262, 29);
        frame.getContentPane().add(maxArrivalTimeLabel);

        minProcessingTime = new JTextField();
        minProcessingTime.setColumns(10);
        minProcessingTime.setBounds(250, 118, 146, 36);
        frame.getContentPane().add(minProcessingTime);

        minProcessingTimeLabel = new JLabel("Minimal processing time =");
        minProcessingTimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        minProcessingTimeLabel.setBounds(35, 118, 227, 29);
        frame.getContentPane().add(minProcessingTimeLabel);

        maxProcessingTime = new JTextField();
        maxProcessingTime.setColumns(10);
        maxProcessingTime.setBounds(727, 111, 146, 36);
        frame.getContentPane().add(maxProcessingTime);

        maxProcessingTimeLabel = new JLabel("Maximal processing time =");
        maxProcessingTimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        maxProcessingTimeLabel.setBounds(510, 118, 214, 29);
        frame.getContentPane().add(maxProcessingTimeLabel);

        numberOfClients = new JTextField();
        numberOfClients.setColumns(10);
        numberOfClients.setBounds(250, 165, 146, 36);
        frame.getContentPane().add(numberOfClients);

        numberOfClientsLabel = new JLabel("Number of clients =");
        numberOfClientsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        numberOfClientsLabel.setBounds(84, 165, 191, 37);
        frame.getContentPane().add(numberOfClientsLabel);

        numberOfServers = new JTextField();
        numberOfServers.setColumns(10);
        numberOfServers.setBounds(727, 158, 146, 36);
        frame.getContentPane().add(numberOfServers);

        numberOfServersLabel = new JLabel("Number of queues =");
        numberOfServersLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        numberOfServersLabel.setBounds(560, 165, 191, 29);
        frame.getContentPane().add(numberOfServersLabel);

        start = new JButton("Start");
        start.setFont(new Font("Arial", Font.PLAIN, 18));
        start.setBounds(331, 228, 146, 36);
        frame.getContentPane().add(start);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SimulationFrame frame = new SimulationFrame();
        frame.setVisible(true);
        SimulationManager simulationManager = new SimulationManager(60,2,40,1,7,50,5,"SHORTEST_QUEUE");
      // SimulationManager simulationManager = new SimulationManager();
//        SimulationManager simulationManager = new SimulationManager(,);
        Thread thread = new Thread(simulationManager);
        thread.start();


    }
}
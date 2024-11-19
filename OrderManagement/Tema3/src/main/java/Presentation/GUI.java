//package Presentation;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//
//public class GUI extends JFrame {
//
//
//    private JButton clientsButton;
//    private JButton productsButton;
//    private JButton ordersButton;
//
//
//
//    public GUI() {
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setBounds(500, 400, 700, 380);
//        this.getContentPane().setLayout(null);
//
//        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);
//        Font hugeFont = new Font("Times New Roman",Font.PLAIN,32);
//
//        clientsButton = new JButton("Clients");
//        clientsButton.setBounds(250, 40, 200, 50);
//        getContentPane().add(clientsButton);
//
//
//        productsButton = new JButton("Products");
//        productsButton.setBounds(250, 120, 200, 50);
//        getContentPane().add(productsButton);
//
//        ordersButton = new JButton("Orders");
//        ordersButton.setBounds(250, 200, 200, 50);
//        getContentPane().add(ordersButton);
//
//
//    }


    package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

    public class GUI extends JFrame {
        private JButton clientsButton;
        private JButton productsButton;
        private JButton ordersButton;

        public GUI() {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setBounds(500, 400, 700, 380);
            this.getContentPane().setLayout(null);

            // Set a new background color
            this.getContentPane().setBackground(Color.lightGray);

            // Set a new font
            Font buttonFont = new Font("Arial", Font.BOLD, 18);
            Font titleFont = new Font("Arial", Font.BOLD, 32);

            JLabel titleLabel = new JLabel("Main Menu");
            titleLabel.setBounds(250, 40, 200, 50);
            titleLabel.setFont(titleFont);
            getContentPane().add(titleLabel);

            clientsButton = new JButton("Manage Clients");
            clientsButton.setBounds(250, 120, 200, 50);
            clientsButton.setFont(buttonFont);
            getContentPane().add(clientsButton);

            productsButton = new JButton("Manage Products");
            productsButton.setBounds(250, 200, 200, 50);
            productsButton.setFont(buttonFont);
            getContentPane().add(productsButton);

            ordersButton = new JButton("Manage Orders");
            ordersButton.setBounds(250, 280, 200, 50);
            ordersButton.setFont(buttonFont);
            getContentPane().add(ordersButton);
        }

    public void clientTableListener(ActionListener a1)
    {
        clientsButton.addActionListener(a1);
    }

    public void productTableListener(ActionListener a2)
    {
        productsButton.addActionListener(a2);
    }

    public void ordersTableListener(ActionListener a3)
    {
        ordersButton.addActionListener(a3);
    }


}
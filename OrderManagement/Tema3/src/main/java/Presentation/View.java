//package Presentation;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//
//public class View extends JFrame{
//    private JTextField textField;
//    private JTextField textField_1;
//    private JTextField textField_2;
//    private JTextField textField_3;
//    private JButton insertButton;
//    private JButton deleteButton;
//    private JButton updateButton;
//    private JButton back;
//    private JScrollPane scrollPane;
//    public View(GUI mainInterface) {
//
//
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        this.setBounds(500, 150, 900, 700);
//        this.getContentPane().setLayout(null);
//
//        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 22);
//
//        insertButton = new JButton("Insert Client");
//        insertButton.setBounds(31, 480, 200, 50);
//        getContentPane().add(insertButton);
//
//        deleteButton = new JButton("Delete Client");
//        deleteButton.setBounds(31, 540, 200, 50);
//        getContentPane().add(deleteButton);
//
//        updateButton = new JButton("Update Client");
//        updateButton.setBounds(31, 600, 200, 50);
//        getContentPane().add(updateButton);
//
//        scrollPane = new JScrollPane();
//        scrollPane.setBounds(890, 660, -818, -476);
//        getContentPane().add(scrollPane);
//
//        back = new JButton("Back");
//        back.addActionListener(e-> {
//            setVisible(false);
//            mainInterface.setVisible(true);
//
//        });
//        back.setBounds(700, 600, 200, 50);
//        getContentPane().add(back);
//
//        scrollPane = new JScrollPane();
//        scrollPane.setBounds(31, 25, 872, 405);
//        getContentPane().add(scrollPane);
//
//        textField = new JTextField();
//        textField.setBounds(500, 467, 86, 20);
//        getContentPane().add(textField);
//        textField.setColumns(10);
//
//        textField_1 = new JTextField();
//        textField_1.setBounds(500, 509, 86, 20);
//        getContentPane().add(textField_1);
//        textField_1.setColumns(10);
//
//        textField_2 = new JTextField();
//        textField_2.setBounds(500, 554, 86, 20);
//        getContentPane().add(textField_2);
//        textField_2.setColumns(10);
//
//        textField_3 = new JTextField();
//        textField_3.setBounds(500, 604, 86, 20);
//        getContentPane().add(textField_3);
//        textField_3.setColumns(10);
//
//        JLabel lblNewLabel = new JLabel("ID");
//        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
//        lblNewLabel.setBounds(470, 467, 46, 14);
//        getContentPane().add(lblNewLabel);
//
//        JLabel lblNewLabel_1 = new JLabel("First Name");
//        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
//        lblNewLabel_1.setBounds(425, 510, 93, 14);
//        getContentPane().add(lblNewLabel_1);
//
//        JLabel lblNewLabel_2 = new JLabel("Last Name");
//        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
//        lblNewLabel_2.setBounds(425, 555, 79, 14);
//        getContentPane().add(lblNewLabel_2);
//
//        JLabel lblNewLabel_3 = new JLabel("Age");
//        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
//        lblNewLabel_3.setBounds(465, 600, 79, 29);
//        getContentPane().add(lblNewLabel_3);
//
//
//    }

package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton backButton;
    private JScrollPane scrollPane;

    public View(GUI mainInterface) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Clients");
        this.setBounds(500, 150, 900, 700);
        this.getContentPane().setLayout(null);

        // Set a new background color
        this.getContentPane().setBackground(Color.lightGray);

        // Set a new font
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        insertButton = new JButton("Add Client");
        insertButton.setBounds(31, 480, 200, 50);
        insertButton.setFont(buttonFont);
        getContentPane().add(insertButton);

        deleteButton = new JButton("Remove Client");
        deleteButton.setBounds(31, 540, 200, 50);
        deleteButton.setFont(buttonFont);
        getContentPane().add(deleteButton);

        updateButton = new JButton("Update Client");
        updateButton.setBounds(31, 600, 200, 50);
        updateButton.setFont(buttonFont);
        getContentPane().add(updateButton);

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            setVisible(false);
            mainInterface.setVisible(true);
        });
        backButton.setBounds(700, 600, 200, 50);
        backButton.setFont(buttonFont);
        getContentPane().add(backButton);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(31, 25, 872, 405);
        getContentPane().add(scrollPane);

        textField = new JTextField();
        textField.setBounds(500, 467, 86, 20);
        getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(500, 509, 86, 20);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(500, 554, 86, 20);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(500, 604, 86, 20);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel.setBounds(470, 467, 46, 14);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("First Name");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(425, 510, 93, 14);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Last Name");
        lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(425, 555, 79, 14);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Age");
        lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNewLabel_3.setBounds(465, 600, 79, 29);
        getContentPane().add(lblNewLabel_3);
    }

    public String getText1() {
        return textField.getText();
    }


    public String getText2() {
        return textField_1.getText();
    }

    public String getText3() {
        return textField_2.getText();
    }

    public int getText4() {
        return Integer.parseInt(textField_3.getText());
    }


    public void insertListener(ActionListener a1){
        insertButton.addActionListener(a1);
    }


    public void deleteListener(ActionListener a2){
        deleteButton.addActionListener(a2);
    }


    public void updateListener(ActionListener a3){
        updateButton.addActionListener(a3);
    }


    public JScrollPane getMyScrollPane(){
        return this.scrollPane;
    }
}

package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductGUI extends JFrame{
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton backButton;
    private JScrollPane scrollPane;
    public ProductGUI(GUI mainInterface) {

        this.setTitle("Products");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 150, 900, 700);
        this.getContentPane().setLayout(null);

        Font biggerFont = new Font("Times New Roman", Font.PLAIN, 18);

        insertButton = new JButton("Insert Product");
        insertButton.setBounds(31, 480, 200, 50);
        getContentPane().add(insertButton);

        deleteButton = new JButton("Delete Product");
        deleteButton.setBounds(31, 540, 200, 50);
        getContentPane().add(deleteButton);

        updateButton = new JButton("Update Product");
        updateButton.setBounds(31, 600, 200, 50);
        getContentPane().add(updateButton);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(890, 655, -818, -476);
        getContentPane().add(scrollPane);

        backButton = new JButton("Back");
        backButton.addActionListener(e-> {
            setVisible(false);
            mainInterface.setVisible(true);

        });
        backButton.setBounds(680, 600, 200, 50);
        getContentPane().add(backButton);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 25, 872, 405);
        getContentPane().add(scrollPane);

        textField = new JTextField();
        textField.setBounds(504, 467, 86, 20);
        getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(504, 509, 86, 20);
        getContentPane().add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(504, 554, 86, 20);
        getContentPane().add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(504, 604, 86, 20);
        getContentPane().add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        lblNewLabel.setBounds(475, 470, 46, 14);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Name");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(460, 510, 93, 14);
        getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Price");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_2.setBounds(465, 555, 79, 14);
        getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Stock");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
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

    public String getText4() {
        return textField_3.getText();
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

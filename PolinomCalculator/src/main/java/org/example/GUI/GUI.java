package org.example.GUI;

import org.example.BusinessLogic.*;
import org.example.DataModels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;

public class GUI {

        private JFrame frame;
        private JTextField firstField;
        private JTextField secondField;
        private Operatie op=new Operatie();
        private Operatie op1=new Operatie();

        public GUI(){
            JFrame frame = new JFrame("Calculator de Polinoame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500,200);

            JPanel panel = new JPanel(new BorderLayout());

            JPanel firstPanel = new JPanel(new FlowLayout());
            firstPanel.add(new JLabel("Primul polinom:"));
            JTextField firstField = new JTextField(20);
            firstPanel.add(firstField);
            panel.add(firstPanel, BorderLayout.NORTH);


            JPanel secondPanel = new JPanel(new FlowLayout());
            secondPanel.add(new JLabel("Al doilea polinom"));
            JTextField secondField = new JTextField(20);
            secondPanel.add(secondField);
            panel.add(secondPanel, BorderLayout.CENTER);



            JPanel buttonPanel = new JPanel(new FlowLayout());
            JButton adunareButton = new JButton("Adunare");
            adunareButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Polinom p1 = parsarePolinom(firstField.getText());
                    Polinom p2 = parsarePolinom(secondField.getText());
                    Polinom suma = op1.adunare(p1, p2);
                    afisareGUI(suma);
                }
            });
            buttonPanel.add(adunareButton);



            JButton scadereButton = new JButton("Scadere");
            scadereButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Polinom p1 = parsarePolinom(firstField.getText());
                    Polinom p2 = parsarePolinom(secondField.getText());
                    Polinom diferenta = op1.scadere(p1, p2);
                    afisareGUI(diferenta);
                }
            });
            buttonPanel.add(scadereButton);

            JButton inmultireButton = new JButton("Inmultire");
            inmultireButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Polinom p1 = parsarePolinom(firstField.getText());
                    Polinom p2 = parsarePolinom(secondField.getText());
                    Polinom produs = op.inmultire(p1, p2);
                    System.out.println(p1);
                    System.out.println(p2);
                    afisareGUI(produs);

                }
            });
            buttonPanel.add(inmultireButton);

            JButton derivareButton = new JButton("Derivare P1:");
            derivareButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Polinom p1= parsarePolinom(firstField.getText());
                    Polinom derivare1=op.derivare(p1);
                    System.out.println(p1.toString());
                    afisareGUI(derivare1);

                }
            });
            buttonPanel.add(derivareButton);

            JButton derivareButton2 = new JButton("Derivare P2:");
            derivareButton2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Polinom p2= parsarePolinom(secondField.getText());
                    Polinom derivare2 = op1.derivare(p2);
                    afisareGUI(derivare2);
                }
            });
            buttonPanel.add(derivareButton2);



            panel.add(buttonPanel,BorderLayout.SOUTH);
            frame.add(panel);
            frame.pack();
            frame.setVisible(true);

        }

    private static Polinom parsarePolinom(String input) {
        Polinom p = new Polinom();
        //https://stackoverflow.com/questions/34946528/decode-polynomial-from-string-with-pattern-and-matcher
        //([+-]?(?:(?:\d+x\^\d+)|(?:\d+x)|(?:\d+)|(?:x)))

        Pattern pattern = Pattern.compile("([+-]?\\s*(?:(?:\\d*\\.?\\d+)\\s*x\\s*(?:\\^\\s*\\d+)?|\\d*\\.?\\d*\\s*x\\s*(?:\\^\\s*\\d+)?|\\d*\\.?\\d+|x))(?!\\^)");
        Matcher matcher = pattern.matcher(input);//[-+{0-9}?:{0-9}+.{0-9} x?^[0-9]|[0-9].[0-9]x^
        while (matcher.find()) {
            String match = matcher.group(1).replaceAll("\\s+", ""); //group(1) e ala mare si face match cu toti termenii polinomului
            float coef = 1.0f;  //
            int exp = 0;
            if (match.matches("[-+]?\\d*\\.?\\d*")) { //-+2.0  daca face match cu un nr neg/poz il conv la float fiind coeficient
                coef = Float.parseFloat(match);
            } else if (match.matches("[-+]?x")) { //-+x
                if(match.startsWith("-")){  // -
                    coef=-1.0f;
                }
                coef=1.0f;
                exp = 1;
            } else if (match.matches("[-+]?\\d*\\.?\\d*\\s*x(\\^\\d+)?")) { //-+2.0x^3 --monom complet

                String[] monoms = match.split("x\\^?");  //   strtok(p,"x^");
                if(monoms.length>0.0f){
                    if(monoms[0].isEmpty()!=true){   //coeficient monom
                        coef=Float.parseFloat(monoms[0]);
                    }
                    else
                        coef=1.0f;  //e gol

                }
                if(monoms.length>1){
                    exp=Integer.parseInt(monoms[1]);}
                    else{
                        exp=1;
                }

                    if (coef == 0.0f) {
                    exp = 0;
                }
            } else {
                throw new IllegalArgumentException("Polinom invalid: " + input);
            }
            p.adaugaMonom(exp, coef);
        }
        return p;
    }

    private static void afisareGUI(Polinom p) {
        String rezultat = "";
        boolean isFirst = true;
        for (Map.Entry<Integer, Float> monom: p.monom.descendingMap().entrySet()) {
            float coeficient = monom.getValue();
            int exponent = monom.getKey();
            if (coeficient != 0) {
                if (isFirst) {
                    isFirst = false;
                    if(coeficient<0)
                        rezultat += " - ";
                } else {
                    rezultat += (coeficient > 0 ? " + " : " - ");
                }
                rezultat += String.format("%.1f", Math.abs(coeficient));
                if (exponent > 0) {
                    rezultat += "x";
                    if (exponent > 1) {
                        rezultat += "^" + exponent + " ";
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, rezultat, "Rezultat", JOptionPane.INFORMATION_MESSAGE);
    }



}

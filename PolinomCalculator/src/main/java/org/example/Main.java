package org.example;

import org.example.BusinessLogic.*;
import org.example.DataModels.*;
import org.example.GUI.*;


public class Main {
    public static void main(String[] args) {

        Polinom p1 = new Polinom();
        p1.adaugaMonom(1, 2.5f);
        p1.adaugaMonom(3, 2.3f);
        p1.adaugaMonom(2, 3.4f);

        Polinom p2 = new Polinom();
        p2.adaugaMonom(1, 2.25f);
        p2.adaugaMonom(3, 2.7f);

        System.out.println("p1= " + p1.toString());
        System.out.println("p2= " + p2.toString());

        Operatie op = new Operatie();

        Polinom suma = op.adunare(p1, p2);
        System.out.println("p1+p2 = " + suma.toString());

        Polinom diferenta = op.scadere(p1, p2);
        System.out.println("p1-p2 = " + diferenta.toString());

        Polinom inmultire = op.inmultire(p1, p2);
        System.out.println("p1*p2 = " + inmultire.toString());

        Polinom derivare = op.derivare(p1);
        System.out.println("p1 derivat " + derivare.toString());

        Polinom derivare2 = op.derivare(p2);
        System.out.println("p2 derivat " + derivare2.toString());

        GUI guiobject = new GUI();

    }
}








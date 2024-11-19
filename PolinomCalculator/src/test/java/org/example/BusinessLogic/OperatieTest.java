package org.example.BusinessLogic;

import org.example.DataModels.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class OperatieTest {

    private Operatie operatie;
    private Polinom p1;
    private Polinom p2;
    @BeforeEach
    void setUp() {
        operatie=new Operatie();
    }

    @Test
    void adunare1() {

        Polinom p1=new Polinom();
        Polinom p2=new Polinom();
        p1.adaugaMonom(2,5.5f);
        p2.adaugaMonom(0,2.5f);
        Polinom rezultat1=new Polinom();
        rezultat1.adaugaMonom(2,5.5f);
        rezultat1.adaugaMonom(0,2.5f);
        Polinom rezultat2=operatie.adunare(p1,p2);
        assertEquals(rezultat1.toString(),rezultat2.toString());
    }

    @Test
    void adunare2(){
        Polinom p1=new Polinom();
        Polinom p2=new Polinom();
        p1.adaugaMonom(1,1.5f);
        p2.adaugaMonom(0,0.5f);
        Polinom rezultat1=new Polinom();
        rezultat1.adaugaMonom(1,1.5f);
        rezultat1.adaugaMonom(0,0.5f);
        Polinom rezultat2=operatie.adunare(p1,p2);
        assertEquals(rezultat1.toString(),rezultat2.toString());

    }

    @Test
    void scadere1() {
        Polinom p1=new Polinom();
        Polinom p2=new Polinom();
        p1.adaugaMonom(2,1.5f);
        p2.adaugaMonom(0,0.5f);
        Polinom rezultat1=new Polinom();
        rezultat1.adaugaMonom(2,1.5f);
        rezultat1=operatie.scadere(rezultat1,p2);
        Polinom rezultat2=operatie.scadere(p1,p2);
        assertEquals(rezultat1.toString(),rezultat2.toString());
    }

    @Test
    void scadere2() {
        Polinom p1=new Polinom();
        Polinom p2=new Polinom();
        p1.adaugaMonom(3,2.3f);
        p2.adaugaMonom(4,0.2f);
        Polinom rezultat1=new Polinom();
        rezultat1.adaugaMonom(3,2.3f);
        rezultat1=operatie.scadere(rezultat1,p2);
        Polinom rezultat2=operatie.scadere(p1,p2);
        assertEquals(rezultat1.toString(),rezultat2.toString());
    }
    @Test
    void inmultire1() {
        Polinom p1=new Polinom();
        Polinom p2=new Polinom();
        p1.adaugaMonom(2,2f);
        p2.adaugaMonom(1,2.5f);
        Polinom rezultat1=new Polinom();
        rezultat1.adaugaMonom(3,5f);
        Polinom rezultat2=operatie.inmultire(p1,p2);
        assertEquals(rezultat1.toString(),rezultat2.toString());
    }

    @Test
    void inmultire2() {
        Polinom p1=new Polinom();
        Polinom p2=new Polinom();
        p1.adaugaMonom(3,1f);
        p2.adaugaMonom(0,5.5f);
        Polinom rezultat1=new Polinom();
        rezultat1.adaugaMonom(3,5.5f);
        Polinom rezultat2=operatie.inmultire(p1,p2);
        assertEquals(rezultat1.toString(),rezultat2.toString());
    }

    @Test
    void derivare1() {
        Polinom p1=new Polinom();
        p1.adaugaMonom(2,3f);
        Polinom rezultat1=new Polinom();
        rezultat1.adaugaMonom(1,6f);
        Polinom rezultat2=operatie.derivare(p1);
        assertEquals(rezultat1.toString(),rezultat2.toString());
    }

    @Test
    void derivare2() {
        Polinom p1=new Polinom();
        p1.adaugaMonom(3,4f);
        Polinom rezultat1=new Polinom();
        rezultat1.adaugaMonom(2,12f);
        Polinom rezultat2=operatie.derivare(p1);
        assertEquals(rezultat1.toString(),rezultat2.toString());
    }
}
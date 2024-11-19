package org.example.DataModels;

import java.util.*;

public class Polinom {

    public TreeMap<Integer,Float>monom;

    public Polinom(){
        monom=new TreeMap<Integer,Float>();
    }
    public Polinom (TreeMap<Integer,Float>monom){

        this.monom=monom;
    }

    public void adaugaMonom(int exponent,float coeficient){
        monom.put(exponent,coeficient);
    }

    public Map<Integer, Float> getCoeficient() {
        return monom;
    }

    public void setMonom(TreeMap<Integer,Float> monom) {
        this.monom = monom;
    }

    public void setCoeficient(int exponent,float coeficient){
        monom.put(exponent,coeficient);
    }

    @Override
    public String toString() {
        String rez="";
        for(Map.Entry<Integer,Float>monom:monom.descendingMap().entrySet()) {
            float coeficient = monom.getValue();
            int exponent = monom.getKey();
            if (coeficient != 0) {
                if (coeficient > 0) {
                        rez = rez +"+";
                    } else {
                        rez = rez +"-";
                    }
            }

            rez = rez + String.format("%.1f", Math.abs(coeficient));
                /*am pus abs() ca sa evit situatiile cand un nr poz cu modul mai mare e al doilea operator la scadere
                si se dubleaza semnul minus ex. 2.3x^3-2.7x^3 = --0.4x^3 */
                if (exponent > 0) {
                    rez = rez+"x";
                    if (exponent > 1) {
                        rez = rez+"^"+exponent+"";
                    }
                }
        }

        return rez;
    }


}



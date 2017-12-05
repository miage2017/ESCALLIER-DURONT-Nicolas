package fr.unice.miage.tp2;

public class Compteur_Synchro {
	private int c = 0;
   
    public synchronized void add(int j) {
       c+=j;
    }
}

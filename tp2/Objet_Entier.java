package fr.unice.miage.tp2;

public class Objet_Entier {
    private int ma_valeur;
    public Objet_Entier(int ma_valeur)    {
    	this.ma_valeur=ma_valeur; 
    	System.out.format("Valeur partagee initialisee a %d\n", ma_valeur); 
    }
    public String  toString(){
	return Integer.toString (ma_valeur);    }
    public int val(){ return ma_valeur;}
    public     void add(int i) { ma_valeur+=i;} 

} 
   
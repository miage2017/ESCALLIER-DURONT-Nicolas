package fr.unice.miage.tp2;

public class Compteur implements Runnable {

	String nom="Toto";
	int maxIter= 1000 ;
	boolean direction = true;
	
	public Compteur(String  nom, boolean directionC)    {
		this.nom=nom;
		this.direction = directionC;
	}

	public void run()    {		
		for (int i = 0; i < maxIter; i++) {
		    if(direction == true){
		    	System.out.format("[%s] dit %d\n",nom,(i+1));
		    }
		    else{
		    	System.out.format("[%s] dit %d\n",nom,(1000-i));
		    }
		}
	}

	public static void main(String[] args)  throws  Exception {
		Compteur   objetExec  = new Compteur("TA", true);
		Thread TA = new Thread(objetExec);
		Compteur   objetExec2  = new Compteur("TB" , false);
		Thread TB = new Thread(objetExec2);
		TA.start();
		TB.start();
		System.out.format("Thread principal terminé  !\n");
	}	
}


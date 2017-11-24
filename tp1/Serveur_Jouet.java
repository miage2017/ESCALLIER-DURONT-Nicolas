package fr.unice.miage.tp1;
import java.net.*;
import java.io.*;
import java.util.*;

public class Serveur_Jouet {
	
	private static final String Finish="quit";
	public static ServerSocket gestionnaire_socket;
	public final static int port = 12000;
	public static Socket connection;
	
	public static void main(String[] args){
		try {
			System.out.println("Lancement du serveur \n");
			gestionnaire_socket = new ServerSocket(port);
			System.out.println("Le serveur est lancé \n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("erreur de lancement du serveur \n");
			e.printStackTrace();
		}
		
		try {
			System.out.println("On attend une connection");
			connection = gestionnaire_socket.accept();
			
			/*On récupére l'ip et le port du client qui viens de se connecté*/
			String c_ip = connection.getInetAddress().toString() ;
	        int c_port= connection.getPort();
	        System.out.format("client admis IP %s  sur le port %d\n", c_ip, c_port);
			
	        // Recuperation du flux entrant  la socket (lecture)
	        InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
	        BufferedReader flux_entrant = new BufferedReader(isr) ;
	        System.out.println("Mon Tampon de lecture est attache ");

	         // Récupération de notre flux de sortie (écriture)
	        PrintWriter ma_sortie = new PrintWriter(connection.getOutputStream() , true);
	        System.out.println("Mon Tampon pour ecrire  attache ");
	        System.out.format("Pret à servir  IP %s  sur le port %d\n", c_ip, c_port);

	        ma_sortie.format("Hello %s  sur le port %d,  Ready!\n" ,  c_ip, c_port );
	        
	        String  message_lu = new String();
		    int line_num =0 ;
		    
	        while (   (message_lu = flux_entrant.readLine())    != null      ){
	             // Si le client demande de terminer
	                 if (message_lu.contains(Finish) ){
	                     // on termine proprement
	                     System.out.format ("[%s] recu, Transmission finie\n",message_lu);
	                     ma_sortie.println("Vous etes VIRE");
	                     terminer();
	                 }
	                 System.out.format( "[%d]--> [%s]]\n", line_num, message_lu);
	                 line_num++;
	         }
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Si on est ici à priori le client à fermé la connection, sans envoyer finish (pex on peut tuer le processus telnet) 
	    System.out.println( "Client deconnecté, je termine\n" )  ;
	    terminer();
	}
	
	// Methode qui met fin à la connection
	private static void terminer(){
		try{
				if (connection != null) connection.close();
				if (gestionnaire_socket != null) gestionnaire_socket.close();
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
		System.exit(0);
	    }
}

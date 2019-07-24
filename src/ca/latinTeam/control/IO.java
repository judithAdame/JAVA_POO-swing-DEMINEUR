package ca.latinTeam.control;

import ca.latinTeam.modele.Jeux;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public  class IO {
    public static Jeux DeSerializacion(String monFichier){             
        try (ObjectInputStream fin = new ObjectInputStream(new FileInputStream(monFichier))){
            Object x = fin.readObject();
            if (x instanceof Jeux) {
                Jeux jeux = (Jeux) x;
                System.out.println("\nLe jeux a ete deseraliser dans le fichier "+monFichier);
                System.out.println("Voici le jeux  deseralisé: ");
                System.out.println(jeux.toString());
                return jeux;
            }
            else {
                System.out.println("Le fichier " + monFichier+" ne contient pas d'objet Jeux.");
            }
        }
        catch(Exception e){
            System.out.println("EREUR d'E/S");
	}
        return null;
    }
    
    public static void Serializacion (String monFichier, Jeux jeux){  
        try (ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(monFichier))){
            fout.writeObject(jeux);   
            System.out.println("Le jeux a ete sauvegardé dans le fichier "+monFichier);
                System.out.println("Voici le jeux  seralisé: ");
                System.out.println(jeux.toString());            
        }
        catch (IOException e)	{
            System.out.println("ERREUR d'E/S");
            e.printStackTrace();
        }
    }
}

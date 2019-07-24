package ca.latinTeam.modele;

import static ca.latinTeam.control.Constantes.*;
import java.io.Serializable;

public class Joueur  implements Serializable{
    private String nom;
    
    public Joueur(){
        this(NOM_JOUEUR);        
    }
    public Joueur(String j) {
        this.nom = estOKJoueur(j);
    }     
    
    public String estOKJoueur(String j){
        if (j.replaceAll(" ", "").equals("")) 
            return NOM_JOUEUR;
        return j;
    }
         
    public String getNom() {
        return nom;
    }
    @Override
    public String toString() {
        return " Joueur{" + "Joueur=" + nom +'}';
    }
}

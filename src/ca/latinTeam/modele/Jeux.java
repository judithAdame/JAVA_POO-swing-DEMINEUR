package ca.latinTeam.modele;

import static ca.latinTeam.control.Constantes.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Jeux  implements Serializable{
    private int nbCol;
    private int nbFil;
    private int nbMin;
    private int score;
    private Timer timer;  
    private Calendar date;
    private Joueur joueur;
    private Grille grille;
    
    public Jeux(){
        this(NB_FIL,NB_COL,NB_MIN,NOM_JOUEUR);
    }
    public Jeux(int f, int c, int m, String j){
        this.nbFil  = estOKFil(f);
        this.nbCol  = estOKCol(c);
        this.nbMin  = estOKMin(m);
        this.grille = new Grille(this.nbFil,this.nbCol,this.nbMin);
        
        this.joueur = new Joueur(j);
        this.score  = 0;
        this.timer  = new Timer();
        this.date   = new GregorianCalendar();
    }
    
    public Jeux(String fStr, String cStr, String mStr, String jStr){
        //valider et conversion de String to int
        int f = estOKFil(fStr);
        int c = estOKCol(cStr);
        int m = estOKMin(mStr);
        
        //valider les bon parametres
        this.nbFil = estOKFil(f);
        this.nbCol = estOKCol(c);
        this.nbMin = estOKMin(m);
        
        //creation de la grille
        this.grille = new Grille(this.nbFil,this.nbCol, this.nbMin);
        

        this.joueur = new Joueur(jStr);
        this.score = 0;
        this.timer = new Timer();
        this.date = new GregorianCalendar();
   }

    public int estOKFil(String fStr){
        if (!estEntier(fStr) || estVide(fStr))
            return NB_FIL;
        return (int)(Integer.parseInt(fStr));
    }
    public int estOKFil(int f){
        if (estNegatif(f)) f= f*-1;
        if (f == 0) f = NB_FIL;
        if (f > NB_MAX_FIL) f = NB_MAX_FIL;
        return f;
    }
    
    public int estOKCol(String cStr){
        if (!estEntier(cStr) || estVide(cStr))
            return NB_COL;
        return (int)(Integer.parseInt(cStr));
    }
    public int estOKCol(int c){
        if (estNegatif(c)) c= c*-1;
        if (c == 0) c = NB_COL;
        if (c > NB_MAX_COL) c = NB_MAX_COL;
        return c;
    }

    public int estOKMin(String mStr){
        if (!estEntier(mStr) || estVide(mStr))
            return NB_MIN;
        return (int)(Integer.parseInt(mStr));
    }
    
     public int estOKMin(int m){
        if (estNegatif(m)) m= m*-1;
        if (m == 0) m = NB_MIN;
        if (m >= (this.nbCol * this.nbFil)) 
            m = (this.nbCol * this.nbFil);
        return m;
    }
     
     public Joueur getJoueur(){
        return joueur;
    }

    public Grille getTGrille() {
        return grille;
    }
        
    public int getNbCol() {
        return nbCol;
    }

    public int getNbFil() {
        return nbFil;
    }

    public int getNbMin() {
        return nbMin;
    }

    public int getScore() {
        return score;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setNbCol(int nbCol) {
        this.nbCol = nbCol;
    }

    public void setNbFil(int nbFil) {
        this.nbFil = nbFil;
    }

    public void setNbMin(int nbMin) {
        this.nbMin = nbMin;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
    
    private boolean estEntier(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
    
    private boolean estNegatif(int i){
        if (i<0) return true;
        return false;
    }

    private boolean estVide (String str){
        if (str.equals("")) return true;
                return false;
    }
    @Override
    public String toString() { 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY hh:mm"); 
        return "Jeux{ \n" + this.joueur.toString() + ",\n nbCol=" + nbCol + ",\n nbFil=" + nbFil + ",\n bMin=" + nbMin + ",\n score=" + score + ",\n timer=" + timer.toString() + ",\n date=" + sdf.format(date.getTime()) + ",\n" +this.grille.toString()+"}";
    }  

}

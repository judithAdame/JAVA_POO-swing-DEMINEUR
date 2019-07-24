package ca.latinTeam.modele;

import static ca.latinTeam.control.Constantes.*;
import java.io.Serializable;

public class Grille implements Serializable{
    private  Cellule tCellule[][];
    
    public Grille(){
        this (NB_FIL,NB_COL,NB_MIN);
    }
    
    public Grille(int nbFil, int nbCol, int nbMin){
        int cMin, fMin;    
        
//creation dun tableau de Cellules
        this.tCellule = new Cellule[nbFil][nbCol];
        //generation du tableau
        for (int f=0; f<nbFil; f++) {
            for (int c=0; c<nbCol;c++) {    
                this.tCellule[f][c] = new Cellule();
            }
        }
        
        //planter les mines
        int m = nbMin;
        while (m > 0) {
            fMin = (int) Math.floor(Math.random() * nbFil);
            cMin = (int) Math.floor(Math.random() * nbCol);
            if (!this.tCellule[fMin][cMin].estMine()){ 
                this.tCellule[fMin][cMin].setMine(true);
                 m--;
            }
        }
        
        //contabiliser les numero de mines au tour de chaque Cellule
        for (int f=0; f<nbFil; f++){
            for (int c=0; c<nbCol;c++){ 
                if (this.tCellule[f][c].estMine()) {   
                    int nordF      = f-1;
                    int nordEstF   = f-1, nordEstC   = c+1;
                    int estC       = c+1;
                    int sudEstF    = f+1, sudEstC    = c+1;
                    int sudF       = f+1;
                    int sudOuestF  = f+1, sudOuestC  = c-1;
                    int ouestC     = c-1;
                    int nordOuestF = f-1, nordOuestC = c-1;
                            
                    if (nordF>=0 && nordF<nbFil)
                        if(!this.tCellule[nordF][c].estMine())
                            this.tCellule[nordF][c].addVoisine();
                    if (nordEstF>=0 && nordEstF<nbFil && nordEstC>=0 && nordEstC<nbCol)
                        if (!this.tCellule[nordEstF][nordEstC].estMine())
                            this.tCellule [nordEstF][nordEstC].addVoisine();
                    if (estC>=0 && estC<nbCol)
                        if (!this.tCellule[f][estC].estMine())
                            this.tCellule [f][estC].addVoisine();
                    if (sudEstF>=0 && sudEstF<nbFil && sudEstC>=0 && sudEstC<nbCol)
                        if(!this.tCellule[sudEstF][sudEstC].estMine())
                            this.tCellule[sudEstF][sudEstC].addVoisine();
                    if (sudF>=0 && sudF<nbFil)
                        if (!this.tCellule[sudF][c].estMine())
                            this.tCellule[sudF][c].addVoisine();    
                    if (sudOuestF>=0 && sudOuestF<nbFil && sudOuestC>=0 && sudOuestC<nbCol)
                        if (!this.tCellule[sudOuestF][sudOuestC].estMine())
                            this.tCellule[sudOuestF][sudOuestC].addVoisine();
                    if (ouestC>=0)
                        if (!this.tCellule[f][ouestC].estMine())
                            this.tCellule[f][ouestC].addVoisine();
                    if (nordOuestF>=0 && nordOuestF<nbFil && nordOuestC>=0 && nordOuestC<nbCol)
                        if (!this.tCellule[nordOuestF][nordOuestC].estMine())
                            this.tCellule[nordOuestF][nordOuestC].addVoisine();                        
                }
            }
        }
    } 
    
    public Cellule[][] getCellule(){
        return this.tCellule;
    }
        public int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;

        }
    }

    public int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;

        }
    }
    
    public void devoileCellule(int f, int c, Cellule TCellule[][]) {        
        int nbFil = tCellule.length;
        int nbCol = tCellule[0].length;
        if (!this.tCellule[f][c].estMine() && !this.tCellule[f][c].estDevoiler()) {
            this.tCellule[f][c].setDevoiler(true);
            int maxF = max(0, f - 1);
            int minF = min(nbFil, f + 2);
            int maxC = max(0, c - 1);
            int minC = min(nbCol, c + 2);
            // System.out.println("Cellule devoile");
            // cont++;
            for (int i = maxF; i < minF; i++) {
                for (int j = maxC; j < minC; j++) {
                    if (!TCellule[i][j].estMine()) {
                        //this.TCellule[i][j].setDevoiler(true);
                        devoileCellule(i, j, TCellule);
                    }
                }
            }
       }
        System.out.println(this.toString());
    }
    
    //@Override
    public String toString() {
        String str1 = "Grille{\n";
        int nbFil = tCellule.length;
        int nbCol = tCellule[0].length;
        for (int f=0; f<nbFil; f++) {
            for (int c=0; c<nbCol;c++) { 
                str1 = str1 + "[" + f + "," + c + "]" + tCellule[f][c].toString() + "\t";
            }
            str1 = str1 + "\n";
        }
        return str1;
    }
}
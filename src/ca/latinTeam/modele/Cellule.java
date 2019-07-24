package ca.latinTeam.modele;

import java.io.Serializable;
import java.util.Observable;

public class Cellule  extends Observable implements Serializable{
    private boolean mine;
    private int     voisine;
    private boolean devoiler;

    public Cellule(){
        this (false,0,false);
    }
    public Cellule(boolean m, int v, boolean d) {
        this.mine     = m;
        this.voisine  = v;
        this.devoiler = d;
    }

    public boolean estMine() {
        return this.mine;
    }
    public boolean estDevoiler() {
        return this.devoiler;
    }
    public int getVoisine() {
        return this.voisine;
    }
    
    public void setMine(boolean m) {
        this.mine = m;
    }
    public void setDevoiler(boolean d){
        this.devoiler = d;        
        setChanged();
        notifyObservers();
    }
    public void setVoisine(int v) {
        this.voisine = v;
    }

    public void addVoisine(){
        this.voisine++;
    }

    
    @Override
    public String toString() {
        String dev = "DEV";
        String min = "MIN";
        if (!mine) min = "---";
        if (!devoiler) dev = "---";
        return "{"+min+","+voisine+","+dev+'}';
    }
}
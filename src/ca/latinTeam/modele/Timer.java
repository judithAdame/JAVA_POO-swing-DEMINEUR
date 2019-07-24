package ca.latinTeam.modele;

import java.io.Serializable;


public class Timer   implements Serializable{
    private Integer secondes;
    private Integer minutes;
    private Integer miles;
    
    public Timer(){
        this(0,0,0);
    }
    public Timer(Integer sec, Integer min, Integer mil){
        this.secondes = sec;
        this.minutes = min;
        this.miles = mil;
    }    
    public Integer getSecondes(){
        return this.secondes;
    }
    public Integer getMinutes(){
        return this.minutes;
    }
    public Integer getMiles(){
        return this.miles;
    }
    @Override
    public String toString() {
        return secondes + ":" + minutes + ":" + miles;
    }    
}

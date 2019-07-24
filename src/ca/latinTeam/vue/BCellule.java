package ca.latinTeam.vue;

import static ca.latinTeam.control.Constantes.*;
import ca.latinTeam.modele.Cellule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.*;

public class BCellule extends JButton implements Observer, ActionListener {    
    private Cellule cellule;
    
    public BCellule(){
        this(new Cellule());
    }
    
    public BCellule(Cellule cel){
        this.cellule = cel;
        this.cellule.addObserver(this);
        this.addActionListener(this);
    }
    
    public BCellule(int f, int c, Cellule cel){        
        this(cel);
        this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_ENCOUR)));
        this.setName(Integer.toString(f)+","+Integer.toString(c));
        this.setActionCommand(NOM_CELLULE);
        this.setText(cellule.toString());
        if (cellule.estDevoiler())
          this.setEnabled(false);
        else 
           this.setEnabled(true);
    }
    
    public int finDuJeux(){   
            this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_MINE)));
            this.setText(cellule.toString());             
            System.out.println("GAME OVER!!");     
            return JOptionPane.showConfirmDialog(this, "Voulez-vous recommencer?","VOUS AVEZ PERDU!", YES_NO_OPTION, QUESTION_MESSAGE, new ImageIcon(PATH_ICON_JEUX.concat(ICON_GAME_OVER)));
    }
    
    public void afficherVoisines(){
            int nbVoisines = cellule.getVoisine();
            this.setText(cellule.toString());
            switch(nbVoisines){
                case 0 :    {this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_GAGNE)));
                            break;}
                case 1 :    {this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_1)));
                            break;}
                case 2 :    {this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_2)));
                            break;}
                case 3 :    {this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_3)));
                            break;}
                case 4 :    {this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_4)));
                            break;}
                case 5 :    {this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_5)));
                            break;}
                case 6 :    {this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_6)));
                            break;}
                case 7 :    {this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_7)));
                            break;}
                case 8 :    {this.setIcon(new ImageIcon(PATH_ICON_JEUX.concat(ICON_8)));
                            break;}                          
            } 
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Cellule) {
            Cellule cellule = (Cellule)o;
            if (this.cellule.estMine()) {
                if (finDuJeux() == 0){
                    this.setVisible(false);                    
                    System.out.println("On va aller re-jouer!!");
                    new PParametres(); 
                }
                else {                   
                    System.out.println("FIN DU JEUX!!");                    
                    JOptionPane.showMessageDialog(this, "Vous allez nous manquer!");
                    System.exit(0);
                }
            } else {  
                this.afficherVoisines();                
                this.setEnabled(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.cellule.setDevoiler(true);
    }
    
    @Override
    public String toString() {
        return cellule.toString();
    }   
}
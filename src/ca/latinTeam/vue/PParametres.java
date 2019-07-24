package ca.latinTeam.vue;

import static ca.latinTeam.control.Constantes.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PParametres {       
        private String fStr;
        private String cStr;
        private String mStr;
        private String jStr;
        
        public PParametres (){  
            fenetreParametres();  
        }       
        
        public void fenetreParametres(){        
            JFrame      fenetre             = new JFrame(NOM_JEUX);
            JPanel      panelPresentation   = new JPanel();
            JLabel      fondDemineur        = new JLabel();

            JLabel      lNomJoueur          = new JLabel("Nom: ");
            JTextField  tnomJoueur          = new JTextField(NOM_JOUEUR);

            JLabel      lnbMin              = new JLabel("num mines: ");
            JTextField  tnbMin              = new JTextField(Integer.toString(NB_MIN));

            JLabel      lnbFil              = new JLabel("num files: ");        
            JTextField  tnbFil              = new JTextField(Integer.toString(NB_FIL));

            JLabel      lnbCol              = new JLabel("num columnes: ");        
            JTextField  tnbCol              = new JTextField(Integer.toString(NB_COL));

            JButton     bJouer              = new JButton("JOUER"); 

            
            fenetre.getContentPane().repaint();
            fenetre.setSize(SIZE1_PANNEAU,SIZE1_PANNEAU);
            fenetre.setLocationRelativeTo(null);
            fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            fenetre.setResizable(false);
            fenetre.setLayout(null);
            fenetre.setBackground(Color.white);

            panelPresentation.setSize(fenetre.getWidth(), fenetre.getHeight());
            panelPresentation.setLocation(0,0);
            panelPresentation.setLayout(null);
            panelPresentation.setBackground(Color.white);

            fondDemineur.setIcon(new ImageIcon(PATH_IMG_JEUX.concat(NOM_FOND_PARAMETRE)));
            fondDemineur.setBounds(0,0, panelPresentation.getWidth(), panelPresentation.getHeight());
            panelPresentation.add(fondDemineur);

            tnomJoueur.setSize(SIZE1,SIZE2);
            tnomJoueur.setLocation(panelPresentation.getWidth()-tnomJoueur.getWidth()-POS_DROIT,POS_GAUCHE+(40*0));
            panelPresentation.add(tnomJoueur);

            lNomJoueur.setSize(SIZE1,SIZE2);
            lNomJoueur.setLocation(panelPresentation.getWidth()-tnomJoueur.getWidth()-lNomJoueur.getWidth()-POS_DROIT,POS_GAUCHE+(40*0));
            panelPresentation.add(lNomJoueur,0);

            tnbFil.setSize(SIZE1,SIZE2);
            tnbFil.setLocation(panelPresentation.getWidth()-tnbFil.getWidth()-POS_DROIT,POS_GAUCHE+(40*1));
            panelPresentation.add(tnbFil);

            lnbFil.setSize(SIZE1,SIZE2);
            lnbFil.setLocation(panelPresentation.getWidth()-tnbFil.getWidth()-lnbFil.getWidth()-POS_DROIT,POS_GAUCHE+(40*1));
            panelPresentation.add(lnbFil,0);

            tnbCol.setSize(SIZE1,SIZE2);
            tnbCol.setLocation(panelPresentation.getWidth()-tnbCol.getWidth()-POS_DROIT,POS_GAUCHE+(40*2));
            panelPresentation.add(tnbCol);      

            lnbCol.setSize(SIZE1,SIZE2);
            lnbCol.setLocation(panelPresentation.getWidth()-tnbFil.getWidth()-lnbCol.getWidth()-POS_DROIT,POS_GAUCHE+(40*2));
            panelPresentation.add(lnbCol,0);

            tnbMin.setSize(SIZE1,SIZE2);
            tnbMin.setLocation(panelPresentation.getWidth()-tnbMin.getWidth()-POS_DROIT,POS_GAUCHE+(40*3));
            panelPresentation.add(tnbMin);      

            lnbMin.setSize(SIZE1,SIZE2);
            lnbMin.setLocation(panelPresentation.getWidth()-tnbMin.getWidth()-lnbMin.getWidth()-POS_DROIT,POS_GAUCHE+(40*3));
            panelPresentation.add(lnbMin,0);

            bJouer.setSize(SIZE1,SIZE2);
            bJouer.setLocation(panelPresentation.getWidth()-tnbCol.getWidth()-POS_DROIT,POS_GAUCHE+(40*4));
            panelPresentation.add(bJouer,0);     
           
            fenetre.add(panelPresentation);
            fenetre.getContentPane().repaint();
            fenetre.setVisible(true);
            
            
            bJouer.addMouseListener(new MouseAdapter(){
                @Override
                public void mousePressed(MouseEvent e){   
                    fStr = tnbFil.getText();
                    cStr = tnbCol.getText();
                    mStr = tnbMin.getText();
                    jStr = tnomJoueur.getText();
                    new PDemineur(fStr, cStr, mStr, jStr);
                    fenetre.dispose();
                    System.out.println("On va aller jouer!!");
                }               
            });
    }   

    public String getfStr() {
        return fStr;
    }

    public String getcStr() {
        return cStr;
    }

    public String getmStr() {
        return mStr;
    }

    public String getjStr() {
        return jStr;
    }

    public void setfStr(String f) {
        this.fStr = f;
    }

    public void setcStr(String c) {
        this.cStr = c;
    }

    public void setmStr(String m) {
        this.mStr = m;
    }

    public void setjStr(String j) {
        this.jStr = j;
    }
        
}
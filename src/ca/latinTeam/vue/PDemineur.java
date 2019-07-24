package ca.latinTeam.vue;

import ca.latinTeam.modele.Timer;
import static ca.latinTeam.control.Constantes.*;
import static ca.latinTeam.control.IO.*;

import ca.latinTeam.modele.Jeux;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PDemineur extends JFrame implements Runnable, ActionListener {  
    private Jeux jeux;
    private Thread th;  
    private JLabel lTimer;
    private int nbCellulesDev;
        
    public PDemineur(String fStr, String cStr, String  mStr, String nStr) {
        this.jeux = new Jeux(fStr, cStr, mStr, nStr);
        fenetreDemineur();
        this.nbCellulesDev = (this.jeux.getNbCol()*this.jeux.getNbFil())-this.jeux.getNbMin();
    } 
    
    public PDemineur(Jeux j){
        this.jeux = j;
        fenetreDemineur();
        this.nbCellulesDev = (this.jeux.getNbCol()*this.jeux.getNbFil())-this.jeux.getNbMin();
    }


     public void fenetreDemineur() throws HeadlessException {
    // formation de mon frame principal
        JMenuBar    mb;
        JMenu       mFichier;
        JMenuItem   mItemEnregistrer, mItemOuvrir, mItemSortir, mItemNouveau;
        JPanel      pCentre, pNord, pEst, pOuest, pSud;
        JLabel      lNomJoueur, lNbFil, lNbCol, lNbMin, lScore, lFondDemineur1, lFondDemineur2;
        JButton     bArreter, bRecommencer;
        
        int nbFil = jeux.getNbFil();
        int nbCol = jeux.getNbCol();
                 
        // definition de composants
            pCentre         = new JPanel();
            pNord           = new JPanel(); 
            pEst            = new JPanel(); 
            pOuest          = new JPanel(); 
            pSud            = new JPanel(); 
        
        // definition de components        
            mb               = new JMenuBar();
            mFichier         = new JMenu("Fichier");
            mItemEnregistrer = new JMenuItem(NOM_ENREGISTRER); 
            mItemOuvrir      = new JMenuItem(NOM_OUVRIR);
            mItemSortir      = new JMenuItem(NOM_SORTIR);
            mItemNouveau     = new JMenuItem(NOM_NOUVEAU);
            lNomJoueur       = new JLabel(" JOUEUR: "   + jeux.getJoueur().getNom()); 
            lNbMin           = new JLabel(" MINES: "    + jeux.getNbMin());
            lNbFil           = new JLabel(" LINES: "    + jeux.getNbFil());
            lNbCol           = new JLabel(" COLONNES: " + jeux.getNbCol());
            lTimer           = new JLabel(" TIMER: "    + jeux.getTimer());
            lScore           = new JLabel(" SCORE: "    + jeux.getScore());
            bArreter         = new JButton(NOM_ARRETER);
            bRecommencer     = new JButton(NOM_PARTIR);
            lFondDemineur1   = new JLabel();
            lFondDemineur2   = new JLabel();
            
            mItemEnregistrer.setActionCommand(NOM_ENREGISTRER);
            mItemOuvrir.setActionCommand(NOM_OUVRIR);
            mItemSortir.setActionCommand(NOM_SORTIR);
            mItemNouveau.setActionCommand(NOM_NOUVEAU);
            bArreter.setActionCommand(NOM_ARRETER);
            bRecommencer.setActionCommand(NOM_PARTIR);
            
        //definition de la mise en page de mes paneaux        
            pNord.setLayout     (new FlowLayout(FlowLayout.CENTER));
            pEst.setLayout      (new FlowLayout());
            pOuest.setLayout    (new FlowLayout());
            pSud.setLayout      (new GridLayout(1,6));
            pCentre.setLayout   (new GridLayout(nbFil,nbCol));
                        
        // contruction de mon LAYOUT_NORD 
            mFichier.add(mItemNouveau);
            mItemNouveau.addActionListener(this);       
            mFichier.add(mItemEnregistrer);
            mItemEnregistrer.addActionListener(this);
            mFichier.add(mItemOuvrir);
            mItemOuvrir.addActionListener(this);
            mFichier.add(mItemSortir); 
            mItemSortir.addActionListener(this);
            mb.add(mFichier);
            this.setJMenuBar(mb);
            pNord.add(lNomJoueur);  
            mb.add(pNord);

            lTimer = new JLabel(jeux.getTimer().toString());

        // contruction de mon LAYOUT_SUD 
            pSud.add(lNbCol);
            pSud.add(lNbFil);
            pSud.add(lNbMin);
            pSud.add(bRecommencer);
            bRecommencer.addActionListener(this);
            pSud.add(lTimer);
            pSud.add(bArreter);
            bArreter.addActionListener(this);
            pSud.add(lScore);
            

            lFondDemineur1.setIcon(new ImageIcon(PATH_IMG_JEUX.concat(NOM_FOND_DEMINEUR1)));
            lFondDemineur2.setIcon(new ImageIcon(PATH_IMG_JEUX.concat(NOM_FOND_DEMINEUR2)));

            pEst.add(lFondDemineur1);
            pOuest.add(lFondDemineur2);
            
        //jeux.getTGrille().devoileCellule(nbFil, nbCol, jeux.getTGrille().getCellule());
        // construction de ma celule LAYOUT_CENTER
            //ajouter mes buttons dans le panel
            for (int f=0; f<nbFil; f++) {
                for (int c=0; c<nbCol;c++) {
                    pCentre.add(new BCellule(f, c, jeux.getTGrille().getCellule()[f][c]));
                }
            }
            pCentre.repaint();
            
        // posicionement de mes JPanel dans les 5 celules
            this.add(mb,      BorderLayout.NORTH);
            this.add(pOuest,  BorderLayout.WEST);
            this.add(pSud,    BorderLayout.SOUTH);
            this.add(pCentre, BorderLayout.CENTER);
            this.add(pEst,    BorderLayout.EAST);
        
        // definition du comportement de mon frame        
            this.setTitle(NOM_JEUX);     
            this.setSize(SIZE1_PANNEAU, SIZE2_PANNEAU);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setBackground(Color.white);
            this.getContentPane().repaint();
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowAdapter() {
                //I skipped unused callbacks for readability
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("FIN DU JEUX!!");                    
                    JOptionPane.showMessageDialog(null, "Vous allez nous manquer! Au revoir!");
                    JFileChooser jfc = new JFileChooser(PATH_FICHIER_JEUX);
                    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_NOM_FICHIER);
                    String str = jeux.getJoueur().getNom().replaceAll(" ","_");
                    String nomFichier = str + sdf.format(new GregorianCalendar().getTime()).concat(EXTENTION_JEUX);
                    jfc.setSelectedFile(new File(PATH_FICHIER_JEUX.concat(nomFichier)));      
                    String fichierDestination = jfc.getSelectedFile().getAbsolutePath(); 
                    JOptionPane.showMessageDialog(null, "Votre jeux a ete souvarger [" + fichierDestination + "]");
                    Serializacion(fichierDestination, jeux);                    
                    System.exit(0);
                }
            });
        
            this.setVisible(true);           
    }
    
    @Override
    public void run() {
        Integer minutes = 0, secondes = 0, miles = 0;
        String min = "", seg = "", mil = "";
        while (!th.isInterrupted()) {
            try {
                Thread.sleep(1);
            } 
            catch (InterruptedException ex) {
                System.out.println("Interrompu par InterruptedException");
                break;
            }
            
            miles += 1;
            if (miles == 1000) {
                miles = 0;
                secondes += 1;
                if (secondes == 60) {
                    secondes = 0;
                    minutes++;
                }
            }

            if (minutes < 10) {
                min = "0" + minutes;
            } else {
                min = minutes.toString();
            }
            
            if (secondes < 10) {
                seg = "0" + secondes;
            } else {
                seg = secondes.toString();
            }

            if (miles < 10) {
                mil = "00" + miles;
            } else if (miles < 100) {
                mil = "0" + miles;
            } else {
                mil = miles.toString();
            }
            lTimer.setText(min + ":" + seg + ":" + mil);
            jeux.setTimer(new Timer (minutes, secondes, miles));
        }
    }

@Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(PATH_FICHIER_JEUX);
        int reponse;
        String fichierChoisi, fichierDestination;
        Object source = e.getSource();
        
        if (source instanceof JMenuItem) {
            JMenuItem jmi = (JMenuItem) source;
            if(jmi.getActionCommand().equals(NOM_OUVRIR)){
                reponse = jfc.showOpenDialog(null);
                if (reponse == JFileChooser.APPROVE_OPTION) {
                    fichierChoisi = jfc.getSelectedFile().getPath();
                    JOptionPane.showMessageDialog(null, "Vous avez choisi le fichier [" + fichierChoisi + "]");
                    jeux = DeSerializacion(fichierChoisi); 
                    this.dispose();
                    new PDemineur(jeux);
                    System.out.println("On va aller continuer a jouer!!");
                }
            } 
            else if(jmi.getActionCommand().equals(NOM_ENREGISTRER)){
                    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_NOM_FICHIER);
                    jfc.setAcceptAllFileFilterUsed(false);
                    String str = jeux.getJoueur().getNom().replaceAll(" ","_");
                    String nomFichier = str + sdf.format(new GregorianCalendar().getTime()).concat(EXTENTION_JEUX);
                    jfc.setSelectedFile(new File(PATH_FICHIER_JEUX.concat(nomFichier)));      
                    fichierDestination = jfc.getSelectedFile().getAbsolutePath(); 
                    JOptionPane.showMessageDialog(null, "Fichier destination [" + fichierDestination + "]");
                    Serializacion(fichierDestination, jeux);
            } 
            else if(jmi.getActionCommand().equals(NOM_SORTIR)){
                reponse = JOptionPane.showConfirmDialog(this, "Voulez-vous vraiment quitter?", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reponse == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } 
            else if(jmi.getActionCommand().equals(NOM_NOUVEAU)){
                this.setVisible(false);
                new PParametres();
            }
        } 
        else if(source instanceof JButton){
            JButton btn = (JButton)source;
            if(btn.getActionCommand().equals(NOM_PARTIR)){
               th = new Thread(this); 
               if (th.isInterrupted()){
                  //CREER UNE METHODE QUE RECONTINUE LE TIMER
                   //th.REstart();                  
                   th.run();
               }
               else
                    th.start();            
            }
            else if(btn.getActionCommand().equals(NOM_ARRETER)){
                th.interrupt();  
            }
        }
 
    }
}
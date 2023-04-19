package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

//import org.w3c.dom.events.MouseEvent;

import modele.Simulation;
import modele.environnement.Case;
import modele.environnement.CaseCultivable;
import modele.environnement.CaseNonCultivable;
import modele.environnement.plantes.Plante;
import modele.environnement.plantes.Varietes;
import modele.item.outils.Instrument;

public class Vue extends JFrame implements Runnable
{
    private int sizeX = 20;
    private int sizeY = 10;

    // icones affichées dans la grille
    private ImageIcon icoSalade;
    private ImageIcon icoCoco;
    private ImageIcon icoOrange;
    private ImageIcon icoRaisin;
    private ImageIcon icoTomate;
    private ImageIcon icoCarotte;
    private ImageIcon icoCourgette;
    private ImageIcon icoPatate;
    private ImageIcon icoVide;
    private ImageIcon icoPickaxe;
    private ImageIcon icoHoe;
    private ImageIcon icoShovel;
    private ImageIcon icoGraines;
    private ImageIcon icoOutils;
    private ImageIcon icoMarket;
    private ImageIcon icoInventaire;
    private ImageIcon top_down_fence_ico;
    private ImageIcon side_fence_ico;
    private ImageIcon top_left_fence_ico;
    private ImageIcon bottom_right_fence_ico;
    private ImageIcon bottom_left_fence_ico;
    private ImageIcon top_right_fence_ico;
    private ImageIcon terre_cultivable_ico;
    private ImageIcon terre_non_cultivable_ico;

    private JLabel[][] tabJLabel;

    private Simulation simulation;

    
    public Vue(Simulation simul) throws UnsupportedLookAndFeelException
    {
        simulation = simul;
        sizeX = simulation.get_size_x();
        sizeY = simulation.get_size_y();

        chargerLesIcones();
        placerLesComposantsGraphiques();
        new Thread(this).start();
    }

    private void chargerLesIcones() {
    	// image libre de droits utilisée pour les légumes : https://www.vecteezy.com/vector-art/2559196-bundle-of-fruits-and-vegetables-icons	
    

        icoSalade = chargerIcone("Images/data.png", 0, 0, 120, 120);//chargerIcone("Images/Pacman.png");
        icoCoco = chargerIcone("Images/data.png", 0, 3 * 390, 140, 140);
        icoOrange = chargerIcone("Images/data.png", 2 * 390, 0, 140, 140);
        icoRaisin = chargerIcone("Images/data.png", 9 * 390, 2 * 390, 140, 140);
        icoTomate = chargerIcone("Images/data.png", 4 * 390, 390, 140, 140);
        icoCarotte = chargerIcone("Images/data.png", 390, 390, 140, 140);
        icoCourgette = chargerIcone("Images/data.png", 0, 390, 140, 140);
        icoPatate = chargerIcone("Images/data.png", 0, 4 * 390, 140, 140);
        icoPickaxe = chargerIcone("Images/Pickaxe.png");
        icoHoe = chargerIcone("Images/Hoe.png");
        icoShovel = chargerIcone("Images/Shovel.png");
        icoGraines = chargerIcone("Images/Graines.png");
        icoOutils = chargerIcone("Images/Outils.png");
        icoVide = chargerIcone("Images/Vide.png");
        icoMarket = chargerIcone("Images/Market.png");
        icoInventaire = chargerIcone("Images/Inventaire.png");
        top_down_fence_ico = chargerIcone("Images/top_down_fence.png");
        side_fence_ico = chargerIcone("Images/data.png");
        top_left_fence_ico = chargerIcone("Images/top_left_fence.png");
        bottom_right_fence_ico = chargerIcone("Images/bottom_right_fence.png");
        bottom_left_fence_ico = chargerIcone("Images/bottom_left_fence.png");
        top_right_fence_ico = chargerIcone("Images/top_right_fence.png");
        terre_cultivable_ico = chargerIcone("Images/CaseCultivable.png");
        terre_non_cultivable_ico = chargerIcone("Images/CaseNonCultivable.png");

    }


    private void placerLesComposantsGraphiques() throws UnsupportedLookAndFeelException {
    	//Applique un theme
    	UIManager.setLookAndFeel(new NimbusLookAndFeel());
    	
        setTitle("Happy Farm");
        setSize(800, 600);
        setLocationRelativeTo(null); // centrer la fenêtre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // permet de terminer l'application à la fermeture de la fenêtre

        JPanel infos = new JPanel();
        infos.setLayout(new BoxLayout(infos, BoxLayout.Y_AXIS));
        add(infos, BorderLayout.EAST);
 
        
        JComponent grilleJLabels = new JPanel(new GridLayout(sizeY, sizeX)); // grilleJLabels va contenir les cases graphiques et les positionner sous la forme d'une grille
        tabJLabel = new JLabel[sizeX][sizeY];
        int labelWidth = grilleJLabels.getWidth() / tabJLabel.length;
        int labelHeight = grilleJLabels.getHeight() / tabJLabel[0].length;

        
        for (int y = 0; y < sizeY; y++) 
        {
            for (int x = 0; x < sizeX; x++) 
            {
                JLabel jlab = new JLabel();
                tabJLabel[x][y] = jlab; // on conserve les cases graphiques dans tabJLabel pour avoir un accès pratique à celles-ci (voir mettreAJourAffichage() )
                tabJLabel[x][y].setPreferredSize(new Dimension(labelWidth, labelHeight));;     
                grilleJLabels.add(jlab);
            }
        }
        
        
        add(grilleJLabels, BorderLayout.CENTER);
        JPanel menuPlanteOutil = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        menuPlanteOutil.add(placerMenuDeroulantOutils(),BorderLayout.NORTH);
        menuPlanteOutil.add(placerMenuDeroulantGraines(),BorderLayout.NORTH);
        infos.add(menuPlanteOutil);
        infos.add(panelInventaire(), BorderLayout.SOUTH);
        

        // écouter les évènements
        for (int y = 0; y < sizeY; y++) 
        {
            for (int x = 0; x < sizeX; x++) 
            {	
	            final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
	            final int yy = y;
	            tabJLabel[x][y].addMouseListener(new MouseAdapter() 
	            {
		            @Override
		            public void mouseClicked(MouseEvent e)
		            	{
		            		simulation.actionUtilisateur(xx, yy);
		            	}
            	});
            }
        }

    }
    private JPanel panelInventaire() {

        // Panel des boutons
        JPanel panelBoutton = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton inv = new JButton("Inventaire", icoInventaire);
        JButton mark = new JButton("Boutique", icoMarket);
        panelBoutton.add(inv);
        panelBoutton.add(mark);

        // Panel qui contient les deux autres d'info
        JPanel panelInfo = new JPanel(new CardLayout());

        // Panel a afficher quand on appuie sur inventaire
        JPanel panelInfoInventaire = new JPanel();
        panelInfoInventaire.add(new JLabel("panelInfoInventaire"));

        // Panel a afficher quand on appuie sur market
        JPanel panelInfoMarket = new JPanel();
        panelInfoMarket.add(new JLabel("panelInfoMarket"));

        // Ajouter les JPanel a la zone d'affichage
        panelInfo.add(panelInfoInventaire, "panelInfoInventaire");
        panelInfo.add(panelInfoMarket, "panelInfoMarket");

        // Panel principal qui contient les deux panels précédents
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(panelBoutton, BorderLayout.NORTH);
        containerPanel.add(panelInfo, BorderLayout.CENTER);

        inv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Afficher le panel d'inventaire
                ((CardLayout) panelInfo.getLayout()).show(panelInfo, "panelInfoInventaire");
                
                //System.out.println(market.);
            }
        });

        mark.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Afficher le panel de la boutique
                ((CardLayout) panelInfo.getLayout()).show(panelInfo, "panelInfoMarket");
                System.out.println("Bouton Boutique cliqué");
            }
        });

        return containerPanel;
    }

    private JComboBox<Varietes> placerMenuDeroulantGraines()
    {
    	 // Créer un Map qui associe chaque élément de menu à son icône correspondante
        Map<Varietes, ImageIcon> iconMap = new HashMap<>();
        iconMap.put(Varietes.GRAINES, icoGraines);
        iconMap.put(Varietes.SALADE, icoSalade);
        iconMap.put(Varietes.CAROTTE, icoCarotte);
        iconMap.put(Varietes.ORANGE, icoOrange);
        iconMap.put(Varietes.COCO, icoCoco);
        iconMap.put(Varietes.COURGETTE, icoCourgette);
        iconMap.put(Varietes.PATATE, icoPatate);
        iconMap.put(Varietes.RAISIN, icoRaisin);
        iconMap.put(Varietes.TOMATE, icoTomate);

        
        // Créer le menu déroulant
        JComboBox<Varietes> comboBox = new JComboBox<>();
        comboBox.addItem(Varietes.GRAINES);
        comboBox.addItem(Varietes.SALADE);
        comboBox.addItem(Varietes.CAROTTE);
        comboBox.addItem(Varietes.ORANGE);
        comboBox.addItem(Varietes.COCO);
        comboBox.addItem(Varietes.COURGETTE);
        comboBox.addItem(Varietes.PATATE);
        comboBox.addItem(Varietes.RAISIN);
        comboBox.addItem(Varietes.TOMATE);
      
        
     // Définir un renderer personnalisé pour afficher l'icône correspondante à chaque élément
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Varietes) 
                {
                    JLabel label = (JLabel) renderer;
                    ImageIcon icon = iconMap.get((Varietes) value);
                    label.setIcon(icon);
                    label.setHorizontalTextPosition(JLabel.RIGHT); // aligner le texte à droite de l'icône
                }
                return renderer;
            }
        });

        // Ajouter un écouteur d'événements pour récupérer l'élément sélectionné dans le menu déroulant
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Varietes var = (Varietes) comboBox.getSelectedItem();
            	if (var != null) {simulation.setChoixPlante(var);}
            	else {return;}
            	//System.out.println("Element sélectionné : " + simulateurPotager.getChoixPlante());
            }
        });
        
        return comboBox;
    }

    //Fonction renvoyant le menu deroulant des outils qui va etre appeler dans placer les éléments graphiques
    private JComboBox<Instrument> placerMenuDeroulantOutils()
    {

    	
    	  //Création du menu déroulant pour les outils
        // Créer un Map qui associe chaque élément de menu à son icône correspondante
           Map<Instrument, ImageIcon> iconMap1 = new HashMap<>();
           iconMap1.put(Instrument.OUTILS, icoOutils);
           iconMap1.put(Instrument.HOE, icoHoe);
           iconMap1.put(Instrument.PICKAXE, icoPickaxe);
           iconMap1.put(Instrument.SHOVEL, icoShovel);

           // Créer le menu déroulant
           JComboBox<Instrument> comboBox1 = new JComboBox<>();
           comboBox1.addItem(Instrument.OUTILS);
           comboBox1.addItem(Instrument.HOE);
           comboBox1.addItem(Instrument.PICKAXE);
           comboBox1.addItem(Instrument.SHOVEL);

        // Définir un renderer personnalisé pour afficher l'icône correspondante à chaque élément
           comboBox1.setRenderer(new DefaultListCellRenderer() {
               @Override
               public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                   Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                   if (renderer instanceof JLabel && value instanceof Instrument) 
                   {
                       JLabel label = (JLabel) renderer;
                       ImageIcon icon = iconMap1.get((Instrument) value);
                       label.setIcon(icon);
                       label.setHorizontalTextPosition(JLabel.RIGHT); // aligner le texte à droite de l'icône
                   }
                   return renderer;
               }
           });
           
           
           
        // Ajouter un écouteur d'événements pour récupérer l'élément sélectionné dans le menu déroulant
           comboBox1.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
            	   Instrument inst = (Instrument) comboBox1.getSelectedItem();
	               if (inst != null) {simulation.setChoixOutil(inst);}
	               else {return;}
                   //System.out.println("Element sélectionné : " + simulateurPotager.getChoixOutil());
               }
           });
           
           
           
           // écouter les évènements
           for (int y = 0; y < sizeY; y++) 
           {
               for (int x = 0; x < sizeX; x++) 
               {	
   	            final int xx = x; // constantes utiles au fonctionnement de la classe anonyme
   	            final int yy = y;
   	            tabJLabel[x][y].addMouseListener(new MouseAdapter() 
   	            {
   		            @Override
   		            public void mouseClicked(MouseEvent e)
   		            	{
   		            		simulation.utiliserOutil(xx, yy);
   		            	}
               	});
               }
           }

           
           return comboBox1;
    }
    /**
     * Il y a une grille du côté du modèle ( jeu.getGrille() ) et une grille du côté de la vue (tabJLabel)
     */
    private void mettreAJourAffichage(){
        Case[][] temp = simulation.get_plateau();
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (temp[x][y] instanceof CaseCultivable) { // si la grille du modèle contient un Pacman, on associe l'icône Pacman du côté de la vue
		            	//System.out.print(simulateurPotager.getPlateau()[x][y].getEtatCase());

                    Plante plante = ((CaseCultivable) temp[x][y]).get_plante();
                    if (plante != null) {
                        switch (plante.getVariete()) {
                        	case GRAINES: return;
                            case SALADE: tabJLabel[x][y].setIcon(icoSalade); break;
							case CAROTTE: tabJLabel[x][y].setIcon(icoCarotte); break;
							case COCO: tabJLabel[x][y].setIcon(icoCoco); break;
							case COURGETTE: tabJLabel[x][y].setIcon(icoCourgette); break;
							case ORANGE: tabJLabel[x][y].setIcon(icoOrange); break;
							case PATATE: tabJLabel[x][y].setIcon(icoPatate); break;
							case RAISIN: tabJLabel[x][y].setIcon(icoRaisin); break;
							case TOMATE: tabJLabel[x][y].setIcon(icoTomate); break;
						default:
							break;

                        }

                    } else {
                        tabJLabel[x][y].setIcon(terre_cultivable_ico);

                    }

                    // si transparence : images avec canal alpha + dessins manuels (voir ci-dessous + créer composant qui redéfinie paint(Graphics g)), se documenter
                    //BufferedImage bi = getImage("Images/smick.png", 0, 0, 20, 20);
                    //tabJLabel[x][y].getGraphics().drawImage(bi, 0, 0, null);
                } else if (temp[x][y] instanceof CaseNonCultivable) {
                    tabJLabel[x][y].setIcon(terre_non_cultivable_ico);
                } else {

                    tabJLabel[x][y].setIcon(icoVide);
                }
            }
        }
    }

    private ImageIcon chargerIcone(String urlIcone) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(urlIcone));
        } catch (IOException ex) {
            Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


        return new ImageIcon(image);
    }

    private ImageIcon chargerIcone(String urlIcone, int x, int y, int w, int h) {
        // charger une sous partie de l'image à partir de ses coordonnées dans urlIcone
        BufferedImage bi = getSubImage(urlIcone, x, y, w, h);
        // adapter la taille de l'image a la taille du composant (ici : 20x20)
        return new ImageIcon(bi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH));
    }

    private BufferedImage getSubImage(String urlIcone, int x, int y, int w, int h) {
        BufferedImage image = null;

        try {
            File f = new File(urlIcone);
            image = ImageIO.read(f);
        } catch (IOException ex) {
            Logger.getLogger(Vue.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        BufferedImage bi = image.getSubimage(x, y, w, h);
        return bi;
    }

    




    @Override
    public void run()
    {
        while (simulation.get_is_running()) 
        {
            mettreAJourAffichage();
        }
    }


    
}

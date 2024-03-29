
package sp4_console_barois_bernard;


import java.util.Random;
import java.util.Scanner;
public class Partie {
    //Attributs:
    Joueur [] listeJoueurs =  new Joueur[2];
    Joueur JoueurCourant;
    Grille grilleJeu;
    
    //Constructeurs: 
    public Partie(Joueur Joueur1,Joueur Joueur2){
        listeJoueurs[0]=Joueur1;
        listeJoueurs[1]=Joueur2;
    }
    
    
    
    
    public void attribuerCouleursAuxJoueurs(){
        Random rand = new Random();
        int upperbound = 2;
        int int_random = rand.nextInt(upperbound);
        if (int_random==0){
            listeJoueurs[0].Couleur="Rouge";
            listeJoueurs[1].Couleur="Jaune";
        } else{
            listeJoueurs[0].Couleur="Jaune";
            listeJoueurs[1].Couleur="Rouge";
        }
    }
    public void initialiserPartie(){
        grilleJeu= new Grille ();
        Random rand = new Random();
        int maxLigne = 6;
        int maxColonne= 7;
        int random_ligne =0;
        int random_colonne=0;
        for (int i=0; i<5;i++){
           random_ligne = rand.nextInt(maxLigne);
           random_colonne= rand.nextInt(maxColonne);
           if (grilleJeu.placerTrouNoir(random_ligne,random_colonne)==false){
               i--;
           }else{
               continue;
           }
        }
        int deux_desint=0;
        while(deux_desint<2){
            random_ligne = rand.nextInt(maxLigne);
            random_colonne= rand.nextInt(maxColonne);
            if (grilleJeu.CellulesJeu[random_ligne][random_colonne].presenceTrouNoir()==true){
                grilleJeu.placerDesintegrateur(random_ligne,random_colonne);
                deux_desint++;
            }
        }
        int trois_desint=0;
        while (trois_desint<3){
            random_ligne = rand.nextInt(maxLigne);
            random_colonne= rand.nextInt(maxColonne);
            if (grilleJeu.CellulesJeu[random_ligne][random_colonne].presenceTrouNoir()==false){
                grilleJeu.placerDesintegrateur(random_ligne,random_colonne);
                trois_desint++;
            }
        }
        
        
        for (int i=0;i<21;i++){
            Jeton unJeton= new Jeton("Rouge");
            if (listeJoueurs[0].Couleur=="Rouge"){
                listeJoueurs[0].ajouterJeton(unJeton);
                listeJoueurs[0].nombreJetonsRestants++;
            }else{
                listeJoueurs[1].ajouterJeton(unJeton);
                listeJoueurs[1].nombreJetonsRestants++;
            }
        }
        for (int i=0;i<21;i++){
            Jeton deuxJeton= new Jeton("Jaune");
            if (listeJoueurs[1].Couleur=="Jaune"){
                listeJoueurs[1].ajouterJeton(deuxJeton);
                listeJoueurs[1].nombreJetonsRestants++;
                
            }else{
                listeJoueurs[0].ajouterJeton(deuxJeton);
                listeJoueurs[0].nombreJetonsRestants++;
            }
        }
        int upperbound = 2;
        int int_random = rand.nextInt(upperbound);
        if (int_random==0){
            JoueurCourant=listeJoueurs[0];
        }else{
            JoueurCourant=listeJoueurs[1];
        }
    }
    
    public void debuterPartie(){
        Joueur DernierJoueur=null;
        Scanner sc = new Scanner(System.in) ;
        int i=0;
        int j=0;
        boolean test1=false;
        boolean test2=false;
        while (test1==false&&test2==false){
            System.out.println();
            System.out.println();
            System.out.println("Au tour de " +JoueurCourant.nom+ " le Joueur de couleur "+JoueurCourant.Couleur+" !");
            System.out.println("Vous avez "+JoueurCourant.nombreJetonsRestants +" Jetons et "+JoueurCourant.nombreDesintegrateurs+" Desintegrateurs");
            System.out.println();
            grilleJeu.afficherGrilleSurConsole();
            System.out.println("Si vous voulez jouer un Jeton: Tapez 1\nSi vous voulez en récupérer: Tapez 2\nSi vous voulez jouer une Desintegrateur: Tapez 3 ");
            int Choix = sc.nextInt();
            int verif_1=1;
            
            
            //Si le Joueur choisit de récupérer un jeton
            
            if (Choix==2){
                for (int q=0;q<6;q++){
                    for (int w=0;w<7;w++){
                        if (grilleJeu.celluleOccupee(q, w)){
                            if (grilleJeu.lireCouleurDuJeton(q, w)==JoueurCourant.Couleur){
                                verif_1=0;
                                break;
                            }
                        }
                    }
                }
                if(verif_1==0){
                    for (int t=0;t<100;t++){
                        System.out.println("Quel jeton souhaitez-vous récupérer?");
                        System.out.print("Tapez la ligne:");
                        int ligne_choisie = sc.nextInt()-1;
                        System.out.print("Tapez la colonne:");
                        int colonne_choisie = sc.nextInt()-1;
                        if (grilleJeu.celluleOccupee(ligne_choisie, colonne_choisie)){
                            if(grilleJeu.lireCouleurDuJeton(ligne_choisie, colonne_choisie)==JoueurCourant.Couleur){
                                Jeton a=grilleJeu.recupererJeton(ligne_choisie, colonne_choisie);
                                JoueurCourant.ajouterJeton(a);
                                JoueurCourant.nombreJetonsRestants++;
                                grilleJeu.tasserGrille();
                                break;
                            }else{
                            System.out.println("Il n'y a pas de jeton à vous dans cette case!");
                            }
                        }
                    }
                }else{
                    System.out.println("Vous ne pouvez pas retirer un jeton car il n'y en a aucun à vous sur la grille!\n Veuillez placer un Jeton:\n");
                    Choix=1;
                }
            }
            
                
                
                
                
            
            if (Choix==1){
                System.out.println("Dans quelle colonne voulez-vous placer votre Jeton?") ;
                int numCol=0;
                int testBug=1;
                while (testBug!=0){
                    testBug=0;
                    try{
                        numCol=sc.nextInt();
                    }
                    catch(Exception e){
                        testBug=1;
                        System.out.println("erreur: Rentrez une colonne entre 1 et 7");
                        sc.reset();
                        sc.next();
                    }
                }
                int numColonne ;
                numColonne=numCol-1 ;
                if (JoueurCourant.Couleur=="Rouge"){
                    grilleJeu.ajouterJetonDansColonne(JoueurCourant.listeJetons[i],numColonne);
                    JoueurCourant.listeJetons[i]=null;
                    JoueurCourant.nombreJetonsRestants--;
                    i++;
                    for (int z=0;z<6;z++){
                        if (grilleJeu.CellulesJeu[z][numColonne].presenceTrouNoir() && grilleJeu.celluleOccupee(z,numColonne)){
                            grilleJeu.CellulesJeu[z][numColonne].activerTrouNoir();
                            if (grilleJeu.CellulesJeu[z][numColonne].presenceDesintegrateur()){
                                grilleJeu.CellulesJeu[z][numColonne].recupererDesintegrateur();
                                JoueurCourant.obtenirDesintegrateur();
                                System.out.println("Vous avez maintenant "+JoueurCourant.nombreDesintegrateurs+" Desintégrateurs!");
                            }
                            break;
                        }
                    }
                    for (int z=0;z<6;z++){
                        if (grilleJeu.CellulesJeu[z][numColonne].presenceDesintegrateur() && grilleJeu.celluleOccupee(z,numColonne)){
                            grilleJeu.CellulesJeu[z][numColonne].recupererDesintegrateur();
                            JoueurCourant.obtenirDesintegrateur();
                            System.out.println("Vous avez maintenant "+JoueurCourant.nombreDesintegrateurs+" Desintégrateurs!");
                            break;
                        }
                    }
                }
                if (JoueurCourant.Couleur=="Jaune"){
                    grilleJeu.ajouterJetonDansColonne(JoueurCourant.listeJetons[j],numColonne);
                    JoueurCourant.listeJetons[j]=null;
                    JoueurCourant.nombreJetonsRestants--;
                    j++;
                    for (int y=0;y<6;y++){
                        if (grilleJeu.CellulesJeu[y][numColonne].presenceTrouNoir() && grilleJeu.celluleOccupee(y,numColonne)){
                            grilleJeu.CellulesJeu[y][numColonne].activerTrouNoir();
                            if (grilleJeu.CellulesJeu[y][numColonne].presenceDesintegrateur()){
                                grilleJeu.CellulesJeu[y][numColonne].recupererDesintegrateur();
                                JoueurCourant.obtenirDesintegrateur();
                                System.out.println("Vous avez maintenant "+JoueurCourant.nombreDesintegrateurs+" Desintégrateurs!");
                            }
                            break;
                        }
                    }
                    for (int y=0;y<6;y++){
                        if (grilleJeu.CellulesJeu[y][numColonne].presenceDesintegrateur() && grilleJeu.celluleOccupee(y,numColonne)){
                            grilleJeu.CellulesJeu[y][numColonne].recupererDesintegrateur();
                            JoueurCourant.obtenirDesintegrateur();
                            System.out.println("Vous avez maintenant "+JoueurCourant.nombreDesintegrateurs+" Desintégrateurs!");
                            break;
                        }
                    }
                }
            }
            
            
            
            
            if (Choix==3){
                for (int q=0;q<6;q++){
                    for (int w=0;w<7;w++){
                        if (grilleJeu.celluleOccupee(q, w)){
                                verif_1=0;
                                break;
                        }
                    }
                }
                if(verif_1==0){
                    if(JoueurCourant.nombreDesintegrateurs!=0){
                        for (int t=0;t<100;t++){
                            System.out.println("Dans quelle case souhaitez vous jouer votre désintigrateur?");
                            System.out.print("Tapez la ligne:");
                            int ligne_choisie = sc.nextInt()-1;
                            System.out.print("Tapez la colonne:");
                            int colonne_choisie = sc.nextInt()-1;
                            if (grilleJeu.celluleOccupee(ligne_choisie, colonne_choisie)){
                                if(grilleJeu.lireCouleurDuJeton(ligne_choisie, colonne_choisie)!=JoueurCourant.Couleur){
                                    grilleJeu.recupererJeton(ligne_choisie, colonne_choisie);
                                    grilleJeu.tasserGrille();
                                    JoueurCourant.nombreDesintegrateurs--;
                                    break;
                                
                                }else{
                                    System.out.println("Il n'y a pas de jeton ennemi dans cette case!");
                                }
                            }
                        }
                    }
                } else{
                    System.out.println("Vous ne pouvez pas désintégrer un jeton car il n'y en a pas sur la grille!\n Veuillez placer un Jeton:\n");
                    Choix=1;
                }

            }
                
            
                
            test1=grilleJeu.etreGagnantePourJoueur(listeJoueurs[0]);  
            test2=grilleJeu.etreGagnantePourJoueur(listeJoueurs[1]); 
            DernierJoueur=JoueurCourant;
            
            if (JoueurCourant==listeJoueurs[0]){
                JoueurCourant=listeJoueurs[1];
            }else{ JoueurCourant=listeJoueurs[0];}
            
        
        }
        JoueurCourant=DernierJoueur;
        if (test1==true&&test2==true){
            JoueurCourant.Couleur="Noir";
        }else if(test1==true){
            JoueurCourant.Couleur=listeJoueurs[0].Couleur;
        }else if(test2==true){
            JoueurCourant.Couleur=listeJoueurs[1].Couleur;
        }
    }
}



  


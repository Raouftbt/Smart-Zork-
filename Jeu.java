package zork;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure très rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se déplacer parmi les différentes pieces.
 *  Ce jeu nécessite vraiment d'etre enrichi pour devenir intéressant!</p> <p>
 *
 *  Pour jouer a ce jeu, créer une instance de cette classe et appeler sa
 *  méthode "jouer". </p> <p>
 *
 *  Cette classe crée et initialise des instances de toutes les autres classes:
 *  elle crée toutes les pieces, crée l'analyseur syntaxique et démarre le jeu.
 *  Elle se charge aussi d'exécuter les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 * 
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.2
 * @since      March 2000
 */

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private Joueur joueur;
	private ArrayList<Piece> pieces;
	private ArrayList<Animal> animauxV;
	private boolean actionFaite = false ;

	/**
	 *  Crée le jeu et initialise la carte du jeu (i.e. les pièces).
	 */
	public Jeu() {
		this.pieces=new ArrayList <Piece>();
		this.animauxV=new ArrayList <Animal>();
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
		this.joueur=new Joueur("Tabti", pieceCourante);
	}


	/**
	 *  Crée toutes les pieces et relie leurs sorties les unes aux autres.
	 */
	public void creerPieces() {
		Piece dehors;
		Piece salleTD;
		Piece taverne;
		Piece batimentC;
		Piece burreau;
		Piece salle_x;

		// création des pieces
		dehors = new Piece("devant le batiment C");
		salleTD = new Piece("une salle de TD dans le batiment G");
		taverne = new Piece("la taverne");
		batimentC = new Piece("le batiment C");
		burreau = new Piece("le secrétariat");
		salle_x= new Piece("X");

		// création des objets
		salleTD.ajouter(new Objet("chaise", 4));
		salleTD.ajouter(new Objet("tableau", 15));
		salleTD.ajouter(new Objet("retroprojecteur", 10));
		salleTD.ajouter(new Objet("table", 12));
		salleTD.ajouter(new Objet("livre", 1));
		salleTD.ajouter(new Objet("sac", 3));

		burreau.ajouter(new Objet("table", 30));
		burreau.ajouter(new Objet("fauteuil", 7));
		burreau.ajouter(new Objet("refrigirateur", 40));
		burreau.ajouter(new Objet("ordinateur", 12));
		burreau.ajouter(new Objet("bouteil_eau", 2));
		burreau.ajouter(new Objet("regle", 1));
		burreau.ajouter(new Objet("pote_manteau", 8));
		burreau.ajouter(new Objet("poubelle", 5));

		batimentC.ajouter(new Objet("extincteur",7));
		batimentC.ajouter(new Objet("plante",25));
		batimentC.ajouter(new Objet("pile_de_livres",15));
		batimentC.ajouter(new Objet("tableau",9));
		batimentC.ajouter(new Objet("secritaire",49));

		taverne.ajouter(new Objet("machine_cafe", 100));
		taverne.ajouter(new Objet("distributeure", 70));
		taverne.ajouter(new Objet("caisse", 40));
		taverne.ajouter(new Objet("epinards", 0));

		// initialise les sorties des pieces
		dehors.setSorties(null, salleTD, batimentC, taverne);
		salleTD.setSorties(null, null, null, dehors);
		taverne.setSorties(null, dehors, null, null);
		batimentC.setSorties(dehors, burreau, null, salle_x);
		burreau.setSorties(null, null, null, batimentC);
		salle_x.setSorties(null,batimentC,null,null);

		// creation des chiens  
		
		dehors.ajouterChien(new Chien(dehors, 20, "chien1",25,RaceChien.Berger));
		dehors.ajouterChien(new Chien(dehors, 15, "chien2",30,RaceChien.Staff));
		dehors.ajouterChien(new Chien(dehors, 25, "chien3",20,RaceChien.Bulldog));

		dehors.ajouterChien(new Loup(dehors, 25, "loup1",20,RaceLoup.Gris));
		salleTD.ajouterChien(new Loup(salleTD, 25, "loup2",20,RaceLoup.Arabe));
									
		dehors.ajouterChien(new Chevre(dehors, 25, "chevre1",20,15,TypeChevre.Alpine));
		salleTD.ajouterChien(new Chevre(salleTD, 25, "chevre2",20,15,TypeChevre.Rove));
		batimentC.ajouterChien(new Chevre(batimentC, 25, "chevre3",20,15,TypeChevre.Corse));

		salleTD.ajouterAliment(new Vegetale("herbe",20,15));
		salleTD.ajouterAliment(new Viande("poisson",20,15));

		batimentC.ajouterAliment(new Vegetale("tomate",20,15));
		batimentC.ajouterAliment(new Viande("saucisse",20,15));
		dehors.ajouterAliment(new Viande("lapin",20,15));

		

		// le jeu commence dehors
		pieceCourante = dehors;

		this.pieces.add(dehors);
		this.pieces.add(salleTD);
		this.pieces.add(taverne);
		this.pieces.add(batimentC);
		this.pieces.add(burreau);
		this.pieces.add(salle_x);
	}



	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();

		// Entrée dans la boucle principale du jeu. Cette boucle lit
		// et exécute les commandes entrées par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		// et afficher si le joueur a gagnée ou non avec son score 
		boolean termine = false;
		boolean m=false;
		int nbe=0;
		for(Piece p:this.pieces){	
			for(Animal anV:p.animaux){
				if(anV.estVivant()){
					this.animauxV.add(anV);
				}
			}
		}
		


		while (!termine && !m ) {
			actionFaite=false;
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);
			m=mission();
			if(this.pieceCourante.descriptionCourte().equals("devant le batiment C")){
				nbe++;
				if (commande.getMotCommande()==null) {
					nbe--;
					
				}
				else if(commande.getMotCommande().equals("adopter") || commande.getMotCommande().equals("liberer") || commande.getMotCommande().equals("saisir") || commande.getMotCommande().equals("deposer") || commande.getMotCommande().equals("ch_saisir")){
					nbe--;
				}
			}
			if (actionFaite) {
				ArrayList<Animal> animauxAsup = new ArrayList <Animal>();
				for(Animal anmAs:animauxAsup){
					this.animauxV.remove(anmAs);
				}
					
				for(Animal anmV:this.animauxV){
					AnimalClassAb an = (AnimalClassAb) anmV;

					if(!an.estVivant()){
					//this.animauxV.remove(an);
					animauxAsup.add(an);
					}

					Piece p = an.getPieceCourante();
					if(an.estVivant()){
						if(an.getClass().getSuperclass().equals(ChasseurClassAb.class)){
							ChasseurClassAb chasseur = (ChasseurClassAb) an;
							Animal chasse = chasseur.chasser(p);
						if(chasse != null){
							Loup l=(Loup) an;
							Chevre ch=(Chevre) chasse;
							System.out.println(l.getNom()+" "+"("+l.getRaceByNom()+")"+" a chassé "+ chasse.getNom()+"("+ch.getRaceByNom()+")");
						}
					}else if(an.getClass().getSuperclass().equals(AdoptableClassAb.class)){
						AdoptableClassAb adopt = (AdoptableClassAb) an;
						Aliment nourriture = adopt.manger();
						if(nourriture != null){
							Chien ch=(Chien) adopt;
							System.out.println(an.getNom()+" "+"("+ch.getRaceByNom()+")"+" a mangé "+ nourriture.getNom());
						}
					}else if(an.getClass().getSuperclass().equals(ProieClassAb.class)){
						ProieClassAb proie = (ProieClassAb) an;
						Aliment nourriture = proie.manger();
						if(nourriture != null){
							Chevre ch=(Chevre) an;
							System.out.println(an.getNom()+" "+"("+ch.getRaceByNom()+")"+" a mangé "+ nourriture.getNom());
						}
					}

					
					if(an.getClass().getSuperclass().equals(AdoptableClassAb.class)){
					
						AdoptableClassAb adopt = (AdoptableClassAb) an;
						
						if(adopt.estLibre()){
							Random random = new Random();
							
							Direction d = Direction.values()[random.nextInt(Direction.values().length)];
							if(p.pieceSuivante(d)!=null){
								adopt.proposerSortie(adopt.getPieceCourante(),d);
							}
						}
						if(adopt.deplacerDepuis(p) != null && adopt.estVivant()){
							
							
							Piece pc = adopt.getPieceCourante();
							Chien sh=(Chien) adopt;
							System.out.println(adopt.getNom()+" "+"("+sh.getRaceByNom()+")"+" s'est déplacé vers "+ pc.descriptionCourte() +"\n");
						}
					}else{
						if(an.deplacerDepuis(p) != null && an.estVivant()){
					
							AnimalClassAb anAb = (AnimalClassAb) an;
							Piece pc = anAb.getPieceCourante();

							System.out.println(an.getNom()+" s'est déplacé vers "+ pc.descriptionCourte() +"\n");
						}
					} 
				}	
			}
		}



		}
		if(m && nbe<=3){
			System.out.println("Félicitation vous avez gagner ------------score: 10/10");
		}
		else if(m && (nbe>3) && (nbe<= 5)){
			System.out.println("Félicitation vous avez gagner ------------score: 8/10");
		}
		else if((m && nbe>5) || !m){
			System.out.println("Vous avez perdu!!!!!!");
		} 
		System.out.println("Merci d'avoir jouer.  Au revoir.");
	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvennue dans le monde de Zork !");
		System.out.println("Zork est un nouveau jeu d'aventure, terriblement enuyeux.");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println("Votre mission est de chercher exactement 3 objets dont le poids totale est 96 kg ");
		System.out.println("et d'essayer de faire ça avec le moindre déplacement possible.\n c'est parti!!!!!!!!! ");
		
		System.out.println(pieceCourante.descriptionLongue());

		System.out.println();
	}


	/**
	 *  Exécute la commande spécifiée. Si cette commande termine le jeu, la valeur
	 *  true est renvoyée, sinon false est renvoyée
	 *
	 * @param  commande  La commande a exécuter
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			//return false;
		}

		String motCommande = commande.getMotCommande();
		if (commande.getMotCommande()==null) {
			System.out.println("la commande doit etre : aller , quitter , aide ,saisir , deposer , adopter , chsaisir ,liberer");
			
		}
		else{
		if (motCommande.equals("aide")) {
			afficherAide();
		} else if (motCommande.equals("aller")) {
			deplacerVersAutrePiece(commande);

		} else if (motCommande.equals("saisir")){
			if (commande.aSecondMot()) {
				if (this.pieceCourante.listObjString().contains(commande.getSecondMot())){
					Objet obj=this.pieceCourante.getObjetByNom(commande.getSecondMot());
					joueur.saisir(obj);
					actionFaite=true;
				}else{ 
					System.out.println("cette piece ne contient pas cet objet !");
				}
				System.out.println(this.pieceCourante.afficherObjetsPiece());
			}else{
				System.out.println("saisir quoi ?");
			}
			
		} else if (motCommande.equals("ch_saisir")){
			if (commande.aSecondMot()) {
				if (this.pieceCourante.listObjString().contains(commande.getSecondMot())){
					Objet obj=this.pieceCourante.getObjetByNom(commande.getSecondMot());
					Scanner sc = new Scanner(System.in);
					System.out.println("Lequel de vos chiens va porter l'objet? ");
					String str = sc.nextLine();
					for(Chien ch:joueur.getChiens()){
						if(ch.getNom().equals(str)){
							ch.saisir(obj);
							actionFaite=true;
						}
					}
					
				}else{
					System.out.println("cette piece ne contient pas cet objet !");
				}
				System.out.println(this.pieceCourante.afficherObjetsPiece());
			}else{
				System.out.println("saisir quoi ?");
			}
			
		}else if (motCommande.equals("deposer")){
			if (commande.aSecondMot()) {
				if (this.joueur.listObjString().contains(commande.getSecondMot())){
					Objet obj=this.joueur.getObjetByNom(commande.getSecondMot());
					joueur.deposer(obj);
					actionFaite=true;
				}
				else{
					for(int i=0;i<this.joueur.getChiens().size();i++){
						if(this.joueur.getChiens().get(i).listObjString().contains(commande.getSecondMot())){
							Objet obj=this.joueur.getChiens().get(i).getObjetByNom(commande.getSecondMot());
							joueur.getChiens().get(i).deposer(obj);
							actionFaite=true;
						}
					}	
					System.out.println("vous n'avez pas cet objet !");
				}
				System.out.println(this.pieceCourante.afficherObjetsPiece());
			}else{
				System.out.println("deposer quoi ?");
			}

		}else if (motCommande.equals("adopter")){ 
			if (commande.aSecondMot()) {
				if (this.pieceCourante.listChienString().contains(commande.getSecondMot())){
					Chien ch=this.pieceCourante.getChienByNom(commande.getSecondMot());
					Scanner sc = new Scanner(System.in);
					System.out.println("Veuillez saisir un nom pour votre chien :");
					String str = sc.nextLine();
					joueur.adopter(ch,str);
					actionFaite=true;
				}else{
					System.out.println("Ce chien n'existe pas !");
				}
				
			}else{
				System.out.println("adopter quoi ?");
			}

		}else if (motCommande.equals("liberer")){ 
			if (commande.aSecondMot()) {
				if (this.joueur.listChiensString().contains(commande.getSecondMot())){
					joueur.liberer(commande.getSecondMot());
				}else{
					System.out.println("Ce chien n'existe pas !");
				}
				
			}else{
				System.out.println("liberer quoi ?");
			}

		}else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()) {
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		}
		}
		return false;
	}


	// implementations des commandes utilisateur:

	/**
	 *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes perdu. Vous etes seul. Vous errez");
		System.out.println("sur le campus de l'Université Paris 13.");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}


	/**
	 *  Tente d'aller dans la direction spécifiée par la commande. Si la piece
	 *  courante possède une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.
	 *
	 * @param  commande  Commande dont le second mot spécifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller où ?");
			return;
		}

		String direction = commande.getSecondMot();
		Direction d = null;
		try {
		    d = Direction.valueOf(direction);
		  } catch (IllegalArgumentException e) {
		      System.out.println("Pour indiquer une direction entrez une des chaînes de caractères suivantes:");
		      for (Direction dok : Direction.values()) {
		          System.out.println("-> \"" + dok + "\"");
		      }
		      return;
		  }

		// Tentative d'aller dans la direction indiquée.
		Piece pieceSuivante = pieceCourante.pieceSuivante(d);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} else {
			pieceCourante = pieceSuivante;
			this.joueur.pieceCourante = pieceSuivante;
			for(Chien ch:this.joueur.getChiens()){
				//ch.pieceCourante = pieceSuivante;
				ch.proposerSortie(ch.getPieceCourante(), d);
			}
			System.out.println(pieceCourante.descriptionLongue());
			if(this.joueur.getChiens().size()!=0){
				System.out.println("vous etes acompagné par:");
				for(String nomChien:this.joueur.listChiensString()){
					System.out.println(nomChien);
					System.out.println();
				}
			}
			System.out.println(pieceCourante.afficherObjetsPiece());

		}
	}

 /** 
    *la methode mession ne prend pas d'argument.
    * et permet de retourner un boolean.
    *
	* @requires poidsVictoire>0 ;
	* @requises nbObjVictoire>0
	*
    * @return true ou false; 
    */ 
    // @pure
	public boolean mission(){
		int poidsVictoire=96;
		int nbObjVictoire=3;
		if(pieceCourante.descriptionCourte().equals("devant le batiment C")){
			if(pieceCourante.getPoidsTotal()==poidsVictoire && pieceCourante.objets.size()==nbObjVictoire){
				return true;
			}
		}
		return false;
	}
}


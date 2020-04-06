package zork;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se déroule l'action du
 *  jeu. Elle est reliée a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont étiquettées "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possède une référence sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.2
 * @since      August 2000
 */

public class Piece {
 	private String description;
	// mémorise les sorties de cette piece.
	private Map<Direction, Piece> sorties;
	public ArrayList<Objet> objets;
	//public ArrayList<Chien> chiens;
	public ArrayList<Animal> animaux;
	public ArrayList<Aliment> aliments;




	/**
	 *  Initialise une piece décrite par la chaine de caractères spécifiée.
	 *  Initialement, cette piece ne possède aucune sortie. La description fournie
	 *  est une courte phrase comme "la bibliothèque" ou "la salle de TP".
	 *
	 * @param  description  Description de la piece.
	 */
	public Piece(String description) {
		this.description = description;
		sorties = new HashMap<Direction, Piece>();
		this.objets=new ArrayList <Objet>();
		this.animaux=new ArrayList <Animal>();
		this.aliments=new ArrayList <Aliment>();
	}


	/**
	 *  Définie les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put(Direction.NORD, nord);
		}
		if (est != null) {
			sorties.put(Direction.EST, est);
		}
		if (sud != null) {
			sorties.put(Direction.SUD, sud);
		}
		if (ouest != null) {
			sorties.put(Direction.OUEST, ouest);
		}
	}


	/**
	 *  Renvoie la description de cette piece (i.e. la description spécifiée lors
	 *  de la création de cette instance).
	 *
	 * @return    Description de cette piece
	 */
	public String descriptionCourte() {
		return description;
	}

	public Map<Direction, Piece> getSorties(){
		return this.sorties;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement formatée pour affichage, de la forme: <pre>
	 *  Vous etes dans la bibliothèque.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caractères
	 *  renvoyée par la méthode descriptionSorties pour décrire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties()+"\n" +afficherChienPiece();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilisée dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette pièce.
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		for (Direction sortie :  sorties.keySet()) {
			resulString += " " + sortie;
		}
		return resulString;
	}


	/**
	 *  Renvoie la piece atteinte lorsque l'on se déplace a partir de cette piece
	 *  dans la direction spécifiée. Si cette piece ne possède aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se déplacer
	 * @return            Piece atteinte lorsque l'on se déplace dans la direction
	 *      spécifiée ou null.
	 */
	public Piece pieceSuivante(Direction d) {
		return sorties.get(d);
	}


	/**
	 *  Cette methode permet d'ajouter un objet a une piece.
	 * cette methode ne renvoie rien.
	 *
	 * @param  Objet l'objet q'on veut ajouter a la piece 
	 *  
	 */
	public void ajouter(Objet obj){
        this.objets.add(obj);
    }


	/**
	 *  Cette methode permet d'ajouter un chien a une piece.
	 * cette methode ne renvoie rien.
	 *
	 * @param  Chien le chien qu'on veut ajouter a la piece
	 *  
	 */
	public void ajouterChien(Animal anml){
        this.animaux.add(anml);
    }



	public void ajouterAliment(Aliment al){
        this.aliments.add(al);
    }

	// cette methode permet d'afficher les objet de la piece
	public String afficherObjetsPiece(){
		String ligne ="Cette piece contient :\n";
		for(Objet oz : this.objets){
			 ligne = ligne + oz.getNom() + ": " +oz.getPoids()+" kg.\n";
		}
		ligne += "\n Aliments: \n";
		for(Aliment al : this.aliments){
			 ligne = ligne + al.getNom() + ": " +al.getValeurNutritive()+" .\n";
		}

		return ligne;
	}

	// afficher les chiens de la piece 
	public String afficherChienPiece(){
		String ligne ="";
		if (this.description.equals("devant le batiment C")){
			ligne ="Cette piece contient :\n";
			for(Animal an : this.animaux){
				if(an.getClass().getSuperclass().equals(AdoptableClassAb.class)){
					Chien ch=(Chien) an;
					ligne = ligne + ch.getNom()+ " de race :"+ ch.getRaceByNom() +"\n";
				}
				if(an.getClass().getSuperclass().equals(ProieClassAb.class)){
					Chevre ch=(Chevre) an;
					ligne = ligne + ch.getNom()+ " de race :"+ ch.getRaceByNom() +"\n";
				}
				if(an.getClass().getSuperclass().equals(ChasseurClassAb.class)){
					Loup ch=(Loup) an;
					ligne = ligne + ch.getNom()+ " de race :"+ ch.getRaceByNom() +"\n";
				}
			
			}
		}
		return ligne;
	}

/** 
    *la methode getObjetByNom prendre en argument un string.
    * et permet de retourner un objet qui se trouve dans
    * la liste des objets qui sont dans la piece.
    *
    * @param nom de type String.
    *
    * @requires nom!=null ;
    * 
    * @return l'objet || null.
    * 
    */
    // @pure 
	public Objet getObjetByNom(String nom){
		for(Objet o2:this.objets){
			if(nom.equals(o2.getNom())){
				return o2;
			}
		}
		return null;
	}

/** 
    *la methode getChienByNom prendre en argument un string.
    * et permet de retourner un chien qui se trouve dans
    * la liste des chiens de la piece.
    *
    * @param nom de type String.
    *
    * @requires nom!=null ;
    * 
    * @return l'objet || null.
    * 
    */
    // @pure 
	public Chien getChienByNom(String nom){
		for(Animal o2:this.animaux){
			if(o2 instanceof Chien){
				if(nom.equals(o2.getNom())){
					return (Chien) o2;
				}
			}
		}
		return null;
	}


/** 
    *la methode listObjetString ne prend pas d'argument.
    * et permet de retourner une liste des nom des objets de la liste piece.
    *
    * @requires nom!=null
    * 
    * @return liste des noms des objets; 
    */ 
    // @pure
	public ArrayList<String> listObjString(){
		ArrayList<String> res= new ArrayList<String>();
		for(Objet o2:this.objets){
			res.add(o2.getNom());
		}	
		return res;	
	}


/**
	 * Renvoie la liste des nom des tous les chiens de la piece,
	 * à l'aide de deux méthodes de l'instance ArrayList.
	 *
	 *@return liste de string 
	 */
	// @ pure
	public ArrayList<String> listChienString(){
		ArrayList<String> res= new ArrayList<String>();
		for(Animal o2:this.animaux){
			if(o2 instanceof Chien){
				res.add(o2.getNom());
			}
		}	
		return res;	
	}

	// permet de revoyer le poids totale des objets de la piece.
	public int getPoidsTotal(){
		int p=0;
		for(int i=0;i<this.objets.size();i++){
			p=p+this.objets.get(i).getPoids();
		}
		return p;
	}
}


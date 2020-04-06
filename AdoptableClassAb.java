package zork;

import java.util.ArrayList;
import java.util.Random;


/**
 *  Les animaux adoptables dans le jeu Zork.
 * 
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte. Cette classe abstraite implimente l'interface Adoptable et herite de la classe AnimalClassAb.
 *  Un animal adoptable, peut faire l'objet de propositions pour ses
 *  déplacements, lorsqu'il est adopté (et donc non libre) les choix effectués
 *  par l'animal adopté pour ses déplacements sont conformes aux propositions qui
 *  lui sont faites.
 * </p>
 * 
 * @invariant !estLibre() ==> (\forall Piece p; p != null; choisirPiece(p) == getSortieProposee(p));
 * 
 *
 * 
 * @author BILAL BRAHIMI
 * @author TABTI HABIB ABDRAOUF
 * @since 28 Decembre 2019
 */
public abstract class AdoptableClassAb extends AnimalClassAb implements Adoptable{

    private boolean libre =true;
    private Direction sortieProposee;
    private ArrayList<Objet> objets;
    

    /**
	 * creé un nouveau Animal dont la piece courrante est 
	 * la piece où se trouve et dont le poidsMax est son poids max 
	 * et dont le nom c'est son nom et dont capitalVie est son capitale de vie . 
	 * 
	 * @param pieceCourante la piece où se trouve l'animal.
	 * @param poidMax son poids maximum qu'il peut prndre avec lui.
	 * @param nom son nom.
	 * @param capitalVie son capitale de vie.
	 */
    public AdoptableClassAb(Piece pieceCourante,int poidMax,String nom ,int capitalVie){
        super( pieceCourante, poidMax, nom,capitalVie);
		this.objets=new ArrayList <Objet>();
    }

	/**
	 * Renvoie true si cet animal est libre.
	 * 
	 * @return true si cet animal est libre, false sinon.
	 * 
	 * @pure
	 */    
	public boolean estLibre(){
        return this.libre;
    }

	/**
	 * Fait de cet animal un animal adopté.
	 * 
	 * @return true si cet animal était libre, false sinon.
	 * 
	 * @ensures !estLibre();
	 * @ensures \result <==> (estLibre() != \old(estLibre()));
	 */
	public boolean adoption(){
        if (this.estLibre()){
            this.libre=false;
            return true;
        }
        return false;
    }

	/**
	 * Libère cet animal.
	 * 
	 * @return true si cet animal était précédemment adopté, false sinon.
	 * 
	 * @ensures estLibre();
	 * @ensures \result <==> (estLibre() != \old(estLibre()));
	 */
	public boolean liberation(){
        if (!(this.estLibre())){
            this.libre=true;
            return true;
        }
        return false;
    }

	/**
	 * Propose ou impose la sortie spécifiée pour la pièce spécifiée. Lorsque cet
	 * animal est adopté cette sortie proposée sera la sortie choisie (par la
	 * méthode choisirSortie) pour le prochain déplacement de cet animal, si ce
	 * déplacement se fait à partir de cette pièce.
	 * 
	 * @param p la pièce pour laquelle une sortie est proposée
	 * @param d sortie proposée pour la pièce spécifiée
	 * 
	 * @throws NullPointerException si l'un des arguments spécifiés est null
	 * @throws IllegalArgumentException si la pièce spécifiée ne possède pas de 
	 * 			sortie dans la direction spécifiée
	 * 
	 * @requires p != null;
	 * @requires d != null;
	 * @requires p.pieceSuivante(d) != null;
	 * @ensures getSortieProposee(p) == d;
	 * 
	 */
	public void proposerSortie(Piece p, Direction d){
        if(p==null || d==null){
            throw new NullPointerException("l'un des arguments spécifiés est null");
        }else if(p.pieceSuivante(d)==null){
            throw new IllegalArgumentException("la pièce spécifiée ne possède pas de sortie dans la direction spécifiée");
        }else{
            this.sortieProposee = d;
        }
    }

	/**
	 * Renvoie une sortie proposée pour la pièce spécifiée. La sortie renvoyée est
	 * celle proposée par le dernier appel à proposerSortie pour la pièce spécifiée,
	 * ou null si cet animal s'est déplacé (appel à la méthode deplacerDepuis)
	 * depuis le dernier appel à proposerSortie.
	 * 
	 * @param p pièce dont on veut connaitre la sortie proposée.
	 * @return sortie proposée pour la pièce spécifiée ou null si aucune sortie n'a
	 *         été proposée depuis le dernier déplacement.
	 * 
	 * @throws NullPointerException si l'un des arguments spécifiés est null
	 * 
	 * @requires p != null;
	 * @ensures (\result != null) ==> (p.pieceSuivante(\result) != null);
	 * @ensures !estLibre() ==> (\result == choisirSortie(p));
	 * 
	 * @pure
	 */
	public Direction getSortieProposee(Piece p){
        if(p==null){
            throw new NullPointerException("l'argument spécifié est null");
        }else{
            return this.sortieProposee;
        }
    }


	/**
	 * {@inheritDoc}
	 * <p>
	 * Lorsque cet animal est adopté, la sortie choisie doit impérativement être
	 * celle renvoyée par la méthode getSortieProposee, l'entitée ayant adopté cet
	 * animal doit donc imposer le choix en appelant la méthode proposerSortie avant
	 * d'effectuer un déplacement à l'aide de la méthode deplacerDepuis. Si cet
	 * animal est libre, il n'y a aucune obligation à ce que le choix effectué
	 * prenne en compte la proposition de sortie renvoyée par la méthode
	 * getSortieProposee.
	 * </p>
	 * 
	 * @requires p != null;
	 * @ensures !estLibre() ==> (\result == getSortieProposee(p));
	 * 
	 * @pure
	 */
	@Override
	public Direction choisirSortie(Piece p){
        if (p== null){
            throw new NullPointerException("L'objet Piece est null");
        }else{ 
            if(!(this.estLibre())){
                return this.getSortieProposee(p);
            }else if(this.estLibre()){
                Random random = new Random();
                return Direction.values()[random.nextInt(Direction.values().length)];
            }else {
                return null;
            }
        }
    }


	/**
	 * {@inheritDoc}
	 * @param p la pièce à partir de laquelle a lieu le déplacement
	 * @return la nouvelle pièce dans laquelle se trouve cet animal ou null si le
	 *         déplacement n'a pas eu lieu.
	 * 
	 * @throws NullPointerException     si l'argument spécifié est null
	 * @throws IllegalStateException    si cet animal est mort
	 * @throws IllegalArgumentException si cet animal n'est pas présent dans la
	 *                                  pièce spécifiée avant appel de cette méthode
	 * 
	 * @requires p != null;
	 * @requires estVivant();
	 * @requires p.contientAnimal(this);
	 * @ensures getSortieProposee(p) == null;
	 */
	@Override
	public Piece deplacerDepuis(Piece p){
        if (p== null){
            throw new NullPointerException("L'objet Piece est null");
        }else if(!(this.estVivant())){
            throw new IllegalStateException("L'animal est mort");
        }else if(this.getPieceCourante()!=p){
            throw new IllegalArgumentException("L'animal n'est pas dans la piece p");
        }else{
            Direction dir = getSortieProposee(p);
            
            if (dir != null){
                this.pieceCourante= p.pieceSuivante(dir);
				int cv= this.getCapitalVie();
                cv -= this.getCoutDeplacement();
                this.sortieProposee=null;
                if(this.getCapitalVie()<=0){
                    this.mourirDans(p);
                }
                return pieceCourante;
            }
            
            return null;
        }
	}


    /** 
    * la methode deposer prendre en argument un objet.
    * et permet de deposer l'objet qui se trouve dans
    * la liste des objets de l'animal adoptable et le rajouter a la piece courante.
    *
    * @param obj de type objet.
    *
    * @requires obj!=null ;
    * @requires this.objets != vide ;
    * 
    * @ensures !(obj instanceof objet) ==> !(deposer) ;
    * @ensures objets contains obj ;
    * @ensures poidsMax decrimente ;
    * @ensures pieceCourante.objets.add(obj);
    * 
    */
    // @pure 
	public void deposer(Objet obj){
        if (this.objets.contains(obj)){
            this.objets.remove(obj);
            this.pieceCourante.objets.add(obj);
			this.setPoidsMax(this.getPoisMax()+obj.getPoids());
            //this.poidMax=this.poidMax+obj.getPoids();
		    System.out.println("Vous avez deposé : "+ obj.getNom());

        }
        
    }

    /** 
    * la methode saisir prendre en argument un objet.
    * et permet de rajouter l'objet qui se trouve dans
    * la piece a la liste des objets de l'animal adoptable.
    *
    * @param obj de type objet.
    *
    * @requires obj!=null.
    * @requires obj.getPoids()<= poidMax.
    *
    * @ensures !(obj instanceof objet) ==> !(saisir).
    * @ensures saisir(obj) ==> poidMax decrimente .
    * @ensures pieceCourante.objets.remove(obj) ;
    * 
    */
    //@pure 
    public void saisir(Objet obj){
        if (obj.getPoids()<= this.getPoisMax()){
            this.objets.add(obj);
            this.pieceCourante.objets.remove(obj);
			this.setPoidsMax(this.getPoisMax()-obj.getPoids());
            //this.poidMax=this.poidMax-obj.getPoids();
            System.out.println("Vous avez saisi : "+ obj.getNom());
        }
        else{
            System.out.println("Saisi impossible ... ");

        }
    }


    /** 
    * ronvoie la liste des objets qui se trouve dans la reserve de l'animal. 
    *
    * @return liste des objets de l'animal adoptable.
    */
    public ArrayList<Objet> getObjets(){
        return this.objets;
    }



    /** 
    * ronvoie la liste des noms des objets qui se trouve dans la reserve de l'animal. 
    *
    * @return liste des noms des objets de l'animal adoptable.
    */
    public ArrayList<String> listObjString(){
		ArrayList<String> res= new ArrayList<String>();
		for(Objet o2:this.objets){
			res.add(o2.getNom());
		}	
		return res;	
	}



    /** 
    * la methode getObjetByNom prendre en argument un String.
    * et permet de retourner un objet qui se trouve dans
    * la liste des objets de lg'animal adoptable.
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
    * ronvoie un aliment qui se trouve dans la resrve de l'animal si y'a pas revoie null. 
    *
    * @return un aliment ou null.
    */
    public Aliment getAliment(){
        for(Aliment al:this.getPieceCourante().aliments){
            if((al instanceof Vegetale) || (al instanceof Viande)){
                return al;
            }
		}
        return null;
    }


    /**
	 * Mange l'aliment renvoyé par getAliment. Si getAliment renvoie une valeur non
	 * null, supprime l'aliment renvoyé de la réserve de cet animal et ajoute sa
	 * valeur nutritive au capital vie de cet animal.
	 * 
	 * @return l'aliment qui a été mangé ou null sinon.
	 * 
	 * @throws IllegalStateException si cet animal est mort
	 * 
	 * @requires estVivant();
	 * @ensures estVivant();
	 * @ensures (\result != null) ==> \result.equals(\old(getAliment()));
	 * @ensures (\result != null) ==> (getNbOccurences(\result) == \old(getNbOccurences(getAliment()) - 1));
	 * @ensures (\result != null) ==> (getCapitalVie() == \old(getCapitalVie() + getAliment().getValeur()));
	 * @ensures (\result == null) ==> (\old(getAliment()) == null);
	 * @ensures (\result == null) ==> (getCapitalVie() == \old(getCapitalVie()));
	 * @ensures (\result == null) ==> (getNbAliments() == \old(getNbAliments()));
	 * 
	 */
    public Aliment manger(){
        if(!(this.estVivant())){
            throw new IllegalStateException("L'animal est mort");
        }else{
            Aliment al = this.getAliment();
            if (al!=null){
                this.setCapitalVie(this.getCapitalVie()+al.getValeurNutritive());
                if(this.getCapitalVie()>=this.getMaxCapitalVie()){
                    this.setCapitalVie(this.getMaxCapitalVie());  
                }
                this.removeAliment(al);
                this.getPieceCourante().aliments.remove(al);
            }
        return al;
        }
    }

}
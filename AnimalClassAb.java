package zork;
import java.util.ArrayList;
import java.util.Set;
import java.util.Random;


/**
 *  Les animaux dans le jeu Zork.
 * 
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte. Cette classe abstraite implimente l'interface Animal.
 * 
 * <li>tous les animaux disposent au départ d'un certain nombre de points de
 * vie. Ils perdent des points à chaque action réalisée et en accumulent à
 * chaque fois qu'ils mangent (le nombre de point de vie dépend de la valeur
 * nutritive de ce qui est consommé). Lorsque le nombre de point de vie d'un
 * animal atteint une valeur négative ou nulle, l'animal meurt, dépose les
 * aliments qu'il transporte, reste dans la pièce où il est mort et ne fait plus
 * aucune action.</li>
 * <li>les animaux se déplacent en respectant les mêmes contraintes que les
 * joueurs: le déplacement d'une pièce à une autre n'est possible que s'il
 * existe un passage entre les deux pièces.</li>
 * <li>les animaux peuvent manger et les pièces peuvent contenir plusieurs types
 * d'aliment (ces types sont ceux énumérés par le type enum TypeAliment): chaque
 * espèce animale possède son propre régime alimentaires correspondant à un ou
 * plusieurs types d'aliment.</li>
 * <li>À l'exception de manger, chaque action de l'animal entraine une
 * diminution de ses points de vie, ce coût en nombre de points de vie est
 * spécifique à chaque action et à chaque espèce. Les différentes actions
 * possibles pour tous les animaux sont:
 * <ul>
 * <li>ramasser un aliment: l'animal choisit un aliment dans la pièce, l'ajoute
 * à sa réserve, le retire de la piece et diminue son capital de point de vie de
 * la valeur du coût de cette action</li>
 * <li>manger un aliment: l'animal choisit un aliment compatible avec son régime
 * alimentaire parmi ceux qui sont dans sa réserve, le supprime de sa réserve et
 * augmente son capital vie de la valeur nutritive de cet aliment</li>
 * <li>se déplacer: l'animal (ou son maître si l'animal est adoptable et adopté)
 * choisit une des sorties de la pièce, il est retiré de cette piece, est ajouté
 * à la piece choisie, puis diminue son capital de point de vie de la valeur du
 * coût de cette action</li>
 *
 * @invariant getNom() != null;
 * @invariant descriptionCourte() != null;
 * @invariant descriptionLongue() != null;
 * @invariant getMaxCapitalVie() > 0;
 * @invariant getCapitalVie() <= getMaxCapitalVie();
 * @invariant getRegimeAlimentaire() != null;
 * @invariant !getRegimeAlimentaire().isEmpty();
 * @invariant getValeurNutritive() > 0;
 * @invariant !contient(null);
 * @invariant estVivant() <==> (getCapitalVie() > 0);
 * @invariant !estVivant() ==> reserveEstVide();
 * @invariant getCoutRamasser() > 0;
 * @invariant getCoutDeplacement() > getCoutRamasser();
 * 
 * @author BILAL BRAHIMI
 * @author TABTI HABIB ABDRAOUF
 * @since 28 Decembre 2019
 */

public abstract class AnimalClassAb implements Animal{


    private String nom;
    private int poidMax;
    private int capitalVie;
    private int maxCapitalVie;
    private int valeurNutritive = 0;
    private int coutDeplacement = 1; // à revoir
    private int coutRamasser = 1; // à revoir
    private final int nbObjMax=5;
    public Piece pieceCourante;
    private ArrayList<Aliment> aliments;
    private String description;
    private boolean vivant = true;
	Set<TypeAliment> regimeAlimentaire;
	
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

    public AnimalClassAb(Piece pieceCourante,int poidMax,String nom,int capitalVie){
        this.capitalVie=capitalVie;
        this.maxCapitalVie=capitalVie;
        this.pieceCourante=pieceCourante;
        this.poidMax=poidMax;
        this.nom=nom;
        this.aliments=new ArrayList <Aliment>();
	}
	
	/**
	 * Renvoie le nom de cet animal.
	 * 
	 * @return le nom de cet animal
	 * 
	 * @pure
	 */
	public String getNom(){
        return this.nom;
	}
	
	/**
	 * Renvoie le poids max de cet animal.
	 * 
	 * @return le poids max de l'animal
	 * 
	 * @pure
	 */

    public int getPoidsMax(){
        return this.poidMax;
	}
	

	/**
	 * Renvoie le nombre d'objet max que l'animal peut saisir.
	 * 
	 * @return nbOjMax
	 * 
	 * @pure
	 */
    public int getNbObjMax(){
        return this.nbObjMax;
    }

	/**
	 * Renvoie la piece où se trouve l'animal.
	 * 
	 * @return la piece courante.
	 * 
	 * @pure
	 */
    public Piece getPieceCourante(){
        return this.pieceCourante;
    }

	/**
	 * Renvoie la liste des aliments qui son present dans la reserve de l'animal.
	 * 
	 * @return la liste des aliments.
	 * 
	 * @pure
	 */

    public ArrayList<Aliment> getAliments(){
        return this.aliments;
	}
	
	/**
	 * Renvoie les noms des aliments qui sont present dans la liste de l'animal comme une chaine de caractaire.
	 * 
	 * @return une description des aliments .
	 * 
	 * @pure
	 */

    public String getAlimentsStr(){
        String resulString = "";
		for (Aliment alm :  aliments) {
			resulString += alm.getNom()+ " ";
		}
		return resulString;
    }


	/**
	 * Renvoie une description courte de cet animal incluant son nom.
	 * 
	 * @return une description courte de cet animal
	 * 
	 * @ensures \result.contains(getNom());
	 * 
	 * @pure
	 */
	public String descriptionCourte(){
        return this.description;
    }

	/**
	 * Renvoie une description longue de cet animal incluant son nom et les aliments
	 * contenus dans sa réserve.
	 * 
	 * @return une description longue de cet animal
	 * 
	 * @ensures \result.contains(getNom());
	 * 
	 * @pure
	 */

	public String descriptionLongue(){
        return this.getNom()+": " + description + ".\n" + "aliments en reserve: "+getAlimentsStr()+"\n son capital de vie: " +getCapitalVie();

    }

	/**
	 * Renvoie le nombre de points de vie de cet animal.
	 * 
	 * @return le capital vie de cet animal
	 * 
	 * @pure
	 */
	public int getCapitalVie(){
        return this.capitalVie;
    }

	/** 
    * modifier le capital de vie de l'animal.
    *
    * @param cv un entier .
    *
    * @requires cv!=null
    */
	public void setCapitalVie(int cv){
        this.capitalVie = cv;
    }

	/** 
    * supprimer un aliment de la reserve de l'animal.
    *
    * @param al type Aliment .
    *
    * @requires al!=null
    */
	public void removeAliment(Aliment al){
        this.aliments.remove(al);
    }

	/**
	 * Renvoie le nombre de points de vie maximal de cet animal.
	 * 
	 * @return le capital vie maximal de cet animal
	 * 
	 * @pure
	 */
	public int getMaxCapitalVie(){
        return this.maxCapitalVie;
    }

	/**
	 * Renvoie true si cet animal est vivant.
	 * 
	 * @return true si cet animal est vivant, false sinon
	 * 
	 * @pure
	 */
	public boolean estVivant(){
        return this.vivant;
    }

	/**
	 * Fait mourir cet animal et dépose tous les aliments de sa réserve dans cette
	 * pièce.
	 * 
	 * @param p la pièce dans laquelle cet animal meurt
	 * 
	 * @requires p != null;
	 * @ensures !estVivant();
	 * @ensures reserveEstVide();
	 * @ensures p.getNbObjets() == \old(p.getNbObjets() + getNbAliments());
	 * @ensures (\forall Aliment al; \old(contient(al)); p.contientObjet(al));
	 * 
	 * @throws NullPointerException si la pièce spécifiée est null
	 */
	public void mourirDans(Piece p){
        if (p != null){
            for(Aliment al : this.aliments){
                p.ajouterAliment(al);
                this.aliments.remove(al);
		    }
            this.capitalVie=0;
            this.vivant=false;
            
        }else{
            throw new NullPointerException("L'objet Piece est null");
        }
    }

	/**
	 * Renvoie la valeur nutritive de cet animal. Cette valeur est prise en compte
	 * dans le cas où cet animal serait mangé.
	 * 
	 * @return la valeur nutritive de cet animal
	 * 
	 * @pure
	 */
	public int getValeurNutritive(){
        return this.valeurNutritive;
    }

	/**
	 * Renvoie true si cet animal peut manger l'aliment spécifié.
	 * 
	 * @param al aliment dont on souhaite savoir s'il peut être mangé par cet animal
	 * @return true si cet animal peut manger l'aliment spécifié, false sinon.
	 * 
	 * @ensures (al == null) ==> !\result;
	 * @ensures (al != null) ==> (\result <==> getRegimeAlimentaire().contains(al.getType()));
	 * 
	 * @pure
	 */
	public boolean peutManger(Aliment al){
		return this.regimeAlimentaire.contains(al.getTypeAliment());
	}

	/**
	 * Renvoie le nombre d'aliments présents dans la réserve de cet animal.
	 * 
	 * @return le nombre d'aliments présents dans la réserve de cet animal
	 * 
	 * @ensures \result >= 0;
	 * 
	 * @pure
	 */
	public int getNbAliments(){
        return this.aliments.size();
    }

	/**
	 * Renvoie le nombre d'exemplaires de l'objet spécifié présents dans la réserve
	 * de cet animal.
	 * 
	 * @param obj l'objet dont on souhaite connaitre le nombre d'exemplaires dans la
	 *            réserve de cet animal
	 * @return le nombre d'exemplaires de l'objet spécifié
	 * 
	 * @ensures !(obj instanceof Aliment) ==> (\result == 0);
	 * @ensures \result >= 0;
	 * @ensures \result <= getNbAliemnts();
	 * @ensures (\result > 0) ==> contient(obj);
	 * 
	 * @pure
	 */
	public int getNbOccurences(Object obj){
        if (!(obj instanceof Aliment)){
            return 0;
        }
        else{
            int res =0;
            Aliment ob = (Aliment) obj;
            for(Aliment al : this.aliments){
                if (obj.equals(al)){
                    res++;
                }
		    }
            return res;
        }
    }

	/**
	 * Renvoie true si l'objet spécifié est présent dans la réserve de cet animal.
	 * 
	 * @param obj l'objet dont on souhaite savoir s'il est présent dans la réserve
	 *            de cet animal
	 * @return true si la réserve de cet animal contient l'objet spécifié, false
	 *         sinon
	 * 
	 * @ensures !(obj instanceof Aliment) ==> !\result;
	 * @ensures \result ==> (getNbOccurences() > 0);
	 * 
	 * @pure
	 */
	public boolean contient(Object obj){
        if (obj instanceof Aliment){
            if (getNbOccurences(obj)>0){
                return true;
            }
        }
		return false;
    }

	/**
	 * Renvoie true si la réserve de cet animal est vide.
	 * 
	 * @return true si la réserve de cet animal est vide
	 * 
	 * @ensures \result <==> (getNbAliments() == 0);
	 * 
	 * @pure
	 */
	public boolean reserveEstVide(){
        return (this.getNbAliments() == 0);
    }

	/**
	 * Choisit dans la piece spécifiée un aliment à ramasser. Les classes
	 * implémentant cette interface sont entièrement libres des critères de choix de
	 * l'aliment, il n'est pas imposé qu'un aliment soit choisi.
	 * 
	 * 
	 * @param p la pièce dans laquelle l'aliment doit être choisi
	 * @return l'aliment choisi ou null si aucun aliment n'a été choisi.
	 * 
	 * @throws NullPointerException si l'argument spécifié est null
	 * 
	 * @requires p != null;
	 * @ensures (\result != null) ==> p.contientObjet(\result);
	 * 
	 * @pure
	 */
	public Aliment choisirAliment(Piece p){
        if(p != null){
            if (p.aliments.size()==0){
                return null;
            }else{
                Random ran = new Random();
                int nxt = ran.nextInt(p.aliments.size());
                return p.aliments.get(nxt);
			}    
        }else{
            throw new NullPointerException("L'objet Piece est null");
        }
    }

	/**
	 * Renvoie le cout d'une action "ramasser" de cet animal. Lors de chaque
	 * ramassage d'aliment effectué par cet animal, son capital vie sera diminué de
	 * ce coût.
	 * 
	 * @return le cout d'une action "ramasser" pour cet animal
	 * 
	 * @pure
	 */
	public int getCoutRamasser(){
        return this.coutRamasser;
    }

	/**
	 * Ramasse dans la pièce spécifié l'aliment renvoyé par la méthode
	 * choisirAliment. Si cette action a pour effet de tuer cet animal le contenu de
	 * sa réserve est transféré dans la pièce spécifié.
	 * 
	 * @param p la pièce dans laquelle un aliment doit être ramassé.
	 * @return l'aliment ramassé ou null si aucun aliment n'a été ramassé
	 * 
	 * @throws NullPointerException  si l'argument spécifié est null
	 * @throws IllegalStateException si cet animal est mort
	 * 
	 * @requires p != null;
	 * @requires estVivant();
	 * @ensures (\result == null) ==> (getNbAliments() == \old(getNbAliments()));
	 * @ensures (\result == null) ==> (getCapitalVie() == \old(getCapitalVie()));
	 * @ensures ((\result != null) && estVivant()) ==> contient(\result);
	 * @ensures ((\result != null) && estVivant()) ==> (getNbAliments() == \old(getNbAliments() + 1));
	 * @ensures ((\result != null) && estVivant()) ==> (p.getNbObjets() == \old(p.getNbObjets() - 1));
	 * @ensures ((\result != null) && estVivant()) ==> (getCapitalVie() == \old(getCapitalVie() - getCoutRamasser()));
	 * @ensures ((\result != null) && !estVivant()) ==> reserveEstVide();
	 * @ensures ((\result != null) && !estVivant()) ==> (p.getNbObjets() == \old(p.getNbObjets() + getNbAliments()));
	 */
	public Aliment ramasserDans(Piece p){
        if (p== null){
            throw new NullPointerException("L'objet Piece est null");
        }else if(!(this.vivant)){
            throw new IllegalStateException("L'animal est mort");
        }else{
            Aliment al= choisirAliment(p);
            if (al != null){
                this.aliments.add(al);
                p.objets.remove(al);
                this.capitalVie -= this.getCoutRamasser();
                if(this.getCapitalVie()<=0){
                    this.mourirDans(p);
                }
            }
            
            return al;
        }
    }

	// Gestion des déplacements

	/**adopt.getPieceCourante()
	 * @pure
	 */
	public int getCoutDeplacement(){
        return this.coutDeplacement;
    }

	/**
	 * Déplace cet animal vers une pièce voisine de la pièce spécifiée. Le
	 * déplacement a lieu dans la direction renvoyée par la méthode choisirPiece, si
	 * choisirPiece renvoie null aucun déplacement n'a lieu. Si le déplacement a
	 * lieu, le capital vie de cet animal est diminué de la valeur renvoyée par la
	 * méthode getCoutDeplacement. Seul un animal vivant peut se déplacer. Si ce
	 * déplacement a pour effet de tuer cet animal, l'animal est déplacé puis le
	 * contenu de sa réserve est transféré dans la pièce de destination renvoyé.
	 * 
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
	 * @ensures (\result == null) <==> \old(choisirSortie(p) == null);
	 * @ensures (\result == null) ==> p.contientAnimal(this);
	 * @ensures (\result == null) ==> (getCapitalVie() == \old(getCapitalVie()));
	 * @ensures (\result != null) ==> (\result == p.pieceSuivante(\old(choisirSortie(p)));
	 * @ensures (\result != null) ==> \result.contientAnimal(this);
	 * @ensures (\result != null) ==> !p.contientAnimal(this);
	 * @ensures ((\result != null) && estVivant()) ==> (getCapitalVie() == (\old(getCapitalVie()) - getCoutDeplacement()));
	 * @ensures ((\result != null) && !estVivant()) ==> reserveEstVide();
	 * @ensures ((\result != null) && !estVivant()) ==> (\result.getNbObjets() == \old(p.pieceSuivante(choisirSortie(p)).getNbObjets() + getNbAliments()));
	 */
	public Piece deplacerDepuis(Piece p){
        if (p== null){
            throw new NullPointerException("L'objet Piece est null");
        }else if(!(this.vivant)){
            throw new IllegalStateException("L'animal est mort");
        }else if(this.getPieceCourante()!=p){
            throw new IllegalArgumentException("L'animal n'est pas dans la piece p");
        }else{
            Direction dir = choisirSortie(p);
            
            if (dir != null && p.pieceSuivante(dir)!=null ){
                this.pieceCourante= p.pieceSuivante(dir);
                this.capitalVie -= this.getCoutDeplacement();
                if(this.getCapitalVie()<=0){
                    this.mourirDans(p);
                }
                return pieceCourante;
            }
            return null;
        }
    }

	/**
	 * Choisit une des sorties de la pièce spécifiée. Les classes implémentant cette
	 * interface sont entièrement libres des critères de choix de la pièce, il n'est
	 * pas imposé qu'une sortie soit choisie.
	 * 
	 * 
	 * @param p la pièce dont une sortie doit être choisie
	 * @return une sortie de la pièce spécifiée ou null
	 * 
	 * @throws NullPointerException si l'argument spécifié est null
	 * 
	 * @requires p != null;
	 * @ensures (\result != null) ==> (p.pieceSuivante(\result) != null);
	 * 
	 * @pure
	 */
	public Direction choisirSortie(Piece p){
        if (p== null){
            throw new NullPointerException("L'objet Piece est null");
        }else{
        //int pick = new Random().nextInt(Direction.values().length);
        //return p.getSorties().get(Direction.keys()[pick]);

		Random random = new Random();
        return Direction.values()[random.nextInt(Direction.values().length)];
        }
    }

	/**
	 * Renvoie l'ensemble des types d'aliment que cet animal peut manger.
	 * 
	 * @return le régime alimentaire de cet animal
	 * 
	 * @ensures \result != null;
	 * @ensures !\result.isEmpty();
	 * @ensures (\forall Aliment al; al != null && \result.contains(al.getType()); peutManger(al));
	 * 
	 * @pure
	 */
	public Set<TypeAliment> getRegimeAlimentaire(){
        return this.regimeAlimentaire;
    }

	/**
	 * Renvoie un aliment présent dans la réserve de cet animal et correspondant au
	 * régime alimentaire de cet animal (cf. getRegimeAlimentaire). L'aliment
	 * renvoyé sera consommé lors de la prochaine action "manger" sous réserve
	 * qu'aucune modification n'ait eu lieu concernant le contenu de la réserve de
	 * cet animal. Il n'est pas imposé que cette méthode renvoie un aliment, y
	 * compris si un élément consommable est présent dans la réserve, les classes
	 * implémentant cette interface sont entièrement libre dans la définition des
	 * critères de choix de l'aliment.
	 * 
	 * 
	 * @return un aliment consommable par cet animal ou null
	 * 
	 * @ensures (\result != null) ==> peutManger(\result);
	 * @ensures (\result != null) ==> contient(\result);
	 * 
	 * @pure
	 */
	public Aliment getAliment(){
        Set<TypeAliment> regime =  this.getRegimeAlimentaire();

        Random ran = new Random();
		if(!this.aliments.isEmpty()) {
			int nxt = ran.nextInt(this.aliments.size());
			Aliment al = this.aliments.get(nxt);
			if(regime.contains(al.getTypeAliment())){
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
        if(!(this.vivant)){
            throw new IllegalStateException("L'animal est mort");
        }else{
            Aliment al = this.getAliment();
            if (al!=null){
                this.capitalVie+=al.getValeurNutritive();
                if(this.capitalVie>=this.getMaxCapitalVie()){
                    this.capitalVie=this.getMaxCapitalVie();
                }
                this.aliments.remove(al);
            }
        return al;
        }
	}
	
	/** 
    * modifier le nom de l'animal
    *
    * @param p de type String.
    *
    * @requires nom!=null
    */
    public void setNom(String nom){
        this.nom=nom;
    }

	/** 
    * modifier le poidsMax de l'animal.
    *
    * @param p de type entier. 
    *
    * @requires p!=null
    */
	public void setPoidsMax(int p){
		this.poidMax = p;
	}

	/** 
	* return le poids max de l'animal.
	*
    * @return poidMax
	* 
	* @pure
    */
    public int getPoisMax(){
        return this.poidMax;
    }

}
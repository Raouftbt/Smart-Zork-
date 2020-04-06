package zork;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *  Un Joueur dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 * 
 *  Un joueur est caracteriser par son nom et la piece où se trouve. .</p>
 * 
 * @author     Brahimi Bilal
 * @author     Tabti habib abedraouf
 * @version    1.2
 * @since      Octobre 2019
 */
public class Joueur{
    private String nom;
    private int poidMax = 50;
    public Piece pieceCourante;
    private ArrayList<Objet> objets;
    private ArrayList<Chien> chiens;

    
    /**
    *inisialiser un Joueur dont le nom 
    *et la pieceCourante sont specifier par les paramétres.
    @param poidMax le poids maximum de joueur
    @param nom le nom de Joueur
    @param pieceCourante la piece où se trouve le joueur
    @param objets la liste des objets de joueur
    @param chiens la liste des chiens de joueur
    @requires poidMax>0;
    @requires nom!=null;
    @ensures getPoidsMax()==poids;
    @ensures getObjets()==objets;
    @ensures getChiens()==chiens;
    @ensures getPieceCourante()==pieceCourante;
    *
    */
    public Joueur(String nom, Piece pieceCourante){

        this.nom=nom;
        this.pieceCourante=pieceCourante;
        this.objets=new ArrayList <Objet>();
        this.chiens=new ArrayList <Chien>();

    }
    /** 
    * ronvoie le poids maximum que le joueur peut transporter 
    * @return   Le poids maximum de joueur
    */
    public int getPoisMax(){
        return this.poidMax;
    }


    /** 
    *ronvoie le poids maximum que le joueur peut transporter 
    @return   Le poids maximum de joueur
    */
    public ArrayList<Objet> getObjets(){
        return this.objets;
    }

    /** 
    *ronvoie la liste des chiens qui accompagnent le joueur
    *
    @return   La liste des chiens adoptant
    */
    public ArrayList<Chien> getChiens(){
        return this.chiens;
    }

    /** 
    * ronvoie la piece où le joueur se trouve.
    *
    * @return   la piece courante
    *
    */
    public Piece getPieceCourante(){
        return this.pieceCourante;
    }


    /** 
    * modifier la piece où le joueur se trouve
    *
    * @param p la piece 
    *
    * @requires p!=null
    */
    public void setPieceCourante(Piece p){
        this.pieceCourante=p;
    }

    /** 
    * la methode saisir prendre en argument un objet.
    * et permet de rajouter l'objet qui se trouve dans
    * la piece a la liste des objets de joueur.
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
        if (obj.getPoids()<= this.poidMax){
            this.objets.add(obj);
            this.pieceCourante.objets.remove(obj);
            this.poidMax=this.poidMax-obj.getPoids();
            System.out.println("Vous avez saisi : "+ obj.getNom());
        }
        else{
            System.out.println("Saisi impossible ... ");

        }
    }



    /** 
    * la methode deposer prendre en argument un objet.
    * et permet de deposer l'objet qui se trouve dans
    * la liste des objets de joueur et le rajouter a la piece courante.
    *
    * @param obj de type objet.
    *
    * @requires obj!=null
    * @requires this.objets != est_vides.
    * 
    * @ensures !(obj instanceof objet) ==> !(deposer).
    * @ensures objets contains obj.
    * @ensures poidsMax decrimente
    * 
    */
    // @pure 
    public void deposer(Objet obj){
        if (this.objets.contains(obj)){
            this.objets.remove(obj);
            this.pieceCourante.objets.add(obj);
            this.poidMax=this.poidMax+obj.getPoids();
		    System.out.println("Vous avez deposé : "+ obj.getNom());

        }
        
    }


/** 
    *la methode getObjetByNom prendre en argument un string.
    * et permet de retourner un objet qui se trouve dans
    * la liste des objets de joueur.
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
    *la methode listObjetString ne prend pas d'argument.
    * et permet de retourner une liste des nom des objets de la liste joueur.
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
	 * Renvoie la liste des nom des tous les chien que joueur adopte,
	 * à l'aide de deux méthodes de l'instance ArrayList.
	 *
	 *
	 */
	// @ pure

    public ArrayList<String> listChiensString(){
		ArrayList<String> res= new ArrayList<String>();
		for(Chien ch:this.chiens){
			res.add(ch.getNom());
		}	
		return res;	
	}
/**
	 * Cette methode permet de rajouter le chien qu'on veut 
     * adopter a la liste des chiens.cette methode ne renvoie rien.
	 * add permet d'ajouter dans l'ArrayList le chien. 
	 * 
	 * @param chien de type Chien;
     * @param nom le nom de chien de type String;
     * 
	 * @requires chien != null;
     * @requies  nom  !=null;
     * 
	 * @ensures chiens.add(chien) ==> pieceCourante.chiens.remove(chien);
	 * 
	 */
    public void adopter(Chien chien,String nom){
        chien.setNom(nom);
        chien.adoption();
        this.chiens.add(chien);
        this.pieceCourante.animaux.remove(chien);
        System.out.println("Vous avez adopté "+nom);

    }


    //permet de recuperer un chien par son nom
	public Chien getChienByNom(String nom){
		for(Chien ch:this.chiens){
			if(nom.equals(ch.getNom())){
				return ch;
			}
		}
		return null;
    }

    // permet de supprimer un chien de la class joueur et le rajouter dans la piece courante.
    public void liberer(String nom){
        Chien ch= getChienByNom(nom);
		for(Objet o2:ch.getObjets()){
			ch.deposer(o2);
		}
        
        this.pieceCourante.animaux.add(ch);
        this.chiens.remove(ch);
    }

}
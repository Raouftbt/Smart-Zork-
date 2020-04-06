package zork;



/**
 *  Un animal de type proie dans un jeu d'aventure. <p>
 *
 *  Cette classe Hérite de la classe abstraite AnimalClassAb et impliment l'interface Aliment
 *  et elle fait partie du logiciel Zork un jeu d'aventure simple en mode texte.</p> <p>
 * 
 *  Un animal de type proie est caracteriser par son nom et la piece où se trouve et son poidMax et son capital de vie 
 *  et sa valeur nutritive.
 *
 * @author     Brahimi Bilal
 * @author     Tabti Habib abdeRaouf
 * @version    1.2
 * @since      Decembre 2019
 */
public abstract class ProieClassAb extends AnimalClassAb implements Aliment{


    private int valeurNutritive;
    private TypeAliment tAliment;

    /**
     * inisialiser un Animal de type proie dont le nom et la pieceCourante et son poidMax et son capital de vie 
     * et sa valeur nutritive sont specifier par les paramétres.
	 * 
	 * @param pieceCourante la piece où se trouve l'animal.
	 * @param poidMax son poids maximum qu'il peut prndre avec lui.
	 * @param nom son nom.
	 * @param capitalVie son capitale de vie.
     * @param valeurN sa valeur nutritive.
     * 
     * @requires poidMax>0;
     * @requires nom != null;
     * @requires pieceCourante != null;
     * @requires capitaleVie >0;
     * @requires valeurN >0
     * 
	 */

    public ProieClassAb(Piece pieceCourante,int poidMax,String nom,int capitalVie,int valeurN){
        super( pieceCourante, poidMax, nom,capitalVie);
        this.valeurNutritive=valeurN;
        this.tAliment = TypeAliment.VIANDE;
    }


    /** 
    * ronvoie le type d'aliment de cette animal.
    *
    * @return  TypeAliment.
    *
    */
    public TypeAliment getTypeAliment(){
        return this.tAliment;
    }


    /** 
    * ronvoie un aliment de type vigitale presente de la piece courante de l'animal
    * et si y'a pas renvoie null.
    *
    * @return  type Aliment.
    *
    */    
    public Aliment getAliment(){
        for(Aliment al:this.getPieceCourante().aliments){
            if(al instanceof Vegetale){
                return al;
            }
		}
        return null;
    }

    /** 
    * ronvoie la valeure nutritive de l'animal.
    *
    * @return  Type entier.
    *
    */
    public int getValeurNutritive(){
        return this.valeurNutritive;
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
                //this.capitalVie+=al.getValeurNutritive();
                this.setCapitalVie(this.getCapitalVie()+al.getValeurNutritive());
                if(this.getCapitalVie()>=this.getMaxCapitalVie()){
                    this.setCapitalVie(this.getMaxCapitalVie());
                    //this.capitalVie=this.getMaxCapitalVie();
                }
                //this.getAliment().remove(al);
                this.removeAliment(al);
                this.getPieceCourante().aliments.remove(al);
            }
        return al;
        }
    }
}

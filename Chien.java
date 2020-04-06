package zork;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import java.lang.Iterable;
import java.util.Iterator;

/**
 *  Un Chien dans un jeu d'aventure. <p>
 *
 *  Cette classe Hérite de la classe abstraite AdoptableClassAb 
 *  un jeu d'aventure simple en mode texte.</p> <p>
 * 
 *  Un Chien est caracteriser par sa race, la piece où se trouve , son poidMax et son capital de vie. .</p>
 *
 * @author    Tabti Habib abdeRaouf
 * @version    1.2
 * @since      Decembre 2019
 */

public class Chien extends AdoptableClassAb{
	private RaceChien race;

    
    /**
    * inisialiser un Chien dont le nom 
    * et la pieceCourante et son poidMax sont specifier par les paramétres.
    * @param poidMax le poids maximum de Chien.
    * @param nom nom de chien .
    * @param race sa race .
    * @param pieceCourante la piece où se trouve le Chien.
    * @param capitalVie  son capital de vie
    *
    * @requires poidMax>0;
    * @requires nom != null;
    * @requires race != null ; 
    * @requires pieceCourante != null;
    * @requires capitaleVie >0;
    *
    *
    */
    public Chien(Piece pieceCourante,int poidMax,String nom ,int capitalVie, RaceChien race){
        super( pieceCourante, poidMax, nom ,capitalVie);
        this.race=race;
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
        return super.choisirSortie(p);
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
        return super.deplacerDepuis(p);
    }
    /**
	 * Renvoie la race du chien.
	 * 
	 * @return race
	 * 
	 * @pure
	 */
    public RaceChien getRace(){

    	return this.race;
	}
	
	public String getRaceByNom() {
		String a="";
		if(this.getRace().equals(RaceChien.Berger)){
			a="Berger";
			
		}else if(this.getRace().equals(RaceChien.Bulldog)){
			a="Bulldog";
	
		}else if(this.getRace().equals(RaceChien.Pitbull)){
			a="Pitbull";
		
		}else if(this.getRace().equals(RaceChien.Staff)){
			a="Staff";
		
		}
		return a;
	}
  
	/**
	 * retourne un itérateur sur l’ensembles des éléments : un itérateur est un objet qui implémente l’interface Iterator, 
     * et qui a pour but de permettre le parcours des éléments d’un conteneur <Aliment>,
     * sans avoir besoin de savoir de quelle nature est ce conteneur <Aliment>.
     * 
    */
    public Iterator<Aliment> iterator() {        
        Iterator<Aliment> iprof = this.getAliments().iterator();
        return iprof; 
    }
}
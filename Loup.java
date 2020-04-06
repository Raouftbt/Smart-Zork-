package zork;

import java.lang.Iterable;
import java.util.Iterator;

import java.util.ArrayList;

/**
 *  Un Loup dans un jeu d'aventure. <p>
 *
 *  Cette classe Hérite de la classe abstraite ChasseurClassAb .
 *  un jeu d'aventure simple en mode texte.</p> <p>
 * 
 *  Un Loup est caracteriser par son nom , sa race  , la piece où se trouve , son poidMax et son capital de vie. .</p>
 *
 * @author     Tabti Habib abdeRaouf
 * @version    1.2
 * @since      Decembre 2019
 */

public class Loup extends ChasseurClassAb{
	private RaceLoup race ;  

    /**
     * inisialiser un Loup dont le nom et la pieceCourante et son poidMax sont specifier par les paramétres.
	 * 
	 * @param pieceCourante la piece où se trouve l'animal.
	 * @param poidMax son poids maximum qu'il peut prndre avec lui.
	 * @param nom son nom.
	 * @param capitalVie son capitale de vie.
     * @param race sa race . 
     *
     * @requires poidMax>0;
     * @requires nom != null;
     * @requires pieceCourante != null;
     * @requires capitaleVie >0;
     * @requires race != null;
	 */

    public Loup(Piece pieceCourante,int poidMax,String nom ,int capitalVie, RaceLoup race){
        super( pieceCourante, poidMax, nom,capitalVie);
        this.race = race ; 
    }
    
    /**
	 * Renvoie la race du loup.
	 * 
	 * @return race
	 * 
	 * @pure
	 */
    public RaceLoup getRace(){

    	return this.race;
    }

    public String getRaceByNom() {
		String a="";
		if(this.getRace().equals(RaceLoup.Arabe)){
			a="Arabe";
		
		}else if(this.getRace().equals(RaceLoup.Gris)){
			a="Gris";
		
		}else if(this.getRace().equals(RaceLoup.Iberique)){
			a="Iberique";
		
		}else if(this.getRace().equals(RaceLoup.Rouge)){
			a="Rouge";
			
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
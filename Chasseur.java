package zork;

import java.util.Set;
import java.util.ArrayList;

/**
 * Un animal chasseur a la possibilité de choisir une proie présente dans une
 * pièce, puis de la tuer et la manger.
 * <p>
 * Comme pour les autres actions définies par l'interface Animal, l'animal doit
 * être vivant pour chasser et son capital vie sera diminué du coût de la
 * chasse, puis augmenté de la valeur nutritive de la proie.
 * </p>
 * <p>
 * Si la pèce dans laquelle l'animal chasse contient un animal mort dont il est
 * prédateur, il a la possibilité de choisir cet animal comme proie et dans ce
 * cas il ne subit aucune perte de points de vie du fait de la chasse et se
 * contente d'augmenter son capital vie de la valeur nutritive de la proie.
 * </p>
 * <p>
 * Si le coût de la chasse est supérieur à son capital vie antérieur à la chasse
 * le chasseur et la proie sont tués, mais la proie n'est pas mangée et reste
 * morte dans la pièce où elle était.
 * </p>
 * <p>
 * Lorsqu'une proie est mangée elle disparait du jeu, après avoir laissé les
 * aliments qu'elle transportait dans la pièce où elle est morte
 * </p>
 * 
 * @invariant getCoutChasse() > (2 * getCoutDeplacement());
 * @invariant getRegimeAlimentaireChasse() != null;
 * @invariant !getRegimeAlimentaireChasse().isEmpty();
 * 
 * @author Marc Champesme
 * @since 15/11/2019
 * @version 30/11/2019
 */
public interface Chasseur extends Animal {

	/**
	 * Choisit une proie dans la pièce spécifiée. Le choix se fait parmi les animaux
	 * dont cet animal chasseur est prédateur.
	 * 
	 * @param p pièce dans laquelle la proie doit être choisie
	 * @return la proie choisie ou null.
	 * 
	 * @throws NullPointerException si la pièce spécifiée est null
	 * 
	 * @requires p != null;
	 * @ensures (\result != null) ==> this.peutChasser(\result);
	 * @ensures (\result != null) ==> p.contientAnimal(\result);
	 * 
	 * @pure
	 */
	public Animal choisirProie(Piece p);

	/**
	 * Renvoie l'ensemble des espèces animales que cet animal peut chasser.
	 * 
	 * @return l'ensemble des classes d'animaux dont cet animal peut chasser les
	 *         instances
	 * 
	 * @pure
	 */
	public Set<Class<? extends Animal>> getRegimeAlimentaireChasse();

	/**
	 * Renvoie true si cet animal est prédateur de l'animal spécifié.
	 * 
	 * @param ani l'animal dont on souhaite savoir si ce chasseur est prédateur
	 * @return true si cet animal est prédateur de l'animal spécifié, false sinon
	 * 
	 * @ensures (ani == null) ==> !\result;
	 * @ensures (ani != null) ==> (\result <==> getRegimeAlimentaireChasse().contains(ani.getClass()));
	 * 
	 * @pure
	 */
	public boolean peutChasser(Animal ani);

	/**
	 * Choisit une proie dans la pièce spécifiée, la tue et la mange. La proie
	 * choisie est l'animal renvoyé par choisirProie. Dans le cas où la méthode
	 * choisirProie renvoie une valeur non null, une fois la proie tuée, le capital
	 * vie de cet animal est diminué du coût de la chasse (cf. getCoutChasse()), il
	 * est alors possible que cet animal meurt et dans ce cas la proie reste morte
	 * dans la pièce et pourra être manger plus tard par un autre prédateur. Dans le
	 * cas où la proie choisie était déjà morte, le captial vie de cet animal n'est
	 * pas diminué du coût de la chasse.
	 * 
	 * @param p la piece dans laquelle cet animal chasse
	 * @return la proie qui a été mangée ou null si aucune proie n'a pu être choisie
	 *         dans la pièce spécifiée
	 * 
	 * @throws NullPointerException  si la pièce spécifiée est null
	 * @throws IllegalStateException si cet animal est mort
	 * 
	 * @requires this.estVivant();
	 * @requires p != null;
	 * @ensures \result == \old(choisirProie(p));
	 * @ensures (\result != null) ==> !\result.estVivant();
	 * @ensures (\result != null) ==> peutChasser(\result);
	 * @ensures ((\result != null) && !estVivant()) ==> p.contientAnimal(\result);
	 * @ensures (\result != null) && (estVivant() && \old(choisirProie(p).estVivant()) 
	 * 		==> (getCapitalVie() 
	 * 			== Math.min(\old(getCapitalVie() + choisirProie(p).getValeurNutritive() - getCoutChasse()),
	 * 						getMaxCapitalVie()));
	 * @ensures (\result != null) && (estVivant() && !\old(choisirProie(p).estVivant()) 
	 * 		==> (getCapitalVie() 
	 * 			== Math.min(\old(getCapitalVie() + choisirProie(p).getValeurNutritive()), 
	 * 						getMaxCapitalVie()));
	 * @ensures (this.estVivant()) ==> !p.contientAnimal(\result);
	 * @ensures ((\result != null) && this.estVivant()) 
	 * 		==> (p.getNbObjets() == \old(p.getNbObjets() + choisirProie(p).getNbAliments()));
	 * @ensures ((\result != null) && !this.estVivant()) 
	 * 		==> (p.getNbObjets() == \old(p.getNbObjets() + getNbAliments() + choisirProie(p).getNbAliments()));
	 */
	public Animal chasser(Piece p);

	/**
	 * Renvoie le coût de la chasse pour cet animal. La valeur renvoyée est le
	 * nombre de points de vie dont le capital vie de cet animal sera diminué à chaque
	 * action de chasse au cours de laquelle il tue un animal.
	 * 
	 * @return le coût de la chasse pour cet animal
	 * 
	 * @pure
	 */
	public int getCoutChasse();

}
package operateurs;

import stockage.Nuplet;

public interface Restriction {
	
	public Nuplet[] egalite(Nuplet[] t, int att, Object v); // Retourne les Nuplets de la table t dont l'attribut att est �gal � la valeur v
	public Nuplet[] superieur(Nuplet[] t, int att, Object v);// Retourne les Nuplets de la table t dont l'attribut att est sup�rieur ou �gal � la valeur v
	public Nuplet[] inferieur(Nuplet[] t, int att, Object v);// Retourne les Nuplets de la table t dont l'attribut att est inferieur ou �gal � la valeur v

}

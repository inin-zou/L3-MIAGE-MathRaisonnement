package graphe;

import java.util.ArrayList;


/**
 * Classe Noeud.
 */
public class Noeud
{
	/*------------*/
	/* Propriétés */
	/*------------*/

	/*----- Nom du noeud -----*/
	private String nom = null;

	/*----- Liste des suivants / successeurs -----*/
	private final ArrayList<Noeud> suivants = new ArrayList<>();

	/*----- Liste des valeurs dans le cas d'un graphe valué -----*/
	private final ArrayList<Double> valeurs = new ArrayList<>();


	/*---------------*/
	/* Constructeurs */
	/*---------------*/

	public Noeud (String e) { this.nom = e; }


	/*----------*/
	/* Méthodes */
	/*----------*/

	/**
	 * Retourne le nom du noeud 'this'.
	 */
	public String getNom () { return this.nom; }


	/**
	 * Retourne le nombre de noeuds suivants à partir du noeud 'this'.
	 */
	public int nbrSuivants () { return this.suivants.size(); }


	/**
	 * Retourne true si le noeud 'nom' est un noeud suivant
	 * à partir du noeud 'this', false sinon.
	 */
	public boolean aLeSuivant (String nom)
		{
		int i = 0;
		while (i < this.suivants.size() && !this.suivants.get(i).nom.equals(nom)) i++;

		return (i != this.suivants.size());
		}

	public boolean aLeSuivant (Noeud n)
		{
		return this.suivants.contains(n);
		}


	/**
	 * Retourne le ième noeud suivant du noeud 'this'.
	 */
	public Noeud getNoeudSuivant (int i) { return this.suivants.get(i); }


	/**
	 * Retourne la position du noeud suivant 'n' du noeud 'this'.
	 */
	public int getPositionNoeudSuivant (Noeud n) { return this.suivants.indexOf(n); }


	/**
	 * Retourne la valeur de l'arc (ou arête) en position 'i'.
	 */
	public double getValeur (int i) { return this.valeurs.get(i); }


	/**
	 * Retourne la valeur de l'arc (ou arête) en fonction du noeud suivant 'n'.
	 */
	public double getValeur (Noeud n) { return this.valeurs.get(this.getPositionNoeudSuivant(n)); }


	/**
	 * Ajoute un noeud suivant à partir du noeud 'this'.
	 */
	public boolean addNoeudSuivant (Noeud noeud)
		{
		/*
		 * On vérifie si l'un des noeuds suivants du noeud 'this' n'a pas
		 * le même nom que 'noeud'.
		 */
		if (this.aLeSuivant(noeud.nom))
			return false;
		else
			{
			this.suivants.add(noeud);

			return true;
			}
		}


	public boolean addNoeudSuivant (Noeud noeud, double valeur)
		{
		/*
		 * On vérifie si l'un des noeuds suivants du noeud 'this' n'a pas
		 * le même nom que 'noeud'.
		 */
		if (this.aLeSuivant(noeud.nom))
			return false;
		else
			{
			this.suivants.add(noeud);
			this.valeurs.add(valeur);

			return true;
			}
		}

	
	/**
	 * Méthode equals.
	 */
	@Override
	public boolean equals (Object obj)
		{
		if (obj == null) return false;

		if (this.getClass() != obj.getClass()) return false;

		final Noeud other = (Noeud) obj;

		return !((this.nom == null) ? (other.nom != null) : !this.nom.equals(other.nom));
		}

	@Override
	public int hashCode ()
		{
		int hash = 3;
		hash = 29 * hash + (this.nom != null ? this.nom.hashCode() : 0);
		return hash;
		}
	
	public String toString()
	{
	StringBuilder sb = new StringBuilder(this.getNom());
	return sb.toString();
	}


} /*----- Fin de la classe Noeud -----*/

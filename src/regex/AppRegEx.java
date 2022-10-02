package regex;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppRegEx
{
	/*------------*/
	/* Propriétés */
	/*------------*/

	/*----- Fichier contenant les logs (50 000 lignes, 1 log / ligne) d'un site commercial -----*/
	private static final String LOG = "data/logs.txt";


	/*----------*/
	/* Méthodes */
	/*----------*/

	/**
	 * Affiche les versions de Windows NT à l'origine de la requête HTTP.
	 */
	public static void WindowsNT ()
		{
		try (Scanner scanner = new Scanner(new FileInputStream(LOG)))
			{
			// Lecture ligne par ligne du fichier.
			while(scanner.hasNextLine())
				{
				String line = scanner.nextLine();
//				System.out.println(line);
	
				// Création du pattern à chercher.
				Pattern pattern = Pattern.compile("Windows NT \\d\\.\\d;");
				Matcher matcher = pattern.matcher(line);
	
				String ch;
				while (matcher.find())
					{
					// Retourne la chaine captée par le pattern.
					ch = matcher.group();

					// Affiche la chaine.
					System.out.println(ch);
					}
				}
	
			scanner.close(); 
			}
		catch (FileNotFoundException ex)
			{
			System.err.println("Erreur lors de la lecture du fichier : " + LOG + " - " + ex.getMessage());
			}
		}
	
	/**
	 * Ajoutez une méthode permettant d’extraire et d’afficher l’heure (au format 13:56) de chaque ligne. 
	 * Après cette extraction, nous pourrions, par exemple, calculer le nombre de requêtes tous les quarts d’heure 
	 * afin de connaitre la répartition de l’affluence du site dans la journée.
	 */
	public static void Heures ()
		{
		try (Scanner scanner = new Scanner(new FileInputStream(LOG)))
			{
			// Lecture ligne par ligne du fichier.
			while(scanner.hasNextLine())
				{
				String line = scanner.nextLine();
//				System.out.println(line);
	
				// Création du pattern à chercher.
				Pattern pattern = Pattern.compile("[0-9]{1,2}:[0-9]{1,2}");
				Matcher matcher = pattern.matcher(line);
				
				String ch;
				while (matcher.find())
					{
					// Retourne la chaine captée par le pattern.
					ch = matcher.group();

					// Affiche la chaine.
					System.out.println(ch);
					}
				}
			scanner.close(); 
			}
		catch (FileNotFoundException ex)
			{
			System.err.println("Erreur lors de la lecture du fichier : " + LOG + " - " + ex.getMessage());
			}
		}
		
		// Ajoutez une méthode permettant d’extraire le nom des « department » visités à chaque ligne.
		public static void NomDept ()
		{
		try (Scanner scanner = new Scanner(new FileInputStream(LOG)))
			{
			// Lecture ligne par ligne du fichier.
			while(scanner.hasNextLine())
				{
				String line = scanner.nextLine();
	//			System.out.println(line);
	
				// Création du pattern à chercher.
				Pattern pattern = Pattern.compile("department/[a-z]+");
				Matcher matcher = pattern.matcher(line);
				
				String ch;
				while (matcher.find())
					{
					// Retourne la chaine captée par le pattern.
					ch = matcher.group();
	
					// Affiche la chaine.
					System.out.println(ch);
					}
				}
			scanner.close(); 
			}
		catch (FileNotFoundException ex)
			{
			System.err.println("Erreur lors de la lecture du fichier : " + LOG + " - " + ex.getMessage());
			}
		}
		
		// Ajoutez une méthode permettant d’extraire les adresses IP de chaque ligne.
				public static void IP ()
				{
				try (Scanner scanner = new Scanner(new FileInputStream(LOG)))
					{
					// Lecture ligne par ligne du fichier.
					while(scanner.hasNextLine())
						{
						String line = scanner.nextLine();
			//			System.out.println(line);
			
						// Création du pattern à chercher.
						Pattern pattern = Pattern.compile("((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}");
						Matcher matcher = pattern.matcher(line);
						
						String ch;
						while (matcher.find())
							{
							// Retourne la chaine captée par le pattern.
							ch = matcher.group();
			
							// Affiche la chaine.
							System.out.println(ch);
							}
						}
					scanner.close(); 
					}
				catch (FileNotFoundException ex)
					{
					System.err.println("Erreur lors de la lecture du fichier : " + LOG + " - " + ex.getMessage());
					}
				}
		
				public static int ChoixMode() {
					int codeMode = 0;
					Scanner sc = new Scanner(System.in);
					System.out.println("Choix d'exercise (options : ex1/ex2/ex3/ex4)" );
					String Mode = sc.nextLine().toLowerCase();
					while (codeMode == 0) {
						switch (Mode) {
						case "ex1" : codeMode = 1; break;
						case "ex2" : codeMode = 2; break;
						case "ex3" : codeMode = 3; break;
						case "ex4" : codeMode = 4; break;
						default : {
							System.out.println("Veuillez saisir une mode valide.");
							System.out.println();
							sc = new Scanner(System.in);
							System.out.println("Choix de mode (options : ex1/ex2/ex3/ex4)" );
							Mode = sc.nextLine().toLowerCase();
							break;
							}
						}
					}
					return codeMode;
				}
		

	/*---------------------*/
	/* Programme principal */
	/*---------------------*/

	public static void main(String[] args)
		{
		System.out.println("Vous voulez essayer sur laquelle exercise?");
		int codeMode = ChoixMode();
		switch (codeMode){
		case 1 : AppRegEx.WindowsNT(); break;
		case 2 : AppRegEx.Heures(); break;
		case 3 : AppRegEx.NomDept(); break;
		case 4 : AppRegEx.IP(); break;
		default : AppRegEx.WindowsNT(); break;
		}
		
		}

} /*----- Fin de la classe AppRegEx -----*/

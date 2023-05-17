import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Cette classe contient la methode executerCourse devant etre utilisee dans le cadre
 * du TP1 de INF1120 - Automne 2022.
 * 
 * @author Melanie Lord
 * @version Automne 2022
 */
public class TP1Utils {
   
   //Plus ce nombre est grand, plus la course est lente.
   //Vous pouvez le changer pour 0 pour afficher le deroulement de la course plus rapidement
   private final static int DUREE = 150; 
   
   //Les noms et numeros des chevaux
   public final static String NOM1 = "Gaspard"; //cheval no 1
   public final static String NOM2 = "Bubulle"; //cheval no 2
   public final static String NOM3 = "Babette"; //cheval no 3
   public final static String NOM4 = "Socrate"; //cheval no 4
   public final static String NOM5 = "Romarin"; //cheval no 5
   public final static String NOM6 = "Canelle"; //cheval no 6
   private final static String[] CHEVAUX = {NOM1, NOM2, NOM3, NOM4, NOM5, NOM6};
   
   //Messages divers
   private final static String MSG_DEPART = "A VOS MARQUES, PRET, PARTEZ !!!\n";
   private final static String CLASSEMENT_ARRIVEE = "\nCLASSEMENT A l'ARRIVEE : ";
   
   
   /**
    * Cette methode affiche le deroulement de la course, puis le classement final
    * et retourne le classement final sous forme d'un nombre entier de 6 chiffres.
    * Par exemple, si le nombre retourne est 561432, cela signifie que le cheval
    * numero 5 est en tête, suivi du cheval numero 6 en deuxième position, suivi 
    * des chevaux 1, 4, 3, et 2 en dernière position.
    * 
    * @return le classement des 6 chevaux dans la course sous la forme d'un nombre
    * entier de 6 chiffres.
    */
   public static int executerCourse() {
      List<Double> positions = new ArrayList<>();
      Collections.addAll(positions, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
      double num;
      int classement = 0;
      
      System.out.println(MSG_DEPART);
      
      while (max(positions) < 5) {

         for (int i = 0 ; i < 6 ; i++) {
            num = Math.random();
            while (positions.contains((double)positions.get(i) + num)) {
               num = Math.random();
            }
            positions.set(i, (double)positions.get(i) + num);
            try {
               Thread.sleep(DUREE); //Afficher la course plus ou moins vide
            } catch (Exception e) {
               //ne rien faire
            }
         }
         
         //Afficher les positions
         if (max(positions) < 5) {
            //course en cours
            classement = afficherPositionsChevaux(copie(positions), false);
         } else {
            //course termine
            System.out.println(CLASSEMENT_ARRIVEE);
            classement = afficherPositionsChevaux(copie(positions), true);
         }
      }
      
      return classement;
   }

   //--------------------------------------
   //METHODES PRIVEES
   //--------------------------------------
   
   /**
    * Retourne une copie de liste.
    * @param liste la liste a copier.
    * @return une copie de liste.
    */
   private static List copie (List liste) {
      List copie = new ArrayList(liste);
      return copie;
   }
   
   /**
    * Affiche la position des chevaux a divers moments dans la course, et affiche
    * le classement final a l'arrivee.
    * @param positions la liste des positions des chevaux
    * @param finCourse true si l'on affiche le classement a l'arrivee, false 
    *                  si l'on affiche le classement temporaire a un moment dans
    *                  la course.
    * @return le classement des chevaux
    */
   private static int afficherPositionsChevaux(List<Double> positions, boolean finCourse) {
      List<Integer> posChevaux = new ArrayList<>();
      int indice;
      String chevaux = "";
      String s;
      
      for (int j = 0 ; j < 6 ; j++) {
         indice = indiceMax(positions);
         posChevaux.add(indice + 1);
         positions.set(indice, -1.0);
      }
      
      if (!finCourse) {
         chevaux = "EN TETE :  ";
         for (int i = 0 ; i < posChevaux.size() ; i++) {
            s = String.format("%-8s <- ", CHEVAUX[posChevaux.get(i) - 1]);
            chevaux = chevaux + s;
         }

         System.out.println(chevaux.substring(0, chevaux.length() - 4));
      
      } else {
         chevaux = "";
         for (int i = 0 ; i < posChevaux.size() ; i++) {
            s = "  #" + (i + 1) + " : " + CHEVAUX[posChevaux.get(i) - 1] + "\n";
            chevaux = chevaux + s;
         }

         System.out.println(chevaux.substring(0, chevaux.length() - 1));
      }
      
      return concat(posChevaux);
   }
      
   /**
    * Concatene les entier contenu dans liste, et retourne le resultat sous forme
    * d'un int.
    * @param liste la liste contenant les entiers a concatener
    * @return le nombre entier forme par la concatenation des entiers contenus dans liste.
    */
   private static int concat(List<Integer> liste) {
      int mult = 1;
      int nbr = 0;
      
      for (int i = liste.size() - 1 ; i >= 0 ; i--) {
         nbr = nbr + liste.get(i) * mult;
         mult = mult * 10;
      }
      return nbr;
   }
   
   /**
    * Retourne le nombre maximum dans la liste donnee.
    * @param liste la liste dans laquelle rechercher le nombre maximum
    * @return le nombre maximum dans la liste donnee.
    */
   private static double max (List<Double> liste) {
      double max = -1;
      for (Double elt : liste) {
         if (elt > max)
            max = elt;
      }
      return max;
   }
   
   /**
    * Retourne l'indice du nombre maximum dans la liste donnee.
    * @param liste la liste dans laquelle rechercher le nombre maximum.
    * @return l'indice du nombre maximum dans la liste donnee
    */
   private static int indiceMax (List<Double> liste) {
      int indiceMax = 0;
      for (int i = 1 ; i < liste.size() ; i++) {
         if (liste.get(i) > liste.get(indiceMax))
            indiceMax = i;
      }
      return indiceMax;
   }
         
}

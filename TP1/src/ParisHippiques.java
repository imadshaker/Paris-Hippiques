/**
 * Cette classe représente une simulation des paris hippiques
 * @author Imad Shaker Bouarfa
 * Code permanent: BOUI24039303
 * Courriel: bouarfa.imad@courrier.uqam.ca
 * Cours: INF1120-20
 * @version 2022-10-23
 */
public class ParisHippiques{
    /**
     * Méthode main qui contient toute la logique du TP
     * @param args paramétre par défaut de la méthode main
     */
    public static void main(String [] args){
        final String PRESENTATION = "Ceci est un programme qui vous permet de placer des paris sur des courses hippiques virtuelles. \n";
        final String BANQUEVIDE = "Votre banque est vide. \nPour continuer, entrez un montant a mettre en banque (0 pour quitter) : ";
        final float MONTANT_MIN = 0;
        final String MENU_PRINCIPAL = "\n\nMENU\n"

                + "1. Placer un pari\n2. Gerer la banque\n3. Quitter"
                + "\n\nEntrez votre choix (1, 2 ou 3) : ";
        final String MSG_FIN = "AUREVOIR!";
        final String MSG_ERR_MENU = "\nErreur, entrez un choix entre 1 et 3! Recommencez...";
        final String MENU_PARI = "--------------\nPLACER UN PARI\n--------------\n\nType de pari\n1. Pari simple gagnant\n2. Pari simple placé\n3. Pari couple gagnant ordonne\n4. Pari couple gagnant non ordonne\n5. Revenir au menu principal\n\n Entrez le type de pari";
        final String MSG_ERR_MENUPARI = "\nErreur, entrez un choix entre 1 et 5! Recommencez...";
        final String LISTE_CHEVAUX = "\nChevaux\n1. Gaspard\n2. Bubulle\n3. Babette\n4. Socrate\n5. Romarin\n6. Canelle\nEntrez le numero du premier cheval :";
        final String LISTE_CHEVAUX2 = "\nChevaux\n1. Gaspard\n2. Bubulle\n3. Babette\n4. Socrate\n5. Romarin\n6. Canelle\nEntrez le numero du deuxième cheval :";
        final String MSG_ERR_CHOIXCHEVAUX = "\nErreur, entrez un choix entre 1 et 6! Recommencez...";
        final String MSG_MISE = "Entrez le montant de la mise (0 pour annuler) :";
        final String MSG_ANNULEMISE = "----> OPERATION ANNULEE <----\n";
        final String MSG_PRESSENTRE = "Appuyez sur <ENTREE> pour revenir au menu principal... ";
        char choixMenu;
        char choixPari;
        int choixCheval;
        int choixCheval2;
        float miseMontant;
        int classement;
        float gainCumule = 0;
        char actionBanque ='x';
        float montantAjoute;


        System.out.println(PRESENTATION);
        System.out.println(BANQUEVIDE);

        float montantEntré = Clavier.lireFloat();


        while (montantEntré < MONTANT_MIN){
            System.out.println("\nErreur, le montant doit etre plus grand ou egal a 0! Recommencez...\n");
            System.out.println(BANQUEVIDE);
            montantEntré = Clavier.lireFloat();
        }


        if (montantEntré > MONTANT_MIN){
            do {
                System.out.println(MENU_PRINCIPAL);

                choixMenu = Clavier.lireCharLn();
                while (choixMenu < '1' || choixMenu > '3'){
                    System.out.println(MSG_ERR_MENU + MENU_PRINCIPAL);
                    choixMenu = Clavier.lireCharLn();
                }

                switch (choixMenu){
                    //TRAITEMENT OPTION 1 PLACER UN PARI
                    case '1':
                        System.out.println(MENU_PARI);
                        choixPari = Clavier.lireCharLn();
                        while (choixPari < '1' || choixPari > '5'){
                            System.out.println(MSG_ERR_MENUPARI);
                            choixPari = Clavier.lireCharLn();
                        }
                        //TRAITEMENT PARI SIMPLE GAGNANT ET PARI SIMPLE PLACÉ
                        if (choixPari == '1' || choixPari == '2') {
                            System.out.println(LISTE_CHEVAUX);
                            choixCheval = Clavier.lireIntLn();
                            while (choixCheval < 1 || choixCheval > 6) {
                                System.out.println(MSG_ERR_CHOIXCHEVAUX);
                                choixCheval = Clavier.lireIntLn();
                            }//MISE DOIT ÊTRE ENTRE 0 ET LE MONTANT ENTRÉ
                            System.out.println(MSG_MISE);
                            miseMontant = Clavier.lireFloatLn();
                            while (miseMontant > montantEntré || miseMontant < MONTANT_MIN) {
                                System.out.println("Erreur, la mise doit etre entre %.2f$" + MONTANT_MIN + "$ et " + montantEntré + "$! Recommencez...");
                                System.out.println(MSG_MISE);
                                miseMontant = Clavier.lireFloatLn();
                            }
                            if (miseMontant > MONTANT_MIN) {
                                gainCumule -= miseMontant;
                                montantEntré -= miseMontant;
                                classement = TP1Utils.executerCourse();
                                if (choixCheval == classement / 10000) {
                                    if (choixPari == '1') {
                                        gainCumule += 3 * miseMontant;
                                        System.out.println("BRAVO ! Vous avez gagné " + 3 * miseMontant);
                                        montantEntré += miseMontant * 3;
                                    } else if (choixPari == '2') {
                                        gainCumule += 2 * miseMontant;
                                        System.out.println("BRAVO ! Vous avez gagné %.2f$" + 2 * miseMontant);
                                        montantEntré += miseMontant * 2;
                                    }
                                } else {
                                    System.out.println("DESOLE ! Vous avez perdu votre pari.\n");
                                }
                                if (gainCumule >= 0) {
                                    System.out.println("GAIN CUMULE     :" + gainCumule + "$");
                                } else {
                                    System.out.printf("PERTE CUMULE     : %.2f$", (-gainCumule), "$");
                                }
                                System.out.printf("\nBANQUE          : %.2f$", montantEntré, "$");

                            } else {
                                System.out.println(MSG_ANNULEMISE);
                                Clavier.lireFinLigne();
                            }


                        } else if (choixPari == '3' || choixPari == '4') {
                            //AFFICHE Pari couple gagnant ordonne/ Choix = 2 chevaux
                            System.out.println(LISTE_CHEVAUX);
                            choixCheval = Clavier.lireIntLn();
                            while (choixCheval < 1 || choixCheval > 6) {
                                System.out.println(MSG_ERR_CHOIXCHEVAUX);
                                choixCheval = Clavier.lireIntLn();
                            }

                            System.out.println(LISTE_CHEVAUX2);
                            choixCheval2 = Clavier.lireIntLn();
                            while (choixCheval2 < 1 || choixCheval2 > 6) {
                                System.out.println(MSG_ERR_CHOIXCHEVAUX);
                                choixCheval2 = Clavier.lireIntLn();
                            }

                            System.out.println(MSG_MISE);
                            miseMontant = Clavier.lireFloatLn();
                            while (miseMontant > montantEntré || miseMontant < MONTANT_MIN) {
                                System.out.printf("Erreur, la mise doit etre entre %.2f$ et %.2f$! Recommencez... ", MONTANT_MIN, montantEntré);
                                System.out.println(MSG_MISE);
                                miseMontant = Clavier.lireFloatLn();
                            }
                            if (miseMontant > MONTANT_MIN) {
                                gainCumule -= miseMontant;
                                montantEntré -= miseMontant;
                                classement = TP1Utils.executerCourse();
                                if (choixPari == '3') {
                                    if (choixCheval == classement / 10000 && choixCheval2 == classement / 10000 % 10) {
                                        gainCumule += 3.5 * miseMontant;
                                        System.out.println("BRAVO ! Vous avez gagné " + 3.5 * miseMontant);
                                        montantEntré += miseMontant * 3.5;
                                    } else {
                                        System.out.println("DESOLE ! Vous avez perdu votre pari.\n");
                                    }
                                } else if (choixPari == '4') {
                                    if ((choixCheval == classement / 10000 && choixCheval2 == classement / 10000 % 10)
                                            || (choixCheval2 == classement / 10000 && choixCheval == classement / 10000 % 10)) {
                                        gainCumule += 2.5 * miseMontant;
                                        System.out.println("BRAVO ! Vous avez gagné " + 2.5 * miseMontant);
                                        montantEntré += miseMontant * 2.5;
                                    } else {
                                        System.out.println("DESOLE ! Vous avez perdu votre pari.\n");
                                    }
                                }
                                if (gainCumule >= 0) {
                                    System.out.println("GAIN CUMULE     : " + gainCumule + "$");
                                } else {
                                    System.out.printf("PERTE CUMULE     : %.2f$", (-gainCumule), "$");
                                }
                                System.out.printf("\nBANQUE          : %.2f$", montantEntré, "$");
                            } else {
                                System.out.println(MSG_ANNULEMISE);
                                Clavier.lireFinLigne();
                            }
                        } else if (choixPari == '5'){
                            break;
                        }

                        System.out.print("\n" + MSG_PRESSENTRE);
                        Clavier.lireFinLigne();

                        if (montantEntré == MONTANT_MIN){
                            System.out.println(BANQUEVIDE);
                            montantEntré = Clavier.lireFloatLn();
                            while (montantEntré< MONTANT_MIN){
                                System.out.println("\nErreur, le montant doit etre plus grand ou egal a 0! Recommencez...\n");
                                System.out.println(BANQUEVIDE);
                                montantEntré = Clavier.lireFloatLn();
                            }
                        }

                        break;
                    case '2':
                        //Option 2 du menu -Gerer la banque-
                        System.out.println("---------------\nGERER LA BANQUE\n---------------\n\n");
                        do {
                            System.out.printf("**Montant en banque : %.2f$" + " **\n\n" +
                                    "(A)jouter, (V)ider, ou (R)evenir au menu principal : ", montantEntré);
                            actionBanque = Clavier.lireCharLn();

                            while (actionBanque != 'a' && actionBanque != 'A'
                                    && actionBanque != 'v' && actionBanque != 'V'
                                    && actionBanque != 'r' && actionBanque != 'R'){
                                System.out.println("Erreur, entrez A, V ou R! Recommencez...\n\n** Montant en banque : "
                                        + montantEntré + "$ **\n\n(A)jouter, (V)ider, ou (R)evenir au menu principal :");
                                actionBanque = Clavier.lireCharLn();
                            }

                            if(actionBanque == 'a' || actionBanque == 'A'){
                                System.out.println("Entrez le montant a ajouter (0 pour annuler) :");
                                montantAjoute = Clavier.lireFloatLn();

                                while (montantAjoute<0){
                                    System.out.println("\nErreur, le montant doit etre plus grand ou egal a 0! Recommencez...\n");
                                    System.out.println("Entrez le montant a ajouter (0 pour annuler) :");
                                    montantAjoute = Clavier.lireFloatLn();
                                }

                                if (montantAjoute>0){
                                    montantEntré += montantAjoute;
                                }
                            } else if (actionBanque == 'v' || actionBanque == 'V'){
                                montantEntré = 0;
                            }

                        } while (actionBanque != 'r' && actionBanque != 'R' && actionBanque != 'v' && actionBanque != 'V');

                        break;
                    case '3':
                        //rien a ajouter, c'est fini. Ceci est l'option 3 du menu qui quitte le programme.
                        break;
                }


            } while (choixMenu != '3' && montantEntré > MONTANT_MIN && actionBanque != 'v' && actionBanque != 'V');

        }

        System.out.println(MSG_FIN);

    }
}

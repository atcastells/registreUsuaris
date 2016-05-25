import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Random;
import java.io.PrintStream;

public class Principal {
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.inici();
    }

    void inici(){

        /*Instanciem Objectes*/
        Biblioteca gui = new Biblioteca();

        /*Definicio de variables*/
        ArrayList<Usuari> llistaUsuaris= new ArrayList<Usuari>();  //ArrayList per a la agenda
        /*Comparador ordenar per nom*/
        Comparator<Usuari> ordenarPerNom = new Comparator<Usuari>() { //ordenem la llista de contactes
            public int compare(Usuari u1, Usuari u2) {
                String name1 = (u1.getNom() + " " + u1.getCognom()).trim();
                String name2 = (u2.getNom() + " " + u2.getCognom()).trim();
                return name1.compareToIgnoreCase(name2);
            }
        };
        /*Definim el fitxer*/
        File agenda = new File("Agenda.txt");

        /*Si hi han dades les carreguem*/
        carregarDades(agenda,llistaUsuaris);
        llistaUsuaris.sort(ordenarPerNom);

        /*************************************************************************/
    /*···························· MENU PRINCIPAL ···························*/
        /*************************************************************************/

        /*Inicialitzem parametres*/
        int opcio = 0; //opció que escollira l'usuari en el menu
        int opcio_submenu = 0; //opcio per al submenu
        int opcio_submenu_llistat = 0; //??¿?¿

        /*Inici del menu*/
        while (opcio<menu_principal.length){
            gui.titol(); //imprimim titol per a la decoracio del programa
            gui.imprimir(gui.funcioMenu(menu_principal)); //gui.decoracio_menus(menu_principal.length, menu_principal);
            opcio = gui.readInt("\nTrieu una opcio: ");
            switch(opcio){

                case 1: //Registrar usuari
                    afegirContacte(llistaUsuaris);
                    gui.imprimir("\n\nContacte guardat!\n");
                    //resum de dades
                    llistaUsuaris.sort(ordenarPerNom);
                    gui.continuar();
                    break;

                case 2: //Consultar usuari
                    mostrarContacte(llistaUsuaris);
                    gui.continuar();
                    break;

                case 3: //Menu eliminar
                    opcio_submenu = 0;
                    while(opcio_submenu<menu_eliminar.length){
                        gui.imprimir("\n");
                        gui.imprimir(gui.funcioMenu(menu_eliminar)); //gui.decoracio_menus(menu_eliminar.length, menu_eliminar);
                        opcio_submenu = gui.readInt("\nTrieu una opcio: ");
                        switch(opcio_submenu){
                            case 1: //donar de baixa un contacte de l'agenda
                                gui.funcioTaula(columnesUsuari,llistaToArrayAlta(llistaUsuaris)); //mostrem la llista de contactes
                                gui.imprimir("\nQuin usuari vols donar de baixa?");
                                int posicio = Biblioteca.readInt("\nNº: ") -1;
                                donarDeBaixa(posicio, llistaUsuaris);
                                gui.continuar();
                                break;

                            case 2: //eliminar tots els contactes amb baixa
                                borrarContactesBaixa(llistaUsuaris);
                                gui.imprimir("\n\n");
                                gui.continuar();
                                break;
                        }
                    }
                    break;

                case 4: //Menu llistar
                    opcio_submenu_llistat = 0;
                    while(opcio_submenu_llistat<menu_llistat.length){
                        gui.imprimir("\n");
                        gui.imprimir(gui.funcioMenu(menu_llistat)); //gui.decoracio_menus(menu_llistat.length, menu_llistat);
                        opcio_submenu_llistat = gui.readInt("\nTrieu una opcio: ");
                        switch(opcio_submenu_llistat){

                            case 1: //mostrar contactes d'alta en l'agenda
                                if (llistaToArrayAlta(llistaUsuaris).length == 0){
                                    gui.imprimir("No hi ha dades per mostrar");
                                }
                                else{
                                    gui.imprimir("\n");
                                    gui.funcioTaula(columnesUsuari,llistaToArrayAlta(llistaUsuaris));
                                    gui.imprimir("\n");
                                    String[][] estadistiques = {
                                            {llistaToArrayAlta(llistaUsuaris).length+""}
                                    };
                                    gui.imprimir("Total usuaris: "+llistaToArrayAlta(llistaUsuaris).length);
                                }
                                gui.imprimir("\n\n");
                                gui.continuar();
                                break;

                            case 2: //mostrar contactes de baixa en l'agenda
                                if (llistaToArrayBaixa(llistaUsuaris).length == 0){
                                    gui.imprimir("\nNo hi ha dades per mostrar");
                                }
                                else{
                                    gui.funcioTaula(columnesUsuari,llistaToArrayBaixa(llistaUsuaris));
                                    gui.imprimir("Total usuaris: "+llistaToArrayBaixa(llistaUsuaris).length);
                                }
                                gui.imprimir("\n\n");
                                gui.continuar();
                                break;
                        }
                    }
            }
            //
        }
        guardarDades(llistaUsuaris, agenda);
        System.out.println("** Fi del programa **");
    }

    /*************************************************************************/
    /*·························ESCRIPTURA AL FITXER ·························*/
    /*************************************************************************/

                        /* GUARDEM DADES AL FITXER */

    public void guardarDades (ArrayList<Usuari> agenda, File arxiu) {
        try {

            PrintStream escriptor = new PrintStream(arxiu); //S'intenta obrir el fitxer de sortida

            /*Definicio de variables*/
            String dades = ""; //Strign que utilitzarem per guardar les dades de l'usuari

            /* Ordre de les dades: Nom Usuari, Nom, Cognom, Correu, Contrasenya i Baixa */

            //Si s'executa aquesta instrucció, s'ha obert el fitxer
            for (int i = 0; i < agenda.size(); i++) {
                dades = agenda.get(i).getUsuari()+";";
                dades += agenda.get(i).getNom()+";";
                dades += agenda.get(i).getCognom()+";";
                dades += agenda.get(i).getCorreu()+";";
                dades += agenda.get(i).getContrasenya()+";";
                if (agenda.get(i).getBaixa() == false){
                    dades += "FALSE;";
                }
                else{
                    dades += "TRUE;";
                }
                escriptor.println(dades); //guardem les DADES al fitxer
            }

            //Cal tancar el fitxer
            escriptor.close();
        }
        catch (Exception e) {
            //Excepció!
            System.out.println("Error: " + e);
        }
    }

                            /*FUNCIONS AUX PRINCIPAL*/

    boolean comprovarFitxer (File arxiu){
        if(arxiu.exists()){
            return true;
        }
        else {
            return false;
        }
    }

    void carregarDades (File arxiu,ArrayList<Usuari> agenda){
        String linia = ""; //inicialitzem la variable linia
        System.out.print(arxiu.getAbsolutePath()+"\n"); //mostrem la ruta absoluta de l'arxiu5
        try {
            Scanner sc = new Scanner(arxiu); //inicialitzem l'scanner
            while (sc.hasNextLine()){ //mentre hi hagi alguna per llegir
                linia = sc.nextLine();
                String[] dadesLinia = linia.split(";"); //cada cop que la linia sera un espai dividirem la cadena
                if(!(dadesLinia[0] == null)){ //si en la primera posicio de cada linia del fitxer hi ha text
                    afegirUsuari(dadesLinia,agenda); //afegim un usuari nou amb les seves dades
                }
            }
        }
        catch (FileNotFoundException e){
        }
    }

    void afegirUsuari(String[] dades, ArrayList<Usuari> agenda){
        /* Quan afegirem un usuari, guardarem les dades de forma ordenada en l'ArrayList */
        /* Ordre de les dades: Nom Usuari, Nom, Cognom, Correu, Contrasenya i Baixa */

        String nomUsuari = dades[0].toUpperCase(); //Nom Usuari posicio 0
        String nom = dades[1].toUpperCase(); //Nom posicio 1
        String cognom = dades[2].toUpperCase(); //Cognom posicio 2
        String correu = dades[3].toUpperCase(); //Correu posicio 3
        String contrasenya = dades[4].toUpperCase(); //Contrassenya posicio 4
        Boolean baixa;
        if (dades[5].toUpperCase().equalsIgnoreCase("FALSE")){
            baixa = false;
        }
        else {
            baixa = true;
        }
        agenda.add(new Usuari(nom,cognom,correu,contrasenya,nomUsuari,baixa)); //afegim un nou objecte a l'agenda amb els parametres que hi ha entre parentesi
    }

                                /*Arrays Menus*/

    /*arrays de menus: menu_principal, menu_eliminar, menu_llistat*/
    String[] menu_principal = {
            "Registrar nou usuari",
            "Consultar usuari",
            "Esborrar usuari",
            "Llistar usuaris",
            "Guardar i sortir"
    };
    String[] menu_eliminar = {
            "Donar de baixa","Eliminar definitivament","Atras"
    };
    String[] menu_llistat = {
            "Usuaris d'alta","Usuaris de baixa","Atras"
    };
    /*Arrays columnes*/
    String[] columnesUsuari = {
            "Nº","USUARI","NOM","COGNOM","CORREU"
    };
    String[] columnesUsuariExtended = {
            "USUARI","NOM","COGNOM","CORREU","CONTRASSENYA","BAIXA"
    };
    String[] columnesEstadistiques = {
            "Total usuaris"
    };

                            /*FUNCIONS AUXILIARS*/

    int buscaPosicio(String nomUsuari, ArrayList<Usuari> llista){
        for (int i = 0; i < llista.size(); i++) {
            if(llista.get(i).getUsuari().equalsIgnoreCase(nomUsuari)){
                return i;
            }
        }
        return -1;
    }
    String[][] usuariToArray(ArrayList<Usuari> llista, ArrayList<Integer> pos){
        String[][] dadesUsuaris = new String[pos.size()][7];
        for (int i = 0; i < pos.size(); i++) {
            dadesUsuaris[i][0] = "#"+(i+1);
            dadesUsuaris[i][1] = llista.get(pos.get(i)).getUsuari();
            dadesUsuaris[i][2] = llista.get(pos.get(i)).getNom();
            dadesUsuaris[i][3] = llista.get(pos.get(i)).getCognom();
            dadesUsuaris[i][4] = llista.get(pos.get(i)).getCorreu();
        }
        return dadesUsuaris;
    }
    String[][] usuariToArrayExtended (ArrayList<Usuari> llista, int i){
        String[][] dadesUsuaris = new String[1][6];
        dadesUsuaris[i][0] = llista.get(i).getUsuari();
        dadesUsuaris[i][1] = llista.get(i).getNom();
        dadesUsuaris[i][2] = llista.get(i).getCognom();
        dadesUsuaris[i][3] = llista.get(i).getCorreu();
        dadesUsuaris[i][4] = llista.get(i).getContrasenya();
        if (llista.get(i).getBaixa() == false){
            dadesUsuaris[i][5] = "FALSE";
        }
        else{
            dadesUsuaris[i][5] = "TRUE";
        }
        return dadesUsuaris;
    }
    String[][] llistaToArrayAlta(ArrayList<Usuari> llista){
        /*Creem array per als usuaris de alta*/
        int midaArray = 0;
        for (int i = 0; i < llista.size(); i++) {
            if(llista.get(i).getBaixa() == false){
                midaArray++;
            }
        }
        String[][] dadesUsuaris = new String[midaArray][7];
        int contador = 0;
        for (int i = 0; i < llista.size(); i++) {
            if(llista.get(i).getBaixa() == false){
                dadesUsuaris[contador][0] = "#"+(contador+1);
                dadesUsuaris[contador][1] = llista.get(i).getUsuari();
                dadesUsuaris[contador][2] = llista.get(i).getNom();
                dadesUsuaris[contador][3] = llista.get(i).getCognom();
                dadesUsuaris[contador][4] = llista.get(i).getCorreu();
                contador++;
            }
        }
        return dadesUsuaris;
    }
    String[][] llistaToArrayBaixa(ArrayList<Usuari> llista){
        /*Creem array per als usuaris de alta*/
        int midaArray = 0;
        for (int i = 0; i < llista.size(); i++) {
            if(llista.get(i).getBaixa() == true){
                midaArray++;
            }
        }
        String[][] dadesUsuaris = new String[midaArray][7];
        int contador = 0;
        for (int i = 0; i < llista.size(); i++) {
            if(llista.get(i).getBaixa() == true){
                dadesUsuaris[contador][0] = "#"+(contador+1);
                dadesUsuaris[contador][1] = llista.get(i).getUsuari();
                dadesUsuaris[contador][2] = llista.get(i).getNom();
                dadesUsuaris[contador][3] = llista.get(i).getCognom();
                dadesUsuaris[contador][4] = llista.get(i).getCorreu();
                contador++;
            }
        }
        return dadesUsuaris;
    }

    /*************************************************************************/
    /*·························· INSERIR CONTACTE ···························*/
    /*************************************************************************/

    void afegirContacte(ArrayList<Usuari> agenda){
        /* Ordre de les dades: Nom Usuari, Nom, Cognom, Correu, Contrasenya i Baixa */

        Biblioteca.imprimir("\nNom: ");

        String nom = Biblioteca.llegirString().toUpperCase();
        while (nom.equals("") || nom.contains(";")){
            if(nom.equals(""))
                Biblioteca.imprimir("El camp no pot estar buit!\n");
            if(nom.contains(";"))
                Biblioteca.imprimir("El nom te caracters prohibits ';'!\n");
            Biblioteca.imprimir("\nNom: ");
            nom = Biblioteca.llegirString().toUpperCase();
        }
        Biblioteca.imprimir("Cognom: ");
        String cognom = Biblioteca.llegirString().toUpperCase();
        while (cognom.equals("") || cognom.contains(";")){
            if(cognom.equals(""))
                Biblioteca.imprimir("El camp no pot estar buit!\n");
            if(cognom.contains(";"))
                Biblioteca.imprimir("El cognnom te caracters prohibits ';'!\n");
            Biblioteca.imprimir("\nCognom: ");
            cognom = Biblioteca.llegirString().toUpperCase();
        }
        Biblioteca.imprimir("Correu: ");
        String correu = Biblioteca.llegirString().toUpperCase();
        while (correu.equals("") || !(correu.contains("@")) || !(correu.contains("."))){
            if(correu.equals(""))
                Biblioteca.imprimir("El camp no pot estar buit!\n");
            if(!(correu.contains("@")) || !(correu.contains(".")))
                Biblioteca.imprimir("El correu es incorrecte!\n");
            Biblioteca.imprimir("\nCorreu: ");
            correu = Biblioteca.llegirString().toUpperCase();
        }
        String contrasenya = Usuari.generar_contrasenya();
        Biblioteca.imprimir("Contrassenya: " +contrasenya);
        String nomUsuari = generarNomUsuari(nom, cognom,agenda);
        Biblioteca.imprimir("\nNom d'Usuari: " +nomUsuari);
        agenda.add(new Usuari(nom,cognom,correu,contrasenya,nomUsuari,false)); //afegim un nou objecte a l'agenda amb els parametres que hi ha entre parentesi
    }

    String generarNomUsuari(String nom, String cognom,ArrayList<Usuari> agenda){
        /*Inicialitzem parametres*/
        String nomUsuari = "";
        int numUsuari = 0;
        boolean valid = false;
        /*El nom d'Usuari es la primera lletra del nom i tot el cognom*/
        nomUsuari = nom.charAt(0)+cognom;
        int k = nomUsuari.length()-1;
        /*Comprobem si el nom d'usuari existeix*/
        for (Usuari u:agenda) {
            if (u.getUsuari().equalsIgnoreCase(nomUsuari)) {
                nomUsuari += "1";
                nomUsuari = sumarNomUsuari(nomUsuari,agenda);
                return nomUsuari;
            }
        }
        return nomUsuari;
    }

    String sumarNomUsuari(String nomUsuari,ArrayList<Usuari> agenda){
        int numDigits = 0;
        String nomSenseXifra;
        int xifra = 0;
       /*Contem el num de digits*/
        for (int i = nomUsuari.length()-1; i > 0; i--) {
            char charUsuari = nomUsuari.charAt(i);
            if (Character.isDigit(charUsuari)){
                numDigits++;
            }
        }
       /*Si hi han 0 digits al final del nom*/
        if (numDigits == 0){
            return nomUsuari+1; //Si existeix el mateix nom d'usuari es crea un igual amb un 1 al final
        }
        else {
            /*Separem el nom d'usuari amb la part amb xifra i la part sense xifra*/
            nomSenseXifra = nomUsuari.substring(0,nomUsuari.length()-numDigits);
            xifra = Integer.parseInt(nomUsuari.substring(nomUsuari.length()-numDigits,nomUsuari.length()));
            nomUsuari = nomSenseXifra+xifra;
            xifra = comprobaNomUsuari(nomSenseXifra,xifra,agenda); //Comprobem que la xifra no es repeteix en un altre nom d'usuari
            return nomSenseXifra+xifra;
        }
    }

    int comprobaNomUsuari(String nomSenseXifra,int xifra, ArrayList<Usuari> agenda){
        int xifraUsuari = 0;
        String nomUsuari = nomSenseXifra+xifra;
        for (int i = 0; i < agenda.size(); i++) {
            if(agenda.get(i).getUsuari().equalsIgnoreCase(nomUsuari)){
                xifraUsuari++;
                nomUsuari = nomSenseXifra+xifraUsuari;
                i = 0;  //Si el nom d'usuari existia tornem a començar el bucle per veure si tambe existeix el nou.
            }
        }
        if (xifraUsuari == 0){
            return 1;
        }
        return xifraUsuari;
    }
    /*************************************************************************/
    /*·························· BORRAR CONTACTE ····························*/
    /*************************************************************************/

    void borrarContactesBaixa (ArrayList<Usuari> agenda) {
        int contador = 0; //contador de contactes borrats
        for (int i = 0; i < agenda.size(); i++) {
            if(agenda.get(i).getBaixa() == true){
                agenda.remove(i);
                contador++;
            }
        }
        Biblioteca.imprimir("\n\nS'han borrat "+contador+" contactes de l'agenda");
    }

    void donarDeBaixa (int pos, ArrayList<Usuari> agenda){
        if (pos >= 0 && pos < agenda.size()){
            agenda.get(pos).setBaixa(true);
            Biblioteca.imprimir("\nContacte donat de baixa!\n");
        }
        else {
            Biblioteca.imprimir("\nNo hi ha cap contacte en aquesta posicio\n");
        }
    }

    /*************************************************************************/
    /*·························· BUSCAR CONTACTE ····························*/
    /*************************************************************************/

    void mostrarContacte (ArrayList<Usuari> agenda){
        /*Inicialitzem paramentres*/
        String text = "";
        boolean existeix = false;
        ArrayList posicions = new ArrayList();
        int posicio = 0;
        /*Inicialitzem l'Scanner*/
        Scanner sc = new Scanner(System.in);

        Biblioteca.imprimir("\nBusca: ");
        text = Biblioteca.llegirString().toUpperCase();
        Biblioteca.imprimir("\n");

        for (int i = 0; i < agenda.size(); i++) {
            if(agenda.get(i).getNom().contains(text)||agenda.get(i).getCognom().contains(text)||agenda.get(i).getCorreu().contains(text)||agenda.get(i).getUsuari().contains(text)){
                posicions.add(i);
                existeix = true;
            }
        }

        if (existeix == false){
            Biblioteca.imprimir("El contacte no existeix!\n\n");
        }
        else{
            if (usuariToArray(agenda,posicions).length == 1) {
                Biblioteca.funcioTaula(columnesUsuariExtended,usuariToArrayExtended(agenda,posicio));
                Biblioteca.imprimir("\n");
            }
            else{
                Biblioteca.funcioTaula(columnesUsuari,usuariToArray(agenda,posicions));
                Biblioteca.imprimir("\nNº: ");
                posicio = sc.nextInt();
                String nomUsuari = usuariToArray(agenda,posicions)[0][1];
                posicio = buscaPosicio(nomUsuari,agenda);
                if (posicio >= 0 && posicio < agenda.size()){
                    Biblioteca.funcioTaula(columnesUsuariExtended,usuariToArrayExtended(agenda,posicio));
                    Biblioteca.imprimir("\n");
                }
                else {
                    Biblioteca.imprimir("\nNo hi ha cap contacte en aquesta posicio\n\n");
                }
            }
        }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.inici();
    }

    void inici(){
        //
        //if (assd.contains("ds")) Biblioteca.imprimir("Funciona\n");

        /*Instanciem Objectes*/
        Biblioteca gui = new Biblioteca();

        /*Definicio de variables*/
        ArrayList<Usuari> llistaUsuaris= new ArrayList<Usuari>();  //ArrayList per a la agenda
        /*Comparador ordenar per nom*/
        Comparator<Usuari> ordenarPerNom = new Comparator<Usuari>() { //ordenem la llista de contactes
            public int compare(Usuari u1, Usuari u2) {
                String name1 = (u1.nom + " " + u1.cognom).trim();
                String name2 = (u2.nom + " " + u2.cognom).trim();
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
            opcio = gui.readInt("Trieu una opcio: ");
            switch(opcio){

                case 1: //Registrar usuari
                    afegirContacte(llistaUsuaris);
                    gui.imprimir("\nContacte guardat!\n");
                    //resum de dades
                    llistaUsuaris.sort(ordenarPerNom);
                    break;

                case 2: //Consultar usuari
                    mostrarContacte(llistaUsuaris);
                    break;

                case 3: //Menu eliminar
                    opcio_submenu = 0;
                    while(opcio_submenu<menu_eliminar.length){
                        gui.imprimir(gui.funcioMenu(menu_eliminar)); //gui.decoracio_menus(menu_eliminar.length, menu_eliminar);
                        opcio_submenu = gui.readInt("Trieu una opcio: ");
                        switch(opcio_submenu){
                            case 1: //donar de baixa un contacte de l'agenda
                                gui.funcioTaula(columnesUsuari,llistaToArrayAlta(llistaUsuaris)); //mostrem la llista de contactes
                                gui.imprimir("\nQuin usuari vols donar de baixa?");
                                int posicio = Biblioteca.readInt("\nNº: ") -1;
                                donarDeBaixa(posicio, llistaUsuaris);
                                break;

                            case 2: //eliminar tots els contactes amb baixa
                                borrarContactesBaixa(llistaUsuaris);
                                break;
                        }
                    }
                    break;

                case 4: //Menu llistar
                    opcio_submenu_llistat = 0;
                    while(opcio_submenu_llistat<menu_llistat.length){
                        gui.imprimir(gui.funcioMenu(menu_llistat)); //gui.decoracio_menus(menu_llistat.length, menu_llistat);
                        opcio_submenu_llistat = gui.readInt("Trieu una opcio: ");
                        switch(opcio_submenu_llistat){

                            case 1: //mostrar contactes d'alta en l'agenda
                                gui.imprimir("\n\n");
                                gui.funcioTaula(columnesUsuari,llistaToArrayAlta(llistaUsuaris));
                                gui.imprimir("\n");
                                String[][] estadistiques = {
                                        {llistaToArrayAlta(llistaUsuaris).length+""}
                                };
                                gui.funcioTaula(columnesEstadistiques,estadistiques);
                                gui.continuar();
                                break;

                            case 2: //mostrar contactes de baixa en l'agenda
                                gui.funcioTaula(columnesUsuari,llistaToArrayBaixa(llistaUsuaris));
                                break;
                        }
                    }
            }
            gui.continuar();
        }
        System.out.println("** Fi del programa **");
    }

    /*************************************************************************/
    /*·························ESCRIPTURA AL FITXER ·························*/
    /*************************************************************************/

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
                String[] dadesLinia = linia.split(" "); //cada cop que la linia sera un espai dividirem la cadena
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
        agenda.add(new Usuari(nom,cognom,correu,contrasenya,nomUsuari)); //afegim un nou objecte a l'agenda amb els parametres que hi ha entre parentesi
    }

                                /*Arrays Menus*/

    /*arrays de menus: menu_principal, menu_eliminar, menu_llistat*/
    String[] menu_principal = {
            "Registrar nou usuari",
            "Consultar usuari",
            "Esborrar usuari",
            "Llistar usuaris",
            "Sortir"
    };
    String[] menu_eliminar = {
            "Donar de baixa","Eliminar definitivament","Sortir"
    };
    String[] menu_llistat = {
            "Usuaris d'alta","Usuaris de baixa","Sortir"
    };
    /*Arrays columnes*/
    String[] columnesUsuari = {
            "Nº","USUARI","NOM","COGNOM","CORREU"
    };
    String[] columnesEstadistiques = {
            "Total usuaris"
    };

                            /*FUNCIONS AUXILIARS*/

    String[][] usuariToArray(ArrayList<Usuari> llista, ArrayList pos){
        String[][] dadesUsuaris = new String[pos.size()][7];
        for (int i = 0; i < pos.size(); i++) {
            dadesUsuaris[i][0] = "#"+(1);
            dadesUsuaris[i][1] = llista.get((int)pos.get(i)).usuari;
            dadesUsuaris[i][2] = llista.get((int)pos.get(i)).nom;
            dadesUsuaris[i][3] = llista.get((int)pos.get(i)).cognom;
            dadesUsuaris[i][4] = llista.get((int)pos.get(i)).correu;
        }
        return dadesUsuaris;
    }
    String[][] llistaToArrayAlta(ArrayList<Usuari> llista){
        /*Creem array per als usuaris de alta*/
        int midaArray = 0;

        for (int i = 0; i < llista.size(); i++) {
            if(llista.get(i).baixa == false){
                midaArray++;
            }
        }

        String[][] dadesUsuaris = new String[midaArray][7];
        int contador = 0;
        for (int i = 0; i < llista.size(); i++) {
            if(llista.get(i).baixa == false){
                dadesUsuaris[contador][0] = "#"+(contador+1);
                dadesUsuaris[contador][1] = llista.get(i).usuari;
                dadesUsuaris[contador][2] = llista.get(i).nom;
                dadesUsuaris[contador][3] = llista.get(i).cognom;
                dadesUsuaris[contador][4] = llista.get(i).correu;
                contador++;
            }
        }
        return dadesUsuaris;
    }
    String[][] llistaToArrayBaixa(ArrayList<Usuari> llista){
        /*Creem array per als usuaris de alta*/
        int midaArray = 0;

        for (int i = 0; i < llista.size(); i++) {
            if(llista.get(i).baixa == true){
                midaArray++;
            }
        }

        String[][] dadesUsuaris = new String[midaArray][7];
        int contador = 0;
        for (int i = 0; i < llista.size(); i++) {
            if(llista.get(i).baixa == true){
                dadesUsuaris[contador][0] = "#"+(contador+1);
                dadesUsuaris[contador][1] = llista.get(i).usuari;
                dadesUsuaris[contador][2] = llista.get(i).nom;
                dadesUsuaris[contador][3] = llista.get(i).cognom;
                dadesUsuaris[contador][4] = llista.get(i).correu;
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
        Biblioteca.imprimir("Cognom: ");
        String cognom = Biblioteca.llegirString().toUpperCase();
        Biblioteca.imprimir("Correu: ");
        String correu = Biblioteca.llegirString().toUpperCase();
        //generarContrassenya(); -- programa de Miki
        String contrasenya = "prova";
        String nomUsuari = generarNomUsuari(nom, cognom);
        agenda.add(new Usuari(nom,cognom,correu,contrasenya,nomUsuari)); //afegim un nou objecte a l'agenda amb els parametres que hi ha entre parentesi
    }

    String generarNomUsuari(String nom, String cognom){
        /*Inicialitzem parametres*/
        String nomUsuari = "";

        /*El nom d'Usuari es la primera lletra del nom i tot el cognom*/
        nomUsuari = nom.charAt(0)+cognom;
        return nomUsuari;
    }

    /*************************************************************************/
    /*·························· BORRAR CONTACTE ····························*/
    /*************************************************************************/

    void borrarContactesBaixa (ArrayList<Usuari> agenda) {
        int contador = 0; //contador de contactes borrats
        for (int i = 0; i < agenda.size(); i++) {
            if(agenda.get(i).baixa == true){
                agenda.remove(i);
                contador++;
            }
        }
        Biblioteca.imprimir("\nS'han borrat "+contador+" contactes de l'agenda");
    }

    void donarDeBaixa (int pos, ArrayList<Usuari> agenda){
        if (pos >= 0 && pos < agenda.size() ){
            agenda.get(pos).baixa = true;
            Biblioteca.imprimir("\nContacte donat de baixa!");
        }
        else {
            Biblioteca.imprimir("\nNo hi ha cap contacte en aquesta posicio ");
        }
    }

    /*************************************************************************/
    /*·························· BUSCAR CONTACTE ····························*/
    /*************************************************************************/

    void mostrarContacte (ArrayList<Usuari> agenda){
        /*Inicialitzem paramentres*/
        String text = "";
        boolean existeix = false;
        Biblioteca.imprimir("\nBusca: ");
        text = Biblioteca.llegirString().toUpperCase();
        ArrayList posicions = new ArrayList();
        int contador = 0;
        for (int i = 0; i < agenda.size(); i++) {
            if(agenda.get(i).nom.contains(text)||agenda.get(i).cognom.contains(text)||agenda.get(i).correu.contains(text)||agenda.get(i).usuari.contains(text)){
               posicions.add(i);
                existeix = true;
                contador++;
            }
        }
        Biblioteca.funcioTaula(columnesUsuari,usuariToArray(agenda,posicions));
        if (existeix == false){
            Biblioteca.imprimir("El contacte no existeix");
        }
    }
}

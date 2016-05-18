import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.inici();
    }

    void inici(){
        /*Instanciem Objectes*/
        Biblioteca gui = new Biblioteca();
        /*Definicio de variables*/
        ArrayList<Usuari> llistaUsuaris= new ArrayList<Usuari>();  //ArrayList Per a la agenda  /*Definim el fitxer*/
        File agenda = new File("Agenda.txt");
        /*Si hi han dades les carreguem*/
        carregarDades(agenda,llistaUsuaris);
        /*Menu*/
        int opcio= 0;//opció que escollira l'usuari
        int opcio_submenu= 0;
        int opcio_submenu_llistat=0;

        while (opcio<menu_principal.length-1){
            gui.decoracio_menus(menu_principal.length, menu_principal);
            opcio = gui.readInt("Trieu una opcio: ");
            switch(opcio){
                case 1: //Registrar usuari
                    break;
                case 2: //Consultar usuari
                    break;
                case 3: //Menu eliminar
                    while(opcio_submenu<menu_eliminar.length-1){
                        gui.decoracio_menus(menu_eliminar.length, menu_eliminar);
                        opcio_submenu = gui.readInt("Trieu una opcio: ");
                        switch(opcio_submenu){
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
                    break;
                case 4: //Menu llistar
                    while(opcio_submenu_llistat<menu_llistat.length-1){
                        gui.decoracio_menus(menu_llistat.length, menu_llistat);
                        opcio_submenu_llistat = gui.readInt("Trieu una opcio: ");
                        switch(opcio_submenu_llistat){
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
            }
        }
    }

        /*Escriptura al fitxer*/

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
           String linia = "";
    try {
        Scanner sc = new Scanner(arxiu);
        while (sc.hasNextLine()){
            linia = sc.nextLine();
            String[] dadesLinia = linia.split(" ");
            if(!(dadesLinia[0] == null)){
                afegirUsuari(dadesLinia,agenda);
            }
        }
    }
    catch (FileNotFoundException e){

    }

    }
     void afegirUsuari(String[] dades, ArrayList<Usuari> agenda){
         //Ordre dades
         //Nom Usuari Nom Cognom Correu Contrasenya Baixa
         String nomUsuari = dades[0].toUpperCase();
         String nom = dades[1].toUpperCase();
         String cognom = dades[2].toUpperCase();
         String correu = dades[3].toUpperCase();
         String contrasenya = dades[4].toUpperCase();
         boolean baixa = false;
         if (dades[5].equalsIgnoreCase("True")){
             baixa = true;
         }
         agenda.add(new Usuari(nomUsuari,nom,cognom,correu,contrasenya,baixa));
     }

    //Arrays Menus
    //arrays de menus: menu_principal, menu_eliminar, menu_llistat
    String[] menu_principal = {
            "AGENDA",
            "1) Registrar nou usuari",
            "2) Consultar usuari",
            "3) Esborrar usuari",
            "4) Llistar usuaris",
            "5) Sortir"
    };
    String[] menu_eliminar = {
            "MENÚ ELIMINAR","1) Donar de baixa","2) Eliminar definitivament","3) Sortir"
    };
    String[] menu_llistat = {
            "MENÚ LLISTAT","1) Usuaris d'alta","2) Usuaris de baix","3) Sortir"
    };

    //Arrays columnes
    String[] columnesUsuari = {
            "Nº","USUARI","NOM","COGNOM","CORREU"
    };

    /*FUNCIONS AUXILIARS*/
    String[][] llistaToArray(ArrayList<Usuari> llista){
        String[][] dadesUsuaris = new String[llista.size()][7];
        for (int i = 0; i < llista.size(); i++) {
            dadesUsuaris[i][0] = i+".";
            dadesUsuaris[i][1] = llista.get(i).usuari;
            dadesUsuaris[i][2] = llista.get(i).nom;
            dadesUsuaris[i][3] = llista.get(i).cognom;
            dadesUsuaris[i][4] = llista.get(i).correu;

        }
        return dadesUsuaris;
    }
}

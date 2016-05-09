import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Principal programa = new Principal();
        programa.inici();
    }

    void inici(){
        /*Instanciem Objectes*/
        Biblioteca gui = new Biblioteca();
        /*Definim el fitxer*/
        File agenda = new File("Agenda.txt");
        /*Comprobem si el fitxer existeix*/
        /*Comprobem si el fitxer te dades*/

        /*Si hi han dades les carreguem*/
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
}

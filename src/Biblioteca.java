import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by acastells on 02/05/16.
 */
public class Biblioteca {

    /*************************************************************************/
    /*··································MENUS································*/
    /*************************************************************************/

    /*DECORACIÓ MENUS*/
    public static void decoracio_menus(int longitud_array, String[] array){
        String[] temp = array;
        Scanner sc = new Scanner(System.in);
        final String DECO = "·";//decoració que s'utiltizara
        int longitud;//longitud de la línia mes llerga de la decoracio
        //int contador=0;
        String linia_decoracio =DECO;//línia superior i inferior de decoració

        for (int i = 1; i < longitud_array; i++) {//afegim la decoració de la esquerra
            array[i] = DECO + " " + array[i];
        }

        /*Contador de caracters*/
        longitud = array[1].length();
        for (int i = 1; i < longitud_array; i++) {//cerquem la línia amb més longitud
            if (longitud < array[i].length()) {
                longitud = array[i].length();
            } else;
        }

        /*Decoració del menu (dreta)*/
        for (int i = 1; i < longitud_array; i++) {//afegim espais a les línies per igualar-les
            while (array[i].length() < longitud) {
                array[i] =array[i] + " ";
            }

        }

        for (int i = 1; i < longitud_array; i++) {//afegim un ultim espai i la decoració de la dreta
            array[i] = array[i] + " " + DECO;
        }


        /*Decoració capsalera del menú*/
        while(array[0].length()<longitud){ //centrem la capsalera segons la longitud
            array[0]=" "+array[0]+" ";
        }
        array[0]=DECO+array[0]+DECO;
        if(array[0].length()>array[1].length()){  //comprovem que els límits de la capsalera i les altres línies siguen igual
            for(int i=1; i< longitud_array; i++){ //si la capsalera es més llerga, eliminem l'ultim caracter i afegim un espai i la decoracio de la dreta
                array[i]= array[i].substring(0,array[i].length()-1);
                array[i]= array[i]+" "+DECO;
            }
        }
        /*lines inferior i superior*/
        while (linia_decoracio.length() < array[0].length()) { //es creen les docarcións superiors i inferiors am la matèixa longitud que la resta de línies
            linia_decoracio = linia_decoracio + DECO;
        }

        /*Imprimir menú*/
        System.out.println(linia_decoracio);
        for (int i = 0; i < longitud_array; i++) {
            System.out.println(array[i]);
        }
        System.out.println(linia_decoracio);

    }

    /*************************************************************************/
    /*·····························IMPRESIONS································*/
    /*************************************************************************/

    /*IMPRIMIR TEXT*/
    void imprimir(String text){ //imprimeix el text
        PrintStream escriptor = new PrintStream(System.out);
        escriptor.print(text);
    }

    /*************************************************************************/
    /*·······························LECTURES································*/
    /*************************************************************************/

    /*LLEGIR ARXIU*/
    void llegirLinia(File arxiu){ //llegir arxiu existent o no
        try {
            Scanner sc = new Scanner(arxiu);
        }
        catch (FileNotFoundException e){
            imprimir("No hi ha arxiu\n");
        }
    }

    /*LLEGIR ENTER*/
    int readInt(String missatge){ //llegir enter
        Scanner sc = new Scanner(System.in);
        try {
            imprimir(missatge);
            return sc.nextInt();
        } catch (InputMismatchException e) {
            imprimir("Error d'entrada, introdueix una xifra!\n");
            return (readInt(missatge));
        }
    }

    /*LLEGIR STRING*/
    public String llegirString(){

        String cadena;
        Scanner sc = new Scanner(System.in);

        cadena = sc.nextLine();
        return cadena;
    }

    /*************************************************************************/
    /*································GETTERS································*/
    /*************************************************************************/

    /*************************************************************************/
    /*································SETTERS································*/
    /*************************************************************************/


    /*************************************************************************/
    /*·································ALTRES································*/
    /*************************************************************************/

    /*APRETAR INTRO PER A CONTINUAR*/
    public void continuar(){

        Scanner sc = new Scanner(System.in);

        imprimir("Prem intro per continuar...");
        sc.nextLine();
    }

}


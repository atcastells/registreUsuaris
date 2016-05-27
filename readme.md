# Registre d'usuaris

## Contingut

1. Carrega desde fitxer
2. Tractament de dades
    * Inserir Usuari
    * Donar de baixa Usuari
    * Eliminar usuaris de baixa
    * Consultar usuari
    * Llista usuaris d'alta
    * Llista usuaris de baixa
3. Guardar al fitxer


### Carrega desde fitxer
    La carrega desde fitxer es fa a traves de dos funcions.
    La primera, carregarDades, crea per cada linia del arxiu, una array amb cada camp que esta separat per ';'
    
```java
void carregarDades (File arxiu,ArrayList<Usuari> agenda){
        String linia = ""; //inicialitzem la variable linia
        System.out.print(arxiu.getAbsolutePath()+"\n"); //mostrem la ruta absoluta de l'arxiu5
        try {
            Scanner sc = new Scanner(arxiu); //inicialitzem l'scanner
            while (sc.hasNextLine()){ //mentre hi hagi alguna per llegir
                linia = sc.nextLine();
                String[] dadesLinia = linia.split(";"); //cada cop que la linia sera un ';' dividirem la cadena
                if(!(dadesLinia[0] == null)){ //si en la primera posicio de cada linia del fitxer hi ha text
                    afegirUsuari(dadesLinia,agenda); //afegim un usuari nou amb les seves dades
                }
            }
        }
        catch (FileNotFoundException e){
        }
    }
```

    La segona, afegirUsuari, agafa les dades enviades per la funcio anterior i les afegeix a la ArrayList on tenim guardats els usuaris.

```java
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
```
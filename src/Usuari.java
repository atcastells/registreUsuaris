import java.util.Random;

public class Usuari{
    private String nom;
    private String cognom;
    private String correu;
    private String contrasenya;
    private String usuari;
    private boolean baixa;

    public Usuari(String nom, String cognom, String correu, String contrasenya, String usuari){
        this.nom = nom;
        this.cognom = cognom;
        this.correu = correu;
        this.contrasenya = contrasenya;
        this.usuari = usuari;
        this.baixa = false;
    }

    public String toString(){
        return usuari +" "+ nom +" "+ cognom +" "+ correu +" "+ contrasenya +" "+ baixa;
    }

    /*************************************************************************/
    /*································GETTERS································*/
    /*************************************************************************/
    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getCorreu() {
        return correu;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public String getUsuari() {
        return usuari;
    }

    public boolean getBaixa() {
        return baixa;
    }

    /*************************************************************************/
    /*································SETTERS································*/
    /*************************************************************************/

    public void setBaixa(boolean opcio) {
        baixa = opcio;
    }

    /*************************************************************************/
    /*·························· GENERAR CONTRASENYA ························*/
    /*************************************************************************/

   static String generar_contrasenya(){
        /*Inicialitzem variables*/
        char[] caracteres;
        char[] caracteres1;
        char[] caracteres2;
        char[] caracteres3;
        caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        caracteres1 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',};
        caracteres2 = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};
        caracteres3 = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String pass = "";
        /*Generem la contrassenya*/
        try {
            for (int i = 0; i < 3; i++) {
                pass += caracteres[new Random().nextInt(62)];
            }
            for (int i = 0; i < 2; i++) {
                pass += caracteres1[new Random().nextInt(10)];
            }
            for (int i = 0; i < 2; i++) {
                pass += caracteres2[new Random().nextInt(26)];
            }
            for (int i = 0; i < 1; i++) {
                pass += caracteres3[new Random().nextInt(26)];
            }
        } catch (Exception ex) {
            System.out.println("Alguna cosa ha anat malament: " + ex.getMessage());
        }
        return pass;
    }

}

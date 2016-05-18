/**
 * Created by acastells on 02/05/16.
 */
public class Usuari{
    String nom;
    String cognom;
    String correu;
    String contrasenya;
    String usuari;
    Boolean baixa;

    public Usuari(String usuari,String nom, String cognom, String correu, String contrasenya, Boolean baixa){
        this.nom = nom;
        this.cognom = cognom;
        this.correu = correu;
        this.contrasenya = contrasenya;
        this.usuari = usuari;
        this.baixa = baixa;
    }

    public Boolean getBaixa() {
        return baixa;
    }
}

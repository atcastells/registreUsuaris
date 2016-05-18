public class Usuari{
    String nom;
    String cognom;
    String correu;
    String contrasenya;
    String usuari;
    Boolean baixa;

    public Usuari(String nom, String cognom, String correu, String contrasenya, String usuari, Boolean baixa){
        this.nom = nom;
        this.cognom = cognom;
        this.correu = correu;
        this.contrasenya = contrasenya;
        this.usuari = usuari;
        this.baixa = baixa;
    }

    public String toString(){
        return usuari +" "+ nom +" "+ cognom +" "+ correu +" "+ contrasenya +" "+ baixa;
    }

    /*************************************************************************/
    /*································GETTERS································*/
    /*************************************************************************/

    public String getnom() {
        return nom;
    }

    public String getcognom() {
        return cognom;
    }

    public String correu() {
        return correu;
    }

    public String contrasenya() {
        return contrasenya;
    }

    public String usuari() {
        return usuari;
    }

    public boolean getbaixa() {
        return baixa;
    }

    /*************************************************************************/
    /*································SETTERS································*/
    /*************************************************************************/

    public void setbaixa(boolean opcio) {
        baixa = opcio;
    }

}

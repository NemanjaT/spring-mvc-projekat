package models;
// Generated Feb 10, 2016 8:58:30 PM by Hibernate Tools 4.3.1



/**
 * Klinika generated by hbm2java
 */
public class Klinika  implements java.io.Serializable {


     private Integer id;
     private String ime;
     private String adresa;
     private String telefon;

    public Klinika() {
    }

	
    public Klinika(String ime, String adresa) {
        this.ime = ime;
        this.adresa = adresa;
    }
    public Klinika(String ime, String adresa, String telefon) {
       this.ime = ime;
       this.adresa = adresa;
       this.telefon = telefon;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getIme() {
        return this.ime;
    }
    
    public void setIme(String ime) {
        this.ime = ime;
    }
    public String getAdresa() {
        return this.adresa;
    }
    
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    public String getTelefon() {
        return this.telefon;
    }
    
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }




}



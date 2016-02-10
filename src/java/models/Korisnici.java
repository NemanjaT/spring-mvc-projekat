package models;
// Generated Feb 10, 2016 8:58:30 PM by Hibernate Tools 4.3.1



/**
 * Korisnici generated by hbm2java
 */
public class Korisnici  implements java.io.Serializable {


     private Integer id;
     private String ime;
     private String prezime;
     private String pol;
     private String brojKnjizice;
     private String lozinka;
     private String jmbg;
     private String osiguranjeImePrezime;
     private String osiguranjeSrodstvo;
     private String adresa;
     private String telefon;
     private String email;
     private String tip;
     private Integer specijalistaTipId;
     private Integer klinikaId;
     private int prihvacen;

    public Korisnici() {
    }

	
    public Korisnici(String ime, String osiguranjeImePrezime, String osiguranjeSrodstvo, String telefon, String tip, int prihvacen) {
        this.ime = ime;
        this.osiguranjeImePrezime = osiguranjeImePrezime;
        this.osiguranjeSrodstvo = osiguranjeSrodstvo;
        this.telefon = telefon;
        this.tip = tip;
        this.prihvacen = prihvacen;
    }
    public Korisnici(String ime, String prezime, String pol, String brojKnjizice, String lozinka, String jmbg, String osiguranjeImePrezime, String osiguranjeSrodstvo, String adresa, String telefon, String email, String tip, Integer specijalistaTipId, Integer klinikaId, int prihvacen) {
       this.ime = ime;
       this.prezime = prezime;
       this.pol = pol;
       this.brojKnjizice = brojKnjizice;
       this.lozinka = lozinka;
       this.jmbg = jmbg;
       this.osiguranjeImePrezime = osiguranjeImePrezime;
       this.osiguranjeSrodstvo = osiguranjeSrodstvo;
       this.adresa = adresa;
       this.telefon = telefon;
       this.email = email;
       this.tip = tip;
       this.specijalistaTipId = specijalistaTipId;
       this.klinikaId = klinikaId;
       this.prihvacen = prihvacen;
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
    public String getPrezime() {
        return this.prezime;
    }
    
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public String getPol() {
        return this.pol;
    }
    
    public void setPol(String pol) {
        this.pol = pol;
    }
    public String getBrojKnjizice() {
        return this.brojKnjizice;
    }
    
    public void setBrojKnjizice(String brojKnjizice) {
        this.brojKnjizice = brojKnjizice;
    }
    public String getLozinka() {
        return this.lozinka;
    }
    
    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
    public String getJmbg() {
        return this.jmbg;
    }
    
    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }
    public String getOsiguranjeImePrezime() {
        return this.osiguranjeImePrezime;
    }
    
    public void setOsiguranjeImePrezime(String osiguranjeImePrezime) {
        this.osiguranjeImePrezime = osiguranjeImePrezime;
    }
    public String getOsiguranjeSrodstvo() {
        return this.osiguranjeSrodstvo;
    }
    
    public void setOsiguranjeSrodstvo(String osiguranjeSrodstvo) {
        this.osiguranjeSrodstvo = osiguranjeSrodstvo;
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
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTip() {
        return this.tip;
    }
    
    public void setTip(String tip) {
        this.tip = tip;
    }
    public Integer getSpecijalistaTipId() {
        return this.specijalistaTipId;
    }
    
    public void setSpecijalistaTipId(Integer specijalistaTipId) {
        this.specijalistaTipId = specijalistaTipId;
    }
    public Integer getKlinikaId() {
        return this.klinikaId;
    }
    
    public void setKlinikaId(Integer klinikaId) {
        this.klinikaId = klinikaId;
    }
    public int getPrihvacen() {
        return this.prihvacen;
    }
    
    public void setPrihvacen(int prihvacen) {
        this.prihvacen = prihvacen;
    }




}



package models;
// Generated Feb 9, 2016 11:43:06 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Pregled generated by hbm2java
 */
public class Pregled  implements java.io.Serializable {


     private Integer id;
     private int pacijentId;
     private Date datumPregleda;
     private String dijagnoza;
     private String nazivBolesti;
     private String tegobe;
     private String propisanaTerapija;
     private Date datumSledeceKontrole;
     private Integer cuvajPacijenta;
     private int nalazId;

    public Pregled() {
    }

	
    public Pregled(int pacijentId, Date datumPregleda, String nazivBolesti, Date datumSledeceKontrole, int nalazId) {
        this.pacijentId = pacijentId;
        this.datumPregleda = datumPregleda;
        this.nazivBolesti = nazivBolesti;
        this.datumSledeceKontrole = datumSledeceKontrole;
        this.nalazId = nalazId;
    }
    public Pregled(int pacijentId, Date datumPregleda, String dijagnoza, String nazivBolesti, String tegobe, String propisanaTerapija, Date datumSledeceKontrole, Integer cuvajPacijenta, int nalazId) {
       this.pacijentId = pacijentId;
       this.datumPregleda = datumPregleda;
       this.dijagnoza = dijagnoza;
       this.nazivBolesti = nazivBolesti;
       this.tegobe = tegobe;
       this.propisanaTerapija = propisanaTerapija;
       this.datumSledeceKontrole = datumSledeceKontrole;
       this.cuvajPacijenta = cuvajPacijenta;
       this.nalazId = nalazId;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getPacijentId() {
        return this.pacijentId;
    }
    
    public void setPacijentId(int pacijentId) {
        this.pacijentId = pacijentId;
    }
    public Date getDatumPregleda() {
        return this.datumPregleda;
    }
    
    public void setDatumPregleda(Date datumPregleda) {
        this.datumPregleda = datumPregleda;
    }
    public String getDijagnoza() {
        return this.dijagnoza;
    }
    
    public void setDijagnoza(String dijagnoza) {
        this.dijagnoza = dijagnoza;
    }
    public String getNazivBolesti() {
        return this.nazivBolesti;
    }
    
    public void setNazivBolesti(String nazivBolesti) {
        this.nazivBolesti = nazivBolesti;
    }
    public String getTegobe() {
        return this.tegobe;
    }
    
    public void setTegobe(String tegobe) {
        this.tegobe = tegobe;
    }
    public String getPropisanaTerapija() {
        return this.propisanaTerapija;
    }
    
    public void setPropisanaTerapija(String propisanaTerapija) {
        this.propisanaTerapija = propisanaTerapija;
    }
    public Date getDatumSledeceKontrole() {
        return this.datumSledeceKontrole;
    }
    
    public void setDatumSledeceKontrole(Date datumSledeceKontrole) {
        this.datumSledeceKontrole = datumSledeceKontrole;
    }
    public Integer getCuvajPacijenta() {
        return this.cuvajPacijenta;
    }
    
    public void setCuvajPacijenta(Integer cuvajPacijenta) {
        this.cuvajPacijenta = cuvajPacijenta;
    }
    public int getNalazId() {
        return this.nalazId;
    }
    
    public void setNalazId(int nalazId) {
        this.nalazId = nalazId;
    }




}



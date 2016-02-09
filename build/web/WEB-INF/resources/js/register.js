var forma        = document.getElementsByName("register-form")[0];
var errorBox     = document.getElementById("error-box");

var email              = document.getElementsByName("email")[0];
var jmbg               = document.getElementsByName("jmbg")[0];
var brojKnjizice       = document.getElementsByName("broj-knjizice")[0];
var ime                = document.getElementsByName("ime")[0];
var prezime            = document.getElementsByName("prezime")[0];
var nosiocOsiguranja   = document.getElementsByName("osiguranje-ime-prezime")[0];
var srodstvoOsiguranje = document.getElementsByName("osiguranje-srodstvo")[0];
var adresa             = document.getElementsByName("adresa")[0];
var telefon            = document.getElementsByName("telefon")[0];
var lozinka            = document.getElementsByName("lozinka")[0];
var lozinkaOpet        = document.getElementsByName("lozinka-opet")[0];

var emailRE              = /^(\w+).*(\w*)@(\w+).((com)|(org)|(rs)|(edu))$/;
var jmbgRE               = /^(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])[0-9]{9}$/;
var brojKnjiziceRE       = /^[0-9]{11}$/;
var adresaRE             = /^[\w\s]+\s\d+\w{0,1}$/;
var telefonRE            = /^06[0-9]\/((\d{3}-\d{2}-\d{2})|\d{3}-\d{3})$/;

forma.addEventListener("submit", function(e) {
    e.preventDefault();
    var poruka = "";
    if(!emailRE.test(email.value)) {
        poruka += "Format e-mail adrese je pogrešan. ";
    }
    if(!jmbgRE.test(jmbg.value)) {
        poruka += "Format jmbg-a je pogrešan. ";
    }
    if(!brojKnjiziceRE.test(brojKnjizice.value)) {
        poruka += "Format broja knjižice je pogrešan (mora imati 11 brojeva). ";
    }
    if(!adresaRE.test(adresa.value)) {
        poruka += "Format adrese je pogrešan (npr. Neka Adresa 1a). ";
    }
    if(!telefonRE.test(telefon.value)) {
        poruka += "Format telefona je pogrešan (npr. 060/123-45-67 ili 060/123-456). ";
    }
    if(ime.value.length < 3 || prezime.value.length < 2) {
        poruka += "Ime i prezime su prekratki. ";
    }
    if(lozinka.value.length < 5) {
        poruka += "Lozinka je prekratka.";
    } else if(lozinka.value !== lozinkaOpet.value) {
        poruka += "Lozinke se ne podudaraju.";
    }
    
    if(poruka !== "") {
        var innerHtml = "<div class='alert alert-danger'><strong>Greška:</strong> " + poruka + "</div>";
        console.log("before:");
        console.log(errorBox);
        console.log("  info:");
        console.log(innerHtml);
        errorBox.innerHTML = innerHtml;
        console.log("after:");
        console.log(errorBox);
    } else {
        forma.submit();
    }
});
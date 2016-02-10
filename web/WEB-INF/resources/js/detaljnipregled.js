var forma = document.getElementsByName("detaljnipregled-form")[0];
var errorBox = document.getElementById("error-box");

var dijagnoza = document.getElementsByName("dijagnoza")[0];
var nazivBolesti = document.getElementsByName("naziv-bolesti")[0];
var tegobe = document.getElementsByName("tegobe")[0];
var propisanaTerapija = document.getElementsByName("propisana-terapija")[0];
var datumSledeceKontrole = document.getElementsByName("datum-sledece-kontrole")[0];

var disanje = document.getElementsByName("disanje")[0];
var puls = document.getElementsByName("puls")[0];
var telesnaTemperatura = document.getElementsByName("telesna-temperatura")[0];

forma.addEventListener("submit", function(e) {
    e.preventDefault();
    
    var poruka = "";
    var datum = new Date(datumSledeceKontrole.value);
    
    if(dijagnoza.value.length < 5)
        poruka += "Dijagnoza prekratka. ";
    if(nazivBolesti.value.length < 5)
        poruka += "Naziv bolesti prekratak. ";
    if(tegobe.value.length < 5)
        poruka += "Tegobe prekratke. ";
    if(datum.getTime() < new Date().getTime() || datumSledeceKontrole.value.length < 10)
        poruka += "Sledeci pregled ne moze biti pre ovog! ";
    if(disanje.value < 10 || disanje.value > 90 || disanje.value === undefined)
        poruka += "Disanje nije u dozvoljenom rangu (10-90). ";
    if(puls.value < 60 || puls.value > 160 || puls.value === undefined)
        poruka += "Puls nije u dozvoljenom rangu (60-160). ";
    if(telesnaTemperatura.value < 36 || telesnaTemperatura.value > 41 || telesnaTemperatura.value === undefined)
        poruka += "Telesna temperatura nije u dozvoljenom rangu (36-41). ";
    
    if(poruka === "") {
        forma.submit();
    } else {
        var innerHtml = "<div class='alert alert-danger'><strong>Greška: </strong>" + poruka + "</div>";
        errorBox.innerHTML = innerHtml;
    }
});
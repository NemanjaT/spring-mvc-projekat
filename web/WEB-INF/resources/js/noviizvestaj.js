var forma = document.getElementsByName("noviizvestaj-form")[0];
var errorBox = document.getElementById("error-box");

var dijagnoza = document.getElementsByName("dijagnoza")[0];
var nazivBolesti = document.getElementsByName("naziv-bolesti")[0];
var tegobe = document.getElementsByName("tegobe")[0];
var datumSledeceKontrole = document.getElementsByName("datum-sledece-kontrole")[0];

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
    
    if(poruka === "") {
        forma.submit();
    } else {
        var innerHtml = "<div class='alert alert-danger'><strong>Greška: </strong>" + poruka + "</div>";
        errorBox.innerHTML = innerHtml;
    }
});
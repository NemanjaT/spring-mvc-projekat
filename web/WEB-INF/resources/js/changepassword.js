var forma = document.getElementsByName("changepassword-form")[0];
var errorBox = document.getElementById("error-box");

var lozinka = document.getElementsByName("lozinka")[0];
var lozinkaOpet = document.getElementsByName("lozinkaopet")[0];

forma.addEventListener("submit", function(e) {
    e.preventDefault();
    
    poruka = "";
    if(lozinka.value.length < 5) {
        poruka = "Lozinka je prekratka.";
    } else if (lozinka.value !== lozinkaOpet.value) {
        poruka = "Lozinke se ne podudaraju.";
    }
    
    if(poruka !== "") {
        var innerHtml = "<div class='alert alert-danger'><strong>Greška:</strong> " + poruka + "</div>";
        errorBox.innerHTML = innerHtml;
    } else {
        forma.submit();
    }
});
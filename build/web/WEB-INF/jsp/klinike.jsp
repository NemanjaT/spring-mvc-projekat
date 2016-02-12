<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
    <table class="table table-bordered table-hover table-responsive">
        <tr>
            <th>Ime</th>
            <th>Adresa</th>
            <th>Telefon</th>
            <th>Izbriši</th>
        </tr>
    <c:forEach items="${ klinike }" var="klinika">
        <tr>
            <td>${ klinika.ime }</td>
            <td>${ klinika.adresa }</td>
            <td>${ klinika.telefon }</td>
            <td>
                <form action="klinike" method="POST">
                    <input type="hidden" name="akcija" value="${ klinika.id }" />
                    <input type="submit" class="btn btn-danger" value="Izbriši" />
                </form>
            </td>
        </tr>
    </c:forEach>
    </table>
    <form action="klinike" method="post" name="klinika-form" class="login-form form-control">
        <input type="hidden" name="akcija" value="dodaj" />
        <div id="error-box">
    <c:choose>
        <c:when test="${ error != null }">
            <div class="alert alert-danger">
                <strong>Greška:</strong> ${ error }
            </div>
        </c:when>
        <c:otherwise>
            <h2>Dodaj kliniku</h2>
        </c:otherwise>
    </c:choose>
        </div>
    <table>
        <tr>
            <td>Ime</td>
            <td><input type="text" name="ime" class="form-control" /></td>
        </tr>
        <tr>
            <td>Adresa</td>
            <td><input type="text" name="adresa" class="form-control" /></td>
        </tr>
        <tr>
            <td>Telefon</td>
            <td><input type="text" name="telefon" class="form-control" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Ubaci" class="btn btn-success"/>
            </td>
        </tr>
    </table>
</form>
    
    <script>
        var forma = document.getElementsByName('klinika-form')[0];
        var errorBox = document.getElementById('error-box');
        
        var ime = document.getElementsByName('ime')[0];
        var adresa = document.getElementsByName('adresa')[0];
        var telefon = document.getElementsByName('telefon')[0];
        
        forma.addEventListener('submit', function(e) {
            e.preventDefault();
            var poruka = "";
            if(ime.value.length < 5)
                poruka += "Ime prekratko. ";
            if(adresa.value.length < 5)
                poruka += "Adresa prekratka. ";
            if(!/^(\d{3}-\d{3})|(\d{4}-\d{3})$/.test(telefon.value))
                poruka += "Telefon nije dobrog formata (1234-567 ili 123-456). ";
            
            if(poruka === "") {
                forma.submit();
            } else {
                var innerHtml = "<div class='alert alert-danger'><strong>Greška: </strong>" + poruka + "</div>";
                errorBox.innerHTML = innerHtml;
            }
        });
    </script>
<jsp:include page="includes/footer.jsp"></jsp:include>
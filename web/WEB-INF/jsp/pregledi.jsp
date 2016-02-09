<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<h3>Pregledi za pacijenta ${ pacijent.ime } ${ pacijent.prezime }</h3>
    <table class="table table-striped">
        <tr>
            <th>Datum pregleda</th>
            <th>Dijagnoza</th>
            <th>Naziv bolesti</th>
            <th>Tegobe</th>
            <th>Propisana terapija</th>
            <th>Datum sledece kontrole</th>
            <th>Pacijent je na čuvanju?</th>
            <!-- nalaz? -->
        </tr>
    <c:forEach items="${ pregledi }" var="pregled">
        <tr>
            <td>${ pregled.datumPregleda }</td>
            <td>${ pregled.dijagnoza }</td>
            <td>${ pregled.nazivBolesti }</td>
            <td>${ pregled.tegobe }</td>
            <td>${ pregled.propisanaTerapija }</td>
            <td>${ pregled.datumSledeceKontrole }</td>
            <td>${ pregled.cuvajPacijenta == 0 ? "Ne" : "Da" }</td>
            <!-- nalaz? -->
        </tr>
    </c:forEach>
    </table>
<a href="noviizvestaj?pac=${ pacijent.id }" class="btn btn-success">Novi pregled</a>
<jsp:include page="includes/footer.jsp"></jsp:include>
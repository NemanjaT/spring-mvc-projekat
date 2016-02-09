<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <td><fmt:formatDate type="date" value="${ pregled.datumPregleda }" pattern="dd.MM.yyyy HH:mm" /></td>
            <td>${ pregled.dijagnoza }</td>
            <td>${ pregled.nazivBolesti }</td>
            <td>${ pregled.tegobe }</td>
            <td>${ pregled.propisanaTerapija }</td>
            <td><fmt:formatDate type="date" value="${ pregled.datumSledeceKontrole }" pattern="dd.MM.yyyy" /></td>
            <td>${ pregled.cuvajPacijenta == 0 ? "Ne" : "Da" }</td>
            <!-- nalaz? -->
        </tr>
    </c:forEach>
    </table>
<c:choose>
    <c:when test='${ korisnik.tip == "lekar specijalista" }'>
        <h3>Uputi:</h3>
        <table class="table table-striped">
            <tr>
                <th>Datum pregleda</th>
                <th>Pregledan?</th>
                <th>Pregledaj</th>
            </tr>
            <c:forEach items="${ uputi }" var="uput">
                <tr>
                    <td><fmt:formatDate value="${ uput.datumPregleda }" type="date" pattern="dd.MM.yyyy" /></td>
                    <td>${ uput.pregledan > 0 ? "Da" : "Ne" }</td>
                    <td><a href='${ uput.pregledan > 0 ? "" : "detaljnipregled?uput=" }${ uput.pregledan > 0 ? "" : uput.id }' 
                           class='btn btn-default ${ uput.pregledan > 0 ? "disabled" : "" }'>Pregledaj</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <a href="${ directpage == null ? "noviizvestaj" : directpage }?pac=${ pacijent.id }" class="btn btn-success">Novi pregled</a>
    </c:otherwise>
</c:choose>
<jsp:include page="includes/footer.jsp"></jsp:include>
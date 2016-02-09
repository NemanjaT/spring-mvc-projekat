<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<form action="pacijentiklinike" method="get" class="pretraga">
    <table>
        <tr>
            <td><input type="text" name="q" class="form-control" /></td>
            <td><input type="submit" class="btn btn-default" value="Pretraži"></td>
        </tr>
    </table>
</form>
    <table class="table table-striped">
        <tr>
            <th>Ime</th>
            <th>Prezime</th>
            <th>Pol</th>
            <th>Broj Knjizice</th>
            <th>JMBG</th>
            <th>Adresa</th>
            <th>Telefon</th>
            <th>Email</th>
            <th>Datum pregleda</th>
            <th>Pregledaj</th>
        </tr>
        <c:forEach items="${ pacijenti }" var="pacijent">
        <tr>
            <td>${ pacijent.key.ime }</td>
            <td>${ pacijent.key.prezime }</td>
            <td>${ pacijent.key.pol == "m" ? "Muški" : "Ženski" }</td>
            <td>${ pacijent.key.brojKnjizice }</td>
            <td>${ pacijent.key.jmbg }</td>
            <td>${ pacijent.key.adresa }</td>
            <td>${ pacijent.key.telefon }</td>
            <td>${ pacijent.key.email }</td>
            <td>${ pacijent.value }</td>
            <td><a href="pregledi?pac=${ pacijent.key.id }" class="btn btn-default">Pregledaj</a></td>
        </tr>
    </c:forEach>
    </table>
<jsp:include page="includes/footer.jsp"></jsp:include>
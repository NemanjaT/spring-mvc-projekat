<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<form action="svipacijenti" method="get" class="pretraga">
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
            <th>Nosioc osiguranja</th>
            <th>Odnos sa nosiocem</th>
            <th>Adresa</th>
            <th>Telefon</th>
            <th>Email</th>
            <th>Pregledi</th>
        </tr>
    <c:forEach items="${ pacijenti }" var="pacijent">
        <tr>
            <td>${ pacijent.ime }</td>
            <td>${ pacijent.prezime }</td>
            <td>${ pacijent.pol == "m" ? "Muški" : "Ženski" }</td>
            <td>${ pacijent.brojKnjizice }</td>
            <td>${ pacijent.jmbg }</td>
            <td>${ pacijent.osiguranjeImePrezime }</td>
            <td>${ pacijent.osiguranjeSrodstvo }</td>
            <td>${ pacijent.adresa }</td>
            <td>${ pacijent.telefon }</td>
            <td>${ pacijent.email }</td>
            <td><a href="pregledi?pac=${ pacijent.id }" class="btn btn-default">Pogledaj</a></td>
        </tr>
    </c:forEach>
    </table>
<jsp:include page="includes/footer.jsp"></jsp:include>
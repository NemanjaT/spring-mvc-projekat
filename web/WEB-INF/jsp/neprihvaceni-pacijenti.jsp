<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
    <table class="table table-striped">
        <tr>
            <th>Prezime</th>
            <th>Ime</th>
            <th>Pol</th>
            <th>Broj knjižice</th>
            <th>JMBG</th>
            <th>Osiguranje (Osoba)</th>
            <th>Osiguranje (Odnos)</th>
            <th>Adresa</th>
            <th>Telefon</th>
            <th>Email</th>
            <th>Prihvati?</th>
        </tr>
    <c:forEach items="${ pacijenti }" var="pacijent">
        <tr>
            <td>${ pacijent.prezime }</td>
            <td>${ pacijent.ime }</td>
            <td>${ pacijent.pol == "m" ? "Muški" : "Ženski" }</td>
            <td>${ pacijent.brojKnjizice }</td>
            <td>${ pacijent.jmbg }</td>
            <td>${ pacijent.osiguranjeImePrezime }</td>
            <td>${ pacijent.osiguranjeSrodstvo }</td>
            <td>${ pacijent.adresa }</td>
            <td>${ pacijent.telefon }</td>
            <td>${ pacijent.email }</td>
            <td>
                <a href="odgovorregistracije?prihvacen=1&pac=${ pacijent.id }" class="btn btn-success"><span class="glyphicon glyphicon-thumbs-up"></span></a>
                <a href="odgovorregistracije?prihvacen=0&pac=${ pacijent.id }" class="btn btn-danger"><span class="glyphicon glyphicon-thumbs-down"></span></a>
            </td>
        </tr>
    </c:forEach>
    </table>
<jsp:include page="includes/footer.jsp"></jsp:include>
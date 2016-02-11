<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
    <table class="table table-bordered table-hover table-responsive">
        <tr>
            <th>Prezime</th>
            <th>Ime</th>
            <th>Pol</th>
            <th>Adresa</th>
            <th>Telefon</th>
            <th>Email</th>
            <th>Tip specijaliste</th>
        </tr>
    <c:forEach items="${ lekari }" var="lekar">
        <tr>
            <td>${ lekar.key.prezime }</td>
            <td>${ lekar.key.ime }</td>
            <td>${ lekar.key.pol == "m" ? "Muški" : "Ženski" }</td>
            <td>${ lekar.key.adresa }</td>
            <td>${ lekar.key.telefon }</td>
            <td>${ lekar.key.email }</td>
            <td>${ lekar.value.ime }</td>
        </tr>
    </c:forEach>
    </table>
    <a href="novilekar" class="btn btn-success">Novi lekar</a>
<jsp:include page="includes/footer.jsp"></jsp:include>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${ korisnik.id == null}">
        <a href="login" class="btn btn-info">Login</a>
        <a href="register" class="btn btn-info">Register</a>
    </c:when>
    <c:otherwise>
        <p>${ korisnik.ime } ${ korisnik.prezime }<small>(<a href="logout">Odjavite se</a>)</small></p>
        <a href="changepassword" class="btn btn-info">Promeni lozinku</a>
    </c:otherwise>
</c:choose>
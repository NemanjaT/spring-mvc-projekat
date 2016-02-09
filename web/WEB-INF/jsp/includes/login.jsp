<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${ korisnik == null}">
        <a href="login">Login</a>
        <a href="register">Register</a>
    </c:when>
    <c:otherwise>
        <p>${ korisnik.ime } ${ korisnik.prezime }<small>(<a href="logout">Odjavite se</a>)</small></p>
        <a href="changepassword">Promeni lozinku</a>
    </c:otherwise>
</c:choose>
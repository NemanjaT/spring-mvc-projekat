<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<div class="login-form register form-control">
<form:form action="register" method="post" modelAttribute="register">
<div id="error-box">
    <c:choose>
        <c:when test="${ error != null }">
            <div class="alert alert-danger">
                <strong>Greška:</strong> ${ error }
            </div>
        </c:when>
        <c:otherwise>
            <h2>Registruj se</h2>
        </c:otherwise>
    </c:choose>
</div>
    <table>
        <tr>
            <td><form:label path="ime">Ime</form:label></td>
            <td><form:input path="ime" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><form:label path="prezime">Prezime</form:label></td>
            <td><form:input path="prezime" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><form:label path="jmbg">JMBG</form:label></td>
            <td><form:input path="jmbg" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><form:label path="pol">Pol</form:label></td>
            <td>
                <form:select path="pol" cssClass="form-control">
                    <form:option value="m">Muško</form:option>
                    <form:option value="z">Žensko</form:option>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path="brojKnjizice">Broj knjižice</form:label></td>
            <td><form:input path="brojKnjizice" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><form:label path="klinikaId">Klinika</form:label></td>
            <td>
                <form:select path="klinikaId"  cssClass="form-control">
                    <c:forEach items="${ klinike }" var="klinika">
                        <form:option value="${ klinika.id }">${ klinika.ime }</form:option>
                    </c:forEach>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><form:label path="osiguranjeImePrezime">Nosioc osiguranja</form:label></td>
            <td><form:input path="osiguranjeImePrezime" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><form:label path="osiguranjeSrodstvo">Srodstvo sa nosiocem</form:label></td>
            <td><form:input path="osiguranjeSrodstvo" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><form:label path="adresa">Adresa</form:label></td>
            <td><form:input path="adresa" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><form:label path="telefon">Telefon</form:label></td>
            <td><form:input path="telefon" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><form:label path="email">Email adresa</form:label></td>
            <td><form:input path="email" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><form:label path="lozinka">Lozinka</form:label></td>
            <td><form:input path="lozinka" type="password" cssClass="form-control" /></td>
        </tr>
        <tr>
            <td><label for="lozinka-opet">Ponovite lozinku</label></td>
            <td><input type="password" id="lozinka-opet" name="lozinka-opet" class="form-control" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Registruj se" class="btn btn-default"/>
            </td>
        </tr>
    </table>
</form:form>
</div>
    
    
    <script src="resources/js/register.js"></script>
<jsp:include page="includes/footer.jsp"></jsp:include>
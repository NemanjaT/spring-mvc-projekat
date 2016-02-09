<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<form action="register" method="post" name="register-form" class="login-form register form-control">
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
            <td>Ime</td>
            <td><input type="text" name="ime" class="form-control" /></td>
        </tr>
        <tr>
            <td>Prezime</td>
            <td><input type="lozinka" name="prezime" class="form-control" /></td>
        </tr>
        <tr>
            <td>JMBG</td>
            <td><input type="text" name="jmbg" class="form-control" /></td>
        </tr>
        <tr>
            <td>Pol</td>
            <td>
                <select name="pol">
                    <option value="m">Muško</option>
                    <option value="z">Žensko</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Broj knjižice</td>
            <td><input type="number" name="broj-knjizice" class="form-control" /></td>
        </tr>
        <tr>
            <td>Klinika</td>
            <td>
                <select name="klinika">
                <c:forEach items="${ klinike }" var="klinika">
                    <option value="${ klinika.id }">${ klinika.ime }</option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Nosioc Osiguranja</td>
            <td><input type="text" name="osiguranje-ime-prezime" class="form-control" /></td>
        </tr>
        <tr>
            <td>Srodstvo sa nosiocem</td>
            <td><input type="text" name="osiguranje-srodstvo" class="form-control" /></td>
        </tr>
        <tr>
            <td>Adresa</td>
            <td><input type="text" name="adresa" class="form-control" /></td>
        </tr>
        <tr>
            <td>Telefon</td>
            <td><input type="text" name="telefon" class="form-control"/></td>
        </tr>
        <tr>
            <td>Email adresa</td>
            <td><input type="email" name="email" class="form-control" /></td>
        </tr>
        <tr>
            <td>Lozinka</td>
            <td><input type="password" name="lozinka" class="form-control" /></td>
        </tr>
        <tr>
            <td>Ponovite lozinku</td>
            <td><input type="password" name="lozinka-opet" class="form-control" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Registruj se" class="btn btn-default"/>
            </td>
        </tr>
    </table>
</form>
    
    
    <script src="resources/js/register.js"></script>
<jsp:include page="includes/footer.jsp"></jsp:include>
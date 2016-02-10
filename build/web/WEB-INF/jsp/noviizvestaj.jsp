<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<form action="noviizvestaj" method="post" name="noviizvestaj-form" class="login-form register form-control">
    <div id="error-box">
    <c:choose>
        <c:when test="${ error != null }">
            <div class="alert alert-danger">
                <strong>Greška:</strong> ${ error }
            </div>
        </c:when>
        <c:otherwise>
            <h2>Novi pregled</h2>
        </c:otherwise>
    </c:choose>
    </div>
    <table>
        <tr>
            <td>Dijagnoza</td>
            <td><textarea name="dijagnoza" class="form-control"></textarea></td>
        </tr>
        <tr>
            <td>Naziv bolesti</td>
            <td><input type="text" name="naziv-bolesti" class="form-control" /></td>
        </tr>
        <tr>
            <td>Tegobe</td>
            <td><textarea name="tegobe" class="form-control"></textarea></td>
        </tr>
        <tr>
            <td>Propisana terapija</td>
            <td><textarea name="propisana-terapija" class="form-control"></textarea></td>
        </tr>
        <tr>
            <td>Datum sledeće kontrole</td>
            <td><input type="date" name="datum-sledece-kontrole" class="form-control"/></td>
        </tr>
        <tr>
            <td>Pacijent na čuvanju</td>
            <td><input type="checkbox" name="cuvaj-pacijenta"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Dodaj pregled" class="btn btn-success" />
            </td>
        </tr>
        <tr>
            <td colspan="2"><hr/></td>
        </tr>
        <tr>
            <td>Pošalji uput</td>
            <td><input type="checkbox" name="uput" /></td>
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
            <td>Specijalista</td>
            <td>
                <select name="specijalista">
                <c:forEach items="${ specijalisti }" var="specijalista">
                    <option value="${ specijalista.id }">${ specijalista.ime }</option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <input type="hidden" value="${ pac }" name="pacijent" />
    </table>
</form>

<script src="resources/js/noviizvestaj.js"></script>
<jsp:include page="includes/footer.jsp"></jsp:include>
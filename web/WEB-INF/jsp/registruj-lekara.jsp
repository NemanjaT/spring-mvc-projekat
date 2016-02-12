<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
    <table class="table table-striped">
        <tr>
            <th>Prezime</th>
            <th>Ime</th>
            <th>Pol</th>
            <th>Adresa</th>
            <th>Telefon</th>
            <th>Email</th>
            <th>Specijalnost</th>
            <td>Izbriši</td>
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
            <td>
                <a href="uklonilekara?lek=${ lekar.key.id }" class="btn btn-danger"><span class="glyphicon glyphicon-fire"></span></a>
            </td>
        </tr>
    </c:forEach>
    </table>
    <form action="registrujlekara" method="post" name="register-form" class="login-form register-other form-control">
    <div id="error-box">
        <c:choose>
            <c:when test="${ error != null }">
                <div class="alert alert-danger">
                    <strong>Greška:</strong> ${ error }
                </div>
            </c:when>
            <c:otherwise>
                <h2>Registruj lekara</h2>
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
                <td>Opšta/Specijalista</td>
                <td>
                    <input type="radio" value="opste" name="tip" checked/>Lekar opšte prakse
                    <input type="radio" value="speci" name="tip" />Lekar specijalista
                </td>
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
                <td>Specijalizacija</td>
                <td>
                    <select name="specijalizacija">
                    <c:forEach items="${ specijaliste }" var="specijalista">
                        <option value="${ specijalista.id }">${ specijalista.ime }</option>
                    </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Registruj" class="btn btn-default"/>
                </td>
            </tr>
        </table>
    </form>
    
    
    <script src="resources/js/register.js"></script>
<jsp:include page="includes/footer.jsp"></jsp:include>
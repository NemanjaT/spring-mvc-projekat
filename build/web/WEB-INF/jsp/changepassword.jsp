<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<form action="changepassword" method="post" name="changepassword-form" class="login-form form-control">
<div id="error-box">
    <c:choose>
        <c:when test="${ error == null }">
            <h2>Promeni lozinku</h2>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger">
                <strong>Greška:</strong> ${ error }
            </div>
        </c:otherwise>
    </c:choose>
</div>
    <table>
        <tr>
            <td>Nova Lozinka</td>
            <td><input type="password" name="lozinka" class="form-control" /></td>
        </tr>
        <tr>
            <td>Ponovite Lozinku</td>
            <td><input type="password" name="lozinkaopet" class="form-control" /></td>
        </tr>
        <tr>
            <td>Stara Lozinka</td>
            <td><input type="password" name="staralozinka" class="form-control" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Promeni lozinku" class="btn btn-default"/>
            </td>
        </tr>
    </table>
</form>
    
    <script src="resources/js/changepassword.js"></script>
<jsp:include page="includes/footer.jsp"></jsp:include>
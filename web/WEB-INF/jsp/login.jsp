<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<form action="login" method="post" name="login-form" class="login-form form-control">
    <c:choose>
        <c:when test="${ error != null }">
            <div class="alert alert-danger">
                <strong>Greška:</strong> ${ error }
            </div>
        </c:when>
        <c:when test="${ success != null }">
            <div class="alert alert-success">
                ${ success }
            </div>
        </c:when>
        <c:otherwise>
            <h2>Prijavi se</h2>
        </c:otherwise>
    </c:choose>
    <table>
        <tr>
            <td>JMBG</td>
            <td><input type="text" name="jmbg" class="form-control" /></td>
        </tr>
        <tr>
            <td>Lozinka</td>
            <td><input type="password" name="lozinka" class="form-control" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Prijavi se" class="btn btn-default"/>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="includes/footer.jsp"></jsp:include>
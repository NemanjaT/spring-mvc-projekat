<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<form action="novilekar" method="post" name="novilekar-form">
    <c:if test="${ error != null }">
        <div class="alert alert-danger">
            ${ error }
        </div>
    </c:if>
    <table class="table">
        <tr>
            <td>Izaberi lekara </td>
            <td>
                <select name="lekar">
                <c:forEach items="${ lekari }" var="lekar">
                    <option value="${ lekar.id }">${ lekar.prezime } ${ lekar.ime }</option>
                </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" class="btn btn-success" value="Dodaj lekara" />
            </td>
        </tr>
    </table>
</form>
<jsp:include page="includes/footer.jsp"></jsp:include>
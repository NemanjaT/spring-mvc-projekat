<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
    <table class="table-responsive table-bordered table table-hover table-bordered">
        <tr>
            <th>Načelnik</th>
            <th>Klinika</th>
            <th>Promeni načelnika</th>
        </tr>
    <c:forEach items="${ nacelnici }" var="nacelnik">
        <tr>
            <td>${ nacelnik.key.prezime } ${ nacelnik.key.ime }</td>
            <td>${ nacelnik.value.ime }</td>
            <td>
                <form action="nacelnici" method="POST">
                    <input type="hidden" name="nacelnik" value="${ nacelnik.key.id }" />
                    <select name="lekar">
                        <c:forEach items="${ lekari }" var="lekar">
                            <option value="${ lekar.id }">${ lekar.prezime } ${ lekar.ime }</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Promeni" class="btn btn-warning" />
                </form>
            </td>
        </tr>
    </c:forEach>
    </table>
<jsp:include page="includes/footer.jsp"></jsp:include>
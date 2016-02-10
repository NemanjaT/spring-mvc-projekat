<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
    <table class="table-responsive table-bordered table table-hover table-bordered">
        <tr>
            <td>Datum nalaza</td>
            <td><fmt:formatDate value="${ nalaz.datumNalaza }" type="date" pattern="dd.MM.yyyy HH:mm" /></td>
        </tr>
        <tr>
            <td>Disanje</td>
            <td>${ nalaz.disanje }</td>
        </tr>
        <tr>
            <td>Puls</td>
            <td>${ nalaz.puls }</td>
        </tr>
        <tr>
            <td>Telesna temperatura</td>
            <td>${ nalaz.telesnaTemperatura }</td>
        </tr>
        <tr>
            <td>Krvni pritisak</td>
            <td>${ nalaz.krvniPritisak }</td>
        </tr>
        <tr>
            <td>Mokraća</td>
            <td>${ nalaz.mokraca == "good" ? "U redu" : "Nije u redu" }</td>
        </tr>
        <tr>
            <td>Stolica</td>
            <td>${ nalaz.stolica == "good" ? "U redu" : "Nije u redu" }</td>
        </tr>
        <tr>
            <td>Krvna slika</td>
            <td>${ nalaz.krvnaSlika == "good" ? "U redu" : "Nije u redu" }</td>
        </tr>
        <tr>
            <td>Specificni pregledi</td>
            <td>${ nalaz.specificanPregled == "" ? "Nema" : nalaz.specificanPregled }</td>
        </tr>
        <tr>
            <td colspan="2">
                <a href="${ lastUrl }" class="btn btn-default form-control">Nazad</a>
            </td>
        </tr>
    </table>
<jsp:include page="includes/footer.jsp"></jsp:include>
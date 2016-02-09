<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EviMedik ${ title == null ? "" : "-" } ${ title }</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="resources/css/style.css"/>
    </head>
    <body>
        <div class="header col-lg-12 col-md-12 col-sm-12 col-xs-12">
            <div class="header-logo col-lg-2 col-md-2 col-sm-2 col-xs-3">
                <img src="resources/images/logo.png" alt="logo"/>
            </div>
            <div class="header-text col-lg-8 col-md-8 col-sm-8 col-xs-6">
                <a href="home"><h1>EviMedik</h1></a>
                <p>Najbolja klinika u zapadnom delu istočnog Požarevca. <small>U krugu pekare "Kod Tome"</small></p>
            </div>
            <div class="header-login col-lg-2 col-md-2 col-sm-2 col-xs-3">
                <jsp:include page="login.jsp"></jsp:include>
            </div>
        </div>
        <div class="navigation">
            <ul>
                <li><a href="home">Pocetna</a></li>
            <c:if test="${ korisnik != null }">
                <c:choose>
                    <c:when test='${ korisnik.tip == "lekar opste prakse" }'>
                    <li><a href="svipacijenti">Svi pacijenti</a></li>
                    </c:when>
                    <c:when test='${ korisnik.tip == "lekar specijalista" }'>
                    <li><a href="pacijentiklinike">Pacijenti</a></li>
                    </c:when>
                    <c:when test='${ korisnik.tip == "pacijent" }'>
                    <li><a href="#">#</a></li>
                    </c:when>
                </c:choose>
            </c:if>
            </ul>
        </div>
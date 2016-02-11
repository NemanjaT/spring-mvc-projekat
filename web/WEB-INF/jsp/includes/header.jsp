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
        <nav class="navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="home">EviMedik</a>
                </div>
                <ul class="nav navbar-nav">
                <c:if test="${ korisnik != null }">
                    <c:choose>
                        <c:when test='${ korisnik.tip == "lekar opste prakse" }'>
                        <li><a href="svipacijenti">Svi pacijenti</a></li>
                        </c:when>
                        <c:when test='${ korisnik.tip == "lekar specijalista" }'>
                            <c:choose>
                                <c:when test='${ korisnik.specijalistaTipId == 1 }'>
                                <li><a href="lekariklinike">Lekari</a></li>
                                <li><a href="kartoni">Kartoni</a></li>
                                </c:when>
                                <c:otherwise>
                                <li><a href="pacijentiklinike">Pacijenti</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:when test='${ korisnik.tip == "pacijent" }'>
                        <li><a href="mojkarton">Moj karton</a></li>
                        </c:when>
                        <c:when test='${ korisnik.tip == "administrator" }'>
                        <li><a href="pregledreg">Odobri pacijente</a></li>
                        <li><a href="novilekarspec">Novi lekari</a></li>
                        <li><a href="nacelnici">Nacelnici</a></li>
                        </c:when>
                    </c:choose>
                </c:if>
                </ul>
            </div>
        </nav>
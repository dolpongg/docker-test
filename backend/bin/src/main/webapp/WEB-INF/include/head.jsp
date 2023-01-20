<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<head>

    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />

    <title>Delicious Bootstrap Template - Index</title>
    <meta content="" name="description" />
    <meta content="" name="keywords" />

    <!-- Favicons -->
    <link href="/assets/img/favicon.png" rel="icon" />
    <link href="/assets/img/apple-touch-icon.png" rel="apple-touch-icon" />

    <!-- Google Fonts -->
    <link
      href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,600,600i,700,700i|Satisfy|Comic+Neue:300,300i,400,400i,700,700i"
      rel="stylesheet"
    />

    <!-- Vendor CSS Files -->
    <link href="/assets/vendor/animate.css/animate.min.css" rel="stylesheet" />
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet" />
    <link href="/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet" />
    <link href="/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet" />
    <link href="/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" />

    <!-- Template Main CSS File -->
    <link href="/assets/css/style.css" rel="stylesheet" />
    <link href="/assets/css/selectBox.css" rel="stylesheet" />

    <!-- =======================================================
  * Template Name: Delicious - v4.8.0
  * Template URL: https://bootstrapmade.com/delicious-free-restaurant-bootstrap-theme/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

    <!-- <script src="/assets/js/SearchTransaction.js" defer></script> -->
  </head>

  <body>
  <script>
  <c:if test="${!empty msg}">
		alert("${msg}");
	</c:if>
  
  </script>

    <!-- ======= Top Bar ======= -->
    <section id="topbar" class="d-flex align-items-center fixed-top">
    
      <div
        class="container-fluid container-xl d-flex align-items-center justify-content-center justify-content-lg-end"
      >
      <c:if test="${empty user}">
        <i class="login bi bi-phone d-flex align-items-center"
          ><button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#exampleModal">
            <span>Login</span>
          </button></i>

        <!-- Modal -->
        
        <div
          class="modal"
          id="exampleModal"
          tabindex="-1"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content" style="background-color: rgb(253, 188, 166)">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Login</h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                <form name="login-form" class="login-form" method="post" action="${root}/main/login">
                	<input type="hidden" value="login" name="action">
                  ID <input id="id" name="id" type="text" placeholder="ID" /> <br>
                  Password <input id="password" name="pass" type="password" placeholder="password" /><br>
                  <input type="button" class="btn btn-secondary" data-bs-dismiss="modal" value="취소">
                  <input type="submit" id="login-btn" class="btn btn-primary" data-bs-dismiss="modal">
                </form>
              </div>
            </div>
          </div>
        </div>
        <i style="display: block" class="login bi bi-clock ms-4 d-none d-lg-flex align-items-center"
          ><button>
            <a href="register.jsp"><span>Sign up</span></a>
          </button></i>
          
          </c:if>
          
          <c:if test="${!empty user}">
        <i class="logout bi bi-phone d-flex align-items-center"
          ><button><span id="logout-btn"><a href="${root}/main/logout">Logout</a></span></button></i>
        <i class="logout bi bi-clock ms-4 d-none d-lg-flex align-items-center"
          ><button>
            <a href="${root}/main/my_page_view"><span>my page</span></a>
          </button></i>
          </c:if>
      </div>
    </section>

    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top d-flex align-items-center">
      <div class="container-fluid container-xl d-flex align-items-center justify-content-between">
        <div class="logo me-auto">
          <a href="${root }/main/"><img src="/assets/img/logo.png" alt="" class="img-fluid" /></a>
        </div>

        <nav id="navbar" class="navbar order-last order-lg-0">
          <ul>
            <li><a class="nav-link scrollto active" href="http://localhost:8080">게시판</a></li>
            <li><a class="nav-link scrollto active" href="noticeList.jsp">공지사항</a></li>
            <li><a class="nav-link scrollto" href="${root}/question/amount?limit=0">매물 추천</a></li>
            <li><a class="nav-link scrollto" href="qna.jsp">인기글</a></li>
            <li><a class="nav-link scrollto" href="${root }/restaurant/SearchCommercial_view">주변탐방</a></li>
            <c:if test="${!empty user}">
            <li>
              <a
                class="logout nav-link scrollto"
                href="${root}/main/attentionLocationForm_view"
                >관심지역 설정</a
              >
            </li>
            <li>
              <a
                class="logout nav-link scrollto"
                href="${root}/main/interestView"
                >관심지역 둘러보기</a
              >
            </li>
            </c:if>
          </ul>
          <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <!-- .navbar -->

        <a href="${root}/main/search_view" class="book-a-table-btn scrollto">실거래 조회</a>
      </div>
    </header>
    <!-- End Header -->
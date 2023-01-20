<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<%@ include file="/WEB-INF/include/head.jsp" %>

  <!-- ======= Hero Section ======= -->
  <section id="hero">
    <div class="hero-container">
      <div id="heroCarousel" data-bs-interval="5000" class="carousel slide carousel-fade" data-bs-ride="carousel">

        <ol class="carousel-indicators" id="hero-carousel-indicators"></ol>

        <div class="carousel-inner" role="listbox">

          <!-- Slide 1 -->
          <div class="carousel-item active" style="background-image: url(/assets/img/slide/slide-1.jpg);">
            <div class="carousel-container">
              <div class="carousel-content">
                <!-- <h2 class="animate__animated animate__fadeInDown"><span>Delicious</span> Restaurant</h2>
                <p class="animate__animated animate__fadeInUp">Ut velit est quam dolor ad a aliquid qui aliquid. Sequi ea ut et est quaerat sequi nihil ut aliquam. Occaecati alias dolorem mollitia ut. Similique ea voluptatem. Esse doloremque accusamus repellendus deleniti vel. Minus et tempore modi architecto.</p> -->
              </div>
            </div>
          </div>

          <!-- Slide 2 -->
          <div class="carousel-item" style="background-image: url(/assets/img/slide/slide-2.jpg);">
            <div class="carousel-container">
              <div class="carousel-content">
                <!-- <h2 class="animate__animated animate__fadeInDown">Lorem Ipsum Dolor</h2>
                <p class="animate__animated animate__fadeInUp">Ut velit est quam dolor ad a aliquid qui aliquid. Sequi ea ut et est quaerat sequi nihil ut aliquam. Occaecati alias dolorem mollitia ut. Similique ea voluptatem. Esse doloremque accusamus repellendus deleniti vel. Minus et tempore modi architecto.</p> -->
              </div>
            </div>
          </div>

          <!-- Slide 3 -->
          <div class="carousel-item" style="background-image: url(/assets/img/slide/slide-3.jpg);">
            <div class="carousel-container">
              <div class="carousel-content">
                <!-- <h2 class="animate__animated animate__fadeInDown">Sequi ea ut et est quaerat</h2>
                <p class="animate__animated animate__fadeInUp">Ut velit est quam dolor ad a aliquid qui aliquid. Sequi ea ut et est quaerat sequi nihil ut aliquam. Occaecati alias dolorem mollitia ut. Similique ea voluptatem. Esse doloremque accusamus repellendus deleniti vel. Minus et tempore modi architecto.</p> -->
              </div>
            </div>
          </div>

        </div>

        <a class="carousel-control-prev" href="#heroCarousel" role="button" data-bs-slide="prev">
          <span class="carousel-control-prev-icon bi bi-chevron-left" aria-hidden="true"></span>
        </a>

        <a class="carousel-control-next" href="#heroCarousel" role="button" data-bs-slide="next">
          <span class="carousel-control-next-icon bi bi-chevron-right" aria-hidden="true"></span>
        </a>
        

      </div>
    </div>
    <div id = "logoMain" class="main-page">
        <div>
          <img src="/assets/img/logo.png" alt="">
        </div>
    </div>

    <div id="j-location-select" class="main-page">
      <div class="form-group col-md-2">
        <select id="sido" class="form-select" aria-label="Default select example">
          <option selected value="all">시도 선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select id="gugun" class="form-select" aria-label="Default select example">
         <option value="all">구군 선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select id="dong" class="form-select" aria-label="Default select example">
          <option value="all">동 선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select id="year" class="form-select" aria-label="Default select example">
          <option value="all">매매년도 선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select id="month" class="form-select" aria-label="Default select example">
          <option value="all">월 선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <button type="submit" id="list-btn" class="book-a-table-btn scrollto" style="border: none;">검색</button>
      </div>
    </div>
    
  </section><!-- End Hero -->

  <!-- ë¡ê·¸ì¸ëª¨ë¬ -->
  <div id="login-form" >
    <form name="login-form" class="login-form">
      <input id="id" name="id" type="text" placeholder="ID" />
      <input
        id="password"
        name="password"
        type="password"
        placeholder="password"
      />
      <button class="login-btn" onclick="login()" type="button">
        로그인
      </button>
      <button onclick="location.href='register.'" type="button">
       	회원가입
      </button>
    </form>
  </div>


  <%@ include file="/WEB-INF/include/footer.jsp" %>
  <script>
//API 불러오기
  document.querySelector("#list-btn").addEventListener("click", function () {
      //localStorage에 저장하고 페이지 이동
      //1. localStorage에 저장하기
      let loca = {};



      let sidoEl = document.querySelector("#sido");
      let sidoV = sidoEl[sidoEl.selectedIndex].text;
      let gugunEl = document.querySelector("#gugun");
      let gugunV = gugunEl[gugunEl.selectedIndex].text;
      let dongEl = document.querySelector("#dong");
      console.log(dongEl);
      let dongV = dongEl[dongEl.selectedIndex].text;
     
      let gugunSel = document.querySelector("#gugun");//구군 저장
      let dong = document.querySelector("#dong");
      let yearSel = document.querySelector("#year");
      let year = yearSel[yearSel.selectedIndex].value;
      let monthSel = document.querySelector("#month");
      let month = monthSel[monthSel.selectedIndex].value;
      let dealYM = year + month;
      
      loca.sido = sidoV;
      loca.gugun = gugunV;
      loca.dong = dongV;
      loca.year = year;
      loca.month = month;
      loca.dealYM = dealYM;

      window.localStorage.setItem("loca",JSON.stringify(loca));


      //2,. 페이지 이동
      location.href = "${root}/main/search?sido=" + sidoV + "&gugun=" + gugunV +"&dong=" + dongV + "&year=" + year  + "&month=" + month + "&page=0";

      });
  
  </script>
  
</body>

</html>
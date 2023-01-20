<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <%@ include file="/WEB-INF/include/head.jsp" %>
  <!-- ëª¨ë¬ í¼ -->
  <div style="margin-top: 120px;">
    <div class="modal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Modal title</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <p>Modal body text goes here.</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary">Save changes</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  
  
  <!-- ======= Hero Section ======= -->
  <section id="hero" class="wrapper" style="display: flex;justify-content: center;align-items: center; height: 50vh;">
    <div class="content" style = "padding: 3rem;" >
      <div class="form-group col-md-2">
        <select id="sido" class="form-select" aria-label="Default select example" style = " width:300px; margin-bottom:10px;">
          <option selected value="all">시도 선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select id="gugun" class="form-select" aria-label="Default select example" style = " width:300px; margin-bottom:10px;">
         <option value="all">구군 선택</option>
        </select>
      </div>
      <div class="form-group col-md-2">
        <select id="dong" class="form-select" aria-label="Default select example" style = " width:300px; margin-bottom:10px;">
          <option value="all">동 선택</option>
        </select>
      </div>
    
      <div class="form-group col-md-2">
        <button type="submit" id="list-btn" class="book-a-table-btn scrollto" style="border: none; margin-left: 100px;">추가</button>
      </div>
    </div>
    
  </section><!-- End Hero -->
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
      
      loca.sido = sidoV;
      loca.gugun = gugunV;
      loca.dong = dongV;

      window.localStorage.setItem("loca",JSON.stringify(loca));

      //2,. 페이지 이동
      location.href = "${root}/main/interestAdd?sido=" + sidoV + "&gugun=" + gugunV +"&dong=" + dongV;

      });
  
  </script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/include/head.jsp" %>

  <!-- ======= Contact Section ======= -->
  <section style="margin-top: 120px;" id="contact" class="contact">
    <div class="container">

      <div class="section-title">
        <h2><span>My</span> Info</h2>
        <p>회원정보</p>
      </div>
    </div>

    <div class="container mt-5">

      <div class="info-wrap">
        <div class="row">
        <div class="col-lg-3 col-md-6 info">
            <i class="bi bi-emoji-smile"></i>
            <h4>ID:</h4>
            <p>${user.id }</p>
          </div>
        
          <div class="col-lg-3 col-md-6 info">
            <i class="bi bi-emoji-smile"></i>
            <h4>Name:</h4>
            <p>${user.name }</p>
          </div>

          <div class="col-lg-3 col-md-6 info mt-4 mt-lg-0">
            <i class="bi bi-envelope"></i>
            <h4>Email:</h4>
            <p>${user.email }</p>
          </div>

        </div>
      </div>
    </div>
    
    <div class="text-center color"><br><button type="button" class="bg-gradient rounded-3 border-light"><a href="${root}/main/edit_my_page_view">수정하기</a></button></div>
  	<div class="text-center color"><br><button type="button" class="bg-gradient rounded-3 border-light"><a href="${root}/main/dropUser">회원탈퇴</a></button></div>
  </section><!-- End Contact Section -->

  <%@ include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
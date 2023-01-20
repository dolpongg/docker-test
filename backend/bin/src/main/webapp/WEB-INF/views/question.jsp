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
        <h2><span>매물</span> 추천</h2>
      </div>
    </div>

    <div class="container mt-5">

      <div class="info-wrap">
        <div class="row">
        
		<h2 style="text-align:center;">
			
			<c:if test="${!empty amount}">
			찾고 있는 매물의 금액대가 ${amount }만원(+-1000만원) 수준입니까?
			</c:if>
			
			<c:if test="${!empty locationName}">
			선호하는 지역이 ${locationName }입니까?
			</c:if>
			
			<c:if test="${!empty buildYear}">
			선호하는 건축 시기가 ${buildYear }년도 입니까?
			</c:if>
		</h2>
			
        </div>
        <c:if test="${!empty amount}">
		<div class="btn-group" role="group" aria-label="Basic mixed styles example" style="margin : 0 500px;">
		  <button type="button" class="btn btn-danger" onclick = "location.href='${root}/question/amount?limit=${amount}&flag=down&low=${low }&high=${high }'">이하</button>
		  <button type="button" class="btn btn-warning" onclick = "location.href='${root}/question/location?save=${amount}'">적정</button>
		  <button type="button" class="btn btn-success" onclick = "location.href='${root}/question/amount?limit=${amount}&flag=up&low=${low }&high=${high }'">이상</button>
		</div>
        </c:if>
        <c:if test="${!empty locationName}">
		<div class="btn-group" role="group" aria-label="Basic mixed styles example" style="margin : 0 500px;">
		  <button type="button" class="btn btn-warning" onclick = "location.href='${root}/question/location?locationNum=${locationNum}'">NO</button>
		  <button type="button" class="btn btn-success" onclick = "location.href='${root}/question/build?save=${locationNum - 1 }'">YES</button>
		</div>
        </c:if>
        <c:if test="${!empty buildYear}">
		<div class="btn-group" role="group" aria-label="Basic mixed styles example" style="margin : 0 500px;">
		  <button type="button" class="btn btn-warning" onclick = "location.href='${root}/question/build?buildNum=${buildNum}'">NO</button>
		  <button type="button" class="btn btn-success" onclick = "location.href='${root}/question/finish?save=${locationNum - 1 }'">YES</button>
		</div>
        </c:if>
      </div>
    </div>
    
  </section><!-- End Contact Section -->

  <%@ include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
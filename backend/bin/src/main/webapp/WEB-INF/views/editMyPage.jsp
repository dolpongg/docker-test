<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <%@ include file="/WEB-INF/include/head.jsp" %>

    
    <section style = "margin-top: 120px;" id="contact" class="contact">
      <div class="container">
  
        <div class="section-title">
          <h2><span>Edit</span> Info</h2>
          <p>모든 항목을 채워주세요</p>
        </div>
      </div>
        <form id = "form" style="width: 800px; margin: auto;"  method="post" role="form" action="${root}/main">
        	<input type="hidden" name="action" value="editInfo">
          <div class="row">
            <div class="col-md-6 form-group" >
              ID
            </div>
            <div class="col-md-6 form-group mt-3 mt-md-0">
              ${user.id }
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 form-group">
              Password
            </div>
            <div class="col-md-6 form-group mt-3 mt-md-0">
              <input type="password" class="form-control" name="pass" id="pass2" placeholder="최소 8자, 최대 20자 최소 하나의 문자 및 숫자" required>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 form-group">
              이름
            </div>
            <div class="col-md-6 form-group mt-3 mt-md-0">
              <input type="text" class="form-control" name="name" id="name" value="${user.name }" required>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 form-group">
              이메일
            </div>
            <div class="col-md-6 form-group mt-3 mt-md-0">
              <input type="text" class="form-control" name="email" id="email" value="${user.email }" placeholder="ex : ssafy@ssafy.com" required>
            </div>
          </div>
          <div class="text-center"><input type="submit" value="저장" id = "edit" class="btn btn-primary" ></div>
        </form>
  
      </div>
    </section><!-- End Contact Section -->
<%@ include file="/WEB-INF/include/footer.jsp" %>
  <script>
    document.querySelector("#edit").addEventListener("click", (e)=>{
    	e.preventDefault();

    	let pass = document.querySelector("#pass2").value;
    	var rulepw = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
    	
    	let email = document.querySelector("#email").value;
    	var ruleEmail = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    	
   		if(pass.match(rulepw)){
    		if(email.match(ruleEmail)){
	    		document.querySelector("#form").submit();
    		}else{
    			alert("이메일 형태가 잘못 되었습니다.")
    		}
   		}else{
   			alert("비밀번호 형태가 잘못 되었습니다.")
   		}
    	
    })
    
    
    </script>
</body>
</html>
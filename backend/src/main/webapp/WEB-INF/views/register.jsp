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
          <p>회원가입</p>
        </div>
      </div>
        <form  id = "form" style="width: 800px; margin: auto;" action="${root}/main" method="post" role="form" class="form">
        	<input type="hidden" name="action" value="registerUser">
          <div class="row">
            <div class="col-md-6 form-group">
              ID
            </div>
            <div class="col-md-6 form-group mt-3 mt-md-0">
              <input type="text" name="id" class="form-control" id="id2" placeholder="5자 이상 20자 이하의 알파벳 또는 숫자" required>
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
              Name
            </div>
            <div class="col-md-6 form-group mt-3 mt-md-0">
              <input type="text" class="form-control" name="name" id="name" placeholder="Name" required>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6 form-group">
              Email
            </div>
            <div class="col-md-6 form-group mt-3 mt-md-0">
              <input type="text" class="form-control" name="email" id="email" placeholder="ex : ssafy@ssafy.com" required>
            </div>
          </div>
          
          <div class="text-center"><input type="submit" id = "regist" value ="회원가입"></div>
        </form>
  
      </div>
    </section><!-- End Contact Section -->
    <script>
    document.querySelector("#regist").addEventListener("click", (e)=>{
    	e.preventDefault();
    	
    	var id = document.querySelector("#id2").value;
    	var ruleId = /^[a-zA-Z0-9]{5,20}$/;
    	
    	let pass = document.querySelector("#pass2").value;
    	var rulepw = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
    	
    	let email = document.querySelector("#email").value;
    	var ruleEmail = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    	
    	
    	console.log(id)
    	
    	if(id.match(ruleId)){
    		if(pass.match(rulepw)){
        		if(email.match(ruleEmail)){
    	    		document.querySelector("#form").submit();
        		}else{
        			alert("이메일 형태가 잘못 되었습니다.")
        		}
       		}else{
       			alert("비밀번호 형태가 잘못 되었습니다.")
       		}
    	}else{
    		alert("아이디 형태가 잘못 되었습니다.")
    	}
    	
    	
    })
    
    
    </script>
  <%@ include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
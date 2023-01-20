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
  <section style = "margin-top: 120px;" id="contact" class="contact">
    <div class="container">

      <div class="section-title">
        <h2><span>Deal</span> Info</h2>
        <div id="j-location-select">
          <div class="form-group col-md-2">
            <select id="sido" class="form-select" aria-label="Default select example">
              <option selected value="all">ìëì í</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select id="gugun" class="form-select" aria-label="Default select example">
             <option value="all">êµ¬êµ°ì í</option>
            </select>
          </div>
          <div class="form-group col-md-2">
            <select id="dong" class="form-select" aria-label="Default select example">
              <option value="all">ëì í</option>
            </select>
          </div>
        </div>
      </div>
    </div>

    <div>
      <div id="map"></div>
      <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b699bf4151c17dc330d1264b4f4bda57"></script>
      <script>
        var container = document.getElementById('map');
        var options = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 3
        };
    
        var map = new kakao.maps.Map(container, options);
      </script>
    </div>
    <div>
      <table class="table table-hover text-center">
        <tr>
          <th>ìíí¸ì´ë¦</th>
          <th>ì¸µ</th>
          <th>ë©´ì </th>
          <th>ë²ì ë</th>
          <th>ê±°ëê¸ì¡</th>
        </tr>
        <tbody id = "aptlist"></tbody>
      </table>
    </div>
    <div class="container mt-5">

      <div class="info-wrap">
        <div class="row">
          <div class="col-lg-3 col-md-6 info">
            <i class="bi bi-geo-alt"></i>
            <h4>Location:</h4>
            <p>A108 Adam Street<br>New York, NY 535022</p>
          </div>

          <div class="col-lg-3 col-md-6 info mt-4 mt-lg-0">
            <i class="bi bi-clock"></i>
            <h4>Open Hours:</h4>
            <p>Monday-Saturday:<br>11:00 AM - 2300 PM</p>
          </div>

          <div class="col-lg-3 col-md-6 info mt-4 mt-lg-0">
            <i class="bi bi-envelope"></i>
            <h4>Email:</h4>
            <p>info@example.com<br>contact@example.com</p>
          </div>

          <div class="col-lg-3 col-md-6 info mt-4 mt-lg-0">
            <i class="bi bi-phone"></i>
            <h4>Call:</h4>
            <p>+1 5589 55488 51<br>+1 5589 22475 14</p>
          </div>
        </div>
      </div>

      <form action="forms/contact.php" method="post" role="form" class="php-email-form">
        <div class="row">
          <div class="col-md-6 form-group">
            <input type="text" name="name" class="form-control" id="name" placeholder="Your Name" required>
          </div>
          <div class="col-md-6 form-group mt-3 mt-md-0">
            <input type="email" class="form-control" name="email" id="email" placeholder="Your Email" required>
          </div>
        </div>
        <div class="form-group mt-3">
          <input type="text" class="form-control" name="subject" id="subject" placeholder="Subject" required>
        </div>
        <div class="form-group mt-3">
          <textarea class="form-control" name="message" rows="5" placeholder="Message" required></textarea>
        </div>
        <div class="my-3">
          <div class="loading">Loading</div>
          <div class="error-message"></div>
          <div class="sent-message">Your message has been sent. Thank you!</div>
        </div>
        <div class="text-center"><button type="submit">Send Message</button></div>
      </form>

    </div>
  </section><!-- End Contact Section -->

  <%@ include file="/WEB-INF/include/footer.jsp" %>
</body>
<script src="/assets/js/selectBox.js"></script>
</html>
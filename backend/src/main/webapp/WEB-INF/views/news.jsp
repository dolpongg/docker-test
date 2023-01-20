<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
  <%@ include file="/WEB-INF/include/head.jsp" %>
  <!-- ======= Events Section ======= -->
  <section style = "margin-top: 120px" id="events" class="events">
    <div class="container">

      <div class="section-title">
        <h2>News &nbsp; <span>Today</span></h2>
      </div>

      <div class="events-slider swiper">
        <div class="swiper-wrapper">

          <div class="swiper-slide">
            <div class="row event-item">
              <div class="col-lg-6">
                <img src="/assets/img/city_1.jpeg" class="img-fluid" alt="">
                <img src="https://imgnews.pstatic.net/image/469/2022/09/07/0000695763_003_20220907061114418.jpg?type=w647" class="img-fluid" style="width: 800px;" alt="">
              </div>
              <div class="col-lg-6 pt-4 pt-lg-0 content">
                <h3>2022.09.07</h3>
                <div class="price">
                  <p><span>ê·¹í ê±°ë ì ë²½â¦ì¶ì ì´í 15ìµ ì´ê³¼ ìíí¸ ëì¶ ê·ì  íë¦¬ë</span></p>
                </div>
                <p class="fst-italic">
                  ìµê·¼ ì£¼íìì¥ì´ 'ì ë²½'ì ëì´ 'ë¹íê¸°'ë¡ ì ì´ë¤ìë¤ë íê°ê° ëì¬ ë§í¼ ê±°ë ì¹¨ì²´ê° ì¬í´ì§ì ì ë¶ê° ì¶ê° ê·ì  ìíë¥¼ ëê³  ê³ ì¬ì ë¹ ì¡ë¤. ìì¥ìì  ì ë¶ê° ê·¸ê° ë¬´ë¦¬í ê·ì ë¼ë ë¹íì´ ë§ìë 15ìµ ì ì´ê³¼ ìíí¸ì ëí ì£¼íë´ë³´ëì¶ ê¸ì§ ê·ì ë¥¼ ì°ì í´ ê±·ì´ë¼ ê±°ë ì ë§ì´ ëì¨ë¤.
                </p>
                <ul>
                  <li><i class="bi bi-check-circle"></i> ì¶ì ì´í ë¶ëì° ê´ê³ ì¥ê´ íì ê°ìµ</li>
                  <li><i class="bi bi-check-circle"></i> ì¬ê±´ì¶ ê·ì  ìí ì´ì´ ì¶ê° ëì± ê´ì¬</li>
                  <li><i class="bi bi-check-circle"></i> ê³ ê°ì£¼í ëì¶ ë´ì ì´ë»ê² ìíí ê¹</li>
                </ul>
                <p>
                  ìê° 15ìµ ìì´ ëë ê³ ê°ì£¼íì ì ì©ë ëì¶ì íì ìì ë ë°©ìì´ ì°ì  ê±°ë¡ ëë¤. ì´ ì¡°ì¹ë ë¬¸ì¬ì¸ ì ë¶ê° 2019ë 12Â·16 ë¶ëì°ëì±ì íµí´ ëìíë¤. ìì¸ ì  ì§ì­ê³¼ ê²½ê¸°Â·ì¸ì² ì¼ë¶ ì§ì­ì ì§ì ë í¬ê¸°ì§ì­ê³¼ í¬ê¸°ê³¼ì´ì§êµ¬ìì ê³ ê°ì£¼íì ì´ ë ì£¼íëì¶ì ìì  ë´ìíë ë´ì©ì´ë¤. ì§ê°ì ì¡ê² ë¤ë ì·¨ì§ì§ë§, ëì ë¹ììë ë¼ëì´ ìë¹íë¤. ëì¶ì í ê¸°ì¤ì ì ìê° 15ìµ ìì¼ë¡ ì í ê·¼ê±°ê° ë¶ë¶ëªíë ë°ë¤ ê³ ê°ì£¼íì¼ìë¡ ë´ë³´ê°ì¹ê° ëìë° ëì¶ì ì ííë ê±´ ê³¼ëí ì¬ì ì¬ì° ì¹¨í´ë¼ë ë¹íë ë§ìë¤. ì ì ì§ê° ì¡ê¸°ìë ìë¬´ë° í¨ê³¼ë¥¼ ë´ì§ ëª»íë¤.
                  <br>
                  ì ë¶ê° ê³ ê°ì£¼í ëì¶ê·ì ë¥¼ íê¸° ìí ë°©ë²ì¼ë¡ ìì¥ìì  í¬ê² ë ê°ì§ê° ê±°ë¡ ëë¤. íì¬ í¬ê¸°ê³¼ì´ì§êµ¬ììë§ ì£¼íë´ë³´ì¸ì ë¹ì¨(LTV) ê¸°ì¤ì ì´ 9ìµ ì ì´ê³¼ì 15ìµ ì ì´ê³¼ë¡ êµ¬ë¶ë¼ ìëë° ì´ ê¸°ì¤ì ì ìì ìì ê±°ë, ê·ì ì§ì­ ìì¤ì ê¸°ì¡´ í¬ê¸°ê³¼ì´ì§êµ¬ìì ì¡°ì ëìì§ì­ì¼ë¡ í ë¨ê³ ë®ì¶ë ê²ì´ë¤. ì¡°ì ëìì§ì­ìì  ê³ ê°ì£¼í ëì¶ ê¸°ì¤ì ì´ ìê³ , 9ìµ ì ì´ê³¼ ì£¼íì LTV 30%(9ìµ ì ì´íê¹ì§ 50%)ê¹ì§ ì¸ì ëë¤.<br>
                  ë¤ë§ ì ë¶ê° íìë¥¼ íí  ê°ë¥ì±ì ë®ë¤. ëì¶ì ë¹ë¡¯í´ ì²­ì½, ì ë§¤ì í, ì¸ê¸ ë± ë¶ëì° ê·ì ê° ì¼ì í í´ì ë¼ ì§ê°ì ìê·¹í  ê°ë¥ì±ì´ ì»¤ìë¤. ë°ë©´ ì ìë ìì¥ ì ìíë¼ë ëªë¶ì ë¤ì´ë§ë ë§í¼ ì ë¶ë¡ìë ë¶ë´ì´ í¬ì§ ìë¤. ì¬ê¸°ì ëí´ ìµê·¼ ì§ê° íë½ì´ ê°íë¼ì ¸ ìì¥ ìí©ì´ ì¤ììììê² ì ì  ì ë¦¬í´ì§ë ë§í¼ ëíµë ¹ ê³µì½ ì¬í­ì´ìë ì ë°ì ì¸ ëì¶ê·ì  ìí(ì¼ë° ì¤ììì LTV 70% ìí¥ ë±) ê°ë¥ì±ë ê±°ë¡ ëë¤.

                  <br>ì ë¶ë ì´ë° ìì¥ì ê¸°ëì ëí´ "ìì¥ ì ìí ì°¨ììì ì¬ë¬ ë°©ìì ë¤ì¬ë¤ë³´ê³  ìë¤"ë©° ë§ì ìë¼ê³  ìì§ë§, ìì¥ ìí©ì´ ë§ì´ ë¬ë¼ì§ ë§í¼ ì ë¶ ë´ ê¸°ë¥ê° ì¡°ê¸ì© ë°ëê³  ìë¤ë íê°ê° ì ì§ ìë¤. ì´ìí ëíê±´ì¤ì ì±ì°êµ¬ì ì°êµ¬ììì "ìµê·¼ ì¸ê¸ëë ê³ ê°ì£¼í ëì¶ê·ì  ìíë ìì¥ ì ìí ì¸¡ë©´ìì ê¸ì ì ì´ë¤"ë©° "ë¤ë§ ìëì ë°ë¼ ëì¶ì ì ííë ì´ë¶ì±ìë¦¬ê¸ìíë¹ì¨(DSR) ê·ì ê° í¨ê» ìëëë êµ¬ì¡°ë¼ ì ì²´ ë§¤ë§¤ìì¥ì íì±íê¹ì§ ê¸°ëíê¸´ ì´ë µë¤"ê³  ë¶ìíë¤.
                </p>
              </div>
            </div>
          </div><!-- End testimonial item -->

          <div class="swiper-slide">
            <div class="row event-item">
              <div class="col-lg-6">
                <img src="/assets/img/event-private.jpg" class="img-fluid" alt="">
              </div>
              <div class="col-lg-6 pt-4 pt-lg-0 content">
                <h3>Private Parties</h3>
                <div class="price">
                  <p><span>$290</span></p>
                </div>
                <p class="fst-italic">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
                  magna aliqua.
                </p>
                <ul>
                  <li><i class="bi bi-check-circle"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
                  <li><i class="bi bi-check-circle"></i> Duis aute irure dolor in reprehenderit in voluptate velit.</li>
                  <li><i class="bi bi-check-circle"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
                </ul>
                <p>
                  Ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                  velit esse cillum dolore eu fugiat nulla pariatur
                </p>
              </div>
            </div>
          </div><!-- End testimonial item -->

          <div class="swiper-slide">
            <div class="row event-item">
              <div class="col-lg-6">
                <img src="/assets/img/event-custom.jpg" class="img-fluid" alt="">
              </div>
              <div class="col-lg-6 pt-4 pt-lg-0 content">
                <h3>Custom Parties</h3>
                <div class="price">
                  <p><span>$99</span></p>
                </div>
                <p class="fst-italic">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
                  magna aliqua.
                </p>
                <ul>
                  <li><i class="bi bi-check-circle"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
                  <li><i class="bi bi-check-circle"></i> Duis aute irure dolor in reprehenderit in voluptate velit.</li>
                  <li><i class="bi bi-check-circle"></i> Ullamco laboris nisi ut aliquip ex ea commodo consequat.</li>
                </ul>
                <p>
                  Ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
                  velit esse cillum dolore eu fugiat nulla pariatur
                </p>
              </div>
            </div>
          </div><!-- End testimonial item -->

        </div>
        <div class="swiper-pagination"></div>
      </div>

    </div>
  </section><!-- End Events Section -->

  <%@ include file="/WEB-INF/include/footer.jsp" %>
</body>
</html>
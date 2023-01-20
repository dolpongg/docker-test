
//조회를 위한 날짜
let date = new Date();

//년도
window.onload = function () {
  let yearEl = document.querySelector("#year");
  console.log(yearEl);
  if(yearEl!=null){
	  let yearOpt = `<option value="">매매년도선택</option>`;
	  let year = date.getFullYear();
	  for (let i = year; i > year - 20; i--) {
	    yearOpt += `<option value="${i}">${i}년</option>`;
	  }
	  yearEl.innerHTML = yearOpt;
  }
  // 브라우저가 열리면 시도정보 얻기.
  sendRequest("sido", "*00000000");
};

//월
document.querySelector("#year").addEventListener("change", function () {
  let month = date.getMonth() + 1;
  let monthEl = document.querySelector("#month");
  let monthOpt = `<option value="">매매월선택</option>`;
  let yearSel = document.querySelector("#year");
  let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
  for (let i = 1; i < m; i++) {
    monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
  }
  monthEl.innerHTML = monthOpt;
});





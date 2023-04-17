let gubun; // 저장 save 수정 update
let no;

$(document).ready(function () {
  gubun = location.href.split("gubun=")[1];
  no = location.href.split("no=")[1];

  const btnElement = document.getElementById("btnSave");

  if (gubun == "save") {
    btnElement.innerText = "저장";
  } else {
    btnElement.innerText = "수정";
    detailSearch(no);
  }
});

/*저장 수정 버튼 이동함수 */
const saveNotice = () => {
  gubun === "save" ? noticeInsert() : noticeUpdate();
};

/*공지사항 상세 조회 */
const detailSearch = (no) => {
  fnAjax(
    "POST",
    "",
    "json",
    "/notice/noticeDetail",
    { no: no },
    function (data) {
      $("input[name=title]").attr("value", data.title);
      $("#content").val(data.content);
    }
  );
};

/*공지사항 저장*/
const noticeInsert = () => {
  let post_data = $("form[name=testForm]").serialize();
  post_data += "&gubun=" + gubun;
  fnAjax("POST", "", "", "/notice/noticeDetailSave", post_data, function () {
    openAlertPop();
  });
};

/*공지사항 수정*/
const noticeUpdate = () => {
  let post_data = $("form[name=testForm]").serialize();
  post_data += "&no=" + no;
  post_data += "&gubun=" + gubun;
  fnAjax("POST", "", "", "/notice/noticeDetailSave", post_data, function () {
    openAlertPop();
  });
};

/*alert 팝업 오픈 */
const openAlertPop = () => {
  document.getElementById("alertPopup_layer").style.display = "block";
};

/*alert 팝업 닫기 */
const closeAlertPop = () => {
  document.getElementById("alertPopup_layer").style.display = "none";
  window.location.href = "/notice/noticePage";
};

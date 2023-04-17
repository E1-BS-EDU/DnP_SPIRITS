$(document).ready(function () {
  noticelist(1);
});

/* 공지사항 목록 조회 함수 */
const noticelist = (page) => {
  fnAjax(
    "POST",
    "",
    "json",
    "/notice/noticeList",
    { page: page },
    function (data) {
      let res = "";
      for (let i = 0; i < Object.keys(data).length; i++) {
        res +=
          "<tr>" +
          "<td>" +
          data[i].no +
          "</td>" +
          "<td> <a href='/notice/noticeDetail?gubun=update&no=" +
          data[i].no +
          "' target='mainfrm' style='text-decoration-line:none; color:#212529;'>" +
          data[i].title +
          "</a></td>" +
          "<td>" +
          data[i].userName +
          "</td>" +
          "<td>" +
          data[i].finCreDtm +
          "</td></tr>";
      }
      $("#notice_list").html(res);
      loadPage(data[0].totalCnt);
    }
  );
};

/* 공지사항 목록 페이징 함수 */
const loadPage = (totalCnt) => {
  let pageSize = 10;
  let _totalPages = totalCnt / 10;

  if (totalCnt % 10 > 0) {
    _totalPages++;
  }

  $("#pagination").twbsPagination({
    totalPages: _totalPages,
    visiblePages: 10,
    first: '<span sris-hidden="true">«</span>',
    last: '<span sris-hidden="true">»</span>',
    prev: "이전",
    next: "다음",
    initiateStartPageClick: false,
    onPageClick: function (event, page) {
      noticelist(page);
    },
  });
};

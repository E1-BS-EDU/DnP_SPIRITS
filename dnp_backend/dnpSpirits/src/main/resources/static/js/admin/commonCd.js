$(document).ready(function () {
  commonlist(1);

  /***********공통코드 불러와서 사용하는 로직**************/
  const comCdList = ["PROJECT", "WORKINGDAY_CD"];

  fnCommonCdAjax(comCdList, function (data) {
    const workgingDayCd = data.filter(function (common) {
      return common.parentCd === "PROJECT";
    });
  });
  /***********공통코드 불러와서 사용하는 로직**************/
});

/* 공통코드 목록 조회 함수 */
const commonlist = (page) => {
  $.ajax({
    url: "/admin/commonCdList",
    type: "POST",
    dataType: "json",
    data: { page: page },
    success: function (data) {
      let res = "";
      for (let i = 0; i < Object.keys(data).length; i++) {
        res +=
          "<tr>" +
          "<td>" +
          data[i].rnum +
          "</td>" +
          "<td> <a href='/admin/commonCdDetail?gubun=update&comCd=" +
          data[i].comCd +
          "' target='mainfrm' style='text-decoration-line:none; color:#212529;'>" +
          data[i].comCd +
          "</a></td>" +
          "<td>" +
          data[i].comNm +
          "</td>" +
          "<td>" +
          data[i].etc +
          "</td>" +
          "<td>" +
          data[i].etcDesc +
          "</td>" +
          "<td>" +
          data[i].parentCd +
          "</td>" +
          "<td></tr>";
      }
      $("#common_list").html(res);

      loadPage(data[0].totalCnt);
    },

    error: function (status, error) {
      console.log("상태값 : " + status + " http 에러 : ");
    },
  });
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
      getNoticelist(page);
    },
  });
};

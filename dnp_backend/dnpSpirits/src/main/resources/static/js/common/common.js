// 공통 ajax 처리
const fnAjax = (type, contentType, dataType, url, param, callback) => {
  if (contentType) {
    $.ajax({
      type: type,
      contentType: "application/json; charset=utf-8",
      data: param,
      dataType: dataType,
      url: url,
      success: function (data, status, xr) {
        return callback(data);
      },
      error: function (status, msg) {
        console.log("상태값 : " + status + " http 에러 : " + msg);
      },
    });
  } else {
    $.ajax({
      type: type,
      data: param,
      dataType: dataType,
      url: url,
      success: function (data, status, xr) {
        return callback(data);
      },
      error: function (status, msg) {
        console.log("상태값 : " + status + " http 에러 : " + msg);
      },
    });
  }
};

// 공통 코드 리스트 불러오기 ajax
const fnCommonCdAjax = (param, callback) => {
  jQuery.ajaxSettings.traditional = true;
  $.ajax({
    type: "POST",
    data: { commonCdList: param },
    dataType: "json",
    url: "/common/commonCdSearch",
    success: function (data, status, xr) {
      return callback(data);
    },
    error: function (status, msg) {
      console.log("상태값 : " + status + " http 에러 : " + msg);
    },
  });
};

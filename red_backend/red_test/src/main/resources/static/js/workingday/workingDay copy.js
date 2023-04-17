$(document).ready(function () {
  //나중에 공통코드 적용부분
  fn_selectWorkInfo();
  fn_selectSiteInfo();
  ///////////////////////////////////

  var calendarEl = document.getElementById("calendar");
  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: "dayGridMonth",
    locale: "ko", // 한국어 설정
    contentHeight: "auto",
    aspectRatio: 1.35,
    editable: false,
    // 해더에 표시할 툴바
    headerToolbar: {
      left: "",
      center: "title",
      right: "prev,next today",
    },
    // 구글캘린더 api 공휴일 연동
    googleCalendarApiKey: "AIzaSyCMSC4dEJUUhV_7sE1c2vvhm0GQRm2MEps",
    eventSources: [
      {
        className: "holiday",
        //title:
        id: "holiday",
        googleCalendarId: "ko.south_korea#holiday@group.v.calendar.google.com",
        color: "white", // an option!
        textColor: "red", // an option!
        editable: false,
      },
      // 등록된 workingday 이벤트 조회
      function (info, successCallback, failureCallback) {
        var param = {
          userNo: 1,
          fromDt: info.startStr,
          endDt: info.endStr,
        };
        fnAjax(
          "POST",
          "",
          "json",
          "/workingday/selectWorkingDay",
          param,
          function (data) {
            var events = [];
            for (i = 0; i < data.length; i++) {
              if (data[i]["gubunCd"] > 0) {
                events.push({
                  title: "[" + data[i]["gubunCd"] + "] " + data[i]["siteNm"],
                  start: data[i]["workDate"],
                  description:
                    "[" + data[i]["gubunCd"] + "] " + data[i]["siteNm"],
                  editable: true,
                  //color: "green",
                });
              }
            }
            successCallback(events);
          }
        );
      },
    ],
    // 일정 선택시 상세 팝업
    eventClick: function (info) {
      info.jsEvent.preventDefault();
      if (info.event.url == "") {
        fn_eventDetail(info.event.start);
        document.getElementById("eventPopup_layer").style.display = "block";
      }
    },
    // 이벤트 툴팁
    eventDidMount: function (info) {
      tippy(info.el, {
        content: info.event.extendedProps.description,
        placement: "bottom",
        arrow: false,
      });
    },
    selectConstraint: {
      //daysOfWeek: [1, 2, 3, 4],
    },
  });
  calendar.render();

  var startDate = null;
  var endDate = null;

  // 캘린더 날짜 선택 이벤트
  calendar.on("dateClick", function (info) {
    if (startDate == null || startDate > info.dateStr) {
      startDate = info.dateStr;
      endDate = null;
      calendar.select(startDate);
    } else if (endDate == null) {
      endDate = info.dateStr;

      var toDate = new Date(
        info.date.getFullYear(),
        info.date.getMonth(),
        info.date.getDate() + 1
      );
      calendar.select(startDate, toDate);
    } else {
      endDate = null;
      startDate = info.dateStr;
      calendar.select(startDate);
    }
  });

  // 저장버튼 클릭 이벤트
  $("#btnSave").click(function () {
    var gbnCode = $("#gubunCd").val();
    var siteNo = $("#siteNo").val();

    if (startDate == null) {
      alert("날짜를 선택하세요.");
      return;
    }

    // 선택된 모든 날짜 가져오기
    var dateArr = new Array();
    dateArr = getDatesStartToLast(startDate, endDate);

    // 파라미터 데이터 생성
    var paramArr = new Array();

    for (var i = 0; i < dateArr.length; i++) {
      var map = new Object();
      map.workDate = dateArr[i];
      map.gubunCd = gbnCode;
      map.siteNo = siteNo;
      paramArr.push(map);
    }

    $.ajax({
      url: "/workingday/saveWorkingDay",
      type: "POST",
      data: JSON.stringify(paramArr),
      dataType: "json",
      contentType: "application/json",
      success: function (data, status) {
        if (data > 0) {
          openAlertPop();
        }
      },
      error: function (status, error) {},
    });
  });
});

// 시작일~종료일 사이 날짜 구하기
function getDatesStartToLast(firstDate, lastDate) {
  if (lastDate == null || lastDate == "") lastDate = firstDate;
  var regex = RegExp(/^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/);
  if (!(regex.test(firstDate) && regex.test(lastDate)))
    return "Not Date Format";
  var result = [];
  var curDate = new Date(firstDate);
  while (curDate <= new Date(lastDate)) {
    result.push(curDate.toISOString().split("T")[0]);
    curDate.setDate(curDate.getDate() + 1);
  }
  return result;
}

/*alert 팝업 오픈 */
function openAlertPop() {
  document.getElementById("alertPopup_layer").style.display = "block";
}

/*alert 팝업 닫기 */
function closeAlertPop() {
  document.getElementById("alertPopup_layer").style.display = "none";
  window.location.href = "/workingday/workingdayPage";
}

/*event 팝업 닫기 */
function closeEventPop() {
  document.getElementById("eventPopup_layer").style.display = "none";
}

// 구분코드 조회
function fn_selectWorkInfo() {
  $.ajax({
    url: "/workingday/selectWorkInfo",
    type: "POST",
    dataType: "json",
    success: function (data) {
      var selectStr = "";
      $.each(data, function (index, value) {
        selectStr +=
          "<option value=" +
          value.gubun_cd +
          ">[" +
          value.gubun_cd +
          "] " +
          value.gubun_b +
          "</option>";
      });
      $("#gubunCd").append(selectStr);
    },
  });
}

// 프로젝트명 조회
function fn_selectSiteInfo() {
  $.ajax({
    url: "/workingday/selectSiteInfo",
    type: "POST",
    dataType: "json",
    success: function (data) {
      var selectStr = "";
      $.each(data, function (index, value) {
        selectStr +=
          "<option value=" + value.site_no + ">" + value.site + "</option>";
      });
      $("#siteNo").append(selectStr);
    },
  });
}

// 이벤트 상세 팝업 데이터 조회
function fn_eventDetail(paramDate) {
  let param = { userNo: 1, workDate: paramDate };

  $.ajax({
    url: "/workingday/workingdayDetail",
    type: "POST",
    data: JSON.stringify(param),
    dataType: "json",
    contentType: "application/json",
    success: function (data) {
      $("#popGubunCd").text(data.gubun_cd);
      $("#popGubunA").text(data.gubun_a);
      $("#popGubunB").text(data.gubun_b);
      $("#popGubunDesc").text(data.gubun_desc);
      $("#popSite").text(data.site);
      $("#popResion").text(data.resion);
    },
  });
}

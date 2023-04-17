$(document).ready(function () {
  //나중에 공통코드 적용부분
  fn_commonInfo();

  ///////////////////////////////////
  fn_create_calendarE1();
});

function fn_create_calendarE1() {
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
      //등록된 workingday 이벤트 조회
      function (info, successCallback, failureCallback) {
        let v_userNo = $("#gv_user_no").val();
        let param = {
          userNo: v_userNo,
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
                  editable: false,
                  id: data[i]["id"],
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
      // console.log(info);
      // console.log(calendar.getEvents());

      info.jsEvent.preventDefault();
      if (info.event.url == "") {
        fn_eventDetail(info.event.start, info.event._def.publicId); //해당날짜 , DB 고유ID
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
  });

  calendar.render();

  var startDate = null;
  var endDate = null;

  // 캘린더 날짜 선택 이벤트
  calendar.on("dateClick", function (info) {
    //console.log(info);
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
  $("#btn_Save").click(function () {
    let v_userNo = $("#gv_user_no").val();
    let gbnCode = $("#gubunCd").val();
    let siteNo = $("#siteNo").val();

    if (gbnCode == null || gbnCode == "") {
      alert("구분 코드를 선택하세요.");
      return;
    }

    if (siteNo == null || siteNo == "") {
      alert("프로젝트명을 선택하세요.");
      return;
    }

    if (startDate == null) {
      alert("날짜를 선택하세요.");
      return;
    }
    //선택한 날짜에 같은 프로젝트명이 존재하는지 체크

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
      map.userNo = v_userNo;
      paramArr.push(map);
    }

    // var jsondata = JSON.stringify(paramArr);
    // console.log(jsondata);
    // console.log(calendar.getEvents());
    // return;

    fnAjax(
      "POST",
      "application/json",
      "json",
      "/workingday/saveWorkingDay",
      JSON.stringify(paramArr),
      function (data) {
        if (data > 0) {
          openAlertPop();
        }
      }
    );
  });

  // 삭제버튼 클릭 이벤트
  // $("#btn_Delete").click(function () {
  //   //console.log(calendar.getEvents());
  //   var allEvent = calendar.getEvents();

  //   // 선택된 모든 날짜 가져오기
  //   var dateArr = new Array();
  //   dateArr = getDatesStartToLast(startDate, endDate);
  //   //console.log(dateArr);

  //   //날짜의 일정목록 정리
  //   var events = new Array();
  //   for (i = 0; i < dateArr.length; i++) {
  //     for (j = 0; j < allEvent.length; j++) {
  //       let allYYYYMMDD = "";
  //       let url = allEvent[j]._def.url;

  //       if (url != "") continue; // 구글 연동 부분 제외 하기 위해

  //       let obj = new Object(); // Json 을 담기 위해 Object 선언
  //       obj.title = allEvent[j]._def.title; // 이벤트 명칭  ConsoleLog 로 확인 가능.

  //       let offset =
  //         allEvent[j]._instance.range.start.getTimezoneOffset() * 60000; //ms단위라 60000곱해줌
  //       let dateOffset = new Date(allEvent[j]._instance.range.start - offset);
  //       allYYYYMMDD = dateOffset.toISOString().split("T")[0];
  //       obj.start = allYYYYMMDD; // 시작
  //       //console.log(obj.title, dateOffset, allYYYYMMDD);

  //       obj.id = allEvent[j]._def.publicId; //DB 키Id
  //       if (allYYYYMMDD == dateArr[i]) {
  //         events.push(obj);
  //       }
  //     }
  //   }
  //   console.log(events);

  //   //날짜별 중복 프로젝트 제거
  //   const result = events.filter(
  //     (v, i) => events.findIndex((x) => x.title === v.title) === i
  //   );

  //   console.log(result.length);
  // });
}

// 구분코드 조회
function fn_commonInfo() {
  const comCdList = ["proj", "workcode"];

  fnCommonCdAjax(comCdList, function (data) {
    const projCd = data.filter(function (common) {
      return common.parentCd === "proj";
    });

    var selectStr = "";
    $.each(projCd, function (index, value) {
      selectStr +=
        "<option value=" + value.comCd + ">" + value.comNm + "</option>";
    });
    $("#siteNo").append(selectStr);

    //구분코드
    const workgingDayCd = data.filter(function (common) {
      return common.parentCd === "workcode";
    });
    console.log(workgingDayCd);

    var selectStr = "";
    $.each(workgingDayCd, function (index, value) {
      selectStr +=
        "<option value=" +
        value.comCd +
        ">[" +
        value.comCd +
        "] " +
        value.comNm +
        "</option>";
    });
    $("#gubunCd").append(selectStr);
  });
}

// 이벤트 상세 팝업 데이터 조회
function fn_eventDetail(paramDate, id) {
  let v_userNo = $("#gv_user_no").val();
  let param = { userNo: v_userNo, workDate: paramDate, id: id };
  let offset = paramDate.getTimezoneOffset() * 60000; //ms단위라 60000곱해줌
  let dateOffset = new Date(paramDate - offset);
  let allYYYYMMDD = dateOffset.toISOString().split("T")[0];

  $.ajax({
    url: "/workingday/workingdayDetail",
    type: "POST",
    data: JSON.stringify(param),
    dataType: "json",
    contentType: "application/json",
    success: function (data) {
      $("#popWorkDate").val(allYYYYMMDD);
      $("#popId").val(data.id);
      $("#popGubunCd").text(data.gubun_cd);
      $("#popGubunA").text(data.gubun_a);
      $("#popGubunB").text(data.gubun_b);
      $("#popGubunDesc").text(data.gubun_desc);
      $("#popSite").text(data.site);
      $("#popResion").text(data.resion);
    },
  });
}

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
/*event 팝업 삭제 */
function delEventPop() {
  let v_id = $("#popId").val();
  let v_workDate = $("#popWorkDate").val();
  let v_userNo = $("#gv_user_no").val();

  // 파라미터 데이터 생성
  const param = {
    workDate: v_workDate,
    id: v_id,
    userNo: v_userNo,
  };

  console.log(param);
  fnAjax(
    "POST",
    "application/json",
    "json",
    "/workingday/deleteWorkingDay",
    JSON.stringify(param),
    function (data) {
      if (data > 0) {
        openAlertPop();
      }
    }
  );
}

// 왼쪽 채움
function lpad(str, padLen, padStr) {
  if (padStr.length > padLen) {
    console.log("오류 : 채우고자 하는 문자열이 요청 길이보다 큽니다");
    return str;
  }
  str += ""; // 문자로
  padStr += ""; // 문자로
  while (str.length < padLen) str = padStr + str;
  str = str.length >= padLen ? str.substring(0, padLen) : str;
  return str;
}
function rpad(str, padLen, padStr) {
  if (padStr.length > padLen) {
    console.log("오류 : 채우고자 하는 문자열이 요청 길이보다 큽니다");
    return str + "";
  }
  str += ""; // 문자로
  padStr += ""; // 문자로
  while (str.length < padLen) str += padStr;
  str = str.length >= padLen ? str.substring(0, padLen) : str;
  return str;
}

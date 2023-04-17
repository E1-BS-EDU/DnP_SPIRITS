let selectDate = "";
let siteList = "";
const projectList = [];

$(document).ready(async () => {
  // 로딩시 당일 기준 조회
  const now = new Date();
  selectDate = dateChange(now);
  await siteListSearch();
  await getWeekOf();
});

// 주간 보고 조회
const getWeekOf = async () => {
  fnAjax(
    "POST",
    "",
    "json",
    "/workweek/weekOf",
    { selectDate },
    function (data) {
      console.log(data);
      // weekNo - 주차
      // weeklyWork - 주간보고 내용
      const weekOf = data.weekOf;
      const weekValue = data.weekValue;
      const weeklyNo = data.weeklyNo;
      const weeklyWork = data.weeklyWork;

      // 계획 날짜 셋팅
      document.getElementById("week_no").value = weekValue;
      document.getElementById("thisWeek").innerHTML = planDate(
        weekOf.sdt,
        weekOf.edt
      );
      document.getElementById("performanceDate").innerHTML = planDate(
        weekOf.sdt,
        weekOf.edt
      );
      document.getElementById("nextWeekDate").innerHTML = planDate(
        weekOf.nextSdt,
        weekOf.nextEdt
      );
      document.getElementById("weekNo").value = weeklyNo;
      if (weeklyWork) {
        document.getElementById("btnSave").style.display = "none";
        document.getElementById("btnUpdate").style.display = "block";
        // document.getElementById("siteNo").value = weeklyWork.siteNo;
        document.getElementById("thisWeekPlan").value = weeklyWork.thisWeekPlan;
        document.getElementById("performance").value = weeklyWork.performance;
        document.getElementById("nextWeekPlan").value = weeklyWork.nextWeekPlan;
        document.getElementById("remarks").value = weeklyWork.remarks;

        siteChange(weeklyWork.siteNo);
      } else {
        document.getElementById("btnSave").style.display = "block";
        document.getElementById("btnUpdate").style.display = "none";
        // document.getElementById("siteNo").value = "";
        document.getElementById("thisWeekPlan").value = "";
        document.getElementById("performance").value = "";
        document.getElementById("nextWeekPlan").value = "";
        document.getElementById("remarks").value = "";

        document.getElementById("resion").innerText = "";
      }
    }
  );
};

const siteListSearch = async () => {
  fnAjax("POST", "", "json", "/site/siteListSearch", "", function (data) {
    siteList = data;
    console.log(siteList);
    siteList.map((s) => {
      const option = $(
        "<label for=" +
          s.siteNo +
          ">" +
          "<input type='checkbox' name='siteNo' class='siteCheck' id='checkBox" +
          s.siteNo +
          "'" +
          "value=" +
          s.siteNo +
          ">" +
          s.site +
          "</label>"
      );

      $("#checkboxes").append(option);
    });
    $("#checkboxes").on("click", ".siteCheck", function (e) {
      if (!projectList.includes(e.target.value)) {
        projectList.push(e.target.value);
      } else {
        projectList.splice(projectList.indexOf(e.target.value), 1);
      }
      const siteName = [];
      const resionName = [];
      siteList.map((s) => {
        if (projectList.includes(s.siteNo)) {
          siteName.push(s.site);
          resionName.push(s.resion);
        }
      });
      if (projectList.length) {
        document.getElementById("selectText").innerText = siteName.join(", ");
        document.getElementById("resion").innerText = resionName.join(", ");
      } else {
        document.getElementById("selectText").innerText = "선택";
        document.getElementById("resion").innerText = "근무지";
      }
    });
  });
};

// 프로젝트 selectBox 변경
const siteChange = (value) => {
  const resionList = value.split(",");
  const siteName = [];
  const resionName = [];

  siteList.map((s) => {
    if (resionList.includes(s.siteNo)) {
      projectList.push(s.siteNo);
      siteName.push(s.site);
      resionName.push(s.resion);
      document.getElementById("checkBox" + s.siteNo).checked = true;
    }
  });
  document.getElementById("selectText").innerText = siteName.join(", ");
  document.getElementById("resion").innerText = resionName.join(", ");
};

// 왼쪽버튼 클릭
const leftButton = () => {
  const date = selectDate.split("-");
  const processDate = new Date(date[0], date[1] * 1 - 1, date[2] * 1);
  processDate.setDate(processDate.getDate() - 7);

  selectDate = dateChange(processDate);
  getWeekOf();
};

// 오른쪽버튼 클릭
const rightButton = () => {
  const date = selectDate.split("-");
  const processDate = new Date(date[0], date[1] * 1 - 1, date[2] * 1);
  processDate.setDate(processDate.getDate() + 7);

  selectDate = dateChange(processDate);
  getWeekOf();
};

// 날짜 형변환
const dateChange = (date) => {
  const year = date.getFullYear();
  const month =
    date.getMonth() + 1 < 10
      ? "0" + (date.getMonth() + 1)
      : date.getMonth() + 1;
  const day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
  return year + "-" + month + "-" + day;
};

// 날짜 범위 형태로 변환
const planDate = (sdt, edt) => {
  return (
    "(" +
    sdt.substr(5, 2) +
    "." +
    sdt.substr(8) +
    " - " +
    edt.substr(5, 2) +
    "." +
    edt.substr(8) +
    ")"
  );
};

// 저장버튼 클릭
const weeklyWorkSave = () => {
  const data = $("form[name=form_weeklyWorkWrite]").serialize();
  fnAjax("POST", "", "", "/workweek/weeklyWorkWrite", data, function () {
    document.getElementById("btnSave").style.display = "none";
    document.getElementById("btnUpdate").style.display = "block";
    openAlertPop();
  });
};

// 수정버튼 클릭
const weeklyWorkUpdate = () => {
  const data = $("form[name=form_weeklyWorkWrite]").serialize();
  console.log(data);
  fnAjax("POST", "", "", "/workweek/weeklyWorkUpdate", data, function () {
    openAlertPop();
  });
};

//팝업 오픈
const openAlertPop = () => {
  document.getElementById("alertPopup_layer").style.display = "block";
};

/*alert 팝업 닫기 */
const closeAlertPop = () => {
  document.getElementById("alertPopup_layer").style.display = "none";
};

//////****************************** */
let expanded = false;

const showCheckboxes = () => {
  const checkboxes = document.getElementById("checkboxes");
  if (!expanded) {
    checkboxes.style.display = "block";
    expanded = true;
  } else {
    checkboxes.style.display = "none";
    expanded = false;
  }
};

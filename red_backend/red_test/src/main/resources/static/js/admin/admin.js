let selectDate = "";
let useYn = [];

const gridObj = new Array();
$(document).ready(async () => {
  const now = new Date();
  selectDate = dateChange(now);

  await commonCodeCall();
  await gridLoad();
  await adminPageListSearch();
});

const commonCodeCall = async () => {
  const comCdList = ["USE_YN"];

  fnCommonCdAjax(comCdList, function (data) {
    useYn = data.filter((s) => s.parentCd === "USE_YN");
  });
  console.log(useYn);
};

const adminPageListSearch = async () => {
  let searchData = $("form[name=form_searchData]").serialize();
  searchData += "&selectDate=" + selectDate;
  fnAjax(
    "POST",
    "",
    "json",
    "/admin/adminPageListSearch",
    searchData,
    function (data) {
      console.log(data);
      gridObj["grid"].resetData(data.adminList);
      document.getElementById("week_no").value = data.weekValue;
    }
  );
};

/**
 *
 * @return void
 */
const gridLoad = async () => {
  // 그리드 생성
  const use_yn = cmmonCodeConversion(useYn);
  console.log("그리드생성중");
  console.log(use_yn);
  const Grid = tui.Grid;
  const grid = new Grid({
    // ================================== 공통 옵션 적용 ==============================
    el: document.getElementById("grid"), // [필수] Container element

    /**
     * [Option] 순번 기능
     * @reference : http://nhn.github.io/tui.grid/latest/tutorial-example11-row-headers
     */
    rowHeaders: [
      {
        type: "rowNum",
        header: "순번",
        width: 50,
      },
    ],

    // ================================== 컬럼 옵션 적용 ==============================
    columns: [
      // [Column-1] 사용자 번호
      {
        header: "사용자 번호", // [필수] 컬럼 이름
        name: "userNo", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        // [선택] 필터 옵션
        filter: {
          type: "text",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      // [Column-2] 사용자 이름
      {
        header: "사용자 이름", // [필수] 컬럼 이름
        name: "userName", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        // [선택] 필터 옵션
        filter: {
          type: "text",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },
      // [Column-3] 사용자 ID
      {
        header: "사용자 ID", // [필수] 컬럼 이름
        name: "userId", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        // [선택] 필터 옵션
        filter: {
          type: "text",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },

      // [Column-4] 팀
      {
        header: "팀", // [필수] 컬럼 이름
        name: "teamName", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        formatter: "listItemText", // [선택] 값을 기반으로 select box 옵션
        // [Option] 필터 옵션
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
        // [Option] select 옵션
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "BS본부", value: "BS" },
              { text: "BI본부", value: "BI" },
              { text: "TS본부", value: "TS" },
              { text: "BC본부", value: "BC" },
            ],
          },
        },
      },

      // [Column-5] 권한
      {
        header: "권한", // [필수] 컬럼 이름
        name: "userAuth", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        formatter: "listItemText", // [선택] 값을 기반으로 select box 옵션
        // [Option] 필터 옵션
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
        // [Option] select 옵션
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "사용자", value: "USER" },
              { text: "관리자", value: "ADMIN" },
            ],
          },
        },
      },

      // [Column-6] 권한명
      {
        header: "권한명", // [필수] 컬럼 이름
        name: "roleName", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        // [선택] 필터 옵션
        filter: {
          type: "text",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },

      // [Column-7] 퇴사유무
      {
        header: "퇴사유무", // [필수] 컬럼 이름
        name: "useYn", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        formatter: "listItemText", // [선택] 값을 기반으로 select box 옵션
        // [Option] 필터 옵션
        filter: {
          type: "select",
          showApplyBtn: true,
          showClearBtn: true,
        },
        // [Option] select 옵션
        editor: {
          type: "select",
          options: {
            listItems: [
              { text: "N", value: "Y" },
              { text: "Y", value: "N" },
            ],
          },
        },
      },

      // [Column-8] 생년월일
      {
        header: "생년월일", // [필수] 컬럼 이름
        name: "birth", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        // [선택] 필터 옵션
        filter: {
          type: "text",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },

      // [Column-9] 번호
      {
        header: "번호", // [필수] 컬럼 이름
        name: "phone", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        // [선택] 필터 옵션
        filter: {
          type: "text",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },

      // [Column-10] 주간업무 작성여부
      {
        header: "금주주간업무", // [필수] 컬럼 이름
        name: "writeYn", // [필수] 컬럼 매핑 이름 값
        align: "center", // [선택] 텍스트 정렬 옵션
        sortable: true, // [선택] 컬럼의 정렬 여부
        resizable: true, // [선택] 컬럼의 리사이즈 여부 옵션
        // [선택] 필터 옵션
        filter: {
          type: "text",
          showApplyBtn: true,
          showClearBtn: true,
        },
      },

      // [Column-11] 사용자 시퀀스 - Hidden
      {
        header: "key", // [필수] 컬럼 이름
        name: "uniqueKey", // [필수] 컬럼 매핑 이름 값
        hidden: true, // [선택] 숨김 여부
      },
    ],
  });

  gridObj["grid"] = grid;
};

/** 공통코드 변환 */
const cmmonCodeConversion = (comCd) => {
  console.log("공통코드변환");
  const conversion = comCd.map((s) => {
    return { value: s.comCd, text: s.comNm };
  });

  return conversion;
};

const saveBtnClick = () => {
  if (!gridObj["grid"].isModified()) {
    console.log("변경된게 없는데?");
    gridObj["grid"].focusAt(0, 0);
    return;
  }

  const params = {
    adminList: JSON.stringify(gridObj["grid"].getModifiedRows().updatedRows),
  };
  fnAjax(
    "POST",
    true,
    "",
    "/admin/adminListModify",
    params.adminList,
    function (data) {
      console.log(data);
      openAlertPop();
    }
  );
};

// 왼쪽버튼 클릭
const leftButton = () => {
  const date = selectDate.split("-");
  const processDate = new Date(date[0], date[1] * 1 - 1, date[2] * 1);
  processDate.setDate(processDate.getDate() - 7);

  selectDate = dateChange(processDate);
  adminPageListSearch();
};

// 오른쪽버튼 클릭
const rightButton = () => {
  const date = selectDate.split("-");
  const processDate = new Date(date[0], date[1] * 1 - 1, date[2] * 1);
  processDate.setDate(processDate.getDate() + 7);

  selectDate = dateChange(processDate);
  adminPageListSearch();
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

//팝업 오픈
const openAlertPop = () => {
  document.getElementById("alertPopup_layer").style.display = "block";
};

/*alert 팝업 닫기 */
const closeAlertPop = () => {
  document.getElementById("alertPopup_layer").style.display = "none";
};

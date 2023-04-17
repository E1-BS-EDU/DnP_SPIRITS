let gubun; // 저장 save 수정 update
let comCd;
let parentNo = 1;
let buttonGubun = "parent"; // 상위저장 parent 하위저장 child

$(document).ready(function () {
  gubun = location.href.split("gubun=")[1];
  comCd = location.href.split("comCd=")[1];

  const btnElement = document.getElementById("btnSave");

  parentCdListSearch();

  if (gubun == "save") {
    btnElement.innerText = "저장";
  } else {
    btnElement.innerText = "수정";
    detailSearch(comCd);
  }

  $(".parent_add_btn").click(function () {
    parentAddRow();
    document.getElementById("childContainer").style.display = "none";
    document.getElementById("parentCd").value = "";
    buttonGubun = "parent";
  });
});

/*상위코드 리스트 조회 */
const parentCdListSearch = () => {
  fnAjax("POST", "", "json", "/admin/commonCdMList", "", function (data) {
    parentCdList = data;
    parentCdList.map((s) => {
      const option = $(
        "<option value=" + s.parentCd + ">" + s.parentCd + "</option>"
      );
      $("#parentCd").append(option);
    });
    document.getElementById("childContainer").style.display = "none";
    parentAddRow();
  });
};

const parentChange = (value) => {
  if (value !== "") {
    document.getElementById("materCd").value = value;
    document.getElementById("childContainer").style.display = "block";
    const parentLength = document.getElementsByClassName("parent_form").length;

    for (var i = 0; i < parentLength; i++) {
      document.getElementById("parent_form" + (i + 1)).remove();
    }
    parentNo = 1;
    buttonGubun = "child";
  } else {
    document.getElementById("childContainer").style.display = "none";
    parentAddRow();
    buttonGubun = "parent";
  }
};

/*상위컬럼 저장 함수 */
const parentAddRow = () => {
  const html =
    '<div class = "parent_form" id="parent_form' +
    parentNo +
    '"><div class="div_container1"><label>코드</label><label id="idBlind_' +
    parentNo +
    '" class="error_next_box"></label><input type="text" id= "commonList_' +
    parentNo +
    '" name="commonList[' +
    (parentNo - 1) +
    '].comCd" class="form-control" /><label>코드명</label><input type="text" name="commonList[' +
    (parentNo - 1) +
    '].comNm" class="form-control" /><hr width="100%" /></div><div class="div_container2"><span><div><button class="btn_delete" onClick="parentDelRow(' +
    parentNo +
    ')">-</button></div></span></div></div>';

  $(".parent_add_container").append(html);
  parentNo++;
};

/*상위컬럼 삭제 함수 */
const parentDelRow = (parentNo) => {
  $("#parent_form" + parentNo).remove();
};

/*공통코드 상세 조회 */
const detailSearch = (comCd) => {
  fnAjax(
    "POST",
    "",
    "json",
    "/admin/commonCdDDetail",
    { comCd: comCd },
    function (data) {
      $("#parentCd").val(data.parentCd).trigger("change");
      $("#parentCd").attr("disabled", true);
      $("input[name=comCd]").attr("value", data.comCd);
      $("input[name=comCd]").attr("disabled", true);
      $("input[name=comNm]").attr("value", data.comNm);
      $("input[name=etc]").attr("value", data.etc);
      $("#content").val(data.etcDesc);
      $("input[name=parentCd]").attr("value", data.parentCd);
    }
  );
};

/*유효성 검사 체크 함수 */
const validation = () => {
  if (buttonGubun === "child") {
    const comCd = $("input[name=comCd]").val();
    const oMsg = $("#idBlind");

    if (comCd === undefined || comCd === "") {
      showErrorMsg(oMsg, "코드는 필수 입력입니다.");
      return false;
    }
  } else {
    const parentLength = document.getElementsByClassName("parent_form").length;

    for (var i = 0; i < parentLength; i++) {
      const parentCd = $("#commonList_" + (i + 1)).val();
      const oMsg = $("#idBlind_" + (i + 1));
      if (parentCd === undefined || parentCd === "") {
        showErrorMsg(oMsg, "코드는 필수 입력입니다.");
        return false;
      }
    }
  }
  save();
};

/*공통코드 저장*/
const save = () => {
  let post_data = "";
  let url = "";

  if ($("input[name=parentCd]").value === "undefined") {
    return;
  }

  if (buttonGubun === "child") {
    $("input[name=parentCd]").removeAttr("disabled");
    post_data = $("form[name=commonChildForm]").serialize();
    url = "/admin/commonCdDSave";
  } else {
    post_data = $("form[name=commonForm]").serialize();
    url = "/admin/commonCdMSave";
  }

  post_data += "&gubun=" + gubun;
  post_data += "&buttonGubun=" + buttonGubun;

  fnAjax("POST", "", "text", url, post_data, function (data) {
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
  window.location.href = "/admin/commonCdPage";
};

/*유효성 에러메세지 함수 */
const showErrorMsg = (obj, msg) => {
  obj.attr("class", "error_next_box");
  obj.html(msg);
  obj.show();
};

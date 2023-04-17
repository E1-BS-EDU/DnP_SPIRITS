var idFlag = false;
var submitFlag = false;

$(document).ready(function () {
  $("#fr_signUp").submit(function () {
    console.log("submit");
    if (!checkPoint()) {
      return false;
    }

    return true;
  });

  $("#userId").blur(function () {
    idFlag = false;
    checkId();
  });

  $("#userPw").blur(function () {
    checkName();
  });

  $("#userPw2").blur(function () {
    checkpwd2();
  });

  $("#teamName").blur(function () {
    checkTeam();
  });

  $("#birth").blur(function () {
    checkBirthday();
  });

  $("#birth").keyup(function () {
    autoBirthday();
  });

  $("#phone").blur(function () {
    checkHpp();
  });

  $("#phone").keyup(function () {
    autoHpp();
  });
});

function checkId() {
  if (idFlag) return true;

  var id = $("#userId").val();
  var oMsg = $("#idBlind");
  var oInput = $("#userId");

  if (id == "") {
    showErrorMsg(oMsg, "필수 정보입니다.");
    setFocusToInputObject(oInput);
    return false;
  }

  var isID = /^[a-z0-9][a-z0-9_\-]{4,19}$/;
  if (!isID.test(id)) {
    showErrorMsg(
      oMsg,
      "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다."
    );
    setFocusToInputObject(oInput);
    return false;
  }

  idFlag = false;

  fnAjax("GET", "", "", "/login/checkId/" + id, "", function (data) {
    if (data.rtn == "N") {
      showSuccMsg(oMsg, "사용가능한 ID 입니다.");
      idFlag = true;
    } else {
      showErrorMsg(oMsg, "이미 사용중이거나 탈퇴한 아이디입니다.");
      setFocusToInputObject(oInput);
    }
  });
  return true;
}

function checkName() {
  var name = $("#userName").val();
  var oMsg = $("#nameBlind");
  var oInput = $("#userName");
  var nonchar = /[^가-힣a-zA-Z0-9]/gi;

  if (name == "") {
    showErrorMsg(oMsg, "필수 정보입니다.");
    setFocusToInputObject(oInput);
    return false;
  }

  if (name != "" && nonchar.test(name)) {
    showErrorMsg(
      oMsg,
      "한글과 영문 대 소문자를 사용하세요. (특수기호, 공백 사용 불가)"
    );
    setFocusToInputObject(oInput);
    return false;
  }

  hideMsg(oMsg);

  return true;
}

function checkpwd2() {
  var pwd = $("#userPw").val();
  var pwd2 = $("#userPw2").val();
  var oMsg = $("#pwBlind");
  var oInput = $("#userPw2");

  if (pwd != pwd2) {
    showErrorMsg(oMsg, "비밀번호가 일치하지 않습니다.");
    setFocusToInputObject(oInput);
    return false;
  }

  hideMsg(oMsg);
  return true;
}

function checkBirthday() {
  var birth = $("#birth").val();
  var oMsg = $("#birthBlind");
  var oInput = $("#birth");

  if (birth == "") {
    showErrorMsg(oMsg, "필수 정보입니다.");
    setFocusToInputObject(oInput);
    return false;
  }

  return true;
}

function checkTeam() {
  var str = $("#teamName").val();
  var oMsg = $("#teamBlind");
  var oInput = $("#teamName");

  if (str.length == 0) {
    showErrorMsg(oMsg, "필수 정보입니다.");
    setFocusToInputObject(oInput);
    return false;
  }

  return true;
}

function autoBirthday() {
  var str = $("#birth").val();
  var oInput = $("#birth");

  str = str.replace(/[^0-9]/g, "");

  var rtn = "";
  var tmp = "";
  if (str.length < 5) {
    rtn = str;
  } else if (str.length < 8) {
    tmp += str.substr(0, 4);
    tmp += ".";
    tmp += str.substr(4);
    rtn = tmp;
  } else {
    tmp += str.substr(0, 4);
    tmp += ".";
    tmp += str.substr(4, 2);
    tmp += ".";
    tmp += str.substr(6);
    rtn = tmp;
  }

  oInput.val(rtn);
}

function checkHpp() {
  var str = $("#phone").val();
  str = str.replace(/[^0-9]/g, "");

  if (str.length == 10 || str.length == 11) {
  } else {
    return false;
  }

  return true;
}
function autoHpp() {
  var str = $("#phone").val();
  var oInput = $("#phone");

  str = str.replace(/[^0-9]/g, "");

  var rtn = "";
  var tmp = "";
  if (str.length < 4) {
    rtn = str;
  } else if (str.length < 7) {
    tmp += str.substr(0, 3);
    tmp += ".";
    tmp += str.substr(3);
    rtn = tmp;
  } else if (str.length < 11) {
    tmp += str.substr(0, 3);
    tmp += ".";
    tmp += str.substr(3, 3);
    tmp += ".";
    tmp += str.substr(6);
    rtn = tmp;
  } else {
    tmp += str.substr(0, 3);
    tmp += ".";
    tmp += str.substr(3, 4);
    tmp += ".";
    tmp += str.substr(7);
    rtn = tmp;
  }

  oInput.val(rtn);
}

function checkPoint() {
  if (
    checkId() &&
    checkName() &&
    checkpwd2() &&
    checkTeam() &&
    checkBirthday() &&
    checkHpp()
  ) {
    console.log("pass");
    return true;
  } else {
    console.log("No Pass.");
    return false;
  }
}

function hideMsg(obj) {
  obj.hide();
}

function showSuccMsg(obj, msg) {
  obj.attr("class", "succ_next_box");
  obj.html(msg);
  obj.show();
}

function showErrorMsg(obj, msg) {
  obj.attr("class", "error_next_box");
  obj.html(msg);
  obj.show();
}

function setFocusToInputObject(obj) {
  if (submitFlag) {
    submitFlag = false;
    obj.focus();
  }
}

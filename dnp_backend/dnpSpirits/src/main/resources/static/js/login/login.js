$(document).ready(function () {
  $(".btn_login").click(function () {
    console.log("button click");
    fn_login_process();
  });

  $("#password").keydown(function (key) {
    console.log("password enter");
    if (key.keyCode == 13) {
      console.log("Enter");
      fn_login_process();
    }
  });

  $("#btn_logout").click(function () {
    fn_logout_process();
  });
});

function fn_login_process() {
  // var email = $("#username").val();

  // // 검증에 사용할 정규식 변수 regExp에 저장
  // var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

  // if (email.match(regExp) != null) {
  // }
  // else {
  // 	alert("이메일 형식이 올바르지 않습니다.");
  // 	return;
  // }

  $("form").submit();
}

function fn_logout_process() {
  $("form").submit();
}

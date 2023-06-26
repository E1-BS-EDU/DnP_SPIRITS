$(document).ready(function () {
  $(".list_box").click(function () {
    if ($(this).attr("name") == "setting") {
      fn_href("/home/adminTabpage");
    } else {
      localStorage.setItem("gubun", $(this).attr("name"));
      fn_href("/home/mainTabpage");
    }
  });
});

function fn_href(url) {
  $("html").fadeIn(500, function () {
    $(this).animate(
      {
        top: "150px",
      },
      300
    );
  });

  $("html").animate(
    {
      opacity: "0",
      top: "100px",
    },
    300,
    function () {
      document.location.href = url;
    }
  );
}

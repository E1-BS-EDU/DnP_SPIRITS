var g_xml;
var pageGubun;
$(document).ready(function () {
  pageGubun = localStorage.getItem("gubun");
  console.log("pageGubun:" + pageGubun);
  /*탭 메뉴 파일 */
  $.ajax({
    url: "/xml/adminMenu.xml",
    type: "GET",
    dataType: "xml",
    success: function (response) {
      xmlParsing(response);
    },
    error: function (xhr, status, msg) {
      console.log("상태값 : " + status + " http 에러 : " + msg);
    },
  });
});

var g_xml;
/*탭 페이지 생성 */
function xmlParsing(xml) {
  let str = "";
  g_xml = xml;

  $(xml)
    .find("topMenu")
    .find("item")
    .each(function () {
      // item 수만큼 loop
      // item 요소 중 title 읽어오기.
      var title = $(this).find("title").text();
      var value = $(this).find("value").text();
      var name = $(this).find("name").text();

      str +=
        "<a href= " +
        value +
        " target='mainfrm' class='" +
        name +
        "'>" +
        title +
        "</a>";
    });

  console.log(str);
  $(".topMenu").empty();
  $(".topMenu").append(str);

  $(".commonCdPage").get(0).click();
}

var g_xml;
var pageGubun;
$(document).ready(async () => {
  pageGubun = localStorage.getItem("gubun");
  /*탭 메뉴 파일 */
  tabMenuFile();

  /*탭 생일자 목록 조회 */
  birthSearch();
});

/*탭 메뉴 파일 */
const tabMenuFile = () => {
  fnAjax("GET", "", "xml", "/xml/menu.xml", "", function (data) {
    xmlParsing(data);
  });
};

/*탭 생일자 목록 조회 */
const birthSearch = () => {
  fnAjax("POST", "", "json", "/home/birthSearch", "", function (data) {
    const topElement = document.getElementById("birthday_list");
    for (var i = 0; i < data.length; i++) {
      topElement.innerHTML +=
        " " + data[i].userName + " (" + data[i].birth + ")";
    }
  });
};

var g_xml;
/*탭 페이지 생성 */
const xmlParsing = (xml) => {
  let str = "";
  g_xml = xml;

  $(xml)
    .find("topMenu")
    .find("item")
    .each(function () {
      // item 수만큼 loop
      // item 요소 중 title 읽어오기.
      const title = $(this).find("title").text();
      const value = $(this).find("value").text();
      const name = $(this).find("name").text();

      str +=
        "<a href= " +
        value +
        " target='mainfrm' class='" +
        name +
        "'>" +
        title +
        "</a>";
    });

  $(".topMenu").empty();
  $(".topMenu").append(str);

  $("." + pageGubun)
    .get(0)
    .click();
};

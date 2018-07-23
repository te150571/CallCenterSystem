(function() {
    'use strict';
    /*今日の日付データを変数todayに格納*/
    var optionLoop, this_day, this_month, this_year, today;
    today = new Date();
    this_year = today.getFullYear();
    this_month = 1;
    this_day = 1;
  
    /*ループ処理（スタート数字、終了数字、表示id名、デフォルト数字）*/
    optionLoop = function(start, end, id, this_day) {
      var i, opt;
  
      opt = null;
      for (i = start; i <= end ; i++) {
        if (i === this_day) {
          opt += "<option value='" + i + "' selected>" + i + "</option>";
        } else {
          opt += "<option value='" + i + "'>" + i + "</option>";
        }
      }
      return document.getElementById(id).innerHTML = opt;
    };
  
  
    /* 関数設定（スタート数字[必須]、終了数字[必須]、表示id名[省略可能]、デフォルト数字[省略可能]）*/
    optionLoop(1950, this_year, 'id_year', this_year-20);
    optionLoop(1, 12, 'id_month', this_month);
    optionLoop(1, 31, 'id_day', this_day);
})();

$(function() {
  $('#table_sort').tablesorter({
      headers: {
        1: {sorter:false}
      }
  });
});

$(document).ready(function(){
  $('textarea[name="tel"]').on('keyup',function(){
    var checkval = $('textarea[name="tel"]').val().replace(/[！-～]/g,
      function (tmpStr) {
        return String.fromCharCode(tmpStr.charCodeAt(0) - 0xFEE0);
      }
    );
    $('textarea[name="tel"]').val(checkval.replace(/[^0-9]/g, ''));
  })
  $('textarea[name="addresscode"]').on('keyup',function(){
    var checkval = $('textarea[name="addresscode"]').val().replace(/[！-～]/g,
      function (tmpStr) {
        return String.fromCharCode(tmpStr.charCodeAt(0) - 0xFEE0);
      }
    );
    $('textarea[name="addresscode"]').val(checkval.replace(/[^0-9]/g, ''));
  })
})
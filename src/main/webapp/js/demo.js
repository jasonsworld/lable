$(document).ready(function(){
  $("#button").click(function(){
    $.ajax({
      url: "se/lable/get",
      type: "post",
      data: {
          ids : "1"
      },
      success: function(data) {
        var $errorcode = data.errorcode;
        if ($errorcode == "0") {
          var $lables = data.lables;
          for (var i=0, l=$lables.length; i<l; i++){
            var $id = $lables[i].id;
            var $name = $lables[i].name;

            var $span0 = $("<span id='delete'>" + "X" + "</span>");
            var $span1 = $("<span id='uid' class='uid'>" + $id + "</span>");
            var $span2 = $("<span id='uname' class='uname'>" + $name + "</span>");

            $("#demo").append($span1);
            $("#demo").append($span2);
            $("#demo").append($span0);
          }
          $("#demo").removeClass("demo")
        }
      }
    })
  })

  $("#demo").delegate("#unmae", "click", function(){
      var $uname_clone = $(this).text();
      alert($uname_clone);
      var $span0 = $("<span id='delete_clone'>" + "X" + "</span>");
      var $span1 = $("<span id='uname_clone'>" + $uname_clone + "</span>");
      $("#stayWhite").append($span0);
      $("#stayWhite").append($span1);
      })

})

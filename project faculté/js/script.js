

 $(function() {
      "use strict";   

       // bind 'myForm' and provide a simple callback function
       $('#regiter').ajaxForm({
           beforeSend:function(){
               
               $("#resualt").html("<img src='images/loding.gif' width='120px'/>");
               
           }, success: function(data) {
            $("#resualt").html(data)
      }
       });
     });



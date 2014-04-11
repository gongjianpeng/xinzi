$.getJSON("http://127.0.0.1:8080/twovs/test/jsonp.json",
function(data){
  alert(data.pi);
});
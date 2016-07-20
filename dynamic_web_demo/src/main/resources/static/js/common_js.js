var restAPI_front_URL = "http://127.0.0.1:8080";

function getQueryStringByName(name){
    var result = location.search.match(new RegExp("[\?\&]" + name+ "=([^\&]+)","i"));
    if(result == null || result.length < 1){
        return "";
    }
    return decodeURI(result[1]);
}




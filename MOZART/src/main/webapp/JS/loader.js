function addSpan(){
    var animation_container = document.querySelector(".animation-container");
    var htmlCode = "";
    for (let index = 0; index < 209; index++) {
        var top = 1;
        if(index == 28 || index==47 || index==66 || index==85 || index==104 || index==123 || index==142 || index==84 || index==83 || index==122 || index==141 || index==101 || index==120 || index==139 || index==158 || index==178 || index==179 || index==180 || index==181 || index==163 || index==144 || index==125 || index==106 || index==29 || index==49 || index==68 ){
            htmlCode += "<span class='active'></span>";
            top = 0;
        }
        if(top==1){
        htmlCode += "<span></span>";
        }
    }
    animation_container.innerHTML = htmlCode;
}
addSpan();
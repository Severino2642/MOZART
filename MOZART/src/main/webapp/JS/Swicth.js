var swicth_next = document.querySelector(".swicth-rigth");
var swicth_prev = document.querySelector(".swicth-left");

let swicth_count = 0;

if (swicth_next != null){
    swicth_next.addEventListener('click' , swicthNext);
}
if (swicth_prev != null){
    swicth_prev.addEventListener('click' , swicthPrev);
}
function swicthNext(){
    var listSection = document.querySelectorAll("#sac section");
    setHidden();
    if (listSection.length-1 <= swicth_count) {
        swicth_next.setAttribute("class",".not-active");
    }
    else{
        swicth_prev.setAttribute("class",".swicth-left");
        swicth_count++;
    }
    setCSS(listSection[swicth_count].getAttribute("cssLink"));
    setSearchVisibility(listSection[swicth_count]);
    listSection[swicth_count].style.display="block";
}
function swicthPrev(){
    var listSection = document.querySelectorAll("#sac section");
    setHidden();
    if (0 >= swicth_count) {
        swicth_prev.setAttribute("class",".not-active");
    }
    else{
        swicth_next.setAttribute("class",".swicth-rigth");
        swicth_count--;
    }
    setCSS(listSection[swicth_count].getAttribute("cssLink"));
    setSearchVisibility(listSection[swicth_count]);
    listSection[swicth_count].style.display="block";
}
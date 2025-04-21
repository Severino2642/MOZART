var allSlides = document.querySelectorAll(".slider-item");
var dots = document.querySelectorAll(".slider-range i");

var bt_prev = document.querySelector(".slider #bt_prev");
var bt_next = document.querySelector(".slider #bt_next");

let count = 0;

bt_next.addEventListener('click' , slideNext);
bt_prev.addEventListener('click' , slidePrev);

function slideNext(){
    allSlides[count].style.animation=" next1 0.5s ease-in forwards";
    if (allSlides.length-1 == count) {
        count = 0;
    }
    else{
        count++;
    }
    allSlides[count].style.animation=" next2 0.5s ease-in forwards";
    indicator();
}
function slidePrev(){
    allSlides[count].style.animation=" prev1 0.5s ease-in forwards";
    if (count == 0) {
        count = allSlides.length - 1;
    }
    else{
        count--;
    }
    allSlides[count].style.animation=" prev2 0.5s ease-in forwards";
    indicator();
}
function autoSliding() {
    deletInterval = setInterval(timer,4000);
    function timer (){
        slideNext();
    }
}
// autoSliding();

//stop auto sliding when mouse is over
const slider_container = document.querySelector(".slider");
slider_container.addEventListener('mouseover',function() {
    clearInterval(deletInterval);    
});

//Resume auto sliding when mouse is out
slider_container.addEventListener('mouseout',autoSliding);

function indicator(){
    for (let index = 0; index < dots.length; index++) {
        dots[index].className = dots[index].className.replace(' active','');        
    }
    dots[count].className += " active"; 
}

function switchSlide(currentSlide){
    currentSlide.classList.add('active');
    var slideId = currentSlide.getAttribute('attr');
    if (slideId > count) {
        allSlides[count].style.animation=" next1 0.5s ease-in forwards";
        count = slideId;
        allSlides[count].style.animation=" next2 0.5s ease-in forwards";
    }
    else if(slideId == count){
        return;
    }
    else{
        allSlides[count].style.animation=" prev1 0.5s ease-in forwards";
        count = slideId;
        allSlides[count].style.animation=" prev2 0.5s ease-in forwards";
    }
    indicator();
}
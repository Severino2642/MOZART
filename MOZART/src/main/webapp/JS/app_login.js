var bt_show = document.querySelector("#show-pwd");
bt_show.addEventListener('click', show_pwd);
function show_pwd(){
    var pwd = document.querySelector(".mdp");
    var type = pwd.getAttribute("type");
    if(type == "password"){
        pwd.setAttribute("type","text");
        bt_show.setAttribute("class","far fa-eye");
    }
    else{
        pwd.setAttribute("type","password");
        bt_show.setAttribute("class","far fa-eye-slash");
    }
}
var songs = [];

let currentMusic = 0;
let changeMusic = 0;
const music = document.querySelector('#audio');

const musicPlayer = document.querySelector('.music-player');
const music_source = document.querySelector('.music-source');
const source_name = document.querySelector('.source-name');
const music_name = document.querySelector('.music-name');
const artiste_name = document.querySelector('.artiste-name');


const seckBar = document.querySelector('.seck-bar');
const disk = document.querySelector('.disk');
const currentTime = document.querySelector('.current-time');
const musicDuration = document.querySelector('.song-duration');
const playBtn = document.querySelector('.play-btn');
const playBtn_icon = document.querySelector('.play-btn i');
const forwardBtn = document.querySelector('.forward-btn');
const backwardBtn = document.querySelector('.backward-btn');
const nextMod = document.querySelector('.nextMod');
const cycleMod = document.querySelector('.cycleMod');
nextMod.addEventListener('click',() =>{
    var attr = nextMod.getAttribute("attr");
    if (attr == 0){
        nextMod.style.color="rgb(0,191,99)";
        nextMod.setAttribute("attr",1);
    }
    if (attr == 1){
        nextMod.style.color="rgb(255,255,255)";
        nextMod.setAttribute("attr",0);
    }
});
cycleMod.addEventListener('click',() =>{
    var attr = cycleMod.getAttribute("attr");
    if (attr == 0){
        cycleMod.style.color="rgb(0,191,99)";
        cycleMod.setAttribute("attr",1);
    }
    if (attr == 1){
        cycleMod.style.color="rgb(255,255,255)";
        cycleMod.setAttribute("attr",0);
    }
});
playBtn.addEventListener('click', () =>{
    var top = 0;
    if(playBtn_icon.className == "fa fa-play"){
        playBtn_icon.className = "fa fa-pause";
        music.play();
        top = 1;
    }
    if(playBtn_icon.className == "fa fa-pause" && top==0){
        playBtn_icon.className = "fa fa-play";
        music.pause();
    }
    disk.classList.toggle('play')
});

const setMusic = (i) => {
    seckBar.value = 0;
    let song = songs[i];
    currentMusic = i;
    changeMusic = 1;
    music.src = "../../music/"+song.music.contenue;
    if (song.album != null){
        music_source.innerHTML = "LECTURE A PARTIR DE L'ALBUM";
        source_name.innerHTML = song.album.title;
    }
    if (song.album == null){
        music_source.innerHTML = "LECTURE A PARTIR DE L'ARTISTE";
        source_name.innerHTML = song.artiste.pseudo;
    }
    music_name.innerHTML = song.music.title;
    artiste_name.innerHTML = song.music.Author;
    disk.style.backgroundImage = "url('../../couverture/"+song.music.couverture+"')";
    currentTime.innerHTML = '00:00';
    setTimeout(() => {
        seckBar.max = music.duration;
        musicDuration.innerHTML = formatTime(music.duration);
    }, 300)
}

const formatTime = (time) => {
    let min = Math.floor(time / 60);
    if (min < 10) {
        min = '0'+min;
    }
    let sec = Math.floor(time % 60);
    if (sec < 10) {
        sec = '0'+sec;
    }
    return ""+min+" : "+sec;
}

setInterval(() => {
    seckBar.value = music.currentTime;
    currentTime.innerHTML = formatTime(music.currentTime);
    if(music.currentTime == music.duration){
        if (playBtn_icon.className == "fa fa-pause"){
            playBtn_icon.className = "fa fa-play";
        }
        if (changeMusic == 1){
            addStream(songs[currentMusic].music.id,user.getAttribute("attr"));
        }
        forward();
        changeMusic = 0;
    }
},500);

seckBar.addEventListener('change',()=> {
    music.currentTime = seckBar.value;
})

const playMusic = () => {
    music.play();
    playBtn_icon.className = "fa fa-pause";
    disk.classList.add('play');
}

forwardBtn.addEventListener('click',forward);

function randomForward(min, max) {
    currentMusic = Math.floor(Math.random() * (max - min + 1) + min);
}
function forward(){
    var nextMethod = nextMod.getAttribute("attr");
    var cycleMethod = cycleMod.getAttribute("attr");
    if (cycleMethod == 0){
        if (nextMethod == 0){
            if (currentMusic >= songs.length - 1) {
                currentMusic = 0;
            }
            else{
                currentMusic++;
            }
        }
        if (nextMethod == 1){
            randomForward(0,songs.length-1);
        }
    }
    setMusic(currentMusic);
    playMusic();
}
backwardBtn.addEventListener('click',backward);
function backward(){
    if (currentMusic <= 0) {
        currentMusic = songs.length;
    }
    else{
        currentMusic--;
    }
    setMusic(currentMusic);
    playMusic();
}

function addSong (newSong) {
    songs.push(newSong);
    setMusic(songs.length-1);
    affMusicPlayer();
}
function addListSong (ListSong) {
    songs.push(...ListSong);
    setMusic(0);
    affMusicPlayer();
}

function playLikedSong(favoriteSong){
    for(var i=0 ; i<favoriteSong.length ; i++){
        songs.push(favoriteSong[i].music);
    }
    setMusic(0);
    affMusicPlayer();
}

function affMusicPlayer(){
    musicPlayer.style.display="block";
    document.querySelector(".split_left").style.display="none";
}
function hideMusicPlayer(){
    musicPlayer.style.display="none";
    document.querySelector(".split_left").style.display="block";
}
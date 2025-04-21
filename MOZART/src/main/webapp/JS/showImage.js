function showBackground(fileInput) {
    var labelClass = "."+fileInput.getAttribute('name')+" label";
    var iconClass = "."+fileInput.getAttribute('name')+" label i";
    var previewImage = document.querySelector(labelClass);
    var icon = document.querySelector(iconClass);
    var file = fileInput.files[0];

    if (file) {
        var reader = new FileReader();

        reader.onload = function (e) {
            previewImage.style.backgroundImage = "url('"+e.target.result+"')";
            icon.style.display="none";
        };

        reader.readAsDataURL(file);
    }
}

function showImage(fileInput) {
    var imageClass = "#preview"+fileInput.getAttribute('name');
    var previewImage = document.querySelector(imageClass);
    var iconClass = "."+fileInput.getAttribute('name')+" label i";
    var icon = document.querySelector(iconClass);

    var file = fileInput.files[0];

    if (file) {
        var reader = new FileReader();

        reader.onload = function (e) {
            previewImage.src = e.target.result;
            icon.style.display="none";
            previewImage.style.display="block";
        };

        reader.readAsDataURL(file);
    }
}
{/* <input type="file" id="fileInput" onchange="showImage()">
<img id="previewImage" src="#" alt="Image Preview"> */}

function processSelectedFile(input)
{
    let imageObjectToTest = new Image();
    let image = input.files[0];
    imageObjectToTest.onload = function ()
    {
        document.getElementById('image-label').innerHTML = 'You selected: ' + image.name;
        document.getElementById("image-label").style.color = "green";
    };
    imageObjectToTest.onerror = function ()
    {
        alert('You paste not an image');
        document.getElementById('image-label').innerHTML = 'Choose an image file';
        document.getElementById("image-label").style.color = "gray";
        input.value = '';
    };
    imageObjectToTest.src = URL.createObjectURL(image);
}
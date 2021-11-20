function concealImageInput()
{
    if (document.getElementById('delete-previous-image').checked)
    {
        document.getElementById('select-image-block').style.display = 'none';
    }
    else
    {
        document.getElementById('select-image-block').style.display = 'block';
    }
}
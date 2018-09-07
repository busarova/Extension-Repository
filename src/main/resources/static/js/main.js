/*
$(document).ready(function() {
    $.ajax({
        url: "http://api/tags"
    }).then(function(data) {
        $('#tags').html('ASDAdas');
      //  $('.tag-owner').append(data.content);
    });
});


$('#tags').html('ASDAdas');*/

hide();

function hide() {
    $("#profilePicForm").toggle();
    $("#editDescriptionForm").toggle();
    $("#editTagsForm").toggle();
    $("#editNameForm").toggle();
    $("#extensionLogoForm").toggle();

}

$('.profilePic').click(function () {

    $('#profilePicForm').toggle();

});

$('#showEditDescription').click(function () {

    $('#editDescriptionForm').toggle();

});

$('#showEditTags').click(function () {

    $('#editTagsForm').toggle();

});

$('#showEditName').click(function () {

    $('#editNameForm').toggle();

});

$('#showExtensionsLogoForm').click(function () {

    $('#extensionLogoForm').toggle();

});

$(document).ready(function(e){
    $('.search-panel .dropdown-menu').find('a').click(function(e) {
        e.preventDefault();
        var param = $(this).attr("href").replace("#","");
        var concept = $(this).text();
        $('.search-panel span#search_concept').text(concept);
        $('.input-group #search_param').val(param);
    });
});


$().ready(function() {
    $("#card").flip({
        trigger: 'manual'
    });
});

// Log in/Register Flip
$(".signup_link").click(function() {

    $(".signin_form").css('opacity', '0');
    $(".signup_form").css('opacity', '100');


    $("#card").flip(true);

    return false;
});

$("#unflip-btn").click(function(){

    $(".signin_form").css('opacity', '100');
    $(".signup_form").css('opacity', '0');

    $("#card").flip(false);

    return false;

});




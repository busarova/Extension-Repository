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

function hide(){
    $("#profilePicForm").toggle();
    $("#editDescriptionForm").toggle();
    $("#editTagsForm").toggle();
    $("#editNameForm").toggle();

}

$('.profilePic').click(function(){

    $('#profilePicForm').toggle();

});

$('#showEditDescription').click(function(){

    $('#editDescriptionForm').toggle();

});

$('#showEditTags').click(function(){

    $('#editTagsForm').toggle();

});

$('#showEditName').click(function(){

    $('#editNameForm').toggle();

});




//display main image and mini-images on page load
$(function() {
	$('.arrow, [class^=arrow-]').bootstrapArrows();
	$.ajax({
    type: 'GET',
    url: '../controller/getpictures.php',
	data: 'mode=' + 'get',
    dataType: 'json',
    success: function(data){
		//console.log(data);
      //var temp = $.parseJSON(data);
	  var x=true;
	  $.each(data, function(key,value) {
		$('#picture_rows').append('<img id="images" height="48px" width="72px" src="'+value+'">');
		if (x){
			$('#main_content').append('<img id="main_image" height="300px" width="390" src="'+value+'">');
			x=false;
		}
	  });
    },
    error: function(resp) {}
  });
})

//fading animation in switching between main images
$(document).on('click','#images',function() {
	var substitute = $(this).prop('src');
	console.log(substitute);
	$('#main_content').fadeOut(1000,function(){ 
		$('#main_content').html('<img id="main_image" height="300px" width="390" src="'+substitute+'">').fadeIn(1000); 
	});
	//$('#main_image').replaceWith('<img id="main_image" height="210px" width="390" src="'+substitute+'">').fadeIn('slow');
	//$('#main_image').fadeIn('slow');
});

//div should scroll up and display more images on arrow click
$(document).on('click','#arrow2',function(event) {
	event.preventDefault();
	setTimeout(function() {
	$('#picture_rows').animate({
		scrollTop:
			$('#picture_rows').scrollTop()+48},
	1000);
	},21);
});

/*$('.arrow2').on('click',function(event) {
	//event.preventDefault();
	$('#picture_rows').delay(50).animate({
		scrollTop:
			$('#picture_rows').scrollTop()+60},
			//50},
	1000);
	return false;
});*/

$(document).on('click','#arrow',function(event) {
	event.preventDefault();
	$('#picture_rows').animate({
		scrollTop:
			$('#picture_rows').scrollTop()-48},
	1000);
	return false;
	/*$('#picture_rows').animate({
		scrollTop:
			$('#picture_rows').position().top *(-1)},
	1000);
	event.preventDefault();*/
});

//show/hide uploading form on button click
var global_boolean = false;
$(document).on('click','#uploader',function() {
	if (global_boolean == false){
		$('#upload_files').fadeIn()
			.css('display','block');
		global_boolean = true;
	}
	else{
		$('#upload_files').fadeOut()
			.css('display', 'none');
		global_boolean = false;
	}
});


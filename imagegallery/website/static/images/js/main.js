//display main image and mini-images on page load

$(function() {
	$('.arrow, [class^=arrow-]').bootstrapArrows();
	console.log('ehh');
	$.ajax({
    type: 'GET',
    url: '/first/load_images/?mode=get',
	//data: 'mode=' + 'get',
    dataType: 'json',
    success: function(data){
	  var x=true;
	  //$('#picture_rows').append('<img id="images" height="48px" width="72px" src="'+data.file+'">');
	  console.log(data.length);
	  console.log(data);
	  console.log(data[0].file);
	  var array = data.split("'file'");
	  for (var index=1;index<array.length;index++){
	  	console.log(array[index]);
	  	$('#picture_rows').append('<img id="images" height="48px" width="72px" src="'+array[index]+'">');
		if (x){
			$('#main_content').append('<img id="main_image" height="300px" width="390" src="'+array[index]+'">');
			x=false;
		}
	  }
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

//also want to make when hover on screen at far right, file upload form slides in
var monitor_width = 0.21 * $(window).width();
//console.log(monitor_width);
//i need a hovering item for point of reference
var lastMouse = new Date().getTime();
console.log(lastMouse);
$(document).on('mousemove', function(event) {
	//console.log(event.pageX);
	
	var current = new Date().getTime();
	if (current - lastMouse > 300){
		var mouse_location = event.pageX;
		lastMouse = current;
		if (mouse_location < monitor_width){
			$('#fade_in').animate({
				//left: parseInt($('#fade_in').css('left'),10) == 0 ?
					//-$('#fade_in').outerWidth() : 0
				left: 0
			});//add delay
		}
		if (mouse_location > monitor_width){
			$('#fade_in').animate({
				//left: parseInt($('#fade_in').css('left'),10) == 0 ?
					//-$('#fade_in').outerWidth() : 0
				
				left: -$('#fade_in').outerWidth()
			});//add delay
		}
	}
	
})

//$('#fade_in').stop(true,true).fadeIn({duration: 30, queue: false}).css('display', 'none').animate({
			//right: parseInt($('#fade_in').css('right'),10) ==0 ? -$('#fade_in').outerWidth() : 0
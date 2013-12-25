$('#form-login').click(function() {
	var username = $('#username').val();
	var password = $('#password').val();
	$.ajax({
		type: 'POST',
		url: '../controller/login.php',
		data:{set:'login', username: username, password: password},
		dataType: 'json',
		beforeSend: function() {
			$('#form-login').prop('disabled', true);
			console.log('hi');
		},
		error: function(resp){
			$('#error').html('Username or password is incorrect.');
			$('#error').css('color','red');
			$('#form-login').prop('disabled', false);
			console.log('hi');
		},
		success: function(resp){
		console.log(resp);
			//var temp = $.parseJSON(resp);
			if (resp.login=='success')
				window.location.href = 'main.php';
			else{
				$('#error').html('Username or password is incorrect.');
				$('#error').css('color','red');
				$('#form-login').prop('disabled', false);
			}
			console.log('hi');
		}
		
	});
}); //gotta make the database schema and the login controller.
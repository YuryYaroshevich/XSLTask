$(document).ready(function() {	
	$("input[value='CANCEL']").click(function() {
		$("form input[name='command']").attr('value','CANCEL');
	});
});

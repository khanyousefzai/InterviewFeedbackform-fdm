
function split(val) {
	return val.split(/,\s*/);
}
function extractLast(term) {
	return split(term).pop();
}
$(document).ready(function() {
	
//	var questionClone = $('.Question').clone(),
//	main = $('#question');
//
//	$('#questionAdd').click(function (){
//		main.append(questionClone.clone());
//		return false;
//	});
	
	$( "#questionListPopup" ).autocomplete({
		source: '/getQuestionList'
	});
	
	$( "#tagListPopup" ).autocomplete({
		source: '/getTagList'
	});
	
	$( "#client" ).autocomplete({
		source: '/getClientList'
	});
	
	$( "#role" ).autocomplete({
		source: '/getRoleList'
	});
	
	$( "#location" ).autocomplete({
		source: '/getRoleLocations'
	});
	
	$( "#username" ).autocomplete({
		source: '/getUsernameList'
	})
});



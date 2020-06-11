var num=1;
var stringToHTML = function (str) {

	var dom = document.createElement('div');
	dom.innerHTML = str;

	return dom;
};
var stringToHTML2 = function (str,name) {

	var dom = document.createElement('input');
	dom.readOnly=true;
	dom.id = str;
	dom.name = name;
	dom.classList.add("insideTag");
	dom.value = str;
	return dom;
};

var stringToHTML3 = function (str,name,n) {

	var dom = document.createElement('input');
	dom.readOnly = true;
	dom.name = name;
	dom.id = "selectTag"+n;
	dom.classList.add("insideTag");
	dom.value = str;
	return dom;
};

function addTag(n){
	let set = document.getElementById("tagList"+n);
	let input = document.getElementById("tagInput"+n).value;

	if(input != ''){

		console.log(input);
		console.log("tagList"+n);

		let already = document.getElementById(input);
		if(!set.contains(already)){
			set.appendChild(stringToHTML2(input,"insideTag"));
		}
		document.getElementById("tagInput"+n).value = '';

	}

};

function addTagSelect(n){
	let set = document.getElementById("tagList"+n);
	let input = document.getElementById("interview_type"+n).value;
	console.log(input);
	console.log("tagList"+n);
	let already = document.getElementById("selectTag"+n);
	if(!set.contains(already)){
		set.appendChild(stringToHTML3(input,"insideTag",n));
	}else{
		already.value = input;
	}

};

function enter(e,n){
	if (e.keyCode == 13) {
		e.preventDefault();
		let set = document.getElementById("tagList"+n);
		let input = document.getElementById("tagInput"+n).value;
		if(input != ''){
			console.log(input);
			console.log("tagList"+n);
			let already = document.getElementById(input);
			document.getElementById("tagInput"+n).value = '';
			if(!set.contains(already)){
				set.appendChild(stringToHTML2(input,"insideTag"));

			}
	    }
	}

}

function disableEnter(e){
	if(e.keyCode == 13){
		e.preventDefault();
	}

}

function add(){
	var questionFormat =
		  '<div class="Question top_item">'+
			'<span>Question '+ num +'</span>'+
			'<div class="Question_container">'+
			 ' <div class ="field_wrapper  ">'+
			   ' <label for="interview_type" class="field_Label">Interview Type</label>'+
			    '<select class="field_full" name="" id="interview_type'+num+'" onchange="addTagSelect('+num+');">'+
			      '<option  value=""> Select question type </option>'+
			      '<option  value="Technical"> Technical </option>'+
			      '<option  value="Behavioural"> Behavioural </option>'+
			    '</select>'+
	  			'</div>'+

			  '<div class ="field_wrapper  ">'+

			    '<label for="lname" class="field_Label">Interview Tag(s)</label>'+
			   ' <input onfocusout="addTag('+num+')" onkeypress="return enter(event,'+num+')" type="text" spellcheck="true" class="field field_full" id="tagInput'+num+'" name="password">'+
			   '<div class="tagDiv field_full" id="tagList'+num+'" name = "tagList'+num+'"></div>' +
			   '<input name="insideTag" value = "'+num+'" type= "hidden" readonly>'+
			  '</div>'+

			 ' <div class ="field_wrapper  ">'+
			    '<label for="lname" class="field_Label">Interview question</label>'+
			    '<input type="text" class="field field_full qbody" spellcheck="true" id="lname" name="qbody">'+
			  '</div>'+
			'</div>'+

		'</div>';

    let set = document.getElementById("question");
    set.appendChild(stringToHTML(questionFormat));
    num=num+1;

 };


 function print(){

	let set = document.getElementsByClassName("qbody");
	console.log(set);
	Array.from(set).forEach(function(element) {
         console.log(element)
    });

}
 
 function loadDate(){
		let today = new Date();
		document.getElementById("interviewDate").valueAsDate = today;
		
		var dd = today.getDate();
		var mm = today.getMonth()+1;
		var yyyy = today.getFullYear();
		 if(dd<10){
		        dd='0'+dd
		    } 
		    if(mm<10){
		        mm='0'+mm
		    } 

		todayDate = yyyy+'-'+mm+'-'+dd;
		document.getElementById("interviewDate").setAttribute("max", todayDate);
}

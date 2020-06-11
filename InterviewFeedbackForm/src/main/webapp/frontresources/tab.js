function changeTabQuestion(){
  let form = document.getElementById("formTab");
  let question = document.getElementById("questionTab");

  form.classList.remove('background');
  form.classList.add('backgroundGrey');

  question.classList.remove('backgroundGrey');
  question.classList.add('background');
}

function changeTabForm(){
  let form = document.getElementById("formTab");
  let question = document.getElementById("questionTab");


  question.classList.remove('background');
  question.classList.add('backgroundGrey');
  form.classList.add('background');
  form.classList.remove('backgroundGrey');

}

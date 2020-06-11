function showLogs(id) {
 
  var x = document.getElementById("logs_" + id);
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}
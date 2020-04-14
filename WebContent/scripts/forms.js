function openForm(id) {
                element = document.getElementById(id);
                style = element.style;
                style.display = "block";
    width = element.clientWidth;
    height = element.clientHeight;
    style.marginTop = Math.floor(-height / 2) + "px";
    style.marginLeft = Math.floor(-width / 2)+ "px";
}

function closeForm(id) {
    document.getElementById(id).style.display = "none";
    document.getElementById("shadowing").style.display = "none";
}

function hideOrShowShadowing(id) {
    element = document.getElementById("shadowing");
    if (element.style.display === "block") {
        element.style.display = "none";
        closeForm(id);
    } else {
    	element.style.display = "block";
    	openForm(id);
    }
}

function hideAll() {
	cols = document.getElementsByClassName('popup');
	for(i = 0; i < cols.length; i++) {
		cols[i].style.display = "none";
	}
	document.getElementById("shadowing").style.display = "none";
}
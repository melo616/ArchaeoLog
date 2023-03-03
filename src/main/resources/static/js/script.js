/**
 * 
 */
const images = [
	'/images/machupichu.jpeg',
	'/images/ruins.jpeg',
	'/images/ruins2.jpeg',
	'/images/city.png'
]

let img = document.getElementById("img");

function imageDisplay() {
	let num = Math.floor(Math.random() * 4)
	img.style.backgroundImage = 'url("' + images[num] + '")';
}
imageDisplay();

function showHide() {
	let content = document.getElementById("participantList");
	if (content.style.display === "none") {
		content.style.display = "block";
	} else {
		content.style.display = "none";
	}
}
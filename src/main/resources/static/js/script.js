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

//alert to confirm dig delete
function deleteDigAlert(id) {
	if(confirm("Are you sure you want to delete this dig? All associated artifacts will also be deleted. This action cannot be undone.")) {
		fetch("/digs/" + id + "/delete", {
			method: 'DELETE'
		})
		.then(response => {
			if(response.ok) {
				window.location.href="/home";
			} else {
				throw new Error("Something went wrong.");
			}
		})
		.catch(error => {
			console.error(error);
		})
	} 
	
}
function validatePhone(input) {

	let phoneno = input.value.trim();
	if (isNaN(phoneno)) {
		input.setCustomValidity("Phone no must be Integer" + phoneno);
	}
	else if (phoneno.length != 10) {
		input.setCustomValidity("Phone no must be of length 10 " + phoneno);
	}
	else {
		input.setCustomValidity("");
	}
}
function validateName(input) {
	let maxlength = 10;
	var name = input.value.trim();
	if (name.length > maxlength) {
		input.setCustomValidity("Name field must be 10 character at max");
	}
	else {
		input.setCustomValidity("");
	}
}
function validateGender(input) {
	let allowedValues = ['M', 'F'];
	let gender = input.value.trim().toUpperCase();
	if (!allowedValues.includes(gender)) {
		input.setCustomValidity("Gender field must either be M or F values only");
	}
	else {
		input.setCustomValidity("");
	}
	input.value = gender;
}
function validateAge(input) {
	let age = parseInt(input.value.trim());
	if (isNaN(age) || age < 1 || age > 125) {
		input.setCustomValidity("Invalid Age. Enter a value in range(1-125)");
	}
	else {
		input.setCustomValidity("");
	}

}
function validatePAN(input) {

	let pan = input.value.trim();
	 if (pan.length != 10) {
		input.setCustomValidity("PAN must be of length 10 " + pan.length);
	}
}
function scrollToContent() {
  var element = document.getElementById("propcontent");
  element.scrollIntoView({ behavior: "smooth" });
}



// display active item
let item = document.querySelectorAll(".item");
let subitem = document.querySelectorAll(".sub-item");
for (let i = 0; i < item.length; i++) {
	item[i].onclick = function() {

		let j = 0;
		let k = 0;
		let list = document.querySelectorAll(".list");
		while (k < subitem.length) {

			subitem[k++].className = "sub-item";
		}
		while (j < list.length) {

			list[j++].className = "list";
		}
		item[i].closest(".list").className = "list active";
	};
}

for (let i = 0; i < subitem.length; i++) {
	subitem[i].onclick = function() {

		let j = 0;
		let k = 0;
		let list = document.querySelectorAll(".list");
		while (k < list.length) {

			list[k++].className = "list";
		}
		while (j < subitem.length) {

			subitem[j++].className = "sub-item";
		}
		subitem[i].className = "sub-item active";
		subitem[i].closest(".list").className = "list active";
	};
}

// toggle on menu button

let menuToggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let content = document.querySelector(".main_content");
let menutoggleclick = function() {
	let subbtn = document.querySelectorAll(".sub-btn");
	menuToggle.classList.toggle("active");
	navigation.classList.toggle("active");
	content.classList.toggle("active");
	//disable dropdown
	for (let i = 0; i < subbtn.length; i++) {
		subbtn[i].classList.toggle("hover-btn")
	}

};
menutoggleclick();
menuToggle.onclick = menutoggleclick;
// show hide dropdown list
$(document).ready(function() {
	$('.sub-btn').click(function() {
		if (!this.classList.contains("hover-btn")) {
			$(this).next('.sub-menu').slideToggle();
			$(this).find('.dropdown').toggleClass('rotate')
		}
	})
})
/*
test();
function test() {
	$
		.get(
			'/sunnyFE/account/getsession',
			function(ketqua) {
				let u = ketqua.user
				if (u == null
					|| !(u.roleId.roleId == 3 || u.roleId.roleId == 1)) {
					window.location.href = "/sunnyFE/account/login";
				}
				if (u.roleId.roleId == 3) {
					$('.authentication-admin').remove()
				}
			})
}*/

function checkfilter(o) {
	return false;
}

function formatmoney(n, currency) {

	const money = n.toString();
	return money.replace(/\B(?=(\d{3})+(?!\d))/g, ',') + currency;
}


//dropdown
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
	document.getElementById("myDropdown").classList
		.toggle("show");
}

function AcountAdmin() {
	document.getElementById("Dropdown").classList
		.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
	if (!event.target.matches('.dropbtn')) {
		var dropdowns = document
			.getElementsByClassName("dropdown-content");
		var i;
		for (i = 0; i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show');
			}
		}
	}
}


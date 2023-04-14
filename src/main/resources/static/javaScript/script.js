function openForm() {
    document.getElementById("loginForm").style.display = "block";
}

function closeForm() {
    document.getElementById("loginForm").style.display = "none";
}

function toggleForm() {
    var loginForm = document.getElementById("loginForm");
    var loginButton = document.getElementById("loginButton");
    if (loginForm.style.display === "block") {
        loginForm.style.display = "none";
        loginButton.classList.remove("hide");
    } else {
        loginForm.style.display = "block";
        loginButton.classList.add("hide");
    }
}
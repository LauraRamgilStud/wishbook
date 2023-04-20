function toggleForm(isLogin) {
    var loginForm = document.getElementById("loginForm");
    var loginButton = document.getElementById("loginButton");
    var cancelButton = document.getElementById("cancel");

    if (isLogin) {
        if (loginForm.style.display === "block") {
            loginForm.style.display = "none";
            cancelButton.style.display = "none";
            loginButton.style.display = "block";
        } else {
            loginForm.style.display = "block";
            cancelButton.style.display = "inline-block";
            loginButton.style.display = "none";
        }
    } else {
        loginForm.style.display = "none";
        cancelButton.style.display = "none";
        loginButton.style.display = "block";
    }
}

document.addEventListener("DOMContentLoaded", function() {
    var form = document.getElementById("loginForm");
    form.style.display = "none";
});

function showMyBooks() {
    document.getElementById("my-books").style.display = "block";
    document.getElementById("books-following").style.display = "none";
}

function showBooksFollowing() {
    document.getElementById("my-books").style.display = "none";
    document.getElementById("books-following").style.display = "block";
}

function showAllBooks() {
    document.getElementById("my-books").style.display = "block";
    document.getElementById("books-following").style.display = "block";
}

function openEditBookForm() {
    document.getElementById("edit-book-form").style.display = "block";
}

function closeEditBookForm() {
    document.getElementById("edit-book-form").style.display = "none";
}

function openCreateBookForm() {
    document.getElementById("create-book-form").style.display = "block";
}

function closeCreateBookForm() {
    document.getElementById("create-book-form").style.display = "none";
}

function openCreateWishForm() {
    document.getElementById("create-wish-form").style.display = "block";
}

function closeCreateWishForm() {
    document.getElementById("create-wish-form").style.display = "none";
}

function openEditWishForm() {
    document.getElementById("edit-wish-form").style.display = "block";
}

function closeEditWishForm() {
    document.getElementById("edit-wish-form").style.display = "none";
}

function openProfilePopUp() {
    document.getElementById("profile-popup-id").style.display = "block";
}

function closeProfilePopUp() {
    document.getElementById("profile-popup-id").style.display = "none";
}



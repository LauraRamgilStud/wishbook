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



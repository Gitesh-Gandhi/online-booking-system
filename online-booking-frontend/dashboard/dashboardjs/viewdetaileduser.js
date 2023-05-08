const readIdQueryParam = () => {
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    return params.id
}

function apiGetEventDetails() {
    const id = readIdQueryParam()

    axios.get(`http://localhost:8080/user/${id}`)
        .then(function (response) {
            const data = response.data.bd;
            console.log(data)
            document.getElementById('userName').textContent = data.userName;
            document.getElementById('email').textContent = data.email;
            document.getElementById('currentLocation').textContent = data.currentLocation;
           
        })
        .catch(function (error) {
            console.log(error);
        });
}

function setupForm() {
    const formEvent = document.getElementById('userdetails')

    formEvent.onsubmit = ev => {
        ev.preventDefault()
         showSuccessModal()
    }
}

setupForm()

apiGetEventDetails()

function logOut() {
    localStorage.setItem("userId", null)
    window.location.href = "../../loginpage/login.html"
}







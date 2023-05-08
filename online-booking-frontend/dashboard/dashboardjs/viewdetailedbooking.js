const readIdQueryParam = () => {
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    return params.id
}

function apiGetEventDetails() {
    const id = readIdQueryParam()

    axios.get(`http://localhost:8080/admin/booking/${id}`)
        .then(function (response) {
            const data = response.data.bd;
            console.log(data)
            document.getElementById('id').textContent = data.id;
            document.getElementById('location').textContent = data.location;
            document.getElementById('startDate').textContent = data.startDate;
            document.getElementById('endDate').textContent = data.endDate;
            document.getElementById('startingTime').textContent = data.startingTime;
            document.getElementById('endingTime').textContent = data.endingTime;
            document.getElementById('price').textContent = data.price;
            document.getElementById('airCondtioning').textContent = data.airConditioning;
            document.getElementById('noOfStops').textContent = data.noOfStops;
            document.getElementById('serviceAvailable').textContent = data.serviceAvailable;
        })
        .catch(function (error) {
            console.log(error);
        });
}

function setupForm() {
    const formEvent = document.getElementById('bookingdetails')
    console.log(formEvent)
    formEvent.onsubmit = ev => {
        ev.preventDefault()
        //  showSuccessModal()
    }
}

setupForm()

apiGetEventDetails()

function logOut() {
    localStorage.setItem("userId", null)
    window.location.href = "../../loginpage/login.html"
}

function bookByUserId() {
    const userId = localStorage.getItem("userId");

    const bookingId = readIdQueryParam()
    console.log(userId,bookingId)
    
    const headers = {
        'content-type': 'application/json'
    }
    axios.post(`http://localhost:8080/user/${userId}/booking/${bookingId}`, { headers })

    .then(()=> {
        //  form.reset()
        showSuccessModal()

    }).catch(err =>{
        showSuccessModal1()
        console.log(err)
    })
}



function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}
function showSuccessModal1() {
    const myModalEl = document.getElementById('successModal1');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}
function goBack() {
    window.history.back();
}

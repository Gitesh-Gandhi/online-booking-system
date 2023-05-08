const readIdQueryParam = () => {
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    return params.id
}

console.log(readIdQueryParam())

function apiGetBookingDetails() {
    const id = readIdQueryParam()

    axios.get(`http://localhost:8080/admin/user/${id}`)
        .then(httpReponse => httpReponse.data)
        .then(data => populateForm(document.getElementById('userUpdate'), data.bd))
        .catch(err => console.log(err))

        
}

function apiUpdateExistingForm(user, form) {
    axios.put('http://localhost:8080/admin/user/update', user)
        .then(httpResponse => httpResponse.data)
        .then(data => console.log(data.msg))
        .then(res => {
            console.log(res)
            showSuccessModal()
           
        })
        .catch(err => console.log(err))
    }

function populateForm(form, data) {
    console.log(data)
    const { elements } = form; 

    const entries = Object.entries(data) 

    for (const entry of entries) {
      

        const [key, value] = entry
        const inputField = elements.namedItem(key)
       
        if (inputField) inputField.value = value
    }


}

function setupForm() {
    const formUpdateUser = document.getElementById('userUpdate')

    formUpdateUser.onsubmit = ev => { 
        const formData = new FormData(ev.target)

        ev.preventDefault() 
        

        const rawData = Object.fromEntries(formData.entries()) 
        

        const id = readIdQueryParam()

       

         
        const user = { ...rawData, id }
        console.log(user)

        apiUpdateExistingForm(user, formUpdateUser) 
        
    }
}

setupForm()

apiGetBookingDetails()

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}
function goBack() {
    window.history.back();
}
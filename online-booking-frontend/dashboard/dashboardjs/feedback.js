const validateForm = ({ comment, rating}) => {

   

    // if (comment.length <= 0) return { msg: 'invalid comment', sts: false}
    // if (rating.length <= 0) return { msg: 'invalid rating', sts: false }
 
    return { sts : 'success', msg :'all fields are valid' }
}

function setupForm() {

    const err = document.getElementById('errMsg')
    
    const feedback = document.getElementById('feedback')
    console.log(feedback)

    feedback.onsubmit = ev => { 

        ev.preventDefault() 

        const formData = new FormData(ev.target) 

        const user = Object.fromEntries(formData.entries()) 
        console.log(user)

        const { sts, msg } = validateForm(user)

        if (sts) apiFeedback(user, feedback)
        else {
            err.style.display = 'block'
            err.innerHTML = `<strong>${msg}</strong>`
        }
    }
}

setupForm()

function apiFeedback(user, form) {
    const headers = {
        'content-type': 'application/json'
    }
    const userId = localStorage.getItem("userId");
    console.log(userId)
    const url = `http://localhost:8080/user/${userId}/feedback`
    axios.post(url , user, { headers })
        .then(()=> {
            form.reset()
            showSuccessModal()

        }).catch(err => console.log(err))
}

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}
function goBack() {
    window.history.back();
}
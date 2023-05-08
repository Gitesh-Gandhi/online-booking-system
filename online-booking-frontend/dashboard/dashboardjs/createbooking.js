const validateForm = ({ location,startDate,endDate,startingTime,endingTime,price,airConditioning,noOfStops,serviceAvailable}) => {

     const airCond = ['yes', 'no']
     const serviceAvail=['yes','no']

    if (location.length <= 0) return { msg: 'invalid location', sts: false}
    if (!validateDate(startDate)) return { msg: 'invalid date', sts: false }
    if (!validateDate(endDate)) return { msg: 'invalid date', sts: false}
    if (startingTime.length <= 0) return { msg: 'invalid time', sts: false }
    if (endingTime.length <= 0) return { msg: 'invalid time', sts: false }
    if (!validateNumber(price)) return { msg: 'price cannot be negative', sts: false }
    if((airConditioning.length <= 0) || !airCond.includes(airConditioning)) return { msg: 'invalid input', sts: false }
    if (!validateNumber(noOfStops)) return { msg: 'Number of stops cannot be negative', sts: false }
    if((serviceAvailable.length <= 0) || !serviceAvail.includes(serviceAvailable)) return { msg: 'invalid input', sts: false }
   
    function validateDate(dateStr) {
        if (!dateStr) {
            return false;
        }
        const currentDate = new Date();
        const inputDate = new Date(dateStr);
        return inputDate >= currentDate;
    }
    function validateNumber(inputValue) {
        if (isNaN(inputValue) || inputValue < 0) {
          return false;
        }
        return true;
      }


    return { sts : 'success', msg :'all fields are valid' }
}

function setupForm() {

    const err = document.getElementById('errMsg')
    err.style.display = 'none'

    const createBooking = document.getElementById('createBooking')

    createBooking.onsubmit = ev => { 
        ev.preventDefault() 

        const formData = new FormData(ev.target) 

        const booking = Object.fromEntries(formData.entries()) 
        console.log(booking)

        const { sts, msg } = validateForm(booking)

        if (sts) apiSignup(booking, createBooking)
        else {
            err.style.display = 'block'
            err.innerHTML = `<strong>${msg}</strong>`
        }
    }
}

setupForm()

function apiSignup(booking, form) {
    const headers = {
        'content-type': 'application/json'
    }

    axios.post('http://localhost:8080/admin/create/bookingslot', booking, { headers })
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
function reloadPage() {
    location.reload();
  }
function setupTable() {
    const table = document.getElementById('table')
    // const btnSearch = document.getElementById('btnSearch')
    
    // btnSearch.onclick = () =>   {

    //     apiFetchBooking(table, document.getElementById('txtLocation').value )
    // }



    apiFetchAllbookings(table)
}

setupTable()


function propulateActualData(table, bookings) {

    for(const booking of bookings) {

        const {id ,location, startDate, endDate, startingTime, endingTime, price } = booking
        const viewPageUrl = `./userviewbookingdetails.html?id=${id}`

        const row = table.insertRow()
        row.insertCell(0).innerHTML = id
        row.insertCell(1).innerHTML = location
        row.insertCell(2).innerHTML = startDate
        row.insertCell(3).innerHTML = endDate
        row.insertCell(4).innerHTML = startingTime
        row.insertCell(5).innerHTML = endingTime
        row.insertCell(6).innerHTML = price
        row.insertCell(7).innerHTML = `
            <a class='ms-2' href='${viewPageUrl}'>view details</a>  
            
        `
    }
}


function apiFetchAllbookings(table) {
    axios.get('http://localhost:8080/admin/bookingslot')
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data

            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}

function apiFetchBooking(table, loc) {
    const url = `http://localhost:8080/admin/filterLocation`
    axios.get(url,{
        params: {
            location: loc
        }
    })
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data
           
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}

function logout() {
    localStorage.setItem("userId", null)
    window.location.href = "../../login/loginhtml/login-page.html"
}









function setupTable() {
    const table = document.getElementById('userBookingTable')

    // const btnSearch = document.getElementById('btnSearch')
    
    // btnSearch.onclick = () =>   {

    //     const api=apiFetchBooking(table, document.getElementById('txtUsername').value )
    //     console.log(api)
    // }
    
    apiFetchAllbookings(table)
}

 setupTable()


function propulateActualData(table, userBookings) {
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
    for(const userBooking of userBookings) {
        console.log(userBooking)
        const {userId, userName,location, startDate, endDate, startingTime, endingTime, price } = userBooking 
       

        const row = table.insertRow()
        row.insertCell(0).innerHTML = userId
        row.insertCell(1).innerHTML = userName
        row.insertCell(2).innerHTML = location
        row.insertCell(3).innerHTML = startDate
        row.insertCell(4).innerHTML = endDate
        row.insertCell(5).innerHTML = startingTime
        row.insertCell(6).innerHTML = endingTime
        row.insertCell(7).innerHTML = price
        row.insertCell(8).innerHTML = `
        <a class='ms-2' onclick='showSuccessModal()'>User reminder</a>
        
    `       
    }

    
}



function apiFetchAllbookings(table) {
    axios.get('http://localhost:8080/admin/alluserbookings')
        .then(res => {
           
            const { data } = res
             
            const { sts, msg, bd } = data

            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}

function apiFetchBooking(table, username) {
    console.log(table)
    console.log(username)
    const url = `http://localhost:8080/admin/filterbyname`
    axios.get(url,{
        params: {
            username: username
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


console.log("View page")
function goBack() {
    window.history.back();
}

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}

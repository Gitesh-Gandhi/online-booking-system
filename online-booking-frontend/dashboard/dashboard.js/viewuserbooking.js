function setupTable() {
    const table = document.getElementById('userBookingTable')

    const btnSearch = document.getElementById('btnSearch')
    
    btnSearch.onclick = () =>   {

        apiFetchBooking(table, document.getElementById('txtUsername').value )
    }
    console.log("JS")
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
        
            
            
            
        
    }
}

// function showConfirmDeleteModal(id) {
//     console.log('clicked ' + id)
//     const myModalEl = document.getElementById('deleteModal');
//     const modal = new bootstrap.Modal(myModalEl)
//     modal.show()

//     const btDl = document.getElementById('btDl')
//     btDl.onclick = () => {
//         apiCallDeleteBooking(id, modal)
//     }
// }

function apiFetchAllbookings(table) {
    axios.get('http://localhost:8080/admin/alluserbookings')
        .then(res => {
            console.log(res)
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data

            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}

function apiFetchBooking(table, username) {
    const url = `http://localhost:8080/admin/filterbyname`
    axios.get(url,{
        params: {
            userName: username
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


// function apiCallDeleteBooking(id, modal) {
//     const url = `http://localhost:8080/admin/bookingslot/${id}`

//     axios.delete(url)
//         .then(res => res.data) // you converted complete response in to our business reponse
//         // .then( data => console.log(data.msg) ) // this line can be written in destructured form as below
//         .then( ({ sts, msg, bd }) =>  modal.hide() )
        
//         .catch(console.log)
// }

console.log("View page")




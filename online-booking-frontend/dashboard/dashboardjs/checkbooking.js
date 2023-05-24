function setupTable() {
    const table = document.getElementById('table')
    const btnSearch = document.getElementById('btnSearch')
    
    btnSearch.onclick = () =>   {
        const location=document.getElementById('txtLocation').value
        const startdate=document.getElementById('txtStartdate').value
        const price=document.getElementById('txtPrice').value
        console.log(location)
        console.log(startdate)
        console.log(price)
        
        apiFetchBookingByLocation(table, location )
        apiFetchBookingByStartDate(table, startdate )
        apiFetchBookingByPrice(table, price )
        apiFetchBookingByFilters(table, location , startdate, price )

        
    }

    apiFetchAllbookings(table)
}

setupTable()


function propulateActualData(table, bookings) {
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
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

function apiFetchBookingByLocation(table, location) {
    console.log(location)
    axios.get(`http://localhost:8080/admin/filterbyLocation/${location}`)
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data
           
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}

function apiFetchBookingByStartDate(table, startDate) {
    console.log(startDate)
    axios.get(`http://localhost:8080/admin/filterStartdate/${startDate}`)
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data
           
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}

function apiFetchBookingByPrice(table, price) {
    console.log(location)
    axios.get(`http://localhost:8080/admin/filterPrice/${price}`)
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data
           
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}

function apiFetchBookingByFilters(table, location, startDate, price) {
    console.log(location)
    axios.get(`http://localhost:8080/admin/filterbyLSP/${location}/${startDate}/${price}`)
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data
           
            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}
function goBack() {
    window.history.back();
}
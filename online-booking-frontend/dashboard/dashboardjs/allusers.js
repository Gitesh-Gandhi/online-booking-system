function setupTable() {
    const table = document.getElementById('userTable')

    const btnSearch = document.getElementById('btnSearch')
    
    btnSearch.onclick = () =>   {

        apiFetchBooking(table, document.getElementById('txtusername').value )
    }

    apiFetchAllbookings(table)
}

setupTable()


function propulateActualData(table, users) {
    while (table.rows.length > 1) {
        table.deleteRow(1)
    }
    for(const user of users) {

        const {id ,userName, email, currentLocation} = user
        const updatePageUrl = `./updateuser.html?id=${id}`

        const row = table.insertRow()
        row.insertCell(0).innerHTML = id
        row.insertCell(1).innerHTML = userName
        row.insertCell(2).innerHTML = email
        row.insertCell(3).innerHTML = currentLocation
        row.insertCell(4).innerHTML = `
            <a class='ms-2' href='${updatePageUrl}'>Update</a> 
            <a class='ms-2' onclick='showSuccessModal()'>User reminder</a> 
            
        `
    }
}

function showConfirmDeleteModal(id) {
    console.log('clicked ' + id)
    const myModalEl = document.getElementById('deleteModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()

    const btDl = document.getElementById('btDl')
    btDl.onclick = () => {
        apiCallDeleteBooking(id, modal)
    }
}

function apiFetchAllbookings(table) {
    axios.get('http://localhost:8080/admin/users')
        .then(res => {
            const { data } = res
            console.log(data)  
            const { sts, msg, bd } = data

            propulateActualData(table, bd)
        })
        .catch(err => console.log(err))
}

function apiFetchBooking(table, userName) {
    const url = `http://localhost:8080/admin/filterbyusername`
    axios.get(url,{
        params: {
            userName: userName
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
function goBack() {
    window.history.back();
}

function showSuccessModal() {
    const myModalEl = document.getElementById('successModal');
    const modal = new bootstrap.Modal(myModalEl)
    modal.show()
}

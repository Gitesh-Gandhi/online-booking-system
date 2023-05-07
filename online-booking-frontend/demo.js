function apiFetchAllbookings() {
    console.log("api")
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
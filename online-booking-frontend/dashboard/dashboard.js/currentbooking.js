function validateDate(dateStr) {
    if (!dateStr) {
        return false;
    }
    const currentDate = new Date();
    const inputDate = new Date(dateStr);
    return inputDate >= currentDate;
}
let isEditing = false;

const reportsEdit = document.querySelectorAll('.edit-report');

for (let i = 0; i < reportsEdit.length; i++) {
    reportsEdit[i].onclick = function(event) {
        let target = event.target;
        let parentElem = target.parentElement
        let submitElem = parentElem.querySelectorAll('.submit-report')[0]
        submitElem.style.display = 'block'
        target.style.display = 'none'
        let cardTitleInput = parentElem.querySelectorAll('.card-title-input')[0]
        let cardTitle = parentElem.querySelectorAll('.card-title')[0]
        cardTitle.style.display = 'none';
        cardTitleInput.style.display = 'block';
        cardTitleInput.value = cardTitle.innerText;
        let cardTextInput = parentElem.querySelectorAll('.card-text-input')[0]
        let cardText = parentElem.querySelectorAll('.card-text')[0]
        cardText.style.display = 'none';
        cardTextInput.style.display = 'block';
        cardTextInput.innerText = cardText.innerText
        isEditing = !isEditing;
    }
}


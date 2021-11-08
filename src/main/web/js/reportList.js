let isEditing = false;

const reportsEdit = document.querySelectorAll('.edit-report');

function sendPostRequest(event) {
    if (isEditing) {
        let target = event.target;
        let parentElem = target.parentElement
        let cardTitleInput = parentElem.querySelectorAll('.card-title-input')[0]
        let cardTitle = parentElem.querySelectorAll('.card-title')[0]
        let reportId = parentElem.querySelectorAll('.report-id')[0]
        cardTitle.style.display = 'block';
        cardTitleInput.style.display = 'none';
        cardTitle.innerText = cardTitleInput.value;
        let cardTextInput = parentElem.querySelectorAll('.card-text-input')[0]
        let cardText = parentElem.querySelectorAll('.card-text')[0]
        cardText.style.height = cardTextInput.offsetHeight - 50;

        cardText.style.display = 'block';
        cardTextInput.style.display = 'none';
        cardText.innerText = cardTextInput.value
        target.innerText = 'Edit'
        isEditing = !isEditing;

        const data = {
            reportId: reportId.value,
            reportTopic: cardTitle.innerText,
            reportText: cardText.innerText
        };

        fetch(document.URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        }).then(r => console.log(r));

        target.onclick = function (event) {
            edit(event);
        }
    }
}

function edit(event) {
    if (!isEditing) {
        let target = event.target;
        let parentElem = target.parentElement
        let cardTitleInput = parentElem.querySelectorAll('.card-title-input')[0]
        let cardTitle = parentElem.querySelectorAll('.card-title')[0]
        cardTitle.style.display = 'none';
        cardTitleInput.style.display = 'block';
        cardTitleInput.value = cardTitle.innerText;
        let cardTextInput = parentElem.querySelectorAll('.card-text-input')[0]
        let cardText = parentElem.querySelectorAll('.card-text')[0]
        cardTextInput.style.height = cardText.offsetHeight + 50;
        cardText.style.display = 'none';
        cardTextInput.style.display = 'block';
        cardTextInput.innerText = cardText.innerText
        target.innerText = 'Save'
        isEditing = !isEditing;

        target.onclick = function (event) {
            sendPostRequest(event);
        }
    }
}

for (let i = 0; i < reportsEdit.length; i++) {
    reportsEdit[i].onclick = function(event) {
        edit(event);
    }
}

  const host = window.location.host;
  let apiBoardID = 8;
  let url = 'http://localhost:8080/api/boards/' + apiBoardID + '/cards';

  // Function to update the Trello board with the fetched data
  async function updateTrelloBoard() {
    try {
      const response = await fetch(url); // Replace with your API endpoint
      const data = await response.json();

      clearBoard();

      // Get the sections on the Trello board
      const sectionToDo = document.getElementById('sectionToDo');
      const sectionInProgress = document.getElementById('sectionInProgress');
      const sectionDone = document.getElementById('sectionDone');

      // Loop through the fetched data and create cards for each item
      data.forEach(cardData => {
        const card = document.createElement('div');
        const deleteCardDropbox = document.getElementById("deleteCard");
        const updateCardDropbox = document.getElementById("updateCard");

        card.classList.add('card');
        card.innerHTML = `
            <h3 class="card-id">#${cardData.id}</h3>
            <h3 class="card-title">${cardData.title}</h3>
            <h6 class="card-description-heading">Description:</h6>
            <p class="card-description">${cardData.description}</p>
          `;
        deleteCardDropbox.innerHTML += `<option>${cardData.id}</option>`;
        updateCardDropbox.innerHTML += `<option>${cardData.id}</option>`;

        // Determine which section the card belongs to and append it to that section
        if (cardData.sectionName === 'To Do') {
          sectionToDo.appendChild(card);
        } else if (cardData.sectionName === 'In Progress') {
          sectionInProgress.appendChild(card);
        } else if (cardData.sectionName === 'Done') {
          sectionDone.appendChild(card);
        }
      });
    } catch (error) {
      console.error('Error fetching data:', error);
    }
    getBoardTitle();
  }
  // Call the updateTrelloBoard function when the DOM is loaded
  document.addEventListener('DOMContentLoaded', updateTrelloBoard);
  //-----------------------------------------------------------------
  const titleValue = document.getElementById("title");
  const descValue = document.getElementById("description");
  const sectionValue = document.getElementById("section");

  function createCardFunction() {

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      "title": titleValue.value,
      "description": descValue.value,
      "section": sectionValue.value
    });

    var requestOptions = {
      method: 'POST',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    };

    if (!titleValue.value || !descValue.value || !sectionValue.value) { }
    else {

      fetch(url, requestOptions)
        .then(response => response.text())
        .then(result => {
          console.log(result);
          location.reload();
        })
        .catch(error => console.log('error', error));
    }
  }
  //-----------------------------------------------------------------

  function deleteCardFunction() {
    var requestOptions = {
      method: 'DELETE'
    };

    var dropdown = document.getElementById("deleteCard");
    var selectedValue = dropdown.value;
    if (selectedValue === '') { } else {


      fetch(`${url}/${selectedValue}`, requestOptions)
        .then(response => response.text())
        .then(result => {
          console.log(result);
          location.reload();
        })
        .catch(error => console.log('error', error));
    }
  }

  //-----------------------------------------------------------------

  function updateCardFunction() {

    var dropdown = document.getElementById("updateCard");
    var selectedValue = dropdown.value;

    var titleValue = document.getElementById('updateTitle');
    var descriptionValue = document.getElementById('updateDescription');
    var sectionValue = document.getElementById('updateSection')


    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
      "title": titleValue.value,
      "description": descriptionValue.value,
      "section": sectionValue.value
    });

    var requestOptions = {
      method: 'PUT',
      headers: myHeaders,
      body: raw,
      redirect: 'follow'
    };

    if (!titleValue.value || !descriptionValue.value || !sectionValue.value) {
      alert("Please fill out all fields")
    }
    else {
      fetch(`${url}/${selectedValue}`, requestOptions)
        .then(response => response.json())
        .then(result => {
          console.log(result)
          location.reload();
        })
        .catch(error => console.log('error', error));
    }
  }
  // Function to clear the board by removing all cards from each section
  function clearBoard() {
    const sectionToDo = document.getElementById('sectionToDo');
    const sectionInProgress = document.getElementById('sectionInProgress');
    const sectionDone = document.getElementById('sectionDone');

    while (sectionToDo.firstChild) {
      sectionToDo.removeChild(sectionToDo.firstChild);
    }

    while (sectionInProgress.firstChild) {
      sectionInProgress.removeChild(sectionInProgress.firstChild);
    }

    while (sectionDone.firstChild) {
      sectionDone.removeChild(sectionDone.firstChild);
    }
  }

  // Function to change the board dynamically
  function changeBoard() {
    const boardIdInput = document.getElementById("headerBoardIdInput");
    const selectedBoardId = boardIdInput.value;

    // Check if the entered board ID is a valid number
    if (selectedBoardId === "" || isNaN(selectedBoardId)) {
      alert("Please enter a valid board ID.");
      return;
    }

    // Update the 'apiBoardID' variable with the new board ID
    apiBoardID = parseInt(selectedBoardId);

    // Update the 'url' variable with the new API endpoint
    url = 'http://localhost:8080/api/boards/' + apiBoardID + '/cards';

    // Call the 'updateTrelloBoard' function to fetch and display the data for the selected board
    updateTrelloBoard();
   // getBoardTitle();
  }

  //-----------------------------------------------------------------
  function getBoardTitle() {
    // Send a GET request to the API endpoint that returns the board title
    fetch(`http://localhost:8080/api/boards/${apiBoardID}`, {
      method: 'GET',
      redirect: 'follow'
    })
      .then(response => response.json())
      .then(result => {

        // Assuming the result contains the board title, update the header's h1 element with it
        document.getElementById('boardTitle').innerHTML = result.title;
      })
      .catch(error => console.log('error', error));
  }

  // Call the function to get and display the board title
  getBoardTitle();
  //-----------------------------------------------------------------

  function updateTitleOfTheBoard(){
    var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/json");

    var titleValue = document.getElementById('updateTitleInput');
  var raw = JSON.stringify({
    "title": titleValue.value
  });

  var requestOptions = {
    method: 'PUT',
    headers: myHeaders,
    body: raw,
    redirect: 'follow'
  };

  fetch(`http://localhost:8080/api/boards/${apiBoardID}`, requestOptions)
    .then(response => response.text())
    .then(result => {
      console.log(result)
      location.reload();
    })
    .catch(error => console.log('error', error));
    
  }



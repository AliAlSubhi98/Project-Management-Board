const host = window.location.host;
let url = `${window.origin}/api/boards/8/cards`;

  // Function to update the Trello board with the fetched data
  async function updateTrelloBoard() {
    try {
      const response = await fetch('http://localhost:8080/api/boards/8/cards'); // Replace with your API endpoint
      const data = await response.json();

      // Get the sections on the Trello board
      const sectionToDo = document.getElementById('sectionToDo');
      const sectionInProgress = document.getElementById('sectionInProgress');
      const sectionDone = document.getElementById('sectionDone');

      // Clear existing cards from the sections
      //   sectionToDo.innerHTML = '';
      //   sectionInProgress.innerHTML = '';
      //   sectionDone.innerHTML = '';

      // Loop through the fetched data and create cards for each item
      data.forEach(cardData => {
        const card = document.createElement('div');
        card.classList.add('card');
        card.innerHTML = `
          <h3 class="card-id">#${cardData.id}</h3>
          <h3 class="card-title">${cardData.title}</h3>
          <h6 class="card-description-heading">Description:</h6>
          <p class="card-description">${cardData.description}</p>
        `;

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
  }

  // Call the updateTrelloBoard function when the DOM is loaded
  document.addEventListener('DOMContentLoaded', updateTrelloBoard);
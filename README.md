
# Project Management Board

This project is a simple project management board application that allows users to manage boards and cards.

## Installation

1. Clone the repository:

```bash
   git clone https://github.com/AliAlSubhi98/Project-Management-Board
```
Navigate to the project directory:

```bash
cd project-management-board
```

Run the application using Docker Compose:

```bash
docker-compose up
```
Features
Create, update, and delete boards.
Add, update, and delete cards within boards.
RESTful API with endpoints for managing boards and cards.
Usage
Create a new board:

Endpoint: POST /api/boards
Example Request Body:
json
```bash
{
  "title": "New Board Title"
}
```

Get all boards:
```bash
Endpoint: GET /api/boards
```
Get a specific board:
```bash
Endpoint: GET /api/boards/{boardId}
```
Update a board:
```bash
Endpoint: PUT /api/boards/{boardId}
Example Request Body:
json

{
  "title": "Updated Board Title"
}
```
Delete a board:
```bash
Endpoint: DELETE /api/boards/{boardId}
```
Add a card to a board:
```bash
Endpoint: POST /api/boards/{boardId}/cards
Example Request Body:
json

{
  "title": "New Card Title",
  "description": "Card Description",
  "section": "To Do"
}
```
Get all cards from a board:
```bash
Endpoint: GET /api/boards/{boardId}/cards
```
Get a specific card from a board:
```bash
Endpoint: GET /api/boards/{boardId}/cards/{cardId}
```
Update a card on a board:
```bash
Endpoint: PUT /api/boards/{boardId}/cards/{cardId}
Example Request Body:
json
{
  "title": "Updated Card Title",
  "description": "Updated Description",
  "section": 1
}
```
Delete a card from a board:
```bash
Endpoint: DELETE /api/boards/{boardId}/cards/{cardId}
```

## User Interface (UI)

The project includes a user interface that allows you to manage boards and cards. Here's how to interact with the UI:

### Board Title and ID

- The board title and ID are displayed at the top of the page.
- To update the board title, type the new title in the "Enter new title" input field and click the "Update Title" button.
- To retrieve a specific board by ID, enter the board ID in the "Type the board ID" input field and click the "Get the Board" button.

### Trello Board Sections

The Trello board is divided into three sections: "To Do," "In Progress," and "Done."

- You can drag and drop cards between sections to indicate their progress.

### Control Panel

The control panel on the side provides tools for managing cards:

- **Create Card:**
  - Fill in the "Title," "Description," and "Section" fields in the "Create Card" form, then click the "Create Card" button.

- **Delete Card:**
  - Select a card from the "Select Card to Delete" dropdown in the "Delete Card" form, then click the "Delete Card" button.

- **Update Card:**
  - Choose a card to update from the "Select Card to Update" dropdown in the "Update Card" form.
  - Modify the "Title," "Description," and "Section" fields as needed, then click the "Update Card" button.

### How to Use

1. Clone the repository and navigate to the project directory.
2. Open the `index.html` file in a web browser to access the user interface.
3. Follow the instructions above to manage boards and cards using the UI.

## JavaScript Functionality

The provided JavaScript code plays a crucial role in interacting with the user interface (UI) and managing data through API calls. Below, we'll break down the main functions and their functionalities:

### Updating the Trello Board

The `updateTrelloBoard` function fetches data from the API and updates the UI with the retrieved card information. It populates the different sections of the Trello board based on the card's section.

### Creating a Card

The `createCardFunction` function is responsible for creating a new card. It collects input values for the title, description, and section from the UI form, sends a POST request to the API, and then reloads the page to reflect the updated board.

### Deleting a Card

The `deleteCardFunction` function handles card deletion. It retrieves the selected card ID from the UI dropdown, sends a DELETE request to the API, and then reloads the page to reflect the deletion.

### Updating a Card

The `updateCardFunction` function allows users to update a card's information. It fetches the selected card's ID and updated values from the UI, sends a PUT request to the API, and then reloads the page to display the updated card details.

### Changing the Board

The `changeBoard` function dynamically changes the displayed board based on the selected board ID. It updates the API endpoint URL and then calls the `updateTrelloBoard` function to fetch and display the data for the selected board.

### Displaying Board Title

The `getBoardTitle` function retrieves and displays the title of the current board by sending a GET request to the API and updating the UI header element.

### Drag and Drop Functionality

The `allowDrop`, `drag`, and `drop` functions enable drag-and-drop functionality for cards within the Trello board. The `drop` function updates the card's section when dropped into a different section.

### Updating Board Title

The `updateTitleOfTheBoard` function updates the board's title based on the input from the UI form. It sends a PUT request to the API to update the board title.

Please note that this is a high-level overview of the JavaScript functionality in your project. For more details, refer to the actual code comments and the JavaScript files in your project.


Contact
If you have any questions or feedback, feel free to reach out to us at Hopeofoman1@email.com.

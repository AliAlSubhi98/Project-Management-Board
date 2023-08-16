

```markdown
# Project Management Board
```

Welcome to the Project Management Board! This application allows users to efficiently manage boards and cards, streamlining project organization.

## Table of Contents
- [Installation](#installation)
- [Features](#features)
- [Usage](#usage)
- [User Interface (UI)](#user-interface-ui)
- [JavaScript Functionality](#javascript-functionality)
- [Prerequisites](#prerequisites)
- [Screenshots](#screenshots)
- [API Documentation](#api-documentation)
- [License](#license)
- [Contributing](#contributing)
- [Contact](#contact)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/AliAlSubhi98/Project-Management-Board
   ```

2. Navigate to the project directory:
   ```bash
   cd project-management-board
   ```

3. Run the application using Docker Compose:
   ```bash
   docker-compose up
   ```

## Features

- Create, update, and delete boards.
- Add, update, and delete cards within boards.
- RESTful API with endpoints for managing boards and cards.

## Usage

The usage instructions guide you through interacting with the application's features.

### User Interface (UI)

The UI provides an intuitive way to manage boards and cards:

- **Board Title and ID:**
  - Easily update the board title using the "Enter new title" input field and the "Update Title" button.
  - Retrieve specific boards by entering their ID in the "Type the board ID" input field and clicking the "Get the Board" button.

- **Trello Board Sections:**
  - Organize cards in three sections: "To Do," "In Progress," and "Done."
  - Utilize drag-and-drop functionality to move cards between sections.

- **Control Panel:**
  - Manage cards efficiently using the control panel:
    - Create cards by filling out the form and clicking "Create Card."
    - Delete cards by selecting a card from the dropdown and clicking "Delete Card."
    - Update cards by choosing a card from the dropdown, modifying details, and clicking "Update Card."

### JavaScript Functionality

The JavaScript code interacts seamlessly with the UI and API:

- **Updating the Trello Board:** Fetches API data and updates the UI with retrieved card information, populating different sections based on card sections.
- **Creating a Card:** Handles card creation by sending a POST request to the API with user input, then reloading the page.
- **Deleting a Card:** Manages card deletion by sending DELETE requests to the API based on user selection, then updating the UI.
- **Updating a Card:** Allows card details to be updated via PUT requests to the API, then reloading the page.
- **Changing the Board:** Dynamically switches displayed boards using selected IDs and API endpoint updates.
- **Displaying Board Title:** Retrieves and displays the current board's title from the API.
- **Drag and Drop Functionality:** Enables intuitive card movement between sections through drag-and-drop actions.
- **Updating Board Title:** Allows users to change the board title through the UI form.

## Prerequisites

- Docker: Ensure you have Docker installed on your system.

## Screenshots

![Screenshot](https://i.ibb.co/pRTWhXv/Screenshot-31.png)

## API Documentation

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

## License

This project is free to use (LICENSE).

## Contributing

We welcome contributions! Feel free to report issues or submit pull requests.

## Contact

If you have questions or feedback, feel free to contact us at [Hopeofoman1@email.com](mailto:Hopeofoman1@email.com).
```

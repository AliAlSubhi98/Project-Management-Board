
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
Contributing
Contributions are welcome! To contribute:

Fork the repository and create a new branch.
Make your changes and submit a pull request.
Follow the contribution guidelines.

License
This project is Free to use.

Contact
If you have any questions or feedback, feel free to reach out to us at Hopeofoman1@email.com.

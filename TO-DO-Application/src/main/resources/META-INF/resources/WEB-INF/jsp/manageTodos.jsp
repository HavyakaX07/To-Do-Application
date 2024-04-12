<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Success</title>
    <style>
        .welcome-message {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .add-task-button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="welcome-message">Welcome, ${username}!</div>
    <button class="add-task-button" onclick="addTask()">Add TO-DO Task</button>
    <br><br>
    <table id="taskTable">
        <thead>
            <tr>
                <th>ID</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
            <!-- Tasks will be dynamically added here -->
        </tbody>
    </table>

    <script>
        function addTask() {
            // Dummy data, replace with actual data from user input
            var id = Math.floor(Math.random() * 1000) + 1;
            var description = "Task Description";

            // Create a new row
            var table = document.getElementById("taskTable").getElementsByTagName('tbody')[0];
            var newRow = table.insertRow();

            // Insert cells
            var cell1 = newRow.insertCell(0);
            var cell2 = newRow.insertCell(1);

            // Add data to cells
            cell1.innerHTML = id;
            cell2.innerHTML = description;
        }
    </script>
</body>
</html>

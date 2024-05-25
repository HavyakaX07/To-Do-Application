<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .container {
            text-align: center;
            margin-top: 50px;
        }
        .welcome-message {
            font-size: 24px;
            margin-bottom: 20px;
        }
        .add-task-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-bottom: 20px;
        }
        .add-task-button-delete {
            background-color: #a52c19;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            padding-top: 60px;
        }

        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 50%; /* Could be more or less, depending on screen size */
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        /* Close Button */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
        }

        /* Form Styling */
        input[type="text"],
        input[type="date"],
        button {
            width: calc(100% - 22px); /* Adjusted width */
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }

        input[type="checkbox"] {
            display: inline-block;
            margin-right: 5px; /* Added margin */
        }

        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        table {
            margin: 0 auto; /* Center align table */
            border-collapse: collapse;
            width: 80%; /* Adjusted width */
            margin-bottom: 20px; /* Added margin */
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="welcome-message">Welcome, ${loggedInUser}!</div>
        <c:choose>
            <c:when test="${empty todos}">
                <p>No todo tasks for the user. You can add some Tasks.</p>

            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Task</th>
                            <th>Description</th>
                            <th>Target Date</th>
                            <th>Is Done?</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>     
                        <c:forEach items="${todos}" var="todo">
                            <tr>
                                <td>${todo.taskName}</td>
                                <td>${todo.taskDescription}</td>
                                <td>${todo.targetDate}</td>
                                <td>${todo.isCompleted()}</td>
                                <c:choose>
                                    <c:when test="${not empty todo}">
                                        <td> <button onclick="openDeleteModel('${todo.getTaksId()}')" class="add-task-button-delete">Delete</a>   </td>
                                        <td> <button class="add-task-button" onclick="openUpdateModal('${todo.getTaksId()}', '${todo.taskName}', '${todo.taskDescription}', '${todo.targetDate}', '${todo.isCompleted()}')">Update</button></td>
                                    </c:when>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
    <button class="add-task-button" onclick="openModal()">Add TO-DO Task</button>
    <br><br>

    <!-- The Modal -->
    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2>Add New Task</h2>
            <label for="taskName">Task Name:</label>
            <input type="text" id="taskName" name="taskName">
            <label for="taskDescription">Task Description:</label>
            <input type="text" id="taskDescription" name="taskDescription">
            <label for="targetDate">Target Date:</label>
            <input type="date" id="targetDate" name="targetDate">
            <label for="completed">Completed:</label>
            <input type="checkbox" id="completed" name="completed">
            <button onclick="addTask()">Save TO-DO</button>
        </div>
    </div>
        <div id="myUpdateModal" class="modal">

            <!-- Modal content -->
            <div class="modal-content">
                <span class="close" onclick="closeModalUpdate()">&times;</span>
                <h2>Update Task</h2>
                <label for="taskName">Task Name:</label>
                <input type="text" id="taskName_UPD" name="taskName">
                <label for="taskDescription">Task Description:</label>
                <input type="text" id="taskDescription_UPD" name="taskDescription">
                <label for="targetDate">Target Date:</label>
                <input type="date" id="targetDate_UPD" name="targetDate">
                <label for="completed">Completed:</label>
                <input type="checkbox" id="completed_UPD" name="completed">
                <button onclick="updateTask()">Save TO-DO</button>
            </div>
        </div>

        <div id="myDeleteModal" class="modal">

            <!-- Modal content -->
            <div class="modal-content">
                <span class="close" onclick="closeDeleteModal()">&times;</span>
                <h2>Delete Task</h2>
                <h4>Do you want to delete selected TO-DO. Are you sure?</h4>
                <button onclick="deleteTask()">DELETE TO-DO</button>
            </div>
        </div>

    

    <script>
        // Get the modal
        var taskid = -1;
        var modal = document.getElementById("myModal");

        // When the user clicks on the button, open the modal
        function openModal() {
            modal.style.display = "block";
        }

        function openUpdateModal(taskId, taskName, taskDescription, targetDate, isCompleted){
            var modal = document.getElementById("myUpdateModal");
            var taskNameInput = document.getElementById("taskName_UPD");
            var taskDescriptionInput = document.getElementById("taskDescription_UPD");
            var targetDateInput = document.getElementById("targetDate_UPD");
            var completedCheckbox = document.getElementById("completed_UPD");

            console.log(taskName,taskDescription,targetDate,isCompleted,taskId);
            taskNameInput.value = taskName;
            taskDescriptionInput.value = taskDescription;
            targetDateInput.value = targetDate;
            completedCheckbox.checked = isCompleted;
            taskid = taskId;
            modal.style.display = "block";
        }

        function openDeleteModel(taskId){
            var modal = document.getElementById("myDeleteModal");
            taskid = taskId;
            modal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        function closeModal() {
            modal.style.display = "none";
        }

        function closeModalUpdate(){
            var modal = document.getElementById("myUpdateModal");
            modal.style.display = "none";
        }

        function closeDeleteModal(){
            var modal = document.getElementById("myDeleteModal");
            modal.style.display = "none";
        }

        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

    function sendData(url) {
        var taskName = document.getElementById("taskName").value;
        var taskDescription = document.getElementById("taskDescription").value;
        var targetDate = document.getElementById("targetDate").value;
        var completed = document.getElementById("completed").checked;
        if(url==='update-todos'){
            taskName = document.getElementById("taskName_UPD").value;
            taskDescription = document.getElementById("taskDescription_UPD").value;
            targetDate = document.getElementById("targetDate_UPD").value;
            completed = document.getElementById("completed_UPD").checked;
        }
   

    var taskData = {
        "taskName": taskName,
        "taskDescription": taskDescription,
        "targetDate": targetDate,
        "isCompleted": completed,
        "taskIdToUpdate":taskid
    };

    console.log("Calling backend api");
    // Send task data
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            // Add any additional headers needed
        },
        body: JSON.stringify(taskData)
    })
    .then(response => {
        if (response.ok) {
            console.log('Task operation successful');
            closeModal();
            if(url==='update-todos'){
                closeModalUpdate();
            }
        } else {
            console.error('Error:', response.status);
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function addTask() {
    sendData('add-todos');
}

function updateTask() {
    sendData('update-todos');
}

function deleteTask(){
    var taskData = {
        "todoId":taskid
    };
    fetch("delete-todos", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            // Add any additional headers needed
        },
        body: JSON.stringify(taskData)
    })
    .then(response => {
        if (response.ok) {
            console.log('Task operation successful');
            closeDeleteModal();
        } else {
            console.error('Error:', response.status);
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}


    </script>
</body>
</html>

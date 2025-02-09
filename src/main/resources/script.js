function sayHello() {
    const name = document.getElementById('name-input').value;
    fetch(`http://localhost:8080/hello?name=${encodeURIComponent(name)}`)
        .then(response => response.text())
        .then(data => {
            document.getElementById('hello-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('hello-result').textContent = 'Error al obtener el saludo';
        });
}

function getPi() {
    fetch('http://localhost:8080/pi')
        .then(response => response.text())
        .then(data => {
            document.getElementById('pi-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('pi-result').textContent = 'Error al obtener el valor de Pi';
        });
}

function getEuler() {
    fetch('http://localhost:8080/euler')
        .then(response => response.text())
        .then(data => {
            document.getElementById('euler-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('euler-result').textContent = 'Error al obtener el valor de Euler';
        });
}

function sumNumbers() {
    const num1 = document.getElementById("num1-input").value;
    const num2 = document.getElementById("num2-input").value;

    if (num1 === "" || num2 === "") {
        document.getElementById("sum-result").innerText = "Por favor, ingresa ambos números.";
        return;
    }

    fetch(`http://localhost:8080/sum?a=${num1}&b=${num2}`)
        .then(response => response.text())
        .then(data => {
            document.getElementById("sum-result").innerText = data;
        })
        .catch(error => {
            console.error("Error al hacer la solicitud:", error);
            document.getElementById("sum-result").innerText = "Error al conectar con el servidor.";
        });
}

function getTomorrow() {
    fetch('http://localhost:8080/manana')
        .then(response => response.text())
        .then(data => {
            document.getElementById('tomorrow-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('tomorrow-result').textContent = 'Error al obtener el día de mañana';
        });
}

function getDateTime() {
    fetch('http://localhost:8080/fecha-hora')
        .then(response => response.text())
        .then(data => {
            document.getElementById('datetime-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('datetime-result').textContent = 'Error al obtener la fecha y hora';
        });
}

function getServerIP() {
    fetch('http://localhost:8080/ip')
        .then(response => response.text())
        .then(data => {
            document.getElementById('server-ip-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('server-ip-result').textContent = 'Error al obtener la IP del servidor';
        });
}

function getSystemInfo() {
    fetch('http://localhost:8080/sistema')
        .then(response => response.text())
        .then(data => {
            document.getElementById('system-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('system-result').textContent = 'Error al obtener información del sistema';
        });
}

function getMemoryInfo() {
    fetch('http://localhost:8080/memoria')
        .then(response => response.text())
        .then(data => {
            document.getElementById('memory-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('memory-result').textContent = 'Error al obtener la memoria usada';
        });
}


function getSystemInfo() {
    fetch('http://localhost:8080/sistema')
        .then(response => response.text())
        .then(data => {
            document.getElementById('system-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('system-result').textContent = 'Error al obtener información del sistema';
        });
}

function getMemoryInfo() {
    fetch('http://localhost:8080/memoria')
        .then(response => response.text())
        .then(data => {
            document.getElementById('memory-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('memory-result').textContent = 'Error al obtener la memoria usada';
        });
}

function getAuthor() {
    fetch('http://localhost:8080/autor')
        .then(response => response.text())
        .then(data => {
            document.getElementById('author-result').textContent = data;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('author-result').textContent = 'Error al obtener el autor';
        });
}

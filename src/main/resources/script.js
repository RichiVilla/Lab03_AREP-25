function sayHello() {
    const name = document.getElementById('name-input').value;
    fetch(`/App/hello?name=${encodeURIComponent(name)}`)
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
    fetch('/App/pi')
        .then(response => response.text())
        .then(data => {
            document.getElementById('pi-result').textContent = `El valor de Pi es: ${data}`;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('pi-result').textContent = 'Error al obtener el valor de Pi';
        });
}

function sumNumbers() {
    const num1 = document.getElementById('num1-input').value;
    const num2 = document.getElementById('num2-input').value;

    fetch(`/App/sum?num1=${encodeURIComponent(num1)}&num2=${encodeURIComponent(num2)}`)
        .then(response => response.text())
        .then(data => {
            document.getElementById('sum-result').textContent = `Resultado: ${data}`;
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('sum-result').textContent = 'Error al obtener la suma';
        });
}

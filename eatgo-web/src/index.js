(async () => {
    const url = "http://localhost:8080/restaurants";
    const response = await fetch(url);
    const data = await response.json();

    const element = document.getElementById("app");
    element.innerHTML = JSON.stringify(data);
})();
HTML 

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <div class="container">
        <h1>Our Menu</h1>

        <div class="menu-item">
            <div class="menu-header chicken">Chicken</div>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>

        <div class="menu-item">
            <div class="menu-header beef">Beef</div>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>

        <div class="menu-item">
            <div class="menu-header sushi">Sushi</div>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>
    </div>

</body>
</html>

CSS


body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f8f8f8;
    text-align: center;
}

.container {
    width: 80%;
    max-width: 600px;
    margin: 20px auto;
    background-color: white;
    padding: 20px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

h1 {
    margin-bottom: 20px;
}

.menu-item {
    background-color: #ddd;
    padding: 15px;
    margin-bottom: 15px;
    position: relative;
    text-align: left;
    border-radius: 5px;
}

.menu-header {
    position: absolute;
    top: 0;
    right: 0;
    padding: 5px 10px;
    color: white;
    font-weight: bold;
    border-top-right-radius: 5px;
}

.chicken {
    background-color: #d97c7c;
}

.beef {
    background-color: #c0392b;
}

.sushi {
    background-color: #f4c542;




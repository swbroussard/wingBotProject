<!-- * A way to read the JSON file
     * Activate the Algorithm
     * A quick authentication function for user login
     * A way to return the values and display them in 
       HTML and CSS -->

<!DOCTYPE html>
<head>
    <script>
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                myObj = JSON.parse(this.responseText);
                document.getElementById("demo").innerHTML = myObj.name;
            }
        };
        xmlhttp.open("GET", "json_demo.txt", true);
        xmlhttp.send();
    </script>
</head>
<html>
    <body>
        <p>Take a look at <a href="json_demo.json" target="_blank">json_demo.txt</a></p>
    </body>
</html>
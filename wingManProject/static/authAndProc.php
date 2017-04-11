<!-- * A way to read the JSON file
     * Activate the Algorithm
     * A quick authentication function for user login
     * A way to return the values and display them in 
       HTML and CSS -->

<!DOCTYPE html>
<html>
    <body>
        <?php
            $fileContent = file_get_contents("./some_folder/*.json");
            $json_content = json_decode($fileContent, true);
            
        ?>
    </body>
</html>
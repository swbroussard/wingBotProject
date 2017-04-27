<!-- * A way to read the JSON file
     * Activate the Algorithm
     * A quick authentication function for user login
     * A way to return the values and display them in 
       HTML and CSS -->

<!DOCTYPE html>
<html>
    <head>
        <title>Wing Man Project</title>
        <link rel="stylesheet" href="./stylingFile.css">
        
        <?php 
        function html_form_guard($formData) {
            $formData = trim($formData);
            $formData = stripcslashes($formData);
            $formData = htmlspecialchars($formData);
            
            return $formData;
        }
        
        function required_field($fieldInput, $fieldNumber) {
            if(empty($fieldInput) && $fieldNumber == 0) {
                echo "<p class='centered'>Facebook Username not entered</p><br>
                      <a href='/' class='centered'>Please click to enter Facebook Username</a>";
            }
            elseif (!empty($fieldInput) && $fieldNumber == 0) {}
            elseif (empty($fieldInput) && $fieldNumber == 1) {
                echo "<p class='centered'>Facebook Password not entered</p><br>
                      <a href='/' class='centered'>Please click to enter Facebook Password</a>";
            }
            elseif (!empty($fieldInput) && $fieldNumber == 1){}
        }
        
        function reading_json_file($jsonFile) {
            $results = "";
            $str = file_get_contents($jsonFile);
            $json = json_decode($str, true);
            
            //$results = $results . '<pre>' . print_r($json, true) . '</pre>';
            
            $results = $results . "<ul>";
            
            $results = $results . "<li>" . $json['suggestionList'][0]['name'] . "; Scores: " . $json['suggestionList'][0]['score'] . "</li><br>";
            $results = $results . "<li>" . $json['suggestionList'][1]['name'] . "; Scores: " . $json['suggestionList'][1]['score']. "</li><br>";
            $results = $results . "<li>" . $json['suggestionList'][2]['name'] . "; Scores: " . $json['suggestionList'][2]['score']. "</li><br>";
            
            $results = $results . "</ul>";
            
            return $results;
        }
        ?>
    </head>
    
    <body>
        <?php
            $user_name = "";
            $passward = "";
            
            if($_SERVER["REQUEST_METHOD"] == "POST") {
                //required_field($_POST["userName"], 0);
                //required_field($_POST["passwrd"], 1);
                $user_name = html_form_guard($_POST["userName"]);
                $passward = html_form_guard($_POST["passwrd"]);
            }
            //$fileContent = file_get_contents("./some_folder/*.json");
            //$json_content = json_decode($fileContent, true);
        ?>
        
        <h1 class="centered">Welcome <?php echo $user_name; ?> </h1>
        <!--<h1>Welcome User</h1>-->
        
        <p class="centered"> Please fill out form for top three compatabilities </p>
        
        <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST") {
                echo reading_json_file("./topSuggestions.json");
            }
        ?>
        
        <!--<ul class="centered"> 
            <li class="centered">ary Jones</li>
            <li class="centered">Jane Smith</li>
            <li class="centered">Sally May</li>
        </ul>-->
        
        <!--for test purposes only-->
        <a href="/">home page</a>
        
        <!--some where have a button to refresh the list of compatibilities-->
        <!--make a styling page for list and button-->
        
    </body>
</html>
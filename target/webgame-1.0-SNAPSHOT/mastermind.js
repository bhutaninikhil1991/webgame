var n = 4; // no. of colours that can be selected
var n_colors = 6; // no. of colours available for selection
var n_at = 8; // no. of misses allowed

var Itags = document.getElementsByTagName("i");
var Btags = document.getElementsByTagName("b");

var Secrets = document.getElementsByClassName("secret");
var Fon = document.getElementsByClassName("fonc");
var Htag = document.getElementsByTagName("h1");
var Sec = document.getElementsByClassName("secrets");
var Att = document.getElementsByClassName("attempt");
var Bod = document.getElementsByTagName("body");

window.onload = function () {
    if (n == 4) {
        Bod[0].classList.add('n-4-6');
    }
    if (n == 5) {
        Bod[0].classList.add('n-5-8');
        Itags = document.querySelectorAll("i,tt");
        Btags = document.querySelectorAll("b,em");
    }
};

//to generate random colour
function rand_till(n) {
    // select random colour between 0 to n
    var aa = Math.random();
    var aaa = Math.floor(aa * n);
    if (aaa == n) {
        aaa = 0;
    }
    return Math.floor(aa * n);
}

function FunColor(but) {
    var pressed_color = but.value;
    var colors = [];
    var colors_cod = [];
    var colors_attempt = [];
    var sum1, sum2;
    var x, x_now, attempt_now, at;
    var end_of_attempt = false;
    var color_def;
    color_def = window.getComputedStyle(document.getElementsByClassName("fonc")[0]).backgroundColor;


    for (i = 0; i < n_colors; i++) {
        colors[i] = window.getComputedStyle(document.getElementsByClassName("bcol")[i]).backgroundColor;
    }

    //generate random secret phrase for code length
    if (window.getComputedStyle(Secrets[0]).backgroundColor == "rgb(0, 0, 0)") {
        color_def = window.getComputedStyle(Itags[0]).backgroundColor;
        Fon[0].style.backgroundColor = color_def;
        for (i = 0; i < n; i++) {
            colors_cod[i] = colors[rand_till(n_colors)];
            Secrets[i].style.backgroundColor = colors_cod[i];
        }
    }

    // runs for 8 columns and 4 rows n_at = 8 and n = 4
    var j = n;
    at = 1;
    for (var i = n_at * n; i > 0; i--) {

        x = i - j; //pointer to current row
        j = j - 2; //decrement j by 2 for each cell in a row
        // it executes from 4 to -4 for each row
        if (j == (-1) * n) {
            j = n;
            end_of_attempt = true;
        }
        // it pulls the i tag for each cell for each row
        var citag = window.getComputedStyle(Itags[x]).backgroundColor;
        if (citag == color_def) {
            x_now = x; // pointer to the current cell of the current row
            attempt_now = at; // points to the current attempt count
            break;
        }
        //if j and n are equal then it marks the beginning of the new row and then increment the attempt count
        if (j == n) at++;
        end_of_attempt = false;
    }

    var v = 0;
    // no. of attempts left
    var AttNow = Att[n_at - attempt_now];

    switch (v == 1) {

        case false:
            //if the user want to play again or had guess correctly then reset the game variables
            if (but.value == "Play Again" || but.value == "Good Guess!!!") {
                window["location"].sup = 1;
                window["location"].reload(true);
            } else if (but.value == "Cancel") {
                x_now--; //decrement the current cell count
                Itags[x_now].style.backgroundColor = color_def; //reset the colour
                return;
            } else Itags[x_now].style.backgroundColor = colors[pressed_color - 1]; //determine the current colour that is pressed and store it in the current cell i tag
            break;

        case true:
            var style = document.createElement('style');
            style.type = 'text/css';
            var h = '.attempt i:hover{opacity:0.5; background-color:' + colors[pressed_color - 1] + '}';
            var hover = document.createTextNode(h);
            var head = document.getElementsByTagName('head')[0];
            style.appendChild(hover);
            head.appendChild(style);
            break;
    }


    if (end_of_attempt) {
        for (i = 0; i < n; i++) {
            colors_cod[i] = window.getComputedStyle(Secrets[i]).backgroundColor; //compute secret code
            colors_attempt[i] = window.getComputedStyle(Itags[x_now + i + 1 - n]).backgroundColor; //compute guess
        }

        sum1 = 0; //exacts
        sum2 = 0; //partials

        //check for exacts
        for (i = 0; i < n; i++) {
            if (colors_cod[i] == colors_attempt[i]) sum1++; //if secret code is equal to the attempted code then increment sum1
        }

        //check for partials
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (colors_cod[i] == colors_attempt[j]) {
                    sum2++;
                    colors_attempt[j] = 0; //reset the colour code
                    break;
                }
            }
        }

        sum2 = sum2 - sum1;

        var Bcan = document.getElementsByClassName("b_cancel");

        //if won the game
        if (sum1 == n) {
            Bcan[0].value = "Good Guess!!!";
            Bcan[0].style.backgroundColor = "#FFC107";
            Bcan[0].style.color = "white";
            Htag[0].innerHTML = "Congratulations!!! You won";
            Htag[0].style.fontSize = "18px";
            Sec[0].style.opacity = 1;
        }

        //if lost all the attempts
        if (attempt_now == n_at && sum1 != n) {
            Htag[0].innerHTML = "Sorry!! Game Over. Better Luck next time";
            Htag[0].style.color = "black";
            Htag[0].style.fontSize = "18px";
            Sec[0].style.opacity = 1;
            Bcan[0].style.backgroundColor = "#E91E63";
            Bcan[0].style.color = "black";
            Bcan[0].value = "Play Again";
            Bcan[0].style.fontWeight = "bold";
        }

        //display partials and exacts
        for (i = 0; i < n; i++) {
            var ii = x_now + i + 1 - n;
            if (sum1 == 0 && sum2 > 0) {
                sum2--;
                Btags[ii].style.backgroundColor = "white"; //partials
                Btags[ii].style.borderColor = "white"; //partials
            }
            if (sum1 > 0) {
                sum1--;
                Btags[ii].style.backgroundColor = "black"; //exacts
                Btags[ii].style.borderColor = "black"; //exacts
            }
        }
    }
}


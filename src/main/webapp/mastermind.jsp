<%--
  Created by IntelliJ IDEA.
  User: bhutani
  Date: 10/23/19
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="./mastermind.js"></script>
<link href='https://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Roboto+Slab" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" type="text/css" href="./mastermind.css">


<body>
<h1>Mastermind</h1>

<div class="secrets">
    <div class="secret"></div>
    <div class="secret"></div>
    <div class="secret"></div>
    <div class="secret"></div>
    <div class="secret"></div>
    <div class="secret fonc"></div>
</div>

<div class="game">
    <div class="field">
        <h5 class="rules">
            <a href="/rules"
               target="_blank">How to play</a>
            <br>
            Player Info:
            <br>
            ${name}
        </h5>
        <ul class="attempts">
            <li><h2>8</h2>
                <div class="result" d-f="0"><b></b><b></b><b></b><b></b><em></em></div>
                <div class="attempt"><i></i><i></i><i></i><i></i><tt></tt></div>
            </li>
            <li><h2>7</h2>
                <div class="result" d-f="0"><b></b><b></b><b></b><b></b><em></em></div>
                <div class="attempt"><i></i><i></i><i></i><i></i><tt></tt></div>
            </li>
            <li><h2>6</h2>
                <div class="result" d-f="0"><b></b><b></b><b></b><b></b><em></em></div>
                <div class="attempt"><i></i><i></i><i></i><i></i><tt></tt></div>
            </li>
            <li><h2>5</h2>
                <div class="result" d-f="0"><b></b><b></b><b></b><b></b><em></em></div>
                <div class="attempt"><i></i><i></i><i></i><i></i><tt></tt></div>
            </li>
            <li><h2>4</h2>
                <div class="result" d-f="0"><b></b><b></b><b></b><b></b><em></em></div>
                <div class="attempt"><i></i><i></i><i></i><i></i><tt></tt></div>
            </li>
            <li><h2>3</h2>
                <div class="result" d-f="0"><b></b><b></b><b></b><b></b><em></em></div>
                <div class="attempt"><i></i><i></i><i></i><i></i><tt></tt></div>
            </li>
            <li><h2>2</h2>
                <div class="result" d-f="0"><b></b><b></b><b></b><b></b><em></em></div>
                <div class="attempt"><i></i><i></i><i></i><i></i><tt></tt></div>
            </li>
            <li><h2>1</h2>
                <div class="result" d-f="0"><b></b><b></b><b></b><b></b><em></em></div>
                <div class="attempt"><i></i><i></i><i></i><i></i><tt></tt></div>
            </li>
        </ul>
    </div>

    <div class="field_buttons">
        <div class="buttons">
            <form id="form" name="form_color">
                <input type="button" class="bcol b_orange" name="inp_color" value="1" onclick="FunColor(this)"/>
                <input type="button" class="bcol b_lilac" name="inp_color" value="2" onclick="FunColor(this)"/>
                <input type="button" class="bcol b_yellow" name="inp_color" value="3" onclick="FunColor(this)"/>
                <input type="button" class="bcol b_green" name="inp_color" value="4" onclick="FunColor(this)"/>
                <input type="button" class="bcol b_red" name="inp_color" value="5" onclick="FunColor(this)"/>
                <input type="button" class="bcol b_blue" name="inp_color" value="6" onclick="FunColor(this)"/>
                <input type="button" class="bcol b_pink" name="inp_color" value="7" onclick="FunColor(this)"/>
                <input type="button" class="bcol b_dark" name="inp_color" value="8" onclick="FunColor(this)"/>
                <input type="button" class="bcol b_cancel" name="inp_cancel" value="Cancel" onclick="FunColor(this)"/>
            </form>
        </div>
    </div>
</div>
</body>

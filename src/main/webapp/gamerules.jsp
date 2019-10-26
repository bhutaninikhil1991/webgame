<%--
  Created by IntelliJ IDEA.
  User: bhutani
  Date: 10/24/19
  Time: 12:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Instructions</title>
</head>
<body>
<h1>Mastermind - Rules of the game</h1>
<ul type="1">
    <li>The computer picks a sequence of colors. The number of colors is the code length.
        The default code length is 4.
    </li>
    <li>The objective of the game is to guess the exact positions of the colors in the computer's sequence.</li>
    <li>Your guess should be a combination of colours R(red), G(Green), Y(yellow), B(blue), O(orange) or P(Purple).</li>
    <li>For each color in your guess that is in the correct color and correct position in the code sequence,
        the computer display a small black color on the left side of the current guess.
    </li>
    <li>For each color in your guess that is in the correct color but is NOT in the correct position in the code
        sequence, the computer display a small white color on the right side of the current guess.
    </li>
    <li>You win the game when you manage to guess all the colors in the code sequence and when they all in the right
        position.
    </li>
    <li>You lose the game if you use all attempts without guessing the computer code sequence.</li>
</ul>
</body>
</html>

<!DOCTYPE html>
<!-- [START_EXCLUDE] -->
<%--
  ~ Copyright 2017 Google Inc.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License"); you
  ~ may not use this file except in compliance with the License. You may
  ~ obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
  ~ implied. See the License for the specific language governing
  ~ permissions and limitations under the License.
  --%>
<!-- [END_EXCLUDE] -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
    <title>Player Info</title>
</head>
<body>
<h1>Player Info</h1>

<form method="POST" action="/mastermind">

    <div class="form-group">
        <label for="first">First</label>
        <input type="text" name="first" id="first" value="" class="form-control"/>
    </div>

    <div class="form-group">
        <label for="last">Last</label>
        <input type="text" name="last" id="last" value="" class="form-control"/>
    </div>

    <button type="submit" class="btn btn-success">Play Game</button>
</form>

</body>
</html>
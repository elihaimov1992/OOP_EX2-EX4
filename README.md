<h1 align="center">
  Object Oriented Programming - Exercise 2
</h1>
<h3 align="center">
  Basic GIS
</h3>
<h4 align="center">
  Eli Haimov (308019306), Elad Cohen (307993030), Yosef Golubchik (209195353)
</h4>

<h4>Ex2:</h4>
This Github project represents a basic geographic information system (GIS).<br />
It supports the creation of GPS points represented by (latitude, longitude, altitude) and operations on those points:<br />
addition of points, distance between points, vector between points...<br />
It also supports the conversion of csv files into GIS daat structures, and conversion of those data structures into a kml file.<br />

<h4>Ex3:</h4>
This exercise is a packman styled game, where there are multiple packmans on the screen, and fruits.
The packmans aren't moved by the player, but by the computer, according to an algorithm whose aim is to create the path that is of the smallest distance.

The GUI allows you to open a csv game file, save the game into a csv file, and add packmans and fruits on the screen at any moment.

<h4>How to play:</h4>
Running the file MyFrame.java will open the game window.

<h4>Shortest Path Algorithm:</h4>
Our algorithm isn't optimal, but it usually finds a reasonable path.
It's a greedy algorithm, which chooses for each packman that fruit that is closest to it, and then chooses the fruits for the rest of the packmans taking into account the time needed for the other packmans to get to the fruits that were already chosen for them.

The program also saves each packmans path into a kml file after the game finished running, which can be opened in google earth and shown with a timeline of when the points on the path were reached.

<p align="center">
  <img width="847" height="400" src="https://github.com/elihaimov1992/OOP_EX2-EX4/blob/master/packman.PNG?raw=true">
</p>

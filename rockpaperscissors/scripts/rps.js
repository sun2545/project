/*
 * Name: Sunhye Kwon
 * Assignment 3
 * Date: June, 2, 2017
 *
 * Description: This javascript is for rock, paper, scissors's game.
 * Files: index.html - the main page for the game
 *         main.css- the main style for this application
 */

// DON'T TOUCH, Just Read  --------------
// array of moves/ids
var pics = ["rock", "paper", "scissors"];
// load the page elements
document.addEventListener("DOMContentLoaded", init, false);
// --------------------------------------
var results = ["You Win!", "You Lose!", "It's a Tie!"];
var playerScore = 0;
var compScore = 0;


function init() {

    //assign the img-container to variable for making standard
    var imgs = document.getElementById("img-container");

    // create header and h1 and send it before img-container
    document.body.insertBefore(document.createElement("header"), document.body.firstElementChild).innerHTML =
        "<h1>Play Rock, Paper, Scissors!</h1>";

    //create div and send before img-container
    imgs.parentNode.insertBefore(document.createElement("div"), imgs).innerHTML = "choose your method destruction";

    //assign className  
    document.getElementsByTagName("div")[0].className = "game-header";

    // put the images in img-container
    for (var i = 0; i < 3; i++) {
        var score = document.createElement("img");
        score.src = "images/" + pics[i] + ".jpg";
        score.id = pics[i];
        imgs.appendChild(score);
        score.className = "rps-img no-img-border";
    }


    //create div and send toward script element
    score = document.createElement("div");
    score.className = "game-header";
    document.body.lastElementChild.appendChild(score).innerHTML ="The computer choose :";

    // create table and send toward script element
    score = document.createElement("table");
    document.body.lastElementChild.appendChild(score);

    //create tr and append in table
    score = score.appendChild(document.createElement("tr"));

    //create td and append in table
    score = score.appendChild(document.createElement("td"));

    //create img and append in td
    score = score.appendChild(document.createElement("img"));

    // assign className
    score.className = "rps-img";

    //assign Id
    score.id = "comp-img";

    //change position which is tr(second parent) for making td
    score = score.parentNode.parentNode;

    //create td in tr and assign className
    score.appendChild(document.createElement("td")).className = "vert-center";

    //create div in td and assign id
    score = score.appendChild(document.createElement("div")).id = "output";

    //create div
    score = document.createElement("div");

    //assign id
    score.id = "score";

    //send toward script element
    document.body.lastElementChild.appendChild(score);

    //create div in div and assign text
    score.appendChild(document.createElement("div")).innerHTML = "Score :";

    //create div
    score = document.createElement("div");

    //make in div and assign text
    score.appendChild(document.createTextNode("You :"));

    //change position as child
    document.getElementById("score").appendChild(score);

    //create span and assign id
    score.appendChild(document.createElement("span")).id = "user-score";

    //change position to parent
    score = score.parentNode;

    //create div
    score = document.createElement("div");

    //append child text node
    score.appendChild(document.createTextNode("Computer :"));

    //change position as child
    document.getElementById("score").appendChild(score);

    //create span in div and assign id
    score.appendChild(document.createElement("span")).id = "comp-score";

    //create footer and send toward script and assign text
    document.body.lastElementChild.appendChild(document.createElement("footer")).innerHTML = "Copyright &copy; 2017 Sunhye Kwon";

    var images = imgs.children;

    // register event for click
    for (i = 0; i < images.length; i++) {
        images[i].addEventListener("click", move);
    }
}


function move() {

    //change the color of border according to click the image
    var borders = document.querySelectorAll(".rps-img");

    //turn off border
    for (var i = 0; i < 3; i++) {

        borders[i].className = "rps-img no-img-border";
    }

    //turn on border when the image clicked
    this.className = "rps-img img-border";


    //call random function for computer choice
    var cMove = getComputerMove();
    var msg = "";

    //change the value which is String to array number using indexOf method
    var index = pics.indexOf(this.id);

    // compare user and computer's selecting and display result of the game
    if (index === cMove)
        msg = results[2];
    else {
        if (index === 0) {
            msg = (cMove === 1) ? results[1] : results[0];
        } else if (index === 1)
            msg = (cMove === 2) ? results[1] : results[0];
        else  msg = (cMove === 0) ? results[1] : results[0];
        (msg === results[0]) ? ++playerScore : ++compScore;
    }

    //Show result message
    document.getElementById("output").innerHTML = msg;

    //Show the increasing user score
    document.getElementById("user-score").innerHTML = playerScore;

    //Show the increasing computer score
    document.getElementById("comp-score").innerHTML = compScore;
}

function getComputerMove() {

    //set computer's choice randomly
    var cMove = Math.floor((Math.random() * 3 ));

    //display the image which is selected
    document.getElementById("comp-img").src = "images/" + pics[cMove] + ".jpg";

    //return random value
    return cMove;
}

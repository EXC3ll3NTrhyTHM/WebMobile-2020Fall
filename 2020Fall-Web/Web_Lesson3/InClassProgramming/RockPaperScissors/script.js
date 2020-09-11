var p1score;
var p2score;
var rock = 1;
var paper = 2;
var scissors = 3;


function rockpaperscissors() {
    let p1 = Math.floor(Math.random() * 2)+1;
    let p2 = Math.floor(Math.random() * 2)+1;
    switch (p1) {
        case 1:
            p1score = "Rock";
            break;
        case 2:
            p1score = "Paper";
            break;
        case 3:
            p1score = "Scissors";
            break;
    }
    switch (p2) {
        case 1:
            p2score = "Rock";
            break;
        case 2:
            p2score = "Paper";
            break;
        case 3:
            p2score = "Scissors";
            break;
    }
    if(p1==p2) {
        return "Tie";
    } else if (p1>p2 && p1 != 3) {
        return "Player One Wins";
    } else if (p2>p1 && p2!=3) {
        return "Player Two Wins";
    } else if (p1==3 && p2==1) {
        return "Player Two Wins"
    } else if (p2==3 && p1==1) {
        return "Player One Wins"
    } else {
        return "Something went wrong"
    }
}
var direction = 0;
var snake;
var apple;

function setup() {
    createCanvas(540, 540); 
    snake = new Snake();
    apple = new Apple(snake);
    apple.start();
}

function draw() {
    background(0);
    snake.show();
    apple.square.show();
    if(frameCount % 5 == 0) {
        if(Math.abs(direction - snake.direction) != 2) {
            snake.update(direction);
        } else {
            snake.update(snake.direction);
        }
    }
    if(snake.collision()) {
        alert("Game Over!\nScore: " + snake.score + "\nYour snake was " + (snake.score + snake.begin) + " squares long!");
        direction = 0;
        snake.end();
        snake.start();
        apple.new();
    }
    if(apple.eaten()) {
        snake.score++;
        snake.grow();
        apple.new();    
    }
}

function keyPressed() {
    switch(keyCode) {
        case LEFT_ARROW:
            direction = 0;
            break;
        case UP_ARROW:
            direction = 1;
            break;    
        case RIGHT_ARROW:
            direction = 2;
            break;
        case DOWN_ARROW:
            direction = 3;
            break;
    }
}
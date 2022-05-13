class Apple {
    constructor(snake) {
        this.snake = snake;
        this.start();
    }
    
    start() {
        do {
            this.x = Math.floor(27 * Math.random()) * 20;
            this.y = Math.floor(27 * Math.random()) * 20;
        } while (this.eaten());
        this.square = new Square(this.x, this.y, 255, 0, 0);
    }

    new() {
        do {
            this.x = Math.floor(27 * Math.random()) * 20;
            this.y = Math.floor(27 * Math.random()) * 20;
        } while (this.eaten());
        this.square.x = this.x;
        this.square.y = this.y;
    }

    eaten() {
        return this.x == this.snake.squares[0].x && this.y == this.snake.squares[0].y;
    }
}
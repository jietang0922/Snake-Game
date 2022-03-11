function Apple(snake) {
    this.x = Math.floor(27 * Math.random()) * 20;
    this.y = Math.floor(27 * Math.random()) * 20;
    
    this.start = function() {
        while(this.eaten()) {
            this.x = Math.floor(27 * Math.random()) * 20;
            this.y = Math.floor(27 * Math.random()) * 20;
        }
        this.square = new Square(this.x, this.y, 255, 255, 255);
    }

    this.new = function() {
        do {
            this.x = Math.floor(27 * Math.random()) * 20;
            this.y = Math.floor(27 * Math.random()) * 20;
        } while (this.eaten());
        this.square.x = this.x;
        this.square.y = this.y;
    }

    this.eaten = function() {
        for(i = 0; i < snake.squares.length; i++) {
            if(this.x == snake.squares[i].x && this.y == snake.squares[i].y) {
                return true;
            }
        }
        return false;
    }
}
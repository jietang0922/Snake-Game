function Snake(colors) {
    this.direction;
    this.squares = [];
    this.size = 5;
    this.begin = this.size;
    this.score;
    this.colors = colors;
    this.period = 3;

    this.show = function() {
        for(i = 0; i < this.squares.length; i++) {
            this.squares[i].show();
        }
    }

    this.update = function(dir) {
        this.direction = dir;
        for(i = this.squares.length - 1; i >= 1; i--) {
            this.squares[i].x = this.squares[i - 1].x;
            this.squares[i].y = this.squares[i - 1].y;
        }
        this.squares[0].update(this.direction);
    }
    
    this.start = function() {
        this.direction = 0;
        this.begin = 5;
        this.score = 0;
        for(i = 0; i < this.begin; i++) {
            let color = this.colors[Math.floor(Math.random() * this.colors.length)];
            let square = new Square(260 + 20 * i, 260, color[0], color[1], color[2]);
            this.squares.push(square);
        }
    }

    this.collision = function() {
        for(i = 4; i < this.squares.length; i++) {
            if(this.squares[0].x == this.squares[i].x && this.squares[0].y == this.squares[i].y) {
                return true;
            }
        }
        return false;
    }

    this.grow = function() {
        let x = 2 * this.squares[this.squares.length - 1].x - this.squares[this.squares.length - 2].x;
        let y = 2 * this.squares[this.squares.length - 1].y - this.squares[this.squares.length - 2].y;
        let color = this.colors[Math.floor(Math.random() * this.colors.length)];
        let square = new Square(x, y, color[0], color[1], color[2]);
        this.squares.push(square);
    }

    this.end = function() {
        this.squares = [];
        console.log("end");
    }
}
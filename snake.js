class Snake {
    constructor() {
        this.squares = [];
        this.size = 5;
        this.begin = this.size;
        this.score;
        this.red = 0;
        this.green = 255;
        this.blue = 255;
        this.head;
        this.start();
    }
    
    show() {
        for(let i = 0; i < this.squares.length; i++) {
            this.squares[i].show();
        }
        this.head.show();
    }

    update(dir) {
        this.direction = dir;
        for(let i = this.squares.length - 1; i >= 1; i--) {
            this.squares[i].x = this.squares[i - 1].x;
            this.squares[i].y = this.squares[i - 1].y;
        }
        this.squares[0].update(this.direction);
        this.head.update(this.squares[0].x + this.squares[0].w / 2, this.squares[0].y + this.squares[0].l / 2)
    }
    
    start() {
        this.direction = 0;
        this.score = 0;
        for(let i = 0; i < this.begin; i++) {
            let square = new Square(260 + 20 * i, 260, this.red, this.green, this.blue);
            this.squares.push(square);
        }
        this.head = new Head(this.squares[0].x + this.squares[0].w / 2, this.squares[0].y + this.squares[0].l / 2, 10);
    }

    collision() {
        for(let i = 4; i < this.squares.length; i++) {
            if(this.squares[0].x == this.squares[i].x && this.squares[0].y == this.squares[i].y) {
                console.log(this.squares.length);
                console.log("COLLISION");
                return true;
            }
        }
        return false;
    }

    grow() {
        let x = 2 * this.squares[this.squares.length - 1].x - this.squares[this.squares.length - 2].x;
        let y = 2 * this.squares[this.squares.length - 1].y - this.squares[this.squares.length - 2].y;
        let square = new Square(x, y, this.red, this.green, this.blue);
        this.squares.push(square);
    }

    end() {
        this.squares = [];
        console.log("end");
    }
}
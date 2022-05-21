class Snake {
    constructor(skins, skin) {
        this.direction = 0; //default left
        this.squares = [];
        this.size = 5;
        this.begin = this.size;
        this.score;
        this.skins = skins;
        this.skin = skin;
        this.skinner = [];
        this.skinning = [];
        this.period = 5;
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
        this.begin = 5;
        this.score = 0;
        if(this.skin == 1) {
            for(let color of Object.values(this.skins["random"])) {
                this.skinning.push(color);
            }
        }
        this.skinner = [...this.skinning];
        for(let i = 0; i < this.begin; i++) {
            let color = this.skinner.splice(Math.floor(Math.random() * this.skinner.length), 1)[0];
            let square = new Square(260 + 20 * i, 260, color[0], color[1], color[2]);
            this.squares.push(square);
            if(this.skinner.length == 0) this.skinner = this.skinning;
        }
        this.head = new Head(this.squares[0].x + this.squares[0].w / 2, this.squares[0].y + this.squares[0].l / 2, 10);
    }

    collision() {
        for(let i = 4; i < this.squares.length; i++) {
            if(this.squares[0].x == this.squares[i].x && this.squares[0].y == this.squares[i].y) {
                return true;
            }
        }
        return false;
    }

    grow() {
        let x = 2 * this.squares[this.squares.length - 1].x - this.squares[this.squares.length - 2].x;
        let y = 2 * this.squares[this.squares.length - 1].y - this.squares[this.squares.length - 2].y;
        let color = this.skinner.splice(Math.floor(Math.random() * this.skinner.length), 1)[0];
        let square = new Square(x, y, color[0], color[1], color[2]);
        this.squares.push(square);
        if(this.skinner.length == 0) this.skinner = [...this.skinning];
    }

    end() {
        this.squares = [];
        // console.log("end");
    }
}
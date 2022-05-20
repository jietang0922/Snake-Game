class Square {
    constructor(x, y, r, g, b) {
        this.x = x;
        this.y = y;
        this.w = 20;
        this.l = this.w;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    show() {
        fill(this.r, this.g, this.b);
        rect(this.x, this.y, this.w, this.l);
    }

    update(dir) {
        switch(dir) {
            case 0: //left
                this.x -= this.w;
                break;
            case 1: //up
                this.y -= this.w;
                break;
            case 2: //right
                this.x += this.w;
                break;
            case 3: //down
                this.y += this.w;
                break;
        }
        if(this.x < 0) {
            this.x += width;
        }
        if(this.x == width) {
            this.x = 0;
        }
        if(this.y < 0) {
            this.y += height;
        }
        if(this.y == height) {
            this.y = 0;
        }
    }
}


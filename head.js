class Head {
    constructor(x, y, r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    show() {
        fill(0, 0, 0);
        ellipse(this.x, this.y, this.r, this.r);
    }

    update(x, y) {
        this.x = x;
        this.y = y;
    }
}
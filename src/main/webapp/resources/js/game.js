var PointsGame = function(){
    this.width = 22;
    this.height = 18;
    this.board = [  [0,0,0,0,0,0],
                    [0,0,0,0,0,0],
                    [0,0,0,0,0,0],
                    [0,0,0,0,0,0],
                    [0,0,0,0,0,0],
                    [0,0,0,0,0,0]];
    this.currentPlayer = 'YOU'; //OTHER
    this.yourColor=1;
    this.otherColor=2;

}
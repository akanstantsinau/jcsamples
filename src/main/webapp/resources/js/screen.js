var GameScreen = function(game){


    this.scoreTexture = PIXI.Texture.fromImage("resources/images/score.png");
    this.currentPlayer=0;
    this.data = undefined;


    this.updateScreen = function(board){
        this.pollServer();
    };


    this.pollServer = function(){
        var xmlhttp = new window.XMLHttpRequest();
        var url = "/dots/game";
        var screen = this;

        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                screen.data = JSON.parse(xmlhttp.responseText);
                //console.log(screen.data);
                redraw();
                screen.check();
                if(screen.data.completed){
                                    finalizeGame(screen.data.winner.name);
                                }
            }
        }
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    };

    this.go = function(x,y){
        var xmlhttp = new window.XMLHttpRequest();
        var url = "/dots/go?x="+x+"&y="+y;
        var screen = this;

        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                screen.data = JSON.parse(xmlhttp.responseText);
                redraw();
                screen.check();
                if(screen.data.completed){
                                    finalizeGame(screen.data.winner.name);
                                }
            }
        }
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    }

    this.check = function(){
        var xmlhttp = new window.XMLHttpRequest();
        var url = "/dots/check";
        var screen = this;

        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                screen.data = JSON.parse(xmlhttp.responseText);
                redraw();
                if(screen.data.completed){
                    finalizeGame(screen.data.winner.name);
                }
            }
        }
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    }
    this.refreshScreen = function(data){


        //this.drawScore1(data.player1Score);
        //this.drawScore2(data.player2Score);

        renderer.render(stage);
    }



    /*this.drawScore1 = function(score){
        scoreSprite = new PIXI.Sprite(this.scoreTexture);
        scoreSprite.x = 40;
        scoreSprite.y = 300;
        scoreText = new PIXI.Text(score, {font:"35px dots_font", fill:"black"});
                        scoreText.x=47;
                        scoreText.y=70;
                        scoreSprite.addChild(scoreText);
        this.graphics.addChild(scoreSprite);
    }

    this.drawScore2 = function(score){
            scoreSprite = new PIXI.Sprite(this.scoreTexture);
            scoreSprite.x = 950;
            scoreSprite.y = 300;
            scoreText = new PIXI.Text(score, {font:"35px dots_font", fill:"black"});
                            scoreText.x=47;
                            scoreText.y=70;
                            scoreSprite.addChild(scoreText);
            this.graphics.addChild(scoreSprite);
    }*/


}

var countdown = setInterval(function(){
    if(screen && screen.data && screen.data.stepTimeout > 0){
        screen.data.stepTimeout--;
    }else{
    clearInterval(countdown);
        screen.check();
    }
    //if( renderer && rootStage){
    //    renderer.render(rootStage);
   // }
     redraw();
    }, 1000);
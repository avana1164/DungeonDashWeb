var canvas = document.getElementById('canvasID');
var c = canvas.getContext('2d');
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

//var image = new Image();
var images = [];
let x_dim = 48;
let y_dim = 11;
   
let spriteMap = [
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
]

for(let i = 0; i < y_dim; i++){
    for(let j = 0; j < x_dim; j++){
        images[i*x_dim + j] = new Image();
        images[i*x_dim + j].onload = function(){
            c.drawImage(images[i*x_dim + j], 32*j, 32*i, 32, 32);
        }
        if(spriteMap[i*x_dim + j] == 0){
            images[i*x_dim + j].src = 'drawing\\tiles\\BossFloor.png';
        } else if(spriteMap[i*x_dim + j] == 1){
            images[i*x_dim + j].src = 'drawing\\tiles\\DungeonFloorCracked.png';
        } else if(spriteMap[i*x_dim + j] == 2){
            images[i*x_dim + j].src = 'drawing\\tiles\\floorNormal.png';
        } else if(spriteMap[i*x_dim + j] == 3){
            images[i*x_dim + j].src = 'drawing\\tiles\\grasstile.jpg';
        } else if(spriteMap[i*x_dim + j] == 4){
            images[i*x_dim + j].src = 'drawing\\tiles\\lilypad.png';
        } else if(spriteMap[i*x_dim + j] == 5){
            images[i*x_dim + j].src = 'drawing\\tiles\\treeSprite.png';
        } else if(spriteMap[i*x_dim + j] == 6){
            images[i*x_dim + j].src = 'drawing\\tiles\\wall_goo.png';
        } else if(spriteMap[i*x_dim + j] == 7){
            images[i*x_dim + j].src = 'drawing\\tiles\\wall_mid.png';
        } else if(spriteMap[i*x_dim + j] == 8){
            images[i*x_dim + j].src = 'drawing\\tiles\\Water3.png';
        } 
    }
}


// images[0].src = 'drawing\\tiles\\BossFloor.png';
// images[1].src = 'drawing\\tiles\\DungeonFloorCracked.png';
// images[2].src = 'drawing\\tiles\\floorNormal.png';
// images[3].src = 'drawing\\tiles\\grasstile.jpg';
// images[4].src = 'drawing\\tiles\\lilypad.png';
// images[5].src = 'drawing\\tiles\\treeSprite.png';
// images[6].src = 'drawing\\tiles\\wall_goo.png';
// images[7].src = 'drawing\\tiles\\wall_mid.png';
// images[8].src = 'drawing\\tiles\\Water3.9.png';
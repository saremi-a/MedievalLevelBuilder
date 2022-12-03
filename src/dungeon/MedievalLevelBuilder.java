package dungeon;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;

public class MedievalLevelBuilder {
    private Level newLevel;
    private int allRoomCount;
    private int rc;
    private int mc;
    private int allMonsterCount;
    private int allTreasureCount;
    private int roomNum=0;
    private int treasureCount=0;
    private int tc=0;
    //private String room_description;
    //private List<Room> rooms;

    //A constructor that takes the number of the level that is being created since most games have many
    // levels. It should also take non-negative values for the target number of rooms, monsters,
    // and treasures that the level is expected to have.

    public MedievalLevelBuilder(int number_of_level, int allRoomCount,int allMonsterCount, int allTreasureCount){
        if(number_of_level<0 || allRoomCount<0 || allMonsterCount<0 || allTreasureCount<0){
            throw new IllegalArgumentException();
        }
        newLevel= new Level(number_of_level);
        this.allRoomCount=allRoomCount;
        this.allMonsterCount=allMonsterCount;
        this.allTreasureCount=allTreasureCount;
        this.rc=0;
        this.mc=0;
        this.tc=0;
    }

    //An addRoom method that has a single parameter for the room's description and a room with
    // that description to the level.
    // The method throws an IllegalStateException if too many rooms are added to the level.
    public MedievalLevelBuilder addRoom(String room_description){
        //this.room_description=room_description;
        if(rc>=allRoomCount){
        //if(roomCount<roomArrayList.size()){
            throw new IllegalStateException("too many room are added to the level");
        }else{
            newLevel.addRoom(room_description);
            rc++;
        }
        return this;
    }

    //A goblin is a "mischievous and very unpleasant, vengeful, and greedy creature
    // whose primary purpose is to cause trouble to humankind" and are the weakest type of monster
    // in our level. They are quite numerous and often travel in packs. The addGoblins method should
    // add the specified number of goblins
    // to the specified room giving each 7 hit points.
    //Adding monsters to the level should raise an IllegalStateException if the target number of monsters
    // has already been reach and an IllegalArgumentException if the target room has not yet been created.
    public MedievalLevelBuilder addGoblins(int roomNum, int monsterCount){
        for(int i=0;i<monsterCount;i++){
            if (isAddMonsterValid(roomNum)){
                Monster newGoblin= new Monster("goblin","mischievous and very unpleasant, vengeful, and greedy creature whose primary purpose" +
                        " is to cause trouble to humankind",7);
                newLevel.addMonster(roomNum,newGoblin);
                mc++;
            }
        }
        return  this;
    }

    //An orc is a "brutish, aggressive, malevolent being serving evil" but tends to be more of a
    // loner than the goblins. The addOrc method should add a
    // single orc to the specified room giving them 20 hit points.
    public MedievalLevelBuilder addOrc(int roomNum){
        if (isAddMonsterValid(roomNum)){
            newLevel.addMonster(roomNum, new Monster("orc","brutish, aggressive, malevolent being serving evil",20));
            mc++;
        }
        return this;
    }

    //Even stronger than orcs are orges. An ogre is a "large, hideous man-like being that likes to eat
    // humans for lunch".They have 50 hit points.
    public MedievalLevelBuilder addOgre(int roomNum){
    // public MedievalLevelBuilder addOrges(int roomNum){
        if (isAddMonsterValid(roomNum)){
            newLevel.addMonster(roomNum, new Monster("ogre","large, hideous man-like being that likes to eat humans for lunch",50));
            mc++;
        }
        return this;
    }

    //Our dungeon can also contain humans that will be stored as a type of monster.
    // The details of the human must be provided to the addHuman method.
    //(int roomIndexNumber, String name, String description, int hp);
    public MedievalLevelBuilder addHuman(int roomNum,String name,String description,int hitPoint){
        if (isAddMonsterValid(roomNum)){
            newLevel.addMonster(roomNum, new Monster(name,description,hitPoint));
            mc++;
        }
        return this;
    }

    //The addPotion method should add "a healing potion" (value = 1) to the specified room.
    public MedievalLevelBuilder addPotion(int roomNum){
        if (isAddTreasuresValid(roomNum)){
            newLevel.addTreasure(roomNum, new Treasure("a healing potion", 1));
            tc++;
        }
        return this;
    }

    //The addGold method should add "pieces of gold" of the specified value to the specified room.
    public MedievalLevelBuilder addGold(int roomNum,int value){
        if (isAddTreasuresValid(roomNum)){
            newLevel.addTreasure(roomNum, new Treasure("pieces of gold", value));
            tc++;
        }
        return this;
    }

    //The addWeapon method should add the specified weapon to the specified room.
    // All weapons have a value equal to 10.\ikju
    public MedievalLevelBuilder addWeapon(int roomNum,String weapon){
        if (isAddTreasuresValid(roomNum)){
            newLevel.addTreasure(roomNum, new Treasure(weapon, 10));
            tc++;
        }
        return this;
    }

    //The addSpecial method can be used to add the most exclusive treasures to the specified room.
    public MedievalLevelBuilder addSpecial(int roomNum,String exclusivein, int treasureValue){
        if (isAddTreasuresValid(roomNum)){
            newLevel.addTreasure(roomNum, new Treasure(exclusivein, treasureValue));
            tc++;
        }
        return this;
    }
    //Similar to adding monsters, adding treasures should raise an IllegalStateException if
    // the target number of treasures has already been reach and an
    // IllegalArgumentException if the target room has not yet been created.
    public boolean isAddTreasuresValid(int roomIndex) {
        if (roomIndex+1 > allRoomCount){
            throw new IllegalArgumentException("target room has not yet been created");
        }
        if (tc >= allTreasureCount){
            throw new IllegalStateException("too many treasures are created");
        }else{
            return true;
        }
    }
    public boolean isAddMonsterValid(int roomIndex) {
        if (roomIndex+1 > allRoomCount){
            throw new IllegalArgumentException("target room has not yet been created");
        }
        if (mc >= allMonsterCount){
            throw new IllegalStateException("too many Monsters are created");
        }else{
            return true;
        }
    }
    public Level build(){
        //check the number of room, monster,treasure with constructor
        if(rc !=allRoomCount || mc !=allMonsterCount || tc !=allTreasureCount){
            throw  new IllegalStateException();
            //throw  new IllegalStateException("the level is not complete yet");
        }
        return newLevel;
    }
}






/*
 add orc
 //        if(mc>=allMonsterCount){
//            throw new IllegalStateException();
//            //throw new IllegalStateException("monster's number has already reached to the total");
//        }
//        if(roomNum >rc){
//            throw new IllegalArgumentException();
//            //throw new IllegalArgumentException("this room hasn't created yet");
//        }
//        Monster newOrc= new Monster("orc","brutish, aggressive",20);
//        newLevel.addMonster(roomNum,newOrc);
//        mc++;

 add orge

 //        if(mc>allMonsterCount){
//            throw new IllegalStateException();
//            //throw new IllegalStateException("monster's number has already reached to the total");
//        }
//        if(roomNum >rc){
//            throw new IllegalArgumentException();
//            //throw new IllegalArgumentException("this room hasn't created yet");
//        }
//        Monster newOrges= new Monster("orges","large, hideous man-like",50);
//        newLevel.addMonster(roomNum,newOrges);
//        mc++;

add human
//        if(mc>allMonsterCount){
//            throw new IllegalStateException();
//            //throw new IllegalStateException("monster's number has already reached to the total");
//        }
//        if(roomNum > rc){
//            throw new IllegalArgumentException();
//            //throw new IllegalArgumentException("this room hasn't created yet ");
//        }
//        Monster newHuman= new Monster(name,description,hitPoint);
//        newLevel.addMonster(roomNum,newHuman);
//        mc++;


//        if(tc>allTreasureCount){
//            throw new IllegalStateException();
//            //throw new IllegalStateException("treasure's number has already reached to the total");
//        }
//        if(roomNum != allRoomCount){
//            throw new IllegalArgumentException();
//        }
//        Treasure treasure= new Treasure("a healing potion",1);
//        newLevel.addTreasure(roomNum-1,treasure);
//        tc++;


add gold
//        if(tc>allTreasureCount){
//            throw new IllegalStateException();
//            //throw new IllegalStateException("treasure's number has already reached to the total");
//        }
//        if(roomNum !=allRoomCount){
//            throw new IllegalArgumentException();
//        }
//        Treasure treasure=new Treasure("pieces of gold",value);
//        newLevel.addTreasure(roomNum-1,treasure);
//        tc++;



add goblin
            //room.addMonster(newGoblin);
            //room.
            //addMonster(int roomNumber, Monster m)
            //how to get the room number?




add weapon
//        if(tc>allTreasureCount){
//            throw new IllegalStateException();
//            //throw new IllegalStateException("treasure's number has already reached to the total");
//        }
//        if(roomNum != allRoomCount){
//            throw new IllegalArgumentException();
//        }
//        Treasure treasure= new Treasure(weapon,10);
//        newLevel.addTreasure(roomNum-1,treasure);
//        tc++;


//-------

add special
//        if(tc>allTreasureCount){
//            throw new IllegalStateException();
//            //throw new IllegalStateException("treasure's number has already reached to the total");
//        }
//        if(roomNum != allRoomCount){
//            throw new IllegalArgumentException();
//        }
//        Treasure treasure= new Treasure(exclusivein,treasureValue);
//        newLevel.addTreasure(roomNum,treasure);
//        tc++;
 */

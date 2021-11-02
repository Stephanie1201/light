package edu.episen.si.ing1.pds.client.model;

public class Floor {

    private static int floor_id = 1;
    private static int floor_number = 1;

    public static int getFloor_number() {
        return floor_number;
    }

    public static void setFloor_number(int floor_number) {
        Floor.floor_number = floor_number;
    }

    public static int getFloor_id(){
        return floor_id;
    }

    public static void setFloor_id(int floor_id){
        Floor.floor_id = floor_id;
    }
}

package edu.episen.si.ing1.pds.client.model;

public class Building {
    private static int building_id;
    private static String builing_name = "";

    public static int getBuiling_id(){
        return building_id;
    }

    public static void setBuilding_id(int building_id){
        Building.building_id = building_id;
    }

    public static String getBuiling_name() {
        return builing_name;
    }

    public static void setBuiling_name(String builing_name) {
        Building.builing_name = builing_name;
    }
}

package edu.episen.si.ing1.pds.client.model;

public class Equipment {
    private static int equipment_id;
    private static String equipment_name = "";

    public static int getEquipment_id() { return equipment_id; }

    public static void setEquipment_id(int equipment_id) {
        Equipment.equipment_id = equipment_id;
    }

    public static String getEquipment_name() {
        return equipment_name;
    }

    public static void setEquipment_name(String equipment_name) {
        Equipment.equipment_name = equipment_name;
    }
}

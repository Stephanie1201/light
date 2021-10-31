package edu.episen.si.ing1.pds.client.model;

public class Space {
    private static int space_id = 1;
    private static String space_name = "";
    private static Integer space_type = 0;
    private static int countable;

    public static int getSpace_id() {
        return space_id;
    }

    public static void setSpace_id(int space_id) {
        Space.space_id = space_id;
    }

    public static String getSpace_name() {
        return space_name;
    }

    public static void setSpace_name(String space_name) {
        Space.space_name = space_name;
    }

    public static Integer getSpace_type() {
        return space_type;
    }

    public static void setSpace_type(Integer space_type) {
        Space.space_type = space_type;
    }

    public static int getCountable(int countable){return Space.countable;}

    public static void setCountable(int countable){ Space.countable=countable;}

}

package edu.episen.si.ing1.pds.client.model;

public class Company {
    private static int company_id;
    private static String company_name="";

    public static int getCompany_id(){
        return company_id;
    }

    public static void setCompany_id(int company_id){
        Company.company_id = company_id;
    }

    public static String getCompany_name() {
        return company_name;
    }

    public static void setCompany_name(String company_name) {
        Company.company_name = company_name;
    }

}

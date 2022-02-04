package com.commento.cleanair.utils;

public class CalculateAirCondition {

    private static Double typeNumber;

    public static String getPM10Grade(String PM10){
        setTypeNumber(PM10);

        if(typeNumber >= 151) return "CRITICAL";
        else if(typeNumber >= 81) return "BAD";
        else if(typeNumber >= 31) return "SOSO";
        else return "GOOD";
    }

    public static String getPM25Grade(String PM25){
        setTypeNumber(PM25);

        if(typeNumber >= 76) return "CRITICAL";
        else if(typeNumber >= 36) return "BAD";
        else if(typeNumber >= 16) return "SOSO";
        else return "GOOD";
    }

    public static String getO3Grade(String O3){
        setTypeNumber(O3);

        if(typeNumber >= 0.151) return "CRITICAL";
        else if(typeNumber >= 0.091) return "BAD";
        else if(typeNumber >= 0.031) return "SOSO";
        else return "GOOD";
    }

    public static String getNO2Grade(String NO2){
        setTypeNumber(NO2);

        if(typeNumber >= 0.201) return "CRITICAL";
        else if(typeNumber >= 0.061) return "BAD";
        else if(typeNumber >= 0.031) return "SOSO";
        else return "GOOD";
    }

    public static String getCOGrade(String CO){
        setTypeNumber(CO);

        if(typeNumber >= 15.01) return "CRITICAL";
        else if(typeNumber >= 9.01) return "BAD";
        else if(typeNumber >= 2.01) return "SOSO";
        else return "GOOD";
    }

    public static String getSO2Grade(String SO2){
        setTypeNumber(SO2);

        if(typeNumber >= 0.151) return "CRITICAL";
        else if(typeNumber >= 0.051) return "BAD";
        else if(typeNumber >= 0.021) return "SOSO";
        else return "GOOD";
    }

    public static void setTypeNumber(String airFormat){
        try {
            typeNumber = Double.parseDouble(airFormat);
        } catch (NumberFormatException e){
            throw new NumberFormatException("변경할 수 없는 수치입니다");
        }
    }
}

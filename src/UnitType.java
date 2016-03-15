/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Seema
 */
public class UnitType {
    private String unitTypeID;
    private String type; //food pet clothing cleaning
    private int totalUnitsOfType; // total amount of units allocated to type

    public UnitType(String unitTypeID, String type, int totalUnitsOfType) {
        this.unitTypeID = unitTypeID;
        this.type = type;
        this.totalUnitsOfType = totalUnitsOfType;
    }

    public String getUnitTypeID() {
        return unitTypeID;
    }

    public String getType() {
        return type;
    }

    public int getTotalUnitsOfType() {
        return totalUnitsOfType;
    }

    public void setUnitTypeID(String unitTypeID) {
        this.unitTypeID = unitTypeID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalUnitsOfType(int totalUnitsOfType) {
        this.totalUnitsOfType = totalUnitsOfType;
    }

    @Override
    public String toString() {
        return "unitType{" + "unitTypeID=" + unitTypeID + ", type=" + type 
                + ", totalUnitsOfType=" + totalUnitsOfType + '}';
    }
    
    
    
}

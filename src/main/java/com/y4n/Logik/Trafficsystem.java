package com.y4n.Logik;

import java.util.ArrayList;
import java.util.List;

public class Trafficsystem {
    protected Crossroad Crossroad_A = new Crossroad("Crossroad A",(byte) 0xA0,(byte)0xA1,(byte)0xA2,(byte)0xA3);
    protected Crossroad Crossroad_B = new Crossroad("Crossroad B",(byte) 0xB0,(byte)0xB1,(byte)0xB2,(byte)0xB3);
    protected Crossroad Crossroad_C = new Crossroad("Crossroad C",(byte) 0xC0,(byte)0xC1,(byte)0xC2,(byte)0xC3);

    protected List Vehicle_list = new ArrayList();
    protected List Vehicle_list_Crossroad_A_idle = new ArrayList();
    protected List Vehicle_list_Crossroad_B_idle = new ArrayList();
    protected List Vehicle_list_Crossroad_C_idle = new ArrayList();


    public void add_Vehicle(byte ID){
        Vehicle temp = new Vehicle(ID);
        Vehicle_list.add(temp);

    }

    public Vehicle acces_Vehicle(int i){
        return (Vehicle) Vehicle_list.get(i);
    }

    public void add_Vehicle_idle(Vehicle Vehicle, Crossroad Crossroad){
        switch (Crossroad.name){
            case "Crossroad A":
                Vehicle_list_Crossroad_A_idle.add(Vehicle);
                break;
            case "Crossroad B":
                Vehicle_list_Crossroad_B_idle.add(Vehicle);
                break;
            case "Crossroad C":
                Vehicle_list_Crossroad_C_idle.add(Vehicle);
                break;
            default:
                System.out.println("Idle "+Vehicle.name+" can not be assigned to the list of "+Crossroad.name+" because the Crossroad is not listed");
        }


    }

    public Vehicle acces_idle_Vehicle(int i, Crossroad Crossroad){
        switch (Crossroad.name){
            case "Crossroad A":
                return (Vehicle) Vehicle_list_Crossroad_A_idle.get(i);
            case "Crossroad B":
                return (Vehicle) Vehicle_list_Crossroad_B_idle.get(i);
            case "Crossroad C":
                return (Vehicle) Vehicle_list_Crossroad_C_idle.get(i);
            default:
                System.out.println("Idle vehicles can not be accessed because the Crossroad is not listed");
                Vehicle Errorvehicle = new Vehicle((byte) 0xFF);
                return Errorvehicle;
        }
    }
    public byte Process_vehicle_status(byte ID, byte position, byte direction, byte speed ){
        Vehicle Processed_vehicle = acces_Vehicle(ID);
        Processed_vehicle.setStatus(position,direction,speed);
        if(Processed_vehicle.determine_crossroad(Crossroad_A, Crossroad_B, Crossroad_C) == 1) {
            if ((Processed_vehicle.getPosition() & 0x0F) < 4) {
                Processed_vehicle.determine_priority();
                Processed_vehicle.determine_Area();
                if(Processed_vehicle.book_Area()==1){
                    Processed_vehicle.setClearance((byte) 0x01);
                    return Processed_vehicle.clearance;
                }else{
                    Processed_vehicle.setClearance((byte) 0x00);
                    return Processed_vehicle.clearance;
                }
            }else if((Processed_vehicle.getPosition() & 0x0F) < 8){
                Processed_vehicle.clear_Area();
                Processed_vehicle.setClearance((byte) 0x00);
                return Processed_vehicle.clearance;

            }else{
                System.out.println("Unknown Position on "+Processed_vehicle.Crossroad_current.getName()+", vehiclestatus was not processed");
                Processed_vehicle.setClearance((byte) 0x00);
                return Processed_vehicle.clearance;
            }
        }else{
            System.out.println("Unknown Crossroad, vehiclestatus was not processed");
            Processed_vehicle.setClearance((byte) 0x00);
            return Processed_vehicle.clearance;
        }


    }

}

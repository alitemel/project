package com.ath2o.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class VehicleSearchSpecificException extends Exception{
    private static final Logger logger =  LogManager.getLogger(VehicleSearchSpecificException.class);

    private String errorCode;
    private String description;

    public VehicleSearchSpecificException(String errorCode, String description, Throwable thr)
    {
        super(description, thr);
        this.errorCode = errorCode;
        this.description = description;
    }

    public VehicleSearchSpecificException(String errorCode, String description )
    {
        super(description, null);
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString()
    {
        return "Error Code : " + errorCode + " ; Description : " + description;
    }
}

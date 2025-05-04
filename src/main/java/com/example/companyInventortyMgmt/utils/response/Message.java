package com.example.companyInventortyMgmt.utils.response;

import lombok.Getter;

@Getter
public enum Message {
    OPERATION_SUCCESS("Operation successful"),
    OPERATION_FAILED("Operation failed");

    private String value;
    
    Message(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}

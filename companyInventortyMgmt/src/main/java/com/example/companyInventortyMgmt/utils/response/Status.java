package com.example.companyInventortyMgmt.utils.response;

import lombok.Getter;

@Getter
public enum Status {
    SUCCESS("Success"),
    FAILED("Failed"),
    ERROR("error");

    private String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
            return value;
    }
}

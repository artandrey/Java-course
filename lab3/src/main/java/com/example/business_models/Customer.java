package com.example.business_models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class Customer extends BaseModel {
    @NonNull
    private String name;
    @NonNull
    private String phone;

}

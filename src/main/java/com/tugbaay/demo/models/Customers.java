package com.tugbaay.demo.models;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customers {

    private String id;
    private String name;
    private String surname;
    private String email;

}

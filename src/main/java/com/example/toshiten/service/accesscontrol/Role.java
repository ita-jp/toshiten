package com.example.toshiten.service.accesscontrol;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class Role {
    private long id;
    private String name;
    private List<Permission> permissions = new LinkedList<>();
}

package com.koleff.kare.exercise.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MachineType {
    DUMBBELL(1),
    BARBELL(2),
    MACHINE(3),
    CALISTHENICS(4),
    NONE(-1);

    private final int machineTypeId;

    public static MachineType fromId(int id) {
        for (MachineType machineType : values()) {
            if (machineType.getMachineTypeId() == id) {
                return machineType;
            }
        }
        return NONE;
    }
}

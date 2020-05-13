package driver;

import gearbox.ExternalSystems;

public class Rpm {
    private final ExternalSystems externalSystems;

    public Rpm(ExternalSystems externalSystems) {
        this.externalSystems = externalSystems;
    }

    public Double get(){
        return externalSystems.getCurrentRpm();
    }
}

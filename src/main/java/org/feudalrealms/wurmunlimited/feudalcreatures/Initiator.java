package org.feudalrealms.wurmunlimited.feudalcreatures;

import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreatures;
import org.feudalrealms.wurmunlimited.feudalcreatures.creatures.*;
import java.util.logging.Logger;


public class Initiator implements WurmServerMod, Initable {

    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public void init() {
        LOGGER.info("Init called");
        try {
            ModCreatures.init();
            ModCreatures.addCreature((ModCreature) new GoblinBrute());
            ModCreatures.addCreature((ModCreature) new TrollWitchDoctor());
            ModCreatures.addCreature((ModCreature) new Rattlesnake());

        } catch (Throwable throwable) {
            LOGGER.severe("Error In Initializing");
        }
        LOGGER.info("All init completed");
    }


}

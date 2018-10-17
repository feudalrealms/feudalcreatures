package org.feudalrealms.wurmunlimited.feudalcreatures;

import org.gotti.wurmunlimited.modloader.classhooks.HookException;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreatures;
import org.feudalrealms.wurmunlimited.feudalcreatures.creatures.*;


import java.util.logging.Logger;

public class FeudalCreatures implements WurmServerMod, PreInitable, Initable {

    private static final Logger LOGGER = Logger.getLogger(FeudalCreatures.class.getName());

    @Override
    public void preInit() {
        LOGGER.info("Pre-init called");
        try {

        } catch (IllegalArgumentException | ClassCastException e) {
            throw new HookException(e);
        }
    }

    @Override
    public void init() {
        LOGGER.info("Init called");
        try {
            ModCreatures.init();
            FeudalCreatures.addNewCreatures();
        } catch (Throwable throwable) {
            LOGGER.severe("Error In Initializing");
        }
        LOGGER.info("All init completed");
    }

    private static void addNewCreatures () {
        ModCreatures.addCreature(new GoblinBrute());
        ModCreatures.addCreature(new TrollWitchDoctor());
        ModCreatures.addCreature(new Rattlesnake());
    }
}

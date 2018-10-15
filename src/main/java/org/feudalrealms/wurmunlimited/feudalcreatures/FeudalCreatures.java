package org.feudalrealms.wurmunlimited.feudalcreatures;

import org.feudalrealms.wurmunlimited.feudalcreatures.creatures.GoblinBrute;
import org.feudalrealms.wurmunlimited.feudalcreatures.creatures.TrollWitchDoctor;
import org.gotti.wurmunlimited.modloader.interfaces.PreInitable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreatures;

import java.util.logging.Logger;

public class FeudalCreatures implements WurmServerMod, PreInitable {

    private static final Logger LOGGER = Logger.getLogger(FeudalCreatures.class.getName());

    @Override
    public void preInit() {
        LOGGER.info("pre-init");

        ModCreatures.init();
        ModCreatures.addCreature(new GoblinBrute());
        ModCreatures.addCreature(new TrollWitchDoctor());
    }
}

package org.feudalrealms.wurmunlimited.feudalcreatures;

import org.gotti.wurmunlimited.modloader.interfaces.*;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreatures;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import org.feudalrealms.wurmunlimited.feudalcreatures.creatures.*;

import java.util.Properties;
import java.util.logging.Logger;


public class Initiator implements WurmServerMod, Initable, Configurable {

    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());


    @Override
    public void configure(Properties properties) {
        //facades
        Constants.SpawnTrollWitchDooctor = Boolean.parseBoolean(properties.getProperty("SpawnTrollWitchDooctor", Boolean.toString(Constants.SpawnTrollWitchDooctor)));
        Constants.SpawnGoblinBrute = Boolean.parseBoolean(properties.getProperty("SpawnGoblinBrute", Boolean.toString(Constants.SpawnGoblinBrute)));
        Constants.SpawnRattlesnake = Boolean.parseBoolean(properties.getProperty("SpawnRattlesnake", Boolean.toString(Constants.SpawnRattlesnake)));
        Constants.SpawnPixie = Boolean.parseBoolean(properties.getProperty("SpawnPixie", Boolean.toString(Constants.SpawnPixie)));
    }


    @Override
    public void init() {
        LOGGER.info("Init called");
        try {
            ModCreatures.init();
            ModCreatures.addCreature((ModCreature) new TrollWitchDoctor());
            ModCreatures.addCreature((ModCreature) new GoblinBrute());
            ModCreatures.addCreature((ModCreature) new Rattlesnake());
           // ModCreatures.addCreature((ModCreature) new Pixie());

        } catch (Throwable throwable) {
            LOGGER.severe("Error In Initializing");
        }
        LOGGER.info("All init completed");
    }


}

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

        Constants.SpawnTrollWitchDoctor = Boolean.parseBoolean(properties.getProperty("SpawnTrollWitchDoctor", Boolean.toString(Constants.SpawnTrollWitchDoctor)));
        Constants.SpawnGoblinBrute = Boolean.parseBoolean(properties.getProperty("SpawnGoblinBrute", Boolean.toString(Constants.SpawnGoblinBrute)));
        Constants.SpawnGoblinPirate = Boolean.parseBoolean(properties.getProperty("SpawnGoblinPirate", Boolean.toString(Constants.SpawnGoblinPirate)));
        Constants.SpawnRattlesnake = Boolean.parseBoolean(properties.getProperty("SpawnRattlesnake", Boolean.toString(Constants.SpawnRattlesnake)));
        Constants.SpawnBrigand = Boolean.parseBoolean(properties.getProperty("SpawnBrigand", Boolean.toString(Constants.SpawnBrigand)));
        Constants.SpawnSnapper = Boolean.parseBoolean(properties.getProperty("SpawnSnapper", Boolean.toString(Constants.SpawnSnapper)));
        Constants.SpawnMegaCrab = Boolean.parseBoolean(properties.getProperty("SpawnMegaCrab", Boolean.toString(Constants.SpawnMegaCrab)));
        Constants.SpawnAngryGoose = Boolean.parseBoolean(properties.getProperty("SpawnAngryGoose", Boolean.toString(Constants.SpawnAngryGoose)));
        Constants.SpawnKraken = Boolean.parseBoolean(properties.getProperty("SpawnKraken", Boolean.toString(Constants.SpawnKraken)));
        Constants.SpawnSlime = Boolean.parseBoolean(properties.getProperty("SpawnSlime", Boolean.toString(Constants.SpawnSlime)));


    }


    @Override
    public void init() {
        LOGGER.info("Init called");
        try {
            ModCreatures.init();
            ModCreatures.addCreature((ModCreature) new TrollWitchDoctor());
            ModCreatures.addCreature((ModCreature) new GoblinBrute());
            ModCreatures.addCreature((ModCreature) new GoblinPirate());
            ModCreatures.addCreature((ModCreature) new Rattlesnake());
            ModCreatures.addCreature((ModCreature) new Brigand());
            ModCreatures.addCreature((ModCreature) new Snapper());
            ModCreatures.addCreature((ModCreature) new MegaCrab());
            ModCreatures.addCreature((ModCreature) new AngryGoose());
            ModCreatures.addCreature((ModCreature) new Kraken());
            ModCreatures.addCreature((ModCreature) new Slime());
        } catch (Throwable throwable) {
            LOGGER.severe("Error In Initializing");
        }
        LOGGER.info("All init completed");
    }


}

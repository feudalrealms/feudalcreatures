package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import java.util.logging.Logger;
import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import com.wurmonline.shared.constants.SoundNames;
import org.feudalrealms.wurmunlimited.feudalcreatures.Constants;
import org.feudalrealms.wurmunlimited.feudalcreatures.Initiator;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import static com.wurmonline.server.items.ItemList.*;

public class Rattlesnake implements ModCreature, CreatureTypes,ItemMaterials, SoundNames {
    private int templateId;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        int[] types = { C_MOD_LURKING,
                C_MOD_RAGING,
                C_TYPE_SWIMMING,
                C_MOD_GREENISH,
                C_TYPE_MOVE_GLOBAL,
                C_TYPE_ANIMAL,
                C_TYPE_AGG_HUMAN,
                C_TYPE_HUNTING,
                C_TYPE_CARNIVORE,
                C_TYPE_NON_NEWBIE,
                C_TYPE_MOVE_LOCAL,
                C_TYPE_DETECTINVIS,
                C_TYPE_STEALTH};

        final int[] itemsButchered = {leather,eye,tooth};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.rattlesnake","Rattlesnake",
                "Hiisssssssssssssssssssssssssssss.....",
                "model.creature.rattlesnake",
                types, (byte)9, (short)5, (byte)0, (short)20, (short)20, (short)350,
//              TYPE, BodyType, Vision, Sex, CentimetersHigh, CentimetersLong, CentimetersWide
                DEATH_SNAKE_SND, DEATH_SNAKE_SND, HIT_SNAKE_SND, HIT_SNAKE_SND,
                0.1F, 0.0F, 0.0F, 8.0F, 0.0F, 8.0F,
                0.8F, 50, itemsButchered, 10, 15, MATERIAL_MEAT_SNAKE
        );

        builder.skill(102, 30.0F);
        builder.skill(104, 25.0F);
        builder.skill(103, 10.0F);
        builder.skill(100, 2.0F);
        builder.skill(101, 4.0F);
        builder.skill(105, 30.0F);
        builder.skill(106, 3.0F);
        builder.skill(10052, 65.0F);

        this.templateId = builder.getTemplateId();

//        CreatureTemplate temp = CreatureTemplateFactory.getInstance().createCreatureTemplate(id, name, plural, longDesc, "model.creature.snake.anaconda", types, (byte)9, skills, (short)10, (byte)0, (short)20, (short)20, (short)350, "sound.death.snake", "sound.death.snake", "sound.combat.hit.snake","sound.combat.hit.snake", 0.3F, 0.0F, 0.0F, 6.0F, 0.0F, 10.0F, 0.8F, 50, new int[]{303, 310}, 10, 24, (byte)86);
        builder.maxGroupAttackSize(4);

        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_LEATHER);
        builder.baseCombatRating(13.0f);
        builder.combatDamageType(Wound.TYPE_POISON);
        builder.handDamString("whip");
        builder.setCombatMoves(new int[]{CombatMove.SWEEP});
        builder.maxPercentOfCreatures(0.02f);
        builder.itemsButchered(itemsButchered);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_SNAKE);
        LOGGER.info("Initiator: Rattlesnake");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnRattlesnake) {
            new EncounterBuilder(Tiles.Tile.TILE_TREE.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_SAND.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_CAVE.id, (byte) -1)
                    .addCreatures(templateId, 1)
                    .build(1);
        }


    }

}

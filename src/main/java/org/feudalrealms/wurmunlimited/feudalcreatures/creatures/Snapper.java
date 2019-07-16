package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import com.wurmonline.shared.constants.SoundNames;
import com.wurmonline.server.bodys.BodyTemplate;
import org.feudalrealms.wurmunlimited.feudalcreatures.Constants;
import org.feudalrealms.wurmunlimited.feudalcreatures.Initiator;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

import java.util.logging.Logger;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class Snapper implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {
    private int templateId;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = { C_MOD_LURKING,
                C_MOD_RAGING,
                C_TYPE_SWIMMING,
                C_TYPE_MOVE_GLOBAL,
                C_TYPE_ANIMAL,
                C_TYPE_AGG_HUMAN,
                C_TYPE_HUNTING,
                C_TYPE_CARNIVORE,
                C_TYPE_NON_NEWBIE,
                C_TYPE_MOVE_LOCAL,
                C_TYPE_DETECTINVIS,
                C_MOD_SIZESMALL,
                C_TYPE_STEALTH};

        final int[] itemsButchered = {eye,heart,turtleShell};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.snapper","Snapper",
                "Powerful jaw capable of bending steel.. ",
                "model.creature.snapper",
                types, (byte)3,(short)5, (byte)0, (short)260, (short)60, (short)40,
//              TYPE, BodyType, Vision, Sex, CentimetersHigh, CentimetersLong, CentimetersWide
                DEATH_CROC_SND, DEATH_CROC_SND, HIT_CROC_SND, HIT_CROC_SND,
                0.5F, 10.0F, 10.0F, 18.0F, 0.0F, 0.0F, 0.5F, 1500, itemsButchered, 10, 94, MATERIAL_MEAT_GAME
        );


        this.templateId = builder.getTemplateId();
        builder.skill(BODY_STRENGTH, 25.0F);
        builder.skill(BODY_CONTROL, 25.0F);
        builder.skill(BODY_STAMINA, 25.0F);
        builder.skill(MIND_LOGICAL, 22.0F);
        builder.skill(MIND_SPEED, 25.0F);
        builder.skill(SOUL_STRENGTH, 26.0F);
        builder.skill(SOUL_DEPTH, 37.0F);
        builder.skill(WEAPONLESS_FIGHTING, 35.0F);
        builder.alignment(-40.0F);
        builder.combatDamageType(Wound.TYPE_BITE);
        builder.maxPercentOfCreatures(0.02F);
        builder.handDamString("claw");
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_PLATE);
        builder.baseCombatRating(30.0f);
        builder.maxGroupAttackSize(6);
        builder.hasHands(false);
        builder.maxPercentOfCreatures(0.01f);
        builder.setCombatMoves(new int[]{CombatMove.FIGHTDEFEND});

        LOGGER.info("Initiator: Snapper");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnSnapper) {
            new EncounterBuilder(Tiles.Tile.TILE_SAND.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_CLAY.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_MARSH.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_KELP.id)
                    .addCreatures(templateId, 1)
                    .build(1);
        }

    }

//    private void getBaseTemplate() {
//        try {
//            CreatureTemplate baseTemplate = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.GOBLIN_CID);
//            this.baseSkillTree = baseTemplate.getSkills().getSkillTree();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

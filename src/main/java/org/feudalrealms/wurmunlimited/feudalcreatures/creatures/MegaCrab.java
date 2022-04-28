package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.server.creatures.AttackAction;
import com.wurmonline.server.creatures.AttackIdentifier;
import com.wurmonline.server.creatures.AttackValues;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import com.wurmonline.shared.constants.SoundNames;
import org.feudalrealms.wurmunlimited.feudalcreatures.Constants;
import org.feudalrealms.wurmunlimited.feudalcreatures.Initiator;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

import java.util.logging.Logger;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class MegaCrab implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {
    private int templateId;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = {
                C_MOD_GREENISH,
                C_MOD_CHAMPION,
                C_TYPE_SWIMMING,
                C_TYPE_MOVE_GLOBAL,
                C_TYPE_AGG_HUMAN,
                C_TYPE_HUNTING,
                C_TYPE_CARNIVORE,
                C_TYPE_NON_NEWBIE,
                C_TYPE_DETECTINVIS,
                C_TYPE_STEALTH,
                C_TYPE_MONSTER,
                C_TYPE_REGENERATING};

        final int[] itemsButchered = {crabMeat,crabMeat,crabMeat,crabMeat, eye,eye};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.crab","Mega Crab",
                "Powerful jaw capable of bending steel.. ",
                "model.creature.quadraped.crab",
//                types, (byte)3,(short)10, (byte)0, (short)520, (short)120, (short)80,
                types, (byte)3,(short)10, (byte)0, (short)1040, (short)240, (short)160,
//              TYPE, BodyType, Vision, Sex, CentimetersHigh, CentimetersLong, CentimetersWide
                DEATH_INSECT_SND, DEATH_INSECT_SND, HIT_INSECT_SND, HIT_INSECT_SND,
                0.5F, 12.0F, 4.0F, 10.0F, 0.0F, 0.0F, .8F, 1500, itemsButchered, 10, 94, MATERIAL_MEAT_SEAFOOD
        );


        this.templateId = builder.getTemplateId();
        builder.skill(BODY_STRENGTH, 50.0F);
        builder.skill(BODY_CONTROL, 55.0F);
        builder.skill(BODY_STAMINA, 55.0F);
        builder.skill(MIND_LOGICAL, 27.0F);
        builder.skill(MIND_SPEED, 35.0F);
        builder.skill(SOUL_STRENGTH, 46.0F);
        builder.skill(SOUL_DEPTH, 37.0F);
        builder.skill(WEAPONLESS_FIGHTING, 65.0F);
        builder.skill(FIGHT_DEFENSIVESTYLE, 65.0F);
        builder.alignment(-40.0F);
        builder.combatDamageType(Wound.TYPE_CRUSH);

        builder.handDamString("claw");
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_PLATE);
        builder.baseCombatRating(50.0f);
        builder.maxGroupAttackSize(8);
        builder.hasHands(false);
        builder.maxPercentOfCreatures(0.01f);
        builder.addPrimaryAttack(new AttackAction("maul", AttackIdentifier.MAUL, new AttackValues(12.0F, 0.04F, 4.0F, 3, 1, (byte)0, false, 3, 1.4F)));
        builder.addSecondaryAttack(new AttackAction("kick", AttackIdentifier.KICK, new AttackValues(4.0F, 0.04F, 5.0F, 3, 1, (byte)0, false, 3, 2.1F)));
        builder.addSecondaryAttack(new AttackAction("bite", AttackIdentifier.BITE, new AttackValues(10.0F, 0.08F, 7.0F, 3, 1, (byte)3, false, 3, 2.0F)));

        builder.setCombatMoves(new int[]{CombatMove.FIGHTDEFEND, CombatMove.BASH});
        builder.sizeModifier(200,200,200);
        LOGGER.info("Initiator: Mega Crab");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnMegaCrab) {
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
            new EncounterBuilder(Tiles.Tile.TILE_REED.id)
                    .addCreatures(templateId, 1)
                    .build(1);
        }

    }
}

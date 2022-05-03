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

public class AngryGoose implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {
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
                C_TYPE_DETECTINVIS,
                C_MOD_SIZESMALL,
                C_TYPE_STEALTH};

        final int[] itemsButchered = {meat, tallow, eye};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.angrygoose","Angry Goose",
                "Don't look at it right in the eye.. ",
                "model.creature.angrygoose",
                types, (byte)7,(short)6, (byte)0, (short)30, (short)14, (short)50,
//              TYPE, BodyType, Vision, Sex, CentimetersHigh, CentimetersLong, CentimetersWide
                DEATH_PHEASANT_SND, DEATH_PHEASANT_SND, HIT_PHEASANT_SND, HIT_PHEASANT_SND,
                1.0F, 1.0F, 0.0F, 1.5F, 0.0F, 0.0F, 1.0F, 1500, itemsButchered, 20, 99, MATERIAL_MEAT_FOWL
        );



        this.templateId = builder.getTemplateId();
        builder.skill(BODY_STRENGTH, 25.0F);
        builder.skill(BODY_CONTROL, 25.0F);
        builder.skill(BODY_STAMINA, 25.0F);
        builder.skill(MIND_LOGICAL, 22.0F);
        builder.skill(MIND_SPEED, 25.0F);
        builder.skill(SOUL_STRENGTH, 26.0F);
        builder.skill(SOUL_DEPTH, 37.0F);
        builder.skill(WEAPONLESS_FIGHTING, 25.0F);
        builder.skill(FIGHT_AGGRESSIVESTYLE, 25.0F);
        builder.alignment(-40.0F);
        builder.combatDamageType(Wound.TYPE_BITE);
        builder.maxPercentOfCreatures(0.02F);
        builder.handDamString("foot slap");
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_LEATHER);
        builder.baseCombatRating(10.0f);
        builder.maxGroupAttackSize(4);
        builder.hasHands(false);
        builder.maxPercentOfCreatures(0.01f);
        builder.setCombatMoves(new int[]{CombatMove.FIGHTAGG});
        builder.addPrimaryAttack(new AttackAction("flap", AttackIdentifier.MAUL, new AttackValues(12.0F, 0.04F, 4.0F, 3, 1, (byte)0, false, 3, 1.4F)));
        builder.addSecondaryAttack(new AttackAction("kick", AttackIdentifier.KICK, new AttackValues(4.0F, 0.04F, 5.0F, 3, 1, (byte)0, false, 3, 2.1F)));
        builder.addSecondaryAttack(new AttackAction("bite", AttackIdentifier.BITE, new AttackValues(10.0F, 0.08F, 7.0F, 3, 1, (byte)3, false, 3, 2.0F)));

        LOGGER.info("Initiator: Angry Goose");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnAngryGoose) {
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
                    .addCreatures(templateId, 1)
                    .build(1);
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
}

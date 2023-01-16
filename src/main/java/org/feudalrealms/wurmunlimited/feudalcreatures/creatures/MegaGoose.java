package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.BodyTemplate;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.server.creatures.AttackAction;
import com.wurmonline.server.creatures.AttackIdentifier;
import com.wurmonline.server.creatures.AttackValues;
import com.wurmonline.server.skills.SkillList;
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

public class MegaGoose implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {
    private int templateId;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = {
                C_MOD_LURKING,
                C_MOD_GREENISH,
                C_TYPE_HUNTING,
                C_TYPE_MONSTER,
                C_TYPE_CARNIVORE,
                C_TYPE_CLIMBER,
                C_TYPE_OPENDOORS,
                C_TYPE_NON_NEWBIE,
                C_MOD_CHAMPION,
                C_TYPE_MOVE_LOCAL,
                C_TYPE_AGG_HUMAN,
                C_MOD_RAGING,
                C_TYPE_SWIMMING,
                C_TYPE_DETECTINVIS,
                C_TYPE_STEALTH,
                C_TYPE_UNIQUE,
                C_TYPE_NO_REBIRTH,
                C_TYPE_DRAGON
        };


        final int[] itemsButchered = {meat, meat, meat, meat, meat, meat, meat, meat, tallow, eye, eye};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.megagoose","Mega Goose",
                "A dragon mated with a goose and made this... ",
                "model.creature.megagoose",
                types, (byte)7,(short)6, (byte)0, (short)300, (short)140, (short)500,
//              TYPE, BodyType, Vision, Sex, CentimetersHigh, CentimetersLong, CentimetersWide
                DEATH_PHEASANT_SND, DEATH_PHEASANT_SND, HIT_PHEASANT_SND, HIT_PHEASANT_SND,
                0.1F, 5.0F, 10.0F, 10.0F, 5.0F, 0.0F, 1.0F, 1500, itemsButchered, 10, 99, MATERIAL_MEAT_FOWL
        );



        this.templateId = builder.getTemplateId();
        builder.skill(BODY_STRENGTH, 35.0F);
        builder.skill(BODY_CONTROL, 35.0F);
        builder.skill(BODY_STAMINA, 35.0F);
        builder.skill(MIND_LOGICAL, 12.0F);
        builder.skill(MIND_SPEED, 15.0F);
        builder.skill(SOUL_STRENGTH, 26.0F);
        builder.skill(SOUL_DEPTH, 7.0F);
        builder.skill(WEAPONLESS_FIGHTING, 75.0F);
        builder.skill(FIGHT_AGGRESSIVESTYLE, 55.0F);
        builder.skill(GROUP_FIGHTING, 40.0f);
        builder.alignment(-90.0F);
        builder.combatDamageType(Wound.TYPE_BITE);
        builder.maxPercentOfCreatures(0.01F);
        builder.maxPopulationOfCreatures(3);
        builder.sizeModifier(800,800,800);

        builder.handDamString("foot slap");
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_SCALE_DRAGON);
        builder.baseCombatRating(85.0f);
        builder.maxGroupAttackSize(20);
        builder.hasHands(false);
        builder.glowing(true);
        builder.usesNewAttacks(true);
        builder.setCombatMoves(new int[]{CombatMove.STUN,CombatMove.FIGHTAGG,CombatMove.STOMP});
        builder.addPrimaryAttack(new AttackAction("flap", AttackIdentifier.MAUL, new AttackValues(12.0F, 0.06F, 4.0F, 3, 1, (byte)0, false, 3, 1.4F)));
        builder.addSecondaryAttack(new AttackAction("kick", AttackIdentifier.KICK, new AttackValues(8.0F, 0.8F, 5.0F, 3, 1, (byte)0, false, 3, 2.1F)));
        builder.addSecondaryAttack(new AttackAction("bite", AttackIdentifier.BITE, new AttackValues(10.0F, 0.1F, 7.0F, 3, 1, (byte)3, false, 3, 2.0F)));

        LOGGER.info("Initiator: Mega Goose");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnMegaGoose) {
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

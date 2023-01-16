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

public class redGnome implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {
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

        final int[] itemsButchered = {};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.redgnome","Red Gnome",
                "I'm down here ya meenie",
                "model.creature.redgnome",
                types, (byte)0,(short)5, (byte)0, (short)260, (short)60, (short)40,
//              TYPE, BodyType, Vision, Sex, CentimetersHigh, CentimetersLong, CentimetersWide
                "sound.death.goblin", "sound.death.goblin", "sound.combat.hit.goblin", "sound.combat.hit.goblin",
                0.2F, 15.0F, 15.0F, 10.0F, 0.0F, 0.0F, 0.7F, 1500, itemsButchered, 10, 94, (byte)81
        );



        this.templateId = builder.getTemplateId();
        builder.skill(BODY_STRENGTH, 35.0F);
        builder.skill(BODY_CONTROL, 35.0F);
        builder.skill(BODY_STAMINA, 35.0F);
        builder.skill(MIND_LOGICAL, 22.0F);
        builder.skill(MIND_SPEED, 25.0F);
        builder.skill(SOUL_STRENGTH, 36.0F);
        builder.skill(SOUL_DEPTH, 37.0F);
        builder.skill(WEAPONLESS_FIGHTING, 45.0F);
        builder.skill(FIGHT_DEFENSIVESTYLE, 55.0F);
        builder.skill(GROUP_FIGHTING, 40.0f);
        builder.leaderTemplateId(26);
        builder.alignment(-100.0F);
        builder.baseCombatRating(40.0f);
        builder.combatDamageType((byte) 2);
        builder.maxGroupAttackSize(4);
        builder.hasHands(true);
        builder.maxPercentOfCreatures(0.01F);
        builder.handDamString("claw");
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_STUDDED);
        builder.baseCombatRating(50.0f);
        builder.combatDamageType((byte) 1);
        builder.maxGroupAttackSize(6);
        builder.hasHands(true);
        builder.maxPercentOfCreatures(0.015f);
        builder.setCombatMoves(new int[]{CombatMove.STUN, CombatMove.FIGHTDEFEND, CombatMove.SWEEP});

        LOGGER.info("Initiator: Red Gnome");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnRedGnome) {
            new EncounterBuilder(Tiles.Tile.TILE_COBBLESTONE_ROUND.id)
                    .addCreatures(templateId, 1)
                    .build(1);
        }
    }
}

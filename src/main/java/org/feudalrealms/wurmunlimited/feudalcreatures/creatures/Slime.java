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
import com.wurmonline.server.bodys.BodyTemplate;
import org.feudalrealms.wurmunlimited.feudalcreatures.Constants;
import org.feudalrealms.wurmunlimited.feudalcreatures.Initiator;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

import java.util.logging.Logger;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;


public class Slime implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {
    private int templateId;

    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = { C_TYPE_MOVE_GLOBAL,C_TYPE_MONSTER,C_TYPE_AGG_HUMAN,C_TYPE_SWIMMING,C_TYPE_CARNIVORE,C_TYPE_NON_NEWBIE };
        final int[] itemsButchered = {eye,skull,tar};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.slime","Slime",
                "Squish squish",
                "model.creature.slime",
                types,BodyTemplate.TYPE_SNAKE, (short)6, (byte)0, (short)200, (short)100, (short)100,
                DEATH_OOZE_SND, DEATH_OOZE_SND, HIT_OOZE_SND, HIT_OOZE_SND,
                0.6F, 8.0F, 0.0F, 8.0F, 0.0F, 0.0F, 1.2F, 1700, itemsButchered, 10, 99, MATERIAL_MEAT_INSECT
        );

        builder.description("Squish squish");
        this.templateId = builder.getTemplateId();
        builder.skill(BODY_STRENGTH, 25.0F);
        builder.skill(BODY_CONTROL, 25.0F);
        builder.skill(BODY_STAMINA, 35.0F);
        builder.skill(MIND_LOGICAL, 10.0F);
        builder.skill(MIND_SPEED, 15.0F);
        builder.skill(SOUL_STRENGTH, 20.0F);
        builder.skill(SOUL_DEPTH, 12.0F);
        builder.skill(WEAPONLESS_FIGHTING, 50.0f);
        builder.skill(FIGHT_AGGRESSIVESTYLE, 25.0F);
        builder.alignment(-60.0F);
        builder.hasHands(false);
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_STUDDED);
        builder.setCombatMoves(new int[]{CombatMove.FIGHTAGG});
        builder.combatDamageType(Wound.TYPE_ACID);
        builder.maxGroupAttackSize(4);
        builder.handDamString("bite");
        builder.baseCombatRating(14.0f);
        builder.addPrimaryAttack(new AttackAction("flap", AttackIdentifier.MAUL, new AttackValues(12.0F, 0.04F, 4.0F, 3, 1, (byte)0, false, 3, 1.4F)));
        builder.addSecondaryAttack(new AttackAction("spit", AttackIdentifier.BITE, new AttackValues(10.0F, 0.08F, 7.0F, 3, 1, (byte)3, false, 3, 2.0F)));
        builder.maxPercentOfCreatures(0.02f);

        LOGGER.info("Initiator: Slime");
        return builder;
    }

    public void addEncounters() {
        if (templateId == 0) return;
        if (Constants.SpawnSlime) {
            new EncounterBuilder(Tiles.Tile.TILE_MARSH.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_TAR.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_MOSS.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_CAVE.id, (byte) -1)
                    .addCreatures(templateId, 1)
                    .build(1);
        }
    }

}

package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import java.util.logging.Logger;
import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.server.creatures.AttackAction;
import com.wurmonline.server.creatures.AttackIdentifier;
import com.wurmonline.server.creatures.AttackValues;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import org.feudalrealms.wurmunlimited.feudalcreatures.Constants;
import org.feudalrealms.wurmunlimited.feudalcreatures.Initiator;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

import static com.wurmonline.server.skills.SkillList.*;
import static com.wurmonline.server.items.ItemList.*;


public class TrollWitchDoctor implements ModCreature, CreatureTypes, ItemMaterials {
    private int templateId;

    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = { C_TYPE_MOVE_GLOBAL,C_TYPE_MONSTER,C_TYPE_AGG_HUMAN,C_TYPE_SWIMMING,C_TYPE_HUNTING,C_TYPE_CARNIVORE,C_TYPE_NON_NEWBIE,C_TYPE_STEALTH };
        final int[] itemsButchered = {meat,tallow,eye,bladder,heart};


        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.trollwitchdoctor","Troll Witch Doctor",
                "Dis wont hurt a bit...",
                "model.creature.trollwitchdoctor",
                types,(byte)0, (short)5, (byte)0, (short)230, (short)50, (short)50,
                "sound.death.troll", "sound.death.troll", "sound.combat.hit.troll", "sound.combat.hit.troll", 0.4F, 8.0F, 4.0F, 12.0F, 0.0F, 0.0F, 1.2F, 1700, itemsButchered, 10, 94, (byte)81
        );

        builder.description("Dis wont hurt a bit...");

        this.templateId = builder.getTemplateId();

        builder.skill(BODY_STRENGTH, 30.0F);
        builder.skill(BODY_CONTROL, 45.0F);
        builder.skill(BODY_STAMINA, 35.0F);
        builder.skill(MIND_LOGICAL, 40.0F);
        builder.skill(MIND_SPEED, 40.0F);
        builder.skill(SOUL_STRENGTH, 20.0F);
        builder.skill(SOUL_DEPTH, 20.0F);
        builder.skill(WEAPONLESS_FIGHTING, 50.0f);

        builder.leaderTemplateId(27);
        builder.alignment(-99.0F);
        builder.hasHands(true);
        builder.usesNewAttacks(true);
        builder.boundsValues(-0.5F, -0.5F, 0.5F, 0.5F);
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_STUDDED);
        builder.baseCombatRating(30.0f);
        builder.bonusCombatRating(5);
        builder.combatDamageType(Wound.TYPE_POISON);
        builder.handDamString("claw");
        builder.kickDamString("kick");
        builder.maxGroupAttackSize(8);
        builder.denMaterial((byte)15);
        builder.denName("troll mound");
        builder.setCombatMoves(new int[]{CombatMove.STUN, CombatMove.FIGHTAGG});
        builder.maxPercentOfCreatures(0.02f);
        builder.addPrimaryAttack(new AttackAction("maul", AttackIdentifier.MAUL, new AttackValues(7.0F, 0.04F, 6.0F, 3, 2, (byte)0, true, 3, 1.4F)));
        builder.addPrimaryAttack(new AttackAction("strike", AttackIdentifier.STRIKE, new AttackValues(7.0F, 0.04F, 4.0F, 3, 1, (byte)0, false, 3, 1.4F)));
        builder.addSecondaryAttack(new AttackAction("kick", AttackIdentifier.KICK, new AttackValues(4.0F, 0.04F, 5.0F, 3, 1, (byte)0, false, 3, 2.1F)));
        builder.addSecondaryAttack(new AttackAction("bite", AttackIdentifier.BITE, new AttackValues(10.0F, 0.08F, 7.0F, 3, 1, (byte)3, false, 3, 2.0F)));

        LOGGER.info("Initiator: Troll Witchdoctor");
        return builder;
    }

    public void addEncounters() {
        if (templateId == 0) return;
        if (Constants.SpawnTrollWitchDoctor) {
            new EncounterBuilder(Tiles.Tile.TILE_TREE.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_CAVE.id, (byte) -1)
                    .addCreatures(templateId, 1)
                    .build(1);
        }
    }

}

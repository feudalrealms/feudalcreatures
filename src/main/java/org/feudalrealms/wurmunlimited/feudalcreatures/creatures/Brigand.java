package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import com.wurmonline.mesh.Tiles;
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


public class Brigand implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {


    private int templateId;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = new int[]{
                C_MOD_LURKING,
                C_TYPE_HUNTING,
                C_TYPE_MONSTER,
                C_TYPE_CLIMBER,
                C_TYPE_OPENDOORS,
                C_TYPE_NON_NEWBIE,
                C_TYPE_MOVE_LOCAL,
                C_TYPE_STEALTH,
                C_TYPE_AGG_HUMAN
        };

        final int[] itemsButchered = {meat, heart, skull};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.brigand", "Brigand",
                "This rogue believes he can take your things, and your life!",
                "model.creature.humanoid.human.wagoner",
                types, BodyTemplate.TYPE_HUMAN, (short)5, (byte)0, (short)180, (short)20, (short)35,
//              TYPE, BodyType, Vision, Sex, CentimetersHigh, CentimetersLong, CentimetersWide
                "sound.death.male", "sound.death.female", "sound.combat.hit.male", "sound.combat.hit.female",
                0.2F, 20.0F, 18.0F, 0.0F, 0.0F, 0.0F, 0.7F, 1500, itemsButchered, 10, 94, MATERIAL_MEAT_HUMANOID
        );


        this.templateId = builder.getTemplateId();
        builder.skill(BODY_STRENGTH, 45.0F);
        builder.skill(BODY_CONTROL, 45.0F);
        builder.skill(BODY_STAMINA, 45.0F);
        builder.skill(MIND_LOGICAL, 22.0F);
        builder.skill(MIND_SPEED, 25.0F);
        builder.skill(SOUL_STRENGTH, 46.0F);
        builder.skill(SOUL_DEPTH, 37.0F);
        builder.skill(WEAPONLESS_FIGHTING, 55.0F);
        builder.alignment(-100.0F);
        builder.combatDamageType((byte) 1);
        builder.maxGroupAttackSize(4);
        builder.hasHands(true);
        builder.maxPercentOfCreatures(0.04F);
        builder.handDamString("whip");
        builder.kickDamString("kick");
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_PLATE);
        builder.baseCombatRating(50.0f);
        builder.setCombatMoves(new int[]{CombatMove.FIGHTDEFEND, CombatMove.STUN, CombatMove.BASH});
        builder.addPrimaryAttack(new AttackAction("strike", AttackIdentifier.STRIKE, new AttackValues(15.0F, 0.04F, 4.0F, 3, 1, (byte)0, false, 3, 1.1F)));
        builder.addSecondaryAttack(new AttackAction("kick", AttackIdentifier.KICK, new AttackValues(15.0F, 0.04F, 5.0F, 3, 1, (byte)0, false, 3, 2.1F)));

        LOGGER.info("Initiator: Brigand");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnBrigand) {
            new EncounterBuilder(Tiles.Tile.TILE_TREE.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
                    .addCreatures(templateId, 1)
                    .build(1);
        }


    }



}

package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.combat.ArmourTemplate;
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


public class Pixie implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {


    private int templateId;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = new int[]{
                C_MOD_LURKING,
                C_MOD_GREENISH,
                C_TYPE_HUNTING,
                C_TYPE_MONSTER,
                C_TYPE_CLIMBER,
                C_TYPE_OPENDOORS,
                C_TYPE_NON_NEWBIE,
                C_TYPE_MOVE_LOCAL,
                C_TYPE_STEALTH,
                C_TYPE_AGG_HUMAN
        };


        final int[] itemsButchered = {wemp, eye, tooth, bladder, heart, flowerLavender, brandy};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.pixie", "Pixie",
                "Hee hee..  I'll steal your things!",
                "model.creature.humanoid.human.wagoner",
                types, BodyTemplate.TYPE_HUMAN, (short) 2, (byte) 0, (short) 10, (short) 10, (short) 10,
//              TYPE, BodyType, Vision, Sex, CentimetersHigh, CentimetersLong, CentimetersWide
                "sound.death.female.child", "sound.death.female.child", "sound.combat.hit.female.child", "sound.combat.hit.female.child",
                0.11F, 15.0f, 10.0F, 0.0F, 0.0F, 0.0F, 1.25F, 2500, itemsButchered, 35, 50, MATERIAL_MEAT_HUMANOID
        );

        this.templateId = builder.getTemplateId();
        builder.skill(BODY_STRENGTH, 35.0F);
        builder.skill(BODY_CONTROL, 35.0F);
        builder.skill(BODY_STAMINA, 35.0F);
        builder.skill(MIND_LOGICAL, 12.0F);
        builder.skill(MIND_SPEED, 15.0F);
        builder.skill(SOUL_STRENGTH, 26.0F);
        builder.skill(SOUL_DEPTH, 7.0F);
        builder.skill(WEAPONLESS_FIGHTING, 50.0F);
        builder.alignment(-40.0F);
        builder.combatDamageType((byte) 2);
        builder.maxGroupAttackSize(4);
        builder.hasHands(true);
        builder.handDamString("slap");
        builder.kickDamString("kick");
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_RING);
        builder.baseCombatRating(30.0f);
        builder.maxPercentOfCreatures(0.02f);
        builder.setCombatMoves(new int[]{1,2,3,11});
        builder.dimension((short) 10,(short)10,(short)10);

        LOGGER.info("Initiator: Pixie");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnPixie) {
            new EncounterBuilder(Tiles.Tile.TILE_TREE.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
                    .addCreatures(templateId, 1)
                    .build(1);
        }


    }



}

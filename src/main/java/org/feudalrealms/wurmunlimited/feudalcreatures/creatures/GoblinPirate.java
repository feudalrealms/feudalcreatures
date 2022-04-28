package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import com.wurmonline.mesh.Tiles;
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

import java.util.logging.Logger;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class GoblinPirate implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {
    private int templateId;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = new int[]{
                C_MOD_LURKING,
                C_MOD_GREENISH,
                C_TYPE_HUNTING,
                C_TYPE_MONSTER,
                C_TYPE_CARNIVORE,
                C_TYPE_CLIMBER,
                C_TYPE_CAVEDWELLER,
                C_TYPE_OPENDOORS,
                C_TYPE_NON_NEWBIE,
                C_MOD_CHAMPION,
                C_TYPE_MOVE_GLOBAL,
                C_TYPE_AGG_HUMAN,
                C_TYPE_STEALTH
        };

        final int[] itemsButchered = {meat, eye,tooth,bladder,heart,skullGoblin};

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.goblinpirate","Goblin Pirate",
                "Growl!  Gnash Gnash! Hugging you!!",
                "model.creature.goblinpirate",
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
        builder.leaderTemplateId(26);
        builder.alignment(-100.0F);
        builder.baseCombatRating(40.0f);
        builder.combatDamageType((byte) 2);
        builder.maxGroupAttackSize(4);
        builder.hasHands(true);
        builder.maxPercentOfCreatures(0.02F);
        builder.handDamString("claw");
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_STUDDED);
        builder.baseCombatRating(50.0f);
        builder.combatDamageType((byte) 1);
        builder.maxGroupAttackSize(6);
        builder.hasHands(true);
        builder.maxPercentOfCreatures(0.02f);
        builder.setCombatMoves(new int[]{CombatMove.STUN, CombatMove.FIGHTDEFEND, CombatMove.SWEEP});

        LOGGER.info("Initiator: GoblinPirate");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnGoblinPirate) {
            new EncounterBuilder(Tiles.Tile.TILE_BUSH_ROSE.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_BUSH_LAVENDER.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_BUSH_THORN.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_BUSH_CAMELLIA.id)
                    .addCreatures(templateId, 1)
                    .build(1);
            new EncounterBuilder(Tiles.Tile.TILE_CAVE.id, (byte) -1)
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
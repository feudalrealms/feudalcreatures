package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.CreatureTemplateIds;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import com.wurmonline.server.skills.Skill;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import com.wurmonline.shared.constants.SoundNames;
import org.feudalrealms.wurmunlimited.feudalcreatures.Initiator;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

import java.util.Map;
import java.util.logging.Logger;

import static com.wurmonline.server.items.ItemList.*;
import static com.wurmonline.server.skills.SkillList.*;

public class Rattlesnake implements ModCreature, CreatureTypes, SoundNames {
    private int templateId;
    private Map<Integer, Skill> baseSkillTree;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        this.getBaseTemplate();

        int[] types = { C_TYPE_MOVE_GLOBAL,
                C_TYPE_ANIMAL,
                C_TYPE_AGG_HUMAN,
                C_TYPE_SWIMMING,
                C_TYPE_HUNTING,
                C_TYPE_CARNIVORE,
                C_TYPE_NON_NEWBIE,
                C_TYPE_DETECTINVIS };
        float ogStrength = this.baseSkillTree.get(BODY_STRENGTH).getNumber();
        float ogStamina = this.baseSkillTree.get(BODY_STRENGTH).getNumber();
        float ogControl = this.baseSkillTree.get(BODY_CONTROL).getNumber();
        final int[] itemsButchered = {leather,eye,tooth};

//        CreatureTemplateBuilder buildero = new CreatureTemplateBuilder(
//                "mod.creature.anaconda.rattlesnake",
//                "Rattlesnake",
//                "Hiisssssssssssssssssssssssssssss.....",
//                "model.creature.anaconda",
//                types,
//                (byte)0, (short)5, (byte)0, (short)360, (short)20, (short)35,
//                DEATH_SNAKE_SND, DEATH_SNAKE_SND, HIT_SNAKE_SND, HIT_SNAKE_SND,
//                1.0F, 0.0F, 0.0F, 2.0F, 0.0F, 0.0F,
//                0.8F, 0, new int[0], 3, 0, (byte)80
//        );

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.rattlesnake") {
            @Override
            public CreatureTemplate build() {
                try {
                    return CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.ANACONDA_CID);
                } catch (NoSuchCreatureTemplateException e) {
                    throw new RuntimeException(e);
                }
            }
        };

//        builder.types(types);
        builder.name("Rattlesnake");
        builder.description("Hiisssssssssssssssssssssssssssss.....");

        this.templateId = builder.getTemplateId();

        builder.skill(BODY_STRENGTH, ogStrength + (ogStrength*0.5f) );
        builder.skill(BODY_CONTROL, ogControl + (ogControl*0.25f));
        builder.skill(BODY_STAMINA, ogStamina + (ogStamina*0.25f));

        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_LEATHER);
        builder.baseCombatRating(20.0f);
        builder.combatDamageType(Wound.TYPE_POISON);
        builder.handDamString("whip");
        builder.hasHands(true);
        builder.setCombatMoves(new int[]{CombatMove.SWEEP});
        builder.maxPercentOfCreatures(0.02f);
        builder.itemsButchered(itemsButchered);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_SNAKE);
        LOGGER.info("Initiator: Rattlesnake");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;

        new EncounterBuilder(Tiles.Tile.TILE_TREE.id)
                .addCreatures(templateId, 2)
                .build(1);
        new EncounterBuilder(Tiles.Tile.TILE_GRASS.id)
                .addCreatures(templateId, 2)
                .build(1);
        new EncounterBuilder(Tiles.Tile.TILE_SAND.id)
                .addCreatures(templateId, 2)
                .build(1);
        new EncounterBuilder(Tiles.Tile.TILE_CAVE.id, (byte) -1)
                .addCreatures(templateId, 2)
                .build(4);
    }

    private void getBaseTemplate() {
        try {
            CreatureTemplate baseTemplate = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.ANACONDA_CID);
            this.baseSkillTree = baseTemplate.getSkills().getSkillTree();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

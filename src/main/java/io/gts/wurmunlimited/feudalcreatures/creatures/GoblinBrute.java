package io.gts.wurmunlimited.feudalcreatures.creatures;

import java.util.Map;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.CreatureTemplateIds;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import com.wurmonline.server.skills.Skill;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.SoundNames;
import static com.wurmonline.server.skills.SkillList.*;

public class GoblinBrute implements ModCreature, CreatureTypes, SoundNames {
    private int templateId;
    private Map<Integer, Skill> baseSkillTree;

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        this.getBaseTemplate();

        int[] types = { C_TYPE_MOVE_GLOBAL,
                C_TYPE_HUMAN,
                C_TYPE_AGG_HUMAN,
                C_TYPE_SWIMMING,
                C_TYPE_HUNTING,
                C_TYPE_DOMINATABLE,
                C_TYPE_CARNIVORE,
                C_TYPE_NON_NEWBIE };
        float ogStrength = this.baseSkillTree.get(BODY_STRENGTH).getNumber();
        float ogStamina = this.baseSkillTree.get(BODY_STRENGTH).getNumber();
        float ogControl = this.baseSkillTree.get(BODY_CONTROL).getNumber();

        CreatureTemplateBuilder buildero = new CreatureTemplateBuilder(
                "mod.creature.goblinbrute",
                "Goblin Brute",
                "He wants you to sit on his lap.",
                "model.creature.humanoid.goblin",
                types,
                (byte)0, (short)5, (byte)0, (short)360, (short)20, (short)35,
                DEATH_MALE_SND, DEATH_FEMALE_SND, HIT_MALE_SND, HIT_FEMALE_SND,
                1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F,
                0.8F, 0, new int[0], 3, 0, (byte)80
        );

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.goblinbrute") {
            @Override
            public CreatureTemplate build() {
                try {
                    return CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.GOBLIN_LEADER_CID);
                } catch (NoSuchCreatureTemplateException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        builder.types(types);
        builder.name("Goblin Brute");
        builder.description("He wants you to sit on his lap.");

        this.templateId = builder.getTemplateId();

        builder.skill(BODY_STRENGTH, ogStrength + (ogStrength*0.5f) );
        builder.skill(BODY_CONTROL, ogControl);
        builder.skill(BODY_STAMINA, ogStamina);

        builder.handDamString("claw");
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_LEATHER);
        builder.baseCombatRating(20.0f);
        builder.combatDamageType((byte) 2);
        builder.maxGroupAttackSize(6);
        builder.hasHands(true);
        builder.maxPercentOfCreatures(0.02f);

        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;

        new EncounterBuilder(Tiles.Tile.TILE_TREE.id)
                .addCreatures(templateId, 2)
                .build(1);

        new EncounterBuilder(Tiles.Tile.TILE_CAVE.id, (byte) -1)
                .addCreatures(templateId, 2)
                .build(4);
    }

    private void getBaseTemplate() {
        try {
            CreatureTemplate baseTemplate = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.GOBLIN_LEADER_CID);
            this.baseSkillTree = baseTemplate.getSkills().getSkillTree();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.combat.CombatMove;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.creatures.CreatureTemplate;
import com.wurmonline.server.creatures.CreatureTemplateFactory;
import com.wurmonline.server.creatures.CreatureTemplateIds;
import com.wurmonline.shared.constants.ItemMaterials;
import com.wurmonline.server.creatures.NoSuchCreatureTemplateException;
import com.wurmonline.server.skills.Skill;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.SoundNames;
import org.feudalrealms.wurmunlimited.feudalcreatures.FeudalCreatures;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

import java.util.Map;
import java.util.logging.Logger;

import static com.wurmonline.server.skills.SkillList.*;
import static com.wurmonline.server.items.ItemList.*;


public class TrollWitchDoctor implements ModCreature, CreatureTypes, SoundNames {
    private int templateId;
    private Map<Integer, Skill> baseSkillTree;
    private static final Logger LOGGER = Logger.getLogger(FeudalCreatures.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {
        this.getBaseTemplate();

        int[] types = { C_TYPE_MOVE_GLOBAL,
                C_TYPE_HUMAN,
                C_TYPE_AGG_HUMAN,
                C_TYPE_SWIMMING,
                C_TYPE_HUNTING,
                C_TYPE_CARNIVORE,
                C_TYPE_NON_NEWBIE };
        final int[] itemsButchered = {leather, cookedMeat, farmersSalve, potion};

        float ogStrength = this.baseSkillTree.get(BODY_STRENGTH).getNumber();
        float ogStamina = this.baseSkillTree.get(BODY_STRENGTH).getNumber();
        float ogControl = this.baseSkillTree.get(BODY_CONTROL).getNumber();


        CreatureTemplateBuilder builder = new CreatureTemplateBuilder("mod.creature.trollwitchdoctor") {
            @Override
            public CreatureTemplate build() {
                try {
                    return CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.TROLL_CID);
                } catch (NoSuchCreatureTemplateException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        builder.types(types);
        builder.name("Troll Witch Doctor");
        builder.description("Dis wont hurt a bit...");

        this.templateId = builder.getTemplateId();

        builder.skill(BODY_STRENGTH, 30.0F);
        builder.skill(BODY_CONTROL, 45.0F);
        builder.skill(BODY_STAMINA, 35.0F);
        builder.skill(MIND_LOGICAL, 40.0F);
        builder.skill(MIND_SPEED, 40.0F);
        builder.skill(SOUL_STRENGTH, 20.0F);
        builder.skill(SOUL_DEPTH, 20.0F);
        builder.skill(SOUL_STRENGTH, 20.0F);
        builder.skill(WEAPONLESS_FIGHTING, 50.0f);

        builder.hasHands(true);
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_STUDDED);
        builder.baseCombatRating(25.0f);

        builder.combatDamageType(Wound.TYPE_POISON);
        builder.handDamString("whip");
        builder.handDamString("strike");
        builder.kickDamString("kick");
        builder.setCombatMoves(new int[]{CombatMove.STUN, CombatMove.DOUBLE_FIST});

// SWEEP = 1 = " makes a circular powerful sweep!"
// EARTHSHAKE = 2 = " shakes the earth!"
// FIREBREATH = 3 =  " breathes fire!"
// DOUBLE_FIST = 4 = " throws down @hisher powerful fists!"
// STOMP = 5 = " stomps!",
// THROW = 6 = " picks up and throws @defender!"
// STUN = 7 = " stuns @defender!"
// BASH = 8 = " bashes @defender!"
// ACIDBREATH = 9 = " breathes acid!"
// HELLHORSEFIRE = 10 = " breathes fire!"
// PHASE = 11 =  " phases!"

        builder.maxPercentOfCreatures(0.04f);
        builder.itemsButchered(itemsButchered);
        builder.meatMaterial(ItemMaterials.MATERIAL_MEAT_HUMANOID);

        LOGGER.info("FeudalCreatures: Troll Witchdoctor");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;

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

    private void getBaseTemplate() {
        try {
            CreatureTemplate baseTemplate = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.TROLL_CID);
            this.baseSkillTree = baseTemplate.getSkills().getSkillTree();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

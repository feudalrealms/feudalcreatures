package org.feudalrealms.wurmunlimited.feudalcreatures.creatures;

import java.util.logging.Logger;
import com.wurmonline.mesh.Tiles;
import com.wurmonline.server.bodys.Wound;
import com.wurmonline.server.combat.ArmourTemplate;
import com.wurmonline.server.combat.CombatMove;
// import com.wurmonline.server.creatures.CreatureTemplate;
// import com.wurmonline.server.creatures.CreatureTemplateFactory;
// import com.wurmonline.server.creatures.CreatureTemplateIds;
import com.wurmonline.shared.constants.CreatureTypes;
import com.wurmonline.shared.constants.ItemMaterials;
import org.feudalrealms.wurmunlimited.feudalcreatures.Initiator;
import org.gotti.wurmunlimited.modsupport.CreatureTemplateBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.EncounterBuilder;
import org.gotti.wurmunlimited.modsupport.creatures.ModCreature;

// import com.wurmonline.server.skills.Skill;
import static com.wurmonline.server.skills.SkillList.*;
import static com.wurmonline.server.items.ItemList.*;


public class TrollWitchDoctor implements ModCreature, CreatureTypes, ItemMaterials {
    private int templateId;
//    private Map<Integer, Skill> baseSkillTree;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = { C_TYPE_MOVE_GLOBAL,C_TYPE_HUMAN,C_TYPE_AGG_HUMAN,C_TYPE_SWIMMING,C_TYPE_HUNTING,C_TYPE_CARNIVORE,C_TYPE_NON_NEWBIE };
        final int[] itemsButchered = {leather, cookedMeat, farmersSalve, potion};

//        this.getBaseTemplate();
//        float ogStrength = this.baseSkillTree.get(BODY_STRENGTH).getNumber();
//        float ogStamina = this.baseSkillTree.get(BODY_STRENGTH).getNumber();
//        float ogControl = this.baseSkillTree.get(BODY_CONTROL).getNumber();

        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.trollwitchdoctor","Troll Witch Doctor",
                "Dis wont hurt a bit...",
                "model.creature.humanoid.troll.standard.witchdoctor",
                types,(byte)0, (short)5, (byte)0, (short)360, (short)20, (short)35,
                "sound.death.troll", "sound.death.troll", "sound.combat.hit.troll", "sound.combat.hit.troll",
                1.0F, 8.0F, 0.0F, 0.0F, 0.0F, 0.0F,
                0.8F, 0, itemsButchered, 10, 40, MATERIAL_MEAT_HUMANOID
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
        builder.skill(SOUL_STRENGTH, 20.0F);
        builder.skill(WEAPONLESS_FIGHTING, 50.0f);

        builder.hasHands(true);
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_STUDDED);
        builder.baseCombatRating(20.0f);
        builder.combatDamageType(Wound.TYPE_POISON);
        builder.handDamString("strike");
        builder.kickDamString("kick");
        builder.maxGroupAttackSize(2);
        builder.setCombatMoves(new int[]{CombatMove.STUN, CombatMove.DOUBLE_FIST});
        builder.maxPercentOfCreatures(0.04f);


        LOGGER.info("Initiator: Troll Witchdoctor");
        return builder;
    }

    public void addEncounters() {
        if (templateId == 0) return;
        new EncounterBuilder(Tiles.Tile.TILE_TREE.id).addCreatures(templateId, 1).build(1);
        new EncounterBuilder(Tiles.Tile.TILE_GRASS.id).addCreatures(templateId, 1).build(1);
        new EncounterBuilder(Tiles.Tile.TILE_CAVE.id, (byte) -1).addCreatures(templateId, 1).build(1);
    }

//    private void getBaseTemplate() {
//        try {
//            CreatureTemplate baseTemplate = CreatureTemplateFactory.getInstance().getTemplate(CreatureTemplateIds.TROLL_CID);
//            this.baseSkillTree = baseTemplate.getSkills().getSkillTree();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

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

public class Kraken implements ModCreature, CreatureTypes, ItemMaterials, SoundNames {
    private int templateId;
    private static final Logger LOGGER = Logger.getLogger(Initiator.class.getName());

    @Override
    public CreatureTemplateBuilder createCreateTemplateBuilder() {

        int[] types = {
                C_MOD_GREENISH,
                C_TYPE_SWIMMING,
                C_TYPE_MOVE_GLOBAL,
                C_TYPE_MONSTER,
                C_TYPE_AGG_HUMAN,
                C_TYPE_CARNIVORE,
                C_TYPE_NON_NEWBIE,
                C_TYPE_UNIQUE,
                C_TYPE_DETECTINVIS,
                C_TYPE_FLOATING,
                C_TYPE_SUBMERGED,
                C_TYPE_MISSION_OK,
                C_TYPE_MISSION_TRAITOR_OK,
                C_TYPE_FENCEBREAKER,
                C_TYPE_NO_REBIRTH,
                C_TYPE_STEALTH};
        //     int[] types = new int[]{8, 38, 13, 16, 29, 44, 48, 37, 60, 61};

        final int[] itemsButchered = {meat,meat,meat,meat,meat,eye, eye,inkSac};



        CreatureTemplateBuilder builder = new CreatureTemplateBuilder(
                "mod.creature.kraken","Kraken",
                "Larger specimen, like this one, have been known to pull whole ships down into the abyss.",
                "model.creature.kraken",
                types, (byte)9,(short)15, (byte)0, (short)2600, (short)600, (short)400,
//              TYPE, BodyType, Vision, Sex, CentimetersHigh, CentimetersLong, CentimetersWide
                DEATH_SNAKE_SND, DEATH_SNAKE_SND, HIT_SNAKE_SND, HIT_SNAKE_SND,
                0.5F, 10.0F, 10.0F, 18.0F, 0.0F, 0.0F, 0.6F, 1500, itemsButchered, 20, 94, MATERIAL_MEAT_SEAFOOD
        );


        this.templateId = builder.getTemplateId();
        builder.skill(BODY_STRENGTH, 50.0F);
        builder.skill(BODY_CONTROL, 50.0F);
        builder.skill(BODY_STAMINA, 50.0F);
        builder.skill(MIND_LOGICAL, 50.0F);
        builder.skill(MIND_SPEED, 50.0F);
        builder.skill(SOUL_STRENGTH, 50.0F);
        builder.skill(SOUL_DEPTH, 50.0F);
        builder.skill(WEAPONLESS_FIGHTING, 85.0F);
        builder.alignment(-99.0F);
        builder.combatDamageType(Wound.TYPE_SLASH);
        builder.maxAge(100);
        builder.armourType(ArmourTemplate.ARMOUR_TYPE_PLATE);
        builder.baseCombatRating(60.0f);
        builder.maxGroupAttackSize(20);
        builder.hasHands(false);
        builder.offZ(-1F);
        builder.maxPopulationOfCreatures(3);
        builder.setCombatMoves(new int[]{CombatMove.FIGHTAGG, CombatMove.THROW, CombatMove.SWEEP});
        builder.sizeModifier(200,200,200);
        builder.addPrimaryAttack(new AttackAction("strike", AttackIdentifier.STRIKE, new AttackValues(12.0F, 0.04F, 4.0F, 6, 1, (byte)0, false, 3, 1.4F)));
        builder.addSecondaryAttack(new AttackAction("slap", AttackIdentifier.KICK, new AttackValues(4.0F, 0.04F, 5.0F, 6, 1, (byte)0, false, 3, 2.1F)));
        builder.addSecondaryAttack(new AttackAction("bite", AttackIdentifier.BITE, new AttackValues(20.0F, 0.08F, 7.0F, 6, 1, (byte)3, false, 3, 2.0F)));

        LOGGER.info("Initiator: Kraken");
        return builder;
    }

    @Override
    public void addEncounters() {
        if (templateId == 0)
            return;
        if (Constants.SpawnKraken) {

            new EncounterBuilder(Tiles.Tile.TILE_KELP.id)
                    .addCreatures(templateId, 1)
                    .build(1);

        }

    }

}

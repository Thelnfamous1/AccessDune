package me.infamous.access_dune.common.entity.dune;

import me.infamous.access_dune.common.entity.ai.magic.AnimatableMagicGoal;
import me.infamous.access_dune.common.registry.AccessModEffects;
import me.infamous.access_dune.duck.DuneSinker;
import net.minecraft.entity.LivingEntity;

public class DuneDragGoal extends AnimatableMagicGoal<Dune, DuneMagicType> {

    public static final int SINK_TIMER = 200;

    public DuneDragGoal(Dune mage) {
        super(mage, DuneMagicType.DRAG);
    }

    @Override
    public boolean canUse() {
        return super.canUse() && this.mage.getTarget().hasEffect(AccessModEffects.DUNE_WRATH.get());
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && this.mage.getTarget().hasEffect(AccessModEffects.DUNE_WRATH.get());
    }

    @Override
    protected void useMagic() {
        LivingEntity target = this.mage.getTarget();
        DuneSinker.sink(target, SINK_TIMER);
    }
}

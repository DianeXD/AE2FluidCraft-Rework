package com.glodblock.github.nei.recipes.extractor;

import codechicken.nei.PositionedStack;
import com.glodblock.github.nei.object.IRecipeExtractor;
import com.glodblock.github.nei.object.OrderStack;
import com.glodblock.github.util.NEIUtil;
import gregtech.api.enums.ItemList;
import gregtech.common.items.GT_FluidDisplayItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.LinkedList;
import java.util.List;

public class GregTech5RecipeExtractor implements IRecipeExtractor {

    boolean removeSpecial;

    public GregTech5RecipeExtractor(boolean removeSpecial) {
        this.removeSpecial = removeSpecial;
    }

    @Override
    public List<OrderStack<?>> getInputIngredients(List<PositionedStack> rawInputs) {
        if (removeSpecial) removeSpecial(rawInputs);
        List<PositionedStack> compressed = NEIUtil.compress(rawInputs);
        List<OrderStack<?>> tmp = new LinkedList<>();
        for (int i = 0; i < compressed.size(); i ++) {
            if (compressed.get(i) == null) continue;
            ItemStack item = compressed.get(i).items[0];
            OrderStack<?> stack;
            if (item.getItem() instanceof GT_FluidDisplayItem) {
                NBTTagCompound aNBT = item.getTagCompound();
                int amt = (int) aNBT.getLong("mFluidDisplayAmount");
                if (amt <= 0) continue;
                stack = new OrderStack<>(new FluidStack(FluidRegistry.getFluid(item.getItemDamage()), amt), i);
                tmp.add(stack);
            } else {
                stack = OrderStack.pack(compressed.get(i), i);
                if (stack != null) tmp.add(stack);
            }
        }
        return tmp;
    }

    @Override
    public List<OrderStack<?>> getOutputIngredients(List<PositionedStack> rawOutputs) {
        List<PositionedStack> compressed = NEIUtil.compress(rawOutputs);
        List<OrderStack<?>> tmp = new LinkedList<>();
        for (int i = 0; i < compressed.size(); i ++) {
            if (compressed.get(i) == null) continue;
            ItemStack item = compressed.get(i).items[0];
            OrderStack<?> stack;
            if (item.getItem() instanceof GT_FluidDisplayItem) {
                NBTTagCompound aNBT = item.getTagCompound();
                int amt = (int) aNBT.getLong("mFluidDisplayAmount");
                if (amt <= 0) continue;
                stack = new OrderStack<>(new FluidStack(FluidRegistry.getFluid(item.getItemDamage()), amt), i);
                tmp.add(stack);
            } else {
                stack = OrderStack.pack(compressed.get(i), i);
                if (stack != null) tmp.add(stack);
            }
        }
        return tmp;
    }

    private void removeSpecial(List<PositionedStack> list) {
        for (int i = list.size() - 1; i >= 0; i --) {
            PositionedStack positionedStack = list.get(i);
            if (positionedStack != null &&
                (positionedStack.items[0].isItemEqual(ItemList.Tool_DataStick.get(1)) || positionedStack.items[0].isItemEqual(ItemList.Tool_DataOrb.get(1)))) {
                list.remove(i);
                break;
            }
        }
    }

}

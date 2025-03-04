package com.glodblock.github.inventory.gui;

import appeng.api.storage.ITerminalHost;
import appeng.container.implementations.ContainerCraftAmount;
import appeng.container.implementations.ContainerCraftingStatus;
import com.glodblock.github.client.gui.*;
import com.glodblock.github.client.gui.container.*;
import com.glodblock.github.common.parts.FCBasePart;
import com.glodblock.github.common.parts.PartSharedFluidBus;
import com.glodblock.github.common.tile.TileFluidPacketDecoder;
import com.glodblock.github.common.tile.TileFluidPatternEncoder;
import com.glodblock.github.common.tile.TileIngredientBuffer;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.List;

public enum GuiType {

    FLUID_BUS_IO(new PartGuiFactory<PartSharedFluidBus>(PartSharedFluidBus.class) {
        @Override
        protected Object createServerGui(EntityPlayer player, PartSharedFluidBus inv) {
            return new ContainerFluidIO(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, PartSharedFluidBus inv) {
            return new GuiFluidIO(player.inventory, inv);
        }
    }),

    INGREDIENT_BUFFER(new TileGuiFactory<TileIngredientBuffer>(TileIngredientBuffer.class) {
        @Override
        protected Object createServerGui(EntityPlayer player, TileIngredientBuffer inv) {
            return new ContainerIngredientBuffer(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, TileIngredientBuffer inv) {
            return new GuiIngredientBuffer(player.inventory, inv);
        }
    }),

    FLUID_PAT_TERM_CRAFTING_STATUS(new PartGuiFactory<FCBasePart>(FCBasePart.class) {
        @Override
        protected Object createServerGui(EntityPlayer player, FCBasePart inv) {
            return new ContainerCraftingStatus(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, FCBasePart inv) {
            return new GuiFluidPatternTerminalCraftingStatus(player.inventory, inv);
        }
    }),

    FLUID_PATTERN_TERMINAL(new PartGuiFactory<ITerminalHost>(ITerminalHost.class) {
        @Override
        protected Object createServerGui(EntityPlayer player, ITerminalHost inv) {
            return new ContainerFluidPatternTerminal(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, ITerminalHost inv) {
            return new GuiFluidPatternTerminal(player.inventory, inv);
        }
    }),

    FLUID_PATTERN_TERMINAL_EX(new PartGuiFactory<ITerminalHost>(ITerminalHost.class) {
        @Override
        protected Object createServerGui(EntityPlayer player, ITerminalHost inv) {
            return new ContainerFluidPatternTerminalEx(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, ITerminalHost inv) {
            return new GuiFluidPatternTerminalEx(player.inventory, inv);
        }
    }),

    FLUID_PATTERN_ENCODER(new TileGuiFactory<TileFluidPatternEncoder>(TileFluidPatternEncoder.class) {
        @Override
        protected Object createServerGui(EntityPlayer player, TileFluidPatternEncoder inv) {
            return new ContainerFluidPatternEncoder(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, TileFluidPatternEncoder inv) {
            return new GuiFluidPatternEncoder(player.inventory, inv);
        }
    }),

    FLUID_CRAFTING_CONFIRM(new PartGuiFactory<FCBasePart>(FCBasePart.class) {
        @Override
        protected Object createServerGui(EntityPlayer player, FCBasePart inv) {
            return new ContainerFluidCraftConfirm(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, FCBasePart inv) {
            return new GuiFluidCraftConfirm(player.inventory, inv);
        }
    }),

    FLUID_CRAFTING_AMOUNT(new PartGuiFactory<FCBasePart>(FCBasePart.class) {
        @Override
        protected Object createServerGui(EntityPlayer player, FCBasePart inv) {
            return new ContainerCraftAmount(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, FCBasePart inv) {
            return new GuiFluidCraftAmount(player.inventory, inv);
        }
    }),

    FLUID_PACKET_DECODER(new TileGuiFactory<TileFluidPacketDecoder>(TileFluidPacketDecoder.class) {
        @Override
        protected Object createServerGui(EntityPlayer player, TileFluidPacketDecoder inv) {
            return new ContainerFluidPacketDecoder(player.inventory, inv);
        }

        @Override
        protected Object createClientGui(EntityPlayer player, TileFluidPacketDecoder inv) {
            return new GuiFluidPacketDecoder(player.inventory, inv);
        }
    });

    public static final List<GuiType> VALUES = ImmutableList.copyOf(values());

    @Nullable
    public static GuiType getByOrdinal(int ordinal) {
        return ordinal < 0 || ordinal >= VALUES.size() ? null : VALUES.get(ordinal);
    }

    public final GuiFactory guiFactory;

    GuiType(GuiFactory guiFactory) {
        this.guiFactory = guiFactory;
    }

}

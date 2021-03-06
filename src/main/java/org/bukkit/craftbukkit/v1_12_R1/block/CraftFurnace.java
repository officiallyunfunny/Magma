package org.bukkit.craftbukkit.v1_12_R1.block;

import net.minecraft.tileentity.TileEntityFurnace;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventoryFurnace;
import org.bukkit.inventory.FurnaceInventory;

public class CraftFurnace extends CraftContainer<TileEntityFurnace> implements Furnace {

    public CraftFurnace(final Block block) {
        super(block, TileEntityFurnace.class);
    }

    public CraftFurnace(final Material material, final TileEntityFurnace te) {
        super(material, te);
    }

    @Override
    public FurnaceInventory getSnapshotInventory() {
        return new CraftInventoryFurnace(this.getSnapshot());
    }

    @Override
    public FurnaceInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }

        return new CraftInventoryFurnace(this.getTileEntity());
    }

    @Override
    public short getBurnTime() {
        return (short) this.getSnapshot().getField(0);
    }

    @Override
    public void setBurnTime(short burnTime) {
        this.getSnapshot().setField(0, burnTime);
    }

    @Override
    public short getCookTime() {
        return (short) this.getSnapshot().getField(2);
    }

    @Override
    public void setCookTime(short cookTime) {
        this.getSnapshot().setField(2, cookTime);
    }

    @Override
    public String getCustomName() {
        TileEntityFurnace furnace = this.getSnapshot();
        return furnace.hasCustomName() ? furnace.getName() : null;
    }

    @Override
    public void setCustomName(String name) {
        this.getSnapshot().setCustomInventoryName(name);
    }

    @Override
    public void applyTo(TileEntityFurnace furnace) {
        super.applyTo(furnace);

        if (!this.getSnapshot().hasCustomName()) {
            furnace.setCustomInventoryName(null);
        }
    }
}

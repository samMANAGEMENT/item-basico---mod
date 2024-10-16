package com.sammicop.decorock.core.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.AxisAlignedBB;

public class LightningItem extends Item {
    public LightningItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isClientSide) {
            // Generar el rayo en la dirección en la que el jugador está mirando
            generateLightningInDirection(worldIn, playerIn);
            playerIn.sendMessage(new StringTextComponent("¡Rayo generado!"), playerIn.getUUID());
        }
        return ActionResult.success(playerIn.getItemInHand(handIn));
    }

    private void generateLightningInDirection(World worldIn, PlayerEntity playerIn) {
        // Obtener la dirección en la que el jugador está mirando
        Vector3d lookVec = playerIn.getLookAngle();

        // Crear un raycast desde la posición del jugador
        Vector3d start = playerIn.getEyePosition(1.0F);
        Vector3d end = start.add(lookVec.scale(50)); // Lanza el rayo a 50 bloques de distancia

        // Usar RayTraceContext para el raycasting
        RayTraceResult rayTraceResult = worldIn.clip(new RayTraceContext(start, end, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, playerIn));

        // Verificar si hay un impacto
        if (rayTraceResult.getType() != RayTraceResult.Type.MISS) {
            // Obtener la posición de impacto
            BlockPos hitPos = new BlockPos(rayTraceResult.getLocation());

            // Crear y posicionar el rayo en la posición de impacto
            LightningBoltEntity lightningBolt = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, worldIn);
            lightningBolt.moveTo(hitPos.getX(), hitPos.getY(), hitPos.getZ());
            worldIn.addFreshEntity(lightningBolt); // Agrega el rayo al mundo

            // Causar daño a las entidades cercanas
            causeDamageToNearbyEntities(worldIn, hitPos.getX(), hitPos.getY(), hitPos.getZ());
        }
    }

    private void causeDamageToNearbyEntities(World worldIn, double x, double y, double z) {
        worldIn.getEntitiesOfClass(Entity.class, new AxisAlignedBB(x - 3, y - 3, z - 3, x + 3, y + 3, z + 3)).forEach(entity -> {
            if (entity instanceof LivingEntity && !(entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative())) {
                ((LivingEntity) entity).hurt(DamageSource.LIGHTNING_BOLT, 3.0F); // Causa 5 puntos de daño
            }
        });
    }
}